package controller.game;

import controller.gamelogic.gamestatelogic.GameStateController;
import controller.gamelogic.gamestatelogic.groupgamelogic.PlayerGroupGameRequestHandler;
import model.main_model.Client;
import model.response.GameOverResponse;
import util.Saver;

import java.util.ArrayList;

public class GroupGame extends GameStateController {
    public GroupGame(ArrayList<Client> clients) {
        super(clients);
    }
    public void update() {
        super.update();
        boolean isOver = true;
        for (Client client:getClients()) {
            if (client.getPlayer().getPlayerController() != null) { //todo : think about it its going to improve now is just giving time to thread to make controller not null. game waiting room line 86
                PlayerGroupGameRequestHandler requestHandler = (PlayerGroupGameRequestHandler)
                        client.getPlayer().getPlayerController().getPlayerRequestHandler();
                if (client.getPlayer().getPlayerController().isLoosed() == false) {

                    if (!requestHandler.isDead() && !requestHandler.isOver()){
                        isOver = false;
                    }
                    client.getPlayer().getPlayerController().update();
                    if (client.getPlayer().getActivePowerItem() != null) {
                        client.getPlayer().getActivePowerItem().getController().update();
                    }
                }
            }
        }
        if (getGameState().getCurrentSection().getRemainingTime() <= 0 || isOver) {
            //todo : bug player client az aval shro nemikone
            endOfGame();
        }
    }
    public void endOfGame() {
        for (Client  client : getClients()) {
            client.getClientController().sendResponse(new GameOverResponse(
                    0,0,"game is over"));// game over response
            client.getClientController().cleanClientFromGame();
        }
        getLoop().kill();

    }
}
