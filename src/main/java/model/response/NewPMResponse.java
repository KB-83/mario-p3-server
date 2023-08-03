package model.response;

import model.main_model.chat.Massage;
import model.main_model.chat.Chat;

import java.util.ArrayList;
import java.util.List;

public class NewPMResponse extends Response {
    private Massage massage;
    private List<Chat> newChat;

    public NewPMResponse(Massage massage, String sender, List<Chat> newChat) {
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

    public List<Chat> getNewPrivateChat() {
        return newChat;
    }

    public void setNewPrivateChat(List<Chat> newChat) {
        this.newChat = newChat;
    }
}
