package model.main_model.room;

import controller.gamelogic.gamestatelogic.GameStateController;
import controller.room.RoomController;
import model.main_model.Client;
import model.main_model.chat.Chat;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private String token;
    private RoomController roomController;
    private final ArrayList<Client> clients = new ArrayList<>();
    private Client manager;
    private final ArrayList<Client> assistants = new ArrayList<>();
    private final ArrayList<Client> viewers = new ArrayList<>();
    private final ArrayList<Client> players = new ArrayList<>();
    private GameStateController gameStateController;
    private Chat chat;

    public Room(Client manager,String token) {
        this.manager = manager;
        this.token = token;
        /////// bug take it
        clients.add(manager);
        manager.setCurrentRoom(this);
        chat = new Chat();
        chat.setOpponentUsername(token);
    }



    public List<Client> getPlayers() {
        return players;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public GameStateController getGameStateController() {
        return gameStateController;
    }

    public void setGameStateController(GameStateController gameStateController) {
        this.gameStateController = gameStateController;
    }

    public Client getManager() {
        return manager;
    }

    public List<Client> getAssistants() {
        return assistants;
    }

    public List<Client> getViewers() {
        return viewers;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public RoomController getRoomController() {
        return roomController;
    }

    public void setRoomController(RoomController roomController) {
        this.roomController = roomController;
    }
}
