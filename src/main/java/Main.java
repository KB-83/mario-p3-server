import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import controller.ServerController;
import model.main_model.Client;
import util.Config;
import util.Constant;
import util.Loader;
import util.Saver;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Config.loadConfigs();
        ServerController serverController = new ServerController();
        serverController.start();

    }
}
