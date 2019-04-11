package edu.hzuapps.androidlabs.model;

public class Task {

    private Long id;
    private String title;
    private String content;
    private String date;
    private int finish;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getFinish() {
        return finish;
    }

    public void setFinish(int finish) {
        this.finish = finish;
    }

    @Override
    public String toString() {
        return "['title': " + getTitle() + ", 'content':" + getContent() + ", 'date':" + getDate() + "]";
    }
}
