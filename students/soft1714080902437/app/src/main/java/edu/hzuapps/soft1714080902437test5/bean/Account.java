package edu.hzuapps.soft1714080902437test5.bean;

public class Account {
    private Long id;
    private String cname;
    private String tname;
    public Long getId() {
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }
    public String getName1() {
        return cname;
    }
    public void setName1(String cname){
        this.cname=cname;
    }
    public String getName2() {
        return tname;
    }
    public void setName2(String tname){
        this.tname=tname;
    }

    public Account(Long id,String cname,String tname){
        super();
        this.id=id;
        this.cname=cname;
        this.tname=tname;
    }
    public Account(String cname,String tname){
        super();
        this.cname=cname;
        this.tname=tname;
    }
    public Account() {
        super();
    }
    public String toString(){
        return "[序号:"+id+",课程名称:"+cname+",老师:"+tname+"]";
    }
}
