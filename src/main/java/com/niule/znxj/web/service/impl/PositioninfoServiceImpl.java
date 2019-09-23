package com.niule.znxj.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.niule.znxj.web.dao.PositioninfoMapper;
import com.niule.znxj.web.model.Positioninfo;
import com.niule.znxj.web.model.PositioninfoExample;
import com.niule.znxj.web.service.PositioninfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by administor on 2017/3/17.
 */
@Service
public class PositioninfoServiceImpl implements PositioninfoService {
    @Override
    public PageInfo<Positioninfo> selectByExample1(int page, int size, String name) {
        PageHelper.startPage(page,size);
        PositioninfoExample example=new PositioninfoExample();
        example.createCriteria().andPositionnameEqualTo(name);
        return new PageInfo<>(positioninfoMapper.selectByExample(example));
    }

    @Resource
    private PositioninfoMapper positioninfoMapper;


    @Override
    public List<Positioninfo> findByPagePos(int page, int pagesize) {
//        PageHelper.startPage(page,pagesize);
        return  positioninfoMapper.findByPagePos((page-1)*pagesize,pagesize);
    }

    @Override
    public int countPos() {
        return positioninfoMapper.countPos();
    }

    @Override
    public int insert(Positioninfo record) {
        return positioninfoMapper.insert(record);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return positioninfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Positioninfo selectByPrimaryKey(Long id) {
        return positioninfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(Positioninfo record) {
        return positioninfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Positioninfo> selectByExample() {
        PositioninfoExample example=new PositioninfoExample();
        return positioninfoMapper.selectByExample(example);
    }

    @Override
    public PageInfo<Positioninfo> selectByExample(int page , int size) {
        PageHelper.startPage(page,size);
        PositioninfoExample example=new PositioninfoExample();
        return new PageInfo<>(positioninfoMapper.selectByExample(example));
    }

    @Override
    public int countByExample() {
        PositioninfoExample positioninfoExample=new PositioninfoExample();
        return positioninfoMapper.countByExample(positioninfoExample);
    }

}
