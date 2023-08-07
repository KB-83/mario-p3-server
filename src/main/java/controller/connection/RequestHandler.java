package controller.connection;

import controller.BagController;
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
import model.main_model.room.Room;
import model.main_model.score_board.ScoreBoard;
import model.request.*;
import model.response.*;
import org.hibernate.PropertyValueException;
import util.ChatDAO;
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
        clientController.setClient(Loader.getLoader().loadClient(request.getUsername(),request.getPassword(),clientController));
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
        RoomsManager.getRoomByToken(request.getToken()).getRoomController().startGame();
//        RoomsManager.getRooms().remove() age pause nadash pak mishe haminja
    }

    @Override
    public void visit(EnterRoomRequest request, ClientController clientController) {
        RoomsManager.roomEnterRequest(clientController,request.getToken(),request.isPlayer());
    }

    @Override
    public void visit(FinalBuyRequest request, ClientController clientController) {
        ShopController.getInstance().finalBuyRequest(request,clientController);
        Client client = clientController.getClient();
        clientController.sendResponse(new ClientUpdateResponse(client.getCoin(),client.getDiamond(),null));
    }

    @Override
    public void visit(ScoreBoardRequest request, ClientController clientController) {
        clientController.sendResponse(new ScoreBoardResponse(ScoreBoard.getScoreBoard()));
    }

    @Override
    public void visit(SearchTableRequest request, ClientController clientController) {
        if(request.isUserName()) {
            clientController.sendResponse(new ScoreBoardResponse(ScoreBoard.searchByUsername(request.getUsername())));
        }
        else {
            clientController.sendResponse(new ScoreBoardResponse(ScoreBoard.searchByScoreRange(request.getMin(),request.getMax())));
        }
    }

    @Override
    public void visit(SelectBagRequest request, ClientController clientController) {
        if (BagController.checkBag(request,clientController)) {
            clientController.sendResponse(new DialogResponse("done successfully"));
        }
        else {
            clientController.sendResponse(new DialogResponse("not enough item!"));
        }
    }

    @Override
    public void visit(NewPrivateChatRequest request, ClientController clientController) {
        Chat chat = new Chat();
        chat.setOpponentUsername(request.getOpponentName());
        Client client = clientController.getClient();
        ArrayList<Massage> massages = new ArrayList<>();
        chat.setMassages(massages);
        client.getChats().add(chat);
        chat.setClient(client);
        Saver.getSaver().updateClient(client);
        clientController.sendResponse(
                new ClientUpdateResponse(client.getCoin(),client.getDiamond(),client.getChats()));

        boolean isOpponentOnline = false;
        Client opponent = null;
        if (Config.ONLINE_CLIENTS.containsKey(request.getOpponentName())) {
            opponent = Config.ONLINE_CLIENTS.get(request.getOpponentName());
            isOpponentOnline = true;
        }
        else {
            opponent = Config.CLIENTS.get(request.getOpponentName());
        }
        chat = new Chat();
        chat.setMassages(new ArrayList<>());
        chat.setOpponentUsername(clientController.getClient().getUsername());
        if (opponent.getChats() == null) {
            opponent.setChats(new ArrayList<>());
        }
        opponent.getChats().add(chat);
        //for db
        chat.setClient(opponent);
        //
        Saver.getSaver().updateClient(opponent);
        if (isOpponentOnline) {
            opponent.getClientController().sendResponse(
                    new ClientUpdateResponse(opponent.getCoin(),opponent.getDiamond(),opponent.getChats()));
        }
    }

    @Override
    public void visit(SearchChatRequest request, ClientController clientController) {
        ArrayList<String> usernames = ChatDAO.userNamesList(
                clientController.getClient().getUsername(),request.getUsername());
        clientController.sendResponse(new ChatSearchResponse(usernames));
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
                handler.bulletRequest();
                break;
            case "jump":
                handler.jumpRequest();
                break;
            case "seat":
                handler.seatRequest();
                break;
            case "sward":
                handler.swardRequest();
                break;
            case "pause":
                handler.PauseRequest();
                break;
            case "damageBomb":
                handler.powerItemRequest("damageBomb", clientController);
                break;
            case "speedBomb":
                handler.powerItemRequest("speedBomb", clientController);
                break;
            case "hammer":
                handler.powerItemRequest("hammer", clientController);
                break;
            case "healthPotion":
                handler.powerItemRequest("healthPotion", clientController);
                break;
            case "invisibilityPotion":
                handler.powerItemRequest("invisibilityPotion", clientController);
                break;
            case "speedPotion":
                handler.powerItemRequest("speedPotion", clientController);
                break;
        }

