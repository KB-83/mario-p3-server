package controller.gamelogic.collisionlogic;



import model.main_model.entity.Entity;
import model.main_model.gamestrucure.gameworldoption.collision.CollisionChecker;
import model.main_model.gamestrucure.gameworldoption.collision.Rect;
import util.Constant;

public class CollisionDetector implements CollisionChecker {
    private Entity entity;

    public CollisionDetector(Entity entity) {
        this.entity = entity;
    }

    @Override
        public boolean didCollide(Rect entityRect, Rect biggerRect) {
            // todo <= or <
//            if (entityRect.getTopY()/ Constant.BACKGROUND_TILE_SIZE == (biggerRect.getTopY()/Constant.BACKGROUND_TILE_SIZE) - (entityRect.getHeight()/Constant.BACKGROUND_TILE_SIZE)
//            && biggerRect.getLeftX() - entityRect.getWidth() <= entityRect.getLeftX() && biggerRect.getRightX() + entityRect.getWidth() >= entityRect.getRightX()){
//                entity.setOnTopOfBlock(true);
//                // todo: improve it too
//                entity.setWorldY(biggerRect.getTopY()-entityRect.getHeight());
//                if (entity.getVY() < 0) {
//                    entity.setVY(0);
//                }
//
//                if(entity.getClass().getSuperclass().getSimpleName().equals("Player")){
//                    ((Player) entity).setCameraY(biggerRect.getTopY()-entityRect.getHeight());
//                    if (entity.getVY() < 0) {
//                        ((Player) entity).setDuringJump(false);
//                    }
//
//                }
//            }

            if(entityRect.getLeftX() < biggerRect.getLeftX() && entityRect.getRightX() > biggerRect.getLeftX()
                    && entityRect.getTopY() < biggerRect.getTopY() && entityRect.getBottomY() > biggerRect.getTopY()){
                return true;
            }
            if(entityRect.getLeftX() < biggerRect.getLeftX() && entityRect.getRightX() > biggerRect.getLeftX()
                    && entityRect.getTopY() < biggerRect.getBottomY() && entityRect.getBottomY() > biggerRect.getBottomY()){
                return true;
            }
            if(entityRect.getLeftX() < biggerRect.getRightX() && entityRect.getRightX() > biggerRect.getRightX()
                    && entityRect.getTopY() < biggerRect.getTopY() && entityRect.getBottomY() > biggerRect.getTopY()){
                return true;
            }
            if(entityRect.getLeftX() < biggerRect.getRightX() && entityRect.getRightX() > biggerRect.getRightX()
                    && entityRect.getTopY() < biggerRect.getBottomY() && entityRect.getBottomY() > biggerRect.getBottomY()){
                return true;
            }
            if(biggerRect.getLeftX() < entityRect.getLeftX() && biggerRect.getRightX() > entityRect.getLeftX()
                && biggerRect.getTopY() < entityRect.getTopY() && biggerRect.getBottomY() > entityRect.getTopY()){
            return true;
            }
            if(biggerRect.getLeftX() < entityRect.getLeftX() && biggerRect.getRightX() > entityRect.getLeftX()
                    && biggerRect.getTopY() < entityRect.getBottomY() && biggerRect.getBottomY() > entityRect.getBottomY()){
                return true;
            }
            if(biggerRect.getLeftX() < entityRect.getRightX() && biggerRect.getRightX() > entityRect.getRightX()
                    && biggerRect.getTopY() < entityRect.getTopY() && biggerRect.getBottomY() > entityRect.getTopY()){
                return true;
            }
            if(biggerRect.getLeftX() < entityRect.getRightX() && biggerRect.getRightX() > entityRect.getRightX()
                    && biggerRect.getTopY() < entityRect.getBottomY() && biggerRect.getBottomY() > entityRect.getBottomY()){
                return true;
            }
                return false;
            }

    @Override
    public String returnSamePoints(Rect rect1, Rect relationToRect1) {
        if (rect1.getTopY()/ Constant.BACKGROUND_TILE_SIZE == (relationToRect1.getTopY()/Constant.BACKGROUND_TILE_SIZE) - (rect1.getHeight()/Constant.BACKGROUND_TILE_SIZE)
            && relationToRect1.getLeftX() - rect1.getWidth() <= rect1.getLeftX() && relationToRect1.getRightX() + rect1.getWidth() >= rect1.getRightX()){
            return "DOWN";
            }
        if (relationToRect1.getTopY()/ Constant.BACKGROUND_TILE_SIZE == (rect1.getTopY()/Constant.BACKGROUND_TILE_SIZE)
                && relationToRect1.getLeftX() - rect1.getWidth()/2 <= rect1.getLeftX() && relationToRect1.getRightX() + rect1.getWidth()/2 >= rect1.getRightX()){
            return "TOP";
        }


        if (rect1.getTopY()/Constant.BACKGROUND_TILE_SIZE == relationToRect1.getTopY()/Constant.BACKGROUND_TILE_SIZE &&
        rect1.getLeftX() < relationToRect1.getLeftX() && rect1.getRightX() > relationToRect1.getLeftX()){
            return "RIGHT";
        }
        if (rect1.getTopY()/Constant.BACKGROUND_TILE_SIZE == relationToRect1.getTopY()/Constant.BACKGROUND_TILE_SIZE &&
                rect1.getLeftX() > relationToRect1.getLeftX() && rect1.getLeftX() < relationToRect1.getRightX()){
            return "LEFT";
        }

        if (rect1.getLeftX() == relationToRect1.getLeftX()){
            if ( rect1.getTopY() == relationToRect1.getTopY()){
                return "FULL";
            }
            if (rect1.getTopY() == relationToRect1.getBottomY()) {
                return "TOP";
            }
            if (rect1.getBottomY() <= relationToRect1.getTopY() +3 && rect1.getBottomY() >= relationToRect1.getTopY() -3) {
                return "DOWN";
            }
        }
        if (rect1.getTopY() == relationToRect1.getTopY()) {
            if (rect1.getLeftX() == relationToRect1.getRightX()) {
                return "LEFT";
            }
            if (rect1.getRightX() == relationToRect1.getLeftX()) {
                return "RIGHT";
            }
        }
        return  "";
    }
}
