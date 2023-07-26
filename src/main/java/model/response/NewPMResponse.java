package model.response;

import model.main_model.room.Massage;
import model.main_model.room.PrivateChat;

import java.util.ArrayList;

public class NewPMResponse extends Response {
    private Massage massage;
    private String sender;
    private ArrayList<PrivateChat> newPrivateChat;

    public NewPMResponse(Massage massage, String sender, ArrayList<PrivateChat> newPrivateChat) {
        this.massage = massage;
        this.sender = sender;
        this.newPrivateChat = newPrivateChat;
    }

    public NewPMResponse() {
    }

    public Massage getMassage() {
        return massage;
    }

    public void setMassage(Massage massage) {
        this.massage = massage;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public ArrayList<PrivateChat> getNewPrivateChat() {
        return newPrivateChat;
    }

    public void setNewPrivateChat(ArrayList<PrivateChat> newPrivateChat) {
        this.newPrivateChat = newPrivateChat;
    }
}
