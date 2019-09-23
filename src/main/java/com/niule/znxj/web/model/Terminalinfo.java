package com.niule.znxj.web.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Terminalinfo {
    private Long id;

    private String customid;

    private String hardwaremodel;

    private String softversion;

    private String desccontent;

    private String department;

    private String authcode;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date enabletime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date unenabletime;

    private Integer state;

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

    public String getHardwaremodel() {
        return hardwaremodel;
    }

    public void setHardwaremodel(String hardwaremodel) {
        this.hardwaremodel = hardwaremodel == null ? null : hardwaremodel.trim();
    }

    public String getSoftversion() {
        return softversion;
    }

    public void setSoftversion(String softversion) {
        this.softversion = softversion == null ? null : softversion.trim();
    }

    public String getDesccontent() {
        return desccontent;
    }

    public void setDesccontent(String desccontent) {
        this.desccontent = desccontent == null ? null : desccontent.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public String getAuthcode() {
        return authcode;
    }

    public void setAuthcode(String authcode) {
        this.authcode = authcode == null ? null : authcode.trim();
    }

    public Date getEnabletime() {
        return enabletime;
    }

    public void setEnabletime(Date enabletime) {
        this.enabletime = enabletime;
    }

    public Date getUnenabletime() {
        return unenabletime;
    }

    public void setUnenabletime(Date unenabletime) {
        this.unenabletime = unenabletime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}