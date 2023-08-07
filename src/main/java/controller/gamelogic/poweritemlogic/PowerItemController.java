package controller.gamelogic.poweritemlogic;

import controller.gamelogic.collisionlogic.PowerItemCollisionHandler;
import model.main_model.entity.player.Player;
import model.main_model.entity.power_item.PowerItem;
import model.main_model.gamestrucure.GameState;
import util.Constant;

public class PowerItemController {
    private Player owner;
    private PowerItemCollisionHandler powerItemCollisionHandler;
//    private ItemShooter itemShooter;
    private PowerItem powerItem;

    public PowerItemController(PowerItem powerItem,Player owner,GameState gameState) {
        this.owner = owner;
        this.powerItem = powerItem;
        powerItemCollisionHandler = new PowerItemCollisionHandler(gameState,powerItem);
    }
    public void update(){
//        itemMovementHandler.updateItemsPosition();
        powerItemCollisionHandler.applyCollisionEffects();
        //test
        powerItem.setWorldX((int) (powerItem.getWorldX()+(1.0/ Constant.FPS * powerItem.getVX())));
        powerItem.setWorldY((int) (powerItem.getWorldY() - (1.0/Constant.FPS * powerItem.getVY())));

    }
    public void shoot() {
        powerItem.setDuringShoot(true);
        powerItem.setWorldY(owner.getWorldY());
        powerItem.setWorldX(owner.getWorldX());
        ItemShooter.getInstance().throwIt(powerItem,200,200);
    }
    public void execute(){
        if (powerItem.getItemExecutor() != null) {
            powerItem.getItemExecutor().execute(owner);
        }
        else {
            owner.setActivePowerItem(null);
            powerItem.setDuringShoot(false);
        }
    }
}
