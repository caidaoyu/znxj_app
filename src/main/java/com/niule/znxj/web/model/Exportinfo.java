package com.niule.znxj.web.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Exportinfo {
    private Integer id;

    private String exportname;

    private String tasktype;

    private Integer taskid;

    private Integer computingtime;

    private Integer consuming;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updatetimestamp;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createtimestamp;

    private String taskcontent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExportname() {
        return exportname;
    }

    public void setExportname(String exportname) {
        this.exportname = exportname == null ? null : exportname.trim();
    }

    public String getTasktype() {
        return tasktype;
    }

    public void setTasktype(String tasktype) {
        this.tasktype = tasktype == null ? null : tasktype.trim();
    }

    public Integer getTaskid() {
        return taskid;
    }

    public void setTaskid(Integer taskid) {
        this.taskid = taskid;
    }

    public Integer getComputingtime() {
        return computingtime;
    }

    public void setComputingtime(Integer computingtime) {
        this.computingtime = computingtime;
    }

    public Integer getConsuming() {
        return consuming;
    }

    public void setConsuming(Integer consuming) {
        this.consuming = consuming;
    }

    public Date getUpdatetimestamp() {
        return updatetimestamp;
    }

    public void setUpdatetimestamp(Date updatetimestamp) {
        this.updatetimestamp = updatetimestamp;
    }

    public Date getCreatetimestamp() {
        return createtimestamp;
    }

    public void setCreatetimestamp(Date createtimestamp) {
        this.createtimestamp = createtimestamp;
    }

    public String getTaskcontent() {
        return taskcontent;
    }

    public void setTaskcontent(String taskcontent) {
        this.taskcontent = taskcontent == null ? null : taskcontent.trim();
    }
}