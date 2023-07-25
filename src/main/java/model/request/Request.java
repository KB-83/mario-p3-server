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
        @JsonSubTypes.Type(value = Room.class)
})

public abstract class Request {
    public abstract void visit(RequestVisitor visitor, ClientController clientController);
}

