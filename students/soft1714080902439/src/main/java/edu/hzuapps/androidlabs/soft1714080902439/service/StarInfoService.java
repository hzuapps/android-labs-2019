package edu.hzuapps.androidlabs.soft1714080902439.service;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import edu.hzuapps.androidlabs.soft1714080902439.StarInfo;

public class StarInfoService {
    public static List<StarInfo> getStarInfos(InputStream inputStream){
        List<StarInfo> starInfos = null;
        StarInfo starInfo = null;
        //得到PULL解析器
        XmlPullParser parser = Xml.newPullParser();
        try {
            parser.setInput(inputStream, "UTF-8");
            //产生事件
            int eventType = parser.getEventType();
            //如果不是文档结束事件就循环推进
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                /*case XmlPullParser.START_DOCUMENT://开始文档事件
                    starInfos = new ArrayList<StarInfo>();
                    break;*/
                    case XmlPullParser.START_TAG://开始元素事件
                        //获取解析器当前指向的元素的名称
                        String name = parser.getName();
                        if ("star".equals(name)) {
                            starInfos = new ArrayList<StarInfo>();
                        } else if ("starInfo".equals(name)) {
                            starInfo = new StarInfo();
                            //starInfo.getName(Integer.parseInt(parser.getAttributeValue(0)));
                        } else if ("icon".equals(name)) {
                            String icon = parser.nextText();
                            starInfo.setIconPath(icon);
                        } else if ("title".equals(name)) {
                            String title = parser.nextText();
                            starInfo.setName(title);
                        } else if ("content".equals(name)) {
                            String time = parser.nextText();
                            starInfo.setTime(time);
                        } else if ("desc".equals(name)) {
                            String desc = parser.nextText();
                            starInfo.setDesc(desc);
                        }
                        break;
                    case XmlPullParser.END_TAG://结束元素事件
                        //判断是都是person的结束事件
                        if ("starInfo".equals(parser.getName())) {
                            starInfos.add(starInfo);
                            starInfo = null;
                        }
                        break;
                }
                //进入下一个元素并触发相应的事件
                eventType = parser.next();
            }
            return starInfos;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
