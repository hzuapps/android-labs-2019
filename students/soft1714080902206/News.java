package com.exampler.mynews;

public class News {

    private String title;

    private String imageId;

    private String time;

    private String content;

    public News(String title, String imageId, String time, String content) {
        this.title = title;
        this.imageId = imageId;
        this.time = time;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getImageId() {
        return imageId;
    }

    public String getTime() {
        return time;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
