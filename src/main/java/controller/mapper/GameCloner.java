package controller.mapper;

import controller.gamelogic.backgroundmaplogic.BackGroundMapGenerator;
import model.main_model.backgroundobject.CheckPoint;
import model.main_model.backgroundobject.block.*;
import model.main_model.backgroundobject.pipe.*;
import model.main_model.entity.enemy.*;
import model.main_model.entity.item.*;
import model.main_model.gamestrucure.Game;
import model.main_model.levelstructure.Section;
import model.main_model.levelstructure.TeleSection;
import model.main_model.levelstructure.Level;


public class GameCloner {
    private static Item[] items;
    private GameCloner() {}
    public static Game cloneGame(Game game) {
        if (game == null){
            return null;
        }
        Level[] levels = new Level[game.getLevels().length];
        for (int i = 0;i < game.getLevels().length; i++){
            levels[i] = cloneLevel(game.getLevels()[i]);
        }
        Game game1 = new Game();
        game1.setHearts(game.getHearts());
        game1.setName(game.getName());
        game1.setMarioState(game.getMarioState());
        game1.setLevels(levels);
        return game1;
    }
    private static Level cloneLevel(Level level){
        if (level == null) {
            return null;
        }
        Section[] sections = new Section[level.getSections().length];
        for (int i = 0;i < level.getSections().length;i++) {
            sections[i] = cloneSection(level.getSections()[i],null);
        }
        Level level1 = new Level();
        level1.setSections(sections);
        return level1;
    }
    private static Section cloneSection(Section section,Section upperSection){
        if (section == null) {
            return null;
        }
        //tele section and section
        Section section1 = null;
        switch (section.getClass().getSimpleName()){
            case "Section" :
                section1 = new Section();
                break;
            case "TeleSection" :
                section1 = new TeleSection();
                ((TeleSection)section1).setSection(((TeleSection)section).getSection());
                section1.setSpawnPipe(clonePipe(section.getSpawnPipe(),upperSection));
//                String s = section1.getSpawnPipe().getClass().getSimpleName();
//                System.out.println("61 game cloner  "+  s);
//                switch (s){
//                    case "SimpleSpawnPipe":
//                        ((SimpleSpawnPipe)section1.getSpawnPipe()).setSection(section1);
//                        break;
//                    case "SpawnPlantPipe":
//                        ((SpawnPlantPipe)section1.getSpawnPipe()).setSection(section1);
//                        break;
//                }
                break;
        }
        section1.setTime(section.getTime());
        section1.setLength(section.getLength());
        section1.setCheckPoint(cloneCheckPoint(section.getCheckPoint()));
//        section1.setBackgroundMap(new BackgroundMap().loadMap(););
        section1.setBackgroundMap(BackGroundMapGenerator.retrunBackgroundMap(section.getLength(),15));
//        System.out.println(section.getItems());

        if (section.getPipes() != null) {
            Pipe[] pipes = new Pipe[section.getPipes().length];
            for (int i = 0; i < section.getPipes().length; i++) {
                if (section.getSpawnPipe() != null && section.getSpawnPipe().equals(section.getPipes()[i])){
                    pipes[i] = section1.getSpawnPipe();
                }
                else {
                    pipes[i] = clonePipe(section.getPipes()[i], section1);
                }
            }
            section1.setPipes(pipes);
        }

        if (section.getBlocks() != null) {
            Block[] blocks = new Block[section.getBlocks().length];
            for (int i = 0; i < section.getBlocks().length; i++) {
                blocks[i] = cloneBlock(section.getBlocks()[i]);
            }
            section1.setBlocks(blocks);
        }
        //todo : make this and block items same
        if (section.getItems() != null) {
            Item[] items = new Item[section.getItems().length];
            for (int i = 0; i < section.getItems().length; i++) {
                for (int j = 0 ; j < section.getBlocks().length ; j++){
                    if (section.getBlocks()[j].getItem() != null && section.getBlocks()[j].getItem().equals(section.getItems()[i])){
                        items[i] = section1.getBlocks()[j].getItem();
                        break;
                    }
                }
                if (items[i] == null) {
                    items[i] = cloneItem(section.getItems()[i]);
                }
            }
            section1.setItems(items);
        }

        if (section.getEnemies() != null) {
            Enemy[] enemies = new Enemy[section.getEnemies().length];
            for (int i = 0; i < section.getEnemies().length; i++) {
                if (section.getEnemies()[i].getClass() == Plant.class) {
                    for (int j = 0; j < section.getPipes().length; j++) {
                        Pipe pipe = section.getPipes()[j];
                        if (pipe.getClass() == SimplePlantPipe.class){
                            if (((SimplePlantPipe) pipe).getPlant().equals(section.getEnemies()[i])){
                                enemies[i] = ((SimplePlantPipe)section1.getPipes()[j]).getPlant();
                                break;
                            }
                        }
                        else if (pipe.getClass() == SpawnPlantPipe.class){
                            enemies[i] = ((SpawnPlantPipe)section1.getPipes()[j]).getPlant();
                            break;
                        }
                        else if (pipe.getClass() == TelePlantPipe.class){
                            enemies[i] = ((TelePlantPipe)section1.getPipes()[j]).getPlant();
                            break;
                        }
                    }
                }
                if(enemies[i] == null) {
                    enemies[i] = cloneEnemy(section.getEnemies()[i]);
                }
            }
            section1.setEnemies(enemies);
        }


        return section1;
    }
    private static Enemy cloneEnemy(Enemy enemy) {
        if (enemy == null) {
            return null;
        }
        Enemy enemy1 = null;
        switch (enemy.getClass().getSimpleName()) {
            case "Goomba":
                enemy1 = new Goomba();
                break;
            case "Koopa":
                enemy1 = new Koopa();
                break;
            case "Spiny":
                enemy1 = new Spiny();
                break;
            case "Bowser":
                enemy1 = new Bowser();
                break;
            case "Plant":
                enemy1 = new Plant();
        }
        enemy1.setWorldX(enemy.getWorldX());
        enemy1.setWorldY(enemy.getWorldY());
        enemy1.setVX(enemy.getVX());
        return  enemy1;
    }
    private static Pipe clonePipe(Pipe pipe,Section upperSection){
        if (pipe == null) {
            return null;
        }
        Pipe pipe1 = null;
        switch (pipe.getClass().getSimpleName()){
            case "SimplePipe":
                pipe1 = new SimplePipe();
                break;
            case "SimplePlantPipe":
                pipe1 = new SimplePlantPipe();
                ((SimplePlantPipe)pipe1).setPlant((Plant) cloneEnemy(((SimplePlantPipe)pipe).getPlant()));
                break;
            case "TelePlantPipe":
                pipe1 = new TelePlantPipe();
                ((TelePlantPipe)pipe1).setPlant((Plant) cloneEnemy(((TelePlantPipe)pipe).getPlant()));
                ((TelePlantPipe)pipe1).setTeleSection((TeleSection) cloneSection(((TelePlantPipe)pipe).getTeleSection(),upperSection));

                break;
            case "SimpleTelePipe":
                pipe1 = new SimpleTelePipe();
                ((SimpleTelePipe)pipe1).setTeleSection((TeleSection) cloneSection(((SimpleTelePipe)pipe).getTeleSection(),upperSection));
                break;
            case "SimpleSpawnPipe" :
                pipe1 = new SimpleSpawnPipe();
                if (upperSection != null) {
                    ((SimpleSpawnPipe) pipe1).setSection(upperSection);
                }
                break;
            case "SpawnPlantPipe" :
                pipe1 = new SpawnPlantPipe();
                ((SpawnPlantPipe)pipe1).setPlant((Plant) cloneEnemy(((SpawnPlantPipe)pipe).getPlant()));
                if (upperSection != null) {
                    ((SpawnPlantPipe) pipe1).setSection(upperSection);
                }
                break;

    }
        pipe1.setCol(pipe.getCol());
        pipe1.setRow(pipe.getRow());
        return pipe1;
    }
    private static CheckPoint cloneCheckPoint(CheckPoint checkPoint){
        if (checkPoint == null) {
            return null;
        }
        CheckPoint checkPoint1 = new CheckPoint();
        checkPoint1.setCol(checkPoint.getCol());
        checkPoint1.setRow(checkPoint.getRow());
        checkPoint1.setSaved(checkPoint.getSaved());
        checkPoint1.setSectionNum(checkPoint.getSectionNum());
        return checkPoint1;
    }
    private static Block cloneBlock(Block block) {
        if (block == null){
            return null;
        }
        Block block1 = null;
        switch (block.getClass().getSimpleName()) {
            case "SimpleBlock":
                block1 = new SimpleBlock();
                break;
            case "QuestionBlock":
                block1 = new QuestionBlock();
                break;
            case "CoinBlock":
                block1 = new CoinBlock();
                break;
            case "FullCoinBlock":
                block1 = new FullCoinBlock();
                break;
        }
        if (block1 == null){
            return null;
        }
        block1.setItem(cloneItem(block.getItem()));
        block1.setCol(block.getCol());
        block1.setRow(block.getRow());
        return block1;
        }
    private static Item cloneItem(Item item) {
        if (item == null) {
            return null;
        }
        Item item1 = null;
        switch (item.getClass().getSimpleName()){
            case "Flower":
                item1 = new Flower();
                break;
            case "Star":
                item1 = new Star();
                break;
            case "Coin":
                item1 = new Coin();
                break;
            case "Mushroom":
                item1 = new Mushroom();
                break;

        }
        item1.setWidth(item.getWidth());
        item1.setHeight(item.getHeight());
        item1.setWorldX(item.getWorldX());
//        item1.setItemCollisionHandler(item.getItemCollisionHandler());
        item1.setImageAddress(item.getImageAddress());
        item1.setWorldY(item.getWorldY());
        item1.setLock(item.isLock());
        return item1;
    }
}
