package com.niule.znxj.web.dao;

import com.niule.znxj.web.model.Taskreportinfo;
import com.niule.znxj.web.model.TaskreportinfoExample;
import com.niule.znxj.web.model.TaskreportinfoWithBLOBs;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface TaskreportinfoMapper {
    int countByExample(TaskreportinfoExample example);

    int deleteByExample(TaskreportinfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Taskreportinfo record);

    int insertSelective(TaskreportinfoWithBLOBs record);

    List<TaskreportinfoWithBLOBs> selectByExampleWithBLOBs(TaskreportinfoExample example);

    List<Taskreportinfo> selectByExample(TaskreportinfoExample example);

    TaskreportinfoWithBLOBs selectByPrimaryKey(Long id);

    public List<Taskreportinfo> selectByExample2(HashMap<String, Object> map);

    int updateByExampleSelective(@Param("record") TaskreportinfoWithBLOBs record, @Param("example") TaskreportinfoExample example);

    int updateByExampleWithBLOBs(@Param("record") TaskreportinfoWithBLOBs record, @Param("example") TaskreportinfoExample example);

    int updateByExample(@Param("record") Taskreportinfo record, @Param("example") TaskreportinfoExample example);

    int updateByPrimaryKeySelective(TaskreportinfoWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(TaskreportinfoWithBLOBs record);

    int updateByPrimaryKey(Taskreportinfo record);

    public List<Taskreportinfo> findByPageReport(HashMap<String, Object> map);

    public int countReport(HashMap<String, Object> map);

    public List<Taskreportinfo> findByPageReport2(HashMap<String, Object> map);

    public int countReport2(HashMap<String, Object> map);

    List<Taskreportinfo> queryByTaskidAndDonetime(int taskid, String donetime);

    /*查询某一天某项任务操作状态是正常或者异常的任务报告的信息*/
    List<Taskreportinfo> queryByState(HashMap<String, Object> map);

    //    List<Taskreportinfo>gettaskreportlog();
    List<Taskreportinfo> gettaskreportlog2(HashMap<String, Object> map);

    List<Taskreportinfo> getreportbytype(int type);

    //不带参数饼图设计
    public List<Map<String, Integer>> getStaticMachineRateByReport();

    //带参数饼图设计
    public List<Map<String, Integer>> getMachineRateByParam(HashMap<String, Object> map);

    //折线图不带参数
    public List<Map<String, Integer>> lineChart();

    //折线图带参数
    public List<Map<String, Integer>> lineChartByParam(HashMap<String, Object> map);

    public List<Map<String, Object>> AvgTimelineChart();

    List<Taskreportinfo> reportList2(HashMap<String, Object> map);

    List<Taskreportinfo> reportList3(HashMap<String, Object> map);

    List<Taskreportinfo> timeChart(Long taskid);

    List<Taskreportinfo> dadiyreportlist(TaskreportinfoExample example);

    List<Taskreportinfo> dadiyreportlist2(HashMap<String, Object> map);

    List<Taskreportinfo> huiZong(int type, int taskid, String donetime);
}