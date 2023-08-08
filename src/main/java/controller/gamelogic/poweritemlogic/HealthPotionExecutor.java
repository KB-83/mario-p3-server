package controller.gamelogic.poweritemlogic;

import model.main_model.entity.player.Player;
import model.main_model.entity.power_item.HealthPotion;
import model.main_model.entity.power_item.PowerItem;

public class HealthPotionExecutor extends PowerItemExecutor{
    private PowerItem powerItem;
    public HealthPotionExecutor(PowerItem powerItem) {
        this.powerItem = powerItem;
    }

    @Override
    public void execute(Player player) {
        powerItem.setItemExecutor(null);
        player.getPlayerGameLog().setRemainingLifePercent(
                player.getPlayerGameLog().getRemainingLifePercent() + HealthPotion.HPPercent
        );
    }
}
