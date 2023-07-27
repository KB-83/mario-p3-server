package model.response;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import controller.connection.ResponseVisitor;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value = SignInLoginResponse.class),
        @JsonSubTypes.Type(value = NewPMResponse.class),
        @JsonSubTypes.Type(value = GameStateStatusResponse.class),
        @JsonSubTypes.Type(value = BuyResponse.class),
        @JsonSubTypes.Type(value = GameStartResponse.class)

})

public abstract class Response {

}
