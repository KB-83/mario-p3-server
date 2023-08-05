package model.main_model.entity.power_item;

import controller.gamelogic.poweritemlogic.PowerItemController;
import model.main_model.Client;
import model.main_model.entity.Entity;
import model.main_model.entity.player.Player;
import model.main_model.gamestrucure.GameState;

public abstract class PowerItem extends Entity {
    private PowerItemController controller;

    public PowerItem(Player owner, GameState gameState) {
        controller = new PowerItemController(this,owner,gameState);
    }

    public PowerItemController getController() {
        return controller;
    }

}
