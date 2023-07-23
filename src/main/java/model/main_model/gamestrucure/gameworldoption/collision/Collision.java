package model.main_model.gamestrucure.gameworldoption.collision;

public class Collision {
    private CollisionChecker collisionChecker;
    public Collision () {
        collisionChecker = new CollisionChecker() {
            @Override
            public boolean didCollide(Rect rect1, Rect rect2) {
                return false;
            }

            @Override
            public String returnSamePoints(Rect rect1, Rect relationToRect1) {
                return "";
            }
        };
    }

}