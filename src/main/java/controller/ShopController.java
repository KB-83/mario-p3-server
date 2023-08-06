package controller;

import model.main_model.Bill;
import model.main_model.Client;
import model.main_model.Fund;
import model.main_model.ShopLimitation;
import model.request.BuyRequest;
import model.request.FinalBuyRequest;
import model.response.DialogResponse;
import util.Config;
import util.Saver;

public class ShopController {
    private ShopLimitation limitation = Config.LIMITATIONS;
    public static  double levelMultiplier = Config.CONSTANT.get("Shop.levelMultiplier");
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
    public synchronized void finalBuyRequest(FinalBuyRequest finalBuyRequest, ClientController clientController) {
        if (checkLimitations(finalBuyRequest.getBuyResponse().getBill().getBuyRequest())) {
            Client client = clientController.getClient();
            Fund fund = client.getFund();
            Bill bill = finalBuyRequest.getBuyResponse().getBill();
            BuyRequest buyRequest = bill.getBuyRequest();

            fund.setDamageBomb(fund.getDamageBomb() + buyRequest.getDamageBomb());
            fund.setSpeedBomb(fund.getSpeedBomb() + buyRequest.getSpeedBomb());
            fund.setHammer(fund.getHammer() + buyRequest.getHammer());
            fund.setSward(fund.getSward() + buyRequest.getSward());
            fund.setInvisibilityPotion(fund.getInvisibilityPotion() + buyRequest.getInVisibilityPotion());
            fund.setHealthPotion(fund.getHealthPotion() + buyRequest.getHealthPotion());
            fund.setSpeedPotion(fund.getSpeedPotion() + buyRequest.getSpeedPotion());

            client.setCoin(client.getCoin() - bill.getCoinCost());
            client.setDiamond(client.getDiamond() - bill.getDiamondCost());

            // save client to db
            Saver.getSaver().updateClient(client);
        }
        else{
            clientController.sendResponse(new DialogResponse("some2 faster than U :D"));
        }
    }
}