package edu.hzuapps.androidlabs.model;

public class Task {

    private Long id;
    private String title;
    private String content;
    private String createTime;
    private String lastTime;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public int getFinish() {
        return finish;
    }

    public void setFinish(int finish) {
        this.finish = finish;
    }

    @Override
    public String toString() {
        return String.format("[title: %s, content:%s, last_time:%s, create_time:%s， finish: %d]",
                getTitle(), getContent(), getLastTime(), getCreateTime(), getFinish());
    }
}
