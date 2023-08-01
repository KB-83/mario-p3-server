package model.response;

public class DialogResponse extends Response{
    private String massage;

    public DialogResponse() {
    }

    public DialogResponse(String massage) {
        this.massage = massage;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }
}
