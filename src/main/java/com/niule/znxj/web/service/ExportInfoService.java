package com.niule.znxj.web.service;

import com.niule.znxj.core.Page;
import com.niule.znxj.web.model.Exportinfo;
import com.niule.znxj.web.model.ExprotReport2Content;
import com.niule.znxj.web.model.ExprotReportContent;
import com.niule.znxj.web.model.Taskplaninfo;
import com.niule.znxj.web.model.response.ExprotResult;

import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2018/4/25.
 */
public interface ExportInfoService {

    /**
     * 分页查询所有数据导出信息列表
     *
     * @param page
     * @param size
     * @param name
     * @return
     */
//    PageInfo<Exportinfo> getPageExportInfo(int page, int size, String name);
    Page<ExprotResult> getPageExportInfoAll(int page, int size, String name,List ids);

    //根据任务类型查询任务号
    List<Taskplaninfo> queryTaskNo(Integer type, Long siteId);

    //    List<Taskplaninfo> queryItemNo(Long siteId);
    Taskplaninfo queryItemNo(Long taskNo);

    /**
     * 添加数据导出模板
     *
     * @param exportinfo
     * @return
     */
    int addExportInfo(Exportinfo exportinfo);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    int deleteExportInfo(Integer id);

    List<ExprotReportContent> queryAllExportBySelect(Map map);

    Exportinfo getExportinfoById(Integer id);

    List<Map> getAreaInoutTime(Map map);

    public List<ExprotReport2Content> queryAllShuyouExportBySelect(Map map);

    int queryCount(Long taskId, String startTime, String endTime);

}
