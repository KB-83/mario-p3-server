
import controller.ServerController;
import controller.game.Marathon;
import util.Config;


public class Main {
    public static void main(String[] args) {

        Config.loadConfigs();
        ServerController serverController = new ServerController();
        serverController.start();

    }
}
