package controller;

import model.main_model.Bill;
import model.main_model.Client;
import model.main_model.ShopLimitation;
import model.request.BuyRequest;
import util.Config;

public class ShopController {
    private ShopLimitation limitation = Config.LIMITATIONS;
    private static ShopController shopController;
    private ShopController(){}
    public static ShopController getInstance() {
        if (shopController == null) {
            shopController = new ShopController();
        }
        return shopController;
    }
    public Bill returnCheckBuyRequest(BuyRequest buyRequest, ClientController clientController) {
        if (checkLimitations(buyRequest)) {
            Bill bill = checkPrice(buyRequest);
            if (checkBalance(clientController.getClient(),bill)) {
                return bill;
            }
        }
        return null;
    }
    private boolean checkLimitations(BuyRequest buyRequest) {
        //have to be improved
        if (buyRequest.getHammer() > limitation.getHammer().getNumber()) {
            return false;
        }
        if (buyRequest.getSward() > limitation.getSward().getNumber()) {
            return false;
        }
        if (buyRequest.getDamageBomb() >limitation.getDamageBomb().getNumber()) {
            return false;
        }
        if (buyRequest.getSpeedBomb() > limitation.getSpeedBomb().getNumber()) {
            return false;
        }
        if (buyRequest.getHealthPotion() > limitation.getHealthPotion().getNumber()) {
            return false;
        }
        if (buyRequest.getSpeedPotion() > limitation.getSpeedPotion().getNumber()) {
            return false;
        }
        if (buyRequest.getInVisibilityPotion() > limitation.getInvisibilityPotion().getNumber()) {
            return false;
        }
        return true;
    }
    private boolean checkBalance(Client client, Bill price) {
        if (client.getCoin() >= price.getCoinCost() && client.getDiamond() >= price.getCoinCost()) {
            return true;
        }
        return false;
    }
    private Bill checkPrice(BuyRequest buyRequest) {
        //have to be improved   fek kon har koodoom 1 seke ye almase
        int totalNum = buyRequest.getHammer()+buyRequest.getSward()+buyRequest.getSpeedPotion()
                +buyRequest.getHealthPotion()+buyRequest.getInVisibilityPotion()
                +buyRequest.getDamageBomb()+buyRequest.getDamageBomb();
        int coins = totalNum * 10;
        int diamonds = totalNum;
        return new Bill(diamonds,coins,buyRequest);
    }
    public synchronized void buy(Bill bill,ClientController clientController) {

    }
}