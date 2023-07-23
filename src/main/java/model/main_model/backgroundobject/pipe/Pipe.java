package model.main_model.backgroundobject.pipe;

public abstract class Pipe {
    private int col;
    private int row;

    public Pipe() {
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
}
