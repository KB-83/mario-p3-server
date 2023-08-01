package model.response;

import model.main_model.chat.Massage;
import model.main_model.chat.Chat;

import java.util.ArrayList;

public class NewPMResponse extends Response {
    private Massage massage;
    private ArrayList<Chat> newChat;

    public NewPMResponse(Massage massage, String sender, ArrayList<Chat> newChat) {
        this.massage = massage;
        this.newChat = newChat;
    }

    public NewPMResponse() {
    }

    public Massage getMassage() {
        return massage;
    }

    public void setMassage(Massage massage) {
        this.massage = massage;
    }

    public ArrayList<Chat> getNewPrivateChat() {
        return newChat;
    }

    public void setNewPrivateChat(ArrayList<Chat> newChat) {
        this.newChat = newChat;
    }
}
