package model.main_model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import controller.ClientController;
import model.dto.entity.PlayerDTO;
import model.dto.game.GameStateDTO;
import model.main_model.entity.player.Player;
import model.main_model.gamestrucure.GameState;
import model.main_model.chat.Chat;
import model.main_model.room.Room;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "coin")
    private int coin;

    @Column(name = "score")
    private int score;

    @Column(name = "diamond")
    private int diamond;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fund_id")
    @JsonIgnore///for now
    private Fund fund;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<Chat> chats = new ArrayList<>();


    @JsonIgnore
    @Transient
    private GameState currentGameState;
    @JsonIgnore
    @Transient
    private GameStateDTO currentGameStateDTO;
    @JsonIgnore
    @Transient
    private Player player;
    @JsonIgnore
    @Transient
    private PlayerDTO playerDTO;
    @JsonIgnore
    @Transient

    private ClientController clientController;
    @JsonIgnore
    @Transient
    private Room currentRoom;

//    todo : maybe in feature going to add multiplie players
//    private Player[] players;
    @JsonIgnore
    @Transient
    private String[] selectedBag;

    public Client() {
        chats = new ArrayList<>();
//        player = new Mario("");
    }

    public Client(String username, String password,ClientController clientController) {
        this.clientController = clientController;
        this.username = username;
        this.password = password;
        //test
        chats = new ArrayList<>();
        fund = new Fund();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        if (player != null) {
            player.setClientName(username);
        }
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

    public List<Chat> getChats() {
        return chats;
    }

    public void setChats(List<Chat> chats) {
        this.chats = chats;
    }

    public Fund getFund() {
        return fund;
    }

    public void setFund(Fund fund) {
        this.fund = fund;
    }

    public String[] getSelectedBag() {
        return selectedBag;
    }

    public void setSelectedBag(String[] selectedBag) {
        this.selectedBag = selectedBag;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }
}
