package model.main_model.entity.power_item;

import controller.gamelogic.poweritemlogic.InvisibilityPotionExecutor;
import model.main_model.entity.player.Player;
import model.main_model.gamestrucure.GameState;
import util.Config;

public class InvisibilityPotion extends PowerItem{
    public static int period = Config.CONSTANT.get("InvisibilityPotion.period").intValue();
    public InvisibilityPotion(Player owner, GameState gameState) {
        super(owner, gameState);
        setItemExecutor(new InvisibilityPotionExecutor(this));
    }
}
