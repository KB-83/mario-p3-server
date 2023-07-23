package model.main_model.entity;


import model.main_model.entity.player.V;
import util.Constant;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Sward extends Entity {
    private boolean isLock;
    private long lastTime;
    private int startX;
    private Timer timer;
    private boolean isGoingRight;
    private boolean isHeatBlock;
    public Sward() {
        addActionListeners();
        lastTime = System.currentTimeMillis();
        setLock(true);
        setWidth(96);
        setHeight(48);
        setGoingRight(true);
    }
    public void addActionListeners() {
        timer = new Timer(1000/ Constant.FPS, new ActionListener() {
            int i = 0;
            int startDirection = 0;
            boolean didStartR;
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Math.abs(getWorldX() - startX) < 4 * Constant.BACKGROUND_TILE_SIZE) {
                    if (isGoingRight && startDirection < 1){
                        startDirection++;
                        didStartR = true;
                    }
                    else if (startDirection < 1 && !isGoingRight){
                        startDirection++;
                        didStartR = false;
                    }
                    if (isGoingRight) {
                        setWorldX(getWorldX() + (V.Mario.returnV() / Constant.FPS));
                    }
                    else {
                        setWorldX(getWorldX() - (V.Mario.returnV() / Constant.FPS));
                    }
                    if (Math.abs(getWorldX() - startX) >= 4 * Constant.BACKGROUND_TILE_SIZE && i <= 1) {
                        if (isGoingRight) {
                            setWorldX(startX + (4 * Constant.BACKGROUND_TILE_SIZE) -1 );
                        }
                        else {
                            setWorldX(startX - (4 * Constant.BACKGROUND_TILE_SIZE) +1 );
                        }
                        isGoingRight = !isGoingRight;
                        i++;
                    }
                    setLastTime(System.currentTimeMillis());
                }
                if ((getWorldX() < startX &&  didStartR) || isHeatBlock){
                    i = 0;
                    setVX(0);
                    startDirection = 0;
                    setLock(true);
                    setLastTime(System.currentTimeMillis());
                    timer.stop();
                    isHeatBlock = false;
                }
                if (getWorldX() > startX && !didStartR){
                    i = 0;
                    setVX(0);
                    startDirection = 0;
                    setLock(true);
                    setLastTime(System.currentTimeMillis());
                    timer.stop();
                }

            }
        });
    }

    public boolean isLock() {
        return isLock;
    }

    public void setLock(boolean lock) {
        isLock = lock;
    }

    public long getLastTime() {
        return lastTime;
    }

    public void setLastTime(long lastTime) {
        this.lastTime = lastTime;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public boolean isGoingRight() {
        return isGoingRight;
    }

    public void setGoingRight(boolean goingRight) {
        isGoingRight = goingRight;
    }

    public boolean isHeatBlock() {
        return isHeatBlock;
    }

    public void setHeatBlock(boolean heatBlock) {
        isHeatBlock = heatBlock;
    }
}
