package controller.gamelogic.gamestatelogic;


import controller.gamelogic.gravitylogic.GravityEffectsHandler;
import controller.mapper.GameCloner;
import model.main_model.Client;
import model.main_model.backgroundobject.pipe.SimpleTelePipe;
import model.main_model.backgroundobject.pipe.TelePlantPipe;
import model.main_model.entity.enemy.Enemy;
import model.main_model.entity.item.Item;
import model.main_model.gamestrucure.Game;
import model.main_model.gamestrucure.GameState;
import model.main_model.levelstructure.Level;
import model.main_model.levelstructure.Section;
import util.Constant;
import util.Loop;

import java.util.ArrayList;
import java.util.List;
//todo : enemy controllers and actually controllers are not clean. clean theme

public class GameStateController extends Thread{
    private GameState gameState;
    private ArrayList<Client> clients;
    private GravityEffectsHandler gravityEffectsHandler;


    public GameStateController(ArrayList<Client> clients) {
        this.clients = clients;
    }

    public void update(){
        //player updates
        if (gameState.isPaused()) {
            return;
        }
        // this is gravity
        // todo : improve it
        // todo : game logic can be handel here if you part it
        if (gravityEffectsHandler!= null) {
            gravityEffectsHandler.applyEffects();
        }
        //check collision
        //playerUpdates
        // did it in marathon subclass maybe todo: have to bring clients arraylist here
        // enemies update
        if(gameState.getCurrentSection().getEnemies() != null) {
            for (Enemy enemy : gameState.getCurrentSection().getEnemies()) {
                enemy.getEnemyController().update();
            }
        }
        // item
        if(gameState.getCurrentSection().getItems() != null) {
            for (Item item : gameState.getCurrentSection().getItems()) {
                item.getItemController().update();
            }
        }
//        gameState.getSwardCollisionHandler().applyCollisionEffects();
//        gameState.getBulletCollisionHandler().applyCollisionEffects();
//

    }
    public void nextSection() {
        if(gameState.getSectionNumber() < gameState.getLevels()[gameState.getLevelNumber()-1].getSections().length) {
            gameState.setCurrentSection(gameState.getLevels()[gameState.getLevelNumber() - 1].getSections()[gameState.getSectionNumber() - 1 + 1]);
            gameState.setSectionNumber(gameState.getSectionNumber() + 1);
            gameState.setRemainingTime(gameState.getCurrentSection().getTime());
//            gameState.getMario().setCameraX(0);
//            gameState.getMario().setWorldX(0);
            //todo : test
//            clients.get(0).getPlayer().setCameraX(0);
//            clients.get(0).getPlayer().setWorldX(0);
            //todo : test

        }
        else {
            changeLevel();
        }
    }
    public void changeSection(Section section,int sectionNumber) {
        gameState.setCurrentSection(section);
        gameState.setSectionNumber(sectionNumber);
        gameState.setRemainingTime(gameState.getCurrentSection().getTime());
        //todo : test
//        clients.get(0).getPlayer().setCameraX(0);
//        clients.get(0).getPlayer().setWorldX(0);
        //todo : test

//        gameState.getMario().setCameraX(0);
//        gameState.getMario().setWorldX(0);
    }
    private void changeLevel() {
        if(gameState.getLevelNumber() < gameState.getLevels().length) {
            gameState.setCurrentLevel(gameState.getLevels()[gameState.getLevelNumber() - 1+1]);
            gameState.setLevelNumber(gameState.getLevelNumber()+1);
            changeSection(gameState.getCurrentLevel().getSections()[0],1 );

        }
        System.out.println("you won");
    }
    public GameState createGameState(Game game) {
        Game game1 = GameCloner.cloneGame(game);
        GameState gameState = new GameState(this);
        //todo : let player use its own selected player :)
        setGameStateDependencies(game1, gameState);
        setGameStateControllerDependencies(gameState);
        //todo : check if its good()
        startGameState(gameState);
        this.gameState = gameState;
        return gameState;
    }

    public void startGameState(GameState gameState){
        //todo : doing gamestATE Timers run
        //test
//        gameState.getSound().play();
//        gameState.getSound().loop();
        Loop gameLoop = new Loop(gameState.getGameStateController(), Constant.FPS);
        gameState.setGameLoop(gameLoop);
        gameLoop.start();

    }
    private void setGameStateDependencies(Game game, GameState gameState) {
        gameState.setLevels(game.getLevels());
        gameState.setName(game.getName());
        for (Level level :gameState.getLevels()) {
            for (Section section : level.getSections()) {
                setControllersGameState(gameState,section);
            }
        }
        gameState.setCurrentLevel(game.getLevels()[0]);
        gameState.setCurrentSection(game.getLevels()[0].getSections()[0]);
        gameState.setCoins(0);
        gameState.setLevelNumber(1);
        gameState.setSectionNumber(1);
        gameState.setPaused(false);
        gameState.setRemainingHeart(game.getHearts());
        gameState.setRemainingTime(gameState.getCurrentSection().getTime());
    }
    private void setControllersGameState(GameState gameState,Section section){
        if (section.getEnemies() != null) {
            for (int i = 0; i < section.getEnemies().length; i++) {
                section.getEnemies()[i].getEnemyController().initGameState(gameState);
            }
        }
        //pipe
        if (section.getPipes() != null) {
            for (int i = 0; i < section.getPipes().length; i++) {
                if (section.getPipes()[i].getClass() == TelePlantPipe.class) {
                    setControllersGameState(gameState, ((TelePlantPipe) section.getPipes()[i]).getTeleSection());
                } else if (section.getPipes()[i].getClass() == SimpleTelePipe.class) {
                    setControllersGameState(gameState, ((SimpleTelePipe) section.getPipes()[i]).getTeleSection());
                }
            }
        }
        // item
        if (section.getItems() != null) {
            for (int i = 0; i < section.getItems().length; i++) {
                section.getItems()[i].getItemController().initGameState(gameState);
            }
        }
    }
    private void setGameStateControllerDependencies(GameState gameState) {
        gravityEffectsHandler = new GravityEffectsHandler(gameState,clients);
    }
    public double returnPR() {
        int totalLength = 0;
        double progressLength = 0;
        boolean reachSection = false;
        for (Section section : gameState.getCurrentLevel().getSections()){
            totalLength += Constant.BACKGROUND_TILE_SIZE * section.getLength();
            if (section.equals(gameState.getCurrentSection())){
//                progressLength += gameState.getMario().getWorldX();
                reachSection = true;
            }
            if (!reachSection){
                progressLength += Constant.BACKGROUND_TILE_SIZE * section.getLength();
            }
        }
        double PR = (progressLength / totalLength) * gameState.getCoins();
        return PR;
    }
    public void muteRequest() {
//        Sound.isMute = ! Sound.isMute;
//        if (Sound.isMute) {
//            gameState.getSound().stop();
//        }
//        else {
//            gameState.getSound().play();
//            gameState.getSound().loop();
//        }
    }
    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
    }
}
