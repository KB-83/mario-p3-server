package model.main_model;

import model.main_model.entity.Sward;
import model.main_model.entity.power_item.*;
import model.request.BuyRequest;

import java.util.HashMap;

public class ShopLog {
    private long date;
    private HashMap<String, String> todayLog = new HashMap<>();
    private static int numOfOreder = 1;

    public ShopLog() {
        date = System.currentTimeMillis();
    }

    public HashMap<String, String> getLog(){
        checkDate();
        return todayLog;
    }
    public void checkDate() {
        if (System.currentTimeMillis() - date > 24 * 60 * 60 * 1000) {
            date = System.currentTimeMillis();
            todayLog = new HashMap<>();
        }
    }
    public int todayNumOfUser(String username, String item) {
        checkDate();
        int num = 0;
        for (String s : todayLog.keySet()) {
            if (s.contains(item) && username.equals(todayLog.get(s))) {
                num++;
            }
        }
        return num;
    }
    public int todayNumOf(String item) {
        checkDate();
        int num = 0;
        for (String s: todayLog.keySet()) {
            if (s.contains(item)) {
                num++;
            }
        }
        return num;
    }
    public void putBillInLog(String username , BuyRequest buyRequest) {
        checkDate();
        for (int i = 0; i < buyRequest.getInvisibilityPotion() ; i++) {
            todayLog.put(InvisibilityPotion.class.getSimpleName()+numOfOreder,username);
            numOfOreder++;
        }
        for (int i = 0;i < buyRequest.getSpeedPotion() ; i++) {
            todayLog.put(SpeedPotion.class.getSimpleName()+numOfOreder,username);
            numOfOreder++;
        }
        for (int i = 0;i < buyRequest.getHealthPotion() ; i++) {
            todayLog.put(HealthPotion.class.getSimpleName()+numOfOreder,username);
            numOfOreder++;
        }
        for (int i = 0;i < buyRequest.getHammer() ; i++) {
            todayLog.put(Hammer.class.getSimpleName()+numOfOreder,username);
            numOfOreder++;
        }
        for (int i = 0;i < buyRequest.getSward() ; i++) {
            todayLog.put(Sward.class.getSimpleName()+numOfOreder,username);
            numOfOreder++;
        }
        for (int i = 0;i < buyRequest.getDamageBomb() ; i++) {
            todayLog.put(DamageBomb.class.getSimpleName()+numOfOreder,username);
            numOfOreder++;
        }
        for (int i = 0;i < buyRequest.getSpeedBomb() ; i++) {
            todayLog.put(SpeedBomb.class.getSimpleName()+numOfOreder,username);
            numOfOreder++;
        }

    }

}
