package controller.gamelogic.gamestatelogic.survivallogic;

import controller.gamelogic.playerlogic.PlayerRequestHandler;
import model.main_model.entity.player.Player;
import model.main_model.gamestrucure.GameState;

public class PlayerGSurvivalRequestHandler extends PlayerRequestHandler {
    private Player player;
    public PlayerGSurvivalRequestHandler(GameState gameState, Player player) {
        super(gameState, player);
        this.player = player;
    }

    @Override
    public void update() {

    }

    @Override
    public void rightDoneRequest() {
        player.setVX(0);
    }

    @Override
    public void leftDoneRequest() {
        player.setVX(0);
    }
}
