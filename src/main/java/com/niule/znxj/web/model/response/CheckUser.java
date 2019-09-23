package com.niule.znxj.web.model.response;

import com.niule.znxj.web.model.Userinfo;

/**
 * Created by administor on 2017/9/11.
 */
public class CheckUser {
    private Userinfo userinfo;
    private boolean isCheck;

    public Userinfo getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(Userinfo userinfo) {
        this.userinfo = userinfo;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
