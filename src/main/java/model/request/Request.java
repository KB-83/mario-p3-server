package model.request;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import controller.ClientController;
import controller.connection.RequestVisitor;
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value = MarathonRequest.class),
        @JsonSubTypes.Type(value = LoginRequest.class),
        @JsonSubTypes.Type(value = SignInRequest.class),
        @JsonSubTypes.Type(value = MakeFriend.class),
        @JsonSubTypes.Type(value = LoginRequest.class),
        @JsonSubTypes.Type(value = GetGameStateRequest.class),
        @JsonSubTypes.Type(value = PlayerActionRequest.class),
        @JsonSubTypes.Type(value = SendPMRequest.class),
        @JsonSubTypes.Type(value = BuyRequest.class),
        @JsonSubTypes.Type(value = SurvivalRequest.class),
        @JsonSubTypes.Type(value = RoomGameStartRequest.class),
        @JsonSubTypes.Type(value = EnterRoomRequest.class),
        @JsonSubTypes.Type(value = FinalBuyRequest.class),
        @JsonSubTypes.Type(value = ScoreBoardRequest.class),
        @JsonSubTypes.Type(value = SearchTableRequest.class),
        @JsonSubTypes.Type(value = SelectBagRequest.class),
        @JsonSubTypes.Type(value = GroupGameRequest.class),
        @JsonSubTypes.Type(value = SearchChatRequest.class),
        @JsonSubTypes.Type(value = GroupSurvivalRequest.class),
        @JsonSubTypes.Type(value = NewPrivateChatRequest.class),
        @JsonSubTypes.Type(value = CreateRoomRequest.class)
})

public abstract class Request {
    public abstract void visit(RequestVisitor visitor, ClientController clientController);
}

