package com.niule.znxj.core;

import java.io.Serializable;
import java.util.List;

/**
 * @author xiaoming
 * @create 2017-07-21 19:19
 **/
public class Page<T> implements Serializable{
    private long total;
    private int pages;
    private List<T> list;
    private boolean isLastPage;
    private boolean isFirstPage;
    private int prePage;

    public Page(){

    }
    public Page(long total, int pages, List<T> list, boolean isLastPage, boolean isFirstPage, int prePage){
        this.total = total;
        this.pages = pages;
        this.list = list;
        this.isLastPage=isLastPage;
        this.isFirstPage = isFirstPage;
        this.prePage = prePage;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public boolean isLastPage() {
        return isLastPage;
    }

    public void setLastPage(boolean lastPage) {
        isLastPage = lastPage;
    }

    public boolean isFirstPage() {
        return isFirstPage;
    }

    public void setFirstPage(boolean firstPage) {
        isFirstPage = firstPage;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }
}
