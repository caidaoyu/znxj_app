package com.niule.znxj.web.service;

import com.github.pagehelper.PageInfo;
import com.niule.znxj.core.entity.Result;
import com.niule.znxj.web.model.*;
import com.niule.znxj.web.model.response.WebChartRes2;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by administor on 2017/4/6.
 */
@Service
public interface TaskreportService {
    public List<Taskreportinfo> findByPageReport(HashMap<String, Object> map);

    public int countReport(HashMap<String, Object> map);

    //日报
    List<Tasktempinfo> dadiyreport(String date, Long taskid);

    List<Taskreportinfo> dadiyreportlist(Long taskid, String date1, String date2) throws ParseException;

    //    List<Reportcontent> reportcontent(HashMap<String,Object> map);
    //周报
    List<Tasktempinfo> dadiyreport2(String date1, String date2, Long taskid);
//    List<Taskreportinfo> dadiyreportlist2(String taskid);


    public List<Taskreportinfo> findByPageReport2(HashMap<String, Object> map);

    public int countReport2(HashMap<String, Object> map);

    int deleteByPrimaryKey(Long id);

    Taskreportinfo selectByPrimaryKey(Long id);

    PageInfo<Reportcontent> queryByTaskidAndDonetime(int page, int size, int taskid, String donetime, String reportstate);

    List<Taskreportinfo> queryByTaskidAndDonetime2(int type, int taskid, String donetime);

    //导出excel时根据日期找到符合条件的任务
    List<Taskreportinfo> queryByTaskidAndDonetime3(int type, int taskid, String time1, String time2);

    List<Reportcontent> exportexcel(int taskid, String donetime);

    /*查询某一天某项任务操作状态是正常或者异常的任务报告的信息*/
    List<Taskreportinfo> queryByState(HashMap<String, Object> map);

    //    PageInfo<Taskreportinfo> gettaskreportlog(int page, int pagesize);
    PageInfo<Taskreportinfo> gettaskreportlog2(HashMap<String, Object> map);

    int delTaskLog(Long id);

    int deleteByExample(TaskreportinfoExample example);

    List<Taskreportinfo> selectByExample(TaskreportinfoExample example);

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

    //任务完成时间和任务完成情况的数据列表
    PageInfo<Taskreportinfo> reportList(HashMap<String, Object> map) throws Exception;

    //任务完成时间折线图
    Result avgTimeLine(HashMap<String, Object> map);

    //设备状态情况数据了列表
    List<TaskReportContent> equipSateInfo(int page, int size);

    List<TaskReportContent> selectByExampleEquipState(int page, int size, List<Long> taskid, Date time1, Date time2, String sitename, String areaname, String equipname, String checkname);

    //    public WebChartRes2 equipStateChart(int page, int size,String checkname,String monthstr,String yearstr,String areaname,String equipname);
    public WebChartRes2 equipStateChart(int page, int size, String checkname, String monthstr, String yearstr, String areaname, String equipname) throws ParseException;

    //    public PageInfo<Reportcontent>  equipSateInfo2(int page,int size,String checkname,String monthstr,String yearstr,String sitename,String areaname,String equipname) ;
    public PageInfo<Reportcontent> equipSateInfo2(int page, int size, String checkname, String startTime, String endTime, String sitename, String areaname, String equipname) throws ParseException;

    //报告复核
    int updReportContent(String reportData, Long reportId, String examuser, String taskcode);
}
