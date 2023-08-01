package model.main_model.room;

import controller.room.ManagerController;
import model.main_model.Client;

public class Manager extends Client {
    private Room room;
    private ManagerController managerController;

    public Manager() {
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }

    public ManagerController getManagerController() {
        return managerController;
    }

    public void setManagerController(ManagerController managerController) {
        this.managerController = managerController;
    }
}
