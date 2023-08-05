package model.main_model.entity.power_item;

import model.main_model.entity.player.Player;
import model.main_model.gamestrucure.GameState;

public class HealthPotion extends PowerItem{

    public HealthPotion(Player owner, GameState gameState) {
        super(owner, gameState);
    }
}
