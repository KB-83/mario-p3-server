package model.main_model.chat;

public class Massage {
    private String senderUsername;
    //all,someone
    private String receiverUsername;
    private String context;

    public Massage(String senderUsername, String context) {
        this.senderUsername = senderUsername;
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
}
