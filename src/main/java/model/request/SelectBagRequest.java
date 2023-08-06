package model.request;

import controller.ClientController;
import controller.connection.RequestVisitor;

public class SelectBagRequest extends Request{
    private String[] bag;
    public SelectBagRequest() {
    }

    public SelectBagRequest(String[] bag) {
        this.bag = bag;
    }

    public String[] getBag() {
        return bag;
    }

    public void setBag(String[] bag) {
        this.bag = bag;
    }

    @Override
    public void visit(RequestVisitor visitor, ClientController clientController) {
        visitor.visit(this,clientController);
    }
}
