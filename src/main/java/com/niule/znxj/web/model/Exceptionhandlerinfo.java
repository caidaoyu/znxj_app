package com.niule.znxj.web.model;

import java.util.Date;

public class Exceptionhandlerinfo {
    private Integer id;

    private Integer reportid;

    private String taskcode;

    private Integer checkuserid;

    private String descontent;

    private String attachment;

    private Integer operatorname;

    private Date reporttime;

    private Date confirmtime;

    private Date appointedtime;

    private Date exceptionclosetime;

    private Integer exceptionstate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReportid() {
        return reportid;
    }

    public void setReportid(Integer reportid) {
        this.reportid = reportid;
    }

    public String getTaskcode() {
        return taskcode;
    }

    public void setTaskcode(String taskcode) {
        this.taskcode = taskcode == null ? null : taskcode.trim();
    }

    public Integer getCheckuserid() {
        return checkuserid;
    }

    public void setCheckuserid(Integer checkuserid) {
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

    public Integer getOperatorname() {
        return operatorname;
    }

    public void setOperatorname(Integer operatorname) {
        this.operatorname = operatorname;
    }

    public Date getReporttime() {
        return reporttime;
    }

    public void setReporttime(Date reporttime) {
        this.reporttime = reporttime;
    }

    public Date getConfirmtime() {
        return confirmtime;
    }

    public void setConfirmtime(Date confirmtime) {
        this.confirmtime = confirmtime;
    }

    public Date getAppointedtime() {
        return appointedtime;
    }

    public void setAppointedtime(Date appointedtime) {
        this.appointedtime = appointedtime;
    }

    public Date getExceptionclosetime() {
        return exceptionclosetime;
    }

    public void setExceptionclosetime(Date exceptionclosetime) {
        this.exceptionclosetime = exceptionclosetime;
    }

    public Integer getExceptionstate() {
        return exceptionstate;
    }

    public void setExceptionstate(Integer exceptionstate) {
        this.exceptionstate = exceptionstate;
    }
}