package controller;

import model.main_model.Client;
import model.main_model.Fund;
import model.request.SelectBagRequest;

public class BagController {
    private BagController(){}
    public static boolean checkBag(SelectBagRequest request, ClientController clientController) {
        Fund fund = clientController.getClient().getFund();
        int sBomb = 0,dBomb = 0,sward = 0,hPotion = 0,iPotion = 0,sPotion = 0;
        for (String item : request.getBag()) {
            if (item == null) {
                return false;
            }
            switch (item) {
                case "InvisibilityPotion" :
                    iPotion++;
                    break;
                case "SpeedPotion" :
                    sPotion++;
                    break;
                case "HealthPotion" :
                    hPotion++;
                    break;
                case "Sward" :
                    sward++;
                    break;
                case "DamageBomb" :
                    dBomb++;
                    break;
                case "SpeedBomb" :
                    sBomb++;
                    break;
            }
        }
        if (sBomb > fund.getSpeedBomb()) {
            return false;
        }
        else if (dBomb > fund.getDamageBomb()) {
            return false;
        }
        else if (sward > fund.getSward()) {
            return false;
        }
        else if (iPotion > fund.getInvisibilityPotion()) {
            return false;
        }
        else if (hPotion > fund.getHealthPotion()) {
            return false;
        }
        else if (sPotion > fund.getSpeedPotion()) {
            return false;
        }
        clientController.getClient().setSelectedBag(request.getBag());
        return true;
    }
}
