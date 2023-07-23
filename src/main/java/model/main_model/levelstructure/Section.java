package model.main_model.levelstructure;

import com.fasterxml.jackson.annotation.JsonIgnore;
import model.main_model.backgroundobject.CheckPoint;
import model.main_model.backgroundobject.block.Block;
import model.main_model.backgroundobject.pipe.Pipe;
import model.main_model.entity.enemy.Enemy;
import model.main_model.entity.item.Item;
import model.main_model.worldtiles.BackgroundMap;
import util.Constant;


public class Section {
    private int length;
    private int time;
    private Block[] blocks;
    private Enemy[] enemies;
    private Pipe[] pipes;
    private Pipe spawnPipe;
    @JsonIgnore
    private BackgroundMap backgroundMap;
    private CheckPoint checkPoint;
    @JsonIgnore
    private Item[] items;
    public Section(){
    }

    public Section(int levelNum,int sectionNum,int cols) {
        backgroundMap = new BackgroundMap();
        backgroundMap.loadMap(levelNum,sectionNum,cols, Constant.PANEL_ROWS);

    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Block[] getBlocks() {
        return blocks;
    }

    public void setBlocks(Block[] blocks) {
        this.blocks = blocks;
    }

    public Enemy[] getEnemies() {
        return enemies;
    }

    public void setEnemies(Enemy[] enemies) {
        this.enemies = enemies;
    }

    public Pipe[] getPipes() {
        return pipes;
    }

    public void setPipes(Pipe[] pipes) {
        this.pipes = pipes;
    }

    public BackgroundMap getBackgroundMap() {
        return backgroundMap;
    }

    public void setBackgroundMap(BackgroundMap backgroundMap) {
        this.backgroundMap = backgroundMap;
    }

    public Pipe getSpawnPipe() {
        return spawnPipe;
    }

    public void setSpawnPipe(Pipe spawnPipe) {
        this.spawnPipe = spawnPipe;
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

    public CheckPoint getCheckPoint() {
        return checkPoint;
    }

    public void setCheckPoint(CheckPoint checkPoint) {
        this.checkPoint = checkPoint;
    }
}
