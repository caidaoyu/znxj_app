package com.niule.znxj.web.model;

import com.niule.znxj.core.util.json.JsonUtil;
import com.niule.znxj.web.model.taskcontent.TaskContent;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Areainfo {
    private Long id;

    private String customid;

    private String longitude; //经度

    private String latitude; //纬度

    private Long plant; //厂区id

    private Long nfctag; //nfc标签id

    private String desccontent; //区域描述

    private Double radiusnumber; //覆盖半径
    private Siteareainfo site;
    private Nfcinfo nfc;

    private Boolean isChecked;

    public Boolean getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(Boolean checked) {
        isChecked = checked;
    }

    public Siteareainfo getSite() {
        return site;
    }

    public void setSite(Siteareainfo site) {
        this.site = site;
    }

    public void setNfc(Nfcinfo nfc) {
        this.nfc = nfc;
    }

    public Nfcinfo getNfc() {

        return nfc;
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

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    public Long getPlant() {
        return plant;
    }

    public void setPlant(Long plant) {
        this.plant = plant;
    }

    public Long getNfctag() {
        return nfctag;
    }

    public void setNfctag(Long nfctag) {
        this.nfctag = nfctag;
    }

    public String getDesccontent() {
        return desccontent;
    }

    public void setDesccontent(String desccontent) {
        this.desccontent = desccontent == null ? null : desccontent.trim();
    }

    public Double getRadiusnumber() {
        return radiusnumber;
    }

    public void setRadiusnumber(Double radiusnumber) {
        this.radiusnumber = radiusnumber;
    }


}