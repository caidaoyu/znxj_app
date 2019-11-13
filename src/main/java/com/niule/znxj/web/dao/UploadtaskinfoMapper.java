package com.niule.znxj.web.dao;

import com.niule.znxj.web.model.Uploadtaskinfo;
import com.niule.znxj.web.model.UploadtaskinfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UploadtaskinfoMapper {
    int countByExample(UploadtaskinfoExample example);

    int deleteByExample(UploadtaskinfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Uploadtaskinfo record);

    int insertSelective(Uploadtaskinfo record);

    List<Uploadtaskinfo> selectByExample(UploadtaskinfoExample example);

    Uploadtaskinfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Uploadtaskinfo record, @Param("example") UploadtaskinfoExample example);

    int updateByExample(@Param("record") Uploadtaskinfo record, @Param("example") UploadtaskinfoExample example);

    int updateByPrimaryKeySelective(Uploadtaskinfo record);

    int updateByPrimaryKey(Uploadtaskinfo record);
}