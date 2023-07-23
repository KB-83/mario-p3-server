package model.main_model.backgroundobject.block;


import model.main_model.entity.item.Item;

public class QuestionBlock extends Block{
//    @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "item")
//    @JsonSubTypes({
//            @JsonSubTypes.Type(value = Coin.class, name = "STAR"),
//            // other subtypes
//    })
    private Item item;

    public QuestionBlock() {

    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
