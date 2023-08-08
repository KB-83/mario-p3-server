package controller.game;


import controller.GameStateManagerCreator;
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
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GameWaitingRoom {
    private ArrayList<Client> marathonClients = new ArrayList<>();
    private ArrayList<Client> groupSurvivalClients = new ArrayList<>();
    private ArrayList<Client> survivalClients = new ArrayList<>();
    private ArrayList<Client> groupGameClients = new ArrayList<>();
    private Timer marathonTimer;
    private Timer survivalTimer;
    private Timer groupSurvivalTimer;
    private Timer groupGameTimer;

    private static GameWaitingRoom gameWaitingRoom;
    private List<Marathon> marathons;
    private List<Survival> survivals;
    private List<GroupSurvival> groupSurvivals;

    private GameWaitingRoom() {
        setMarathonTimer();
        setSurvivalTimer();
        setGroupSurvivalTimer();
        setGroupGameTimer();
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
//            Game game = Config.ONLINE_GAMES.get("marathon");
//            Marathon marathon = new Marathon(marathonClients);
//            GameState gameState = marathon.createGameState(game);
//            //todo : test too
//            client.getPlayer().setPlayerController(new PlayerController(gameState, client.getPlayer(),"marathon"));
//
//            marathons.add(marathon);
//            startAGame(marathonClients,DTOCreator.createGameStateDTO(gameState),gameState,"marathon");
//            marathon.start();
//            //todo : clone it
//            marathonClients = new ArrayList<>();
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
//            Game game = Config.ONLINE_GAMES.get("survival");
//            Survival survival = new Survival(survivalClients);
//            GameState gameState = survival.createGameState(game);
//            //todo : test too
//            client.getPlayer().setPlayerController(new PlayerController(gameState, client.getPlayer(),"survival"));
//
//            survivals.add(survival);
//            startAGame(survivalClients,DTOCreator.createGameStateDTO(gameState),gameState,"survival");
//            survival.start();
//            //todo : clone it
//            survivalClients = new ArrayList<>();
//            survivalTimer.stop();
            // start game
        }
        if (survivalClients.size() == 1) {
            survivalTimer.start();
        }
    }
    public void groupSurvivalClient(Client client) {
        if (groupSurvivalClients.size() < 8) {
            groupSurvivalClients.add(client);
        }
        else {
            //todo : do it better it is just a test
//            Game game = Config.ONLINE_GAMES.get("survival");
//            int num = groupSurvivalClients.size() / 2;
//
//            ArrayList<Client> team1 = new ArrayList<>();
//            ArrayList<Client> team2 = new ArrayList<>();
//            for (int i = 0;i<num;i++){
//                team1.add(groupSurvivalClients.get(2 * i));
//                team2.add(groupSurvivalClients.get((2 * i) + 1 ));
//            }
//            //            todo : reject extra
//
//            if (groupSurvivalClients.size() % 2 == 1) {
//                groupSurvivalClients.remove(groupSurvivalClients.size() - 1);
//            }
//
//            GroupSurvival groupSurvival = new GroupSurvival(groupSurvivalClients,team1,team2);
//            GameState gameState = groupSurvival.createGameState(game);
//            //todo : test too
//            client.getPlayer().setPlayerController(new PlayerController(gameState, client.getPlayer(),"groupSurvival"));
//
//            groupSurvivals.add(groupSurvival);
//
//
//
//            startAGame(survivalClients,DTOCreator.createGameStateDTO(gameState),gameState,"groupSurvival");
//            groupSurvival.start();
//            //todo : clone it
//            groupSurvivalClients = new ArrayList<>();
//            groupSurvivalTimer.stop();
//            // start game
        }
        if (groupSurvivalClients.size() == 1) {
            groupSurvivalTimer.start();
        }
    }
    public void groupGameClient(Client client) {
        if (groupGameClients.size() < 8) {
            groupGameClients.add(client);
        }
        else {
            //todo : do it better it is just a test
//            Game game = Config.ONLINE_GAMES.get("survival");
//            int num = groupSurvivalClients.size() / 2;
//
//            ArrayList<Client> team1 = new ArrayList<>();
//            ArrayList<Client> team2 = new ArrayList<>();
//            for (int i = 0;i<num;i++){
//                team1.add(groupSurvivalClients.get(2 * i));
//                team2.add(groupSurvivalClients.get((2 * i) + 1 ));
//            }
//            //            todo : reject extra
//
//            if (groupSurvivalClients.size() % 2 == 1) {
//                groupSurvivalClients.remove(groupSurvivalClients.size() - 1);
//            }
//
//            GroupSurvival groupSurvival = new GroupSurvival(groupSurvivalClients,team1,team2);
//            GameState gameState = groupSurvival.createGameState(game);
//            //todo : test too
//            client.getPlayer().setPlayerController(new PlayerController(gameState, client.getPlayer(),"groupSurvival"));
//
//            groupSurvivals.add(groupSurvival);
//
//
//
//            startAGame(survivalClients,DTOCreator.createGameStateDTO(gameState),gameState,"groupSurvival");
//            groupSurvival.start();
//            //todo : clone it
//            groupSurvivalClients = new ArrayList<>();
//            groupSurvivalTimer.stop();
//            // start game
        }
        if (groupGameClients.size() == 1) {
            groupGameTimer.start();
        }
    }



    public void setMarathonTimer() {
        marathonTimer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(marathonClients.size() >= 1) {
                    //start game

                    Marathon marathon = GameStateManagerCreator.createMarathon(marathonClients);

                    marathon.startGameState();
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
                    Survival survival = GameStateManagerCreator.createSurvival(survivalClients);
                    survival.startGameState();
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
    public void setGroupSurvivalTimer() {
        groupSurvivalTimer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(groupSurvivalClients.size() >= 1) {
                    //start game
                    GroupSurvival groupSurvival = GameStateManagerCreator.createGroupSurvival(groupSurvivalClients);
                    groupSurvival.startGameState();
                    //todo : clone it
                    groupSurvivalClients = new ArrayList<>();
                }
                else if (groupSurvivalClients.size() == 1) {
                    //send no online user to the client
                }
                groupSurvivalTimer.stop();
            }
        });
    }
    public void setGroupGameTimer() {
        groupGameTimer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(groupGameClients.size() >= 1) {
                    //start game

                    GroupGame groupGame = GameStateManagerCreator.createGroupGame(groupGameClients);

                    groupGame.startGameState();
                    //todo : clone it
                    groupGameClients = new ArrayList<>();
                }
                else if (groupGameClients.size() == 1) {
                    //send no online user to the client
                }
                groupGameTimer.stop();
            }
        });
    }


}
