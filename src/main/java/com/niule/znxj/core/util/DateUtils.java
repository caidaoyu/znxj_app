package com.niule.znxj.core.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by MrD on 2016/10/25.
 */
public final class DateUtils  {
    private DateUtils() {
    }
    /**
     * 返回某一天前整点信息
     *
     * @param days ！= 0
     * @return 2014-3-3 00:00:00
     */
    public static Date getWholePointDate(int days) {
        GregorianCalendar gc = new GregorianCalendar();
        Date date = new Date();
        gc.setTime(date);
        Date aa=new Date(date.getTime() - gc.get(gc.HOUR_OF_DAY) * 60 * 60
                * 1000 - gc.get(gc.MINUTE) * 60 * 1000 - gc.get(gc.SECOND)
                * 1000 - Long.valueOf(days)*(24 * 60 * 60 * 1000));
        return aa;
    }
    public static Date getDaysDate(int days,Date date) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        return new Date(date.getTime() - gc.get(gc.HOUR_OF_DAY) * 60 * 60
                * 1000 - gc.get(gc.MINUTE) * 60 * 1000 - gc.get(gc.SECOND)
                * 1000 - days * (24 * 60 * 60 * 1000));
    }
    /**
     * 某一时间点到现在的天数
     *
     * @param lastdate
     * @return 2014-3-3 00:00:00
     */
    public static int getDays(Date lastdate) {
        Date date = new Date();
        return (int)((date.getTime() - lastdate.getTime())/(24 * 60 * 60 * 1000));
    }


    /**
     * 获取当前小时
     *
     */
    public static int getCurrentHour() {
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        return  c.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取当前分
     *
     */
    public static int getCurrentMinute() {
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        return  c.get(Calendar.MINUTE);
    }

    /**
     * 设置执行时间
     *
     */
    public static Date setTime(int hour,int minute) {
        Date date = getWholePointDate(0);
        date = org.apache.commons.lang.time.DateUtils.addHours(date,hour);
        date = org.apache.commons.lang.time.DateUtils.addMinutes(date,minute);
        date = org.apache.commons.lang.time.DateUtils.setSeconds(date,0);
        return date;
    }


}
