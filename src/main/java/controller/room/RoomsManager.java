package controller.room;

import controller.ClientController;
import model.main_model.room.Manager;
import model.main_model.room.Room;
import model.main_model.room.TokenGenerator;
import model.request.RoomRequest;
import model.response.RoomResponse;

import java.util.ArrayList;

public class RoomsManager {
    private ArrayList<Room> rooms;
    private static RoomsManager roomsManager;
    public static RoomsManager getInstance(){
        if (roomsManager == null) {
            roomsManager = new RoomsManager();
        }
        return roomsManager;
    }
    private RoomsManager() {}
    public void createRoom(RoomRequest roomRequest, ClientController clientController){
        Manager manager = new Manager();
        manager.setClientController(clientController);
        Room room = new Room(manager, TokenGenerator.generateRandomToken()); // bayad check beshe yski nabashe
        RoomResponse roomResponse = new RoomResponse(room.getToken());
        clientController.sendResponse(roomResponse);
        RoomController roomController = new RoomController(room);
//        room.seRoomController
        rooms.add(room);
    }

}
