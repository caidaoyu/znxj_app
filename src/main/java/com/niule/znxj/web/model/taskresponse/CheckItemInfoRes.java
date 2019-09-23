package com.niule.znxj.web.model.taskresponse;

import com.niule.znxj.web.model.Checkiteminfo;

/**
 * Created by MrD on 2017/3/27.
 */
public class CheckItemInfoRes extends Checkiteminfo{
    private Checkiteminfo checkiteminfo;

    public CheckItemInfoRes() {
    }

    public CheckItemInfoRes(Checkiteminfo checkiteminfo) {
        this.checkiteminfo = checkiteminfo;
    }

    public Checkiteminfo getCheckiteminfo() {
        return checkiteminfo;
    }

    public void setCheckiteminfo(Checkiteminfo checkiteminfo) {
        this.checkiteminfo = checkiteminfo;
    }
}
