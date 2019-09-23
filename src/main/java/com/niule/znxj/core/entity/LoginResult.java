package com.niule.znxj.core.entity;

/**
 * Created by MrD on 2016/12/6.
 */
public class LoginResult<T> extends JSONResult<T>{
    private String token;

    public LoginResult(T data){
        super();
        setData(data);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
