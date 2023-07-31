package controller.gamelogic.gamestatelogic;


import controller.gamelogic.gamestatelogic.marathonlogic.PlayerMarathonLifeChecker;
import controller.gamelogic.gamestatelogic.marathonlogic.PlayerMarathonRequestHandler;
import controller.gamelogic.gamestatelogic.survivallogic.PlayerGSurvivalLifeChecker;
import controller.gamelogic.gamestatelogic.survivallogic.PlayerGSurvivalRequestHandler;
import controller.gamelogic.gamestatelogic.survivallogic.PlayerSurvivalLifeChecker;
import controller.gamelogic.gamestatelogic.survivallogic.PlayerSurvivalRequestHandler;
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
    private boolean isLoosed;
    private int lifeTime;

    public PlayerController(GameState gameState, Player player,String gameStateType) {
        if (gameStateType.toLowerCase().equals("marathon")) {
            playerRequestHandler = new PlayerMarathonRequestHandler(gameState, player);
            playerLifeChecker = new PlayerMarathonLifeChecker(gameState,player);
        }
        else if (gameStateType.toLowerCase().equals("survival")){
            playerRequestHandler = new PlayerSurvivalRequestHandler(gameState, player);
            playerLifeChecker = new PlayerSurvivalLifeChecker(gameState,player);
        }
        else if (gameStateType.toLowerCase().equals("groupsurvival")){
            playerRequestHandler = new PlayerGSurvivalRequestHandler(gameState, player);
            playerLifeChecker = new PlayerGSurvivalLifeChecker(gameState,player);
        }
        playerMovementHandler = new PlayerMovementHandler(gameState,player);
        playerCollisionHandler = new PlayerCollisionHandler(gameState,playerLifeChecker,player);

    }
    public void update(){
        playerCollisionHandler.applyCollisionEffects();
        playerMovementHandler.updatePlayerPosition();
        playerLifeChecker.update();
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

    public boolean isLoosed() {
        return isLoosed;
    }

    public void setLoosed(boolean loosed) {
        isLoosed = loosed;
    }

    public int getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(int lifeTime) {
        this.lifeTime = lifeTime;
    }
}
