package com.niule.znxj.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.niule.znxj.core.Page;
import com.niule.znxj.web.dao.ExportinfoMapper;
import com.niule.znxj.web.dao.TaskplaninfoMapper;
import com.niule.znxj.web.model.*;
import com.niule.znxj.web.model.response.ExprotResult;
import com.niule.znxj.web.service.ExportInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Created by admin on 2018/4/25.
 */
@Service
public class ExportInfoServiceImpl implements ExportInfoService {


    @Resource
    private ExportinfoMapper exportinfoMapper;

    @Resource
    private TaskplaninfoMapper taskplaninfoMapper;

    /**
     * 分页查询所有数据导出列表
     * @param page
     * @param size
     * @param name
     * @return
     */
  /*  @Override
    public PageInfo<Exportinfo> getPageExportInfo(int page, int size, String name) {
        PageHelper.startPage(page,size);
        ExportinfoExample exportinfoExample = new ExportinfoExample();
        exportinfoExample.setOrderByClause("id desc");
        if(name != null &&!name.isEmpty()){
            exportinfoExample.createCriteria().andExportnameLike("%"+name+"%");
        }

        PageInfo<Exportinfo> pageInfo = new PageInfo<>(exportinfoMapper.selectByExample(exportinfoExample));
        return pageInfo;
    }*/

    @Override
    public Page<ExprotResult> getPageExportInfoAll(int page, int size, String name,List ids) {

        List<ExprotResult> exprotResultList = new ArrayList<>();
        ExportinfoExample exportinfoExample = new ExportinfoExample();
        exportinfoExample.setOrderByClause("id desc");
        ExportinfoExample.Criteria criteria = exportinfoExample.createCriteria();
        if(name != null &&!name.isEmpty()){
            criteria.andExportnameLike("%"+name+"%");
        }
        TaskplaninfoExample taskplaninfoExample = new TaskplaninfoExample();
        TaskplaninfoExample.Criteria criteria1 = taskplaninfoExample.createCriteria();
        criteria1.andSiteidIn(ids);
        List<Taskplaninfo >  taskplaninfos = taskplaninfoMapper.selectByExample(taskplaninfoExample);

        if(taskplaninfos.size()>0){
            List idList = new ArrayList();
            for(Taskplaninfo taskplaninfo : taskplaninfos){
                idList.add(taskplaninfo.getId());
            }
            criteria.andTaskidIn(idList);
        }
        PageHelper.startPage(page,size);
        List<Exportinfo> exportinfos = exportinfoMapper.selectByExample(exportinfoExample);
        PageInfo<Exportinfo> pageInfo = new PageInfo<>(exportinfos);
        for(Exportinfo exportinfo : exportinfos){
            ExprotResult exprotResult = new ExprotResult();
            exprotResult.setExportinfos(exportinfo);

            Taskplaninfo taskplaninfo = taskplaninfoMapper.selectByPrimaryKey(Long.valueOf(exportinfo.getTaskid()));
            exprotResult.setTaskplaninfo(taskplaninfo != null ? taskplaninfo : null);
            exprotResultList.add(exprotResult);
        }

        Page<ExprotResult> page1 = new Page<>(pageInfo.getTotal(),pageInfo.getPages(),exprotResultList,pageInfo.isIsLastPage(),pageInfo.isIsLastPage(),page);
        return page1;
    }

    @Override
    public List<Taskplaninfo> queryTaskNo(Integer type,Long siteId) {
        TaskplaninfoExample taskplaninfoExample = new TaskplaninfoExample();
        taskplaninfoExample.createCriteria().andTypeEqualTo(type).andSiteidEqualTo(siteId).andStateEqualTo(1);
        List<Taskplaninfo> taskplaninfoList = taskplaninfoMapper.selectByExample(taskplaninfoExample);
        return taskplaninfoList;
    }

    @Override
    public Taskplaninfo queryItemNo(Long taskNo) {

       /* TaskplaninfoExample taskplaninfoExample = new TaskplaninfoExample();
        taskplaninfoExample.createCriteria().andIdentifyingidEqualTo(taskNo);
        List<Taskplaninfo> taskplaninfoList = taskplaninfoMapper.selectByExample(taskplaninfoExample);*/
        return taskNo != null?  taskplaninfoMapper.selectTaskId(taskNo) : null;
    }

    @Override
    public int addExportInfo(Exportinfo exportinfo) {

        exportinfo.setCreatetimestamp(new Date());
        exportinfo.setUpdatetimestamp(new Date());
        return exportinfoMapper.insertSelective(exportinfo);
    }

    @Override
    public int deleteExportInfo(Integer id) {
        return exportinfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<ExprotReportContent> queryAllExportBySelect(Map map) {

        return exportinfoMapper.selectExport(map);
    }
    @Override
    public Exportinfo getExportinfoById(Integer id) {
        Exportinfo exportinfo=exportinfoMapper.selectByPrimaryKey(id);
        return exportinfo;
    }

    @Override
    public List<Map> getAreaInoutTime(Map map) {
        List<Map> list=exportinfoMapper.selectAreaInoutTime(map);
        return list;
    }

    @Override
    public List<ExprotReport2Content> queryAllShuyouExportBySelect(Map map) {

        return exportinfoMapper.selectShuyouExport(map);
    }

    @Override
    public int queryCount(Long taskId, String startTime, String endTime) {
        int count = exportinfoMapper.queryCount(taskId,startTime,endTime);

        return count;
    }

    private static long daysBetween(Date one, Date two) {
        long difference =  (one.getTime()-two.getTime())/86400000;
        return Math.abs(difference);
    }

    /*public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(daysBetween(sdf.parse("2018-06-13"),sdf.parse("2018-06-10"))+1);;
    }*/

    public static void main(String[] args) {
        long timeStamp = Long.parseLong("1528687520060");//直接是时间戳
//        long timeStamp = System.currentTimeMillis();  //获取当前时间戳,也可以是你自已给的一个随机的或是别人给你的时间戳(一定是long型的数据)
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//这个是你要转成后的时间的格式
        String sd = sdf.format(new Date(Long.parseLong(String.valueOf(timeStamp))));   // 时间戳转换成时间
        System.out.println(sd);//打印出你要的时间
    }
}
