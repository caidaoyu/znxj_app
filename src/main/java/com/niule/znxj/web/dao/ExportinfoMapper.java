package com.niule.znxj.web.dao;

import com.niule.znxj.web.model.Exportinfo;
import com.niule.znxj.web.model.ExportinfoExample;
import java.util.List;
import java.util.Map;

import com.niule.znxj.web.model.ExprotReport2Content;
import com.niule.znxj.web.model.ExprotReportContent;
import org.apache.ibatis.annotations.Param;

public interface ExportinfoMapper {
    int countByExample(ExportinfoExample example);

    int deleteByExample(ExportinfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Exportinfo record);

    int insertSelective(Exportinfo record);

    List<Exportinfo> selectByExampleWithBLOBs(ExportinfoExample example);

    List<Exportinfo> selectByExample(ExportinfoExample example);

    Exportinfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Exportinfo record, @Param("example") ExportinfoExample example);

    int updateByExampleWithBLOBs(@Param("record") Exportinfo record, @Param("example") ExportinfoExample example);

    int updateByExample(@Param("record") Exportinfo record, @Param("example") ExportinfoExample example);

    int updateByPrimaryKeySelective(Exportinfo record);

    int updateByPrimaryKeyWithBLOBs(Exportinfo record);

    int updateByPrimaryKey(Exportinfo record);

    List<ExprotReportContent> selectExport(Map map);

    List<Map> selectAreaInoutTime(Map map);

    List<ExprotReport2Content> selectShuyouExport(Map map);

    int queryCount(Long taskId, String startTime, String endTime);
}