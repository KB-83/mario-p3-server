package controller.gamelogic.enemieslogic;


import controller.gamelogic.collisionlogic.EnemyCollisionHandler;
import model.main_model.entity.enemy.Enemy;
import model.main_model.gamestrucure.GameState;

public class KoopaController extends EnemyController{
    private EnemyCollisionHandler enemyCollisionHandler;
    private EnemyMovementHandler enemyMovementHandler;
    public KoopaController( Enemy enemy){
        super(enemy);
        setEnemyLifeChecker(new KoopaLifeChecker(enemy));
    }
    public void update() {
        enemyMovementHandler.updateEnemiesPosition();
        enemyCollisionHandler.applyCollisionEffects();
    }
}
