package com.niule.znxj.web.model.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by administor on 2017/5/27.
 */
public class WebChartRes2 implements Serializable {
    private List<WebChartResBean2> series;
    private List<String>monthes;

    public List<WebChartResBean2> getSeries() {
        return series;
    }

    public void setSeries(List<WebChartResBean2> series) {
        this.series = series;
    }

    public List<String> getMonthes() {
        return monthes;
    }

    public void setMonthes(List<String> monthes) {
        this.monthes = monthes;
    }

    public WebChartRes2() {
    }

    public WebChartRes2(List<WebChartResBean2> series, List<String> monthes) {
        this.series = series;
        this.monthes = monthes;
    }
}
