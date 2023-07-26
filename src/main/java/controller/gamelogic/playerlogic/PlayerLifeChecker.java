package controller.gamelogic.playerlogic;

import model.main_model.backgroundobject.CheckPoint;
import model.main_model.entity.enemy.Enemy;
import model.main_model.entity.player.Player;
import model.main_model.gamestrucure.GameState;
import model.main_model.levelstructure.Section;
import util.Constant;

import java.util.ArrayList;

public class PlayerLifeChecker {
    private GameState gameState;
    private long lastKickTimeByEnemy;
    private Player player;
//    private Sound sound;
    public PlayerLifeChecker(GameState gameState,Player player) {
        this.gameState = gameState;
        this.player = player;//new
//        sound = new Sound("HEART-LOOSE");
    }
    public void checkIfHurt() {

        if (player.getWorldY() > Constant.PANEL_ROWS * Constant.BACKGROUND_TILE_SIZE) {
            handleFalling();
        }
        if (gameState.getRemainingTime() < 0){
            handleOutOfTime();
        }

    }
    private void handleFalling(){
        decreaseHeart();
        gameState.setScore(gameState.getScore() - 30);
        if (gameState.getScore() < 0) {
            gameState.setScore(0);
        }
    }
    private void handleOutOfTime(){}
    public void handleEnemyCollide(Enemy enemy, String position){
        if (player.isUnHeat()){
            return;
        }
        if (position.equals("DOWN")) {
            return;
//            pass to EnemyLifeChecker
        }
        else {
            //cool down
            if (System.currentTimeMillis() - lastKickTimeByEnemy >= 3000) {
                lastKickTimeByEnemy = System.currentTimeMillis();
                kickPlayer();
            }
        }
    }
    private void kickPlayer(){
        if (!player.isFire() && !player.isMega()) {
            decreaseHeart();
            return;
        }
//        sound.setSound("KICK");
        if (player.isFire()){
            player.setFire(false);
            player.setMega(true);
//            gameState.setMarioState(1);
        }

        else if (player.isMega()){
            player.setMega(false);
            player.setHeight(Constant.BACKGROUND_TILE_SIZE);
//            gameState.setMarioState(0);
        }
//        sound.play();
    }
    private void decreaseHeart(){
//        if (gameState.getRemainingHeart() <=1) {
//            killPlayer();
//            return;
//        }
//        gameState.getSound().stop();
//        sound.setSound("HEART-LOOSE");
//        sound.play();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//        gameState.getSound().play();
        gameState.setRemainingHeart(gameState.getRemainingHeart() - 1);
        gameState.setScore(gameState.getScore() - 20);
        if (gameState.getScore() < 0) {
            gameState.setScore(0);
        }
        player.setMega(false);
        player.setFire(false);
        player.setHeight(Constant.BACKGROUND_TILE_SIZE);
//        gameState.setMarioState(0);
//        gameState.getMario().getPlayerRequestHandler().jumpRequest();
        CheckPoint checkPoint = null;
        Section checkPointSection = null;
        int sectionNum = 0;
        int i = 0;
        int savedCheckpoints = 0;
        for (Section section : gameState.getCurrentLevel().getSections()) {
            i++;
            if (section.getCheckPoint() != null && section.getCheckPoint().getSaved()) {
                checkPoint = section.getCheckPoint();
                checkPointSection = section;
                sectionNum = i;
                savedCheckpoints++;
            }
        }
        int Dn = (int) (((savedCheckpoints+1 * gameState.getCoins()) + gameState.getGameStateController().returnPR())/(savedCheckpoints+4));
        gameState.setCoins(gameState.getCoins()-Dn);

        if (checkPoint != null) {
            gameState.getGameStateController().changeSection(checkPointSection,sectionNum);
            player.setWorldX(checkPoint.getCol() * Constant.BACKGROUND_TILE_SIZE);
            player.setWorldY(checkPoint.getRow() * Constant.BACKGROUND_TILE_SIZE);
            if(gameState.getCurrentSection().getLength() - checkPoint.getCol() - 3 >= Constant.PANEL_WIDTH/Constant.BACKGROUND_TILE_SIZE) {
                player.setCameraX(2 * Constant.BACKGROUND_TILE_SIZE);
//                    player.setCameraY();
            }
            else {
                player.setCameraX(player.getWorldX());
            }
        }
        else {
            player.setWorldX(0);
            player.setWorldY(7 * Constant.BACKGROUND_TILE_SIZE);
            player.setCameraX(0);
            gameState.getGameStateController().changeSection(gameState.getCurrentLevel().getSections()[0],1);
        }
    }
    private void killPlayer(){}
}
