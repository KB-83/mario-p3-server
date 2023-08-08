package controller.gamelogic.playerlogic;


import model.main_model.entity.player.Player;
import model.main_model.gamestrucure.GameState;
import util.Constant;

public class PlayerMovementHandler {
    private GameState gameState;
    private Player player;

    public PlayerMovementHandler(GameState gameState, Player player) {

        this.gameState = gameState;
        this.player = player;
    }
    public void updatePlayerPosition() {
        if (player.getWorldX() >= (gameState.getCurrentSection().getLength() * Constant.BACKGROUND_TILE_SIZE)
                - player.getWidth() - Constant.BACKGROUND_TILE_SIZE){
            if (gameState.getCurrentSection().getClass().getSimpleName().equals("TeleSection") || player.getVX() < 0) {
                if (player.getVX() > 0){
                    player.setVX(0);
                }
            }
            else {
                player.getPlayerController().getPlayerRequestHandler().nextSection();
                return;
            }
        }
        if(player.getVX() < 0 && player.getCameraX() < 10){
            player.setVX(0);
        }
        player.setWorldX((int) (player.getWorldX()+(1.0/ Constant.FPS * player.getVX())));
        // movment mechanisem
        if(player.getCameraX() < Constant.PANEL_WIDTH/2 || player.getVX() < 0 ||
                player.getWorldX() >= (gameState.getCurrentSection().getLength() * Constant.BACKGROUND_TILE_SIZE) - Constant.PANEL_WIDTH/2) {
            player.setCameraX((int) (player.getCameraX()+(1.0/Constant.FPS * player.getVX())));
        }
        player.setWorldY((int) (player.getWorldY() - (1.0/Constant.FPS * player.getVY())));
        player.setCameraY((int) (player.getCameraY() - (1.0/Constant.FPS * player.getVY())));
    }
}
