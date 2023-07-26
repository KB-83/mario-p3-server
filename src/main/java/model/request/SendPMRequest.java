package model.request;

import controller.ClientController;
import controller.connection.RequestVisitor;

public class SendPMRequest extends Request{
    private String opponentUserName;
    private String Context;

    public SendPMRequest() {
    }


    public String getOpponentUserName() {
        return opponentUserName;
    }

    public void setOpponentUserName(String opponentUserName) {
        this.opponentUserName = opponentUserName;
    }

    public String getContext() {
        return Context;
    }

    public void setContext(String context) {
        Context = context;
    }

    @Override
    public void visit(RequestVisitor visitor, ClientController clientController) {
        visitor.visit(this,clientController);
    }
}
