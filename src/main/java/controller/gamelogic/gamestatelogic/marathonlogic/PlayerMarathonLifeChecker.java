package controller.gamelogic.gamestatelogic.marathonlogic;

import controller.game.Marathon;
import controller.gamelogic.playerlogic.PlayerLifeChecker;
import model.main_model.entity.enemy.Enemy;
import model.main_model.entity.player.Player;
import model.main_model.entity.player.V;
import model.main_model.gamestrucure.GameState;

public class PlayerMarathonLifeChecker extends PlayerLifeChecker {
    private Player player;
    private long lastKickTime = 0;
    public PlayerMarathonLifeChecker(GameState gameState, Player player) {
        super(gameState, player);
        this.player = player;
    }

    @Override
    public void update() {
        super.update();
        if (System.currentTimeMillis() - lastKickTime > Marathon.periodSlowDown * 1000 && lastKickTime != 0) {
            player.getPlayerController().getPlayerRequestHandler().setV(V.Mario.getV());
            player.setVX(V.Mario.getV());
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
        kickPlayer();
    }

    @Override
    public void kickPlayer() {
        if (System.currentTimeMillis() - lastKickTime > Marathon.periodSlowDown * 1000) {
            player.getPlayerController().getPlayerRequestHandler().setV((int) (V.Mario.getV()  * Marathon.multiplierSlowDown));
            player.setVX(V.Mario.getV() * Marathon.multiplierSlowDown);
            lastKickTime = System.currentTimeMillis();
        }
    }
}
