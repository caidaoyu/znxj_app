package com.niule.znxj.web.model;

public class Reportcontent {
    private Integer id;

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

    private String operationtime;

    private Long reportid;

    private String lowerwarning;

    private String normalmax;

    private String normalmin;

    private String upperwarning;

    private String video;

    private String audio;

    private String img;

    private String checkvalue;

    private String enumitem;


    private String firstValue;

    private Taskreportinfo report;

    private Long userid;

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getFirstValue() {
        return firstValue;
    }
    public void setFirstValue(String firstValue) {
        this.firstValue = firstValue;
    }

    public Taskreportinfo getReport() {
        return report;
    }

    public void setReport(Taskreportinfo report) {
        this.report = report;
    }
    public Reportcontent(String areaname, String equipname, String checkname, String checktype, String numvalue, String recordname, String unitname, String reportstate, String errcontent, Integer areaskip, Integer equipmentskip, String areaskipdesc, String equipmentskipdesc, String operationtime, Long reportid, String lowerwarning, String normalmax, String normalmin, String upperwarning, String video, String audio, String img, String enumitem, String equipinouttime, String areainouttime,Long userid) {
        this.areaname = areaname;
        this.equipname = equipname;
        this.checkname = checkname;
        this.checktype = checktype;
        this.numvalue = numvalue;
        this.recordname = recordname;
        this.unitname = unitname;
        this.reportstate = reportstate;
        this.errcontent = errcontent;
        this.areaskip = areaskip;
        this.equipmentskip = equipmentskip;
        this.areaskipdesc = areaskipdesc;
        this.equipmentskipdesc = equipmentskipdesc;
        this.operationtime = operationtime;
        this.reportid = reportid;
        this.lowerwarning = lowerwarning;
        this.normalmax = normalmax;
        this.normalmin = normalmin;
        this.upperwarning = upperwarning;
        this.video = video;
        this.audio = audio;
        this.img = img;
        this.enumitem = enumitem;
        this.userid=userid;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname == null ? null : areaname.trim();
    }

    public String getEquipname() {
        return equipname;
    }

    public void setEquipname(String equipname) {
        this.equipname = equipname == null ? null : equipname.trim();
    }

    public String getCheckname() {
        return checkname;
    }

    public void setCheckname(String checkname) {
        this.checkname = checkname == null ? null : checkname.trim();
    }

    public String getChecktype() {
        return checktype;
    }

    public void setChecktype(String checktype) {
        this.checktype = checktype == null ? null : checktype.trim();
    }

    public String getNumvalue() {
        return numvalue;
    }

    public void setNumvalue(String numvalue) {
        this.numvalue = numvalue == null ? null : numvalue.trim();
    }

    public String getRecordname() {
        return recordname;
    }

    public void setRecordname(String recordname) {
        this.recordname = recordname == null ? null : recordname.trim();
    }

    public String getUnitname() {
        return unitname;
    }

    public void setUnitname(String unitname) {
        this.unitname = unitname == null ? null : unitname.trim();
    }

    public String getReportstate() {
        return reportstate;
    }

    public void setReportstate(String reportstate) {
        this.reportstate = reportstate == null ? null : reportstate.trim();
    }

    public String getErrcontent() {
        return errcontent;
    }

    public void setErrcontent(String errcontent) {
        this.errcontent = errcontent == null ? null : errcontent.trim();
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

    public String getAreaskipdesc() {
        return areaskipdesc;
    }

    public void setAreaskipdesc(String areaskipdesc) {
        this.areaskipdesc = areaskipdesc == null ? null : areaskipdesc.trim();
    }

    public String getEquipmentskipdesc() {
        return equipmentskipdesc;
    }

    public void setEquipmentskipdesc(String equipmentskipdesc) {
        this.equipmentskipdesc = equipmentskipdesc == null ? null : equipmentskipdesc.trim();
    }

    public String getOperationtime() {
        return operationtime;
    }

    public void setOperationtime(String operationtime) {
        this.operationtime = operationtime == null ? null : operationtime.trim();
    }

    public Long getReportid() {
        return reportid;
    }

    public void setReportid(Long reportid) {
        this.reportid = reportid;
    }

    public String getLowerwarning() {
        return lowerwarning;
    }

    public void setLowerwarning(String lowerwarning) {
        this.lowerwarning = lowerwarning == null ? null : lowerwarning.trim();
    }

    public String getNormalmax() {
        return normalmax;
    }

    public void setNormalmax(String normalmax) {
        this.normalmax = normalmax == null ? null : normalmax.trim();
    }

    public String getNormalmin() {
        return normalmin;
    }

    public void setNormalmin(String normalmin) {
        this.normalmin = normalmin == null ? null : normalmin.trim();
    }

    public String getUpperwarning() {
        return upperwarning;
    }

    public void setUpperwarning(String upperwarning) {
        this.upperwarning = upperwarning == null ? null : upperwarning.trim();
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video == null ? null : video.trim();
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio == null ? null : audio.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public String getCheckvalue() {
        return checkvalue;
    }

    public void setCheckvalue(String checkvalue) {
        this.checkvalue = checkvalue == null ? null : checkvalue.trim();
    }

    public String getEnumitem() {
        return enumitem;
    }

    public void setEnumitem(String enumitem) {
        this.enumitem = enumitem == null ? null : enumitem.trim();
    }
    public Reportcontent(String areaname, String equipname, String checkname, String checktype, String numvalue, String recordname, String unitname, String reportstate, String errcontent, Integer areaskip, Integer equipmentskip, String areaskipdesc, String equipmentskipdesc, Long reportid, String lowerwarning, String normalmax, String normalmin, String upperwarning, String video, String audio, String img) {
        this.areaname = areaname;
        this.equipname = equipname;
        this.checkname = checkname;
        this.checktype = checktype;
        this.numvalue = numvalue;
        this.recordname = recordname;
        this.unitname = unitname;
        this.reportstate = reportstate;
        this.errcontent = errcontent;
        this.areaskip = areaskip;
        this.equipmentskip = equipmentskip;
        this.areaskipdesc = areaskipdesc;
        this.equipmentskipdesc = equipmentskipdesc;
        this.reportid = reportid;
        this.lowerwarning = lowerwarning;
        this.normalmax = normalmax;
        this.normalmin = normalmin;
        this.upperwarning = upperwarning;
        this.video = video;
        this.audio = audio;
        this.img = img;
    }
    public Reportcontent(String areaname, String equipname, String checkname, String checktype, String numvalue, String recordname, String unitname, String reportstate, String errcontent, Integer areaskip, Integer equipmentskip, String areaskipdesc, String equipmentskipdesc, String operationtime, Long reportid, String lowerwarning, String normalmax, String normalmin, String upperwarning, String video, String audio, String img) {
        this.areaname = areaname;
        this.equipname = equipname;
        this.checkname = checkname;
        this.checktype = checktype;
        this.numvalue = numvalue;
        this.recordname = recordname;
        this.unitname = unitname;
        this.reportstate = reportstate;
        this.errcontent = errcontent;
        this.areaskip = areaskip;
        this.equipmentskip = equipmentskip;
        this.areaskipdesc = areaskipdesc;
        this.equipmentskipdesc = equipmentskipdesc;
        this.operationtime = operationtime;
        this.reportid = reportid;
        this.lowerwarning = lowerwarning;
        this.normalmax = normalmax;
        this.normalmin = normalmin;
        this.upperwarning = upperwarning;
        this.video = video;
        this.audio = audio;
        this.img = img;
    }
    public Reportcontent(){};
}