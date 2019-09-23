package com.niule.znxj.web.model.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by administor on 2017/5/27.
 */
public class WebChartResBean implements Serializable {
    private String name;
    private List<Integer> data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }

}
