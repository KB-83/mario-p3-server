package controller.gamelogic.enemieslogic;


import controller.gamelogic.collisionlogic.EnemyCollisionHandler;
import model.main_model.entity.enemy.Enemy;
import model.main_model.entity.enemy.Koopa;
import model.main_model.gamestrucure.GameState;

public class KoopaController extends EnemyController{
    private Koopa koopa;
    public KoopaController( Enemy enemy){
        super(enemy);
        this.koopa = (Koopa) enemy;

    }
    public void update() {
        getEnemyMovementHandler().updateEnemiesPosition();
        getEnemyCollisionHandler().applyCollisionEffects();
    }

    @Override
    public void initGameState(GameState gameState) {
        super.initGameState(gameState);
        setEnemyLifeChecker(new KoopaLifeChecker(koopa,gameState));
    }
}
