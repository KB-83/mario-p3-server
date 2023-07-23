package model.request;

import controller.ClientController;
import controller.connection.RequestVisitor;

public class ReceiveGame extends Request{

    @Override
    public void visit(RequestVisitor visitor, ClientController clientController) {
        visitor.visit(this, clientController);
    }
}
