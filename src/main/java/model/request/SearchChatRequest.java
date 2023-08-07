package model.request;

import controller.ClientController;
import controller.connection.RequestVisitor;

public class SearchChatRequest extends Request{
    private String username;
    public SearchChatRequest() {
    }

    @Override
    public void visit(RequestVisitor visitor, ClientController clientController) {
        visitor.visit(this,clientController);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
