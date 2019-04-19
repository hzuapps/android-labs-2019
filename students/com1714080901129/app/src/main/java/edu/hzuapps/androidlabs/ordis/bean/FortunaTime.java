package com.yzk.ordis.bean;

public class FortunaTime {

    /**
     * id : vallisCycle1555330800000
     * expiry : 2019-04-15T12:40:28.000Z
     * isWarm : false
     * timeLeft : 8m 37s
     * shortString : 8m to Warm
     */

    private String id;
    private String expiry;
    private boolean isWarm;
    private String timeLeft;
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

    public boolean isIsWarm() {
        return isWarm;
    }

    public void setIsWarm(boolean isWarm) {
        this.isWarm = isWarm;
    }

    public String getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(String timeLeft) {
        this.timeLeft = timeLeft;
    }

    public String getShortString() {
        return shortString;
    }

    public void setShortString(String shortString) {
        this.shortString = shortString;
    }
}
