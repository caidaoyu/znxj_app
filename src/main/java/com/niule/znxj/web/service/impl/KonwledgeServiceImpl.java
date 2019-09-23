package com.niule.znxj.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.niule.znxj.web.dao.KnowledgeMapper;
import com.niule.znxj.web.dao.KnowledgetypeMapper;
import com.niule.znxj.web.model.Knowledge;
import com.niule.znxj.web.model.KnowledgeExample;
import com.niule.znxj.web.model.Knowledgetype;
import com.niule.znxj.web.model.KnowledgetypeExample;
import com.niule.znxj.web.service.KonwledgeService;
import com.niule.znxj.web.service.KonwledgeTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * Created by administor on 2017/5/8.
 */
@Service
public class KonwledgeServiceImpl implements KonwledgeService {
    @Resource
    private KnowledgeMapper knowledgeMapper;
    @Override
    public List<Knowledge> selectByExample(HashMap<String,Object> map) {
//        int page=Integer.parseInt(map.get("page").toString());
//        int size=Integer.parseInt(map.get("size").toString());
//        PageHelper.startPage(page,size);
        return knowledgeMapper.selectByExample(map);
    }

    @Override
    public List<Knowledge> selectByExample1(HashMap<String,Object> map) {
        int page=Integer.parseInt(map.get("page").toString());
        int size=Integer.parseInt(map.get("size").toString());
        PageHelper.startPage(page,size);
        List<Knowledge> aa=knowledgeMapper.selectByExample(map);
        return aa;
    }

    @Override
    public Knowledge selectByPrimaryKey(Integer id) {
        return knowledgeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return knowledgeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Knowledge record) {
        return knowledgeMapper.insert(record);
    }

    @Override
    public int updateByPrimaryKeySelective(Knowledge record) {
        return knowledgeMapper.updateByPrimaryKeySelective(record);
    }
}
