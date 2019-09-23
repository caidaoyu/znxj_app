package com.niule.znxj.web.dao;

import com.niule.znxj.web.model.Daterecordinfo;
import com.niule.znxj.web.model.DaterecordinfoExample;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DaterecordinfoMapper {
    int countByExample(DaterecordinfoExample example);

    int deleteByExample(DaterecordinfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Daterecordinfo record);

    int insertSelective(Daterecordinfo record);

    List<Daterecordinfo> selectByExample(DaterecordinfoExample example);

    Daterecordinfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Daterecordinfo record, @Param("example") DaterecordinfoExample example);

    int updateByExample(@Param("record") Daterecordinfo record, @Param("example") DaterecordinfoExample example);

    int updateByPrimaryKeySelective(Daterecordinfo record);

    int updateByPrimaryKey(Daterecordinfo record);

    public List<Daterecordinfo> findByPageData(int page, int pagesize);
    public int countData();

    public List<Daterecordinfo> findByPageData2(HashMap<String, Object> map);
    public int countData2(HashMap<String, Object> map);
}