package com.niule.znxj.web.dao;

import com.niule.znxj.web.model.Tasktempinfo;
import com.niule.znxj.web.model.TasktempinfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TasktempinfoMapper {
    int countByExample(TasktempinfoExample example);

    int deleteByExample(TasktempinfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Tasktempinfo record);

    int insertSelective(Tasktempinfo record);

    List<Tasktempinfo> selectByExample(TasktempinfoExample example);

    Tasktempinfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Tasktempinfo record, @Param("example") TasktempinfoExample example);

    int updateByExample(@Param("record") Tasktempinfo record, @Param("example") TasktempinfoExample example);

    int updateByPrimaryKeySelective(Tasktempinfo record);

    int updateByPrimaryKey(Tasktempinfo record);

    int updateTempState(Long taskid);
    int updateTempState2(Long id);

    List<Tasktempinfo> dadiyreport(TasktempinfoExample example);
    List<Tasktempinfo> dadiyreport2(String date1, String date2);
}