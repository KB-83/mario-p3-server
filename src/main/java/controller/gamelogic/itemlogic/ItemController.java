package controller.gamelogic.itemlogic;


import controller.gamelogic.collisionlogic.ItemCollisionHandler;
import model.main_model.entity.item.Item;
import model.main_model.gamestrucure.GameState;

public class ItemController {
    private ItemCollisionHandler itemCollisionHandler;
    private ItemMovementHandler itemMovementHandler;
    private Item item;

    public ItemController(Item item) {
        this.item = item;
    }

    public void update(){
        itemMovementHandler.updateItemsPosition();
        itemCollisionHandler.applyCollisionEffects();
    }
    public void initGameState(GameState gameState) {
        itemCollisionHandler = new ItemCollisionHandler(gameState,item);
        itemMovementHandler  = new ItemMovementHandler(gameState);
    }
}
