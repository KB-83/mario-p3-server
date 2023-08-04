package controller.gamelogic.poweritemlogic;

import controller.gamelogic.Throwable;
import model.main_model.entity.Entity;
import model.main_model.gamestrucure.GameState;
import model.main_model.gamestrucure.gameworldoption.Gravity;
import util.Constant;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemShooter implements Throwable {
    private long shootStartTime;
    private GameState gameState;
    private Timer shootTimer;
    private ActionListener shootActionListener;
    private static ItemShooter itemShooter;

    private ItemShooter() {
    }
    public static ItemShooter getInstance() {
        if (itemShooter == null) {
            itemShooter = new ItemShooter();
        }
        return itemShooter;
    }
    @Override
    public void throwIt(Entity entity, int v0X, int v0Y) {
        getNewShootActionListener(entity,v0X,v0Y);
        shootStartTime = System.currentTimeMillis();
        shootTimer = new Timer(1000/Constant.FPS,shootActionListener);
        shootTimer.start();
    }
    private void getNewShootActionListener(Entity entity, int v0X, int v0Y) { // todo : or one is enough
        shootActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double deltaY = 0.1;
                double t = 0;
                if (!gameState.isPaused()){
                    t = ( System.currentTimeMillis() - shootStartTime) / 1000;
                    entity.setVY ((-(Gravity.MARIO_GAME) * (t)) + v0Y);
                    entity.setVX(v0X);
                    deltaY = -(((Gravity.MARIO_GAME/2)) * Math.pow(t, 2)) + (v0Y * t);
                }
                else {
                    entity.setVY(0);
                    deltaY = 0;
                    t = 0;
                    // here means jump completed
                    shootTimer.stop();
                }
            }
        };
    }

}
