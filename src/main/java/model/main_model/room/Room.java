package model.main_model.room;

import model.main_model.Client;

import java.util.List;

public class Room {
    private Manager manager;
    private List<Assistant> assistants;
    private List<Viewer> viewers;
    private List<Client> players;
    private String token;

    public Room(Manager manager,String token) {
        this.manager = manager;
        this.token = token;
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
}
