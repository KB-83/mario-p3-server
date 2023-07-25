package controller.gamelogic.enemieslogic;


import model.main_model.entity.enemy.Goomba;
import model.main_model.gamestrucure.GameState;
import util.ObjectRemover;

public class GoombaLifeChecker extends EnemyLifeChecker{
//    private Sound sound = new Sound("KICK");
    private Goomba goomba;
    private GameState gameState;

    public GoombaLifeChecker(GameState gameState,Goomba goomba) {
        this.goomba = goomba;
        this.gameState = gameState;
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
        gameState.getCurrentSection().setEnemies(ObjectRemover.removeObjectFromArray(gameState.getCurrentSection().getEnemies(), goomba));
    }

    @Override
    public void heatByHead() {
        kill();
    }
}
