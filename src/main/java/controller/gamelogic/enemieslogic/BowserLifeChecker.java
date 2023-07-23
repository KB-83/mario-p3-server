package controller.gamelogic.enemieslogic;


import model.main_model.entity.enemy.Bowser;
import model.main_model.gamestrucure.GameState;

public class BowserLifeChecker extends EnemyLifeChecker{
    private GameState gameState;
    private Bowser bowser;

    public BowserLifeChecker(GameState gameState, Bowser bowser) {
        this.gameState = gameState;
        this.bowser = bowser;
    }


    @Override
    public void kickedBybullet() {

    }

    @Override
    public void kickedBySward() {
        kill();
    }

    @Override
    public void kill() {
//        gameState.getCurrentSection().setEnemies(ObjectRemover.removeObjectFromArray(gameState.getCurrentSection().getEnemies(), bowser));
    }

    @Override
    public void heatByHead() {

    }
}
