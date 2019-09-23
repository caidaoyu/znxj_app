package com.niule.znxj.web.model;

public class Roleandpower {
    private Long roleid;

    private String persionid;

    public Long getRoleid() {
        return roleid;
    }

    public void setRoleid(Long roleid) {
        this.roleid = roleid;
    }

    public String getPersionid() {
        return persionid;
    }

    public void setPersionid(String persionid) {
        this.persionid = persionid == null ? null : persionid.trim();
    }
}