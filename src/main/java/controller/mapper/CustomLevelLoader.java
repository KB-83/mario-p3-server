package controller.mapper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import controller.gamelogic.backgroundmaplogic.BackGroundMapGenerator;
import model.main_model.backgroundobject.CheckPoint;
import model.main_model.backgroundobject.block.*;
import model.main_model.backgroundobject.pipe.*;
import model.main_model.entity.enemy.*;
import model.main_model.entity.item.*;
import model.main_model.levelstructure.Level;
import model.main_model.levelstructure.Section;
import model.main_model.levelstructure.TeleSection;
import util.Constant;

import java.io.IOException;
import java.util.ArrayList;

public class CustomLevelLoader extends JsonDeserializer<Level> {
        @Override
        public Level deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
                throws IOException, JsonProcessingException {
            JsonNode rootNode = jsonParser.getCodec().readTree(jsonParser);

            Level level = new Level();
            Section[] sections = null;

            JsonNode sectionsNode = rootNode.get("sections");
            if (sectionsNode != null && sectionsNode.isArray()) {
                sections = new Section[sectionsNode.size()];
                int index = 0;
                for (JsonNode sectionNode : sectionsNode) {
                    Section section = createSection(sectionNode);
                    setSectionPlantEnemies(section);
                    sections[index++] = section;
                }
            }
            level.setSections(sections);

            return level;
        }
        private Section createSection(JsonNode sectionNode) {
            Section section = new Section();

            section.setLength(sectionNode.get("length").asInt());
            section.setTime(sectionNode.get("time").asInt());
            section.setBackgroundMap(BackGroundMapGenerator.retrunBackgroundMap(section.getLength(), 15));
            JsonNode checkPointNode = sectionNode.get("checkPoint");
            CheckPoint checkPoint = null;
            if (checkPointNode != null) {
                checkPoint = creatCheckPoint(checkPointNode);
            }
            section.setCheckPoint(checkPoint);

            Block[] blocks = null;
            JsonNode blocksNode = sectionNode.get("blocks");
            if (blocksNode != null && blocksNode.isArray()) {
                blocks = new Block[blocksNode.size()];
                int blockIndex = 0;
                for (JsonNode blockNode : blocksNode) {
                    Block block = createBlock(blockNode);
                    blocks[blockIndex++] = block;
                }
            }
            section.setBlocks(blocks);

            // Deserialize enemies array
            Enemy[] enemies = null;
            JsonNode enemiesNode = sectionNode.get("enemies");
            if (enemiesNode != null && enemiesNode.isArray()) {
                enemies = new Enemy[enemiesNode.size()];
                int enemyIndex = 0;
                for (JsonNode enemyNode : enemiesNode) {
                    Enemy enemy = createEnemy(enemyNode);
                    enemies[enemyIndex++] = enemy;
                }
            }
            section.setEnemies(enemies);

            // Deserialize pipes array

            Pipe[] pipes = null;
            JsonNode pipesNode = sectionNode.get("pipes");
            if (pipesNode != null && pipesNode.isArray()) {
                pipes = new Pipe[pipesNode.size()];
                int pipeIndex = 0;
                for (JsonNode pipeNode : pipesNode) {
                    Pipe pipe = createPipe(pipeNode,section);
                    pipes[pipeIndex++] = pipe;
                }
            }
            section.setPipes(pipes);

            // Deserialize spawnPipe
            createItemArray(section, sectionNode);


            return section;
        }
        private TeleSection creatTeleSection(JsonNode sectionNode, Section upperSection)   {
        Section section = createSection(sectionNode);
        setSectionPlantEnemies(section);
        TeleSection teleSection = new TeleSection();
        teleSection.setSection(upperSection);
        teleSection.setCheckPoint(section.getCheckPoint());
        teleSection.setPipes(section.getPipes());
        teleSection.setBlocks(section.getBlocks());
        teleSection.setEnemies(section.getEnemies());
        teleSection.setItems(section.getItems());
        teleSection.setLength(section.getLength());
        teleSection.setBackgroundMap(section.getBackgroundMap());
        teleSection.setTime(section.getTime());
        teleSection.setSpawnPipe(section.getSpawnPipe());
        Pipe spawnPipe = null;
        JsonNode spawnPipeNode = sectionNode.get("spawnPipe");
        if (spawnPipeNode != null) {
            spawnPipe = createSpawnPipeAndAddToPipes(spawnPipeNode,teleSection);
        }
        teleSection.setSpawnPipe(spawnPipe);//todo : it is not correct
        return teleSection;
    }
    private CheckPoint creatCheckPoint(JsonNode checkPointNode){
            CheckPoint checkPoint = new CheckPoint();
            checkPoint.setCol(checkPointNode.get("col").asInt());
            checkPoint.setRow(checkPointNode.get("row").asInt());
            checkPoint.setSaved(checkPointNode.get("isSaved").asBoolean());
            return checkPoint;

    }
        private void createItemArray(Section section,JsonNode sectionNode) {
            //items
            Item[] items = null;
            JsonNode itemsNode = sectionNode.get("item");
            if (itemsNode != null && itemsNode.isArray()) {
                items = new Item[itemsNode.size()];
                int itemIndex = 0;
                for (JsonNode itemNode : itemsNode) {
                    Item item = createItem(itemNode.textValue());
                    item.setWorldX(itemNode.get("x").asInt() * Constant.BACKGROUND_TILE_SIZE);
                    item.setWorldY(((Constant.PANEL_ROWS-Constant.GROUND_BLOCKS-1)-itemsNode.get("y").asInt())*Constant.BACKGROUND_TILE_SIZE);
                    items[itemIndex++] = item;
                }
            }
            //blocks item
            ArrayList<Item> items1 = new ArrayList<>();
            for (Block block : section.getBlocks()) {
                if(block.getItem() != null) {
                    block.getItem().setWorldX(block.getCol()*Constant.BACKGROUND_TILE_SIZE);
                    block.getItem().setWorldY(block.getRow()* Constant.BACKGROUND_TILE_SIZE);
                    items1.add(block.getItem());
                }
            }
            int finalItemSize = 0;
            if (items1 != null){
                finalItemSize += items1.size();
            }
            if (items != null){
                finalItemSize += items.length;
            }
            Item[] finalItems = new Item[finalItemSize];
            int index = 0;
            if (items != null) {
                for (Item item : items) {
                    finalItems[index] = item;
                    index++;
                }
            }
            if (items1 != null) {
                for (Item item : items1) {
                    finalItems[index] = item;
                    index++;
                }
            }
            section.setItems(finalItems);
        }
        private Item createItem(String itemType) {
            Item item;
            switch (itemType) {
                case "STAR":
                    item = new Star();
                    break;

                case "COIN":
                    item = new Coin();
                    break;

                case "MUSHROOM":
                    item = new Mushroom();
                    break;
                case "FLOWER":
                    item = new Flower();
                    break;
                default:
                    item = new Coin();
                    break;
            }
            return item;
        }
        private Block createBlock(JsonNode blockNode) {
        String type = blockNode.get("type").asText();

        Block block;
        switch (type) {
            case "QUESTION":
                block = new QuestionBlock();
                break;

            case "SIMPLE":
                block = new SimpleBlock();
                break;

            // Add more cases for other block types
//               SLIME/FIREBAR
            case "COIN":
                block = new CoinBlock();
                block.setItem(createItem("COIN"));
                break;

            case "COINS":
                block = new FullCoinBlock();
                break;
            case "EMPTY":
                block = new EmptyBlock();
                break;
            default:
                block = new SimpleBlock();
                break;
        }
        // todo : set it as col and row then
        block.setCol(blockNode.get("x").asInt());
        block.setRow((Constant.PANEL_ROWS-Constant.GROUND_BLOCKS-1)-blockNode.get("y").asInt());

        JsonNode itemNode = blockNode.get("item");
        if (itemNode != null && itemNode.isTextual()) {
            Item item = createItem(itemNode.asText());
            block.setItem(item);
        }

        return block;
    }
        private Pipe createPipe(JsonNode pipeNode,Section upperSection) {
        String type = pipeNode.get("type").asText();

        Pipe pipe;
        Plant plant = null;
        switch (type) {
            case "SIMPLE":
                pipe = new SimplePipe();
                break;

            case "TELE_PIRANHA":
                pipe = new TelePlantPipe();
                plant = new Plant();
                ((TelePlantPipe)pipe).setPlant(plant);
                JsonNode sectionNode = pipeNode.get("section");
                if (sectionNode != null) {
                    TeleSection teleSection = creatTeleSection(sectionNode,upperSection);
                    ((TelePlantPipe) pipe).setTeleSection(teleSection);
                }
                break;
            // todo : initialize this pipes
            case "TELE_SIMPLE":
                pipe = new SimpleTelePipe();
                sectionNode = pipeNode.get("section");
                if (sectionNode != null) {
                    TeleSection teleSection = creatTeleSection(sectionNode,upperSection);
                    ((SimpleTelePipe) pipe).setTeleSection(teleSection);
                }
                break;

            case "PIRANHA_TRAP":
                pipe = new SimplePlantPipe();
                plant = new Plant();
                ((SimplePlantPipe) pipe).setPlant(plant);

                break;
            //todo add this
            case "DECEIT":
                pipe = new DeceitPipe();
                break;
//                /PIRANHA_TRAP/TELE_SIMPLE//DECEIT

            // Add more cases for other pipe types

            default:
                pipe = new SimplePipe();
                break;
        }
        // todo : change these to col and row
        //todo 3 is pipe length
        pipe.setCol(pipeNode.get("x").asInt());
        pipe.setRow((Constant.PANEL_ROWS-Constant.GROUND_BLOCKS-3)-pipeNode.get("y").asInt());
        if (plant != null) {
            plant.setWorldX((pipe.getCol()*Constant.BACKGROUND_TILE_SIZE)+Constant.BACKGROUND_TILE_SIZE/2);
            plant.setWorldY((pipe.getRow() * Constant.BACKGROUND_TILE_SIZE) - plant.getWidth());
        }

        return pipe;
    }
        private Pipe createSpawnPipeAndAddToPipes(JsonNode pipeNode,TeleSection teleSection){
        String type = pipeNode.get("type").asText();
        Pipe pipe = null;
        Plant plant = null;
        if (type.equals("SIMPLE") || type.equals("TELE_SIMPLE")) {
            pipe = new SimpleSpawnPipe();
            ((SimpleSpawnPipe) pipe).setSection(teleSection.getSection());
        }

        else if (type.equals("PIRANHA_TRAP") || type.equals("TELE_PIRANHA")) {
            pipe = new SpawnPlantPipe();
            plant = new Plant();
            ((SpawnPlantPipe)pipe).setPlant(plant);
            ((SpawnPlantPipe) pipe).setSection(teleSection.getSection());
        }
            pipe.setCol(pipeNode.get("x").asInt());
            pipe.setRow((Constant.PANEL_ROWS - Constant.GROUND_BLOCKS - 3) - pipeNode.get("y").asInt());
            if (plant != null) {
                plant.setWorldX((pipe.getCol() * Constant.BACKGROUND_TILE_SIZE) + Constant.BACKGROUND_TILE_SIZE / 2);
                plant.setWorldY((pipe.getRow() * Constant.BACKGROUND_TILE_SIZE) - plant.getWidth());
            }
            Pipe[] pipes = null;
        if (teleSection.getPipes() != null) {
            pipes = new Pipe[teleSection.getPipes().length + 1];
            for (int i = 0; i < teleSection.getPipes().length; i++) {
                pipes[i] = teleSection.getPipes()[i];
            }
            pipes[teleSection.getPipes().length] = pipe;
        }
        else {
            pipes = new Pipe[]{pipe};
        }
        teleSection.setPipes(pipes);
        return pipe;
    }

