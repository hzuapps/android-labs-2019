package com.example.myandroid_2_3.bean;

import android.content.Intent;

public class Account {
    private Long id;
    private String actions;
    private Integer cost;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActions() {
        return actions;
    }

    public void setActions(String actions) {
        this.actions = actions;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }
    public Account(Long id,String actions,Integer cost){
        super();
        this.id=id;
        this.actions=actions;
        this.cost=cost;
    }
    public Account(String actions,Integer cost){
        super();
        this.actions=actions;
        this.cost=cost;
    }
    public Account(){
        super();
    }
    public String toString(){
        return "[序号："+id+"，消费事件："+actions+"，消费金额："+cost+"]";
    }
}
