package com.niule.znxj.web.model.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by administor on 2017/5/27.
 */
public class WebChartRes implements Serializable {
    private List<WebChartResBean> series;
    private List<String>monthes;

    public List<WebChartResBean> getSeries() {
        return series;
    }

    public void setSeries(List<WebChartResBean> series) {
        this.series = series;
    }

    public List<String> getMonthes() {
        return monthes;
    }

    public void setMonthes(List<String> monthes) {
        this.monthes = monthes;
    }

    public WebChartRes() {
    }

    public WebChartRes(List<WebChartResBean> series, List<String> monthes) {
        this.series = series;
        this.monthes = monthes;
    }
}
