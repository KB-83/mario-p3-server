package controller.gamelogic.enemieslogic;


import controller.gamelogic.collisionlogic.EnemyCollisionHandler;
import model.main_model.entity.enemy.Enemy;
import model.main_model.entity.enemy.Goomba;
import model.main_model.gamestrucure.GameState;

public class GoombaController extends EnemyController{

    public GoombaController(Enemy enemy){
        super(enemy);
        setEnemyLifeChecker(new GoombaLifeChecker((Goomba) enemy));
    }
    @Override
    public void initGameState(GameState gameState) {
        super.initGameState(gameState);
//        setEnemyLifeChecker(new SpinyLifeChecker(gameState,spiny));
    }
}
