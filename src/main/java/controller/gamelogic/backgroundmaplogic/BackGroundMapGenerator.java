package controller.gamelogic.backgroundmaplogic;

import model.main_model.worldtiles.BackGroundTile;
import model.main_model.worldtiles.BackgroundMap;
import model.main_model.worldtiles.TileNum;
import util.Constant;

public class BackGroundMapGenerator {
    private BackGroundMapGenerator() {
    }
    public static BackgroundMap retrunBackgroundMap(int cols, int rows){
        // my gameRows
        rows = 15;
        if (cols <= Constant.PANEL_WIDTH/Constant.BACKGROUND_TILE_SIZE) {
            cols = (Constant.PANEL_WIDTH/Constant.BACKGROUND_TILE_SIZE) + 1;
        }
        BackgroundMap backgroundMap = new BackgroundMap();
        BackGroundTile[][] backGroundTiles = new BackGroundTile[rows][cols];
        int col = 0;
        int row = 0;

        while (col < cols && row < rows){
//            || (col <= 26 && col>22)
            if (row < rows - 3 || (col <= 26 && col>22)) {
                BackGroundTile backGroundTile = new BackGroundTile(col,row, TileNum.Sky,false);
                backGroundTiles[row][col] = backGroundTile;
            }
            else  {
                BackGroundTile backGroundTile = new BackGroundTile(col,row,TileNum.Tile1,true);
                backGroundTiles[row][col] = backGroundTile;
            }
            col++;
            if(col >= cols) {
                col = 0;
                row++;

            }

        }
        backgroundMap.setBackGroundTiles(backGroundTiles);
        return backgroundMap;
    }
}
