package controller.gamelogic.gamestatelogic.survivallogic;

import controller.gamelogic.playerlogic.PlayerLifeChecker;
import model.main_model.entity.enemy.Enemy;
import model.main_model.entity.player.Player;
import model.main_model.gamestrucure.GameState;

import java.awt.*;

public class PlayerSurvivalLifeChecker extends PlayerLifeChecker {
    private Player player;
    private long lastKickTimeByEnemy;
    public PlayerSurvivalLifeChecker(GameState gameState, Player player) {
        super(gameState, player);
        this.player = player;
        player.getPlayerGameLog().setRemainingLifePercent(100);
        player.setTeamColor(Color.RED.getRGB());

    }

    @Override
    public void handleFalling() {

    }

    @Override
    public void handleEnemyCollide(Enemy enemy, String position) {
        if (System.currentTimeMillis() - lastKickTimeByEnemy > 2000) {
            player.getPlayerGameLog().setRemainingLifePercent(player.getPlayerGameLog().getRemainingLifePercent() - 10);
            lastKickTimeByEnemy = System.currentTimeMillis();
        }
    }

    @Override
    public void kickPlayer() {

    }
    public void handleDamageBomb(){}
    public void handleSward(){}
    public void handleHammer() {}//etc
}
