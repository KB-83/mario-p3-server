package model.request;

import controller.ClientController;
import controller.connection.RequestVisitor;

public class EnterRoomRequest extends Request{
    private String token;
    private boolean isPlayer;

    public EnterRoomRequest() {
    }

    @Override
    public void visit(RequestVisitor visitor, ClientController clientController) {
        visitor.visit(this,clientController);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isPlayer() {
        return isPlayer;
    }

    public void setPlayer(boolean player) {
        isPlayer = player;
    }
}
