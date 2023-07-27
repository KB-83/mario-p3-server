package model.main_model;

import model.request.BuyRequest;

public class Bill {
    private int diamondCost;
    private int coinCost;
    private BuyRequest buyRequest;

    public Bill() {
    }
    public Bill(int diamondCost, int coinCost, BuyRequest buyRequest) {
        this.diamondCost = diamondCost;
        this.coinCost = coinCost;
        this.buyRequest = buyRequest;
    }

    public int getDiamondCost() {
        return diamondCost;
    }

    public void setDiamondCost(int diamondCost) {
        this.diamondCost = diamondCost;
    }

    public int getCoinCost() {
        return coinCost;
    }

    public void setCoinCost(int coinCost) {
        this.coinCost = coinCost;
    }

    public BuyRequest getBuyRequest() {
        return buyRequest;
    }

    public void setBuyRequest(BuyRequest buyRequest) {
        this.buyRequest = buyRequest;
    }
}
