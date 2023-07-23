package model.dto.game;

import model.dto.entity.player.PlayerDTO;

import java.util.ArrayList;

public class GameStateDTO {
    private SectionDTO currentSection;
    private ArrayList<PlayerDTO> playerDTOS;

    public GameStateDTO() {
    }

    public SectionDTO getCurrentSection() {
        return currentSection;
    }

    public void setCurrentSection(SectionDTO currentSection) {
        this.currentSection = currentSection;
    }

    public ArrayList<PlayerDTO> getPlayerDTOS() {
        return playerDTOS;
    }

    public void setPlayerDTOS(ArrayList<PlayerDTO> playerDTOS) {
        this.playerDTOS = playerDTOS;
    }
}
