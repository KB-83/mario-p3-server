package model.main_model.entity.enemy;

import controller.gamelogic.enemieslogic.PlantController;
import util.Constant;

public class Plant extends Enemy{
    public Plant() {
        setHeight(Constant.BACKGROUND_TILE_SIZE);
        setWidth(Constant.BACKGROUND_TILE_SIZE);
        setEnemyController(new PlantController(this));
//        setVY(200);
        setAlive(true);
//        timer.start();
    }


}
