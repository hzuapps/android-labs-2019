package com.yzk.ordis.bean;

public class CetusTime {

    /**
     * id : cetusCycle1553420460000
     * expiry : 2019-03-24T09:41:00.000Z
     * isDay : true
     * timeLeft : 54m 55s
     * isCetus : true
     * shortString : 54m to Night
     */

    private String id;
    private String expiry;
    private boolean isDay;
    private String timeLeft;
    private boolean isCetus;
    private String shortString;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public boolean isIsDay() {
        return isDay;
    }

    public void setIsDay(boolean isDay) {
        this.isDay = isDay;
    }

    public String getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(String timeLeft) {
        this.timeLeft = timeLeft;
    }

    public boolean isIsCetus() {
        return isCetus;
    }

    public void setIsCetus(boolean isCetus) {
        this.isCetus = isCetus;
    }

    public String getShortString() {
        return shortString;
    }

    public void setShortString(String shortString) {
        this.shortString = shortString;
    }
}
