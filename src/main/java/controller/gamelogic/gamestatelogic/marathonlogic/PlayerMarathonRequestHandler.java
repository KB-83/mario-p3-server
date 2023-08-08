package controller.gamelogic.gamestatelogic.marathonlogic;

import controller.gamelogic.playerlogic.PlayerRequestHandler;
import model.main_model.Client;
import model.main_model.entity.player.Player;
import model.main_model.gamestrucure.GameState;
import model.main_model.levelstructure.Section;

public class PlayerMarathonRequestHandler extends PlayerRequestHandler {
    private char direction = 'r';//r l
    public PlayerMarathonRequestHandler(GameState gameState, Player player, Client client) {
        super(gameState, player,client);
        setCounterMax(12);
    }

    @Override
    public void update() {
        if (direction == 'r') {
            super.rightRequest();
        }
        else {
            super.leftRequest();
        }
    }

    @Override
    public void rightDoneRequest() {
//        super.rightRequest();
    }

    @Override
    public void leftDoneRequest() {
//        super.leftRequest();
    }
    public void rightRequest() {
        super.rightRequest();
        direction = 'r';
    }
    public void leftRequest() {
        super.leftRequest();
        direction = 'l';
    }


    @Override
    public void nextSection() {
    }
}
