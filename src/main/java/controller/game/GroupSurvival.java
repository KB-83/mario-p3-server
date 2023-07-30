package controller.game;

import controller.gamelogic.gamestatelogic.GameStateController;
import model.main_model.Client;
import model.main_model.gamestrucure.GameState;

import java.util.ArrayList;
import java.util.List;

public class GroupSurvival extends GameStateController {
    private GameState gameState;
    private ArrayList<Client> group1;
    private ArrayList<Client> group2;

    public GroupSurvival(ArrayList<Client> clients) {
        super(clients);
    }
}
