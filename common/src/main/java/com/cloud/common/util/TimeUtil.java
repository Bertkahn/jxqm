package com.cloud.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {
    // 格式化时间
    public static String formatTime (String format) {
        format = format.replaceAll("y", "yy");
        format = format.replaceAll("Y", "yyyy");
        format = format.replaceAll("M", "MM");
        format = format.replaceAll("D", "dd");
        format = format.replaceAll("h", "HH");
        format = format.replaceAll("m", "mm");
        format = format.replaceAll("s", "ss");
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }
    //13位时间戳
    public static long getTimeStampMilli(){
        return System.currentTimeMillis();
    }
    //10位时间戳
    public static int getTimeStamp(){
        return (int)(System.currentTimeMillis() / 1000);
    }
    // 当天0点时间戳
    public static int getTodayStartTimeStamp () {
        return (int)(getTodayStartTimeStampMilli() / 1000);
    }
    // 当天0点时间戳
    public static long getTodayStartTimeStampMilli(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }
    // 当天24点时间戳
    public static long getTodayEndTimeStampMilli(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }
    // 当天24点时间戳
    public static int getTodayEndTimeStamp () {
        return (int)(getTodayEndTimeStampMilli() / 1000);
    }
    // 本周0点时间戳
    public static long getWeekStartTimeStampMilli(){
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0,0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTimeInMillis();
    }
    // 本周0点时间戳
    public static int getWeekStartTimeStamp(){
        return (int) (getWeekStartTimeStampMilli()/1000);
    }
    //获得本周最后的时间
    public static long getWeekEndTimeStampMilli(){
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0,0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime().getTime()+ (7 * 24 * 60 * 60 * 1000 - 1000);
    }
    //获得本周最后的时间
    public static int getWeekEndTimeStamp(){
        return (int) (getWeekEndTimeStampMilli()/1000);
    }
    //获得本月第一天0点时间
    public static long getMonthStartTimeStampMilli(){
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0,0);
        cal.set(Calendar.DAY_OF_MONTH,cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTimeInMillis();
    }
    //获得本月第一天0点时间
    public static int getMonthStartTimeStamp(){
        return (int) (getMonthStartTimeStampMilli()/1000);
    }
    //获得本月最后的时间
    public static long getMonthEndTimeStampMilli(){
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0,0);
        cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }
    //获得本月最后的时间
    public static int getMonthEndTimeStamp(){
        return (int) (getMonthEndTimeStampMilli()/1000);
    }
}
