package controller.connection;

import controller.ClientController;
import controller.gamelogic.playerlogic.PlayerRequestHandler;
import controller.ShopController;
import controller.game.GameWaitingRoom;
import controller.mapper.DTOCreator;
import controller.room.RoomsManager;
import model.dto.entity.PlayerDTO;
import model.dto.game.GameStateDTO;
import model.main_model.Bill;
import model.main_model.Client;
import model.main_model.chat.Massage;
import model.main_model.chat.Chat;
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
    public void visit(SurvivalRequest request, ClientController clientController) {
        GameWaitingRoom.getInstance().survivalClient(clientController.getClient());
    }

    @Override
    public void visit(GroupSurvivalRequest request, ClientController clientController) {
        GameWaitingRoom.getInstance().groupSurvivalClient(clientController.getClient());
    }

    @Override
    public void visit(CreateRoomRequest request, ClientController clientController) {
        RoomsManager.createRoom(request,clientController);
    }

    @Override
    public void visit(RoomGameStartRequest request, ClientController clientController) {
        //test har otaghi hasto start kon
        RoomsManager.getRooms().get(0).getRoomController().startGame();
    }

    @Override
    public void visit(EnterRoomRequest request, ClientController clientController) {
        RoomsManager.roomEnterRequest(clientController,request.getToken(),request.isPlayer());
    }

    @Override
    public void visit(GetGameStateRequest request, ClientController clientController) {
        // todo: change it this response doesnt have to new
        try {
            Client client = clientController.getClient();
            GameStateDTO gameStateDTO = DTOCreator.updateGameStateDTO(client.getCurrentGameStateDTO(), client.getCurrentGameState());
            client.setCurrentGameStateDTO(gameStateDTO);
            PlayerDTO playerDTO = DTOCreator.updatePlayerDTO(clientController.getClient().getPlayer(), clientController.getClient().getPlayerDTO());
            client.setPlayerDTO(playerDTO);
            clientController.sendResponse(new GameStateStatusResponse(gameStateDTO, playerDTO));
        }
        catch (NullPointerException e){
            System.out.println("still looks for update");
        }
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
        String senderUsername = clientController.getClient().getUsername();
        boolean didChatBefore = false;
        Chat preChat = null;
        for (Chat chat : clientController.getClient().getChats()) {
            if (chat.getOpponentUsername().equals(request.getMassage().getReceiverUsername())) {
                didChatBefore = true;
                preChat = chat;
                break;
            }
        }
        Client opponent = Config.CLIENTS.get(request.getMassage().getReceiverUsername());

        if (didChatBefore) {
            preChat.getMassages().add(new Massage(senderUsername,request.getMassage().getContext()));
            for (Chat chat : opponent.getChats()) {
                if (chat.getOpponentUsername().equals(clientController.getClient().getUsername())) {
                   chat.getMassages().add(new Massage(senderUsername,request.getMassage().getContext()));
                   break;
                }
            }
        }
        else {
            Chat chat = new Chat();
            chat.setOpponentUsername(request.getMassage().getReceiverUsername());
            ArrayList<Massage> massages = new ArrayList<>();
            massages.add(new Massage(senderUsername,request.getMassage().getContext()));
            chat.setMassages(massages);
            clientController.getClient().getChats().add(chat);
            chat.setOpponentUsername(clientController.getClient().getUsername());
            massages.get(0).setSenderUsername(senderUsername);
            opponent.getChats().add(chat);
        }
        // bara dotashoon be private chathashoon add mishe
        // in sakhte
        //client ha save mishan
        Saver.getSaver().saveUser(clientController.getClient());
        Saver.getSaver().saveUser(opponent);
//        va dobare toyr oon response send mishan
        if (Config.ONLINE_CLIENTS.containsKey(opponent.getUsername())) {
            Config.ONLINE_CLIENTS.get(opponent.getUsername()).setChats(opponent.getChats());
            opponent = Config.ONLINE_CLIENTS.get(opponent.getUsername());
            opponent.getClientController().sendResponse(new NewPMResponse(new Massage(senderUsername, request.getMassage().getContext()),clientController.getClient().getUsername(),opponent.getChats()));
        }
        clientController.sendResponse(new NewPMResponse(new Massage(senderUsername,request.getMassage().getContext()),"",clientController.getClient().getChats()));// bara khodesh

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
