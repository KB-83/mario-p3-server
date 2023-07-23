package model.main_model.worldtiles;

public enum TileNum {
    Sky(0),
    Tile1(1),
    Tile2(2),
    Tile3(3),
    Tile4(4),
    NightSky(5);
    private int index;
    TileNum(int index) {
        this.index = index;
    }
    public int getIndex() {
        return index;
    }
    public static TileNum getByIndex(int index) {
        for (TileNum tileNum : TileNum.values()) {
            if (tileNum.getIndex() == index) {
                return tileNum;
            }
        }
        throw new IllegalArgumentException("Invalid index: " + index);
    }

}
