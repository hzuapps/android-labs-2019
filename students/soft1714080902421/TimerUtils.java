package edu.hzuapps.androidlabs.soft1714080902421;

import java.util.Locale;

public class TimerUtils {
    public static String getTime(int second) {
        int hour = second/3600;
        int min = second % 3600 / 60;
        int sec = second % 60;
        return String.format(Locale.CHINA,"%02d:%02d:%02d",hour,min,sec);
    }
}


