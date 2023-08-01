package controller.room;

import controller.ClientController;
import controller.GameStateManagerCreator;
import model.dto.RoomDTO;
import model.main_model.Client;
import model.main_model.chat.Chat;
import model.main_model.chat.Massage;
import model.main_model.room.Room;
import model.main_model.room.Viewer;
import model.request.SendPMRequest;
import model.response.GameStartResponse;
import model.response.NewPMResponse;
import model.response.RoomChatUpdateResponse;
import model.response.RoomUpdateResponse;
import util.Config;
import util.Saver;

import java.util.ArrayList;


public class RoomController {
    private Room room;

    public RoomController(Room room) {
        this.room = room;
    }

    public void addViewer (String username) {
        Client client = Config.findOnlineClientByUserName(username);
//        Viewer viewer = new Viewer();
//        viewer.setClientController(client.getClientController());
//        viewer.setUsername(client.getUsername());
        room.getViewers().add(client);
        room.getClients().add(client);
    }
    public void addPLayer(String username) {
        Client client = Config.findOnlineClientByUserName(username);
        room.getPlayers().add(client);
        room.getClients().add(client);
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
    public void startGame(){
        //            todo : add if its room so no score no payment

        double i = Math.random();
        if (i < 0.33) {
            room.setGameStateController(GameStateManagerCreator.createMarathon(room.getClients()));
//            gameState = GA
        }
        else {
            room.setGameStateController(GameStateManagerCreator.createSurvival(room.getClients()));
//            GameStateController gameStateController = new GroupSurvival(room.getClients(),); todo :do team one team two
        }
        room.getGameStateController().startGameState();
        for (Client client : room.getClients()) {
            client.getClientController().sendResponse(new GameStartResponse(client.getCurrentGameStateDTO(),client.getPlayerDTO()));
        }
    }
    public void updateClientsRoom() {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setRoomChat(room.getChat());
        ArrayList<String> users = new ArrayList<>();
        for (Client client : room.getClients()) {
            users.add(client.getUsername());
        }
        roomDTO.setRoomUsers(users);
        for (Client client : room.getClients()) {
            client.getClientController().sendResponse(new RoomUpdateResponse(roomDTO));
        }
    }
    public void newPM(SendPMRequest request) {
        String senderUsername = request.getMassage().getSenderUsername();
        room.getChat().getMassages().add(new Massage(senderUsername,request.getMassage().getContext()));
        //test that an offline client would be removed from room clients
        for (Client client : room.getClients()) {
            client.getClientController().sendResponse(new RoomChatUpdateResponse(room.getChat()));
        }
    }

}
