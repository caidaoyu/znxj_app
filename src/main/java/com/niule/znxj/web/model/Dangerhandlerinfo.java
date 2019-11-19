package com.niule.znxj.web.model;

import java.util.Date;

public class Dangerhandlerinfo {
    private Long id;

    private Long reportid;

    private Long checkuserid;

    private String descontent;

    private String attachment;

    private Long operatorid;

    private String operatorname;

    private Date reporttime;

    private Date appointedtime;

    private Date dangerclosetime;

    private Integer dangerstate;

    private String  reportcode;

    private String username;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReportid() {
        return reportid;
    }

    public String getReportcode() {
        return reportcode;
    }

    public void setReportcode(String reportcode) {
        this.reportcode = reportcode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setReportid(Long reportid) {
        this.reportid = reportid;
    }

    public Long getCheckuserid() {
        return checkuserid;
    }

    public void setCheckuserid(Long checkuserid) {
        this.checkuserid = checkuserid;
    }

    public String getDescontent() {
        return descontent;
    }

    public void setDescontent(String descontent) {
        this.descontent = descontent == null ? null : descontent.trim();
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment == null ? null : attachment.trim();
    }

    public Long getOperatorid() {
        return operatorid;
    }

    public void setOperatorid(Long operatorid) {
        this.operatorid = operatorid;
    }

    public String getOperatorname() {
        return operatorname;
    }

    public void setOperatorname(String operatorname) {
        this.operatorname = operatorname == null ? null : operatorname.trim();
    }

    public Date getReporttime() {
        return reporttime;
    }

    public void setReporttime(Date reporttime) {
        this.reporttime = reporttime;
    }

    public Date getAppointedtime() {
        return appointedtime;
    }

    public void setAppointedtime(Date appointedtime) {
        this.appointedtime = appointedtime;
    }

    public Date getDangerclosetime() {
        return dangerclosetime;
    }

    public void setDangerclosetime(Date dangerclosetime) {
        this.dangerclosetime = dangerclosetime;
    }

    public Integer getDangerstate() {
        return dangerstate;
    }

    public void setDangerstate(Integer dangerstate) {
        this.dangerstate = dangerstate;
    }
}