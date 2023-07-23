package controller;

import model.main_model.Client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class ServerController {
    private final int PORT = 9001;
    private ServerSocket serverSocket;
    private List<Client> onlineClients;// handle it

    public ServerController() {
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            System.out.println("port " + PORT +" is not available.");
        }
    }
    public void start() {
        while (true) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ClientController clientController = new ClientController(socket);
            clientController.start();
        }
    }
}
