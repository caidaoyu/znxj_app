package com.niule.znxj.web.service.impl;

import com.niule.znxj.web.dao.DoublereportsettingMapper;
import com.niule.znxj.web.dao.ReportsettingMapper;
import com.niule.znxj.web.dao.SystemsettinginfoMapper;
import com.niule.znxj.web.model.*;
import com.niule.znxj.web.service.SystemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by administor on 2017/5/16.
 */
@Service
public class SystemServiceImpl implements SystemService{
    @Resource
    private SystemsettinginfoMapper systemsettinginfoMapper;
    @Resource
    private ReportsettingMapper reportsettingMapper;
    @Resource
    private DoublereportsettingMapper doublereportsettingMapper;

    @Override
    public int updSysByKey(String key, String value) {
        return systemsettinginfoMapper.updSysByKey(key,value);
    }

    @Override
    public List<Systemsettinginfo> selectByExample() {
        SystemsettinginfoExample example=new SystemsettinginfoExample();
        return systemsettinginfoMapper.selectByExample(example);
    }

    @Override
    public Systemsettinginfo findByKey(String key) {
        SystemsettinginfoExample example=new SystemsettinginfoExample();
        example.createCriteria().andKeynameEqualTo(key);
        return systemsettinginfoMapper.selectByExample(example).get(0);
    }

    @Override
    public List<Reportsetting> showReportSetting() {
        ReportsettingExample example = new ReportsettingExample();
        return reportsettingMapper.selectByExample(example);
    }

    @Override
    public List<Doublereportsetting> showDoubleReportSetting() {
        DoublereportsettingExample example = new DoublereportsettingExample();
        return doublereportsettingMapper.selectByExample(example);
    }

    @Override
    public int updReportSettingByKey(String key, String value) {
        return reportsettingMapper.updReportSettingByKey(key,value);
    }

    @Override
    public int updDoubleReportSettingByKey(String key, String value) {
        return doublereportsettingMapper.updDoubleReportSettingByKey(key,value);
    }
}
