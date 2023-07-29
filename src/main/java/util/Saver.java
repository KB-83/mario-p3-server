package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import controller.ClientController;
import model.main_model.Client;
import model.response.SignInLoginResponse;

import java.io.*;
import java.util.Properties;

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
            addNewClientToProperties(fish);
            saveUser(fish);
            Config.loadConfigs();
            controller.sendResponse(new SignInLoginResponse(fish,true,""));
            return fish;
        }
    }
    private void addNewClientToProperties(Client client){
        File propertiesFile = new File("src/main/resources/config/clients.properties");
        Config properties = Config.getConfig("clients");

        try {

            FileInputStream inputStream = new FileInputStream(propertiesFile);
            properties.load(inputStream);
            inputStream.close();

            properties.setProperty(client.getUsername(), "/" + client.getUsername() + ".json");

            FileOutputStream outputStream = new FileOutputStream(propertiesFile);
            properties.store(outputStream,null);
            outputStream.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
