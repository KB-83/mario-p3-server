package model.response;

import model.dto.game.GameStateDTO;

public class GameStartResponse extends Response{
    private GameStateDTO gameStateDTO;
    public GameStartResponse() {
    }

    public GameStartResponse(GameStateDTO gameStateDTO) {
        this.gameStateDTO = gameStateDTO;
    }

    public GameStateDTO getGameStateDTO() {
        return gameStateDTO;
    }

    public void setGameStateDTO(GameStateDTO gameStateDTO) {
        this.gameStateDTO = gameStateDTO;
    }
}
