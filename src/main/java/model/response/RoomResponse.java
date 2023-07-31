package model.response;

public class RoomResponse extends Response{
    private String roomToken;

    public RoomResponse() {
    }

    public RoomResponse(String roomToken) {
        this.roomToken = roomToken;
    }

    public String getRoomToken() {
        return roomToken;
    }

    public void setRoomToken(String roomToken) {
        this.roomToken = roomToken;
    }
}
