package com.niule.znxj.web.model.taskresponse;

import com.niule.znxj.web.model.Checkiteminfo;
import com.niule.znxj.web.model.Equipmentinfo;

import java.util.List;

/**
 * Created by MrD on 2017/3/23.
 */
public class EquipmentRes extends Equipmentinfo{
    private List<CheckItemInfoRes>checkiteminfoList;

    public EquipmentRes(List<CheckItemInfoRes> checkiteminfoList) {
        this.checkiteminfoList = checkiteminfoList;
    }

    public EquipmentRes() {
    }


    public List<CheckItemInfoRes> getCheckiteminfoList() {
        return checkiteminfoList;
    }

    public void setCheckiteminfoList(List<CheckItemInfoRes> checkiteminfoList) {
        this.checkiteminfoList = checkiteminfoList;
    }
}
