package com.niule.znxj.web.dao;

import com.niule.znxj.core.generic.GenericDao;
import com.niule.znxj.web.model.Systemsettinginfo;
import com.niule.znxj.web.model.SystemsettinginfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemsettinginfoMapper extends GenericDao<Systemsettinginfo,Long>{
    int countByExample(SystemsettinginfoExample example);

    int deleteByExample(SystemsettinginfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Systemsettinginfo record);

    int insertSelective(Systemsettinginfo record);

    List<Systemsettinginfo> selectByExample(SystemsettinginfoExample example);

    Systemsettinginfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Systemsettinginfo record, @Param("example") SystemsettinginfoExample example);

    int updateByExample(@Param("record") Systemsettinginfo record, @Param("example") SystemsettinginfoExample example);

    int updateByPrimaryKeySelective(Systemsettinginfo record);

    int updateByPrimaryKey(Systemsettinginfo record);

    int updSysByKey(String key, String value);
}