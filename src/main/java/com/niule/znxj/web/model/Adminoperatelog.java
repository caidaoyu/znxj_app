package com.niule.znxj.web.model;

import java.util.Date;

public class Adminoperatelog {
    private Long id;

    private Long adminid;

    private Integer operatetype;

    private String operatecontent;

    private Date operatetime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAdminid() {
        return adminid;
    }

    public void setAdminid(Long adminid) {
        this.adminid = adminid;
    }

    public Integer getOperatetype() {
        return operatetype;
    }

    public void setOperatetype(Integer operatetype) {
        this.operatetype = operatetype;
    }

    public String getOperatecontent() {
        return operatecontent;
    }

    public void setOperatecontent(String operatecontent) {
        this.operatecontent = operatecontent == null ? null : operatecontent.trim();
    }

    public Date getOperatetime() {
        return operatetime;
    }

    public void setOperatetime(Date operatetime) {
        this.operatetime = operatetime;
    }
}