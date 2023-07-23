package model.main_model.backgroundobject.pipe;


import model.main_model.entity.enemy.Plant;
import model.main_model.levelstructure.Section;

public class SpawnPlantPipe extends Pipe{
    private Section section;
    private Plant plant;

    public SpawnPlantPipe() {
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }
}
