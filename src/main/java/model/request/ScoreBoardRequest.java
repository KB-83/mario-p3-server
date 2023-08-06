package model.request;

import controller.ClientController;
import controller.connection.RequestVisitor;

public class ScoreBoardRequest extends Request{
    public ScoreBoardRequest() {
    }

    @Override
    public void visit(RequestVisitor visitor, ClientController clientController) {
        visitor.visit(this,clientController);
    }
}
