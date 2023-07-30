package controller.game;


import controller.gamelogic.gamestatelogic.GameStateController;
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

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GameWaitingRoom {
    private  ArrayList<Client> marathonClients = new ArrayList<>();
    private  Timer marathonTimer;
    private  Timer survivalTimer;
    private final ArrayList<Client> groupSurvivalClients = new ArrayList<>();
    private ArrayList<Client> survivalClients = new ArrayList<>();
    private static GameWaitingRoom gameWaitingRoom;
    private List<Marathon> marathons;
    private List<Survival> survivals;

    private GameWaitingRoom() {
        setMarathonTimer();
        setSurvivalTimer();
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
            Marathon marathon = new Marathon(marathonClients);
            GameState gameState = marathon.createGameState(game);
            //todo : test too
            client.getPlayer().setPlayerController(new PlayerController(gameState, client.getPlayer(),"marathon"));

            marathons.add(marathon);
            startAGame(marathonClients,DTOCreator.createGameStateDTO(gameState),gameState,"marathon");
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
    public void survivalClient(Client client) {
        if (survivalClients.size() < 8) {
            survivalClients.add(client);
        }
        else {
            //todo : do it better it is just a test
            Game game = Config.ONLINE_GAMES.get(0);
            Survival survival = new Survival(survivalClients);
            GameState gameState = survival.createGameState(game);
            //todo : test too
            client.getPlayer().setPlayerController(new PlayerController(gameState, client.getPlayer(),"survival"));

            survivals.add(survival);
            startAGame(survivalClients,DTOCreator.createGameStateDTO(gameState),gameState,"survival");
            survival.start();
            //todo : clone it
            survivalClients = new ArrayList<>();
            survivalTimer.stop();
            // start game
        }
        if (survivalClients.size() == 1) {
            survivalTimer.start();
        }
    }

    public void setMarathonTimer() {
        marathonTimer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(marathonClients.size() >= 1) {
                    //start game
                    Game game = Config.ONLINE_GAMES.get("marathon");//todo : it can be a hash map by game names.
                    Marathon marathon = new Marathon(marathonClients);
                    GameState gameState = marathon.createGameState(game);

//                marathons.add(marathon);
                    startAGame(marathonClients, DTOCreator.createGameStateDTO(gameState),gameState,"marathon");
                    marathon.startGameState(gameState);
                    //todo : clone it
                    marathonClients = new ArrayList<>();
                }
                else if (marathonClients.size() == 1) {
                    //send no online user to the client
                }
                marathonTimer.stop();
            }
        });
    }
    public void setSurvivalTimer() {
        survivalTimer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(survivalClients.size() >= 1) {
                    //start game
                    Game game = Config.ONLINE_GAMES.get("survival");//todo : it can be a hash map by game names.
                    Survival survival = new Survival(survivalClients);
                    GameState gameState = survival.createGameState(game);

//                marathons.add(marathon);
                    startAGame(survivalClients, DTOCreator.createGameStateDTO(gameState),gameState,"survival");
                    survival.startGameState(gameState);
                    //todo : clone it
                    survivalClients = new ArrayList<>();
                }
                else if (survivalClients.size() == 1) {
                    //send no online user to the client
                }
                survivalTimer.stop();
            }
        });
    }

    public void startAGame(ArrayList<Client> clients, GameStateDTO gameStateDTO,GameState gameState,String gameStateType) {
        for (Client client : clients) {
            client.setPlayer(new Mario(client.getUsername()));
            client.getPlayer().setPlayerController(new PlayerController(gameState, client.getPlayer(),gameStateType));
            client.setCurrentGameStateDTO(gameStateDTO);
            client.setCurrentGameState(gameState);
            PlayerDTO playerDTO = DTOCreator.createPlayerDTO(client.getPlayer());
            client.setPlayerDTO(playerDTO);
            client.getClientController().sendResponse(new GameStartResponse(gameStateDTO,playerDTO));
        }
    }
}
