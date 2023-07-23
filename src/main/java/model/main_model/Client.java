package model.main_model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import controller.ClientController;
import model.dto.entity.player.PlayerDTO;
import model.dto.game.GameStateDTO;
import model.main_model.entity.player.Mario;
import model.main_model.entity.player.Player;
import model.main_model.gamestrucure.Game;
import model.main_model.gamestrucure.GameState;

public class Client {
    @JsonIgnore
    private Game currentGame;
    @JsonIgnore
    private GameState currentGameState;
    @JsonIgnore
    private GameStateDTO currentGameStateDTO;
    @JsonIgnore
    private Player player;
    @JsonIgnore
    private PlayerDTO playerDTO;
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
        this.player = new Mario();
    }

    public Client(String username, String password,ClientController clientController) {
        this.clientController = clientController;
        this.username = username;
        this.password = password;
        //test
        this.player = new Mario();
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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public GameStateDTO getCurrentGameStateDTO() {
        return currentGameStateDTO;
    }

    public void setCurrentGameStateDTO(GameStateDTO currentGameStateDTO) {
        this.currentGameStateDTO = currentGameStateDTO;
    }

    public PlayerDTO getPlayerDTO() {
        return playerDTO;
    }

    public void setPlayerDTO(PlayerDTO playerDTO) {
        this.playerDTO = playerDTO;
    }
}
