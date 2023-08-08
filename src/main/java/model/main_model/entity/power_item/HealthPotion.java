package model.main_model.entity.power_item;

import controller.gamelogic.poweritemlogic.HealthPotionExecutor;
import controller.gamelogic.poweritemlogic.SpeedPotionExecutor;
import model.main_model.entity.player.Player;
import model.main_model.gamestrucure.GameState;
import util.Config;

public class HealthPotion extends PowerItem{
    public static int HPPercent = Config.CONSTANT.get("HealthPotion.HPPercent").intValue();;
    public HealthPotion(Player owner, GameState gameState) {
        super(owner, gameState);
        setItemExecutor(new HealthPotionExecutor(this));
    }
}
