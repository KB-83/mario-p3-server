package model.response;


import model.main_model.chat.Chat;

import java.util.List;

public class ClientUpdateResponse extends Response{
    private int coin;
    private int diamond;
    private List<Chat> chats;
    public ClientUpdateResponse() {
    }

    public ClientUpdateResponse(int coin, int diamond, List<Chat> chats) {
        this.coin = coin;
        this.diamond = diamond;
        this.chats = chats;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public int getDiamond() {
        return diamond;
    }

    public void setDiamond(int diamond) {
        this.diamond = diamond;
    }

    public List<Chat> getChats() {
        return chats;
    }

    public void setChats(List<Chat> chats) {
        this.chats = chats;
    }
}
