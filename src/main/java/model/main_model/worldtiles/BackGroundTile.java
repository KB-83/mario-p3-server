package model.main_model.worldtiles;

public class BackGroundTile {
    private int col,row;
    private TileNum num;
    private boolean isSolid;
    public BackGroundTile(){
    }

    public BackGroundTile(int col, int row, TileNum num, boolean isSolid) {
        this.col = col;
        this.row = row;
        this.num = num;
        this.isSolid = isSolid;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public TileNum getNum() {
        return num;
    }

    public void setNum(TileNum num) {
        this.num = num;
    }

    public boolean isSolid() {
        return isSolid;
    }

    public void setSolid(boolean solid) {
        isSolid = solid;
    }
}
