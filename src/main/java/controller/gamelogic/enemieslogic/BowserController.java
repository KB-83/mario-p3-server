package controller.gamelogic.enemieslogic;


import controller.gamelogic.collisionlogic.EnemyCollisionHandler;
import model.main_model.entity.enemy.Bowser;
import model.main_model.entity.enemy.Enemy;
import model.main_model.gamestrucure.GameState;

public class BowserController extends EnemyController{
    private EnemyCollisionHandler enemyCollisionHandler;
    private EnemyMovementHandler enemyMovementHandler;
    public BowserController(Enemy enemy){
        super(enemy);

    }

    @Override
    public void initGameState(GameState gameState) {
        super.initGameState(gameState);
//        setEnemyLifeChecker(new BowserLifeChecker(gameState,(Bowser) enemy));
        enemyMovementHandler = new EnemyMovementHandler(gameState);
//        enemyCollisionHandler = new EnemyCollisionHandler(gameState,enemy);
    }
}
