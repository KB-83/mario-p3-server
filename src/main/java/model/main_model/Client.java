package model.main_model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import controller.ClientController;
import model.main_model.entity.player.Player;
import model.main_model.gamestrucure.Game;
import model.main_model.gamestrucure.GameState;

public class Client {
    @JsonIgnore
    private Game currentGame;
    @JsonIgnore
    private GameState currentGameState;
    @JsonIgnore
    private Player player;
    @JsonIgnore
    private ClientController clientController;
    private String username;
    private String password;
    private Game[] games;
    //todo : think about it (next-line)
    private GameState[] savedGames;
    private Player[] players;
    private int coins;
    private int score;

    public Client() {

    }

    public Client(String username, String password,ClientController clientController) {
        this.clientController = clientController;
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Game[] getGames() {
        return games;
    }

    public void setGames(Game[] games) {
        this.games = games;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }

    public GameState getCurrentGameState() {
        return currentGameState;
    }

    public void setCurrentGameState(GameState currentGameState) {
        this.currentGameState = currentGameState;
    }

    public GameState[] getSavedGames() {
        return savedGames;
    }

    public void setSavedGames(GameState[] savedGames) {
        this.savedGames = savedGames;
    }

    public ClientController getClientController() {
        return clientController;
    }

    public void setClientController(ClientController clientController) {
        this.clientController = clientController;
    }
}
