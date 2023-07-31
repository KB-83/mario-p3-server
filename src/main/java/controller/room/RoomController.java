package controller.room;

import controller.ClientController;
import model.main_model.Client;
import model.main_model.room.Manager;
import model.main_model.room.Room;
import model.main_model.room.TokenGenerator;
import model.request.RoomRequest;
import model.response.RoomResponse;

import java.util.ArrayList;

public class RoomController {
    public static ArrayList<Room> rooms;//maybe in feature add a rooms manage singlton class
    private Room room;
    private void addVisitor() {}
    private void showChat() {}
    private void closeRoom() {}
    private void managerLeft() {
        closeRoom();
    }
    private void clientLeft() {}
    public Room getRoom() {
        return room;
    }
    public static void createRoom(RoomRequest roomRequest, ClientController clientController){
        Manager manager = new Manager();
        manager.setClientController(clientController);
        Room room1 = new Room(manager, TokenGenerator.generateRandomToken());
        RoomResponse roomResponse = new RoomResponse(room1.getToken());
        clientController.sendResponse(roomResponse);
    }
}
