package com.niule.znxj.web.dao;

import com.niule.znxj.web.model.CheckitemTask;
import com.niule.znxj.web.model.CheckitemTaskExample;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CheckitemTaskMapper {
    int countByExample(CheckitemTaskExample example);

    int deleteByExample(CheckitemTaskExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CheckitemTask record);

    int insertSelective(CheckitemTask record);

    List<CheckitemTask> selectByExample(CheckitemTaskExample example);

    CheckitemTask selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CheckitemTask record, @Param("example") CheckitemTaskExample example);

    int updateByExample(@Param("record") CheckitemTask record, @Param("example") CheckitemTaskExample example);

    int updateByPrimaryKeySelective(CheckitemTask record);

    int updateByPrimaryKey(CheckitemTask record);

    List<CheckitemTask> getcheckid(HashMap<String, Object> map);
}