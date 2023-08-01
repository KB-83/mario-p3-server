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
    private Manager manager;
    private List<Assistant> assistants;
    private List<Viewer> viewers;
    private List<Client> players;
    private GameStateController gameStateController;
    private Chat chat;

    public Room(Manager manager,String token) {
        this.manager = manager;
        this.token = token;
        /////// bug take it
        clients.add(manager.getClientController().getClient());
        chat = new Chat();
        chat.setOpponentUsername(token);
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public List<Assistant> getAssistants() {
        return assistants;
    }

    public void setAssistants(List<Assistant> assistants) {
        this.assistants = assistants;
    }

    public List<Viewer> getViewers() {
        return viewers;
    }

    public void setViewers(List<Viewer> viewers) {
        this.viewers = viewers;
    }

    public List<Client> getPlayers() {
        return players;
    }

    public void setPlayers(List<Client> players) {
        this.players = players;
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
