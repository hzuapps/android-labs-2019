package edu.hzuapps.androidlabs.soft1714080902228;

public class Music {
    private String info;
    private int imageId;
    public Music(String info, int imageId) {
        this.info = info;
        this.imageId = imageId;
    }
    public String getName() {
        return info;
    }
    public int getImageId() {
        return imageId;
    }
}
