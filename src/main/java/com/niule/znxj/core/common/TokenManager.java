package com.niule.znxj.core.common;//package com.niule.znxj.core.common;
//
//import com.niule.znxj.web.model.Tokeninfo;
//import com.niule.znxj.web.service.TokenInfoService;
//import org.springframework.util.StringUtils;
//
//import javax.annotation.Resource;
//import java.util.*;
//
///**
// * Created by MrD on 2016/10/20.
// */
//public class TokenManager {
//
//    private static TokenInfoService tokenInfoService;
//    /**
//     * Token
//     */
//    private static Map<String, TokenBean> tokenMap = new Hashtable<String, TokenBean>();
//
//    public static TokenInfoService getTokenInfoService() {
//        return tokenInfoService;
//    }
//
//    public static void setTokenInfoService(TokenInfoService tokenInfoService) {
//        if(TokenManager.tokenInfoService == null )
//            TokenManager.tokenInfoService = tokenInfoService;
//    }
//
//    /**
//     * 保存token
//     *
//     * @param key
//     * @param token
//     */
//    public static void setToken(String key,Long userid, String devicetoken, String token ,Integer devicetype) {
//        if (tokenMap.containsKey(key)) {
//            tokenMap.get(key).setKey(userid);
//            tokenMap.get(key).setToken(token);
//            tokenMap.get(key).setDevicetype(devicetype);
//            if (!StringUtils.isEmpty(devicetoken)) {
//                tokenMap.get(key).setDevicetoken(devicetoken);
//            }
//            tokenMap.get(key).setLastlogin(new Date());
//            tokenMap.get(key).setLastonline(new Date());
//        } else {
//            TokenBean item = new TokenBean();
//            item.setKey(userid);
//            item.setToken(token);
//            item.setDevicetype(devicetype);
//            if (!StringUtils.isEmpty(devicetoken)) {
//                item.setDevicetoken(devicetoken);
//            }
//            item.setLastlogin(new Date());
//            item.setLastonline(new Date());
//            tokenMap.put(key, item);
//        }
//        tokenInfoService.replaceToken(new Tokeninfo(userid,key,token,devicetoken,devicetype,new Date(),new Date()));
//    }
//
//    /**
//     * 更新时间
//     *
//     * @param key
//     */
//    public static void updateToken(String key) {
//        if (key == null) {
//            return;
//        }
//        TokenBean item = tokenMap.get(key);
//        if (item != null) {
//            item.setLastonline(new Date());
////            tokenInfoService.replaceToken(new Tokeninfo(item.getKey(),key,item.getToken(),item.getDevicetoken(),item.getDevicetype(),item.getLastlogin(),new Date()));
//        }
//    }
//
//    /**
//     * 获取tokenString
//     *
//     * @param key
//     */
//    public static String getTokenString(String key) {
//        if (key == null) {
//            return null;
//        }
//        TokenBean item = tokenMap.get(key);
//        if (item != null) {
//            return item.getToken();
//        }
//        return null;
//    }
//
//    /**
//     * 获取最近在线时间
//     *
//     * @param key
//     */
//    public static Date getLastOnline(String key) {
//        if (key == null) {
//            return null;
//        }
//        TokenBean item = tokenMap.get(key);
//        if (item != null) {
//            return item.getLastonline();
//        }
//        return null;
//    }
//
//    /**
//     * 获取最后一次登录时间
//     *
//     * @param key
//     */
//    public static Date getLastLogin(String key) {
//        if (key == null) {
//            return null;
//        }
//        TokenBean item = tokenMap.get(key);
//        if (item != null) {
//            return item.getLastlogin();
//        }
//        return null;
//    }
//
//
//    /**
//     * 获取userid
//     *
//     * @param key
//     */
//    public static Long getIdByToken(String key) {
//        if (key == null) {
//            return null;
//        }
//        TokenBean item = tokenMap.get(key);
//        if (item != null) {
//            return item.getKey();
//        }
//        return null;
//    }
//
//
//    /**
//     * 获取device-token
//     *
//     * @param key
//     */
//    public static String getDeviceToken(String key) {
//        if (key == null) {
//            return null;
//        }
//        TokenBean item = tokenMap.get(key);
//        if (item != null) {
//            return item.getDevicetoken();
//        }
//        return null;
//    }
//
//    /**
//     * 获取devicetype
//     *
//     * @param key
//     */
//    public static Integer getDeviceType(String key) {
//        if (key == null) {
//            return null;
//        }
//        TokenBean item = tokenMap.get(key);
//        if (item != null) {
//            return item.getDevicetype();
//        }
//        return null;
//    }
//
//    /**
//     * 移除token
//     *
//     * @param key
//     */
//    public static void removeToken(String key) {
//        tokenMap.remove(key);
//    }
//
//    /**
//     * 清楚token
//     */
//    public static void clearToken() {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(new Date());
//        calendar.add(Calendar.HOUR, -4);
//        Date nowDate = calendar.getTime();
//
//        Iterator<Map.Entry<String, TokenBean>> it = tokenMap.entrySet().iterator();
//        while (it.hasNext()) {
//            Map.Entry<String, TokenBean> entry = it.next();
//            TokenBean tb = entry.getValue();
//            if (nowDate.after(tb.getLastonline())) {
//                it.remove();
//            }
//        }
//    }
//
//    /**
//     * 生成token
//     *
//     * @param key
//     * @return
//     */
//    public static String createToken(String username , Long key, String devicetoken , Integer devicetype) {
//        String token = UUID.randomUUID().toString().replaceAll("-", "");
//        setToken(username ,key, devicetoken, token , devicetype);
//        return token;
//    }
//
//    public static void loadTokenFromDB(List<Tokeninfo> tokens){
//        for(Tokeninfo info : tokens){
//            TokenBean tokenBean = new TokenBean();
//            tokenBean.setKey(info.getUserid());
//            tokenBean.setDevicetype(info.getDeviceType());
//            tokenBean.setToken(info.getToken());
//            tokenBean.setLastonline(info.getLastonlinetime());
//            tokenBean.setLastlogin(info.getLastlogintime());
//            tokenBean.setDevicetoken(info.getDeviceToken());
//            tokenMap.put(info.getUsername(),tokenBean);
//        }
//    }
//}
