package model.main_model.levelstructure;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import controller.mapper.CustomLevelLoader;
import controller.mapper.CustomLevelSaver;

@JsonDeserialize(using = CustomLevelLoader.class)
@JsonSerialize(using = CustomLevelSaver.class)

public class Level {
    private Section[] sections;

    public Level() {
    }

    public Level(Section[] sections) {
        this.sections = sections;
    }

    public Section[] getSections() {
        return sections;
    }

    public void setSections(Section[] sections) {
        this.sections = sections;
    }
}
