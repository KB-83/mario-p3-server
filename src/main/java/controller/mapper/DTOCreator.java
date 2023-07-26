package controller.mapper;


import model.dto.backgroundobject.block.BlockDTO;
import model.dto.backgroundobject.pipe.PipeDTO;
import model.dto.entity.EnemyDTO;
import model.dto.entity.ItemDTO;
import model.dto.entity.PlayerDTO;
import model.dto.game.GameStateDTO;
import model.dto.game.SectionDTO;
import model.main_model.entity.player.Player;
import model.main_model.gamestrucure.GameState;

public class DTOCreator {
    private DTOCreator(){}
    public static PlayerDTO createPlayerDTO(Player player) {
        PlayerDTO dto = new PlayerDTO();
        return updatePlayerDTO(player,dto);
    }
    public static PlayerDTO updatePlayerDTO(Player player,PlayerDTO dto) {
        dto.setX(player.getWorldX());
        dto.setCameraX(player.getCameraX());
        dto.setY(player.getWorldY());
        dto.setCameraY(player.getCameraY());
        dto.setType(player.getClass().getSimpleName());
        dto.setImage(player.getImageAddress());
        dto.setHeight(player.getHeight());
        return dto;
    }

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
        if (enemyDTOS != null &&gameState.getCurrentSection().getEnemies() != null) {
            if (gameState.getCurrentSection().getEnemies() == null) {
                sectionDTO.setEnemies(null);
            }
            else {
                if (gameState.getSectionNumber() != dto.getSectionNumber() ||
                        gameState.getLevelNumber() != dto.getLevelNumber()
                        || gameState.getCurrentSection().getEnemies().length != dto.getCurrentSection().getEnemies().length) { // az shomare secction befahm va level number
                    enemyDTOS = new EnemyDTO[gameState.getCurrentSection().getEnemies().length];
                    dto.setLevelNumber(gameState.getLevelNumber());
                    dto.setSectionNumber(gameState.getSectionNumber());
                    for (int i = 0; i < enemyDTOS.length; i++) {
                        enemyDTOS[i] = new EnemyDTO();
                    }
                    sectionDTO.setEnemies(enemyDTOS);
                }

                for (int i = 0; i < enemyDTOS.length; i++) {
                    enemyDTOS[i].setType(gameState.getCurrentSection().getEnemies()[i].getClass().getSimpleName());
                    enemyDTOS[i].setX(gameState.getCurrentSection().getEnemies()[i].getWorldX());
                    enemyDTOS[i].setY(gameState.getCurrentSection().getEnemies()[i].getWorldY());
                }
            }
        }


        PipeDTO[] pipeDTOS = sectionDTO.getPipes();
        if (pipeDTOS != null || gameState.getCurrentSection().getPipes()!= null) {
            if (gameState.getCurrentSection().getPipes() == null) {
                System.out.println("76");
                sectionDTO.setPipes(null) ;
            }//todo : we need a next section method here
            else {
                if (pipeDTOS == null || gameState.getSectionNumber() != dto.getSectionNumber() || gameState.getLevelNumber() != dto.getLevelNumber()
                        || gameState.getCurrentSection().getPipes().length != dto.getCurrentSection().getPipes().length) {
                    dto.setLevelNumber(gameState.getLevelNumber());
                    dto.setSectionNumber(gameState.getSectionNumber());
                    pipeDTOS = new PipeDTO[gameState.getCurrentSection().getPipes().length];
                    for (int i = 0; i < pipeDTOS.length; i++) {
                        pipeDTOS[i] = new PipeDTO();
                    }
                    sectionDTO.setPipes(pipeDTOS);
                }
                for (int i = 0; i < pipeDTOS.length; i++) {
//            pipeDTOS[i].setType(gameState.getCurrentSection().getPipes()[i].getClass().getSimpleName());
                    pipeDTOS[i].setCol(gameState.getCurrentSection().getPipes()[i].getCol());
                    pipeDTOS[i].setRow(gameState.getCurrentSection().getPipes()[i].getRow());
                }
            }
        }

