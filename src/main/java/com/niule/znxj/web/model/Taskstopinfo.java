package com.niule.znxj.web.model;

import java.util.Date;

public class Taskstopinfo {
    private Long id;

    private Long tasktempid;

    private String reason;

    private String content;

    private Date stoptime;

    private String classname;

    private String directorname;
    private String stoptime2;

    public String getStoptime2() {
        return stoptime2;
    }

    public void setStoptime2(String stoptime2) {
        this.stoptime2 = stoptime2;
    }
/*private Tasktempinfo tem;*/

    /*public Tasktempinfo getTem() {
        return tem;
    }

    public void setTem(Tasktempinfo tem) {
        this.tem = tem;
    }
*/
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTasktempid() {
        return tasktempid;
    }

    public void setTasktempid(Long tasktempid) {
        this.tasktempid = tasktempid;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getStoptime() {
        return stoptime;
    }

    public void setStoptime(Date stoptime) {
        this.stoptime = stoptime;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname == null ? null : classname.trim();
    }

    public String getDirectorname() {
        return directorname;
    }

    public void setDirectorname(String directorname) {
        this.directorname = directorname == null ? null : directorname.trim();
    }
}