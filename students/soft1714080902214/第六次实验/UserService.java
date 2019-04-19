package edu.hzuapps.androidlabs.soft1714080902214;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by windowsPC on 2019/4/17.
 */

public class UserService {

    public static List<User> getuser(InputStream inputStream) throws Exception{
        User user = new User();
        List<User> users = new ArrayList<>();
        XmlPullParser pullParser = Xml.newPullParser();
        pullParser.setInput(inputStream,"UTF-8");
        int event = pullParser.getEventType();
        while(event != XmlPullParser.END_DOCUMENT){
            switch (event){
                case XmlPullParser.START_TAG:

                    if("u".equals(pullParser.getName())){
                        users = new ArrayList<>();
                    }

                    else if("user".equals(pullParser.getName())){
                        int id = new Integer(pullParser.getAttributeValue(0));
                        user = new User();
                        user.setId(id);
                    }
                    else if("name".equals(pullParser.getName())){
                        String name = pullParser.nextText();
                        user.setName(name);
                    }
                    else if("score".equals(pullParser.getName())){
                        String score = pullParser.nextText();
                        user.setScore(score);
                    }
                    break;

                case XmlPullParser.END_TAG:
                    if("user".equals(pullParser.getName())){
                        users.add(user);
                        user = new User();
                    }
                    break;
            }
            event = pullParser.next();
        }
        return users;
    }
}
