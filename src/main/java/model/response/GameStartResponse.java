package model.response;

import model.dto.entity.PlayerDTO;
import model.dto.game.GameStateDTO;

public class GameStartResponse extends Response{
    private GameStateDTO gameStateDTO;
    private PlayerDTO playerDTO;
    public GameStartResponse() {
    }

    public GameStartResponse(GameStateDTO gameStateDTO,PlayerDTO playerDTO) {
        this.gameStateDTO = gameStateDTO;
        this.playerDTO = playerDTO;
    }

    public GameStateDTO getGameStateDTO() {
        return gameStateDTO;
    }

    public void setGameStateDTO(GameStateDTO gameStateDTO) {
        this.gameStateDTO = gameStateDTO;
    }

    public PlayerDTO getPlayerDTO() {
        return playerDTO;
    }

    public void setPlayerDTO(PlayerDTO playerDTO) {
        this.playerDTO = playerDTO;
    }
}
