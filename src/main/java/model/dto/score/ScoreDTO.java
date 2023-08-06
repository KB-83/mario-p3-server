package model.dto.score;

public class ScoreDTO {
    private String place,username,grade,score;

    public ScoreDTO() {
    }

    public ScoreDTO(String place, String username, String score,String grade) {
        this.place = place;
        this.username = username;
        this.grade = grade;
        this.score = score;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
