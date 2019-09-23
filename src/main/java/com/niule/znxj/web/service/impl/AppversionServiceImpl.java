package com.niule.znxj.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.niule.znxj.web.dao.AppversionMapper;
import com.niule.znxj.web.model.Appversion;
import com.niule.znxj.web.model.AppversionExample;
import com.niule.znxj.web.service.AppversionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by administor on 2017/5/10.
 */
@Service
public class AppversionServiceImpl implements AppversionService{
    @Resource
    private AppversionMapper appversionMapper;

    @Override
    public int insert(Appversion record) {
        return appversionMapper.insert(record);
    }

    @Override
    public int deleteByPrimaryKey(int id) {
        return appversionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Appversion selectByPrimaryKey(int id) {
        return appversionMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(Appversion record) {
        return appversionMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<Appversion> selectByExample(int page,int size) {
        PageHelper.startPage(page,size);
        AppversionExample example=new AppversionExample();
        example.setOrderByClause("id desc");
        return appversionMapper.selectByExample(example);
    }
}
