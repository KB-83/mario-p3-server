package controller.gamelogic.enemieslogic;


import controller.gamelogic.collisionlogic.EnemyCollisionHandler;
import model.main_model.entity.enemy.Enemy;
import model.main_model.entity.enemy.Goomba;
import model.main_model.gamestrucure.GameState;

public class GoombaController extends EnemyController{
    private EnemyCollisionHandler enemyCollisionHandler;
    private EnemyMovementHandler enemyMovementHandler;
    public GoombaController(GameState gameState , Enemy enemy){
        super(gameState,enemy);
        setEnemyLifeChecker(new GoombaLifeChecker(gameState,(Goomba) enemy));
        enemyMovementHandler = new EnemyMovementHandler(gameState);
        enemyCollisionHandler = new EnemyCollisionHandler(gameState,enemy);
    }
}
