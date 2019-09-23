package com.niule.znxj.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.niule.znxj.web.dao.WarningtasktypeMapper;
import com.niule.znxj.web.model.Warningtasktype;
import com.niule.znxj.web.model.WarningtasktypeExample;
import com.niule.znxj.web.service.WarnTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by administor on 2017/5/11.
 */
@Service
public class WarnTypeServiceImpl implements WarnTypeService{
    @Resource
    private WarningtasktypeMapper warningtasktypeMapper;
    @Override
    public List<Warningtasktype> showAllWarnType(int page, int size) {
        PageHelper.startPage(page,size);
        WarningtasktypeExample warningtasktypeExample=new WarningtasktypeExample();
        warningtasktypeExample.setOrderByClause("id desc");
        return warningtasktypeMapper.selectByExample(warningtasktypeExample);
    }

    @Override
    public Warningtasktype selectByPrimaryKey(Integer id) {
        return warningtasktypeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Warningtasktype> selectByExample() {
        return null;
    }

    @Override
    public int insert(Warningtasktype record) {
        return warningtasktypeMapper.insert(record);
    }

    @Override
    public int updateByPrimaryKeySelective(Warningtasktype record) {
        return warningtasktypeMapper.updateByPrimaryKeySelective(record);
    }


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return warningtasktypeMapper.deleteByPrimaryKey(id);
    }
}
