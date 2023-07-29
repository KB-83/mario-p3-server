package model.response;

public class GameOverResponse extends Response{
    private int score;
    private int diamond;
    private String massage;

    public GameOverResponse() {
    }

    public GameOverResponse(int score, int diamond, String massage) {
        this.score = score;
        this.diamond = diamond;
        this.massage = massage;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getDiamond() {
        return diamond;
    }

    public void setDiamond(int diamond) {
        this.diamond = diamond;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }
}
