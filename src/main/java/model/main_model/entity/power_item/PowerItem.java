package model.main_model.entity.power_item;

import controller.gamelogic.poweritemlogic.PowerItemExecutor;
import controller.gamelogic.poweritemlogic.PowerItemController;
import model.main_model.entity.Entity;
import model.main_model.entity.player.Player;
import model.main_model.gamestrucure.GameState;

public abstract class PowerItem extends Entity {
    private PowerItemController controller;
    private PowerItemExecutor powerItemExecutor;

    public PowerItem(Player owner, GameState gameState) {
        controller = new PowerItemController(this,owner,gameState);
    }

    public PowerItemController getController() {
        return controller;
    }

    public void setItemExecutor(PowerItemExecutor powerItemExecutor) {
        this.powerItemExecutor = powerItemExecutor;
    }

    public PowerItemExecutor getItemExecutor() {
        return powerItemExecutor;
    }
}
