package model.dto.game;

import model.dto.backgroundobject.CheckPointDTO;
import model.dto.backgroundobject.block.BlockDTO;
import model.dto.backgroundobject.pipe.PipeDTO;
import model.dto.entity.enemy.EnemyDTO;
import model.dto.entity.item.ItemDTO;

public class SectionDTO {
    private int length;
    private int time;
    private BlockDTO[] blocks;
    private EnemyDTO[] enemies;
    private PipeDTO[] pipes;
    private PipeDTO spawnPipe;
    private int[][] backGroundTiles;
    private CheckPointDTO checkPoint;
    private ItemDTO[] items;

    public SectionDTO() {
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

    public BlockDTO[] getBlocks() {
        return blocks;
    }

    public void setBlocks(BlockDTO[] blocks) {
        this.blocks = blocks;
    }

    public EnemyDTO[] getEnemies() {
        return enemies;
    }

    public void setEnemies(EnemyDTO[] enemies) {
        this.enemies = enemies;
    }

    public PipeDTO[] getPipes() {
        return pipes;
    }

    public void setPipes(PipeDTO[] pipes) {
        this.pipes = pipes;
    }

    public PipeDTO getSpawnPipe() {
        return spawnPipe;
    }

    public void setSpawnPipe(PipeDTO spawnPipe) {
        this.spawnPipe = spawnPipe;
    }

    public CheckPointDTO getCheckPoint() {
        return checkPoint;
    }

    public void setCheckPoint(CheckPointDTO checkPoint) {
        this.checkPoint = checkPoint;
    }

    public ItemDTO[] getItems() {
        return items;
    }

    public void setItems(ItemDTO[] items) {
        this.items = items;
    }

    public int[][] getBackGroundTiles() {
        return backGroundTiles;
    }

    public void setBackGroundTiles(int[][] backGroundTiles) {
        this.backGroundTiles = backGroundTiles;
    }
}
