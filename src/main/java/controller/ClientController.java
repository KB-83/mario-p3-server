package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import controller.connection.RequestHandler;
import controller.room.RoomsManager;
import model.main_model.Client;
import model.main_model.entity.player.V;
import model.main_model.room.Room;
import model.request.Request;
import model.response.Response;
import util.Config;
import util.Constant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientController extends Thread{
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private Client client;
    private boolean isOnline;
    private final static ObjectMapper JSON_MAPPER = new ObjectMapper();
    private static boolean LIFE_IS_HARD = true;

    public ClientController(Socket socket) {
        this.socket = socket;
        this.client = new Client();
        client.setClientController(this);
        isOnline = true;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(),true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public ClientController(Client client) {
        this.client = client;
    }
    public void run() {
        while (isOnline) {
            receiveRequest();
        }
    }
    public void setClient(Client client) {
        this.client = client;
        if (client != null) {
            client.setClientController(this);
        }
    }
    public void sendResponse(Response response) {
        try {
            writer.println(JSON_MAPPER.writeValueAsString(response));
        } catch (JsonProcessingException e) {
            System.out.println("json mapping problem");
            throw new RuntimeException(e);
        }
    }
    private void receiveRequest() {
        try {
            String s = reader.readLine();
            Request request = JSON_MAPPER.readValue(s,Request.class);
            request.visit(RequestHandler.getInstance(),this);
        } catch (JsonProcessingException e) {
            System.out.println("json mapping problem");
            throw new RuntimeException(e);
        } catch (IOException | IllegalArgumentException e) {
            disconnectClient();
        }
    }

    public Client getClient() {
        return client;
    }

    public boolean isOnline() {
        return isOnline;
    }
    public void cleanClientFromGame() {
        client.setCurrentGameState(null);
        client.setCurrentGameStateDTO(null);
        //test
        client.setPlayerDTO(null);
        client.setPlayer(null);
        client.setSelectedBag(null);
    }
    private void disconnectClient() {
        isOnline = false;
        if (client != null) {
            Config.ONLINE_CLIENTS.remove(client.getUsername(), client);
            if (client.getCurrentRoom() != null) {
                if (client.equals(client.getCurrentRoom().getManager())) {
                    client.getCurrentRoom().getRoomController().closeRoom("Room's Manager Left :(");
                }
                else {
                    Room room = client.getCurrentRoom();
                    room.getClients().remove(client);
                    room.getRoomController().updateClientsRoom();
                }

            }
        }
        System.out.println("client "+ socket.getInetAddress().toString()+" disconnected");
    }
}
