package com.niule.znxj.web.dao;

import com.niule.znxj.web.model.Quickreport;
import com.niule.znxj.web.model.QuickreportExample;

import java.util.HashMap;
import java.util.List;

import com.niule.znxj.web.model.Taskreportinfo;
import org.apache.ibatis.annotations.Param;

public interface QuickreportMapper {
    int countByExample(int type);

    int deleteByExample(QuickreportExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Quickreport record);

    int insertSelective(Quickreport record);

 /*   List<Quickreport> selectByExample(QuickreportExample example);*/
    List<Quickreport> selectByExample(int page, int pagesize, int type);
    Quickreport selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Quickreport record, @Param("example") QuickreportExample example);

    int updateByExample(@Param("record") Quickreport record, @Param("example") QuickreportExample example);

    int updateByPrimaryKeySelective(Quickreport record);

    int updateByPrimaryKey(Quickreport record);

    List<Quickreport> showQuickreport(HashMap<String, Object> map);
//    List<Quickreport> showQuickreport2();
}