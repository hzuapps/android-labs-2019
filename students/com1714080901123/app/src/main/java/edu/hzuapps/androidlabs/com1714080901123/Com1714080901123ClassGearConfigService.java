package edu.hzuapps.androidlabs.com1714080901123;

import android.util.Xml;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.EOFException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import edu.hzuapps.androidlabs.com1714080901123.Com1714080901123ClassGearConfig;

public class Com1714080901123ClassGearConfigService {   //用于解析XML文件，获取装备配置信息
    public static List<Com1714080901123ClassGearConfig> getGearConfigs(InputStream is){
        try {
            //获取XmlPullParser对象
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(is,"UTF-8");
            //获取指针
            int type = parser.getEventType();
            List<Com1714080901123ClassGearConfig> gearConfigs = null;
            Com1714080901123ClassGearConfig gearConfig = null;

            while (type != XmlPullParser.END_DOCUMENT) {
                switch (type) {
                    case XmlPullParser.START_TAG:
                        //拿到标签名并判断
                        if ("GearConfig".equals(parser.getName())) {
                            gearConfigs = new ArrayList<Com1714080901123ClassGearConfig>();
                        } else if ("ConfigInfo".equals(parser.getName())) {
                            gearConfig = new Com1714080901123ClassGearConfig();
                        } else if ("playerName".equals(parser.getName())) {
                            String playerName = parser.nextText();
                            gearConfig.setPlayerName(playerName);
                        } else if ("describe".equals(parser.getName())) {
                            String describe = parser.nextText();
                            gearConfig.setDescribe(describe);
                        } else if ("weaponID".equals(parser.getName())) {
                            int weaponID = Integer.parseInt(parser.nextText());
                            gearConfig.setWeaponID(weaponID);
                        } else if ("abilityID11".equals(parser.getName())) {
                            int abilityID = Integer.parseInt(parser.nextText());
                            gearConfig.setAbilityID11(abilityID);
                        } else if ("abilityID12".equals(parser.getName())) {
                            int abilityID = Integer.parseInt(parser.nextText());
                            gearConfig.setAbilityID12(abilityID);
                        } else if ("abilityID13".equals(parser.getName())) {
                            int abilityID = Integer.parseInt(parser.nextText());
                            gearConfig.setAbilityID13(abilityID);
                        } else if ("abilityID14".equals(parser.getName())) {
                            int abilityID = Integer.parseInt(parser.nextText());
                            gearConfig.setAbilityID14(abilityID);
                        } else if ("abilityID21".equals(parser.getName())) {
                            int abilityID = Integer.parseInt(parser.nextText());
                            gearConfig.setAbilityID21(abilityID);
                        } else if ("abilityID22".equals(parser.getName())) {
                            int abilityID = Integer.parseInt(parser.nextText());
                            gearConfig.setAbilityID22(abilityID);
                        } else if ("abilityID23".equals(parser.getName())) {
                            int abilityID = Integer.parseInt(parser.nextText());
                            gearConfig.setAbilityID23(abilityID);
                        } else if ("abilityID24".equals(parser.getName())) {
                            int abilityID = Integer.parseInt(parser.nextText());
                            gearConfig.setAbilityID24(abilityID);
                        } else if ("abilityID31".equals(parser.getName())) {
                            int abilityID = Integer.parseInt(parser.nextText());
                            gearConfig.setAbilityID31(abilityID);
                        } else if ("abilityID32".equals(parser.getName())) {
                            int abilityID = Integer.parseInt(parser.nextText());
                            gearConfig.setAbilityID32(abilityID);
                        } else if ("abilityID33".equals(parser.getName())) {
                            int abilityID = Integer.parseInt(parser.nextText());
                            gearConfig.setAbilityID33(abilityID);
                        } else if ("abilityID34".equals(parser.getName())) {
                            int abilityID = Integer.parseInt(parser.nextText());
                            gearConfig.setAbilityID34(abilityID);
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if ("ConfigInfo".equals(parser.getName())) {
                            gearConfigs.add(gearConfig);
                            gearConfig = null;
                        }
                        break;
                }
                type = parser.next();
            }
            return gearConfigs;
        } catch (java.io.IOException e) {
            e.printStackTrace();
            return null;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return null;
    }
}
