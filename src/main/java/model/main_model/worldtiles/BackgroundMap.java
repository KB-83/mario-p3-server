package model.main_model.worldtiles;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BackgroundMap {
    private BackGroundTile[][] backGroundTiles;
    public BackgroundMap(){

    }
    public void loadMap(int levelNum ,int sectionNum,int sectionCols,int sectionRows){
        backGroundTiles = new BackGroundTile[sectionRows][sectionCols];

        try {

            InputStream is = getClass().getResourceAsStream("/database/maps/map"+levelNum+sectionNum+".txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < sectionCols && row < sectionRows){

                String line = br.readLine();

                while (col < sectionCols) {

                    String numbers[] = line.split(" ");
                    int num;

                    num = Integer.parseInt(numbers[col]);
                    BackGroundTile backGroundTile = new BackGroundTile(col,row,TileNum.getByIndex(num),(TileNum.getByIndex(num) != TileNum.NightSky && TileNum.getByIndex(num) != TileNum.Sky));
                    backGroundTiles[row][col] = backGroundTile;
                    col++;
                }
                if(col >= sectionCols) {

                    col = 0;
                    row++;

                }
            }
            br.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public BackGroundTile[][] getBackGroundTiles() {
        return backGroundTiles;
    }

    public void setBackGroundTiles(BackGroundTile[][] backGroundTiles) {
        this.backGroundTiles = backGroundTiles;
    }
}
