package com.niule.znxj.web.dao;

import com.niule.znxj.web.model.Taskstopinfo;
import com.niule.znxj.web.model.TaskstopinfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskstopinfoMapper {
    int countByExample(TaskstopinfoExample example);

    int deleteByExample(TaskstopinfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Taskstopinfo record);

    int insertSelective(Taskstopinfo record);

    List<Taskstopinfo> selectByExample(TaskstopinfoExample example);

    Taskstopinfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Taskstopinfo record, @Param("example") TaskstopinfoExample example);

    int updateByExample(@Param("record") Taskstopinfo record, @Param("example") TaskstopinfoExample example);

    int updateByPrimaryKeySelective(Taskstopinfo record);

    int updateByPrimaryKey(Taskstopinfo record);

    List<Taskstopinfo> getStopTask(int state, int type);

    Taskstopinfo queryStopByTempid(int tempid);
}