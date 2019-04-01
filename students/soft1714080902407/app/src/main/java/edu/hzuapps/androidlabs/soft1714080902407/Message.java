package edu.hzuapps.androidlabs.soft1714080902407;

public class Message {
    private Long id;
    private String msg;

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

    public Message(Long id,String msg){
        super();
        this.id=id;
        this.msg=msg;
    }

    public Message(String msg){
        super();
        this.msg=msg;
    }

    public  Message(){
        super();
    }
}
