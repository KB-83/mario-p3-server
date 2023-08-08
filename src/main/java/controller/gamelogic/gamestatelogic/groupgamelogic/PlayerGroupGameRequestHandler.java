package controller.gamelogic.gamestatelogic.groupgamelogic;

import controller.game.GroupGame;
import controller.gamelogic.playerlogic.PlayerRequestHandler;
import model.main_model.Client;
import model.main_model.entity.player.Player;
import model.main_model.gamestrucure.GameState;

public class PlayerGroupGameRequestHandler extends PlayerRequestHandler {
    private GameState gameState;
    private Player player;
    private boolean isDead;
    private boolean isOver;
    public PlayerGroupGameRequestHandler(GameState gameState, Player player, Client client) {
        super(gameState, player, client);
        this.gameState = gameState;
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
    @Override
    public void nextSection() {

        super.nextSection();
    }

    @Override
    public void changeLevel() {
        super.changeLevel();
        if (!(gameState.getLevelNumber() < gameState.getLevels().length)){
            isOver = true;
        }
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public boolean isOver() {
        return isOver;
    }

    public void setOver(boolean over) {
        isOver = over;
    }
}
