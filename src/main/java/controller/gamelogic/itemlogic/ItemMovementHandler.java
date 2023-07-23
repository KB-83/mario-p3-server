package controller.gamelogic.itemlogic;

import model.main_model.entity.item.Item;
import model.main_model.gamestrucure.GameState;
import model.main_model.gamestrucure.gameworldoption.Gravity;
import util.Constant;

public class ItemMovementHandler {
    private GameState gameState;

    public ItemMovementHandler(GameState gameState) {
        this.gameState = gameState;
    }
    public void updateItemsPosition() {
        for (Item item: gameState.getCurrentSection().getItems()) {
//            item.getItemCollisionHandler().setSection(gameState.getCurrentSection());
//            item.getItemCollisionHandler().applyCollisionEffects();
            item.setWorldX((int) (item.getWorldX()+(1.0/ Constant.FPS * item.getVX())));
            item.setWorldY((int) (item.getWorldY()+(1.0/Constant.FPS * item.getVY())));
            if (item.getOnTopOfBlock() == false) {
                item.setVY(item.getVY()+(1.0/Constant.FPS* Gravity.MARIO_GAME));
            }
            else {
                item.setVY(0);
            }

        }
    }
}
