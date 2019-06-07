package edu.hzuapps.androidlabs.soft1714080902333.bean;

public class NewsInfo {
    private String icon;
    private String title;
    private String content;
    private String comment;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    private int type;

    public String getIcon() {
        return icon;
    }

    public NewsInfo(String icon, String title, String content, String comment, int type) {
        this.icon = icon;
        this.title = title;
        this.content = content;
        this.comment = comment;
        this.type = type;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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
}
