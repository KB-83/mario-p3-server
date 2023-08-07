package controller.game;

import controller.gamelogic.gamestatelogic.GameStateController;
import model.main_model.Client;
import model.main_model.entity.player.PlayerGameLog;
import model.main_model.gamestrucure.GameState;
import model.response.GameOverResponse;
import util.Config;
import util.Saver;

import java.util.ArrayList;

public class GroupSurvival extends GameStateController {
    private GameState gameState;
    private Team team1;
    private Team team2;//todo : maybe im going to improve camera system in client code

    public static double damageMultiplier = Config.CONSTANT.get("Survival.damageMultiplier");
    public static int equipmentMultiplier = (int) (Config.CONSTANT.get("Survival.equipmentMultiplier").doubleValue()) ;

    public GroupSurvival(ArrayList<Client> clients,ArrayList<Client> team1,ArrayList<Client> team2) {
        super(clients);
        this.team1 = new Team(1,team1);
        this.team2 = new Team(2,team2);
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
    private void checkIfEnd() {
        boolean team1 = false;
        boolean team2 = false;
        for (Client client : this.team1.getClients()) {
            if (client.getPlayer().getPlayerGameLog().getRemainingLifePercent() > 0) {
                team1 = true;
                break;
            }
        }
        for (Client client : this.team2.getClients()) {
            if (client.getPlayer().getPlayerGameLog().getRemainingLifePercent() > 0) { // or player is dead boolean
                team2 = true;
                break;
            }
        }
        if (!team1 || !team2) {
            endOfGame();
        }
    }
    private void endOfGame() {
        GroupSurvivalScore[] groupSurvivalScores = sortClientsByScore();
        groupSurvivalScores = addDiamond(groupSurvivalScores);
        for (GroupSurvivalScore groupSurvivalScore : groupSurvivalScores) {
            Client client = groupSurvivalScore.getClient();
            client.setScore(client.getScore()+ groupSurvivalScore.getScore());
            client.setDiamond(client.getDiamond()+ groupSurvivalScore.getDiamond());
            Saver.getSaver().updateClient(client);
            if (client.getClientController().isOnline()) {
                client.getClientController().sendResponse(new GameOverResponse(
                        groupSurvivalScore.getScore(), groupSurvivalScore.getDiamond(),"group survival is over"));// game over response
                client.getClientController().cleanClientFromGame();
            }
        }
        getLoop().kill();
    }

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }
    private GroupSurvivalScore[] sortClientsByScore() {
        GroupSurvivalScore[] sortedGroupSurvivalScores = new GroupSurvivalScore[getClients().size()];
        for (int i = 0; i < sortedGroupSurvivalScores.length ; i++) {
            sortedGroupSurvivalScores[i] = new GroupSurvivalScore(getClients().get(i),score(getClients().get(i)));

        }

        for (int i = 0; i < sortedGroupSurvivalScores.length - 1; i++) {
            for (int j = 0; j < sortedGroupSurvivalScores.length- i - 1; j++) {
                if (sortedGroupSurvivalScores[j].getScore() < sortedGroupSurvivalScores[j+1].getScore()) {
                    GroupSurvivalScore temp = sortedGroupSurvivalScores[j];
                    sortedGroupSurvivalScores[j] = sortedGroupSurvivalScores[j+1];
                    sortedGroupSurvivalScores[j+1] = temp;
                }
            }
        }
        return sortedGroupSurvivalScores;
    }
    private int score(Client client) {
        PlayerGameLog playerGameLog = client.getPlayer().getPlayerGameLog();
        return (int) ((playerGameLog.getTotalDamageDelta() - (100 - playerGameLog.getRemainingLifePercent())) * damageMultiplier - (playerGameLog.getPowerItems()) * equipmentMultiplier) ; // bayad dar execute kardan ha begim

    }
    private GroupSurvivalScore[] addDiamond(GroupSurvivalScore[] groupSurvivalScores) {
        double n = getClients().size();
        for (int i = 0; i < groupSurvivalScores.length; i++) {
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
            groupSurvivalScores[i].setDiamond(delta);
        }
        return groupSurvivalScores;
    }
}
class GroupSurvivalScore {
    private Client client;
    private int score;
    private int diamond;

    public GroupSurvivalScore(Client client, int score) {
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