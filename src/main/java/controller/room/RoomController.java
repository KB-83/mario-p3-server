package controller.room;

import controller.ClientController;
import model.main_model.Client;
import model.main_model.room.Manager;
import model.main_model.room.Room;
import model.main_model.room.TokenGenerator;
import model.main_model.room.Viewer;
import model.request.RoomRequest;
import model.response.RoomResponse;
import util.Config;

import java.util.ArrayList;

public class RoomController {
    private Room room;

    public RoomController(Room room) {
        this.room = room;
    }

    private void addViewer (String username) {
        Client client = Config.findOnlineClientByUserName(username);
        Viewer viewer = new Viewer();
        viewer.setClientController(client.getClientController());
        viewer.setUsername(client.getUsername());
        room.getViewers().add(viewer);
    }
    private void addPLayer(String username) {
        Client client = Config.findOnlineClientByUserName(username);
        room.getPlayers().add(client);
    }
    private void showChat() {}
    private void closeRoom() {}
    private void managerLeft() {
        closeRoom();
    }
    private void clientLeft() {}
    public Room getRoom() {
        return room;
    }

}
