package model.main_model.chat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import model.main_model.Client;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "chats")
public class Chat {
    // if room its a room token

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "opponent_username", nullable = false)
    private String opponentUsername;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    @JsonIgnore
    private Client client;

    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL, orphanRemoval = true , fetch = FetchType.EAGER)
    private List<Massage> massages = new ArrayList<>();



    public Chat() {
    }

    public Chat(String opponentUsername, ArrayList<Massage> massages) {
        this.opponentUsername = opponentUsername;
        this.massages = massages;
    }

    public String getOpponentUsername() {
        return opponentUsername;
    }

    public void setOpponentUsername(String opponentUsername) {
        this.opponentUsername = opponentUsername;
    }

    public List<Massage> getMassages() {
        return massages;
    }

    public void setMassages(List<Massage> massages) {
        this.massages = massages;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
