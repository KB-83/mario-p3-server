package model.dto.backgroundobject.block;


import model.dto.entity.ItemDTO;

public class QuestionBlockDTO extends BlockDTO {

    private ItemDTO itemDTO;

    public QuestionBlockDTO() {

    }

    public ItemDTO getItem() {
        return itemDTO;
    }

    public void setItem(ItemDTO itemDTO) {
        this.itemDTO = itemDTO;
    }
}
