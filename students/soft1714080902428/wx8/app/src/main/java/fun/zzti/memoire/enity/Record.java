package fun.zzti.memoire.enity;

/**
 * create_by Android Studio
 *
 * @author zouguo0212@
 * @package_name fun.zzti.enity
 * @description
 * @date 2018/10/26 19:11
 */
public class Record {
    private Integer id;
    private String titleName;
    private String textBody;
    private String createTime;
    private String noticeTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getNoticeTime() {
        return noticeTime;
    }

    public void setNoticeTime(String noticeTime) {
        this.noticeTime = noticeTime;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", titleName='" + titleName + '\'' +
                ", textBody='" + textBody + '\'' +
                ", createTime='" + createTime + '\'' +
                ", noticeTime='" + noticeTime + '\'' +
                '}';
    }
}
