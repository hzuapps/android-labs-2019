package edu.hzuapps.androidlabs.soft1714080902120;

import java.util.ArrayList;
import java.util.List;

public class Soft1714080902120HistoryPeople {


    private String name;
    private String lvde;
    private  String repu;
    //构造函数
    public Soft1714080902120HistoryPeople(String name, String lvde, String repu)
    {
        this.name=name;
        this.lvde=lvde;
        this.repu=repu;
    }

    public static List<Soft1714080902120HistoryPeople> getHistoryPeople()
    {
        List<Soft1714080902120HistoryPeople> hp=
                new ArrayList<Soft1714080902120HistoryPeople>();
        hp.add(new Soft1714080902120HistoryPeople("白起","？—公元前257年","中国战国时代军事家、秦国名将，兵家代表人物。" +
                "《千字文》将他与廉颇、李牧、王翦并称为战国四大名将，位列战国四大名将之首。"));
        hp.add(new Soft1714080902120HistoryPeople("赵奢","生卒年不详","战国后期赵国名将，战国时代东方六国八名将之一，简曰马氏，赵武灵王之子。" +
                "主要生活在赵武灵王（前324—前299年）到赵孝成王（前265—前245年）时期，享年约60余岁。"));
        hp.add(new Soft1714080902120HistoryPeople("蒙恬","约公元前259年—公元前210年","公元前221年，蒙恬被封为将军，攻破齐国，拜为内史 ，深得秦始皇的尊宠，当时与其弟蒙毅号称“忠信”。" +
                "秦统一后，蒙恬率三十万大军北击匈奴。" +
                "恬曾驻守九郡十余年，威震匈奴，被誉为“中华第一勇士”。"));
         hp.add(new Soft1714080902120HistoryPeople("乐毅","生卒年不详","战国后期杰出的军事家，魏将乐羊后裔，拜燕上将军，受封昌国君，辅佐燕昭王振兴燕国。" +
                 "公元前284年，他统帅燕国等五国联军攻打齐国，连下70余城，创造了中国古代战争史上以弱胜强的著名战例，报了强齐伐燕之仇。"));
         hp.add(new Soft1714080902120HistoryPeople("廉颇","生卒年不详"," 嬴姓，廉氏，名颇，战国末期赵国的名将，与白起、王翦、李牧并称“战国四大名将”。" +
                 "唐德宗时将廉颇等历史上六十四位武功卓著的名将，供奉于武成王庙内，被称为武成王庙六十四将。" +
                 "宋徽宗时追尊廉颇为临城伯，位列宋武庙七十二将之一。"));
        return hp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLvde() {
        return lvde;
    }

    public void setLvde(String lvde) {
        this.lvde = lvde;
    }

    public String getRepu() {
        return repu;
    }

    public void setRepu(String repu) {
        this.repu = repu;
    }
}
