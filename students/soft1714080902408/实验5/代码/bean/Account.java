package com.hzuandroid.soft1714080902408.bean;

import android.content.Intent;

public class Account {
    private Long id;
    private String name;
    private Integer balance;

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

public Account(Long id,String name,Integer balance){
    super();
    this.id=id;
    this.name=name;
    this.balance=balance;
}

public Account(String name,Integer balance){
    super();
    this.name=name;
    this.balance=balance;
}

public Account() {
    super();
}
public String toString(){
    return "[序号："+id+",宝贝名称："+name+",余额："+balance+"]";
}
}