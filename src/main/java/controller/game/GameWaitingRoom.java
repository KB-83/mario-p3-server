package controller.game;


import controller.gamelogic.gamestatelogic.GameStateController;
import controller.mapper.DTOCreator;
import model.dto.entity.player.PlayerDTO;
import model.dto.game.GameStateDTO;
import model.main_model.Client;
import model.main_model.gamestrucure.Game;
import model.main_model.gamestrucure.GameState;
import model.response.GameStartResponse;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GameWaitingRoom {
    private final ArrayList<Client> marathonClients = new ArrayList<>();
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
            Game game = client.getGames()[0];
            GameState gameState = new GameStateController().createGameState(game);
            Marathon marathon = new Marathon(marathonClients,gameState);
//            marathons.add(marathon);
            startAGame(marathonClients,DTOCreator.createGameStateDTO(gameState),gameState);
            marathon.start();
            marathonClients.clear();
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
                Game game = marathonClients.get(0).getGames()[0];
                GameState gameState = new GameStateController().createGameState(game);
                Marathon marathon = new Marathon(marathonClients,gameState);
//                marathons.add(marathon);
                startAGame(marathonClients, DTOCreator.createGameStateDTO(gameState),gameState);
                marathon.start();
                marathonClients.clear();
                marathonTimer.stop();
            }
        });
    }
    public void startAGame(ArrayList<Client> clients, GameStateDTO gameStateDTO,GameState gameState) {
        for (Client client : clients) {
            client.setCurrentGameStateDTO(gameStateDTO);
            client.setCurrentGameState(gameState);
            PlayerDTO playerDTO = DTOCreator.createPlayerDTO(client.getPlayer());
            client.setPlayerDTO(playerDTO);
            client.getClientController().sendResponse(new GameStartResponse(gameStateDTO,playerDTO));
        }
    }
}
