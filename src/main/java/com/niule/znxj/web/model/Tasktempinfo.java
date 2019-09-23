package com.niule.znxj.web.model;

import java.util.Date;

public class Tasktempinfo {
    private Long id;

    private Long taskid;

    private Date executetime;

    private Integer state;

    private Long userid;

    private String taskcode;

    private Integer type;

    private Integer operationstate;

    private Integer stopstate;

    private Date updatetime;
    private Taskstopinfo taskstopinfo;

    private Userinfo user;

    private Taskplaninfo task;

    public Taskplaninfo getTask() {
        return task;
    }

    public void setTask(Taskplaninfo task) {
        this.task = task;
    }

    public Userinfo getUser() {
        return user;
    }

    public void setUser(Userinfo user) {
        this.user = user;
    }

    public Taskstopinfo getTaskstopinfo() {
        return taskstopinfo;
    }

    public void setTaskstopinfo(Taskstopinfo taskstopinfo) {
        this.taskstopinfo = taskstopinfo;
    }

    public Tasktempinfo() {
    }

    public Tasktempinfo(Long taskid, Date executetime, Integer state, String taskcode, Integer type,Date updatetime) {
        this.taskid = taskid;
        this.executetime = executetime;
        this.state = state;
        this.taskcode = taskcode;
        this.type = type;
        this.updatetime = updatetime;
    }

    public Tasktempinfo(Long taskid,Long userid, Date executetime, Integer state, String taskcode, Integer type,Date updatetime) {
        this.taskid = taskid;
        this.userid = userid;
        this.executetime = executetime;
        this.state = state;
        this.taskcode = taskcode;
        this.type = type;
        this.updatetime = updatetime;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTaskid() {
        return taskid;
    }

    public void setTaskid(Long taskid) {
        this.taskid = taskid;
    }

    public Date getExecutetime() {
        return executetime;
    }

    public void setExecutetime(Date executetime) {
        this.executetime = executetime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getTaskcode() {
        return taskcode;
    }

    public void setTaskcode(String taskcode) {
        this.taskcode = taskcode == null ? null : taskcode.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

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

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}