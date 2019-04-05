package edu.hzuapps.androidlabs.soft1714080902201.edu.hzuapps.androidlabs.soft1714080902201.bean;

public class Book {
    private Long id;
    private String name;
    private String author;
    private Integer balance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
    public Book(Long id,String name,String author,Integer balance){
        super();
        this.id=id;
        this.name=name;
        this.author=author;
        this.balance=balance;
    }
    public Book(String name,String author,Integer balance){
        super();
        this.name=name;
        this.author=author;
        this.balance=balance;
    }
    public Book(){
        super();
    }
    public String toString(){
        return "[序号："+id+"，图书名称："+name+"，作者："+author+",金额："+balance+"]";
    }
}
