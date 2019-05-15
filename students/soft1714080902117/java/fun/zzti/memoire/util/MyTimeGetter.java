package fun.zzti.memoire.util;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * create_by Android Studio
 *
 * @author zouguo0212@
 * @package_name fun.zzti.memoire.util
 * @description
 * @date 2018/12/3 17:19
 */
public class MyTimeGetter {

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    public MyTimeGetter(){}

    public MyTimeGetter(Date date){
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
        String strDate = dateFormat.format(date);
        String strArray[] =  strDate.split("-");
        year = Integer.parseInt(strArray[0]);
        month = Integer.parseInt(strArray[1]);
        day = Integer.parseInt(strArray[2]);
        hour = Integer.parseInt(strArray[3]);
        minute = Integer.parseInt(strArray[4]);
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }
}
