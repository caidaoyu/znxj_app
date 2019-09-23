package com.niule.znxj.web.dao;

import com.niule.znxj.web.model.Reportsetting;
import com.niule.znxj.web.model.ReportsettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReportsettingMapper {
    int countByExample(ReportsettingExample example);

    int deleteByExample(ReportsettingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Reportsetting record);

    int insertSelective(Reportsetting record);

    List<Reportsetting> selectByExample(ReportsettingExample example);

    Reportsetting selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Reportsetting record, @Param("example") ReportsettingExample example);

    int updateByExample(@Param("record") Reportsetting record, @Param("example") ReportsettingExample example);

    int updateByPrimaryKeySelective(Reportsetting record);

    int updateByPrimaryKey(Reportsetting record);

    int updReportSettingByKey(String key, String value);
}