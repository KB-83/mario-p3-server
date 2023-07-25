package controller.game;


import controller.gamelogic.gamestatelogic.GameStateController;
import controller.gamelogic.gamestatelogic.PlayerController;
import controller.mapper.DTOCreator;
import model.dto.entity.PlayerDTO;
import model.dto.game.GameStateDTO;
import model.main_model.Client;
import model.main_model.gamestrucure.Game;
import model.main_model.gamestrucure.GameState;
import model.response.GameStartResponse;
import util.Config;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GameWaitingRoom {
    private  ArrayList<Client> marathonClients = new ArrayList<>();
    private  Timer marathonTimer;
    private final ArrayList<Client> groupSurvivalClients = new ArrayList<>();
    private final ArrayList<Client> survivalClients = new ArrayList<>();
    private static GameWaitingRoom gameWaitingRoom;
    private List<Marathon> marathons;

    private GameWaitingRoom() {
        setMarathonTimer();
    }
    public static GameWaitingRoom getInstance() {
        if (gameWaitingRoom == null) {
            gameWaitingRoom = new GameWaitingRoom();
        }
        return gameWaitingRoom;
    }

    public void marathonClient(Client client) {
        if (marathonClients.size() < 8) {
            marathonClients.add(client);
        }
        else {
            //todo : do it better it is just a test
            Game game = Config.ONLINE_GAMES.get(0);
            GameState gameState = new GameStateController(marathonClients).createGameState(game);
            //todo : test too
            client.getPlayer().setPlayerController(new PlayerController(gameState, client.getPlayer()));
            Marathon marathon = new Marathon(marathonClients,gameState);
//            marathons.add(marathon);
            startAGame(marathonClients,DTOCreator.createGameStateDTO(gameState),gameState);
            marathon.start();
            //todo : clone it
            marathonClients = new ArrayList<>();
            marathonTimer.stop();
            // start game
        }
        if (marathonClients.size() == 1) {
            marathonTimer.start();
        }
    }
    public void setMarathonTimer() {
        marathonTimer = new Timer(6000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(marathonClients.size() >= 2) {
                    //start game
                }
                else if (marathonClients.size() == 1) {
                    //send no online user to the client
                }
                Game game = Config.ONLINE_GAMES.get(0);
                GameState gameState = new GameStateController(marathonClients).createGameState(game);
                Marathon marathon = new Marathon(marathonClients,gameState);
//                marathons.add(marathon);
                startAGame(marathonClients, DTOCreator.createGameStateDTO(gameState),gameState);
                marathon.start();
                //todo : clone it
                marathonClients = new ArrayList<>();
                marathonTimer.stop();
            }
        });
    }
    public void startAGame(ArrayList<Client> clients, GameStateDTO gameStateDTO,GameState gameState) {
        for (Client client : clients) {
            client.getPlayer().setPlayerController(new PlayerController(gameState, client.getPlayer()));
            client.setCurrentGameStateDTO(gameStateDTO);
            client.setCurrentGameState(gameState);
            PlayerDTO playerDTO = DTOCreator.createPlayerDTO(client.getPlayer());
            client.setPlayerDTO(playerDTO);
            client.getClientController().sendResponse(new GameStartResponse(gameStateDTO,playerDTO));
        }
    }
}
