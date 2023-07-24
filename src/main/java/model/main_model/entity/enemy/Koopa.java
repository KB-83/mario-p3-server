package model.main_model.entity.enemy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import controller.gamelogic.enemieslogic.KoopaController;

public class Koopa extends Enemy{
//    @JsonIgnore
//    private KoopaController koopaController;
    private boolean isWaitingToDie;
    public Koopa() {
        setVX(100);
        isWaitingToDie = false;
        setEnemyController(new KoopaController(this));
    }

//    public KoopaController getKoopaController() {
//        return koopaController;
//    }
//
//    public void setKoopaController(KoopaController koopaController) {
//        this.koopaController = koopaController;
//    }

    public boolean isWaitingToDie() {
        return isWaitingToDie;
    }

    public void setWaitingToDie(boolean waitingToDie) {
        isWaitingToDie = waitingToDie;
    }
}
