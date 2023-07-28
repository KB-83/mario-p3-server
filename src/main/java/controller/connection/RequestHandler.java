package controller.connection;

import controller.ClientController;
import controller.gamelogic.playerlogic.PlayerRequestHandler;
import controller.ShopController;
import controller.game.GameWaitingRoom;
import controller.mapper.DTOCreator;
import model.dto.entity.PlayerDTO;
import model.dto.game.GameStateDTO;
import model.main_model.Bill;
import model.main_model.Client;
import model.main_model.room.Massage;
import model.main_model.room.PrivateChat;
import model.request.*;
import model.response.BuyResponse;
import model.response.GameStateStatusResponse;
import model.response.NewPMResponse;
import util.Config;
import util.Loader;
import util.Saver;

import java.util.ArrayList;

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
        clientController.setClient(Loader.getLoader().loadClient(request.getPassword(),request.getUsername(),clientController));
        if (clientController.getClient() != null) {
            Config.ONLINE_CLIENTS.put(clientController.getClient().getUsername(), clientController.getClient());
        }
    }
    @Override
    public void visit(SignInRequest request, ClientController clientController) {
        Client fish = Saver.getSaver().signInUser(request.getPassword(),request.getUsername(),clientController);
        if (fish != null){
            clientController.setClient(fish);
            Config.ONLINE_CLIENTS.put(fish.getUsername(),fish);
        }
    }

    @Override
    public void visit(MarathonRequest request, ClientController clientController) {
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
    @Override
    public void visit(PlayerActionRequest request, ClientController clientController) {
        PlayerRequestHandler handler = clientController.getClient().getPlayer().getPlayerController().getPlayerRequestHandler();
        switch (request.getType()) {
            case "right":
                handler.rightRequest();
                break;
            case "left":
                handler.leftRequest();
                break;
            case "rightD":
                handler.rightDoneRequest();
                break;
            case "leftD":
                handler.leftDoneRequest();
                break;
            case "bullet":
                handler.BulletRequest();
                break;
            case "jump":
                handler.jumpRequest();
                break;
            case "seat":
                handler.SeatRequest();
                break;
            case "sward":
                handler.SwardRequest();
                break;
            case "pause":
                handler.PauseRequest();
                break;
        }

//        GameWaitingRoom.getInstance().marathonClient(clientController.getClient());
    }

    @Override
    public void visit(SendPMRequest request, ClientController clientController) {

        boolean didChatBefore = false;
        PrivateChat preChat = null;
        for (PrivateChat privateChat : clientController.getClient().getPrivateChats()) {
            if (privateChat.getOpponentUsername().equals(request.getOpponentUserName())) {
                didChatBefore = true;
                preChat = privateChat;
                break;
            }
        }
        Client opponent = Config.CLIENTS.get(request.getOpponentUserName());

        if (didChatBefore) {
            preChat.getMassages().add(new Massage(true,request.getContext()));
            for (PrivateChat privateChat : opponent.getPrivateChats()) {
                if (privateChat.getOpponentUsername().equals(clientController.getClient().getUsername())) {
                   privateChat.getMassages().add(new Massage(false,request.getContext()));
                   break;
                }
            }
        }
        else {
            PrivateChat privateChat = new PrivateChat();
            privateChat.setOpponentUsername(request.getOpponentUserName());
            ArrayList<Massage> massages = new ArrayList<>();
            massages.add(new Massage(true,request.getContext()));
            privateChat.setMassages(massages);
            clientController.getClient().getPrivateChats().add(privateChat);
            privateChat.setOpponentUsername(clientController.getClient().getUsername());
            massages.get(0).setOwnersPM(false);
            opponent.getPrivateChats().add(privateChat);
        }
        // bara dotashoon be private chathashoon add mishe
        // in sakhte
        //client ha save mishan
        Saver.getSaver().saveUser(clientController.getClient());
        Saver.getSaver().saveUser(opponent);
//        va dobare toyr oon response send mishan
        if (Config.ONLINE_CLIENTS.containsKey(opponent.getUsername())) {
            Config.ONLINE_CLIENTS.get(opponent.getUsername()).setPrivateChats(opponent.getPrivateChats());
            opponent = Config.ONLINE_CLIENTS.get(opponent.getUsername());
            opponent.getClientController().sendResponse(new NewPMResponse(new Massage(false, request.getContext()),clientController.getClient().getUsername(),opponent.getPrivateChats()));
        }
        clientController.sendResponse(new NewPMResponse(new Massage(true,request.getContext()),"",clientController.getClient().getPrivateChats()));// bara khodesh

    }

    @Override
    public void visit(BuyRequest request, ClientController clientController) {
        Bill bill = ShopController.getInstance().returnCheckBuyRequest(request,clientController);
        if (bill != null) {
            clientController.sendResponse(new BuyResponse(bill,"r u sure?",true));
        }
        else {
            clientController.sendResponse(new BuyResponse(null,"oops",false));
        }
    }

}
