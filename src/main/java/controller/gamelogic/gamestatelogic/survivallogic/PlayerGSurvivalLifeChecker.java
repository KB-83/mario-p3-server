package controller.gamelogic.gamestatelogic.survivallogic;

import model.main_model.entity.enemy.Enemy;
import model.main_model.entity.player.Player;
import model.main_model.gamestrucure.GameState;

import java.awt.*;

public class PlayerGSurvivalLifeChecker extends PlayerSurvivalLifeChecker{
    private Player player;
    private long lastKickTimeByEnemy;
    public PlayerGSurvivalLifeChecker(GameState gameState, Player player) {
        super(gameState, player);
        this.player = player;
        player.getPlayerGameLog().setRemainingLifePercent(100);

    }

    @Override
    public void handleFalling() {
        kickPlayer();
        player.setWorldY(0);
        player.setCameraY(0);
        player.setWorldX(0);
        player.setCameraX(0);
    }

    @Override
    public void handleEnemyCollide(Enemy enemy, String position) {
        kickPlayer();
    }

    @Override
    public void kickPlayer() {
        if (System.currentTimeMillis() - lastKickTimeByEnemy > 2000) {
            player.getPlayerGameLog().setRemainingLifePercent(player.getPlayerGameLog().getRemainingLifePercent() - 10);
            lastKickTimeByEnemy = System.currentTimeMillis();
        }
    }
    public void handleDamageBomb(){}
    public void handleSward(){}
    public void handleHammer() {}//etc
}
