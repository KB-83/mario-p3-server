package controller.gamelogic.enemieslogic;

import model.main_model.entity.enemy.Enemy;
import util.Constant;

public abstract class EnemyLifeChecker {
    public void checkLife(Enemy enemy){
        if (enemy.getWorldY() >= Constant.PANEL_HEIGHT)
        {
            enemy.getEnemyController().getEnemyLifeChecker().kill();
        }
    };
    public abstract void kickedBybullet();
    public abstract void kickedBySward();
    public abstract void kill();
    public abstract void heatByHead();
}
