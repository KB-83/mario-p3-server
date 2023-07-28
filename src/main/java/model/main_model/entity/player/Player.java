package model.main_model.entity.player;

import com.fasterxml.jackson.annotation.JsonIgnore;
import controller.gamelogic.gamestatelogic.PlayerController;
import model.main_model.entity.Bullet;
import model.main_model.entity.Entity;
import model.main_model.entity.Sward;
import util.Constant;

public abstract class Player extends Entity {
    private int cameraX, cameraY;
    private String imageAddress;
    // todo : test if you load a game which player is during jump what happens
    @JsonIgnore
    private boolean isDuringJump;
    @JsonIgnore
    private final Bullet bullet;
    @JsonIgnore
    private final Sward sward;
    private boolean isFire, isMega;
    @JsonIgnore
    private boolean isUnHeat;
    private String clientName;
    //todo: is it nessesart?
//    @JsonIgnore
//    private PlayerRequestHandler playerRequestHandler;
    @JsonIgnore
    private PlayerController playerController;

    public Player() {

        setOnTopOfBlock(true);
        bullet = new Bullet();
        sward = new Sward();
        isUnHeat = false;
        setHeight(Constant.BACKGROUND_TILE_SIZE);
        setWidth(Constant.BACKGROUND_TILE_SIZE);
    }

    public int getCameraX() {
        return cameraX;
    }

    public void setCameraX(int cameraX) {
        this.cameraX = cameraX;
    }

    public int getCameraY() {
        return cameraY;
    }

    public void setCameraY(int cameraY) {
        this.cameraY = cameraY;
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }

    public boolean isDuringJump() {
        return isDuringJump;
    }

    public void setDuringJump(boolean duringJump) {
        isDuringJump = duringJump;
    }

//    public PlayerRequestHandler getPlayerRequestHandler() {
//        return playerRequestHandler;
//    }
//
//    public void setPlayerRequestHandler(PlayerRequestHandler playerRequestHandler) {
//        this.playerRequestHandler = playerRequestHandler;
//    }
//
    public PlayerController getPlayerController() {
        return playerController;
    }

    public void setPlayerController(PlayerController playerController) {
        this.playerController = playerController;
    }

    public boolean isFire() {
        return isFire;
    }

    public void setFire(boolean fire) {
        isFire = fire;
        if (fire){
            setHeight(Constant.BACKGROUND_TILE_SIZE*2);
        }
    }

    public boolean isMega() {
        return isMega;
    }

    public void setMega(boolean mega) {
        isMega = mega;
        if (mega){
            setHeight(Constant.BACKGROUND_TILE_SIZE*2);
        }
    }

    public Bullet getBullet() {
        return bullet;
    }

    public Sward getSward() {
        return sward;
    }

    public boolean isUnHeat() {
        return isUnHeat;
    }

    public void setUnHeat(boolean unHeat) {
        isUnHeat = unHeat;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}
