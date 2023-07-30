package model.dto.entity;

public class PlayerDTO {
    private int x,y;
    private int cameraX, cameraY;
    private String type;
    private String image;
    private String name;
    private int height;
    private int remainingLifePercent;

    public PlayerDTO() {
        image = "MarioRight1";
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRemainingLifePercent() {
        return remainingLifePercent;
    }

    public void setRemainingLifePercent(int remainingLifePercent) {
        this.remainingLifePercent = remainingLifePercent;
    }
}
