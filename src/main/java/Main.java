
import controller.ServerController;
import model.main_model.Client;
import model.main_model.chat.Chat;
import model.main_model.chat.Massage;
import util.Config;
import util.Loader;
import util.Saver;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {

        Config.loadConfigs();
        ServerController serverController = new ServerController();
        serverController.start();



//        List<Chat> chats = new ArrayList<>();
//        Chat chat = new Chat();
//
//        chats.add(chat);
//        List<Massage> massages = new ArrayList<>();
//        chat.setMassages(massages);
//        Massage massage = new Massage("tested client","john","hi john");
//        massage.setChat(chat);
//        massages.add(massage);
//
//
//        Client client = new Client("tested client","123",null);
//        client.setChats(chats);
//
//        chat.setOpponentUsername("john");
//// Set the bidirectional relationship
//        chat.setClient(client);
//
//
//        Saver.getSaver().saveClient(client);



//        ChatDAO chatDAO = new ChatDAO();
//        // Fetch chats for Marry
//        String marryUsername = "marry";
//        List<Chat> marryChats = chatDAO.getChatsForClient(marryUsername);
//            // Fetch chats for John
//        System.out.println(marryChats.size());
//        for (Chat chat : marryChats) {
//            for (Massage massage : chat.getMassages()) {
//                System.out.println(massage.getContext());
//            }
//        }
//        String johnUsername = "john";
//        List<Chat> johnChats = chatDAO.getChatsForClient(johnUsername);
//
//            // Process and use the retrieved chats as needed


    }
}

