package com.niule.znxj.web.model;

import java.util.Date;

public class Exceptionhandlerinfo {
    private Long id;

    private Long reportid;

    private String taskcode;

    private Long checkuserid;

    private String descontent;

    private String attachment;

    private String operatorname;

    private Date reporttime;

    private Date confirmtime;

    private Date appointedtime;

    private Date exceptionclosetime;

    private Integer exceptionstate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReportid() {
        return reportid;
    }

    public void setReportid(Long reportid) {
        this.reportid = reportid;
    }

    public String getTaskcode() {
        return taskcode;
    }

    public void setTaskcode(String taskcode) {
        this.taskcode = taskcode == null ? null : taskcode.trim();
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