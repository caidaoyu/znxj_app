package com.niule.znxj.web.model;

public class Siteareainfo {
    private Long id;

    private String customid; //厂区名称

    private String desccontent; //厂区描述

    private String remark; //厂区备注

    private Long districtid; //片区id

    private String currentSite; //是否当前用户厂区 1：是

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

    public String getDesccontent() {
        return desccontent;
    }

    public void setDesccontent(String desccontent) {
        this.desccontent = desccontent == null ? null : desccontent.trim();
    }

    public Long getDistrictid() {
        return districtid;
    }

    public void setDistrictid(Long districtid) {
        this.districtid = districtid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getCurrentSite() {
        return currentSite;
    }

    public void setCurrentSite(String currentSite) {
        this.currentSite = currentSite;
    }
}