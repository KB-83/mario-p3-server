package model.request;

import controller.ClientController;
import controller.connection.RequestVisitor;
import model.main_model.chat.Massage;

public class SendPMRequest extends Request{
    private Massage massage;

    public SendPMRequest() {
    }

    public Massage getMassage() {
        return massage;
    }

    public void setMassage(Massage massage) {
        this.massage = massage;
    }

    @Override
    public void visit(RequestVisitor visitor, ClientController clientController) {
        visitor.visit(this,clientController);
    }
}
