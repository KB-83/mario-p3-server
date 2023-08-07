package model.main_model.entity.player;

public class PlayerGameLog {
    private int totalDamageDelta;
    private int powerItems;
    private int remainingLifePercent = -1;

    public PlayerGameLog() {
    }

    public int getTotalDamageDelta() {
        return totalDamageDelta;
    }

    public void setTotalDamageDelta(int totalDamageDelta) {
        this.totalDamageDelta = totalDamageDelta;
    }

    public int getPowerItems() {
        return powerItems;
    }

    public void setPowerItems(int powerItems) {
        this.powerItems = powerItems;
    }

    public int getRemainingLifePercent() {
        return remainingLifePercent;
    }

    public void setRemainingLifePercent(int remainingLifePercent) {
        this.remainingLifePercent = remainingLifePercent;
    }
}
