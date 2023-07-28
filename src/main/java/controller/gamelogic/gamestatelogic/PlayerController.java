package controller.gamelogic.gamestatelogic;


import controller.gamelogic.gamestatelogic.marathonlogic.PlayerMarathonRequestHandler;
import controller.gamelogic.playerlogic.PlayerRequestHandler;
import controller.gamelogic.collisionlogic.PlayerCollisionHandler;
import controller.gamelogic.playerlogic.PlayerLifeChecker;
import controller.gamelogic.playerlogic.PlayerMovementHandler;
import model.main_model.entity.player.Player;
import model.main_model.gamestrucure.GameState;

public class PlayerController {
    private PlayerMovementHandler playerMovementHandler;
    private PlayerLifeChecker playerLifeChecker;
    private PlayerCollisionHandler playerCollisionHandler;
    private PlayerRequestHandler playerRequestHandler;

    public PlayerController(GameState gameState, Player player,String gameStateType) {
        playerMovementHandler = new PlayerMovementHandler(gameState,player);
        playerLifeChecker = new PlayerLifeChecker(gameState,player);
        playerCollisionHandler = new PlayerCollisionHandler(gameState,playerLifeChecker,player);
        if (gameStateType.toLowerCase().equals("marathon")) {
            playerRequestHandler = new PlayerMarathonRequestHandler(gameState, player);
        }

    }
    public void update(){
        playerCollisionHandler.applyCollisionEffects();
        playerMovementHandler.updatePlayerPosition();
        playerLifeChecker.checkIfHurt();
        playerRequestHandler.update();
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

    public PlayerRequestHandler getPlayerRequestHandler() {
        return playerRequestHandler;
    }

    public void setPlayerRequestHandler(PlayerRequestHandler playerRequestHandler) {
        this.playerRequestHandler = playerRequestHandler;
    }
}
