package model.main_model.entity.enemy;

import util.Constant;

public class Plant extends Enemy{
    public Plant() {
        setHeight(Constant.BACKGROUND_TILE_SIZE);
        setWidth(Constant.BACKGROUND_TILE_SIZE);
//        setVY(200);
        setAlive(true);
//        timer.start();
    }


}
