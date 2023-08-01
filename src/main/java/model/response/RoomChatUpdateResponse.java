package model.response;

import model.main_model.chat.Chat;

public class RoomChatUpdateResponse extends Response {
    private Chat chat;

    public RoomChatUpdateResponse() {
    }

    public RoomChatUpdateResponse(Chat chat) {
        this.chat = chat;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }
}
