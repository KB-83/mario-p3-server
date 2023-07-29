package model.main_model.entity.player;

public enum V {

    Mario(400),
    UniqueGirl(15),
    Luigi(3),
    Princess(7),
    Poker(17);

//    UniqueGirl(5);

    int v;

    V(int v ){
        this.v = v;
    }

    public int returnV(){
        return v;
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }
}
