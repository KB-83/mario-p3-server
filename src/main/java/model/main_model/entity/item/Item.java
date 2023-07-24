package model.main_model.entity.item;

import com.fasterxml.jackson.annotation.JsonIgnore;
import controller.gamelogic.itemlogic.ItemController;
import model.main_model.entity.Entity;

public abstract class Item extends Entity {
    @JsonIgnore
    private boolean isLock;
    @JsonIgnore
    private ItemController itemController;
    public Item() {
        isLock = true;
        setOnTopOfBlock(true);
        setItemController(new ItemController(this));
    }

    public ItemController getItemController() {
        return itemController;
    }

    public void setItemController(ItemController itemController) {
        this.itemController = itemController;
    }

    public boolean isLock() {
        return isLock;
    }

    public void setLock(boolean lock) {
        isLock = lock;
    }

}
