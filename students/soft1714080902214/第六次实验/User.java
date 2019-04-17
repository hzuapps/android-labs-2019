package edu.hzuapps.androidlabs.soft1714080902214;

/**
 * Created by windowsPC on 2019/4/17.
 */

public class User {
    private String name;
    private String score;
    int id;

    public User() {
    }

    public User(String name, String score, int id) {
        this.name = name;
        this.score = score;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", score='" + score + '\'' +
                ", id=" + id +
                '}';
    }
}
