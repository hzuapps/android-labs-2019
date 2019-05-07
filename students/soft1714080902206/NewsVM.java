package com.exampler.mynews;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class NewsVM extends BaseObservable {

    private News news;

    public NewsVM(News news) {
        this.news = news;
    }

    @Bindable
    public String getTitle() {
        return news.getTitle();
    }

    @Bindable
    public String getImageId() {
        return news.getImageId();
    }

    @Bindable
    public String getContent() {
        return news.getContent();
    }

    @Bindable
    public String getTime() {
        return news.getTime();
    }

    public void setTitle(String title) {
        news.setTitle(title);
        notifyPropertyChanged(BR.title);
    }

    public void setImageId(String imageId) {
        news.setImageId(imageId);
        notifyPropertyChanged(BR.imageId);
    }

    public void setTime(String time) {
        news.setTime(time);
        notifyPropertyChanged(BR.time);
    }

    public void setContent(String content) {
        news.setContent(content);
        notifyPropertyChanged(BR.content);
    }

}

