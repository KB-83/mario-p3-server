package controller.connection;

import controller.ClientController;
import model.response.Response;
import model.response.SignInLoginResponse;

public interface ResponseVisitor {
    void visit(SignInLoginResponse response);
}
