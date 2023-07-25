package controller.gamelogic.enemieslogic;


import controller.gamelogic.collisionlogic.EnemyCollisionHandler;
import model.main_model.entity.enemy.Bowser;
import model.main_model.entity.enemy.Enemy;
import model.main_model.entity.enemy.Goomba;
import model.main_model.gamestrucure.GameState;

public class BowserController extends EnemyController{
    private EnemyCollisionHandler enemyCollisionHandler;
    private EnemyMovementHandler enemyMovementHandler;
    private Bowser bowser;
    public BowserController(Enemy enemy){
        super(enemy);
        this.bowser = (Bowser) enemy;


    }

    @Override
    public void initGameState(GameState gameState) {
        super.initGameState(gameState);
        enemyMovementHandler = new EnemyMovementHandler(gameState);
        setEnemyLifeChecker(new BowserLifeChecker(bowser,gameState));
//        enemyCollisionHandler = new EnemyCollisionHandler(gameState,enemy);
    }
}
