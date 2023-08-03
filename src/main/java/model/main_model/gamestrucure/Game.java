package model.main_model.gamestrucure;


import model.main_model.levelstructure.Level;

public class Game {
    // todo : check if it is nessesary
    private String type;
    private String name;
//    private Player player;
    // todo : till here
    private Level[] levels;
    private int hearts;
    private int marioState;

    public Game() {
    }

    public Level[] getLevels() {
        return levels;
    }

    public void setLevels(Level[] levels) {
        this.levels = levels;
    }

    public int getHearts() {
        return hearts;
    }

    public void setHearts(int hearts) {
        this.hearts = hearts;
    }

    public int getMarioState() {
        return marioState;
    }

    public void setMarioState(int marioState) {
        this.marioState = marioState;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
