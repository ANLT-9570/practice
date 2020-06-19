package com.dg.main.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {
    /**
     * 取得系统时间
     * @param pattern eg:yyyy-MM-dd HH:mm:ss,SSS
     * @return
     */
    public static String getSysTime(String pattern) {

        return formatSysTime(new SimpleDateFormat(pattern));
    }

    /**
     * 格式化系统时间
     * @param format
     * @return
     */
    private static String formatSysTime(SimpleDateFormat format) {

        String str = format.format(Calendar.getInstance().getTime());
        return str;
    }

    public static String getTodayTimeDD(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
        String dateTime = simpleDateFormat.format(new Date());
        return dateTime;
    }
    public static String getMonthTimeMM(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM");
        String dateTime = simpleDateFormat.format(new Date());
        return dateTime;
    }
    //上个月的时间
    public static String getUpMonthTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM");
        Date date = new Date();
        Calendar calendar= Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH,calendar.get(Calendar.MONTH)-1);
        return simpleDateFormat.format(calendar.getTime());
    }

    public static String getDisposeMM(String dateTime){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD");
        try {
            Date date = simpleDateFormat.parse(dateTime);
            SimpleDateFormat sf = new SimpleDateFormat("YYYY-MM");
            dateTime = sf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateTime;
    }
    public static String getDisposeDD(String dateTime){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD");
        dateTime = simpleDateFormat.format(dateTime);
        return dateTime;
    }

    public static String getDay(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("DD");
        Date date = new Date();
        String day = simpleDateFormat.format(date);
        return day;
    }

    public static String getMonth(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM");
        Date date = new Date();
        String day = simpleDateFormat.format(date);
        return day;
    }

    public static String getYear(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY");
        Date date = new Date();
        String day = simpleDateFormat.format(date);
        return day;
    }

    public static String getDisposeMonth(String dateTime){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD");
        try {
            Date date = simpleDateFormat.parse(dateTime);
            SimpleDateFormat sf = new SimpleDateFormat("MM");
            dateTime = sf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateTime;
    }
    public static String getDisposeDay(String dateTime){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
        try {
            Date date = simpleDateFormat.parse(dateTime);
            SimpleDateFormat sf = new SimpleDateFormat("dd");
            dateTime = sf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateTime;
    }
    public static String getDisposeYear(String dateTime){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
        try {
            Date date = simpleDateFormat.parse(dateTime);
            SimpleDateFormat sf = new SimpleDateFormat("YYYY");
            dateTime = sf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateTime;
    }
    public static void main(String[] args) {
        String year = getYear();
        System.out.println(year);
    }
}
