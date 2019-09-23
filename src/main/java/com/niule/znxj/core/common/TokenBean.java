package com.niule.znxj.core.common;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by MrD on 2016/10/20.
 */
public class TokenBean implements Serializable {
    private String token;
    private Date lastlogin;
    private Date lastonline;
    private Long key;
    private String devicetoken;
    private Integer devicetype; //0 android 1 ios

    public TokenBean() {
    }

    public TokenBean(String token, Date lastonline, Long key, String devicetoken, Integer devicetype) {
        this.token = token;
        this.lastonline = lastonline;
        this.key = key;
        this.devicetoken = devicetoken;
        this.devicetype = devicetype;
    }

    public Date getLastlogin() {
        return lastlogin;
    }

    public void setLastlogin(Date lastlogin) {
        this.lastlogin = lastlogin;
    }

    public Integer getDevicetype() {
        return devicetype;
    }

    public void setDevicetype(Integer devicetype) {
        this.devicetype = devicetype;
    }

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token
     *            the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return the lastonline
     */
    public Date getLastonline() {
        return lastonline;
    }

    /**
     * @param lastonline
     *            the lastonline to set
     */
    public void setLastonline(Date lastonline) {
        this.lastonline = lastonline;
    }

    /**
     * @return the key
     */
    public Long getKey() {
        return key;
    }

    /**
     * @param key
     *            the key to set
     */
    public void setKey(Long key) {
        this.key = key;
    }

    /**
     * @return the devicetoken
     */
    public String getDevicetoken() {
        return devicetoken;
    }

    /**
     * @param devicetoken
     *            the devicetoken to set
     */
    public void setDevicetoken(String devicetoken) {
        this.devicetoken = devicetoken;
    }
}
