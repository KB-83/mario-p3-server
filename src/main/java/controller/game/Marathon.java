package controller.game;

import controller.ClientController;
import controller.gamelogic.gamestatelogic.GameStateController;
import model.main_model.Client;
import model.main_model.gamestrucure.GameState;
import util.Config;
import util.Loop;

import java.util.ArrayList;
import java.util.List;

public class Marathon extends GameStateController{

    private GameState gameState;
    private Loop gameloop;
    public static double multiplierSpeed = Config.CONSTANT.get("Marathon.multiplierSpeed");
    public static double multiplierSlowDown = Config.CONSTANT.get("Marathon.multiplierSlowDown");
    public static int periodSlowDown = (int) (Config.CONSTANT.get("Marathon.periodSlowDown").doubleValue()) ;
    public static double lifeTimeMultiplier = Config.CONSTANT.get("Marathon.lifeTimeMultiplier");
    public static double distanceMultiplier = Config.CONSTANT.get("Marathon.distanceMultiplier");
    public static int minLifeTime = (int) (Config.CONSTANT.get("Marathon.minLifeTime").doubleValue()) ;
    public static int minDistance = (int) (Config.CONSTANT.get("Marathon.minDistance").doubleValue()) ;

    public Marathon(ArrayList<Client> clients) {
        super(clients);
    }
    private void checkIfEnd() {
        if(gameState.getRemainingTime() <= 0) {
            //dude
        }
    }

//    @Override
//    public void run() {
////        loop;
//        System.out.println(40);
////        gameloop =  new Loop(this,60);
////        gameloop.start();
//    }
    public void update() {
        super.update();
        for (Client client:getClients()) {
            if (client.getPlayer().getPlayerController() != null) { //todo : think about it its going to improve now is just giving time to thread to make controller not null. game waiting room line 86
                client.getPlayer().getPlayerController().update();
            }
        }
    }
}