//        GameWaitingRoom.getInstance().marathonClient(clientController.getClient());
    }

    @Override
    public void visit(SendPMRequest request, ClientController clientController) {
        String senderUsername = clientController.getClient().getUsername();
        boolean didChatBefore = false;
        Chat clientChat = null;
        for (Chat chat : clientController.getClient().getChats()) {
            if (chat.getOpponentUsername().equals(request.getMassage().getReceiverUsername())) {
                didChatBefore = true;
                clientChat = chat;
                break;
            }
        }
        Client opponent = Config.CLIENTS.get(request.getMassage().getReceiverUsername());
        if (opponent == null) {
            for (Room room : RoomsManager.getRooms()) {
                if (room.getToken().equals(request.getMassage().getReceiverUsername())) {
                    room.getRoomController().newPM(request);
                    return;
                }
            }
            return;
        }
        Massage massage = new Massage(senderUsername,opponent.getUsername(),request.getMassage().getContext());
        if (Config.ONLINE_CLIENTS.containsKey(opponent.getUsername())) {
            opponent = Config.ONLINE_CLIENTS.get(opponent.getUsername());
        }
        else {
            opponent = Loader.getLoader().loadClient(opponent.getUsername(), opponent.getPassword(), null);
        }
        Chat opponentChat = null;
        if (didChatBefore) {
            clientChat.getMassages().add(massage);
            for (Chat chat : opponent.getChats()) {
                if (chat.getOpponentUsername().equals(clientController.getClient().getUsername())) {
                   chat.getMassages().add(massage);
                   opponentChat = chat;
                   break;
                }
            }
        }
        else {
            Chat chat = new Chat();
            chat.setOpponentUsername(request.getMassage().getReceiverUsername());
            ArrayList<Massage> massages = new ArrayList<>();
            massages.add(new Massage(senderUsername,opponent.getUsername(),request.getMassage().getContext()));
            chat.setMassages(massages);
            clientController.getClient().getChats().add(chat);
            chat.setOpponentUsername(clientController.getClient().getUsername());
            massages.get(0).setSenderUsername(senderUsername);
            //add room chat //todo
            opponent.getChats().add(chat);
        }
        // bara dotashoon be private chathashoon add mishe
        // in sakhte
        //client ha save mishan
        System.out.println("send pm request");
        Saver.getSaver().addMassageToChat(clientChat,massage);
        try {
            Saver.getSaver().addMassageToChat(opponentChat,massage);
        }
        catch (PropertyValueException e) {
            System.out.println(massage);
            System.out.println(opponentChat);;
            System.out.println(opponent.getUsername());
            e.printStackTrace();
        }

//        Saver.getSaver().updateClient(clientController.getClient());
//        Saver.getSaver().updateClient(opponent);
//        va dobare toyr oon response send mishan
        if (Config.ONLINE_CLIENTS.containsKey(opponent.getUsername())) {
            Config.ONLINE_CLIENTS.get(opponent.getUsername()).setChats(opponent.getChats());
            opponent = Config.ONLINE_CLIENTS.get(opponent.getUsername());
            opponent.getClientController().sendResponse(new NewPMResponse(new Massage(senderUsername, opponent.getUsername(),request.getMassage().getContext()),clientController.getClient().getUsername(),opponent.getChats()));
        }
        clientController.sendResponse(new NewPMResponse(new Massage(senderUsername,opponent.getUsername(),request.getMassage().getContext()),"",clientController.getClient().getChats()));// bara khodesh

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
