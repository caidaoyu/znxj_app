package com.niule.znxj.web.model;

import com.niule.znxj.core.util.json.JsonUtil;
import com.niule.znxj.web.model.taskresponse.TaskTempRes;

import java.util.List;

/**
 * Created by administor on 2017/4/27.
 */
public class TaskReportRes{
    private List<TaskReportContent> res;

    public List<TaskReportContent> getRes() {
        return res;
    }

    public void setRes(List<TaskReportContent> res) {
        this.res = res;
    }


}
