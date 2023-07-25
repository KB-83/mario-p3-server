package model.request;

import controller.ClientController;
import controller.connection.RequestVisitor;

public class PlayerActionRequest extends Request{
    private String type;

    public PlayerActionRequest() {
    }

    @Override
    public void visit(RequestVisitor visitor, ClientController clientController) {
        visitor.visit(this,clientController);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
