package model.response;

import model.main_model.Bill;

public class BuyResponse extends Response{
    private Bill bill;
    private String message;
    private boolean isAccepted;

    public BuyResponse() {
    }

    public BuyResponse(Bill bill, String message, boolean isAccepted) {
        this.bill = bill;
        this.message = message;
        this.isAccepted = isAccepted;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }
}
