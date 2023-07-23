package model.main_model.gamestrucure.gameworldoption.collision;


public interface CollisionChecker {
    public boolean didCollide(Rect rect1, Rect rect2);
    public String returnSamePoints(Rect rect1 , Rect relationToRect1);
}
