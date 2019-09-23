package com.niule.znxj.web.controller;

import java.util.Date;

/**
 * Created by admin on 2018/4/12.
 */
public class TestUser {
    private Integer id;

    private String name;

    private Integer age;

    private Date brith;

    public TestUser(Integer id, String name, Integer age, Date brith) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.brith = brith;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBrith() {
        return brith;
    }

    public void setBrith(Date brith) {
        this.brith = brith;
    }
}
