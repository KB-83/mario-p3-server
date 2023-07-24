package controller.gamelogic.gravitylogic;

import model.main_model.Client;
import model.main_model.entity.player.Player;
import model.main_model.gamestrucure.GameState;
import model.main_model.gamestrucure.gameworldoption.Gravity;
import util.Constant;

import java.util.ArrayList;
import java.util.List;

public class GravityEffectsHandler {
//    private GameState gameState;
//    private Player player;
    private ArrayList<Client> clients;

    public GravityEffectsHandler(GameState gameState, ArrayList<Client> clients) {
        this.clients = clients;
//        this.gameState = gameState;
    }
    public void applyEffects() {
        for (Client client : clients) {
            Player player = client.getPlayer();
        if (player.isDuringJump() == false && player.getOnTopOfBlock() == false) {
            player.setVY(player.getVY() + (-Gravity.MARIO_GAME * 1 / Constant.FPS));
        }

        }
    }
}
