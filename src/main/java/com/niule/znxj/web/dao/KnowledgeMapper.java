package com.niule.znxj.web.dao;

import com.niule.znxj.web.model.Knowledge;
import com.niule.znxj.web.model.KnowledgeExample;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface KnowledgeMapper {
    int countByExample(KnowledgeExample example);

    int deleteByExample(KnowledgeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Knowledge record);

    int insertSelective(Knowledge record);

    List<Knowledge> selectByExample(HashMap<String, Object> map);

    Knowledge selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Knowledge record, @Param("example") KnowledgeExample example);

    int updateByExample(@Param("record") Knowledge record, @Param("example") KnowledgeExample example);

    int updateByPrimaryKeySelective(Knowledge record);

    int updateByPrimaryKey(Knowledge record);

    List<Knowledge>getKnowledgeByTypeid(int typeid);

    List<Knowledge>getKnowledgeByParam(String str);
}