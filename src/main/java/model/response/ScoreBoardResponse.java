package model.response;

import model.dto.score.ScoreBoardDTO;

public class ScoreBoardResponse extends Response{
    private ScoreBoardDTO scoreBoardDTO;

    public ScoreBoardResponse() {
    }

    public ScoreBoardResponse(ScoreBoardDTO scoreBoardDTO) {
        this.scoreBoardDTO = scoreBoardDTO;
    }

    public ScoreBoardDTO getScoreBoardDTO() {
        return scoreBoardDTO;
    }

    public void setScoreBoardDTO(ScoreBoardDTO scoreBoardDTO) {
        this.scoreBoardDTO = scoreBoardDTO;
    }
}
