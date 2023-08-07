package model.request;

import controller.ClientController;
import controller.connection.RequestVisitor;

public class RoomGameStartRequest extends Request{
    private boolean isFromRoomManager;
    private String token;

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
