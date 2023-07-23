package model.request;

import controller.ClientController;
import controller.connection.RequestVisitor;

public class GetGameStateRequest extends Request{
    public GetGameStateRequest() {
    }

    @Override
    public void visit(RequestVisitor visitor, ClientController clientController) {
        visitor.visit(this,clientController);
    }
}
