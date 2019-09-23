package com.niule.znxj.web.model.taskcontent;

import com.niule.znxj.web.model.Checkiteminfo;
import com.niule.znxj.web.model.Equipmentinfo;

import java.util.List;

/**
 * Created by administor on 2017/4/21.
 */
public class TaskEquipment {
    private Equipmentinfo equipment;
    private List<TaskCheckItem> checkItems;

    public Equipmentinfo getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipmentinfo equipment) {
        this.equipment = equipment;
    }

    public List<TaskCheckItem> getCheckItems() {
        return checkItems;
    }

    public void setCheckItems(List<TaskCheckItem> checkItems) {
        this.checkItems = checkItems;
    }
}
