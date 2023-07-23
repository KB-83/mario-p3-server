package model.main_model.entity.player;


import model.main_model.gamestrucure.gameworldoption.Gravity;
import util.Constant;

public enum JumpV0 {

    MARIO((int) (2* Math.sqrt(3* Gravity.MARIO_GAME * Constant.BACKGROUND_TILE_SIZE))),
    UNIQUE_GIRL(15),
    LUIGI(3),
    PRINCESS(7),
    POKER(17);

//    UniqueGirl(5);

    int jumpV0;

    JumpV0(int v0 ){
        this.jumpV0 = v0;
    }

    public int returnV0(){
        return jumpV0;
    }
}
