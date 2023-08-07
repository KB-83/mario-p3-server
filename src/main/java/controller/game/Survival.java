package controller.game;

import controller.gamelogic.gamestatelogic.GameStateController;
import model.main_model.Client;
import model.main_model.entity.player.PlayerGameLog;
import model.main_model.gamestrucure.GameState;
import model.response.GameOverResponse;
import util.Config;
import util.Saver;

import java.util.ArrayList;

public class Survival extends GameStateController {
    private GameState gameState;

    public static double damageMultiplier = Config.CONSTANT.get("Survival.damageMultiplier");
    public static int equipmentMultiplier = (int) (Config.CONSTANT.get("Survival.equipmentMultiplier").doubleValue()) ;

    public Survival(ArrayList<Client> clients) {
        super(clients);

    }


    private void checkIfEnd() {
        int numOfDeadClients = 0;
        for (Client client : getClients()) {
            if (client.getPlayer().getPlayerGameLog().getRemainingLifePercent() <= 0) { // or player is dead boolean
                numOfDeadClients++;
            }
        }
        if (numOfDeadClients >= getClients().size() - 1) {
            endOfGame();
        }
    }

    public void update() {
        super.update();
        for (Client client:getClients()) {
            if (client.getPlayer().getPlayerController() != null) { //todo : think about it its going to improve now is just giving time to thread to make controller not null. game waiting room line 86
                if (client.getPlayer().getPlayerController().isLoosed() == false) {
                    client.getPlayer().getPlayerController().update();
                    if (client.getPlayer().getActivePowerItem() != null) {
                        client.getPlayer().getActivePowerItem().getController().update();
                    }
                }
            }
        }
        checkIfEnd();
    }
    private void endOfGame() {
        GroupSurvivalScore[] survivalScores = sortClientsByScore();
        survivalScores = addDiamond(survivalScores);
        for (GroupSurvivalScore survivalScore : survivalScores) {
            Client client = survivalScore.getClient();
            client.setScore(client.getScore()+ survivalScore.getScore());
            client.setDiamond(client.getDiamond()+ survivalScore.getDiamond());
            Saver.getSaver().updateClient(client);
            if (client.getClientController().isOnline()) {
                client.getClientController().sendResponse(new GameOverResponse(
                        survivalScore.getScore(), survivalScore.getDiamond(),"survival is over"));// game over response
                client.getClientController().cleanClientFromGame();
            }
        }
        getLoop().kill();
    }
    private GroupSurvivalScore[] sortClientsByScore() {
        GroupSurvivalScore[] sortedSurvivalScores = new GroupSurvivalScore[getClients().size()];
        for (int i = 0; i < sortedSurvivalScores.length ; i++) {
            sortedSurvivalScores[i] = new GroupSurvivalScore(getClients().get(i),score(getClients().get(i)));

        }

        for (int i = 0; i < sortedSurvivalScores.length - 1; i++) {
            for (int j = 0; j < sortedSurvivalScores.length- i - 1; j++) {
                if (sortedSurvivalScores[j].getScore() < sortedSurvivalScores[j+1].getScore()) {
                    GroupSurvivalScore temp = sortedSurvivalScores[j];
                    sortedSurvivalScores[j] = sortedSurvivalScores[j+1];
                    sortedSurvivalScores[j+1] = temp;
                }
            }
        }
        return sortedSurvivalScores;
    }
    private int score(Client client) {

        PlayerGameLog playerGameLog = client.getPlayer().getPlayerGameLog();
        return (int) ((playerGameLog.getTotalDamageDelta() - (100 - playerGameLog.getRemainingLifePercent())) * damageMultiplier - (playerGameLog.getPowerItems()) * equipmentMultiplier) ; // bayad dar execute kardan ha begim

    }
    private GroupSurvivalScore[] addDiamond(GroupSurvivalScore[] survivalScores) {
        double n = getClients().size();
        for (int i = 0; i < survivalScores.length; i++) {
            int delta = -1;
            if ((i+1)/n <= 1.0/4) {
                delta = 2;
            }
            else if ((i+1)/n <= 1.0/2){
                delta = 1;
            }
            else if ((i+1)/n <= 3.0/4){
                delta = 0;
            }
            survivalScores[i].setDiamond(delta);
        }
        return survivalScores;
    }
}
class SurvivalScore {
    private Client client;
    private int score;
    private int diamond;

    public SurvivalScore(Client client, int score) {
        this.client = client;
        this.score = score;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getDiamond() {
        return diamond;
    }

    public void setDiamond(int diamond) {
        this.diamond = diamond;
    }
}
