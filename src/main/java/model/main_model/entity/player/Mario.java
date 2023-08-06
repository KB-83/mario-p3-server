package model.main_model.entity.player;

import util.Constant;

public class Mario extends Player {
    public Mario(String name,String[] selectedBag){
        super(name,selectedBag);
    }
    public Mario(String name,String[] selectedBag,int x, int y) {
        super(name,selectedBag);
        setWorldX(x);
        setWorldY(y);
        setCameraX(0);
        setCameraY(0);
    }

}
