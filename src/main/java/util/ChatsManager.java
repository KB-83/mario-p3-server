package util;

import controller.ClientController;
import controller.room.RoomsManager;
import model.main_model.Client;
import model.main_model.chat.Chat;
import model.main_model.chat.Massage;
import model.main_model.room.Room;
import model.request.NewPrivateChatRequest;
import model.request.SendPMRequest;
import model.response.ClientUpdateResponse;
import model.response.NewPMResponse;

import java.util.ArrayList;

public class ChatsManager {
    private ChatsManager(){}
    public static void visitNewPmRequest(SendPMRequest request, ClientController clientController) {
        String senderUsername = clientController.getClient().getUsername();
        Chat clientChat = null;
        for (Chat chat : clientController.getClient().getChats()) {
            if (chat.getOpponentUsername().equals(request.getMassage().getReceiverUsername())) {
                clientChat = chat;
                break;
            }
        }
        if (!Config.CLIENTS.containsKey(request.getMassage().getReceiverUsername())) {
            for (Room room : RoomsManager.getRooms()) {
                if (room.getToken().equals(request.getMassage().getReceiverUsername())) {
                    room.getRoomController().newPM(request);
                    return;
                }
            }
            return;
        }
        Client opponent ;
        String opponentUsername = request.getMassage().getReceiverUsername();
        Massage massage = new Massage(senderUsername,opponentUsername,request.getMassage().getContext());
        if (Config.ONLINE_CLIENTS.containsKey(opponentUsername)) {
            opponent = Config.ONLINE_CLIENTS.get(opponentUsername);
        }
        else {
            opponent = Loader.getLoader().loadClient(opponentUsername,
                    Config.CLIENTS.get(opponentUsername), null);
        }
        Chat opponentChat = null;
        clientChat.getMassages().add(massage);
        for (Chat chat : opponent.getChats()) {
            if (chat.getOpponentUsername().equals(clientController.getClient().getUsername())) {
                chat.getMassages().add(massage);
                opponentChat = chat;
                break;
            }
        }

        Saver.getSaver().addMassageToChat(clientChat,massage);
        Saver.getSaver().addMassageToChat(opponentChat,massage);

        if (Config.ONLINE_CLIENTS.containsKey(opponent.getUsername())) {
            opponent.getClientController().sendResponse(new NewPMResponse(new
                    Massage(senderUsername, opponent.getUsername(),
                    request.getMassage().getContext()),
                    clientController.getClient().getUsername(),opponent.getChats()));
        }
        clientController.sendResponse(new NewPMResponse(new
                Massage(senderUsername,opponent.getUsername(),
                request.getMassage().getContext()),""
                ,clientController.getClient().getChats()));// bara khodesh
    }
    public static void visitNewPrivateChatRequest(NewPrivateChatRequest request, ClientController clientController) {
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
        Client opponent;
        if (Config.ONLINE_CLIENTS.containsKey(request.getOpponentName())) {
            opponent = Config.ONLINE_CLIENTS.get(request.getOpponentName());
            isOpponentOnline = true;
        }
        else {
            opponent = Loader.getLoader().loadClient(request.getOpponentName(),
                    Config.CLIENTS.get(request.getOpponentName()),null);
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
    public static ArrayList<String> userNamesList(String username, String s) {
        ArrayList<String> clientsName = new ArrayList<>();
        for (String name : Config.CLIENTS.keySet()) {
            if (name.contains(s) && !name.equals(username)) {
                clientsName.add(name);
            }
        }
        return clientsName;
    }

}