        ItemDTO[] itemDTOS = sectionDTO.getItems();
        if (itemDTOS != null) {
            if (gameState.getCurrentSection().getItems() == null) {
                sectionDTO.setItems(null);
            }
            else {
                if (gameState.getSectionNumber() != dto.getSectionNumber() || gameState.getLevelNumber() != dto.getLevelNumber()
                        || gameState.getCurrentSection().getItems().length != dto.getCurrentSection().getItems().length) {
                    dto.setLevelNumber(gameState.getLevelNumber());
                    dto.setSectionNumber(gameState.getSectionNumber());
                    itemDTOS = new ItemDTO[gameState.getCurrentSection().getItems().length];
                    for (int i = 0; i < itemDTOS.length; i++) {
                        itemDTOS[i] = new ItemDTO();
                    }
                    sectionDTO.setItems(itemDTOS);
                }

                for (int i = 0; i < itemDTOS.length; i++) {
                    itemDTOS[i].setType(gameState.getCurrentSection().getItems()[i].getClass().getSimpleName());
                    itemDTOS[i].setX(gameState.getCurrentSection().getItems()[i].getWorldX());
                    itemDTOS[i].setY(gameState.getCurrentSection().getItems()[i].getWorldY());
                    itemDTOS[i].setLock(gameState.getCurrentSection().getItems()[i].isLock());
                }
            }
        }

        BlockDTO[] blockDTOS = sectionDTO.getBlocks();
        if (blockDTOS != null) {
            if (gameState.getCurrentSection().getBlocks() == null) {
                sectionDTO.setBlocks(null);
            }
            else {
                if (gameState.getSectionNumber() != dto.getSectionNumber() || gameState.getLevelNumber() != dto.getLevelNumber()
                        || gameState.getCurrentSection().getBlocks().length != dto.getCurrentSection().getBlocks().length) {
                    dto.setLevelNumber(gameState.getLevelNumber());
                    dto.setSectionNumber(gameState.getSectionNumber());
                    blockDTOS = new BlockDTO[gameState.getCurrentSection().getBlocks().length];
                    for (int i = 0; i < blockDTOS.length; i++) {
                        blockDTOS[i] = new BlockDTO();
                    }
                    sectionDTO.setBlocks(blockDTOS);
                }

                for (int i = 0; i < blockDTOS.length; i++) {
                    blockDTOS[i].setType(gameState.getCurrentSection().getBlocks()[i].getClass().getSimpleName());
                    blockDTOS[i].setCol(gameState.getCurrentSection().getBlocks()[i].getCol());
                    blockDTOS[i].setRow(gameState.getCurrentSection().getBlocks()[i].getRow());
                }
            }
        }

        int[][] backGroundTiles = sectionDTO.getBackGroundTiles();
        if (backGroundTiles != null) {
            if (gameState.getCurrentSection().getBackgroundMap().getBackGroundTiles() == null) {
                sectionDTO.setBackGroundTiles(null);
            }
            else {
                if (gameState.getSectionNumber() != dto.getSectionNumber() || gameState.getLevelNumber() != dto.getLevelNumber()
                        || gameState.getCurrentSection().getBackgroundMap().getBackGroundTiles()[0].length != dto.getCurrentSection().getBackGroundTiles()[0].length) {
                    dto.setLevelNumber(gameState.getLevelNumber());
                    dto.setSectionNumber(gameState.getSectionNumber());
                    backGroundTiles = new int[gameState.getCurrentSection().getBackgroundMap().getBackGroundTiles().length]
                            [gameState.getCurrentSection().getBackgroundMap().getBackGroundTiles()[0].length];

                    sectionDTO.setBackGroundTiles(backGroundTiles);
                }

                for (int i = 0; i < backGroundTiles.length; i++) {
                    for (int j = 0; j < backGroundTiles[0].length; j++) {
                        backGroundTiles[i][j] = gameState.getCurrentSection().getBackgroundMap().getBackGroundTiles()[i][j].getNum().getIndex();
                    }
                }
            }
        }

        return dto;
    }
}
