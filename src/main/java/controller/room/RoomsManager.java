package controller.room;

import controller.ClientController;
import model.main_model.chat.Chat;
import model.main_model.room.Manager;
import model.main_model.room.Room;
import model.main_model.room.TokenGenerator;
import model.request.RoomRequest;
import model.response.RoomResponse;

import java.util.ArrayList;

public class RoomsManager {
    private final ArrayList<Room> rooms = new ArrayList<>();
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
        Chat chat = new Chat();
        chat.setOpponentUsername(room.getToken());
        RoomController roomController = new RoomController(room);
        room.setRoomController(roomController);
        manager.setRoom(room);
        manager.setManagerController(new ManagerController(manager));
//        room.seRoomController
        rooms.add(room);
        RoomResponse roomResponse = new RoomResponse(room.getToken());
        clientController.sendResponse(roomResponse);
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

}
