package model.request;

import controller.ClientController;
import controller.connection.RequestVisitor;
import model.response.BuyResponse;

public class FinalBuyRequest extends Request{
    private BuyResponse buyResponse;

    public FinalBuyRequest() {
    }
    @Override
    public void visit(RequestVisitor visitor, ClientController clientController) {
        visitor.visit(this,clientController);
    }
    public BuyResponse getBuyResponse() {
        return buyResponse;
    }

    public void setBuyResponse(BuyResponse buyResponse) {
        this.buyResponse = buyResponse;
    }

}
