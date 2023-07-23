package model.main_model.backgroundobject.pipe;


import model.main_model.levelstructure.TeleSection;

public class SimpleTelePipe extends Pipe{
    private TeleSection teleSection;

    public SimpleTelePipe() {
    }

    public TeleSection getTeleSection() {
        return teleSection;
    }

    public void setTeleSection(TeleSection teleSection) {
        this.teleSection = teleSection;
    }
}
