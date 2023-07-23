package model.main_model.backgroundobject.block;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import model.main_model.entity.item.Item;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CoinBlock.class, name = "COIN"),
        @JsonSubTypes.Type(value = EmptyBlock.class, name = "EMPTY"),
        @JsonSubTypes.Type(value = FullCoinBlock.class, name = "COINS"),
        @JsonSubTypes.Type(value = QuestionBlock.class, name = "QUESTION"),
        @JsonSubTypes.Type(value = SimpleBlock.class, name = "SIMPLE"),
        @JsonSubTypes.Type(value = SlimeBlock.class, name = "SLIME"),
        //FIREBAR

})

public abstract class Block {
    private int col;
    private int row;
    private Item item;

//    میتونید تو سوپرکلس بذارید و یه مقدار دیفالت یا نال بدید و اگه تو ساب کلس مقدارش داده شد تو کانستراکتور، تغییر داده بشه
//    یا میتونید کانستراکتور های متفاوتی داشته باشید

    public Block() {
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

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
