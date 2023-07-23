package controller.connection;

import controller.ClientController;
import controller.game.GameWaitingRoom;
import model.request.*;
import util.Loader;
import util.Saver;

public class RequestHandler implements RequestVisitor {
    private static RequestHandler requestResponseHandler;
    private RequestHandler() {}
    public static RequestHandler getInstance() {
        if (requestResponseHandler == null) {
            requestResponseHandler = new RequestHandler();
        }
        return requestResponseHandler;
    }
    @Override
    public void visit(ReceiveGame request, ClientController clientController) {
        //        response = new Game state Response // mapper dto game state ro misaze
        // clientController.sendResponse(response)
    }

    @Override
    public void visit(ReceiveItem request, ClientController clientController) {

    }
    @Override
    public void visit(LoginRequest request, ClientController clientController) {
        clientController.setClient(Loader.getLoader().loadClient(request.getUsername(),clientController));

    }
    @Override
    public void visit(SignInRequest request, ClientController clientController) {
        if (Saver.getSaver().saveUser(request.getUsername(),true,clientController)){
            clientController.setClient(Loader.getLoader().loadClient(request.getUsername(),clientController));
        }
    }

    @Override
    public void visit(MarathonRequest request, ClientController clientController) {
        System.out.println("marathon request sent");
        GameWaitingRoom.getInstance().marathonClient(clientController.getClient());
    }
}
