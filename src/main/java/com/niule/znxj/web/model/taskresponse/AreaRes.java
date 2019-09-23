package com.niule.znxj.web.model.taskresponse;

import com.niule.znxj.web.model.Areainfo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by MrD on 2017/3/23.
 */
public class AreaRes extends Areainfo{
    private List<EquipmentRes>equipmentResList;

    public AreaRes() {
    }

    public AreaRes(List<EquipmentRes> equipmentResList) {
        this.equipmentResList = equipmentResList;
    }

    public List<EquipmentRes> getEquipmentResList() {
        return equipmentResList;
    }

    public void setEquipmentResList(List<EquipmentRes> equipmentResList) {
        this.equipmentResList = equipmentResList;
    }
}
