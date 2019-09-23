package com.niule.znxj.web.model;

import java.util.Date;
import java.util.List;

/**
 * Created by administor on 2017/4/27.
 */
public class TaskReportContent {
    private String areaname;
    private String equipname;
    private String checkname;
    private String checktype;
    private String numvalue;
    private String recordname;
    private String unitname;
    private String reportstate;
    private String errcontent;
    private Integer areaskip;
    private Integer equipmentskip;
    private String areaskipdesc;
    private String equipmentskipdesc;
    private List<String> video;
    private List<String> audio;
    private List<String> img;
    private float lowerwarning;
    private float normalmax;
    private float normalmin;
    private float upperwarning;
    private Date operationtime;
    private String checkvalue;
    private String enumitem;
    private String equipinouttime;
    private Long userid;

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getEnumitem() {
        return enumitem;
    }

    public void setEnumitem(String enumitem) {
        this.enumitem = enumitem;
    }

    public String getEquipinouttime() {
        return equipinouttime;
    }

    public void setEquipinouttime(String equipinouttime) {
        this.equipinouttime = equipinouttime;
    }

    public String getAreainouttime() {
        return areainouttime;
    }

    public void setAreainouttime(String areainouttime) {
        this.areainouttime = areainouttime;
    }

    private String areainouttime;


    public String getCheckvalue() {
        return checkvalue;
    }

    public void setCheckvalue(String checkvalue) {
        this.checkvalue = checkvalue;
    }

    public Date getOperationtime() {
        return operationtime;
    }

    public void setOperationtime(Date operationtime) {
        this.operationtime = operationtime;
    }

    public float getLowerwarning() {
        return lowerwarning;
    }

    public void setLowerwarning(float lowerwarning) {
        this.lowerwarning = lowerwarning;
    }

    public float getNormalmax() {
        return normalmax;
    }

    public void setNormalmax(float normalmax) {
        this.normalmax = normalmax;
    }

    public float getNormalmin() {
        return normalmin;
    }

    public void setNormalmin(float normalmin) {
        this.normalmin = normalmin;
    }

    public float getUpperwarning() {
        return upperwarning;
    }

    public void setUpperwarning(float upperwarning) {
        this.upperwarning = upperwarning;
    }

    public String getAreaskipdesc() {
        return areaskipdesc;
    }

    public void setAreaskipdesc(String areaskipdesc) {
        this.areaskipdesc = areaskipdesc;
    }

    public String getEquipmentskipdesc() {
        return equipmentskipdesc;
    }

    public void setEquipmentskipdesc(String equipmentskipdesc) {
        this.equipmentskipdesc = equipmentskipdesc;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    public String getEquipname() {
        return equipname;
    }

    public void setEquipname(String equipname) {
        this.equipname = equipname;
    }

    public String getCheckname() {
        return checkname;
    }

    public void setCheckname(String checkname) {
        this.checkname = checkname;
    }

    public String getChecktype() {
        return checktype;
    }

    public void setChecktype(String checktype) {
        this.checktype = checktype;
    }

    public String getNumvalue() {
        return numvalue;
    }

    public void setNumvalue(String numvalue) {
        this.numvalue = numvalue;
    }

    public String getRecordname() {
        return recordname;
    }

    public void setRecordname(String recordname) {
        this.recordname = recordname;
    }

    public String getUnitname() {
        return unitname;
    }

    public void setUnitname(String unitname) {
        this.unitname = unitname;
    }

    public String getReportstate() {
        return reportstate;
    }

    public void setReportstate(String reportstate) {
        this.reportstate = reportstate;
    }

    public String getErrcontent() {
        return errcontent;
    }

    public void setErrcontent(String errcontent) {
        this.errcontent = errcontent;
    }

    public Integer getAreaskip() {
        return areaskip;
    }

    public void setAreaskip(Integer areaskip) {
        this.areaskip = areaskip;
    }

    public Integer getEquipmentskip() {
        return equipmentskip;
    }

    public void setEquipmentskip(Integer equipmentskip) {
        this.equipmentskip = equipmentskip;
    }

    public List<String> getVideo() {
        return video;
    }

    public void setVideo(List<String> video) {
        this.video = video;
    }

    public List<String> getAudio() {
        return audio;
    }

    public void setAudio(List<String> audio) {
        this.audio = audio;
    }

    public List<String> getImg() {
        return img;
    }

    public void setImg(List<String> img) {
        this.img = img;
    }
}
