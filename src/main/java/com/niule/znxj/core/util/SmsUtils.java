package com.niule.znxj.core.util;

import com.niule.znxj.core.common.Resources;

import java.text.ParseException;
import java.util.Date;

/**
 * 短信息发送
 * Created by MrD on 2016/10/9.
 */
public class SmsUtils {
    public static String sendVerMessage(String phonenumber,String content){
        String res = "";
//        try {
//            res = HttpSender.send(Resources.ApplicationResources.getString("sms.url"),
//                    Resources.ApplicationResources.getString("sms.account"),
//                    Resources.ApplicationResources.getString("sms.password"),
//                    phonenumber,
//                    content, true, "", "001");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return res;
    }

    /**
     * 获取短信返回 发送时间
     * @param val
     * @return
     * @throws ParseException
     */
    public static Date getReturnDateTime(String val) throws ParseException {
        return StringUtils.convertToDatetime(val.substring(0,4)+"-"+val.substring(4,6)+"-"+val.substring(6,8)
                +" "+val.substring(8,10)+":"+val.substring(10,12)+":"+val.substring(12,14));
    }

    /**
     * 获取短信发送返回状态
     * @param val
     * @return
     */
    public static String getReturnStatus(String val){
        return val.substring(15,16);
    }

}
