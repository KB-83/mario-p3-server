package model.response;

import java.util.ArrayList;

public class ChatSearchResponse extends Response{
    private ArrayList<String> usernames;

    public ChatSearchResponse() {
    }

    public ChatSearchResponse(ArrayList<String> usernames) {
        this.usernames = usernames;
    }

    public ArrayList<String> getUsernames() {
        return usernames;
    }

    public void setUsernames(ArrayList<String> usernames) {
        this.usernames = usernames;
    }
}
