package com.niule.znxj.web.model;

import java.util.Date;

public class Taskuploadconfig {
    private Long id;

    private Long siteid;

    private Long taskid;

    private Long contactid;

    private Integer uploadstate;

    private Date createtime;

    private Date updatetime;

    private String identifyingid;
    private String sitename;
    private String email;
    private String ip;
    private String port;
    private String enginetype;

    public String getIdentifyingid() {
        return identifyingid;
    }

    public void setIdentifyingid(String identifyingid) {
        this.identifyingid = identifyingid;
    }

    public String getSitename() {
        return sitename;
    }

    public void setSitename(String sitename) {
        this.sitename = sitename;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getEnginetype() {
        return enginetype;
    }

    public void setEnginetype(String enginetype) {
        this.enginetype = enginetype;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSiteid() {
        return siteid;
    }

    public void setSiteid(Long siteid) {
        this.siteid = siteid;
    }

    public Long getTaskid() {
        return taskid;
    }

    public void setTaskid(Long taskid) {
        this.taskid = taskid;
    }

    public Long getContactid() {
        return contactid;
    }

    public void setContactid(Long contactid) {
        this.contactid = contactid;
    }

    public Integer getUploadstate() {
        return uploadstate;
    }

    public void setUploadstate(Integer uploadstate) {
        this.uploadstate = uploadstate;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}