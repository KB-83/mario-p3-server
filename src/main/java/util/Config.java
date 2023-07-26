package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.main_model.Client;
import model.main_model.gamestrucure.Game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Config extends Properties {
    private static final String CONFIGS_ADDRESS = "src/main/resources/config/address.properties";
    private static final Config DEFAULT_CONFIG = new Config(CONFIGS_ADDRESS);
    public final static HashMap<String, Long> CONSTANT = new HashMap<>();
    public final static HashMap<String, Client> CLIENTS = new HashMap<>();
    public final static HashMap<String, Client> ONLINE_CLIENTS = new HashMap<>();
    public final static ArrayList<Game> ONLINE_GAMES = new ArrayList<>();
    public static ObjectMapper objectMapper = new ObjectMapper();

    public static Config getConfig(String name) {
        if ("mainConfig".equals(name))
            return DEFAULT_CONFIG;
        return new Config(DEFAULT_CONFIG.getProperty(name));
    }
    //singleton

    private Config(String address) {
        super();
        try {
            Reader fileReader = new FileReader(address);
            this.load(fileReader);
        } catch (IOException e) {
            System.err.println(address);
            e.printStackTrace();
        }
    }
    public int getPropertyAsInt(String name){
        return Integer.parseInt(super.getProperty(name));
    }
    public boolean getPropertyAsBoolean(String name) {
        return Boolean.parseBoolean(super.getProperty(name));
    }
    public static void loadConfigs() {
        loadConstants("constants");
        loadClients("clients");
    }
    private  static void loadConstants(String name) {
        Config config = getConfig(name);
        for (String key : config.stringPropertyNames()) {
            CONSTANT.put(key, Long.valueOf(config.getProperty(key)));
        }
        ONLINE_GAMES.add(Loader.getLoader().loadGame("Marathon"));
    }
    private  static void loadClients(String name) {
        Config config = getConfig(name);

        for (String key : config.stringPropertyNames()) {
            String clientPath = config.getProperty(key);
            try {
                Reader fileReader = new FileReader("src/main/resources/user"+clientPath);
                Client client = objectMapper.readValue(fileReader,Client.class);
                CLIENTS.put(key, client);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

//        return imageMap;
    }

}
