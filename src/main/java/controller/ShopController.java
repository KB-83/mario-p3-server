package controller;

import model.main_model.*;
import model.main_model.entity.Sward;
import model.main_model.entity.power_item.*;
import model.main_model.score_board.ScoreBoard;
import model.request.BuyRequest;
import model.request.FinalBuyRequest;
import model.response.DialogResponse;
import util.Config;
import util.Saver;

public class ShopController {
    private ShopLimitation shopLimitation = Config.LIMITATIONS;
    private ShopLog shopLog = new ShopLog();
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
        System.out.println("26");
        if (checkLimitations(clientController,buyRequest)) {
            System.out.println("28");
            Bill bill = checkPrice(buyRequest);
            if (checkBalance(clientController.getClient(),bill)) {
                System.out.println("31");
                return bill;
            }
        }
        return null;
    }
    private boolean checkLimitations(ClientController clientController, BuyRequest buyRequest) {
        if (checkLimitationByName(clientController,shopLimitation.getHammer(),buyRequest.getHammer(),
                Hammer.class.getSimpleName()) &&
                checkLimitationByName(clientController,shopLimitation.getSward(),
                        buyRequest.getSward(), Sward.class.getSimpleName()) &&
                checkLimitationByName(clientController,shopLimitation.getDamageBomb(),
                        buyRequest.getDamageBomb(), DamageBomb.class.getSimpleName()) &&
                checkLimitationByName(clientController,shopLimitation.getSpeedBomb(),
                        buyRequest.getSpeedBomb(), SpeedBomb.class.getSimpleName()) &&
                checkLimitationByName(clientController,shopLimitation.getHealthPotion(),
                        buyRequest.getHealthPotion(), HealthPotion.class.getSimpleName()) &&
                checkLimitationByName(clientController,shopLimitation.getSpeedPotion(),
                        buyRequest.getSpeedPotion(), SpeedPotion.class.getSimpleName()) &&
                checkLimitationByName(clientController,shopLimitation.getInvisibilityPotion(),
                        buyRequest.getInvisibilityPotion(), InvisibilityPotion.class.getSimpleName())) {
            return true;
        }
        return false;
    }
    private boolean checkLimitationByName(ClientController clientController,Limitation limitation, int itemNumber, String itemName) {
        String userName = clientController.getClient().getUsername();
        int grade = ScoreBoard.grade(clientController.getClient());
        if (itemNumber <= 0) {
            return true;
        }
        //check num per day
        if (limitation.getNumber() != -1) {
            if (itemNumber + shopLog.todayNumOf(itemName)
                    > limitation.getNumber()) {
                clientController.sendResponse(new DialogResponse("sold out today"));
                return false;
            }
        }
        //check num per user
        if (limitation.getNumberPerPlayer() != -1) {
            if (itemNumber + shopLog.todayNumOfUser(userName, itemName)
                    > limitation.getNumberPerPlayer()) {
                clientController.sendResponse(new DialogResponse("you can buy "+limitation.getNumberPerPlayer()+" per day"));
                return false;
            }
        }
        //grade
        if (limitation.getGrade() != -1) {
            if (grade < limitation.getGrade()) {
                clientController.sendResponse(new DialogResponse("your grade have to be "+limitation.getGrade()+" !"));
                return false;
            }
        }
        //checkDate
        if (limitation.getPublishDate() != -1) {
            if (limitation.getPublishDate() > System.currentTimeMillis()) {
                return false;
            }
        }
        if (limitation.getEndDate() != -1) {
            if (limitation.getEndDate() < System.currentTimeMillis()) {
                return false;
            }
        }
        return true;

    }
    private boolean checkBalance(Client client, Bill price) {
        if (client.getCoin() >= price.getCoinCost() && client.getDiamond() >= price.getDiamondCost()) {
            return true;
        }
        return false;
    }
    private Bill checkPrice(BuyRequest buyRequest) {
        //have to be improved   fek kon har koodoom 1 seke ye almase
        int totalNum = buyRequest.getHammer()+buyRequest.getSward()+buyRequest.getSpeedPotion()
                +buyRequest.getHealthPotion()+buyRequest.getInvisibilityPotion()
                +buyRequest.getSpeedBomb()+buyRequest.getDamageBomb();
        int coins = totalNum * 10;
        int diamonds = totalNum;
        return new Bill(diamonds,coins,buyRequest);
    }
    public synchronized void finalBuyRequest(FinalBuyRequest finalBuyRequest, ClientController clientController) {
        if (checkLimitations(clientController,finalBuyRequest.getBuyResponse().getBill().getBuyRequest())) {
            Client client = clientController.getClient();
            Fund fund = client.getFund();
            Bill bill = finalBuyRequest.getBuyResponse().getBill();
            BuyRequest buyRequest = bill.getBuyRequest();
            // put bill in log
            shopLog.putBillInLog(client.getUsername(),buyRequest);

            fund.setDamageBomb(fund.getDamageBomb() + buyRequest.getDamageBomb());
            fund.setSpeedBomb(fund.getSpeedBomb() + buyRequest.getSpeedBomb());
            fund.setHammer(fund.getHammer() + buyRequest.getHammer());
            fund.setSward(fund.getSward() + buyRequest.getSward());
            fund.setInvisibilityPotion(fund.getInvisibilityPotion() + buyRequest.getInvisibilityPotion());
            fund.setHealthPotion(fund.getHealthPotion() + buyRequest.getHealthPotion());
            fund.setSpeedPotion(fund.getSpeedPotion() + buyRequest.getSpeedPotion());

            client.setCoin(client.getCoin() - bill.getCoinCost());
            client.setDiamond(client.getDiamond() - bill.getDiamondCost());

            // save client to db
            Saver.getSaver().updateClient(client);
        }
        else{
            clientController.sendResponse(new DialogResponse("some1 faster than U :D"));
        }
    }
}