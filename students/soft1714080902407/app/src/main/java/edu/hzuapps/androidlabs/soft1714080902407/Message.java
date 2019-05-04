package edu.hzuapps.androidlabs.soft1714080902407;

public class Message {
    private Long id;
    private String msg;
    private String time;
    private Boolean send;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean getSend() {
        return send;
    }

    public void setSend(boolean send) {
        this.send = send;
    }

    public Message(){

    }


    public Message(Long id,String msg,String time,Boolean send){
        this.id=id;
        this.msg=msg;
        this.time=time;
        this.send=send;
    }

}
