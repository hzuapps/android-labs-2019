package edu.hzuapps.androidlabs.soft1714080902133.javabean;

//用户信息javabean，未连接数据库
public class Soft1714080902133User {
    //学号
    private String number;
    //手机号
    private String telephone;
    //昵称
    private String nickname;
    //性别
    private String sex;
    //密码
    private String password;

    public Soft1714080902133User() {

    }

    public Soft1714080902133User(String number,String telephone,String nickname,String sex,String password) {
        this.number = number;
        this.telephone = telephone;
        this.nickname = nickname;
        this.password = password;
        this.sex = sex;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
