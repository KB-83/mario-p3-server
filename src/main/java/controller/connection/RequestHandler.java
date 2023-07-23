package controller.connection;

import controller.ClientController;
import controller.game.GameWaitingRoom;
import controller.mapper.DTOCreator;
import model.dto.entity.player.PlayerDTO;
import model.dto.game.GameStateDTO;
import model.main_model.Client;
import model.request.*;
import model.response.GameStateStatusResponse;
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

    @Override
    public void visit(GetGameStateRequest request, ClientController clientController) {
        // todo: change it this response doesnt have to new
        Client client = clientController.getClient();
        GameStateDTO gameStateDTO= DTOCreator.updateGameStateDTO(client.getCurrentGameStateDTO(),client.getCurrentGameState());
        client.setCurrentGameStateDTO(gameStateDTO);
        PlayerDTO playerDTO = DTOCreator.updatePlayerDTO(clientController.getClient().getPlayer(),clientController.getClient().getPlayerDTO());
        client.setPlayerDTO(playerDTO);
        clientController.sendResponse(new GameStateStatusResponse(gameStateDTO,playerDTO));
    }
}
