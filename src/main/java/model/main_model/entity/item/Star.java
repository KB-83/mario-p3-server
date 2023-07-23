package model.main_model.entity.item;

import util.Constant;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Star extends Item{
    private Timer jumpTimer;
    private long jumpStartTime;
    public Star() {
        setJumpTimer();
    }
    private void setJumpTimer() {
        jumpTimer = new Timer(1000/ Constant.FPS, new ActionListener() {
            double deltaY = 0.1;
            double t = 0;
            int i = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
//                    if (i < 3000){
//                        if (!getOnTopOfBlock()) {
//                            t = (System.currentTimeMillis() - jumpStartTime) / 1000;
//                            setVY((-(Gravity.MARIO_GAME) * (t)) + JumpV0.MARIO.returnV0());
//                            deltaY = -(((Gravity.MARIO_GAME / 2)) * Math.pow(t, 2)) + (JumpV0.MARIO.returnV0() * t);
//                            i++;
//                        }
//                    }
//                    else {
//                        setVY(0);
//                        deltaY = 0;
//                        t = 0;
//                        i = 0;
                        jumpTimer.stop();
//                    }

            }
        });
    }
    public void startJumping(){
        jumpStartTime = System.currentTimeMillis();
        jumpTimer.start();
    }
}
