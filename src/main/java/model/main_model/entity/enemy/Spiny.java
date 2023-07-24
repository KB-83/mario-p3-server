package model.main_model.entity.enemy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import controller.gamelogic.enemieslogic.SpinyController;


public class Spiny extends Enemy {
//    @JsonIgnore
//    private SpinyController spinyController;
    public Spiny() {
        setVX(100);
        setEnemyController(new SpinyController(this));
    }

//    public SpinyController getSpinyController() {
//        return spinyController;
//    }
//
//    public void setSpinyController(SpinyController spinyController) {
//        this.spinyController = spinyController;
//    }
}
