package controller.gamelogic.collisionlogic;


import model.main_model.backgroundobject.block.Block;
import model.main_model.backgroundobject.pipe.Pipe;
import model.main_model.entity.enemy.Enemy;
import model.main_model.entity.enemy.Plant;
import model.main_model.gamestrucure.GameState;
import model.main_model.gamestrucure.gameworldoption.collision.CollisionChecker;
import model.main_model.gamestrucure.gameworldoption.collision.Rect;
import model.main_model.levelstructure.Section;
import util.Constant;

public class EnemyCollisionHandler{

        //todo : improve this
        public boolean isHeat;
        private GameState gameState;
        private CollisionChecker collisionChecker;
        private Enemy enemy;
        private Rect blockRect = new Rect(0,48, 0,48);
        private Rect mainEnemyRect = new Rect(0,48, 0,48);
        private Rect enemyRect = new Rect(0,48, 0,48);
        private Rect pipeRect = new Rect(0,96,0,48*3);
        private Rect backgrounTileRect = new Rect(0,48,0,48);
        public EnemyCollisionHandler(GameState gameState,Enemy enemy) {
            collisionChecker = new CollisionDetector(enemy);
            this.enemy = enemy;
            this.gameState = gameState;
        }
        public void setSection(GameState gameState) {

//            enemies = section.getEnemies();
//            pipes = section.getPipes();
//            blocks = section.getBlocks();
//            backGroundTiles = section.getBackgroundMap().getBackGroundTiles();
        }
        public void updateSection(Section section) {
//            enemies = section.getEnemies();
//            pipes = section.getPipes();
//            blocks = section.getBlocks();
        }
        public void applyCollisionEffects(){
            // blocks effects
//            if (blocks != null) {
            //todo : improve it
            enemy.setOnTopOfBlock(false);
                mainEnemyRect.updatePosition(enemy.getWorldX(), enemy.getWorldY());
                for (Block block : gameState.getCurrentSection().getBlocks()) {
                    blockRect.updatePosition(block.getCol() * 48, block.getRow() * 48);
                    if (collisionChecker.didCollide(mainEnemyRect, blockRect)) {
                        enemy.setVX(-enemy.getVX());
                    }
                    String s = collisionChecker.returnSamePoints(mainEnemyRect,blockRect);
                    if (s.equals("DOWN")) {
                        enemy.setOnTopOfBlock(true);
//                    // todo: improve it too
                        enemy.setWorldY(blockRect.getTopY()-mainEnemyRect.getHeight());
                        if (enemy.getVY() < 0) {
                            enemy.setVY(0);
                        }
                    }
                    if (s.equals("RIGHT") || s.equals("LEFT")) {
                        if (enemy.getVX() > 0){
                            enemy.setWorldX(block.getCol()*  48  - 50);
                        }
                        if (enemy.getVX() < 0){
                            enemy.setWorldX(block.getCol()*48  + 50 );
                        }
                        enemy.setVX(-enemy.getVX());
                    }
//                        handelCollision(block.getCol() * Constant.BACKGROUND_TILE_SIZE, block.getRow() *
//                                Constant.BACKGROUND_TILE_SIZE, Constant.BACKGROUND_TILE_SIZE, Constant.BACKGROUND_TILE_SIZE);
                    }
//                }
//            }
            //pipes
            mainEnemyRect.updatePosition(enemy.getWorldX(), enemy.getWorldY());
            if (gameState.getCurrentSection().getPipes() != null && enemy.getClass() != Plant.class) {
                for (Pipe pipe : gameState.getCurrentSection().getPipes()) {
                    pipeRect.updatePosition(pipe.getCol() * 48, pipe.getRow() * 48);
//                    //todo give thebiigger rect first
                    if (collisionChecker.didCollide(mainEnemyRect, pipeRect)) {
                        if (enemy.getVX() > 0){
                            enemy.setWorldX(pipe.getCol()*  48  - 50);
                        }
                        if (enemy.getVX() < 0){
                            enemy.setWorldX(pipe.getCol()*48  + 98 );
                        }
                        enemy.setVX(-enemy.getVX());
//                        handelCollision(pipe.getCol() * Constant.BACKGROUND_TILE_SIZE, pipe.getRow() *
//                                Constant.BACKGROUND_TILE_SIZE, 2 * Constant.BACKGROUND_TILE_SIZE, 3 * Constant.BACKGROUND_TILE_SIZE);
                    }
                    if (collisionChecker.returnSamePoints(mainEnemyRect,pipeRect).equals("DOWN")) {
                        enemy.setOnTopOfBlock(true);
//                    // todo: improve it too
                        enemy.setWorldY(pipeRect.getTopY()-mainEnemyRect.getHeight());
                        if (enemy.getVY() < 0) {
                            enemy.setVY(0);
                        }
                    }
                }
            }
//        enemies
            if (gameState.getCurrentSection().getEnemies() != null) {
                mainEnemyRect.updatePosition(enemy.getWorldX(), enemy.getWorldY());
                for (Enemy enemy : gameState.getCurrentSection().getEnemies()) {
                    enemyRect.updatePosition(enemy.getWorldX(), enemy.getWorldY());
                    String s = collisionChecker.returnSamePoints(mainEnemyRect,enemyRect);
                    if (collisionChecker.didCollide(mainEnemyRect, enemyRect) || s.equals("LEFT") || s.equals("RIGHT")) {
                        enemy.setVX(-enemy.getVX());
//                        handelCollision(enemy.getWorldX(), enemy.getWorldY(), Constant.BACKGROUND_TILE_SIZE, Constant.BACKGROUND_TILE_SIZE);
//                        return;
                    }
                    if (collisionChecker.returnSamePoints(mainEnemyRect,enemyRect).equals("DOWN")) {
                        enemy.setOnTopOfBlock(true);
//                    // todo: improve it too
                        enemy.setWorldY(enemyRect.getTopY()-mainEnemyRect.getHeight());
                        if (enemy.getVY() < 0) {
                            enemy.setVY(0);
                        }
                    }
                }
            }
            //background tiles todo: give a bound to background tiles
            mainEnemyRect.updatePosition(enemy.getWorldX(), enemy.getWorldY());
            for (int i = 0; i <gameState.getCurrentSection().getBackgroundMap().getBackGroundTiles().length; i++){
                for (int j = 0;j < gameState.getCurrentSection().getBackgroundMap().getBackGroundTiles()[i].length;j++) {
                    if (gameState.getCurrentSection().getBackgroundMap().getBackGroundTiles()[i][j].isSolid()){
                        backgrounTileRect.updatePosition(j*48,i*48);
                        if(collisionChecker.didCollide(mainEnemyRect, backgrounTileRect)){
                            enemy.setVX(-enemy.getVX());
//                            handelCollision(j*48,i*48,48,48);
                        }
                        if (collisionChecker.returnSamePoints(mainEnemyRect,backgrounTileRect).equals("DOWN")) {
                            enemy.setOnTopOfBlock(true);
//                    // todo: improve it too
                            enemy.setWorldY(backgrounTileRect.getTopY()-mainEnemyRect.getHeight());
                            if (enemy.getVY() < 0) {
                                enemy.setVY(0);
                            }
                        }
                    }
                }
            }
        }
        public void handelCollision(int itemLeftWorldX, int itemTopWorldY,int itemWidth,int itemHeight) {
            if (enemy.getVX() > 0 && itemLeftWorldX < enemy.getWorldX()+Constant.BACKGROUND_TILE_SIZE
                    && itemLeftWorldX+itemWidth  > enemy.getWorldX()+Constant.BACKGROUND_TILE_SIZE) {
                enemy.setVX(0);
            }
            if (enemy.getVX() < 0 && itemLeftWorldX < enemy.getWorldX()
                    && (itemLeftWorldX+itemWidth > enemy.getWorldX())) {
                enemy.setVX(0);
            }
            if (enemy.getVY() > 0 && itemTopWorldY+itemHeight > enemy.getWorldY() &&
                    itemTopWorldY < enemy.getWorldY()) {
                enemy.setVY(-enemy.getVY());
//                if (entity.isDuringJump()){
//                    entity.setDuringJump(false);
//                }
            }
            if (enemy.getVY() < 0 && itemTopWorldY < enemy.getWorldY()+Constant.BACKGROUND_TILE_SIZE &&
                    itemTopWorldY+itemHeight > enemy.getWorldY()+Constant.BACKGROUND_TILE_SIZE) {
                enemy.setVY(0);
                enemy.setWorldY(itemTopWorldY - Constant.BACKGROUND_TILE_SIZE);
//                entity.setCameraY(itemTopWorldY-Constant.BACKGROUND_TILE_SIZE);
//                if (entity.isDuringJump()){
//                    player.setDuringJump(false);
//                }
            }
        }


}
