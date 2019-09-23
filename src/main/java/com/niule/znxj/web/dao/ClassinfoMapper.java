package com.niule.znxj.web.dao;

import com.niule.znxj.web.model.Classinfo;
import com.niule.znxj.web.model.ClassinfoExample;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClassinfoMapper {
    int countByExample(ClassinfoExample example);

    int deleteByExample(ClassinfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Classinfo record);

    int insertSelective(Classinfo record);

    List<Classinfo> selectByExample(ClassinfoExample example);

    Classinfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Classinfo record, @Param("example") ClassinfoExample example);

    int updateByExample(@Param("record") Classinfo record, @Param("example") ClassinfoExample example);

    int updateByPrimaryKeySelective(Classinfo record);

    int updateByPrimaryKey(Classinfo record);
   /* List<Classinfo> findByPageClass(int page, int pagesize,List ids);*/
    List<Classinfo> findByPageClass(HashMap<String, Object> map);
    int countClass(HashMap<String, Object> map);
    List<Classinfo>siteclass(HashMap<String, Object> map);
    List<Classinfo> queryCustomid();

    List<Classinfo> queryusername(int uid);

}