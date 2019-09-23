package com.niule.znxj.web.model;

public class AreaTask {
    private Long id;

    private Long areaid;

    private Long taskid;

    public AreaTask() {
    }
    public AreaTask(Long areaid, Long taskid) {
        this.areaid = areaid;
        this.taskid = taskid;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAreaid() {
        return areaid;
    }

    public void setAreaid(Long areaid) {
        this.areaid = areaid;
    }

    public Long getTaskid() {
        return taskid;
    }

    public void setTaskid(Long taskid) {
        this.taskid = taskid;
    }
}