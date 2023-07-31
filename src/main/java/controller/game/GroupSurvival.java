package controller.game;

import controller.gamelogic.gamestatelogic.GameStateController;
import model.main_model.Client;
import model.main_model.gamestrucure.Game;
import model.main_model.gamestrucure.GameState;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GroupSurvival extends GameStateController {
    private GameState gameState;
    private Team team1;
    private Team team2;//todo : maybe im going to improve camera system in client code

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
                }
            }
        }
        if (getGameState().getCurrentSection().getRemainingTime() <= 0) {
            //todo : bug player client az aval shro nemikone
//            endOfGame();
        }
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
}
class Team{
    private int groupNumber;
    private ArrayList<Client> clients;
    private int numOfPotion;
    private int numOfThrowable;

    public Team(int groupNumber, ArrayList<Client> clients) {
        this.groupNumber = groupNumber;
        this.clients = clients;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
    }

    public int getNumOfPotion() {
        return numOfPotion;
    }

    public void setNumOfPotion(int numOfPotion) {
        this.numOfPotion = numOfPotion;
    }

    public int getNumOfThrowable() {
        return numOfThrowable;
    }

    public void setNumOfThrowable(int numOfThrowable) {
        this.numOfThrowable = numOfThrowable;
    }
}
