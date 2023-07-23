package controller.gamelogic.playerlogic;


import model.main_model.gamestrucure.GameState;
import util.Constant;

public class PlayerMovementHandler {
    private GameState gameState;

    public PlayerMovementHandler(GameState gameState) {
        this.gameState = gameState;
    }
    public void updatePlayerPosition() {
//        if (gameState.getMario().getWorldX() >= (gameState.getCurrentSection().getLength() * Constant.BACKGROUND_TILE_SIZE)
//                - gameState.getMario().getWidth() - Constant.BACKGROUND_TILE_SIZE){
//            if (gameState.getCurrentSection().getClass().getSimpleName().equals("TeleSection")) {
//                if (gameState.getMario().getVX() > 0){
//                    gameState.getMario().setVX(0);
//                }
//            }
//            else {
//                gameState.getGameStateController().nextSection();
//                return;
//            }
//        }
//        if(gameState.getMario().getVX() < 0 && gameState.getMario().getCameraX() < 10){
//            gameState.getMario().setVX(0);
//        }
//        gameState.getMario().setWorldX((int) (gameState.getMario().getWorldX()+(1.0/ Constant.FPS * gameState.getMario().getVX())));
//        // movment mechanisem
//        if(gameState.getMario().getCameraX() < Constant.PANEL_WIDTH/2 || gameState.getMario().getVX() < 0 ||
//                gameState.getMario().getWorldX() >= (gameState.getCurrentSection().getLength() * Constant.BACKGROUND_TILE_SIZE) - Constant.PANEL_WIDTH/2) {
//            gameState.getMario().setCameraX((int) (gameState.getMario().getCameraX()+(1.0/Constant.FPS * gameState.getMario().getVX())));
//        }
//        gameState.getMario().setWorldY((int) (gameState.getMario().getWorldY() - (1.0/Constant.FPS * gameState.getMario().getVY())));
//        gameState.getMario().setCameraY((int) (gameState.getMario().getCameraY() - (1.0/Constant.FPS * gameState.getMario().getVY())));
    }
}
