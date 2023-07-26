
import controller.ServerController;
import util.Config;


public class Main {
    public static void main(String[] args) {

        Config.loadConfigs();
        ServerController serverController = new ServerController();
        serverController.start();
    }
}
