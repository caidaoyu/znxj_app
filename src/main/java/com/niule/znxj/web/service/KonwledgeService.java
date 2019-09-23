package com.niule.znxj.web.service;

import com.niule.znxj.web.model.Knowledge;
import com.niule.znxj.web.model.KnowledgeExample;
import com.niule.znxj.web.model.Knowledgetype;

import java.util.HashMap;
import java.util.List;

/**
 * Created by administor on 2017/5/8.
 */
public interface KonwledgeService {
    List<Knowledge> selectByExample(HashMap<String, Object> map);
    List<Knowledge> selectByExample1(HashMap<String, Object> map);
    int insert(Knowledge record);
    int updateByPrimaryKeySelective(Knowledge record);

    int deleteByPrimaryKey(Integer typeid);

    Knowledge selectByPrimaryKey(Integer id);
}
