package model.main_model.levelstructure;

import model.main_model.backgroundobject.pipe.Pipe;

public class TeleSection extends Section{
    private Section section;
    private Pipe spwanPipe;

    public TeleSection() {
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }
}
