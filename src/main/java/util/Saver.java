package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import controller.ClientController;
import model.main_model.Client;
import model.main_model.chat.Chat;
import model.main_model.chat.Massage;
import model.response.SignInLoginResponse;
import org.hibernate.Session;

import java.io.*;

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
    public boolean saveClient(Client client) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            session.save(client);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    //todo : fix it
    public boolean updateClient(Client client) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            session.update(client);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean addMassageToChat(Chat chat, Massage massage) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            session.save(massage);
            session.update(chat);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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
            saveClient(fish);
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
