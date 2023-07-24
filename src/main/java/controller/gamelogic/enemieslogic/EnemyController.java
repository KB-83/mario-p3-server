package controller.gamelogic.enemieslogic;


import controller.gamelogic.collisionlogic.EnemyCollisionHandler;
import model.main_model.entity.enemy.Enemy;
import model.main_model.gamestrucure.GameState;

public class EnemyController {
    private EnemyCollisionHandler enemyCollisionHandler;
    private EnemyMovementHandler enemyMovementHandler;
    private EnemyLifeChecker enemyLifeChecker;
    private Enemy enemy;
    private GameState gameState;

    public EnemyController(Enemy enemy){
        this.enemy = enemy;

    }
    public void update() {
        enemyMovementHandler.updateEnemiesPosition();
        enemyCollisionHandler.applyCollisionEffects();
        enemyLifeChecker.checkLife(enemy);
    }
    public void initGameState(GameState gameState) {
        this.gameState = gameState;
        enemyMovementHandler = new EnemyMovementHandler(gameState);
        enemyCollisionHandler = new EnemyCollisionHandler(gameState,enemy);
    }

    public GameState getGameState() {
        return gameState;
    }

    public EnemyCollisionHandler getEnemyCollisionHandler() {
        return enemyCollisionHandler;
    }

    public void setEnemyCollisionHandler(EnemyCollisionHandler enemyCollisionHandler) {
        this.enemyCollisionHandler = enemyCollisionHandler;
    }

    public EnemyMovementHandler getEnemyMovementHandler() {
        return enemyMovementHandler;
    }

    public void setEnemyMovementHandler(EnemyMovementHandler enemyMovementHandler) {
        this.enemyMovementHandler = enemyMovementHandler;
    }

    public EnemyLifeChecker getEnemyLifeChecker() {
        return enemyLifeChecker;
    }

    public void setEnemyLifeChecker(EnemyLifeChecker enemyLifeChecker) {
        this.enemyLifeChecker = enemyLifeChecker;
    }
}
