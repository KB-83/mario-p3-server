package model.main_model;

public class ShopLimitation {
    private Limitation damageBomb;
    private Limitation speedBomb;
    private Limitation speedPotion;
    private Limitation invisibilityPotion;
    private Limitation healthPotion;
    private Limitation hammer;
    private Limitation sward;

    public ShopLimitation() {
    }

    public Limitation getDamageBomb() {
        return damageBomb;
    }

    public void setDamageBomb(Limitation damageBomb) {
        this.damageBomb = damageBomb;
    }

    public Limitation getSpeedBomb() {
        return speedBomb;
    }

    public void setSpeedBomb(Limitation speedBomb) {
        this.speedBomb = speedBomb;
    }

    public Limitation getSpeedPotion() {
        return speedPotion;
    }

    public void setSpeedPotion(Limitation speedPotion) {
        this.speedPotion = speedPotion;
    }

    public Limitation getInvisibilityPotion() {
        return invisibilityPotion;
    }

    public void setInvisibilityPotion(Limitation invisibilityPotion) {
        this.invisibilityPotion = invisibilityPotion;
    }

    public Limitation getHealthPotion() {
        return healthPotion;
    }

    public void setHealthPotion(Limitation healthPotion) {
        this.healthPotion = healthPotion;
    }

    public Limitation getHammer() {
        return hammer;
    }

    public void setHammer(Limitation hammer) {
        this.hammer = hammer;
    }

    public Limitation getSward() {
        return sward;
    }

    public void setSward(Limitation sward) {
        this.sward = sward;
    }
}

