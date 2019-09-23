package com.niule.znxj.web.model.response;

/**
 * Created by administor on 2017/9/21.
 */
public class ShowReportParam {
    private int page;
    private int tasktype;
    private String taskCcode;
    private String reportSstate;
    private String time1;
    private String time2;
    private String operationstate;
    private Long siteid;
    private Integer searchtype;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTasktype() {
        return tasktype;
    }

    public void setTasktype(int tasktype) {
        this.tasktype = tasktype;
    }

    public String getTaskCcode() {
        return taskCcode;
    }

    public void setTaskCcode(String taskCcode) {
        this.taskCcode = taskCcode;
    }

    public String getReportSstate() {
        return reportSstate;
    }

    public void setReportSstate(String reportSstate) {
        this.reportSstate = reportSstate;
    }

    public String getTime1() {
        return time1;
    }

    public void setTime1(String time1) {
        this.time1 = time1;
    }

    public String getTime2() {
        return time2;
    }

    public void setTime2(String time2) {
        this.time2 = time2;
    }

    public String getOperationstate() {
        return operationstate;
    }

    public void setOperationstate(String operationstate) {
        this.operationstate = operationstate;
    }

    public Long getSiteid() {
        return siteid;
    }

    public void setSiteid(Long siteid) {
        this.siteid = siteid;
    }

    public Integer getSearchtype() {
        return searchtype;
    }

    public void setSearchtype(Integer searchtype) {
        this.searchtype = searchtype;
    }

    public ShowReportParam() {
    }

    public ShowReportParam(int page, int tasktype, String taskCcode, String reportSstate, String time1, String time2, String operationstate, Long siteid, Integer searchtype) {
        this.page = page;
        this.tasktype = tasktype;
        this.taskCcode = taskCcode;
        this.reportSstate = reportSstate;
        this.time1 = time1;
        this.time2 = time2;
        this.operationstate = operationstate;
        this.siteid = siteid;
        this.searchtype = searchtype;
    }
}
