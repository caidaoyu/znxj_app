package com.niule.znxj.web.dao;

import com.niule.znxj.web.model.Knowledgetype;
import com.niule.znxj.web.model.KnowledgetypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface KnowledgetypeMapper {
    int countByExample(KnowledgetypeExample example);

    int deleteByExample(KnowledgetypeExample example);

    int deleteByPrimaryKey(Integer typeid);

    int insert(Knowledgetype record);

    int insertSelective(Knowledgetype record);

    List<Knowledgetype> selectByExample(KnowledgetypeExample example);

    Knowledgetype selectByPrimaryKey(Integer typeid);

    int updateByExampleSelective(@Param("record") Knowledgetype record, @Param("example") KnowledgetypeExample example);

    int updateByExample(@Param("record") Knowledgetype record, @Param("example") KnowledgetypeExample example);

    int updateByPrimaryKeySelective(Knowledgetype record);

    int updateByPrimaryKey(Knowledgetype record);
}