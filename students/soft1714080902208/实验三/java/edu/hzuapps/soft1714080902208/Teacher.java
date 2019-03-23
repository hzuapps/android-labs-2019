package edu.hzuapps.soft1714080902208;

import java.util.ArrayList;
import java.util.List;

public class Teacher {

    private String name;
    private int imageId;
    private String desc;

    //构造函数
    public Teacher(String name, int imageId, String desc) {
        this.name = name;
        this.imageId = imageId;
        this.desc = desc;
    }

    // 返回一个Teacher的列表
    public static List<Teacher> getAllTeachers() {
        List<Teacher> teachers = new ArrayList<Teacher>();
        teachers.add(new Teacher("歌手", R.drawable.wm, " "));
        teachers.add(new Teacher("织部里沙", R.drawable.zbls, "名织部里沙，岐阜县出身。\n" +
                "为摇滚乐团“Love is Same All”的主唱，其艺名从该乐队的队名缩写而来。\n" +
                "以高亢的摇滚曲风而称著。\n" +
                "高中时代组成摇滚乐团「CHUCKY」时已担当主唱。后来CHUCKY于2008年7月解散。\n" +
                "2010年《Angel Beats!》邀请她以“Girls Dead Monster starring LiSA”名义为角色由依代唱，为其角色歌手。发布第一张代唱单曲《Thousand Enemies》与《一番の宝物》后，获得好评。\n" +
                "2011年1月16日宣布以个人身份正式出道。同年7月2日晚8时，在上海的MAO Live House举行了一场长达两小时的\"LiVE is Smile Always\"小型演唱会，这是她在海外举办的首场演唱会。11月23日发表首张单曲《oath sign》。\n" +
                "2012年2月22日发表首张个人专辑《LOVER\"S\"MiLE》。同年7月被邀请参加美国的Anime Expo，第一次在美国演出。第二张个人单曲《crossing field》在2012年8月8日发售，此单曲拿过Oricon日榜单冠军，在周榜初登场获得了5位的成绩，在榜时间长达半年，先后出货量突破10万枚。\n" +
                "2017年8月30日，为了庆祝中国将于9月15日将上映《刀剑神域剧场版 -序列争战-》，本人挑战了中文演唱主题曲《Catch the Moment》，这是LiSA个人的第一首中文歌曲。\n" +
                "\n" +
                "阅读更多：https://zh.moegirl.org/LiSA\n" +
                "本文引自萌娘百科（https://zh.moegirl.org/），文字内容遵守【知识共享 署名-非商业性使用-相同方式共享 3.0】协议。"));
        teachers.add(new Teacher("井上正大", R.drawable.jszd, "井上正大（Inoue Masahiro），1989年3月20日出生于日本神奈川县，日本男演员，Box Corporation旗下艺人 [1]  。\n" +
                "2008年7月29日，在《网球王子舞台剧》冰帝全国战（冰帝二代B组vs青学五代版）饰演迹部景吾正式出道 [2]  。2009年1月，在特摄剧《假面骑士Decade》中饰演主人公门矢士 [3]  。2012年7月，在深夜剧《青空之卵》中饰演坂木司 [4]  。\n" +
                "2016年12月19日，在博客宣布已与模特、女演员Jaimie夏树入籍结婚"));


        teachers.add(new Teacher("蓝井艾露", R.drawable.ljal, "蓝井艾露是日本女性歌手，出身于北海道札幌市。\n" +
                "\n" +
                "曾有成为医护人员的意向，其别名Eir来自于北欧神话中司掌医药和健康的女神Eir，即艾露，词作活动是使用该名义。\n" +
                "\n" +
                "歌唱特点为爆发性的高音。"));

        return teachers;
    }

    // 以下都是访问内部属性的getter和setter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}