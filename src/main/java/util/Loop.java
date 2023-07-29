package util;


import controller.gamelogic.gamestatelogic.GameStateController;
import model.main_model.Client;
import model.main_model.gamestrucure.GameState;

import java.util.ArrayList;

public class Loop implements Runnable{
    private GameStateController gameStateController;// to update
    private Thread gameThread;
    private int FPS;
    private boolean running;
    private boolean isPaused;
//    this int is to test app rendering
    private int tryFps;

    public Loop(GameStateController gameStateController, int FPS) {
        this.gameStateController = gameStateController;
        this.FPS = FPS;
    }

    public void start(){
        if(gameThread == null) {
            gameThread = new Thread(this);
        }
        running = true;
        gameThread.start();
    }

    public void kill() {
        gameThread.stop();
        running = false;
        gameThread = null;
    }
    public void pause() {
        isPaused = true;
    }
    public void resume() {
        isPaused = false;
    }

    public void run() {
        final long drawInterval = 1000000000/FPS;
        long lastTime = System.nanoTime();
        long startfPS = System.nanoTime();
        long delta = 0;
        long currentTime;
        while (running){
            checkOnlineClients();
            // sorry but it is the best i can design fo pause mechanisem :(
            //todo : improve pause mechanisem
//            while (gameState.isPaused()){}
            currentTime = System.nanoTime();
            delta = (currentTime - lastTime) / drawInterval ;
            if(delta >= 1){
                tryFps++;
                gameStateController.update();
                lastTime = System.nanoTime();
            }
            if (System.nanoTime()-startfPS >= 1000000000){
                startfPS = System.nanoTime();
                tryFps = 0;
            }
        }
    }
    private void checkOnlineClients() {
        ArrayList<Client> clients = gameStateController.getClients();
        for (Client client : clients) {
            if (Config.ONLINE_CLIENTS.containsValue(client)) {
                return;
            }
        }
        kill();

    }
}
