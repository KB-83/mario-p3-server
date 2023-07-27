package model.request;

import controller.ClientController;
import controller.connection.RequestVisitor;

public class BuyRequest extends Request{
    private int hammer,speedPotion,healthPotion, inVisibilityPotion,speedBomb,damageBomb,sward;

    public BuyRequest() {
    }

    public int getHammer() {
        return hammer;
    }

    public void setHammer(int hammer) {
        this.hammer = hammer;
    }

    public int getSpeedPotion() {
        return speedPotion;
    }

    public void setSpeedPotion(int speedPotion) {
        this.speedPotion = speedPotion;
    }

    public int getHealthPotion() {
        return healthPotion;
    }

    public void setHealthPotion(int healthPotion) {
        this.healthPotion = healthPotion;
    }

    public int getInVisibilityPotion() {
        return inVisibilityPotion;
    }

    public void setInVisibilityPotion(int inVisibilityPotion) {
        this.inVisibilityPotion = inVisibilityPotion;
    }

    public int getSpeedBomb() {
        return speedBomb;
    }

    public void setSpeedBomb(int speedBomb) {
        this.speedBomb = speedBomb;
    }

    public int getDamageBomb() {
        return damageBomb;
    }

    public void setDamageBomb(int damageBomb) {
        this.damageBomb = damageBomb;
    }

    public int getSward() {
        return sward;
    }

    public void setSward(int sward) {
        this.sward = sward;
    }

    @Override
    public void visit(RequestVisitor visitor, ClientController clientController) {
        visitor.visit(this,clientController);
    }
}
