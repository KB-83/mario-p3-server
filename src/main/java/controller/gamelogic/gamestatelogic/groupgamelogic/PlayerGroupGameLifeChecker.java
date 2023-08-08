package controller.gamelogic.gamestatelogic.groupgamelogic;

import controller.gamelogic.playerlogic.PlayerLifeChecker;
import model.main_model.entity.enemy.Enemy;
import model.main_model.entity.player.Player;
import model.main_model.gamestrucure.GameState;
import util.Constant;

public class PlayerGroupGameLifeChecker extends PlayerLifeChecker {
    private GameState gameState;
    private Player player;
    private long lastKickTimeByEnemy;
    private boolean isDead = false;
    public PlayerGroupGameLifeChecker(GameState gameState, Player player) {
        super(gameState, player);
        this.gameState = gameState;
        this.player = player;

    }

    @Override
    public void handleFalling() {
        super.decreaseHeart();
        gameState.setScore(gameState.getScore() - 30);
        if (gameState.getScore() < 0) {
            gameState.setScore(0);
        }

    }

    @Override
    public void handleEnemyCollide(Enemy enemy, String position) {
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

    @Override
    public void kickPlayer() {
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
        else {
            player.getPlayerController().setLoosed(true);
            isDead = true;
            ((PlayerGroupGameRequestHandler)player.getPlayerController().getPlayerRequestHandler()).setDead(true);
        }
//        sound.play();
    }

    @Override
    public void decreaseHeart() {
        if (isDead) {
            return;
        }
        if (gameState.getRemainingHeart() <=1) {
            ((PlayerGroupGameRequestHandler)player.getPlayerController().getPlayerRequestHandler()).setDead(true);
            isDead = true;
            return;
        }
        super.decreaseHeart();
    }
}
