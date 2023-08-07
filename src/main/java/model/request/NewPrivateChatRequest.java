package model.request;

import controller.ClientController;
import controller.connection.RequestVisitor;

public class NewPrivateChatRequest extends Request{
    private String opponentName;

    public NewPrivateChatRequest() {
    }

    @Override
    public void visit(RequestVisitor visitor, ClientController clientController) {
        visitor.visit(this,clientController);
    }

    public String getOpponentName() {
        return opponentName;
    }

    public void setOpponentName(String opponentName) {
        this.opponentName = opponentName;
    }
}
