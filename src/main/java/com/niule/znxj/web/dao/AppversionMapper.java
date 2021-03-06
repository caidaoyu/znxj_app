package com.niule.znxj.web.dao;

import com.niule.znxj.web.model.Appversion;
import com.niule.znxj.web.model.AppversionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppversionMapper {
    int countByExample(AppversionExample example);

    int deleteByExample(AppversionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Appversion record);

    int insertSelective(Appversion record);

    List<Appversion> selectByExample(AppversionExample example);

    Appversion selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Appversion record, @Param("example") AppversionExample example);

    int updateByExample(@Param("record") Appversion record, @Param("example") AppversionExample example);

    int updateByPrimaryKeySelective(Appversion record);

    int updateByPrimaryKey(Appversion record);

    Appversion getLastestAppVersion();

}