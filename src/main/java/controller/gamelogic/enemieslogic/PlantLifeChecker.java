package controller.gamelogic.enemieslogic;


import model.main_model.entity.enemy.Plant;
import model.main_model.gamestrucure.GameState;

public class PlantLifeChecker extends EnemyLifeChecker{
    private GameState gameState;
    private Plant plant;
//    private Sound sound = new Sound("KICK");
    public PlantLifeChecker(GameState gameState, Plant plant) {
        this.gameState = gameState;
        this.plant = plant;
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
//        gameState.getCurrentSection().setEnemies(ObjectRemover.removeObjectFromArray(gameState.getCurrentSection().getEnemies(), plant));
    }

    @Override
    public void heatByHead() {

    }
}
