package controller.connection;

import controller.ClientController;
import model.request.*;

// Visitor interface
public interface RequestVisitor {
    void visit(ReceiveGame request, ClientController clientController);
    void visit(ReceiveItem request, ClientController clientController);
    void visit(LoginRequest request, ClientController clientController);
    void visit(SignInRequest request, ClientController clientController);
    void visit(MarathonRequest request, ClientController clientController);


}

