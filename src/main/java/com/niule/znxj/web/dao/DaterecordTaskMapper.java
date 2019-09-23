package com.niule.znxj.web.dao;

import com.niule.znxj.web.model.DaterecordTask;
import com.niule.znxj.web.model.DaterecordTaskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DaterecordTaskMapper {
    int countByExample(DaterecordTaskExample example);

    int deleteByExample(DaterecordTaskExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DaterecordTask record);

    int insertSelective(DaterecordTask record);

    List<DaterecordTask> selectByExample(DaterecordTaskExample example);

    DaterecordTask selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DaterecordTask record, @Param("example") DaterecordTaskExample example);

    int updateByExample(@Param("record") DaterecordTask record, @Param("example") DaterecordTaskExample example);

    int updateByPrimaryKeySelective(DaterecordTask record);

    int updateByPrimaryKey(DaterecordTask record);
}