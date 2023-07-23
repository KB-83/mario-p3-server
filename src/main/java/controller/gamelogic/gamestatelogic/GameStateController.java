package controller.gamelogic.gamestatelogic;


import controller.gamelogic.enemieslogic.*;
import controller.gamelogic.gravitylogic.GravityEffectsHandler;
import controller.gamelogic.itemlogic.ItemController;
import controller.mapper.GameCloner;
import model.main_model.backgroundobject.pipe.Pipe;
import model.main_model.backgroundobject.pipe.SimpleTelePipe;
import model.main_model.backgroundobject.pipe.TelePlantPipe;
import model.main_model.entity.enemy.Enemy;
import model.main_model.entity.enemy.Goomba;
import model.main_model.entity.enemy.Koopa;
import model.main_model.entity.enemy.Spiny;
import model.main_model.entity.item.Item;
import model.main_model.entity.player.Mario;
import model.main_model.gamestrucure.Game;
import model.main_model.gamestrucure.GameState;
import model.main_model.levelstructure.Level;
import model.main_model.levelstructure.Section;
import util.Constant;
import util.Loop;

public class GameStateController extends Thread{
    private GameState gameState;
    private GravityEffectsHandler gravityEffectsHandler;

    public GameStateController() {

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
//        gameState.getMario().getPlayerController().update();
        // enemies update
        if(gameState.getCurrentSection().getEnemies() != null) {
            for (Enemy enemy : gameState.getCurrentSection().getEnemies()) {
                enemy.getEnemyController().update();
            }
        }
        // item
        if(gameState.getCurrentSection().getItems() != null) {
            for (Item item : gameState.getCurrentSection().getItems()) {
//                item.getItemController().update();
            }
        }
//        gameState.getSwardCollisionHandler().applyCollisionEffects();
//        gameState.getBulletCollisionHandler().applyCollisionEffects();


    }
    public void nextSection() {
        if(gameState.getSectionNumber() < gameState.getLevels()[gameState.getLevelNumber()-1].getSections().length) {
            gameState.setCurrentSection(gameState.getLevels()[gameState.getLevelNumber() - 1].getSections()[gameState.getSectionNumber() - 1 + 1]);
            gameState.setSectionNumber(gameState.getSectionNumber() + 1);
            gameState.setRemainingTime(gameState.getCurrentSection().getTime());
//            gameState.getMario().setCameraX(0);
//            gameState.getMario().setWorldX(0);

        }
        else {
            changeLevel();
        }
    }
    public void changeSection(Section section,int sectionNumber) {
        gameState.setCurrentSection(section);
        gameState.setSectionNumber(sectionNumber);
        gameState.setRemainingTime(gameState.getCurrentSection().getTime());
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
//    public GameState createGameState(GameState gameState, LogicManager logicManager) {
//        gameState.setGameStateController(this);
//        gameState.setDefaultDependencies();
//        //todo : let player use its own selected player :)
//        setGameStateDependencies(gameState,logicManager);
//        setGameStateControllerDependencies(gameState);
//        //todo : check if its good()
//        startGameState(gameState,logicManager);
//        this.gameState = gameState;
//        return gameState;
//    }

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
//        gameState.setCurrentUser(logicManager.getUser());
        gameState.setLevels(game.getLevels());
        gameState.setName(game.getName());
        Mario mario = new Mario();
        mario.setWorldY(7 * 48);
        mario.setCameraY(7 * 48);
        mario.setImageAddress(game.getMarioState()+"Right1");
        mario.setWidth(Constant.BACKGROUND_TILE_SIZE);
        mario.setHeight(Constant.BACKGROUND_TILE_SIZE);
        if (game.getMarioState() > 0) {
            mario.setHeight(2 * mario.getHeight());
            mario.setWorldY(mario.getWorldY() - 48);
            mario.setCameraY(mario.getCameraY() - 48);
            if (game.getMarioState() == 1) {
                mario.setMega(true);
            }
            else {
                mario.setFire(true);
            }
        }
        //todo : sound test
//        gameState.setSound(new Sound());
//        gameState.getSound().setFile(0);
//        gameState.getSound().play();
        //
//        todo : use it as a method
        for (Level level :gameState.getLevels()) {
            for (Section section : level.getSections()) {
                if (section.getEnemies() != null) {
                for (int i = 0; i < section.getEnemies().length; i++) {
                    Enemy enemy = section.getEnemies()[i];
                    String s = enemy.getClass().getSimpleName();
                    switch (s){
                        case "Koopa":
                            ((Koopa)enemy).setEnemyController(new KoopaController(gameState, enemy));
                            break;
                        case "Goomba":
                            ((Goomba)enemy).setEnemyController(new GoombaController(gameState, enemy));
                            break;
                        case "Spiny":
                            ((Spiny)enemy).setEnemyController(new SpinyController(gameState, enemy));
                            break;
                        case "Bowser":
                            enemy.setEnemyController(new BowserController(gameState, enemy));
                            break;
                        case "Plant":
                            enemy.setEnemyController(new PlantController(gameState, enemy));
                            break;
                    }
                }
                if (section.getPipes() != null) {
                    for (int j = 0; j < section.getPipes().length; j++) {
                        Pipe pipe = section.getPipes()[j];
                        if (pipe.getClass() == TelePlantPipe.class) {
                            for (int k = 0; k < ((TelePlantPipe) pipe).getTeleSection().getEnemies().length; k++) {
                                Enemy enemy = ((TelePlantPipe) pipe).getTeleSection().getEnemies()[k];
                                String s = enemy.getClass().getSimpleName();
                                switch (s){
                                    case "Koopa":
                                        ((Koopa)enemy).setEnemyController(new KoopaController(gameState, enemy));
                                        break;
                                    case "Goomba":
                                        ((Goomba)enemy).setEnemyController(new GoombaController(gameState, enemy));
                                        break;
                                    case "Spiny":
                                        ((Spiny)enemy).setEnemyController(new SpinyController(gameState, enemy));
                                        break;
                                    case "Bowser":
                                        enemy.setEnemyController(new BowserController(gameState, enemy));
                                        break;
                                    case "Plant":
                                        enemy.setEnemyController(new PlantController(gameState, enemy));
                                        break;
                                }
                            }
                        } else if (pipe.getClass() == SimpleTelePipe.class) {
                            for (int k = 0; k < ((TelePlantPipe) pipe).getTeleSection().getEnemies().length; k++) {
                                Enemy enemy = ((TelePlantPipe) pipe).getTeleSection().getEnemies()[k];
                                enemy.setEnemyController(new EnemyController(gameState, enemy));
                            }
                        }
                    }
                }
            }
            }
        }
        for (Level level :gameState.getLevels()){
            for (Section section : level.getSections()){
                if (section.getItems() != null){
                    for (Item item : section.getItems()){
                        item.setItemController(new ItemController(item,gameState));
                    }
                }
                if (section.getPipes() != null) {
                    for (int j = 0; j < section.getPipes().length; j++) {
                        Pipe pipe = section.getPipes()[j];
                        if (pipe.getClass() == TelePlantPipe.class) {
                            for (int k = 0; k < ((TelePlantPipe) pipe).getTeleSection().getItems().length; k++) {
                                Item item = ((TelePlantPipe) pipe).getTeleSection().getItems()[k];
                                item.setItemController(new ItemController(item,gameState));
                            }
                        } else if (pipe.getClass() == SimpleTelePipe.class) {
                            for (int k = 0; k < ((TelePlantPipe) pipe).getTeleSection().getItems().length; k++) {
                                Item item = ((TelePlantPipe) pipe).getTeleSection().getItems()[k];
                                item.setItemController(new ItemController(item,gameState));
                            }
                        }
                    }
                }
            }
        }
        gameState.setMarioState(gameState.getMarioState());
        gameState.setMario(mario);
        gameState.setCurrentLevel(game.getLevels()[0]);
        gameState.setCurrentSection(game.getLevels()[0].getSections()[0]);
        gameState.getMario().setPlayerController(new PlayerController(gameState));
        gameState.setCoins(0);
        gameState.setLevelNumber(1);
        gameState.setSectionNumber(1);
        gameState.setPaused(false);
        gameState.setRemainingHeart(game.getHearts());
        gameState.setRemainingTime(gameState.getCurrentSection().getTime());
        gameState.setScore(0);
    }
//    private void setGameStateDependencies(GameState gameState,LogicManager logicManager) {
//        gameState.setCurrentUser(logicManager.getUser());
//        gameState.setCurrentLevel(gameState.getLevels()[gameState.getLevelNumber()-1]);
//        gameState.getMario().setPlayerController(new PlayerController(gameState));
//        for (Level level :gameState.getLevels()) {
//            for (Section section : level.getSections()) {
//                if (section.getEnemies() != null) {
//                    for (int i = 0; i < section.getEnemies().length; i++) {
//                        Enemy enemy = section.getEnemies()[i];
//                        String s = enemy.getClass().getSimpleName();
//                        switch (s){
//                            case "Koopa":
//                                ((Koopa)enemy).setEnemyController(new KoopaController(gameState, enemy));
//                                break;
//                            case "Goomba":
//                                ((Goomba)enemy).setEnemyController(new GoombaController(gameState, enemy));
//                                break;
//                            case "Spiny":
//                                ((Spiny)enemy).setEnemyController(new SpinyController(gameState, enemy));
//                                break;
//                            case "Bowser":
//                                enemy.setEnemyController(new BowserController(gameState, enemy));
//                                break;
//                            case "Plant":
//                                enemy.setEnemyController(new PlantController(gameState, enemy));
//                                break;
//                        }
//                    }
//                    if (section.getPipes() != null) {
//                        for (int j = 0; j < section.getPipes().length; j++) {
//                            Pipe pipe = section.getPipes()[j];
//                            if (pipe.getClass() == TelePlantPipe.class) {
//                                for (int k = 0; k < ((TelePlantPipe) pipe).getTeleSection().getEnemies().length; k++) {
//                                    Enemy enemy = ((TelePlantPipe) pipe).getTeleSection().getEnemies()[k];
//                                    String s = enemy.getClass().getSimpleName();
//                                    switch (s){
//                                        case "Koopa":
//                                            ((Koopa)enemy).setEnemyController(new KoopaController(gameState, enemy));
//                                            break;
//                                        case "Goomba":
//                                            ((Goomba)enemy).setEnemyController(new GoombaController(gameState, enemy));
//                                            break;
//                                        case "Spiny":
//                                            ((Spiny)enemy).setEnemyController(new SpinyController(gameState, enemy));
//                                            break;
//                                        case "Bowser":
//                                            enemy.setEnemyController(new BowserController(gameState, enemy));
//                                            break;
//                                        case "Plant":
//                                            enemy.setEnemyController(new PlantController(gameState, enemy));
//                                            break;
//                                    }
//                                }
//                            } else if (pipe.getClass() == SimpleTelePipe.class) {
//                                for (int k = 0; k < ((TelePlantPipe) pipe).getTeleSection().getEnemies().length; k++) {
//                                    Enemy enemy = ((TelePlantPipe) pipe).getTeleSection().getEnemies()[k];
//                                    enemy.setEnemyController(new EnemyController(gameState, enemy));
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        for (Level level :gameState.getLevels()){
//            for (Section section : level.getSections()){
//                if (section.getItems() != null){
//                    for (Item item : section.getItems()){
//                        item.setItemController(new ItemController(item,gameState));
//                    }
//                }
//                if (section.getPipes() != null) {
//                    for (int j = 0; j < section.getPipes().length; j++) {
//                        Pipe pipe = section.getPipes()[j];
//                        if (pipe.getClass() == TelePlantPipe.class) {
//                            for (int k = 0; k < ((TelePlantPipe) pipe).getTeleSection().getItems().length; k++) {
//                                Item item = ((TelePlantPipe) pipe).getTeleSection().getItems()[k];
//                                item.setItemController(new ItemController(item,gameState));
//                            }
//                        } else if (pipe.getClass() == SimpleTelePipe.class) {
//                            for (int k = 0; k < ((TelePlantPipe) pipe).getTeleSection().getItems().length; k++) {
//                                Item item = ((TelePlantPipe) pipe).getTeleSection().getItems()[k];
//                                item.setItemController(new ItemController(item,gameState));
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        gameState.setCurrentSection(gameState.getCurrentLevel().getSections()[gameState.getSectionNumber()-1]);
//        gameState.setPaused(false);
////        gameState.setRemainingTime(gameState.getCurrentSection().getTime());
//    }
    private void setGameStateControllerDependencies(GameState gameState) {
        gravityEffectsHandler = new GravityEffectsHandler(gameState);
    }
//    public void checkPointRequest(String s) {
//        double PR = returnPR();
//        gameState.getMario().setVX(0);
//        switch (s){
//            case "Save CheckPoint":
//                gameState.getWaitingCheckpoint().setSaved(true);
//                gameState.setCoins((int) (gameState.getCoins() - PR));
//                gameState.setPaused(false);
//                GameState[] gameStates = gameState.getCurrentUser().getSavedGames();
//                for (int i = 0;i < gameStates.length ; i++){
//                    if (gameStates[i].getName().equals(gameState.getName())){
//                        gameStates[i] = gameState;
//                        gameState.getCurrentUser().setSavedGames(gameStates);
//                        Saver.getSaver().saveUser(gameState.getCurrentUser(),false);
//                        return;
//                    }
//                }
//                if (gameStates.length == 1){
//                    gameStates[1] = gameState;
//                    gameState.getCurrentUser().setSavedGames(gameStates);
//                    Saver.getSaver().saveUser(gameState.getCurrentUser(),false);
//                    return;
//                }
//                else if (gameStates.length == 2){
//                    gameStates[2] = gameState;
//                    gameState.getCurrentUser().setSavedGames(gameStates);
//                    Saver.getSaver().saveUser(gameState.getCurrentUser(),false);
//                    return;
//                }
//            case "Get Coins":
//                gameState.setCoins( (gameState.getCoins()+ (int)(PR/4)));
//                gameState.setWaitingCheckpoint(null);
//                gameState.getCurrentSection().setCheckPoint(null);
//                gameState.setPaused(false);
//                break;
//        }
//    }
//    public void saveAndPauseRequest(){
//        GameState[] gameStates = gameState.getCurrentUser().getSavedGames();
//        if (gameStates != null) {
//            for (int i = 0; i < gameStates.length; i++) {
//                if (gameStates[i].getName().equals(gameState.getName())) {
//                    gameStates[i] = gameState;
//                    gameState.getCurrentUser().setSavedGames(gameStates);
//                    Saver.getSaver().saveUser(gameState.getCurrentUser(), false);
//                }
//            }
//        }
//        if (gameStates == null){
//            gameStates = new GameState[]{gameState};
//            gameState.getCurrentUser().setSavedGames(gameStates);
//            Saver.getSaver().saveUser(gameState.getCurrentUser(),false);
//        }
//        PanelsManagerCard cardPanel = this.gameState.getCurrentUser().getLogicManager().getGraphicManager().getFrame().getPanelsManagerCard();
//        cardPanel.getCardLayout().show(cardPanel,"mainMenu");
//        gameState.getGameloop().kill();
//        gameState.getSound().stop();
//        this.gameState = null;
//    }
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
}
