package controller.gamelogic.playerlogic;



import model.main_model.entity.item.Item;
import model.main_model.entity.player.Player;
import model.main_model.gamestrucure.GameState;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerItemEater {
    private GameState gameState;
    private Player player;
//    private Sound sound;
    private Timer unHeat;
    private long lastUnHeatTime;

    public PlayerItemEater(GameState gameState,Player player) {
        this.gameState = gameState;
        this.player = player;
//        sound = new Sound("POWER_UP");
        setUnHeat();
    }
    private void setUnHeat(){
        unHeat = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (System.currentTimeMillis() - lastUnHeatTime > 15000 ){
//                    gameState.getMario().setUnHeat(false);
                    unHeat.stop();
                }
            }
        });
    }


    public void eatItem(Item[] items, Item item, int index) {
        items[index] = null;
        String s = item.getClass().getSimpleName();
        switch (s) {
            case "Star":
//                sound.setSound("POWER_UP");
                gameState.setScore(gameState.getScore() + 40);
                lastUnHeatTime = System.currentTimeMillis();
                unHeat.start();
                player.setUnHeat(true);
                int i = gameState.getMarioState();
                switch (i) {
                    case 0:
                        gameState.setMarioState(1);
                        player.setMega(true);
                        break;
                    case 1:
                        gameState.setMarioState(2);
                        player.setMega(false);
                        player.setFire(true);
                }
                break;
            case "Mushroom":
//                sound.setSound("POWER_UP");
                gameState.setScore(gameState.getScore() + 30);
                i = gameState.getMarioState();
                switch (i) {
                    case 0:
                        gameState.setMarioState(1);
                        player.setMega(true);
                        break;
                    case 1:
                        gameState.setMarioState(2);
                        player.setMega(false);
                        player.setFire(true);
                }
                break;
            case "Flower":
//                sound.setSound("POWER_UP");
                gameState.setScore(gameState.getScore() + 20);
                i = gameState.getMarioState();
                switch (i) {
                    case 0:
                        gameState.setMarioState(1);
                        player.setMega(true);
                        break;
                    case 1:
                        gameState.setMarioState(2);
                        player.setMega(false);
                        player.setFire(true);
                        break;
                }
                break;
            case "Coin":
//                sound.setSound("COIN");
                gameState.setScore(gameState.getScore() + 10);
                gameState.setCoins(gameState.getCoins()+1);
                break;
        }
//        sound.play();
        Item[] newItems = new Item[items.length-1];
        int i = 0;
        for (int j = 0 ; j < items.length ; j++){
            if(items[j] != null) {
                newItems[i] = items[j];
                i++;
            }
        }
        gameState.getCurrentSection().setItems(newItems);

    }

//    public Sound getSound() {
//        return sound;
//    }
//
//    public void setSound(Sound sound) {
//        this.sound = sound;
//    }
}
