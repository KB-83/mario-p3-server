package controller.gamelogic.enemieslogic;

import controller.gamelogic.collisionlogic.EnemyCollisionHandler;
import model.main_model.entity.enemy.Enemy;
import model.main_model.entity.enemy.Plant;
import model.main_model.gamestrucure.GameState;
import util.Constant;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlantController extends EnemyController{
    private EnemyCollisionHandler enemyCollisionHandler;
    private EnemyMovementHandler enemyMovementHandler;
    private Plant plant;
    private Timer timer;
    private boolean timerStarted;
    private final int maxY,minY;
    private long lastStand,lastSeat;
    private boolean isDuringStand;

    public PlantController(GameState gameState , Enemy enemy){
        super(gameState,enemy);
        setEnemyLifeChecker(new PlantLifeChecker(gameState,(Plant) enemy));
        enemyMovementHandler = new EnemyMovementHandler(gameState);
        enemyCollisionHandler = new EnemyCollisionHandler(gameState,enemy);
        plant = (Plant) enemy;
        maxY = plant.getWorldY();
        minY = (plant.getWorldY() + 2* Constant.BACKGROUND_TILE_SIZE);
        setTimer();
        isDuringStand = true;
    }
    private void setTimer(){

        lastSeat = System.currentTimeMillis();
        timer = new Timer(1000/Constant.FPS, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timerStarted = true;
                if (plant.isAlive()) {
                    if (isDuringStand){
                        if (System.currentTimeMillis() - lastStand > 4000 ){
                            isDuringStand = false;
                            lastSeat = System.currentTimeMillis();
                        }
                        if (plant.getWorldY() > maxY) {
                            plant.setVY(-6);
                        }
                        else {
                            plant.setVY(0);
                        }
                    }
                    else {
                        if (System.currentTimeMillis() - lastSeat < 5000) {
                            if (plant.getWorldY() < minY){
                                plant.setVY(60);
                            }
                            else {
                                plant.setVY(0);
                            }
                        }
                        else {
                            isDuringStand = true;
                            lastStand = System.currentTimeMillis();
                        }
                    }
                }
                else {
                    timer.stop();
                }
            }
        });
        timer.start();
    }
}
