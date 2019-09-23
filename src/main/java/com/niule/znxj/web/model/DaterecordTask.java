package com.niule.znxj.web.model;

public class DaterecordTask {
    private Long id;

    private Long daterecordid;

    private Long taskid;

    public DaterecordTask() {
    }

    public DaterecordTask(Long daterecordid, Long taskid) {
        this.daterecordid = daterecordid;
        this.taskid = taskid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDaterecordid() {
        return daterecordid;
    }

    public void setDaterecordid(Long daterecordid) {
        this.daterecordid = daterecordid;
    }

    public Long getTaskid() {
        return taskid;
    }

    public void setTaskid(Long taskid) {
        this.taskid = taskid;
    }
}