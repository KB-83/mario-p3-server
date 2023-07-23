package model.main_model.backgroundobject.pipe;

import com.fasterxml.jackson.annotation.JsonIgnore;
import model.main_model.entity.enemy.Plant;
import model.main_model.levelstructure.TeleSection;

public class TelePlantPipe extends Pipe{
    @JsonIgnore
    private TeleSection teleSection;
    private Plant plant;

    public TelePlantPipe() {
    }

    public TeleSection getTeleSection() {
        return teleSection;
    }

    public void setTeleSection(TeleSection teleSection) {
        this.teleSection = teleSection;
    }


    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }
}
