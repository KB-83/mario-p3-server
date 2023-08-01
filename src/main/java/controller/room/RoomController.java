package controller.room;

import controller.ClientController;
import controller.GameStateManagerCreator;
import model.main_model.Client;
import model.main_model.room.Room;
import model.main_model.room.Viewer;
import model.response.GameStartResponse;
import util.Config;


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

}
