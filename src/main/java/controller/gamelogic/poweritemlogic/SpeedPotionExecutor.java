package controller.gamelogic.poweritemlogic;

import model.main_model.entity.player.Player;
import model.main_model.entity.player.V;
import model.main_model.entity.power_item.PowerItem;
import model.main_model.entity.power_item.SpeedPotion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SpeedPotionExecutor extends PowerItemExecutor {
    private Timer timer;
    private long startTime;
    private Player player;
    private PowerItem powerItem;

    public SpeedPotionExecutor(PowerItem powerItem) {
        this.powerItem = powerItem;
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (System.currentTimeMillis() - startTime >= SpeedPotion.period * 1000) {
                    player.setVXMeasure(V.Mario.getV());
                    timer.stop();
                    powerItem.setDuringShoot(false);
                }
            }
        });
    }

    @Override
    public void execute(Player player) {
        powerItem.setItemExecutor(null);
        this.player = player;
        startTime = System.currentTimeMillis();
        player.setVXMeasure(player.getVXMeasure() * SpeedPotion.multiplier);
        timer.start();
    }
}
