package controller.gamelogic.enemieslogic;


import model.main_model.entity.enemy.Bowser;
import model.main_model.gamestrucure.GameState;
import util.ObjectRemover;

public class BowserLifeChecker extends EnemyLifeChecker{
    private Bowser bowser;
    private GameState gameState;

    public BowserLifeChecker( Bowser bowser,GameState gameState) {
        this.bowser = bowser;
        this.gameState = gameState;
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
        gameState.getCurrentSection().setEnemies(ObjectRemover.removeObjectFromArray(gameState.getCurrentSection().getEnemies(), bowser));
    }

    @Override
    public void heatByHead() {

    }
}
