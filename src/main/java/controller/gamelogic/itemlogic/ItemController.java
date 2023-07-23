package controller.gamelogic.itemlogic;


import controller.gamelogic.collisionlogic.ItemCollisionHandler;
import model.main_model.entity.item.Item;
import model.main_model.gamestrucure.GameState;

public class ItemController {
    private ItemCollisionHandler itemCollisionHandler;
    private ItemMovementHandler itemMovementHandler;

    public ItemController(Item item, GameState gameState) {
        itemCollisionHandler = new ItemCollisionHandler(gameState,item);
        itemMovementHandler  = new ItemMovementHandler(gameState);
    }

    public void update(){
        itemMovementHandler.updateItemsPosition();
        itemCollisionHandler.applyCollisionEffects();
    }
}
