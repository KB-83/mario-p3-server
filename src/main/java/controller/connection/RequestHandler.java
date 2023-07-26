package controller.connection;

import controller.ClientController;
import controller.PlayerRequestHandler;
import controller.game.GameWaitingRoom;
import controller.mapper.DTOCreator;
import model.dto.entity.PlayerDTO;
import model.dto.game.GameStateDTO;
import model.main_model.Client;
import model.main_model.room.Massage;
import model.main_model.room.PrivateChat;
import model.request.*;
import model.response.GameStateStatusResponse;
import model.response.NewPMResponse;
import util.Config;
import util.Constant;
import util.Loader;
import util.Saver;

import java.util.ArrayList;
import java.util.Arrays;

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
        Config.ONLINE_CLIENTS.put(clientController.getClient().getUsername(),clientController.getClient());
    }
    @Override
    public void visit(SignInRequest request, ClientController clientController) {
        if (Saver.getSaver().saveUser(request.getUsername(),true,clientController,clientController.getClient())){
            clientController.setClient(Loader.getLoader().loadClient(request.getUsername(),clientController));
            Config.ONLINE_CLIENTS.put(clientController.getClient().getUsername(),clientController.getClient());
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
                handler.RightRequest();
                break;
            case "left":
                handler.LeftRequest();
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
        System.out.println(request.getOpponentUserName());
        System.out.println(Config.CLIENTS.size());
        System.out.println(Config.CLIENTS.get(request.getOpponentUserName()).getUsername());
        Client opponent = Config.CLIENTS.get(request.getOpponentUserName());

        if (didChatBefore) {
            System.out.println(118);
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
        Saver.getSaver().saveUser(clientController.getClient().getUsername(),false,null,clientController.getClient());
        Saver.getSaver().saveUser(opponent.getUsername(),false,null,opponent);
//        va dobare toyr oon response send mishan
        if (Config.ONLINE_CLIENTS.containsKey(opponent.getUsername())) {
            Config.ONLINE_CLIENTS.get(opponent.getUsername()).setPrivateChats(opponent.getPrivateChats());
            opponent = Config.ONLINE_CLIENTS.get(opponent.getUsername());
            opponent.getClientController().sendResponse(new NewPMResponse(new Massage(false, request.getContext()),clientController.getClient().getUsername(),opponent.getPrivateChats()));
        }
        clientController.sendResponse(new NewPMResponse(new Massage(true,request.getContext()),"",clientController.getClient().getPrivateChats()));// bara khodesh

    }

}
