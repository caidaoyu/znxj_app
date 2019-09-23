package com.niule.znxj.web.model.taskresponse;

import com.niule.znxj.web.model.Siteareainfo;

import java.util.List;

/**
 * Created by MrD on 2017/3/23.
 */
public class SiteAreaRes extends Siteareainfo{
    private List<AreaRes>areaResList;

    public SiteAreaRes() {
    }

    public List<AreaRes> getAreaResList() {
        return areaResList;
    }

    public void setAreaResList(List<AreaRes> areaResList) {
        this.areaResList = areaResList;
    }
}
