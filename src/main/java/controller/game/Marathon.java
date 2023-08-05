package controller.game;

import controller.gamelogic.gamestatelogic.GameStateController;
import model.main_model.Client;
import model.main_model.gamestrucure.GameState;
import model.response.GameOverResponse;
import util.Config;
import util.Constant;
import util.Saver;

import java.util.ArrayList;

public class Marathon extends GameStateController{

    private GameState gameState;
    public static double multiplierSpeed = Config.CONSTANT.get("Marathon.multiplierSpeed");
    public static double multiplierSlowDown = Config.CONSTANT.get("Marathon.multiplierSlowDown");
    public static int periodSlowDown = (int) (Config.CONSTANT.get("Marathon.periodSlowDown").doubleValue()) ;
    public static double lifeTimeMultiplier = Config.CONSTANT.get("Marathon.lifeTimeMultiplier");
    public static double distanceMultiplier = Config.CONSTANT.get("Marathon.distanceMultiplier");
    public static int minLifeTime = (int) (Config.CONSTANT.get("Marathon.minLifeTime").doubleValue()) ;
    public static int minDistance = (int) (Config.CONSTANT.get("Marathon.minDistance").doubleValue()) ;

    public Marathon(ArrayList<Client> clients) {
        super(clients);
    }
    private void checkIfEnd() {
        if(gameState.getRemainingTime() <= 0) {
            //dude
        }
    }

//    @Override
//    public void run() {
////        loop;
//        System.out.println(40);
////        gameloop =  new Loop(this,60);
////        gameloop.start();
//    }
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
        if (getGameState().getCurrentSection().getRemainingTime() <= 0) {
            //todo : bug player client az aval shro nemikone
            endOfGame();
        }
    }
    private void endOfGame() {
        Score[] scores = sortClientsByScore();
        scores = addDiamond(scores);
        for (Score score : scores) {
            Client client = score.getClient();
            client.setScore(client.getScore()+score.getScore());
            client.setDiamond(client.getDiamond()+score.getDiamond());
            Saver.getSaver().updateClient(client);
            if (client.getClientController().isOnline()) {
                client.getClientController().sendResponse(new GameOverResponse(
                        score.getScore(),score.getDiamond(),"marathon is over"));// game over response
                client.getClientController().cleanClientFromGame();
            }
        }
        getLoop().kill();

    }
    private Score[] sortClientsByScore() {
        Score[] sortedScores = new Score[getClients().size()];
        for (int i = 0; i < sortedScores.length ; i++) {
            sortedScores[i] = new Score(getClients().get(i),score(getClients().get(i)));

        }

        for (int i = 0; i < sortedScores.length - 1; i++) {
            for (int j = 0; j < sortedScores.length- i - 1; j++) {
                if (sortedScores[j].getScore() < sortedScores[j+1].getScore()) {
                    Score temp = sortedScores[j];
                    sortedScores[j] = sortedScores[j+1];
                    sortedScores[j+1] = temp;
                }
            }
        }
        return sortedScores;
    }
    private int score(Client client) {
        double distanceFromStart = client.getPlayer().getWorldX() / Constant.BACKGROUND_TILE_SIZE;
        // age mord sabt mishe
        double lifeTime = 0;
        return  (int) (Math.max(lifeTime, minLifeTime) * lifeTimeMultiplier +
                        Math.max(distanceFromStart, minDistance) * distanceMultiplier);
    }
    private Score[] addDiamond(Score[] scores) {
        int n = getClients().size();
        for (int i = 0; i < scores.length; i++) {
            scores[i].setDiamond((int) Math.floor(n/Math.pow(2,i+1)));
        }
        return scores;
    }
}
class Score {
    private Client client;
    private int score;
    private int diamond;

    public Score(Client client, int score) {
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
