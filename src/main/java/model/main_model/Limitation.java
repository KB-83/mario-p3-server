package model.main_model;

import java.util.Date;

public class Limitation {
    private int number;
    private int numberPerPlayer;
    private Date publishDate;
    private Date endDate;
    private String moneyUnit;
    private long grade;

    public Limitation() {
    }

    public Limitation(int number, int numberPerPlayer, Date publishDate, Date endDate, String moneyUnit, long grade) {
        this.number = number;
        this.numberPerPlayer = numberPerPlayer;
        this.publishDate = publishDate;
        this.endDate = endDate;
        this.moneyUnit = moneyUnit;
        this.grade = grade;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumberPerPlayer() {
        return numberPerPlayer;
    }

    public void setNumberPerPlayer(int numberPerPlayer) {
        this.numberPerPlayer = numberPerPlayer;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getMoneyUnit() {
        return moneyUnit;
    }

    public void setMoneyUnit(String moneyUnit) {
        this.moneyUnit = moneyUnit;
    }

    public long getGrade() {
        return grade;
    }

    public void setGrade(long grade) {
        this.grade = grade;
    }
}
