package com.niule.znxj.core.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import static org.springframework.util.StringUtils.isEmpty;

/**
 * Created by MrD on 2016/10/9.
 */
public class StringUtils {

    /**
     * 生成随机数字
     *
     * @return
     */
    private static String getRandomInt() {
        Random random = new Random();
        return String.valueOf(random.nextInt(10));
    }
    /**
     * 生成验证码
     *
     * @return
     */
    public static String createSecurityCode() {
        StringBuilder br = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            br.append(getRandomInt());
        }
        return br.toString();
    }

    /**
     * 字符串转日期
     *
     * @param val
     * @return
     * @throws ParseException
     */
    public static Date convertToDatetime(String val) throws ParseException {
        if (isEmpty(val)) {
            return null;
        }
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return fmt.parse(val);
    }


    /**
     * 获取时间 时
     *
     * @param val
     * @return
     */
    public static Integer getHours(String val){
        if (isEmpty(val)) {
            return null;
        }
        return Integer.parseInt(val.substring(0,2));
    }

    /**
     * 获取时间 时
     *
     * @param val
     * @return
     */
    public static Integer getMinutes(String val){
        if (isEmpty(val)) {
            return null;
        }
        return Integer.parseInt(val.substring(3,5));
    }

}
