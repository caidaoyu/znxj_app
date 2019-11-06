package com.niule.znxj.web.dao;

import com.niule.znxj.web.model.Exceptionhandlerinfo;
import com.niule.znxj.web.model.ExceptionhandlerinfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExceptionhandlerinfoMapper {
    int countByExample(ExceptionhandlerinfoExample example);

    int deleteByExample(ExceptionhandlerinfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Exceptionhandlerinfo record);

    int insertSelective(Exceptionhandlerinfo record);

    List<Exceptionhandlerinfo> selectByExample(ExceptionhandlerinfoExample example);

    Exceptionhandlerinfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Exceptionhandlerinfo record, @Param("example") ExceptionhandlerinfoExample example);

    int updateByExample(@Param("record") Exceptionhandlerinfo record, @Param("example") ExceptionhandlerinfoExample example);

    int updateByPrimaryKeySelective(Exceptionhandlerinfo record);

    int updateByPrimaryKey(Exceptionhandlerinfo record);
}