package com.niule.znxj.web.model.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by administor on 2017/5/27.
 */
public class WebChartResBean2 implements Serializable {
    private String name;
    private List<Double> data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Double> getData() {
        return data;
    }

    public void setData(List<Double> data) {
        this.data = data;
    }

}
