package com.hzuandroid.jsonjx;
public class Movie {
    private int id;
    private String name;
    private String price;
    private String language;
    public Movie(int id,String name,String price,String language){
        super();
        this.id=id;
        this.name=name;
        this.price=price;
        this.language=language;
    }
    public Movie(){
        super();
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getPrice() {
        return price;
    }
    public String getLanguage() {
        return language;
    }
}
