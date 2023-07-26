package model.main_model.room;

public class Massage {
    private boolean isOwnersPM;
    private String context;

    public Massage(boolean isOwnersPm, String context) {
        this.isOwnersPM = isOwnersPm;
        this.context = context;
    }

    public Massage() {
    }

    public boolean isOwnersPM() {
        return isOwnersPM;
    }

    public void setOwnersPM(boolean ownersPM) {
        isOwnersPM = ownersPM;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
