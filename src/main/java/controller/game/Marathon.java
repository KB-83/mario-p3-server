package controller.game;

import controller.ClientController;
import controller.gamelogic.gamestatelogic.GameStateController;
import model.main_model.Client;
import model.main_model.gamestrucure.GameState;
import util.Loop;

import java.util.List;

public class Marathon extends GameStateController{
    private List<Client> clients;
    private GameState gameState;
    private Loop gameloop;

    public Marathon(List<Client> clients,GameState gameState) {
        setGameState(gameState);
        this.clients = clients;
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
}
