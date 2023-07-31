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
    void visit(GetGameStateRequest request, ClientController clientController);
    void visit(PlayerActionRequest request, ClientController clientController);
    void visit(SendPMRequest request, ClientController clientController);
    void visit(BuyRequest request, ClientController clientController);
    void visit(SurvivalRequest request, ClientController clientController);
    void visit(GroupSurvivalRequest request, ClientController clientController);
    void visit(RoomRequest request, ClientController clientController);


}

