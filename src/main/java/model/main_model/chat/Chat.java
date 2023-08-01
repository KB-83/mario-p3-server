package model.main_model.chat;

import java.util.ArrayList;

public class Chat {
    // if room its a room token
    private String opponentUsername;
    private ArrayList<Massage> massages;

    public Chat() {
    }

    public Chat(String opponentUsername, ArrayList<Massage> massages) {
        this.opponentUsername = opponentUsername;
        this.massages = massages;
    }

    public String getOpponentUsername() {
        return opponentUsername;
    }

    public void setOpponentUsername(String opponentUsername) {
        this.opponentUsername = opponentUsername;
    }

    public ArrayList<Massage> getMassages() {
        return massages;
    }

    public void setMassages(ArrayList<Massage> massages) {
        this.massages = massages;
    }
}
