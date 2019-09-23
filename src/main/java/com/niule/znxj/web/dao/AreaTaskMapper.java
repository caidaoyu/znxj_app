package com.niule.znxj.web.dao;

import com.niule.znxj.web.model.AreaTask;
import com.niule.znxj.web.model.AreaTaskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AreaTaskMapper {
    int countByExample(AreaTaskExample example);

    int deleteByExample(AreaTaskExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AreaTask record);

    int insertSelective(AreaTask record);

    List<AreaTask> selectByExample(AreaTaskExample example);

    AreaTask selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AreaTask record, @Param("example") AreaTaskExample example);

    int updateByExample(@Param("record") AreaTask record, @Param("example") AreaTaskExample example);

    int updateByPrimaryKeySelective(AreaTask record);

    int updateByPrimaryKey(AreaTask record);
}