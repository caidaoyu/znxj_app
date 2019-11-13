package com.niule.znxj.web.dao;

import com.niule.znxj.web.model.Taskuploadconfig;
import com.niule.znxj.web.model.TaskuploadconfigExample;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaskuploadconfigMapper {
    int countByExample(TaskuploadconfigExample example);

    int deleteByExample(TaskuploadconfigExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Taskuploadconfig record);

    int insertSelective(Taskuploadconfig record);

    List<Taskuploadconfig> selectByExample(TaskuploadconfigExample example);

    Taskuploadconfig selectByPrimaryKey(Long id);

    Taskuploadconfig selectByReportId(Long reportid);

    int updateByExampleSelective(@Param("record") Taskuploadconfig record, @Param("example") TaskuploadconfigExample example);

    int updateByExample(@Param("record") Taskuploadconfig record, @Param("example") TaskuploadconfigExample example);

    int updateByPrimaryKeySelective(Taskuploadconfig record);

    int updateByPrimaryKey(Taskuploadconfig record);
}