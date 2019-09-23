package com.niule.znxj.web.model.taskresponse;

import java.util.List;

/**
 * Created by MrD on 2017/3/23.
 */
public class TaskDetails{
    private List<SiteAreaRes> siteAreaResList;

    public TaskDetails() {
    }

    public TaskDetails(List<SiteAreaRes> siteAreaResList) {
        this.siteAreaResList = siteAreaResList;
    }

    public List<SiteAreaRes> getSiteAreaResList() {
        return siteAreaResList;
    }

    public void setSiteAreaResList(List<SiteAreaRes> siteAreaResList) {
        this.siteAreaResList = siteAreaResList;
    }
}
