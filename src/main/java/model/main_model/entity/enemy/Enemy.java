package model.main_model.entity.enemy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import controller.gamelogic.enemieslogic.EnemyController;
import model.main_model.entity.Entity;


public abstract class Enemy extends Entity {

    @JsonIgnore
    private EnemyController enemyController;
    private boolean isAlive;
    public Enemy() {
        setOnTopOfBlock(true);
    }

    public EnemyController getEnemyController() {
        return enemyController;
    }

    public void setEnemyController(EnemyController enemyController) {
        this.enemyController = enemyController;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}
