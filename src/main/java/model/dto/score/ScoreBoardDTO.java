package model.dto.score;

import java.util.List;

public class ScoreBoardDTO {
    private List<ScoreDTO> scoreDTOS;

    public ScoreBoardDTO() {
    }

    public List<ScoreDTO> getScoreDTOS() {
        return scoreDTOS;
    }

    public void setScoreDTOS(List<ScoreDTO> scoreDTOS) {
        this.scoreDTOS = scoreDTOS;
    }
}
