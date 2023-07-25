package controller.gamelogic.enemieslogic;


import controller.gamelogic.collisionlogic.EnemyCollisionHandler;
import model.main_model.entity.enemy.Enemy;
import model.main_model.entity.enemy.Goomba;
import model.main_model.gamestrucure.GameState;

public class GoombaController extends EnemyController{
    private Goomba goomba;

    public GoombaController(Enemy enemy){
        super(enemy);
        this.goomba = (Goomba) enemy;

    }
    @Override
    public void initGameState(GameState gameState) {
        super.initGameState(gameState);
        setEnemyLifeChecker(new GoombaLifeChecker(gameState,goomba));
//        setEnemyLifeChecker(new SpinyLifeChecker(gameState,spiny));
    }
}
