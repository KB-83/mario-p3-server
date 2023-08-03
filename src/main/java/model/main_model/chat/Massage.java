package model.main_model.chat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "massages")
public class Massage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "chat_id", nullable = false)
    @JsonIgnore
    private Chat chat;
    @Column(name = "sender_username", nullable = false)
    private String senderUsername;
    @Column(name = "receiver_username")
    private String receiverUsername;
    @Column(name = "context", nullable = false)
    private String context;

    public Massage(String senderUsername, String receiverUsername, String context) {
        this.senderUsername = senderUsername;
        this.receiverUsername = receiverUsername;
        this.context = context;
    }

    public Massage() {
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getReceiverUsername() {
        return receiverUsername;
    }

    public void setReceiverUsername(String receiverUsername) {
        this.receiverUsername = receiverUsername;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }
}
