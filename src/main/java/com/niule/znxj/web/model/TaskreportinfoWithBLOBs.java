package com.niule.znxj.web.model;

public class TaskreportinfoWithBLOBs extends Taskreportinfo {
    private String content;

    private String logcat;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getLogcat() {
        return logcat;
    }

    public void setLogcat(String logcat) {
        this.logcat = logcat == null ? null : logcat.trim();
    }
}