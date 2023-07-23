package controller.mapper;


import model.dto.backgroundobject.BackGroundTileDTO;
import model.dto.backgroundobject.block.BlockDTO;
import model.dto.backgroundobject.pipe.PipeDTO;
import model.dto.entity.enemy.EnemyDTO;
import model.dto.entity.item.ItemDTO;
import model.dto.game.GameStateDTO;
import model.dto.game.SectionDTO;
import model.main_model.gamestrucure.GameState;

public class DTOCreator {
    private DTOCreator(){}
//    public MarioDTO retrunMarioDTO() {
//        return null;
//        //inke object dto ro hey new kone khoob nist, mitoonim yek DTOgamestate ro update konim
//        //intori ke object haye oono meghdaresh ro taghir bede. im going to ask az gpt :  so yek game dto darim ke update mishe
//    }
    public static GameStateDTO createGameStateDTO(GameState gameState) {
        GameStateDTO dto = new GameStateDTO();
        dto.setCurrentSection(new SectionDTO());
        //todo : mahdoodiat dadam ke new nakonam vali akaresh moshkel dare? felan ba hamin mirim
        dto.getCurrentSection().setItems(new ItemDTO[]{});
        dto.getCurrentSection().setBlocks(new BlockDTO[]{});
        dto.getCurrentSection().setEnemies(new EnemyDTO[]{});
        dto.getCurrentSection().setPipes(new PipeDTO[]{});
        dto.getCurrentSection().setBackGroundTiles(new int[][]{{}});
        return updateGameStateDTO(dto,gameState);
    }
    public static GameStateDTO updateGameStateDTO(GameStateDTO dto, GameState gameState) {
        SectionDTO sectionDTO = dto.getCurrentSection();
        sectionDTO.setTime(sectionDTO.getTime());
        sectionDTO.setLength(sectionDTO.getLength());
        EnemyDTO[] enemyDTOS = sectionDTO.getEnemies();
        if (enemyDTOS.length != gameState.getCurrentSection().getEnemies().length) {
            enemyDTOS = new EnemyDTO[gameState.getCurrentSection().getEnemies().length];
            for (int i = 0; i < enemyDTOS.length;i++) {
                enemyDTOS[i] = new EnemyDTO();
            }
            sectionDTO.setEnemies(enemyDTOS);
        }

        for (int i = 0; i < gameState.getCurrentSection().getEnemies().length; i++) {
            enemyDTOS[i].setType(gameState.getCurrentSection().getEnemies()[i].getClass().getSimpleName());
            enemyDTOS[i].setX(gameState.getCurrentSection().getEnemies()[i].getWorldX());
            enemyDTOS[i].setY(gameState.getCurrentSection().getEnemies()[i].getWorldY());
        }


        PipeDTO[] pipeDTOS = sectionDTO.getPipes();
        if (pipeDTOS.length != gameState.getCurrentSection().getPipes().length) {
            pipeDTOS = new PipeDTO[gameState.getCurrentSection().getPipes().length];
            for (int i = 0; i < pipeDTOS.length;i++) {
                pipeDTOS[i] = new PipeDTO();
            }
            sectionDTO.setPipes(pipeDTOS);
        }
        for (int i = 0; i < gameState.getCurrentSection().getPipes().length; i++) {
//            pipeDTOS[i].setType(gameState.getCurrentSection().getPipes()[i].getClass().getSimpleName());
            pipeDTOS[i].setCol(gameState.getCurrentSection().getPipes()[i].getCol());
            pipeDTOS[i].setRow(gameState.getCurrentSection().getPipes()[i].getRow());
        }

        ItemDTO[] itemDTOS = sectionDTO.getItems();
        if (itemDTOS.length != gameState.getCurrentSection().getItems().length) {
            itemDTOS = new ItemDTO[gameState.getCurrentSection().getItems().length];
            for (int i = 0; i < itemDTOS.length;i++) {
                itemDTOS[i] = new ItemDTO();
            }
            sectionDTO.setItems(itemDTOS);
        }

        for (int i = 0; i < gameState.getCurrentSection().getEnemies().length; i++) {
            itemDTOS[i].setType(gameState.getCurrentSection().getItems()[i].getClass().getSimpleName());
            itemDTOS[i].setX(gameState.getCurrentSection().getItems()[i].getWorldX());
            itemDTOS[i].setY(gameState.getCurrentSection().getItems()[i].getWorldY());
        }

        BlockDTO[] blockDTOS = sectionDTO.getBlocks();
        if (blockDTOS.length != gameState.getCurrentSection().getBlocks().length) {
            blockDTOS = new BlockDTO[gameState.getCurrentSection().getBlocks().length];
            for (int i = 0; i < blockDTOS.length;i++) {
                blockDTOS[i] = new BlockDTO();
            }
            sectionDTO.setBlocks(blockDTOS);
        }

        for (int i = 0; i < gameState.getCurrentSection().getEnemies().length; i++) {
            blockDTOS[i].setType(gameState.getCurrentSection().getBlocks()[i].getClass().getSimpleName());
            blockDTOS[i].setCol(gameState.getCurrentSection().getBlocks()[i].getCol());
            blockDTOS[i].setRow(gameState.getCurrentSection().getBlocks()[i].getRow());
        }

        int[][] backGroundTiles = sectionDTO.getBackGroundTiles();
        if (backGroundTiles[0].length != gameState.getCurrentSection().getBackgroundMap().getBackGroundTiles()[0].length) {
            backGroundTiles = new int[gameState.getCurrentSection().getBackgroundMap().getBackGroundTiles().length]
                    [gameState.getCurrentSection().getBackgroundMap().getBackGroundTiles()[0].length];

            sectionDTO.setBackGroundTiles(backGroundTiles);
        }

        for (int i = 0; i < backGroundTiles.length; i++) {
            for (int j = 0; j < backGroundTiles[0].length;j++) {
                backGroundTiles[i][j] = gameState.getCurrentSection().getBackgroundMap().getBackGroundTiles()[i][j].getNum().getIndex();
            }
        }

        return dto;
    }
}
