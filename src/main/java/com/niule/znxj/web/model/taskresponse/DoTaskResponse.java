package com.niule.znxj.web.model.taskresponse;

import com.niule.znxj.web.model.Taskplaninfo;


/**
 * Created by admin on 2018/5/24.
 */
public class DoTaskResponse {
    private String taskCode;
    private Taskplaninfo taskplaninfos;


    public Taskplaninfo getTaskplaninfos() {
        return taskplaninfos;
    }

    public void setTaskplaninfos(Taskplaninfo taskplaninfos) {
        this.taskplaninfos = taskplaninfos;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

}
