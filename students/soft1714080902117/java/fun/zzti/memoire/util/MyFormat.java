package fun.zzti.memoire.util;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * create_by Android Studio
 *
 * @author zouguo0212@
 * @package_name fun.zzti.memoire.util
 * @description 一个格式化类，目前只添加了时分秒的格式化(8:1:15->08:01:15)、日期与字符串互转的格式化操作
 * @date 2018/12/3 11:22
 */
public class MyFormat {
    /**
     * 拼接并格式化时间 HH:mm:ss
     * @param hour
     * @param minute
     * @param second
     * @return
     */
    public static String timeFormat(int hour,int minute,int second){
        if (hour>23||hour<0||minute>59||minute<0||second>59||second<0) {
            return null;
        }
        String result;
        String strHour = String.valueOf(hour);
        String strMinute = String.valueOf(minute);
        String strSecond = String.valueOf(second);
        if (hour<10) {
            strHour = "0" + strHour;
        }
        if (minute<10) {
            strMinute = "0" + strMinute;
        }
        if (second<10) {
            strSecond = "0" + strSecond;
        }
        result = strHour + ":" + strMinute + ":" + strSecond;
        return result;
    }

    /**
     * 拼接并格式化时间 HH:mm
     * @param hour
     * @param minute
     * @return
     */
    public static String timeFormat(int hour, int minute){
        if (hour>23||hour<0||minute>59||minute<0) {
            return null;
        }
        String result;
        String strHour = String.valueOf(hour);
        String strMinute = String.valueOf(minute);
        if (hour<10) {
            strHour = "0" + strHour;
        }
        if (minute<10) {
            strMinute = "0" + strMinute;
        }
        result = strHour + ":" + strMinute;
        return result;
    }

    /**
     * 日期转字符串
     * @param date
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String myDateFormat(Date date, @Nullable DateFormatType dateFormatType){

        SimpleDateFormat dateFormat;
        if (dateFormatType == null){
            dateFormatType = DateFormatType.NORMAL_TIME;
        }

        switch (dateFormatType){
            case NORMAL_TIME:
                dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                break;
            case REMOVE_YEAR_TIME:
                dateFormat = new SimpleDateFormat("MM-dd HH:mm");
                break;
            case NORMAL_DATE:
                dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                break;
            case REMOVE_YEAR_DATE:
                dateFormat = new SimpleDateFormat("MM-dd");
                break;
            case SPECIAL_TYPE:
                dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
                break;
            default:
                dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                break;
        }

        return dateFormat.format(date);
    }

    /**
     * 字符串转日期
     * @param str
     * @return DATE
     */
    @SuppressLint("SimpleDateFormat")
    public static Date myDateFormat(String str,@Nullable DateFormatType dateFormatType){
        SimpleDateFormat dateFormat;
        if (dateFormatType == null){
            dateFormatType = DateFormatType.NORMAL_TIME;
        }
        switch (dateFormatType){
            case NORMAL_TIME:
                dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                break;
            case REMOVE_YEAR_TIME:
                dateFormat = new SimpleDateFormat("MM-dd HH:mm");
                break;
            case NORMAL_DATE:
                dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                break;
            case REMOVE_YEAR_DATE:
                dateFormat = new SimpleDateFormat("MM-dd");
                break;
            case SPECIAL_TYPE:
                dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
                break;
            default:
                dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                break;
        }
        try {
            return dateFormat.parse(str);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 字符串转日期
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @param dateFormatType
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static Date myDateFormat(Integer year,
                                    Integer month,
                                    Integer day,
                                    Integer hour,
                                    Integer minute,
                                    @Nullable DateFormatType dateFormatType){
        SimpleDateFormat dateFormat;
        String str;
        if (dateFormatType == null){
            dateFormatType = DateFormatType.NORMAL_TIME;
        }
        switch (dateFormatType){
            case NORMAL_TIME:
                dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                str = year+"-"+month+"-"+day+" "+hour+":"+minute;
                break;
            case REMOVE_YEAR_TIME:
                dateFormat = new SimpleDateFormat("MM-dd HH:mm");
                str = month+"-"+day+" "+hour+":"+minute;
                break;
            case NORMAL_DATE:
                dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                str = year+"-"+month+"-"+day;
                break;
            case REMOVE_YEAR_DATE:
                dateFormat = new SimpleDateFormat("MM-dd");
                str = month+"-"+day;
                break;
            case SPECIAL_TYPE:
                dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
                str = year+"-"+month+"-"+day+"-"+hour+"-"+minute;
                break;
            default:
                dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                str = year+"-"+month+"-"+day+" "+hour+":"+minute;
                break;
        }
        try {
            return dateFormat.parse(str);
        } catch (ParseException e) {
            return null;
        }
    }


    /*
     *  得到时间字符串，判断是否去除年份
     *  去除规则，如果年份是当年
     */
    public static String getTimeStr(Date date){
        int nowYear = new MyTimeGetter(new Date(System.currentTimeMillis())).getYear();
        int targetYear = new MyTimeGetter(date).getYear();
        if (nowYear == targetYear){
            //  去除年份
            return myDateFormat(date,DateFormatType.REMOVE_YEAR_TIME);
        }
        return myDateFormat(date,DateFormatType.NORMAL_TIME);
    }
}
