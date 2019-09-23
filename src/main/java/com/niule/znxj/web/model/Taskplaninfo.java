package com.niule.znxj.web.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Taskplaninfo {
    private Long id;

    private Integer type;

    private String identifyingid;

    private String customid;

    private String taskdesc;

    private Integer issequentially;

    private String createuser;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createtime;

    private Integer state;

    private Integer auditstatus;

    private String revieweduser;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date reviewedtime;

    private String approveuser;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date approvetime;

    private String taskversion;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updatetime;

    private Long classid;

    private Long directorid;

    private Integer maxduration;

    private Integer issingletime;

    private String starttime;

    private String endtime;

    private String implementtime;

    private String weeklytime;

    private Long siteid;

    private String taskcontent;

    private Siteareainfo site;

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getIdentifyingid() {
        return identifyingid;
    }

    public void setIdentifyingid(String identifyingid) {
        this.identifyingid = identifyingid == null ? null : identifyingid.trim();
    }

    public String getCustomid() {
        return customid;
    }

    public void setCustomid(String customid) {
        this.customid = customid == null ? null : customid.trim();
    }

    public String getTaskdesc() {
        return taskdesc;
    }

    public void setTaskdesc(String taskdesc) {
        this.taskdesc = taskdesc == null ? null : taskdesc.trim();
    }

    public Integer getIssequentially() {
        return issequentially;
    }

    public void setIssequentially(Integer issequentially) {
        this.issequentially = issequentially;
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser == null ? null : createuser.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getAuditstatus() {
        return auditstatus;
    }

    public void setAuditstatus(Integer auditstatus) {
        this.auditstatus = auditstatus;
    }

    public String getRevieweduser() {
        return revieweduser;
    }

    public void setRevieweduser(String revieweduser) {
        this.revieweduser = revieweduser == null ? null : revieweduser.trim();
    }

    public Date getReviewedtime() {
        return reviewedtime;
    }

    public void setReviewedtime(Date reviewedtime) {
        this.reviewedtime = reviewedtime;
    }

    public String getApproveuser() {
        return approveuser;
    }

    public void setApproveuser(String approveuser) {
        this.approveuser = approveuser == null ? null : approveuser.trim();
    }

    public Date getApprovetime() {
        return approvetime;
    }

    public void setApprovetime(Date approvetime) {
        this.approvetime = approvetime;
    }

    public String getTaskversion() {
        return taskversion;
    }

    public void setTaskversion(String taskversion) {
        this.taskversion = taskversion == null ? null : taskversion.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Long getClassid() {
        return classid;
    }

    public void setClassid(Long classid) {
        this.classid = classid;
    }

    public Long getDirectorid() {
        return directorid;
    }

    public void setDirectorid(Long directorid) {
        this.directorid = directorid;
    }

    public Integer getMaxduration() {
        return maxduration;
    }

    public void setMaxduration(Integer maxduration) {
        this.maxduration = maxduration;
    }

    public Integer getIssingletime() {
        return issingletime;
    }

    public void setIssingletime(Integer issingletime) {
        this.issingletime = issingletime;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime == null ? null : starttime.trim();
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime == null ? null : endtime.trim();
    }

    public String getImplementtime() {
        return implementtime;
    }

    public void setImplementtime(String implementtime) {
        this.implementtime = implementtime == null ? null : implementtime.trim();
    }

    public String getWeeklytime() {
        return weeklytime;
    }

    public void setWeeklytime(String weeklytime) {
        this.weeklytime = weeklytime == null ? null : weeklytime.trim();
    }

    public Long getSiteid() {
        return siteid;
    }

    public void setSiteid(Long siteid) {
        this.siteid = siteid;
    }

    public String getTaskcontent() {
        return taskcontent;
    }

    public void setTaskcontent(String taskcontent) {
        this.taskcontent = taskcontent == null ? null : taskcontent.trim();
    }
}