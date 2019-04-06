package com.example.menu.Model;

import java.util.List;

public class CaiPu {

    private String title;
    private String imtro;
    private String ingredients;
    private String burden;
    private String album;
    private List<Step>stepList;

    public CaiPu() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImtro() {
        return imtro;
    }

    public void setImtro(String imtro) {
        this.imtro = imtro;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getBurden() {
        return burden;
    }

    public void setBurden(String burden) {
        this.burden = burden;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public List<Step> getStepList() {
        return stepList;
    }

    public void setStepList(List<Step> stepList) {
        this.stepList = stepList;
    }


    public String ToString(){
        return "Title:"+getTitle()+"\n"+
                "Imtro:"+getImtro()+"\n"+
                "Ingredient:"+getIngredients()+"\n"+
                "Burden"+getBurden()+"\n\n";
    }
}