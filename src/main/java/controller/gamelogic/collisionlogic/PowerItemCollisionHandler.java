package controller.gamelogic.collisionlogic;

import model.main_model.backgroundobject.block.Block;
import model.main_model.backgroundobject.pipe.Pipe;
import model.main_model.entity.enemy.Enemy;
import model.main_model.entity.power_item.PowerItem;
import model.main_model.gamestrucure.GameState;
import model.main_model.gamestrucure.gameworldoption.collision.CollisionChecker;
import model.main_model.gamestrucure.gameworldoption.collision.Rect;

public class PowerItemCollisionHandler implements CollisionHandler{
    private GameState gameState;
    private PowerItem powerItem;
    private CollisionChecker collisionChecker;
    private Rect blockRect = new Rect(0,48, 0,48);
    private Rect itemRect = new Rect(0,48, 0,48);
    private Rect pipeRect = new Rect(0,96,0,48*3);
    private Rect enemyRect = new Rect(0,48,0,48);
    private Rect backgrounTileRect = new Rect(0,48,0,48);

    public PowerItemCollisionHandler(GameState gameState, PowerItem powerItem) {
        collisionChecker = new CollisionDetector(powerItem);
        this.gameState = gameState;
        this.powerItem = powerItem;
    }
    public void applyCollisionEffects(){
        //todo : improve it
        //
        // blocks effects
//        checkBlocksCollision();

        //pipes
//        checkPipesCollision();

//        enemies
//        checkEnemiesCollision();

        //background tiles todo: give a bound to background tiles
        checkBackgroundTilesCollision();

    }

    // todo : there is a hard code in collision checking fix it if you had time.
    @Override
    public void checkBlocksCollision() {
        itemRect.updatePosition(powerItem.getWorldX(), powerItem.getWorldY());
        for (Block block : gameState.getCurrentSection().getBlocks()) {
            blockRect.updatePosition(block.getCol() * 48, block.getRow() * 48);
            if (collisionChecker.didCollide(itemRect, blockRect)) {
                powerItem.getController().execute();
            }
        }

    }

    @Override
    public void checkPipesCollision() {
        itemRect.updatePosition(powerItem.getWorldX(), powerItem.getWorldY());
        if (gameState.getCurrentSection().getPipes() != null) {
            for (Pipe pipe : gameState.getCurrentSection().getPipes()) {
                pipeRect.updatePosition(pipe.getCol() * 48, pipe.getRow() * 48);
//                    //todo give thebiigger rect first
                if (collisionChecker.didCollide(itemRect, pipeRect)) {
                    powerItem.getController().execute();
                }
            }
        }

    }

    @Override
    public void checkEnemiesCollision() {
        if (gameState.getCurrentSection().getEnemies() != null) {
            itemRect.updatePosition(powerItem.getWorldX(), powerItem.getWorldY());
            for (Enemy enemy : gameState.getCurrentSection().getEnemies()) {
                enemyRect.updatePosition(enemy.getWorldX(), enemy.getWorldY());
                if (collisionChecker.didCollide(itemRect, enemyRect)) {
                    powerItem.getController().execute();

                }
            }
        }

    }

    @Override
    public void checkBackgroundTilesCollision() {
        itemRect.updatePosition(powerItem.getWorldX(), powerItem.getWorldY());
        for (int i = 0; i < gameState.getCurrentSection().getBackgroundMap().getBackGroundTiles().length; i++){
            for (int j = 0;j < gameState.getCurrentSection().getBackgroundMap().getBackGroundTiles()[i].length;j++) {
                if (gameState.getCurrentSection().getBackgroundMap().getBackGroundTiles()[i][j].isSolid()){
                    backgrounTileRect.updatePosition(j*48,i*48);
                    if(collisionChecker.didCollide(itemRect, backgrounTileRect)){
                        powerItem.getController().execute();
                    }
                }
            }
        }
    }

    @Override
    public void checkPlayerCollision() {
        // it has to have  list of clients.
    }
}
