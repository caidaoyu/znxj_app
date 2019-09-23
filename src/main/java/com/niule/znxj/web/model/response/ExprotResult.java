package com.niule.znxj.web.model.response;

import com.niule.znxj.web.model.Exportinfo;
import com.niule.znxj.web.model.Taskplaninfo;

/**
 * Created by admin on 2018/5/12.
 */
public class ExprotResult {
    private Exportinfo exportinfos;
    private Taskplaninfo taskplaninfo;

    public Exportinfo getExportinfos() {
        return exportinfos;
    }

    public void setExportinfos(Exportinfo exportinfos) {
        this.exportinfos = exportinfos;
    }

    public Taskplaninfo getTaskplaninfo() {
        return taskplaninfo;
    }

    public void setTaskplaninfo(Taskplaninfo taskplaninfo) {
        this.taskplaninfo = taskplaninfo;
    }
}
