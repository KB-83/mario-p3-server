package controller.room;

import controller.ClientController;
import model.main_model.chat.Chat;
import model.main_model.room.Manager;
import model.main_model.room.Room;
import model.main_model.room.TokenGenerator;
import model.request.CreateRoomRequest;
import model.response.DialogResponse;
import model.response.EnterRoomResponse;
import model.response.RoomResponse;

import java.util.ArrayList;

public class RoomsManager {
    private static final ArrayList<Room> rooms = new ArrayList<>();
    private static RoomsManager roomsManager;
//    public static RoomsManager getInstance(){
//        if (roomsManager == null) {
//            roomsManager = new RoomsManager();
//        }
//        return roomsManager;
//    }
    private RoomsManager() {}
    public static void createRoom(CreateRoomRequest createRoomRequest, ClientController clientController){
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
    public static void roomEnterRequest(ClientController clientController,String token, boolean isPlayer) {
        for (Room room : rooms) {
            if(room.getToken().equals(token)) {
                if (isPlayer) {
                    room.getRoomController().addPLayer(clientController.getClient().getUsername());
                }
                else {
                    room.getRoomController().addViewer(clientController.getClient().getUsername());
                }
                clientController.sendResponse(new EnterRoomResponse());
                return;
            }
        }
        //return massage that room is full
        clientController.sendResponse(new DialogResponse("WRONG TOKEN"));

    }

    public static ArrayList<Room> getRooms() {
        return rooms;
    }

}
