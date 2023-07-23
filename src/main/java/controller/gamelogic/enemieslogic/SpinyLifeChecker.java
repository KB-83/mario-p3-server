package controller.gamelogic.enemieslogic;


import model.main_model.entity.enemy.Spiny;
import model.main_model.gamestrucure.GameState;

public class SpinyLifeChecker extends EnemyLifeChecker{
    private GameState gameState;
    private Spiny spiny;
//    private Sound sound = new Sound("KICK");

    public SpinyLifeChecker(GameState gameState, Spiny spiny) {
        this.gameState = gameState;
        this.spiny = spiny;
    }

    @Override
    public void kickedBybullet() {
        kill();
    }

    @Override
    public void kickedBySward() {
        kill();
    }

    @Override
    public void kill() {
//        sound.setSound("KICK");
//        sound.play();
//        gameState.getCurrentSection().setEnemies(ObjectRemover.removeObjectFromArray(gameState.getCurrentSection().getEnemies(), spiny));
    }

    @Override
    public void heatByHead() {

    }
}
