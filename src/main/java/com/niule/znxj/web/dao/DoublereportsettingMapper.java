package com.niule.znxj.web.dao;

import com.niule.znxj.web.model.Doublereportsetting;
import com.niule.znxj.web.model.DoublereportsettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DoublereportsettingMapper {
    int countByExample(DoublereportsettingExample example);

    int deleteByExample(DoublereportsettingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Doublereportsetting record);

    int insertSelective(Doublereportsetting record);

    List<Doublereportsetting> selectByExample(DoublereportsettingExample example);

    Doublereportsetting selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Doublereportsetting record, @Param("example") DoublereportsettingExample example);

    int updateByExample(@Param("record") Doublereportsetting record, @Param("example") DoublereportsettingExample example);

    int updateByPrimaryKeySelective(Doublereportsetting record);

    int updateByPrimaryKey(Doublereportsetting record);

    int updDoubleReportSettingByKey(String key, String value);
}