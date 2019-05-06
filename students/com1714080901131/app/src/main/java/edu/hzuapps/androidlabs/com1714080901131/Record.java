package edu.hzuapps.androidlabs.com1714080901131;

public class Record {
    private String id;
    private String titleName;
    private String textBody;

    public Record(String id, String titleName, String textBody) {
        this.id=id;
        this.titleName=titleName;
        this.textBody=textBody;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getTextBody() {
        return textBody;
    }

    public void setTextBody(String textBody) {
        this.textBody = textBody;
    }
    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", titleName='" + titleName + '\'' +
                ", textBody='" + textBody + '\'' + '}';
    }
}
