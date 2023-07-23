package controller.gamelogic.collisionlogic;

public interface CollisionHandler {
    public void checkBlocksCollision();

    public void checkPipesCollision();
    public void checkEnemiesCollision();

    public void checkBackgroundTilesCollision();
    public void checkPlayerCollision();
}
