package model.dto;
import model.main_model.chat.Chat;

import java.util.ArrayList;

public class RoomDTO {
    private ArrayList<String> roomUsers;
    private Chat roomChat;

    public RoomDTO(ArrayList<String> roomUsers, Chat roomChat) {
        this.roomUsers = roomUsers;
        this.roomChat = roomChat;
    }

    public RoomDTO() {

    }
    public ArrayList<String> getRoomUsers() {
        return roomUsers;
    }

    public void setRoomUsers(ArrayList<String> roomUsers) {
        this.roomUsers = roomUsers;
    }

    public Chat getRoomChat() {
        return roomChat;
    }

    public void setRoomChat(Chat roomChat) {
        this.roomChat = roomChat;
    }
}
