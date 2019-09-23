package com.niule.znxj.web.model;

public class CheckitemTask {
    private Long id;

    private Long checkitemid;

    private Long taskid;

    public CheckitemTask() {
    }

    public CheckitemTask(Long checkitemid, Long taskid) {
        this.checkitemid = checkitemid;
        this.taskid = taskid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCheckitemid() {
        return checkitemid;
    }

    public void setCheckitemid(Long checkitemid) {
        this.checkitemid = checkitemid;
    }

    public Long getTaskid() {
        return taskid;
    }

    public void setTaskid(Long taskid) {
        this.taskid = taskid;
    }
}