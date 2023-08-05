
import controller.ServerController;
import model.main_model.score_board.ScoreBoard;
import util.Config;


public class Main {
    public static void main(String[] args) {
        Config.loadConfigs();
        ServerController serverController = new ServerController();
        serverController.start();


//        for (int i = 0; i < 3;i++) {
//            System.out.println(ScoreBoard.getPlayersByScore().get(i).getUsername()+": "+ ScoreBoard.getPlayersByScore().get(i).getScore());
////        }
//        System.out.println(ScoreBoard.getPlayersByScoreRange(100,200).get(0).getScore());
//        System.out.println(ScoreBoard.searchPlayersByUsername("oh").get(0).getUsername());


    }
}

