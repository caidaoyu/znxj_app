package com.niule.znxj.web.model;

import java.util.Date;

/**
 * 导出输油项
 */
public class ExprotReport2Content {
    private Long tempId;
    private String areaname;
    private String equipname;
    private String reportid;
    private String checkname;
    private Date executetime;
    private String worker;
    private String enumitem;
    private String equipmentskipdesc;
    private String areainouttime;
    private Date starttime;
    private Date endtime;

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Long getTempId() {
        return tempId;
    }

    public void setTempId(Long tempId) {
        this.tempId = tempId;
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

    public String getReportid() {
        return reportid;
    }

    public void setReportid(String reportid) {
        this.reportid = reportid;
    }

    public String getCheckname() {
        return checkname;
    }

    public void setCheckname(String checkname) {
        this.checkname = checkname;
    }

    public Date getExecutetime() {
        return executetime;
    }

    public void setExecutetime(Date executetime) {
        this.executetime = executetime;
    }

    public String getWorker() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker = worker;
    }

    public String getEnumitem() {
        return enumitem;
    }

    public void setEnumitem(String enumitem) {
        this.enumitem = enumitem;
    }

    public String getAreainouttime() {
        return areainouttime;
    }

    public void setAreainouttime(String areainouttime) {
        this.areainouttime = areainouttime;
    }
}
