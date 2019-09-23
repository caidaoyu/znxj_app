package com.niule.znxj.web.model.response;

import java.util.Date;

/**
 * Created by administor on 2017/5/3.
 */
public class TaskSimpleReport  {
    private String taskcode;//任务编号
    private String taskname;//任务名称
    private Date starttime; //开始时间
    private Date endtime;//结束时间
    private int areaCnt;//区域数
    private int equipmentCnt;//设备数
    private int itemCnt;//项目数
    private int errorCnt;//异常数
    private int executeState;//执行状态
    private int uploadState;//上传状态

    public String getTaskcode() {
        return taskcode;
    }

    public void setTaskcode(String taskcode) {
        this.taskcode = taskcode;
    }

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

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

    public int getAreaCnt() {
        return areaCnt;
    }

    public void setAreaCnt(int areaCnt) {
        this.areaCnt = areaCnt;
    }

    public int getEquipmentCnt() {
        return equipmentCnt;
    }

    public void setEquipmentCnt(int equipmentCnt) {
        this.equipmentCnt = equipmentCnt;
    }

    public int getItemCnt() {
        return itemCnt;
    }

    public void setItemCnt(int itemCnt) {
        this.itemCnt = itemCnt;
    }

    public int getErrorCnt() {
        return errorCnt;
    }

    public void setErrorCnt(int errorCnt) {
        this.errorCnt = errorCnt;
    }

    public int getExecuteState() {
        return executeState;
    }

    public void setExecuteState(int executeState) {
        this.executeState = executeState;
    }

    public int getUploadState() {
        return uploadState;
    }

    public void setUploadState(int uploadState) {
        this.uploadState = uploadState;
    }
}
