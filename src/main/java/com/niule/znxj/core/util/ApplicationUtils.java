package com.niule.znxj.core.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.UUID;
import java.util.regex.Pattern;

/**
 * ApplicationUtils : 程序工具类，提供大量的便捷方法
 *
 * @author StarZou
 * @since 2014-09-28 22:31
 */
public class ApplicationUtils {

    /**
     * 产生一个36个字符的UUID
     *
     * @return UUID
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * md5加密
     *
     * @param value 要加密的值
     * @return md5加密后的值
     */
    public static String md5Hex(String value) {
        return DigestUtils.md5Hex(value);
    }

    /**
     * sha1加密
     *
     * @param value 要加密的值
     * @return sha1加密后的值
     */
    public static String sha1Hex(String value) {
        return DigestUtils.sha1Hex(value);
    }

    /**
     * sha256加密
     *
     * @param value 要加密的值
     * @return sha256加密后的值
     */
    public static String sha256Hex(String value) {
        return DigestUtils.sha256Hex(value);
    }


    // 手机号码正则 13[0-9],45,47,15[0-9],70,73,76,77,18[0-9]
    public final static String REG_EXP_MOBILE = "^[1]([3][0-9]{1}|45|47|[5][0-9]{1}|70|73|76|77|[8][0-9]{1})[0-9]{8}$";

    // 判断手机是否符合正则标准
    public static boolean checkPhonenumber(String phonenumber) {
        return Pattern.compile(REG_EXP_MOBILE).matcher(phonenumber).find();
    }

}
