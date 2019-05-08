package edu.hzuapps.androidlabs.soft1714080902424;
import java.util.Date;

public class ChatMessage {

    private String name;// 姓名
    private String message;// 消息
    private Type type;// 类型：0.发送者 1.接受者
    private Date data;// 时间

    public ChatMessage() {

    }

    public ChatMessage(String message, Type type, Date data) {
        super();
        this.message = message;
        this.type = type;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public enum Type {
        INCOUNT, OUTCOUNT
    }
}