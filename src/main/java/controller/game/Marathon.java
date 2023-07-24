package controller.game;

import controller.ClientController;
import controller.gamelogic.gamestatelogic.GameStateController;
import model.main_model.Client;
import model.main_model.gamestrucure.GameState;
import util.Loop;

import java.util.ArrayList;
import java.util.List;

public class Marathon extends GameStateController{

    private GameState gameState;
    private Loop gameloop;

    public Marathon(ArrayList<Client> clients, GameState gameState) {
        super(clients);
        setGameState(gameState);
        gameloop =  new Loop(this,60);
        gameloop.start();
    }
    private void checkIfEnd() {
        if(gameState.getRemainingTime() <= 0) {
            //dude
        }
    }

    @Override
    public void run() {
//        loop;
    }
    public void update() {
        super.update();
        for (Client client:getClients()) {
            client.getPlayer().getPlayerController().update();
        }
    }
}
