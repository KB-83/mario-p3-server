package util;

import model.main_model.gamestrucure.Game;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

public class Config extends Properties {
    private static final String CONFIGS_ADDRESS = "src/main/resources/config/address.properties";
    private static final Config DEFAULT_CONFIG = new Config(CONFIGS_ADDRESS);
    public final static HashMap<String, Long> CONSTANT = new HashMap<>();
    public final static ArrayList<Game> ONLINE_GAMES = new ArrayList<>();

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
    }
    private  static void loadConstants(String name) {
        Config config = getConfig(name);
        for (String key : config.stringPropertyNames()) {
            CONSTANT.put(key, Long.valueOf(config.getProperty(key)));
        }
        ONLINE_GAMES.add(Loader.getLoader().loadGame("Marathon"));
    }

    // ...
}
