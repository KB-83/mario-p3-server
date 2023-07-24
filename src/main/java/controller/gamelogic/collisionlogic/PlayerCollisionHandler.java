package controller.gamelogic.collisionlogic;

import controller.gamelogic.itemlogic.BlockUnlocker;
import controller.gamelogic.playerlogic.PlayerItemEater;
import controller.gamelogic.playerlogic.PlayerLifeChecker;
import model.main_model.backgroundobject.CheckPoint;
import model.main_model.backgroundobject.block.Block;
import model.main_model.backgroundobject.block.CoinBlock;
import model.main_model.backgroundobject.block.FullCoinBlock;
import model.main_model.backgroundobject.block.SimpleBlock;
import model.main_model.backgroundobject.pipe.Pipe;
import model.main_model.entity.enemy.Bowser;
import model.main_model.entity.enemy.Enemy;
import model.main_model.entity.enemy.Plant;
import model.main_model.entity.player.Player;
import model.main_model.gamestrucure.GameState;
import model.main_model.gamestrucure.gameworldoption.collision.CollisionChecker;
import model.main_model.gamestrucure.gameworldoption.collision.Rect;
import model.main_model.levelstructure.Section;
import util.Constant;

public class PlayerCollisionHandler implements CollisionHandler{
    //todo: improve collision checker boundery
    //todo : improve this
    private GameState gameState;
    private CollisionChecker collisionChecker;
    private PlayerItemEater playerItemEater;
    private PlayerLifeChecker playerLifeChecker;
    private Player player;
    private Rect blockRect = new Rect(0,48, 0,48);
    private Rect itemRect = new Rect(0,0,48,48);
    private Rect playerRect = new Rect(0,48, 0,48);
    private Rect pipeRect = new Rect(0,96,0,48*3);
    private Rect enemyRect = new Rect(0,48,0,48);
    private Rect checkpointRect = new Rect(0,48,0,48);
    private Rect backgrounTileRect = new Rect(0,48,0,48);
    //todo: improve this too
    //too : test why blocks or pipes problem doesent apper
    private BlockUnlocker blockUnlocker;
    public PlayerCollisionHandler(GameState gameState, PlayerLifeChecker playerLifeChecker,Player player) {
        this.gameState = gameState;
        collisionChecker = new CollisionDetector(gameState.getMario());
        this.playerLifeChecker = playerLifeChecker;
        playerItemEater = new PlayerItemEater(gameState);
        this.player = player;//new
        blockUnlocker = new BlockUnlocker();

    }
    public void updateSection(Section section) {
//        enemies = section.getEnemies();
//        pipes = section.getPipes();
//        blocks = section.getBlocks();
    }
    public void applyCollisionEffects(){
        //todo : improve it
        if (player.getOnTopOfBlock() && player.getVY() < 0){
            player.setDuringJump(false);
        }
        player.setOnTopOfBlock(false);
        //
        // blocks effects
        checkBlocksCollision();

        //pipes
        checkPipesCollision();

//        enemies
        checkEnemiesCollision();
//
//        //background tiles todo: give a bound to background tiles
        checkBackgroundTilesCollision();
        checkItemEating();
        checkCheckPointTouch();


    }
    private void checkCheckPointTouch(){
        CheckPoint checkPoint = gameState.getCurrentSection().getCheckPoint();
        if (checkPoint != null){
            checkpointRect.updatePosition(checkPoint.getCol() * 48,checkPoint.getRow() * 48);
            if (!checkPoint.getSaved() && collisionChecker.didCollide(playerRect,checkpointRect)) {
                //todo : do it as a requset
                gameState.setWaitingCheckpoint(checkPoint);
                gameState.setPaused(true);
//                gameState.getCurrentUser().getLogicManager().getGraphicManager().getFrame().getCheckPointFrame().setVisible(true);
            }
        }
    }
    public void checkItemEating(){
        if (gameState == null) {
            return;
        }
        if (gameState.getCurrentSection().getItems() != null) {
            playerRect.updatePositionAndSize(player.getWidth(), player.getHeight(), player.getWorldX(), player.getWorldY());
            for (int i = 0; i < gameState.getCurrentSection().getItems().length; i++) {
                itemRect.updatePosition(gameState.getCurrentSection().getItems()[i].getWorldX(), gameState.getCurrentSection().getItems()[i].getWorldY());
                if (gameState.getCurrentSection().getItems()[i].isLock() == false && (collisionChecker.didCollide(playerRect, itemRect) || !collisionChecker.returnSamePoints(playerRect,itemRect).equals(""))) {
                    playerItemEater.eatItem(gameState.getCurrentSection().getItems(), gameState.getCurrentSection().getItems()[i], i);
                }
            }
        }
    }
    public void checkBlocksCollision(){
        playerRect.updatePositionAndSize(player.getWidth(), player.getHeight(),player.getWorldX(), player.getWorldY());
        for (int i = 0; i < gameState.getCurrentSection().getBlocks().length ; i++) {
            Block block = gameState.getCurrentSection().getBlocks()[i];
            blockRect.updatePosition(block.getCol() * 48, block.getRow() * 48);
            if (block.getItem() != null || block.getClass() == CoinBlock.class
                    || block.getClass() == FullCoinBlock.class || block.getClass() == SimpleBlock.class) {
                if (collisionChecker.returnSamePoints(playerRect,blockRect).equals("TOP")) {
                    blockUnlocker.unlock(gameState, block, gameState.getCurrentSection().getBlocks(), i);
                }
            }
            if (collisionChecker.didCollide(playerRect, blockRect)) {
                handelCollision(block.getCol() * Constant.BACKGROUND_TILE_SIZE, block.getRow() *
                        Constant.BACKGROUND_TILE_SIZE, Constant.BACKGROUND_TILE_SIZE, Constant.BACKGROUND_TILE_SIZE);
            }
            if (collisionChecker.returnSamePoints(playerRect,blockRect).equals("DOWN")) {
                player.setOnTopOfBlock(true);
//                    // todo: improve it too
                player.setWorldY(blockRect.getTopY()-player.getHeight());
                player.setCameraY(blockRect.getTopY()-player.getHeight());
                if (player.getVY() < 0) {
                    player.setVY(0);
                    player.setDuringJump(false);
                }
            }
        }
    }
    public void checkPipesCollision(){
        playerRect.updatePositionAndSize(player.getWidth(), player.getHeight(),player.getWorldX(), player.getWorldY());
        if (gameState.getCurrentSection().getPipes() != null) {
            for (Pipe pipe : gameState.getCurrentSection().getPipes()) {
                pipeRect.updatePosition(pipe.getCol() * 48, pipe.getRow() * 48);
                //todo give thebiigger rect first
                if (collisionChecker.didCollide(playerRect, pipeRect)) {
                    handelCollision(pipe.getCol() * Constant.BACKGROUND_TILE_SIZE, pipe.getRow() *
                            Constant.BACKGROUND_TILE_SIZE, 2 * Constant.BACKGROUND_TILE_SIZE, 3 * Constant.BACKGROUND_TILE_SIZE);
                }
                if (collisionChecker.returnSamePoints(playerRect,pipeRect).equals("DOWN")) {
                    player.setOnTopOfBlock(true);
//                    // todo: improve it too
                    player.setWorldY(pipeRect.getTopY()-player.getHeight());
                    player.setCameraY(pipeRect.getTopY()-player.getHeight());
                    if (player.getVY() < 0) {
                        player.setVY(0);
                        player.setDuringJump(false);
                    }
                }
            }
        }}
    public void checkEnemiesCollision(){
        if (gameState.getCurrentSection().getEnemies() != null) {
            playerRect.updatePositionAndSize(player.getWidth(), player.getHeight(),player.getWorldX(), player.getWorldY());
            for (Enemy enemy : gameState.getCurrentSection().getEnemies()) {
                enemyRect.updatePosition(enemy.getWorldX(), enemy.getWorldY());
                String position = collisionChecker.returnSamePoints(playerRect,enemyRect);
                if ((enemy.getOnTopOfBlock() || enemy.getClass() == Plant.class) && collisionChecker.didCollide(playerRect, enemyRect) || position.equals("DOWN")
                || position.equals("RIGHT") || position.equals("LEFT") || position.equals("TOP") || position.equals("FULL")) {
                    if (player.isUnHeat()){
                        if (enemy.getClass() != Bowser.class) {
                            enemy.getEnemyController().getEnemyLifeChecker().kill();
                            return;
                        }
                    }
                    if (position.equals("DOWN")){
                        enemy.getEnemyController().getEnemyLifeChecker().heatByHead();
                    }
                    else {
                        playerLifeChecker.handleEnemyCollide(enemy,position);
                        player.setDuringJump(false);
                    }

                    return;
                }
            }
        }
    }
    public void checkBackgroundTilesCollision(){
        playerRect.updatePositionAndSize(player.getWidth(), player.getHeight(),player.getWorldX(), player.getWorldY());
        for (int i = 0; i < gameState.getCurrentSection().getBackgroundMap().getBackGroundTiles().length; i++){
            for (int j = 0;j < gameState.getCurrentSection().getBackgroundMap().getBackGroundTiles()[i].length;j++) {
                if (gameState.getCurrentSection().getBackgroundMap().getBackGroundTiles()[i][j].isSolid()){
                    backgrounTileRect.updatePosition(j*48,i*48);
                    if(collisionChecker.didCollide(playerRect,backgrounTileRect)){
                        handelCollision(j*48,i*48,48,48);
                    }
                }
//                System.out.println("111 player collision handler  "+collisionChecker.returnSamePoints(playerRect,backgrounTileRect));
                if (collisionChecker.returnSamePoints(playerRect,backgrounTileRect).equals("DOWN")) {
                    player.setOnTopOfBlock(true);
//                    // todo: improve it too
                    player.setWorldY(backgrounTileRect.getTopY()-player.getHeight());
                    player.setCameraY(backgrounTileRect.getTopY()-player.getHeight());
                    if (player.getVY() < 0) {
                        player.setVY(0);
                        player.setDuringJump(false);
                    }
                }
            }
        }}
    public Pipe isOnTopOfTelePipe(){
        if (gameState.getCurrentSection().getPipes() != null) {
            for (Pipe pipe : gameState.getCurrentSection().getPipes()) {
                if (player.getWorldX() >= (pipe.getCol()*Constant.BACKGROUND_TILE_SIZE)-(Constant.BACKGROUND_TILE_SIZE/2)
                        && player.getWorldX()+ player.getWidth() <= (pipe.getCol()*Constant.BACKGROUND_TILE_SIZE) +
                        pipeRect.getWidth() && player.getWorldY()+ player.getHeight() >= pipe.getRow() * Constant.BACKGROUND_TILE_SIZE) {
                    String s = pipe.getClass().getSimpleName();
                    if (s.equals("TelePlantPipe") || s.equals("SimpleTelePipe") ||
                            s.equals("SimpleSpawnPipe") || s.equals("SpawnPlantPipe")) {
                        return pipe;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public void checkPlayerCollision() {

    }

    public void handelCollision(int itemLeftWorldX, int itemTopWorldY,int itemWidth,int itemHeight) {
        if (player.getVX() > 0 && itemLeftWorldX < player.getWorldX() + player.getWidth()
                && itemLeftWorldX + itemWidth > player.getWorldX() + player.getHeight()) {
            player.setVX(0);
        }
        if (player.getVX() < 0 && itemLeftWorldX < player.getWorldX()
                && (itemLeftWorldX + itemWidth > player.getWorldX())) {
            player.setVX(0);
        }
        if (player.getVY() > 0 && itemTopWorldY + itemHeight > player.getWorldY() &&
                itemTopWorldY < player.getWorldY()) {
            player.setVY(-player.getVY());
            if (player.isDuringJump()) {
                player.setDuringJump(false);
            }
        }
        if (player.getVY() < 0 && itemTopWorldY < player.getWorldY() + player.getHeight() &&
                itemTopWorldY + itemHeight > player.getWorldY() + player.getHeight()) {
            player.setVY(0);
            player.setWorldY(itemTopWorldY - player.getHeight());
            player.setCameraY(itemTopWorldY - player.getHeight());
            if (player.isDuringJump()) {
                player.setDuringJump(false);
            }
        }
    }
}
