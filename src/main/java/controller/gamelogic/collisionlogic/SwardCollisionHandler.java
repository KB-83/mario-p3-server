package controller.gamelogic.collisionlogic;



import model.main_model.gamestrucure.GameState;
import model.main_model.backgroundobject.block.Block;
import model.main_model.backgroundobject.pipe.Pipe;
import model.main_model.entity.Sward;
import model.main_model.entity.enemy.Enemy;
import model.main_model.gamestrucure.gameworldoption.collision.CollisionChecker;
import model.main_model.gamestrucure.gameworldoption.collision.Rect;

public class SwardCollisionHandler implements CollisionHandler {

    //todo: improve collision checker boundery
    //todo : improve this
    private GameState gameState;
    private CollisionChecker collisionChecker;
    private Sward sward;
    private Enemy[] enemies;
    private Pipe[] pipes;
    private Block[] blocks;
    private Rect blockRect = new Rect(0,48, 0,48);
    private Rect playerRect = new Rect(0,48, 0,48);
    private Rect pipeRect = new Rect(0,96,0,48*3);
    private Rect enemyRect = new Rect(0,48,0,48);
    private Rect swardRect = new Rect(0,96,0,48);
    //todo: improve this too
    public SwardCollisionHandler(GameState gameState) {
        this.gameState = gameState;
//        enemies = gameState.getCurrentSection().getEnemies();
//        pipes = gameState.getCurrentSection().getPipes();
//        blocks = gameState.getCurrentSection().getBlocks();
    }
    public void applyCollisionEffects(){
        //todo : improve it
        if (sward != null && sward.isLock()){
            return;
        }
//        if (collisionChecker == null){
//            collisionChecker = new CollisionDetector(gameState.getMario().getSward());
//            this.sward = gameState.getMario().getSward();
//        }
        // block
        checkBlocksCollision();

        //pipes
        checkPipesCollision();

//        enemies
        checkEnemiesCollision();


    }

    @Override
    public void checkBlocksCollision() {
//        swardRect.updatePositionAndSize(sward.getWidth(), sward.getHeight(),sward.getWorldX(), sward.getWorldY());
        blocks = gameState.getCurrentSection().getBlocks();
        for (int i = 0; i < blocks.length ; i++) {
            Block block = blocks[i];
            blockRect.updatePosition(block.getCol() * 48, block.getRow() * 48);
//            if (collisionChecker.didCollide(swardRect, blockRect) && !collisionChecker.returnSamePoints(swardRect,blockRect).equals("DOWN")) {
//                sward.setHeatBlock(true);
//                return;
//            }
        }
    }

    @Override
    public void checkPipesCollision() {
        swardRect.updatePositionAndSize(sward.getWidth(), sward.getHeight(),sward.getWorldX(), sward.getWorldY());
        pipes = gameState.getCurrentSection().getPipes();
        if (pipes == null){
            return;
        }
        for (int i = 0; i < pipes.length ; i++) {
            Pipe pipe = pipes[i];
            pipeRect.updatePosition(pipe.getCol() * 48, pipe.getRow() * 48);
            if (collisionChecker.didCollide(swardRect, pipeRect) && !collisionChecker.returnSamePoints(swardRect,pipeRect).equals("DOWN")) {
                sward.setHeatBlock(true);
                return;
            }
        }
    }

    @Override
    public void checkEnemiesCollision() {

        swardRect.updatePositionAndSize(sward.getWidth(), sward.getHeight(),sward.getWorldX(), sward.getWorldY());
        enemies = gameState.getCurrentSection().getEnemies();
        if (enemies == null){
            return;
        }
        for (int i = 0; i < enemies.length ; i++) {
            Enemy enemy = enemies[i];
            enemyRect.updatePosition(enemy.getWorldX(), enemy.getWorldY());
            if (collisionChecker.returnSamePoints(swardRect,enemyRect).equals("RIGHT") || collisionChecker.returnSamePoints(swardRect,enemyRect).equals("LEFT") ||
                    (collisionChecker.didCollide(swardRect, enemyRect) && !collisionChecker.returnSamePoints(swardRect,enemyRect).equals("DOWN"))) {
                sward.setHeatBlock(true);
//                enemy.getEnemyController().getEnemyLifeChecker().kickedBySward();
                return;
            }
        }
    }

    @Override
    public void checkBackgroundTilesCollision() {

    }

    @Override
    public void checkPlayerCollision() {

    }
}
