package model.main_model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import controller.ClientController;
import model.dto.entity.PlayerDTO;
import model.dto.game.GameStateDTO;
import model.main_model.entity.player.Mario;
import model.main_model.entity.player.Player;
import model.main_model.gamestrucure.GameState;
import model.main_model.room.PrivateChat;

import java.util.ArrayList;

public class Client {
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
    @JsonInclude(JsonInclude.Include.ALWAYS)
    private ArrayList <PrivateChat> privateChats;
//    todo : maybe in feature going to add multiplie players
//    private Player[] players;
    private int coin;
    private int score;
    private int diamond;

    public Client() {
        this.player = new Mario();
        privateChats = new ArrayList<>();
    }

    public Client(String username, String password,ClientController clientController) {
        this.clientController = clientController;
        this.username = username;
        this.password = password;
        //test
        this.player = new Mario();
        privateChats = new ArrayList<>();
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


    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    public GameState getCurrentGameState() {
        return currentGameState;
    }

    public void setCurrentGameState(GameState currentGameState) {
        this.currentGameState = currentGameState;
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

    public int getDiamond() {
        return diamond;
    }

    public void setDiamond(int diamond) {
        this.diamond = diamond;
    }

    public ArrayList<PrivateChat> getPrivateChats() {
        return privateChats;
    }

    public void setPrivateChats(ArrayList<PrivateChat> privateChats) {
        this.privateChats = privateChats;
    }
}
