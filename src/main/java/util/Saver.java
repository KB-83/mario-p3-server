package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import controller.ClientController;
import model.main_model.Client;
import model.response.SignInLoginResponse;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Saver {
    private static Saver saver;
    private ObjectMapper objectMapper = new ObjectMapper();
    private Saver(){
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }
    public static Saver getSaver() {
        if (saver == null) {
            saver = new Saver();
        }return saver;
    }
    public boolean saveUser(Client client) {
        File file = new File("src/main/resources/user/"+client.getUsername()+".json");
        try {
            FileWriter fileWriter = new FileWriter(file);
            objectMapper.writeValue(fileWriter,client);
        } catch (IOException e) {
            System.out.println("json mapping for this user is not right.\nsource: Saver class saveUser method.");
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public Client signInUser(String password,String username,ClientController controller) {
        File file = new File("src/main/resources/user/"+username+".json");
        if (file.exists()) {
            controller.sendResponse(new SignInLoginResponse(null,false,"user already exist."));
            return null;
        }
        else {
            Client fish = new Client(username,password,controller);
            saveUser(fish);
            controller.sendResponse(new SignInLoginResponse(fish,true,""));
            return fish;
        }
    }

}
