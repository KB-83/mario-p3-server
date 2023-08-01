package model.response;

import model.dto.RoomDTO;

public class RoomUpdateResponse extends Response{
    private RoomDTO roomDTO;

    public RoomUpdateResponse(RoomDTO roomDTO) {
        this.roomDTO = roomDTO;
    }

    public RoomDTO getRoomDTO() {
        return roomDTO;
    }

    public void setRoomDTO(RoomDTO roomDTO) {
        this.roomDTO = roomDTO;
    }
}
