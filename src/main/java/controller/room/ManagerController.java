package controller.room;

import model.main_model.room.Manager;
import model.main_model.room.Room;

public class ManagerController {
    private Manager manager;

    public ManagerController(Manager manager) {
        this.manager = manager;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public void fireRequest(String username) {}
    public void startGameRequest() {
        manager.getRoom().getRoomController().startGame();
    }
    public void inviteFriendRequest(String username) {}
}
