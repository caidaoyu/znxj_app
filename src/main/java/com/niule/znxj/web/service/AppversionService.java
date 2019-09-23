package com.niule.znxj.web.service;

import com.github.pagehelper.PageInfo;
import com.niule.znxj.web.model.Appversion;

import java.util.List;

/**
 * Created by administor on 2017/5/10.
 */
public interface AppversionService {
    //添加新的职位
    int insert(Appversion record);
    int deleteByPrimaryKey(int id);
    Appversion selectByPrimaryKey(int id);
    int updateByPrimaryKey(Appversion record);

    List<Appversion> selectByExample(int page, int size);
}
