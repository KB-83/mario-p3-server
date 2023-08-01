package model.request;

import controller.ClientController;
import controller.connection.RequestVisitor;

public class RoomGameStartRequest extends Request{
    private boolean isFromRoomManager;

    public RoomGameStartRequest() {
    }
    @Override
    public void visit(RequestVisitor visitor, ClientController clientController) {
        visitor.visit(this,clientController);
    }
    public boolean isFromRoomManager() {
        return isFromRoomManager;
    }

    public void setFromRoomManager(boolean fromRoomManager) {
        isFromRoomManager = fromRoomManager;
    }
}
