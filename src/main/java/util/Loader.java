package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import controller.ClientController;
import model.main_model.Client;
import model.response.SignInLoginResponse;

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
    public Client loadClient (String userName, ClientController clientController) {
        File file = new File("src/main/resources/user/"+userName+".json");
        Client client = null;
        if (!file.exists()) {
            clientController.sendResponse(new SignInLoginResponse(false,"user dos not exist."));
            return null;
        }
        try {
            client = objectMapper.readValue(file, Client.class);
//            System.out.println("loader"  + user.getSavedGames()[0].getMario().getImageAddress());
            } catch (IOException e) {
            System.out.println("json mapping for this user is not right.\nsource: Loader class loadUser method.");
            e.printStackTrace();
        }
           clientController.sendResponse(new SignInLoginResponse(true,""));
        return client;
    }
//    public Game LoadGame() {
//        return null;
//    } this is for giving user a game
}
