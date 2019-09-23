package com.niule.znxj.web.model.taskresponse;

import com.niule.znxj.web.model.Taskplaninfo;

import java.util.Date;

/**
 * Created by MrD on 2017/3/28.
 */
public class TaskTempRes extends Taskplaninfo {
    private Long tempid;
    private Date executetime;
    private String classname;
    private String directorname;//负责人名字
    private Long userid;
    private String taskcode;
    private Integer operationstate;
    private Integer stopstate;

    public Integer getOperationstate() {
        return operationstate;
    }

    public void setOperationstate(Integer operationstate) {
        this.operationstate = operationstate;
    }

    public Integer getStopstate() {
        return stopstate;
    }

    public void setStopstate(Integer stopstate) {
        this.stopstate = stopstate;
    }

    public String getTaskcode() {
        return taskcode;
    }

    public void setTaskcode(String taskcode) {
        this.taskcode = taskcode;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public TaskTempRes() {
    }

    public Long getTempid() {
        return tempid;
    }

    public Date getExecutetime() {
        return executetime;
    }

    public void setTempid(Long tempid) {
        this.tempid = tempid;
    }

    public void setExecutetime(Date executetime) {
        this.executetime = executetime;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getDirectorname() {
        return directorname;
    }

    public void setDirectorname(String directorname) {
        this.directorname = directorname;
    }
}