        private Enemy createEnemy(JsonNode enemyNode) {
        String type = enemyNode.get("type").asText();
        Enemy enemy;
        switch (type) {
            case "GOOMBA":
                enemy = new Goomba();
                break;

            case "KOOPA":
                enemy = new Koopa();
                break;
            case "SPINY":
                enemy = new Spiny();
                break;

            case "BOWSER":
                enemy = new Bowser();
                break;
            case "NUKEBIRD":
                enemy = new NukeBird();
                break;

            default:
                enemy = new Goomba();
                break;
        }

        enemy.setWorldX(enemyNode.get("x").asInt() * Constant.BACKGROUND_TILE_SIZE);
        enemy.setWorldY(((Constant.PANEL_ROWS-Constant.GROUND_BLOCKS-1)-enemyNode.get("y").asInt())*Constant.BACKGROUND_TILE_SIZE);

        return enemy;
    }
        private void setSectionPlantEnemies(Section section){
        int plantsNumber = 0;
        if(section.getPipes() != null) {
            for (Pipe pipe: section.getPipes()) {
                String s = pipe.getClass().getSimpleName();
                if(s.equals("SimplePlantPipe") || s.equals("TelePlantPipe")) {
                    plantsNumber++;
                }
            }
        }
        if(section.getEnemies() != null) {
            Enemy[] newEnemies = new Enemy[section.getEnemies().length + plantsNumber];
            for (int i = 0; i < section.getEnemies().length; i++) {
                newEnemies[i] = section.getEnemies()[i];
            }
            int i = section.getEnemies().length;
            if (section.getPipes() != null) {
                for (Pipe pipe : section.getPipes()) {
                    String s = pipe.getClass().getSimpleName();
                    if (s.equals("SimplePlantPipe")) {
                        newEnemies[i] = ((SimplePlantPipe) pipe).getPlant();
                        i++;
                    } else if (s.equals("TelePlantPipe")) {
                        newEnemies[i] = ((TelePlantPipe) pipe).getPlant();
                        i++;
                    }
                }
            }
            section.setEnemies(newEnemies);
        }
    }



}

