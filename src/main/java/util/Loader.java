package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import controller.ClientController;
import model.main_model.Client;
import model.main_model.gamestrucure.Game;
import model.response.SignInLoginResponse;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.File;
import java.io.IOException;

public class Loader {
    private static Loader loader;
    private ObjectMapper objectMapper = new ObjectMapper();
    private Loader(){
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }
    public static Loader getLoader() {
        if (loader == null) {
            loader = new Loader();
        }return loader;
    }
//    public Client loadClient (String password,String userName, ClientController clientController) {
//        File file = new File("src/main/resources/user/"+userName+".json");
//        Client client = null;
//        if (!file.exists()) {
//            clientController.sendResponse(new SignInLoginResponse(client,false,"user dos not exist."));
//            return null;
//        }
//        try {
//            client = objectMapper.readValue(file, Client.class);
////            System.out.println("loader"  + user.getSavedGames()[0].getMario().getImageAddress());
//            } catch (IOException e) {
//            System.out.println("json mapping for this user is not right.\nsource: Loader class loadUser method.");
//            e.printStackTrace();
//        }
//        if (password.equals(client.getPassword())){
//           clientController.sendResponse(new SignInLoginResponse(client,true,""));
//        return client;}
//        else {
//            clientController.sendResponse(new SignInLoginResponse(client,false,"password is wrong"));
//            return null;
//        }
//    }
    public Client loadClient(String username, String password, ClientController clientController) {
    try (Session session = HibernateUtil.getSession()) {
        String hql = "FROM Client c LEFT JOIN FETCH c.chats WHERE c.username = :username";
        Query<Client> query = session.createQuery(hql, Client.class);
        query.setParameter("username", username);
        Client client = query.uniqueResult();

        if (client == null) {
            clientController.sendResponse(new SignInLoginResponse(null, false, "User does not exist."));
            return null;
        }

        if (password.equals(client.getPassword())) {
            if (clientController != null) {//test
                clientController.sendResponse(new SignInLoginResponse(client, true, ""));
            }
            return client;
        } else {
            clientController.sendResponse(new SignInLoginResponse(client, false, "Password is wrong."));
            return null;
        }
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}


    public Game loadGame(String name) {
        File file = new File("src/main/resources/game/"+name+".json");
        Game game = null;
        try {
            game = objectMapper.readValue(file, Game.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return game;
    }
}
