package com.niule.znxj.web.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Classinfo {
    private Long id;

    private String customid;

    private String classdesc;
@DateTimeFormat(pattern="yyyy-MM-dd")
    private Date workstarttime;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date workendtime;

    private Long directorid;

    private Long siteareaid;

    private Userinfo userinfo;

    private Siteareainfo site;

    public Userinfo getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(Userinfo userinfo) {
        this.userinfo = userinfo;
    }

    public Siteareainfo getSite() {
        return site;
    }

    public void setSite(Siteareainfo site) {
        this.site = site;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomid() {
        return customid;
    }

    public void setCustomid(String customid) {
        this.customid = customid == null ? null : customid.trim();
    }

    public String getClassdesc() {
        return classdesc;
    }

    public void setClassdesc(String classdesc) {
        this.classdesc = classdesc == null ? null : classdesc.trim();
    }

    public Date getWorkstarttime() {
        return workstarttime;
    }

    public void setWorkstarttime(Date workstarttime) {
        this.workstarttime = workstarttime;
    }

    public Date getWorkendtime() {
        return workendtime;
    }

    public void setWorkendtime(Date workendtime) {
        this.workendtime = workendtime;
    }

    public Long getDirectorid() {
        return directorid;
    }

    public void setDirectorid(Long directorid) {
        this.directorid = directorid;
    }

    public Long getSiteareaid() {
        return siteareaid;
    }

    public void setSiteareaid(Long siteareaid) {
        this.siteareaid = siteareaid;
    }
}