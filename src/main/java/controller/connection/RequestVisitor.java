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
    void visit(CreateRoomRequest request, ClientController clientController);
    void visit(RoomGameStartRequest request, ClientController clientController);
    void visit(EnterRoomRequest request, ClientController clientController);
    void visit(FinalBuyRequest request, ClientController clientController);
    void visit(ScoreBoardRequest request, ClientController clientController);
    void visit(SearchTableRequest request, ClientController clientController);
    void visit(SelectBagRequest request, ClientController clientController);
}

