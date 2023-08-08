package model.main_model.entity.power_item;

import controller.gamelogic.poweritemlogic.SpeedPotionExecutor;
import model.main_model.entity.player.Player;
import model.main_model.gamestrucure.GameState;
import util.Config;

public class SpeedPotion extends PowerItem{
    public static int period = Config.CONSTANT.get("SpeedPotion.period").intValue();
    public static double multiplier = Config.CONSTANT.get("SpeedPotion.multiplier");

    public SpeedPotion(Player owner, GameState gameState) {
        super(owner, gameState);
        setItemExecutor(new SpeedPotionExecutor(this));
    }
}
