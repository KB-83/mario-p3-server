package model.main_model.entity.player;

import util.Constant;

public class Mario extends Player {
    public Mario(String name){
        super(name);
    }
    public Mario(String name,int x, int y) {
        super(name);
        setWorldX(x);
        setWorldY(y);
        setCameraX(0);
        setCameraY(0);
    }

}
