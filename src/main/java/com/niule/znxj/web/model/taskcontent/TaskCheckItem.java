package com.niule.znxj.web.model.taskcontent;

import com.niule.znxj.web.model.Checkiteminfo;

/**
 * Created by administor on 2017/4/21.
 */
public class TaskCheckItem {
    private Checkiteminfo item;

    public Checkiteminfo getItem() {
        return item;
    }

    public void setItem(Checkiteminfo item) {
        this.item = item;
    }
    private Boolean isChecked;

    public Boolean getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(Boolean checked) {
        isChecked = checked;
    }
}
