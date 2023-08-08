package controller;

import controller.game.GroupGame;
import controller.game.GroupSurvival;
import controller.game.Marathon;
import controller.game.Survival;
import controller.gamelogic.gamestatelogic.PlayerController;
import controller.mapper.DTOCreator;
import model.dto.entity.PlayerDTO;
import model.dto.game.GameStateDTO;
import model.main_model.Client;
import model.main_model.entity.player.Mario;
import model.main_model.gamestrucure.Game;
import model.main_model.gamestrucure.GameState;
import model.response.GameStartResponse;
import util.Config;

import java.awt.*;
import java.util.ArrayList;

public class GameStateManagerCreator {
    public static Marathon createMarathon(ArrayList<Client> marathonClients) {

        Game game = Config.ONLINE_GAMES.get("marathon");//todo : it can be a hash map by game names.
        Marathon marathon = new Marathon(marathonClients);
        GameState gameState = marathon.createGameState(game);
        startAGame(marathonClients, DTOCreator.createGameStateDTO(gameState),gameState,"marathon");
        return marathon;
    }
    public static Survival createSurvival(ArrayList<Client> survivalClients) {
        Game game = Config.ONLINE_GAMES.get("survival");//todo : it can be a hash map by game names.
        Survival survival = new Survival(survivalClients);
        GameState gameState = survival.createGameState(game);
        startAGame(survivalClients, DTOCreator.createGameStateDTO(gameState),gameState,"survival");
        return survival;
    }
    public static GroupGame createGroupGame(ArrayList<Client> groupGameClients) {
        Game game = Config.ONLINE_GAMES.get("group_game");//todo : it can be a hash map by game names.
        GroupGame groupGame = new GroupGame(groupGameClients);
        GameState gameState = groupGame.createGameState(game);
        startAGame(groupGameClients, DTOCreator.createGameStateDTO(gameState),gameState,"groupGame");
        return groupGame;
    }
    public static GroupSurvival createGroupSurvival(ArrayList<Client> groupSurvivalClients) {
        int num = groupSurvivalClients.size()/2;
        ArrayList<Client> team1 = new ArrayList<>();
        ArrayList<Client> team2 = new ArrayList<>();
        for (int i = 0;i<num;i++){
            team1.add(groupSurvivalClients.get(2 * i));
            team2.add(groupSurvivalClients.get((2 * i) + 1 ));
        }
        //todo : reject extra
        if (groupSurvivalClients.size() % 2 == 1) {
            groupSurvivalClients.remove(groupSurvivalClients.size() - 1);
        }
        Game game = Config.ONLINE_GAMES.get("group_survival");
        GroupSurvival groupSurvival = new GroupSurvival(groupSurvivalClients,team1,team2);
        GameState gameState = groupSurvival.createGameState(game);

        startAGame(groupSurvivalClients, DTOCreator.createGameStateDTO(gameState),gameState,"groupSurvival");
        return groupSurvival;
    }
    private static void startAGame(ArrayList<Client> clients, GameStateDTO gameStateDTO, GameState gameState, String gameStateType) {
        for (Client client : clients) {
            client.setPlayer(new Mario(client.getUsername(),client.getSelectedBag()));
            switch (gameState.getMarioStartState()) {
                case 1 :
                    client.getPlayer().setMega(true);
                    break;
                case 2 :
                    client.getPlayer().setFire(true);
                    break;
            }
            client.getPlayer().setPlayerController(new PlayerController(client,gameState, client.getPlayer(),gameStateType));
            client.setCurrentGameStateDTO(gameStateDTO);
            client.setCurrentGameState(gameState);
            PlayerDTO playerDTO = DTOCreator.createPlayerDTO(client.getPlayer());
            client.setPlayerDTO(playerDTO);
            client.getClientController().sendResponse(new GameStartResponse(gameStateDTO,playerDTO));
        }
        if (gameStateType.equals("groupSurvival")) {
            setGSurvivalColors(((GroupSurvival)gameState.getGameStateController()).getTeam1().getClients(),
                    ((GroupSurvival)gameState.getGameStateController()).getTeam2().getClients());
        }

    }
    private static void setGSurvivalColors(ArrayList<Client> team1,ArrayList<Client> team2) {
        for (int i = 0;i<team1.size();i++) {
            team1.get(i).getPlayer().setTeamColor(Color.RED.getRGB());
            team2.get(i).getPlayer().setTeamColor(Color.GREEN.getRGB());

        }
    }
}
