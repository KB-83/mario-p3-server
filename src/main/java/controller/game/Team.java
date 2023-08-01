package controller.game;

import model.main_model.Client;

import java.util.ArrayList;

public class Team{
    private int groupNumber;
    private ArrayList<Client> clients;
    private int numOfPotion;
    private int numOfThrowable;

    public Team(int groupNumber, ArrayList<Client> clients) {
        this.groupNumber = groupNumber;
        this.clients = clients;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
    }

    public int getNumOfPotion() {
        return numOfPotion;
    }

    public void setNumOfPotion(int numOfPotion) {
        this.numOfPotion = numOfPotion;
    }

    public int getNumOfThrowable() {
        return numOfThrowable;
    }

    public void setNumOfThrowable(int numOfThrowable) {
        this.numOfThrowable = numOfThrowable;
    }
}

