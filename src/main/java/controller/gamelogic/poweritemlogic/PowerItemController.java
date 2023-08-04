package controller.gamelogic.poweritemlogic;

import controller.gamelogic.collisionlogic.ItemCollisionHandler;
import controller.gamelogic.collisionlogic.PowerItemCollisionHandler;
import controller.gamelogic.itemlogic.ItemMovementHandler;
import model.main_model.Client;
import model.main_model.entity.power_item.PowerItem;
import model.main_model.gamestrucure.GameState;

public class PowerItemController {
    private Client owner;
    private PowerItemCollisionHandler powerItemCollisionHandler;
    private PowerItemMovementHandler powerItemMovementHandler;
    private ItemShooter itemShooter;
    private PowerItem powerItem;

    public PowerItemController(Client owner,PowerItem powerItem) {
        this.owner = owner;
        this.powerItem = powerItem;
    }
    public void shoot() {
        itemShooter.throwIt(powerItem,30,30);
    }
    public void execute(){}
    public void initGameState(GameState gameState) {
        powerItemCollisionHandler = new PowerItemCollisionHandler(gameState,powerItem);
        powerItemMovementHandler = new PowerItemMovementHandler();

    }
}
