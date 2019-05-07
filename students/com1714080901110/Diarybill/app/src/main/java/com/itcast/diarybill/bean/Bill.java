package com.itcast.diarybill.bean;

public class Bill {
    private Long id;
    private String date;
    private Integer income;
    private Integer coast;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getIncome() {
        return income;
    }

    public void setIncome(Integer income) {
        this.income = income;
    }

    public Integer getCoast() {
        return coast;
    }

    public void setCoast(Integer coast) {
        this.coast = coast;
    }

    public Bill(Long id, String date, Integer income, Integer coast) {
        this.id = id;
        this.date = date;
        this.income = income;
        this.coast = coast;
    }

    public Bill(String date, Integer income, Integer coast) {
        this.date = date;
        this.income = income;
        this.coast = coast;
    }

    @Override
    public String toString() {
        return date + " 收入" + income +"元,支出" + coast +"元";
    }
}
