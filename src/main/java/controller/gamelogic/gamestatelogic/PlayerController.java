package controller.gamelogic.gamestatelogic;


import controller.gamelogic.collisionlogic.PlayerCollisionHandler;
import controller.gamelogic.playerlogic.PlayerLifeChecker;
import controller.gamelogic.playerlogic.PlayerMovementHandler;
import model.main_model.gamestrucure.GameState;

public class PlayerController {
    private PlayerMovementHandler playerMovementHandler;
    private PlayerLifeChecker playerLifeChecker;
    private PlayerCollisionHandler playerCollisionHandler;

    public PlayerController(GameState gameState) {
        playerMovementHandler = new PlayerMovementHandler(gameState);
        playerLifeChecker = new PlayerLifeChecker(gameState);
        playerCollisionHandler = new PlayerCollisionHandler(gameState,playerLifeChecker);
    }
    public void update(){
        playerCollisionHandler.applyCollisionEffects();
        playerMovementHandler.updatePlayerPosition();
        playerLifeChecker.checkIfHurt();
    }


    public PlayerMovementHandler getPlayerMovementHandler() {
        return playerMovementHandler;
    }

    public void setPlayerMovementHandler(PlayerMovementHandler playerMovementHandler) {
        this.playerMovementHandler = playerMovementHandler;
    }

    public PlayerLifeChecker getPlayerLifeChecker() {
        return playerLifeChecker;
    }

    public void setPlayerLifeChecker(PlayerLifeChecker playerLifeChecker) {
        this.playerLifeChecker = playerLifeChecker;
    }

    public PlayerCollisionHandler getPlayerCollisionHandler() {
        return playerCollisionHandler;
    }

    public void setPlayerCollisionHandler(PlayerCollisionHandler playerCollisionHandler) {
        this.playerCollisionHandler = playerCollisionHandler;
    }
}
