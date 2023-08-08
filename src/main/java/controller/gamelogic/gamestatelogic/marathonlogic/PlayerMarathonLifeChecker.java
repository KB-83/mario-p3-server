package controller.gamelogic.gamestatelogic.marathonlogic;

import controller.game.Marathon;
import controller.gamelogic.playerlogic.PlayerLifeChecker;
import model.main_model.entity.enemy.Enemy;
import model.main_model.entity.player.Player;
import model.main_model.entity.player.V;
import model.main_model.gamestrucure.GameState;
import util.Constant;

public class PlayerMarathonLifeChecker extends PlayerLifeChecker {
    private Player player;
    private long lastKickTime = 0;
    private GameState gameState;
    private long lastKickTimeByEnemy;
    public PlayerMarathonLifeChecker(GameState gameState, Player player) {
        super(gameState, player);
        this.player = player;
        this.gameState = gameState;
    }

    @Override
    public void update() {
        super.update();
        if (System.currentTimeMillis() - lastKickTime > Marathon.periodSlowDown * 1000 && lastKickTime != 0) {
            player.setVXMeasure(V.Mario.getV());//todo : toye loop nabashe
            player.setVX(player.getVXMeasure());
        }
    }

    @Override
    public void handleFalling() {
        kickPlayer();
        player.setWorldY(0);
        player.setCameraY(0);
        player.setWorldX(player.getWorldX() + 100);
    }

    @Override
    public void handleEnemyCollide(Enemy enemy, String position) {
        if (System.currentTimeMillis() - lastKickTimeByEnemy >= 3000) {
            lastKickTimeByEnemy = System.currentTimeMillis();
            kickPlayer();
        }
    }

    @Override
    public void kickPlayer() {
        if (System.currentTimeMillis() - lastKickTime > Marathon.periodSlowDown * 1000) {
            player.setVXMeasure((int) (V.Mario.getV()  * Marathon.multiplierSlowDown));
            player.setVX(player.getVXMeasure());
            lastKickTime = System.currentTimeMillis();
        }
        if (!player.isFire() && !player.isMega()) {
            player.getPlayerController().setLoosed(true);
            player.getPlayerController().setLifeTime(gameState.getCurrentSection().getRemainingTime());
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
    }
}
