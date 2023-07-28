package model.response;

import model.main_model.Client;

public class SignInLoginResponse extends Response{
    private boolean isOk;
    private String massage;
    private Client client;
    public SignInLoginResponse() {
    }

    public SignInLoginResponse(Client client,boolean isOk, String massage) {
        this.client = client;
        this.isOk = isOk;
        this.massage = massage;
    }

    public boolean isOk() {
        return isOk;
    }

    public void setOk(boolean ok) {
        isOk = ok;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
