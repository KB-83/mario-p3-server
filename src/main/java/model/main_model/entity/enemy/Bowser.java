package model.main_model.entity.enemy;

import controller.gamelogic.enemieslogic.BowserController;

public class Bowser extends Enemy{
    public Bowser() {
        setEnemyController(new BowserController(this));
    }
}
