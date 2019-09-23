package com.niule.znxj.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.niule.znxj.web.dao.KnowledgetypeMapper;
import com.niule.znxj.web.model.Knowledgetype;
import com.niule.znxj.web.model.KnowledgetypeExample;
import com.niule.znxj.web.service.KonwledgeTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by administor on 2017/5/8.
 */
@Service
public class KonwledgeTypeServiceImpl implements KonwledgeTypeService {
    @Resource
    private KnowledgetypeMapper knowledgetypeMapper;
    @Override
    public List<Knowledgetype> selectByExample(int page, int size) {
        PageHelper.startPage(page,size);
        KnowledgetypeExample example=new KnowledgetypeExample();
        return knowledgetypeMapper.selectByExample(example);
    }

    @Override
    public List<Knowledgetype> selectByExample1() {
        KnowledgetypeExample knowledgetypeExample=new KnowledgetypeExample();
        return knowledgetypeMapper.selectByExample(knowledgetypeExample);
    }

    @Override
    public Knowledgetype selectByPrimaryKey(Integer typeid) {
        return knowledgetypeMapper.selectByPrimaryKey(typeid);
    }

    @Override
    public int deleteByPrimaryKey(Integer typeid) {
        return knowledgetypeMapper.deleteByPrimaryKey(typeid);
    }

    @Override
    public int insert(Knowledgetype record) {
        return knowledgetypeMapper.insert(record);
    }

    @Override
    public int updateByPrimaryKeySelective(Knowledgetype record) {
        return knowledgetypeMapper.updateByPrimaryKeySelective(record);
    }
}

