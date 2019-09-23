package com.niule.znxj.web.model.response;

import com.niule.znxj.web.model.Userinfo;
import com.niule.znxj.web.model.Warningtasktype;

import java.util.List;

/**
 * Created by administor on 2017/4/19.
 */
public class LoginResponse {
    private Userinfo userinfo;
    private List<Warningtasktype> warningtypes;
    private List<Warningtasktype> warninglevels;
    private List<Warningtasktype> stopreasons;
    public LoginResponse() {
    }

    public Userinfo getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(Userinfo userinfo) {
        this.userinfo = userinfo;
    }

    public List<Warningtasktype> getWarningtypes() {
        return warningtypes;
    }

    public void setWarningtypes(List<Warningtasktype> warningtypes) {
        this.warningtypes = warningtypes;
    }

    public List<Warningtasktype> getWarninglevels() {
        return warninglevels;
    }

    public void setWarninglevels(List<Warningtasktype> warninglevels) {
        this.warninglevels = warninglevels;
    }

    public List<Warningtasktype> getStopreasons() {
        return stopreasons;
    }

    public void setStopreasons(List<Warningtasktype> stopreasons) {
        this.stopreasons = stopreasons;
    }
}
