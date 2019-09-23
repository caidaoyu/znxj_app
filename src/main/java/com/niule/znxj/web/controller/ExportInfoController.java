package com.niule.znxj.web.controller;

import com.niule.znxj.core.Page;
import com.niule.znxj.core.common.Resources;
import com.niule.znxj.core.util.PageBean;
import com.niule.znxj.core.util.json.JsonUtil;
import com.niule.znxj.web.dao.TaskplaninfoMapper;
import com.niule.znxj.web.dao.TasktempinfoMapper;
import com.niule.znxj.web.model.*;
import com.niule.znxj.web.model.response.ExprotResult;
import com.niule.znxj.web.model.taskcontent.TaskArea;
import com.niule.znxj.web.model.taskcontent.TaskCheckItem;
import com.niule.znxj.web.model.taskcontent.TaskContent;
import com.niule.znxj.web.model.taskcontent.TaskEquipment;
import com.niule.znxj.web.service.ExportInfoService;
import com.niule.znxj.web.service.SiteService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 数据导出
 * Created by admin on 2018/4/25.
 */
@Controller
public class ExportInfoController {

    @Resource
    private TaskplaninfoMapper taskplaninfoMapper;

    @Resource
    private TasktempinfoMapper tasktempinfoMapper;

    @Resource
    private ExportInfoService exportInfoService;

    @Resource
    private SiteService siteService;
    private String ip = Resources.ApplicationResources.getString("ip");

    /**
     * 分页查询所有数据导出列表
     *
     * @param page
     * @param model
     * @param name
     * @return
     */
    @RequestMapping("/getPageExportInfo")
    @RequiresPermissions("item:exportInfo")
    public String getPageExportInfo(int page, Model model, String name,HttpServletRequest request) {
        if(page <= 0 ){
            page = 1;
        }
        int size = 15;

        page = PageBean.countCurrentPage(page);

        Admininfo admininfo=(Admininfo) request.getSession().getAttribute("userInfo");
        List ids  = new ArrayList();
        if(admininfo.getSiteid() == null){
            List<Siteareainfo> siteareainfos = siteService.queryAllSite();
            if(siteareainfos.size() > 0){
                for(Siteareainfo siteareainfo : siteareainfos){
                    ids.add(siteareainfo.getId());
                }
            }else{
                ids = null;
            }
        }else{
            ids.add(admininfo.getSiteid());
        }
        Page<ExprotResult> pageInfo = exportInfoService.getPageExportInfoAll(page, size, name,ids);
//        PageInfo<Exportinfo> pageInfo = exportInfoService.getPageExportInfo(page,size,name);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("name", name);
        return "export/exportList";
    }

    @RequestMapping("/queryTaskNo")
    @ResponseBody
    public List<Taskplaninfo> queryTaskNo(Integer type, Long siteId) {
        return exportInfoService.queryTaskNo(type, siteId);

    }

    @RequestMapping("/queryItemNo")
    @ResponseBody
    public Taskplaninfo queryItemNo(Long taskNo) {
        return exportInfoService.queryItemNo(taskNo);
    }

    @RequestMapping("toAddExport")
    public String toAddExport(Model model, Integer type, Long siteId, HttpServletRequest request) {
        if (null == type) type = 0;
        Admininfo admininfo = (Admininfo) request.getSession().getAttribute("userInfo");
        List ids = new ArrayList(); //装载当前登录用户对应厂区
        if (admininfo.getSiteid() == null) { //siteId为空是超级管理员，查询所有厂区
            List<Siteareainfo> sites = siteService.queryAllSite();
            if (sites.size() > 0) {
                for (Siteareainfo siteareainfo : sites) {
                    ids.add(siteareainfo.getId());
                }
            } else
                ids = null;
        } else { //siteId不为空有对应厂区
            ids.add(admininfo.getSiteid());
        }
        SiteareainfoExample siteareainfoExample = new SiteareainfoExample();
        SiteareainfoExample.Criteria criteria1 = siteareainfoExample.createCriteria();
        criteria1.andIdIn(ids);
        List<Siteareainfo> siteareainfoList = siteService.selectSiteByUser(siteareainfoExample);
        model.addAttribute("siteList", siteareainfoList);
        Siteareainfo sitearea = siteareainfoList.get(0);
        //任务号
        TaskplaninfoExample taskplaninfoExample = new TaskplaninfoExample();
        TaskplaninfoExample.Criteria criteria2 = taskplaninfoExample.createCriteria();
        criteria2.andTypeEqualTo(type);
        criteria2.andSiteidEqualTo(sitearea.getId());
        criteria2.andStateEqualTo(1);
        List<Taskplaninfo> taskplaninfoList = taskplaninfoMapper.selectByExample(taskplaninfoExample);
        model.addAttribute("taskPlanList", taskplaninfoList);

        //区域、设备、巡检项

        if (taskplaninfoList.size() != 0 && taskplaninfoList != null) {
            if (siteId == null) {
                siteId = taskplaninfoList.get(0).getId();
                Taskplaninfo taskplaninfo = taskplaninfoMapper.selectTaskId(siteId);
//                model.addAttribute("taskContents", JsonUtil.toObject(taskplaninfo.getTaskcontent(), TaskContent.class));
                model.addAttribute("taskContents", taskplaninfo.getTaskcontent());
            }
           /* for(Taskplaninfo taskplaninfo: taskplaninfoList){
                Taskplaninfo taskplaninfo1 = taskplaninfoMapper.selectByPrimaryKey(taskplaninfo.getId());
                model.addAttribute("taskContents", JsonUtil.toObject(taskplaninfo1.getTaskcontent(), TaskContent.class));
            }*/
        }
        return "export/addExport";
    }

    @RequestMapping("/addExportInfo")
    @ResponseBody
    public int addExportInfo(Exportinfo exportinfo) {
        return exportInfoService.addExportInfo(exportinfo);
    }

    @RequestMapping("/deleteExportInfo")
    @ResponseBody
    public int deleteExportInfo(Integer id) {
        return exportInfoService.deleteExportInfo(id);
    }

    List<ExprotReportContent> allCheckitems; //所有此任务所对应的任务报告巡检项   ////2018年7月19日17:19:41  修改map为ExprotReportContent
    Exportinfo exportinfo = null; //所有此任务所对应的任务报告巡检项   ////2018年7月19日17:19:41  修改map为ExprotReportContent

    //查询出用户指定任务指定时间段的所有巡检项
    @RequestMapping("/selectExport2")
    @ResponseBody
    public String selectExport2(Integer exportinfoId, HttpServletResponse response1, String startTime, String endTime) throws Exception {
        exportinfo = exportInfoService.getExportinfoById(exportinfoId); //获取要导出的模板信息
        HashMap<String, Object> map = new HashMap<>(); //装入查询参数
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("taskId", exportinfo.getTaskid());

        allCheckitems = null;
        allCheckitems = exportInfoService.queryAllExportBySelect(map); //查询出用户指定任务指定时间段的所有巡检项
        if (allCheckitems == null || allCheckitems.size() == 0)
            return "0";
        return null;
    }

    @RequestMapping("/selectExport")
    @ResponseBody
    public String selectExport(Integer exportinfoId, HttpServletResponse response1, String startTime, String endTime) throws Exception {

        //查找总任务所有子任务  //2018年7月19日17:19:41  添加漏检的导出 条目
        TasktempinfoExample example = new TasktempinfoExample();
        TasktempinfoExample.Criteria criteria = example.createCriteria();
        criteria.andExecutetimeBetween(startTime, endTime);
        criteria.andTaskidEqualTo(Long.valueOf(exportinfo.getTaskid()));
        List<Tasktempinfo> tasktempinfos = tasktempinfoMapper.dadiyreport(example);
//        Collections.reverse(tasktempinfos); //倒序
        //2018年7月19日17:19:41  添加漏检的导出 条目

        if (exportinfo.getConsuming() == 1) { //******区域耗时勾选，表示显示区域耗时导出输油状态,区域耗时样式表不一样
            return exportShuYouState(tasktempinfos, allCheckitems, exportinfo, startTime, endTime, response1);
        }
        String taskContent = exportinfo.getTaskcontent(); //需要导出任务勾选的巡检项json
        Workbook workbook = new HSSFWorkbook();// 创建一个excel工作薄
        String name = exportinfo.getExportname() + ".xls"; //表名
        try {
            TaskContent content = JsonUtil.toObject(taskContent, TaskContent.class); //json生成对象

            if (exportinfo.getTasktype().equals("2") || exportinfo.getTasktype().equals("3")) { //如果任务类型是隐患排查或者视频巡检，则没有巡检项，走下面代码导出
                exportTaskReportByType2and3(tasktempinfos, allCheckitems, workbook, content, response1, exportinfo);//****** 处理不一样
                return null;
            }
            // 创建一个sheet页
            Sheet sheet = workbook.createSheet(name);
            HSSFCellStyle cellStyle = (HSSFCellStyle) workbook.createCellStyle();
            cellStyle.setAlignment(HSSFCellStyle.VERTICAL_CENTER);
            cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            int start = 2;
            int end = 1;
            List<Integer> indexList = new ArrayList<>();
            indexList.add(start); //首行设备合并单元格的角标集合
            for (TaskArea taskArea : content.getAreas()) {
                for (TaskEquipment taskEquipment : taskArea.getEquipments()) {
                    int itemSize = taskEquipment.getCheckItems().size();
                    if (itemSize > 0) { //有巡检项时
                        end += itemSize;
                        sheet.addMergedRegion(new CellRangeAddress(0, 0, start, end)); //合并单元格
                        start += taskEquipment.getCheckItems().size();
                        indexList.add(start);
                    }
                }
            }
            Row row = sheet.createRow(rowNumber);  //第一行，设备行，需要合并单元格，一个设备对应下面多个巡检项
            int index = 0;
            for (TaskArea taskArea : content.getAreas()) {
                for (TaskEquipment taskEquipment : taskArea.getEquipments()) {
                    int itemSize = taskEquipment.getCheckItems().size();
                    if (itemSize > 0) { //有巡检项时
                        System.out.printf(taskEquipment.getEquipment().getName());
                        HSSFCell cell = (HSSFCell) row.createCell(indexList.get(index));//需要根据角标来设置合并单元格的数据
                        cell.setCellStyle(cellStyle);
                        cell.setCellValue(taskEquipment.getEquipment().getName());
                        index++;
                    }
                }
            }
            Row row2 = sheet.createRow(++rowNumber); //第二行绘制巡检项行
            row2.createCell(0).setCellValue("日期");
            row2.createCell(1).setCellValue("时间");
            List<ExportReport3Content> equipmentCells = new ArrayList<>();  //区域名称
//            List<String> itemCells = new ArrayList<>();  //巡检项名称
//            List<Integer> itemCells2 = new ArrayList<>();    //巡检项状态
            int rowTwo = 2;
            for (TaskArea taskArea : content.getAreas()) {
                for (TaskEquipment taskEquipment : taskArea.getEquipments()) {

                    for (TaskCheckItem taskCheckItem : taskEquipment.getCheckItems()) {
                        String checkItem = taskCheckItem.getItem().getItemname();
                        row2.createCell(rowTwo).setCellValue(checkItem);
//                        itemCells.add(checkItem);
//                        itemCells2.add(taskCheckItem.getItem().getType());
                        rowTwo++;
                        ExportReport3Content exportReport3Content = new ExportReport3Content();
                        exportReport3Content.setEquipname(taskEquipment.getEquipment().getName());
                        exportReport3Content.setCheckname(taskCheckItem.getItem().getItemname());
                        exportReport3Content.setChecktype(taskCheckItem.getItem().getType());
                        equipmentCells.add(exportReport3Content);
                    }
                }
            }
            row2.createCell(rowTwo).setCellValue("巡检人");
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat df2 = new SimpleDateFormat("HH:mm:ss");


            //2018年7月19日17:19:41  添加漏检的导出 条目

//                for (ExprotReportContent exprotReportContent : allCheckitems) {
//                    saveCheckItem(content, exprotReportContent);
//                }
            for (Tasktempinfo tasktempinfo : tasktempinfos) {
                if (tasktempinfo.getOperationstate() == 1) {
                    drawMissingInspection(tasktempinfo.getExecutetime(), sheet, df, df2, equipmentCells);
                } else if (tasktempinfo.getOperationstate() == 2
                        || tasktempinfo.getOperationstate() == 3
                        || tasktempinfo.getOperationstate() == 4) {
                    drawReport(workbook, tasktempinfo.getTaskcode(), tasktempinfo.getUpdatetime(), allCheckitems,
                            sheet, df, df2, equipmentCells);

                }

            }
            //2018年7月19日17:19:41  添加漏检的导出 条目
//                for (ExprotReportContent checkItem : allCheckitems) { //******开始绘制数据
//                        row2 = createrowData(checkItem, sheet, content, df, df2, itemCells);
//                }

//                for (int i = 0; i < itemCells.size(); i++) { //绘制最后一行的数据
//                    for (int j = 0; j < keys.size(); j++) {
//                        if (itemCells.get(i).equals(keys.get(j))) {
//                            row2.createCell(cell++).setCellValue(values.get(j));
//                            break;
//                        }
//                    }
//                }
//                row2.createCell(cell).setCellValue(allCheckitems.get(allCheckitems.size() - 1).getWorker());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            response1.setHeader("Content-Disposition", "attachment;filename=" + new String(name.getBytes("GBK"), "ISO8859_1"));
            tempReporId = null;
            rowNumber = 0;
            cell = 0;
            rowTemp = null;
            values.clear();
            keys.clear();
            OutputStream fOut = response1.getOutputStream();
            workbook.write(fOut);
            fOut.flush();
            fOut.close();
        }

        return null;
    }

    private static long daysBetween(Date one, Date two) {
        long difference = (one.getTime() - two.getTime()) / 86400000;
        return Math.abs(difference);
    }

    /**
     * 1.查询出所有报告的区域及区域时间，把计算出来的时间间隔放到List里面，以map.put(reportid,list</time>)中
     * 2.查询出此任务所有输油状态巡检项，因为每个设备只有一个输油巡检项
     * //2018年7月23日17:30:37  修改 添加漏检等等  如果不需要， 就把
     * //漏检
     * if (tasktempinfo.getOperationstate() == 1) {
     * rowTemp = sheet.getRow(rowNumber + 1) != null ? sheet.getRow(++rowNumber) : sheet.createRow(++rowNumber); //绘制新行，导出这种表几乎是reontent表里拿到最终数据的，绘制逻辑较复杂
     * rowTemp.createCell(0).setCellValue(df1.format(tasktempinfo.getExecutetime())); //日期
     * rowTemp.createCell(1).setCellValue(df2.format(tasktempinfo.getExecutetime())); //时间
     * cell = 2;
     * rowTemp.createCell(cell++).setCellValue("-");//巡逻点
     * rowTemp.createCell(cell++).setCellValue("-"); //间隔时间
     * rowTemp.createCell(cell++).setCellValue("-"); //巡逻员
     * //                    rowTemp.createCell(cell++).setCellValue(map.get("equipname").toString()); //设备名
     * rowTemp.createCell(cell++).setCellValue("-"); //输油枚举项
     * rowTemp.createCell(cell++).setCellValue("漏检"); //备注
     * } 注释掉就行了
     *
     * @return
     */
    private String exportShuYouState(List<Tasktempinfo> tasktempinfos, List<ExprotReportContent> allCheckitems, Exportinfo exportinfo,
                                     String startTime, String endTime, HttpServletResponse response) {
        try {
            HashMap<String, Object> hashMap = new HashMap<>(); //参数map
            int taskid = exportinfo.getTaskid();
            hashMap.put("taskId", taskid);
            hashMap.put("startTime", startTime);
            hashMap.put("endTime", endTime);
            List<Map> areaTimelist = exportInfoService.getAreaInoutTime(hashMap); //查询所有任务报告的区域时间
            if (areaTimelist == null || areaTimelist.size() == 0) {
                return null;
            }
            Map<String, List<List>> intervalTimeMap = new HashMap<>(); // 所有的任务报告id及对应的区域时间和区域名称
            Long timeTemp = 0L; //计算时间间隔的中间变量
            List<List> listAreaTimeAndName = new ArrayList<>(); //装载的是对应报告的所有区域间隔时间和所有区域名称
            JSONArray arrayAll = new JSONArray();
            for (int i = 0; i < areaTimelist.size(); i++) {       //把所有的子任务的时间合并成一个集合
//                Map map=areaTimelist.get(i);
//                String areainoutTime = map.get("areainouttime").toString();
//                getIntervalTimeList(timeTemp, areainoutTime, listAreaTimeAndName);
////                getTimeList(timeTemp, areainoutTime, listAreaTimeAndName);
//                intervalTimeMap.put(map.get("id").toString(), listAreaTimeAndName);
//                if(i!=areaTimelist.size()-1) {
//                    listAreaTimeAndName.clear();
//                }
                Map map = areaTimelist.get(i);
                String areainoutTime = map.get("areainouttime").toString();
                JSONArray array = JSONArray.fromObject(areainoutTime); //开始解析
                arrayAll.addAll(array);

            }

            hashMap.put("checkname", "输油状态");
            List<ExprotReport2Content> shuyouList = exportInfoService.queryAllShuyouExportBySelect(hashMap); //查询出用户指定任务指定时间段的所有输油巡检项
           /* for(int i= 0 ; i< shuyouList.size();i++){
                Map map = shuyouList.get(i);
                String areainoutTime = map.get("areainouttime").toString();
                if(i>0){
                    getIntervalTimeList(timeTemp,areainoutTime,listAreaTimeAndName);
                    intervalTimeMap.put(map.get("reportid").toString(),listAreaTimeAndName);
                    if( i != areaTimelist.size()-1){
                        listAreaTimeAndName.clear();
                    }
                }

            }*/
            if (shuyouList == null || shuyouList.size() == 0) {
                return null;
            }

            HashMap<String, List<String>> listHashMap = getIntervalTimeList2(timeTemp, arrayAll, shuyouList);       // time 区域间隔时间  name 区域名称

//            intervalTimeMap.put(map.get("id").toString(), listAreaTimeAndName);
//            if(i!=areaTimelist.size()-1) {
            listAreaTimeAndName.clear();
//            }

            Workbook workbook = new HSSFWorkbook();// 创建一个excel工作薄
            String name = exportinfo.getExportname() + ".xls"; //表名
            Sheet sheet = workbook.createSheet(name);
            HSSFCellStyle cellStyle = (HSSFCellStyle) workbook.createCellStyle();
            cellStyle.setAlignment(HSSFCellStyle.VERTICAL_CENTER);
            cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            Row row = sheet.createRow(rowNumber);  //绘制第一行
            row.createCell(cell++).setCellValue("日期");
            row.createCell(cell++).setCellValue("时间");
            row.createCell(cell++).setCellValue("巡逻点");
            row.createCell(cell++).setCellValue("间隔时间");
            row.createCell(cell++).setCellValue("巡逻员");
//            row.createCell(cell++).setCellValue("设备名");
            row.createCell(cell++).setCellValue("输油状态");
            row.createCell(cell++).setCellValue("备注");
            row.createCell(cell).setCellValue("统计表");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            int dayCount = (int) (daysBetween(sdf.parse(endTime), sdf.parse(startTime)) + 1);

            int count = exportInfoService.queryCount((long) taskid, startTime, endTime);

            int noCount = dayCount - count;

            int louJian = 0;
            for (Tasktempinfo tasktempinfo : tasktempinfos) {
                if (tasktempinfo.getOperationstate() == 1)
                    louJian++;
            }
            for (int i = 1; i < 6; i++) {
                Row mRow = sheet.createRow(i);
                switch (i) { //统计
                    case 1:
                        mRow.createCell(cell).setCellValue("本月总天数：" + dayCount);
                        break;
                    case 2:
                        mRow.createCell(cell).setCellValue("本月输油天数：" + count);
                        break;
                    case 3:
                        mRow.createCell(cell).setCellValue("本月停输天数：" + noCount);
                        break;
                    case 4:
                        mRow.createCell(cell).setCellValue("漏检情况：" + louJian);
                        break;
                    case 5:
                        mRow.createCell(cell).setCellValue("其他说明：");
                        break;
                    default:
                }
            }
            DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd"); //时间格式 年月日
            DateFormat df2 = new SimpleDateFormat("HH:mm:ss");  //时间格式  时分秒

            List list1 = (List) listHashMap.get("time"); //任务报告中的时间间隔list
            List list2 = (List) listHashMap.get("name"); //任务报告中的区域名list ,因为要导出区域耗时，要按照taskReportInfo中的areainouttime来绘制cell，这两list会告诉我们一个任务报告中区域的顺序
            List list3 = (List) listHashMap.get("startTime"); //任务报告中的区域开始时间

            for (Tasktempinfo tasktempinfo : tasktempinfos) {
                //漏检
                if (tasktempinfo.getOperationstate() == 1) {
                    rowTemp = sheet.getRow(rowNumber + 1) != null ? sheet.getRow(++rowNumber) : sheet.createRow(++rowNumber); //绘制新行，导出这种表几乎是reontent表里拿到最终数据的，绘制逻辑较复杂
                    rowTemp.createCell(0).setCellValue(df1.format(tasktempinfo.getExecutetime())); //日期
                    rowTemp.createCell(1).setCellValue(df2.format(tasktempinfo.getExecutetime())); //时间
                    cell = 2;
                    rowTemp.createCell(cell++).setCellValue("-");//巡逻点
                    rowTemp.createCell(cell++).setCellValue("-"); //间隔时间
                    rowTemp.createCell(cell++).setCellValue("-"); //巡逻员
//                    rowTemp.createCell(cell++).setCellValue(map.get("equipname").toString()); //设备名
                    rowTemp.createCell(cell++).setCellValue("-"); //输油枚举项
                    rowTemp.createCell(cell++).setCellValue("漏检"); //备注
                } else {
                    for (int i = 0; i < shuyouList.size(); i++) { //绘制整个内容
                        ExprotReport2Content map = shuyouList.get(i);
                        if (Objects.equals(tasktempinfo.getId(), map.getTempId())) {

//                List<AreainoutTime> areainoutTime = JSONArray.fromObject(map.getAreainouttime());
//                JSONArray array = JSONArray.fromObject(map.get("areainouttime"));
//                for(int i=0;i<list2.size();i++) {//根据需求，每个区域得绘制一行
                            if (list2.get(i).equals(map.getAreaname())) {
//                if (list2.get(i).equals(map.get("areaname").toString())) {
//                        JSONObject area = array.getJSONObject(i);
//                        JSONArray times = area.getJSONArray("districtTime");
//                        JSONObject time = times.getJSONObject(0);
//                List<Map> timeListMap = JsonUtil.toObject(area.getString("districtTime"),List.class);
                                rowTemp = sheet.getRow(rowNumber + 1) != null ? sheet.getRow(++rowNumber) : sheet.createRow(++rowNumber); //绘制新行，导出这种表几乎是reontent表里拿到最终数据的，绘制逻辑较复杂
//                rowTemp.createCell(0).setCellValue(df1.format(checkItem.get("executetime"))); //日期
//                rowTemp.createCell(1).setCellValue(df2.format(checkItem.get("executetime"))); //时间
//                        rowTemp.createCell(0).setCellValue(df1.format(new Date(Long.parseLong(time.getString("startTime"))))); //日期
//                        rowTemp.createCell(1).setCellValue(df2.format(new Date(Long.parseLong(time.getString("startTime"))))); //时间
//                                if (i < list3.size()) {
//                                    rowTemp.createCell(0).setCellValue(df1.format(new Date(Long.parseLong(list3.get(i).toString())))); //日期
//                                    rowTemp.createCell(1).setCellValue(df2.format(new Date(Long.parseLong(list3.get(i).toString())))); //时间
                                    rowTemp.createCell(0).setCellValue(df1.format(map.getEndtime())); //日期
                                    rowTemp.createCell(1).setCellValue(df2.format(map.getEndtime())); //时间
//                                } else {
//                                    rowTemp.createCell(0).setCellValue("时间未知");
//                                    rowTemp.createCell(1).setCellValue("时间未知");
//                                }
                                cell = 2;
                                rowTemp.createCell(cell++).setCellValue(map.getEquipname());//巡逻点
                                if (i < list1.size()) {
                                    rowTemp.createCell(cell++).setCellValue(list1.get(i).toString()); //间隔时间
                                } else {
                                    rowTemp.createCell(cell++).setCellValue("时间未知");
                                }
                                rowTemp.createCell(cell++).setCellValue(map.getWorker()); //巡逻员
//                    rowTemp.createCell(cell++).setCellValue(map.get("equipname").toString()); //设备名
                                if (map.getEnumitem() != null && !map.getEnumitem().isEmpty())
                                    rowTemp.createCell(cell++).setCellValue(map.getEnumitem()); //输油枚举项
                                else
                                    rowTemp.createCell(cell++).setCellValue("-"); //输油枚举项
                            }
//                }
                            String desc = map.getEquipmentskipdesc() == null ? null : map.getEquipmentskipdesc();
                            String errContent = "巡检";
                            if (desc != null) {
                                errContent = desc;
                            }
                            rowTemp.createCell(cell++).setCellValue(errContent); //备注

//                createrowData3(map, sheet, intervalTimeMap, df, df2);
                            break;
                        }
                    }
                }
            }

            response.setHeader("Content-Disposition", "attachment;filename=" + new String(name.getBytes("GBK"), "ISO8859_1"));
            OutputStream fOut = response.getOutputStream();
            workbook.write(fOut);
            fOut.flush();
            fOut.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            rowTemp = null;  //这几个成员变量都要清空，因为Controller是单例。如果不清空，下次访问此接口使用这些数据会出问题
            cell = 0;
            rowNumber = 0;
            listMap = new ArrayList<>();
            tempReporId = null;
        }
        return null;
    }

    /**
     * 导出隐患排查、视频巡检
     *
     * @param tasktempinfos
     * @param list
     * @return
     */
    private void exportTaskReportByType2and3(List<Tasktempinfo> tasktempinfos, List<ExprotReportContent> list, Workbook wk,
                                             TaskContent content, HttpServletResponse response, Exportinfo exportinfo) throws IOException {

        int rowTwo = 0;
        String name = exportinfo.getExportname() + ".xls"; //表名
        Sheet sheet = wk.createSheet(name);  // 创建一个sheet页
        HSSFCellStyle cellStyle = (HSSFCellStyle) wk.createCellStyle(); //创建单元格样式，设置居中
        cellStyle.setAlignment(HSSFCellStyle.VERTICAL_CENTER);
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        Row row = sheet.createRow(rowNumber);  //第一行
        row.createCell(rowTwo++).setCellValue("日期");
        row.createCell(rowTwo++).setCellValue("时间");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); //年月日
        DateFormat df2 = new SimpleDateFormat("HH:mm:ss"); //时分秒

        List<ExportReport3Content> equipmentCells = new ArrayList<>();  //区域名称
//            List<String> itemCells = new ArrayList<>();  //巡检项名称
//            List<Integer> itemCells2 = new ArrayList<>();    //巡检项状态

//        List<String> equipments = new ArrayList<>();
//        List<Integer> equipments2 = new ArrayList<>();
        for (TaskArea taskArea : content.getAreas()) { //因为只有设备，没有巡检项，用设备名创建cell
            for (TaskEquipment taskEquipment : taskArea.getEquipments()) {
                row.createCell(rowTwo++).setCellValue(taskEquipment.getEquipment().getName());
//                equipments.add(taskEquipment.getEquipment().getName());
//                equipments2.add(1);

                ExportReport3Content exportReport3Content = new ExportReport3Content();
                exportReport3Content.setEquipname(taskEquipment.getEquipment().getName());
                exportReport3Content.setChecktype(1);
                equipmentCells.add(exportReport3Content);
            }
        }
        row.createCell(rowTwo++).setCellValue("巡检人");
        Row row2 = null;
        for (Tasktempinfo tasktempinfo : tasktempinfos) {
            if (tasktempinfo.getOperationstate() == 1) {
                drawMissingInspection(tasktempinfo.getExecutetime(), sheet, df, df2, equipmentCells);
            } else if (tasktempinfo.getOperationstate() == 2
                    || tasktempinfo.getOperationstate() == 3
                    || tasktempinfo.getOperationstate() == 4) {
                if (tasktempinfo.getStopstate() == 2) {
                    drawReport2(wk, tasktempinfo.getTaskcode(), tasktempinfo.getExecutetime(), list,
                            sheet, df, df2, equipmentCells);
                } else
                    drawReport2(wk, tasktempinfo.getTaskcode(), tasktempinfo.getUpdatetime(), list,
                            sheet, df, df2, equipmentCells);
            }

        }
//        if (list.size() > 0) { //代码健壮性，没有查出巡检项导的是空白excel
//            for (ExprotReportContent checkItem : list) {
//                row2 = createrowData2(checkItem, sheet, content, df, df2, equipments);
//            }
//            for (int i = 0; i < equipments.size(); i++) {
//                for (int j = 0; j < keys.size(); j++) {
//                    if (equipments.get(i).equals(keys.get(j))) {
//                        row2.createCell(cell++).setCellValue(values.get(j));
//                        break;
//                    }
//                }
//            }
//            row2.createCell(cell).setCellValue(list.get(list.size() - 1).getWorker()); //最后一个cell是巡检人名字
//        } else {
//            wk = new HSSFWorkbook();
//        }
        response.setHeader("Content-Disposition", "attachment;filename=" + new String(name.getBytes("GBK"), "ISO8859_1"));
        rowNumber = 0; //因为Controller是单例，这些成员变量得清空
        tempReporId = null;
        OutputStream fOut = response.getOutputStream();
        wk.write(fOut);
        fOut.flush();
        fOut.close();
    }

    //为了避免一个方法参数太多，把一些要用到的临时变量放到类成员变量中。因为Controller是单例，所以方法结束前要初始化。
    private Long tempReporId;
    private int rowNumber = 0;
    private int cell = 0;
    private Row rowTemp;
    private List<String> keys = new ArrayList<>(); //对应的巡检项Column
    private HashMap<String, List<String>> hashMap = new HashMap<>(); //对应的报告号
    private List<String> values = new ArrayList<>(); //巡检项column对应的值
    List<Map> listMap = new ArrayList<>(); //装载一份报告对应的所有巡检项

    /**
     * @param checkItem 巡检顶
     * @param sheet     要操作的表
     * @param content   用户要导出的巡检项
     * @param df1       日期格式类
     * @param df2       时间格式类
     * @return Row 遍历完所有巡检项后最后返回的Excel记录行
     */
    private synchronized Row createrowData(ExprotReportContent checkItem, Sheet sheet, TaskContent content,
                                           DateFormat df1, DateFormat df2, List<String> itemCells) {
        Long reportId = Long.valueOf(checkItem.getReportid()); //获取巡检项的任务报告id

        isNewExcelLine(checkItem, sheet, df1, df2, itemCells, reportId);
        //同一区域同一设备的巡检项
        for (TaskArea taskArea : content.getAreas()) {
            String areaname = taskArea.getArea().getCustomid();
            for (TaskEquipment taskEquipment : taskArea.getEquipments()) {
                String equipName = taskEquipment.getEquipment().getName();

                for (TaskCheckItem taskCheckItem : taskEquipment.getCheckItems()) {
                    String itemname = taskCheckItem.getItem().getItemname();

                    if (checkItem.getAreaname().equals(areaname) && checkItem.getEquipname().equals(equipName)
                            && checkItem.getCheckname().equals(itemname)) { //
                        if (checkItem.getChecktype().equals("记录项")) {
                            String numvalue = checkItem.getNumvalue() == null ? "0" : checkItem.getNumvalue();
                            keys.add(itemname);
                            values.add(numvalue);
//                            rowTemp.createCell(cell).setCellValue(numvalue);
                        } else {
                            if (checkItem.getChecktype().equals("枚举项")) {
                                String enumitem = checkItem.getEnumitem();
                                keys.add(itemname);
                                values.add(enumitem);
                            } else {
                                if (checkItem.getReportstate().equals("1")) {
                                    String errContent = checkItem.getErrcontent() + "," +
                                            JsonUtil.toJSON(checkItem.getImg() + "," +
                                                    JsonUtil.toJSON(checkItem.getAudio() + "," +
                                                            JsonUtil.toJSON(checkItem.getVideo())));
                                    keys.add(itemname);
                                    values.add(errContent);
                                } else {
                                    String desc = checkItem.getEquipmentskipdesc() == null ? null : checkItem.getEquipmentskipdesc();
                                    String errContent = "正常";
                                    if (desc != null) {
                                        if (desc.contains("被动终止")) {
                                            errContent = "超时";
                                        } else if (desc.contains("主动终止")) {
                                            errContent = "主动终止";
                                        } else if (desc.contains("跳检")) {
                                            errContent = "跳检";
                                        }
                                    }
                                    keys.add(itemname);
                                    values.add(errContent);
                                }
                            }
                        }
                        return rowTemp;
                    }
                }
            }
        }
        return rowTemp;
    }

    /**
     * 当tempReportId 更新时，excel将开始绘制新行
     *
     * @param checkItem 巡检项报告内容
     * @param sheet     excel表
     * @param df1       格式 年月日
     * @param df2       格式 时分秒
     * @param itemCells 要导出的巡检项
     * @param reportId  报告id
     */
    private void isNewExcelLine(ExprotReportContent checkItem, Sheet sheet, DateFormat df1,
                                DateFormat df2, List<String> itemCells, Long reportId) {
        if (tempReporId == null || reportId.longValue() != tempReporId.longValue()) {//null表示第一个巡检项  != 表示下一个任务报告

            if (tempReporId != null && reportId.longValue() != tempReporId.longValue() && rowTemp != null) {
                //开始绘制一个任务执行记录
                for (int i = 0; i < itemCells.size(); i++) {
                    for (int j = 0; j < keys.size(); j++) {
                        if (itemCells.get(i).equals(keys.get(j))) {
                            rowTemp.createCell(cell++).setCellValue(values.get(j));
                            break;
                        }
                    }
                }
                values.clear();
                keys.clear();
                rowTemp.createCell(cell).setCellValue(checkItem.getWorker()); //在excel记录行尾加上工人名字
            }
            //新的报告id
            tempReporId = reportId;

            rowTemp = sheet.createRow(++rowNumber); //创建新行
            rowTemp.createCell(0).setCellValue(df1.format(checkItem.getExecutetime())); //日期
            rowTemp.createCell(1).setCellValue(df2.format(checkItem.getExecutetime())); //时间
            cell = 2;
        }
    }

    /**
     * type 2 HSE隐患排查  3 视频巡检
     * 当执行任务类型为HSE隐患排查和视频巡检时这样导出才对，因为此时任务没有巡检项，只需给出设备状况就行
     *
     * @return
     */
    private synchronized Row createrowData2(ExprotReportContent checkItem, Sheet sheet, TaskContent content,
                                            DateFormat df1, DateFormat df2, List<String> equipments) {
        Long reportId = Long.valueOf(checkItem.getReportid()); //获取巡检项的任务报告id
        isNewExcelLine(checkItem, sheet, df1, df2, equipments, reportId);
        //同一区域同一设备的巡检项
        for (TaskArea taskArea : content.getAreas()) {
            String areaname = taskArea.getArea().getCustomid();
            for (TaskEquipment taskEquipment : taskArea.getEquipments()) {
                String equipName = taskEquipment.getEquipment().getName();
                String errContent = "正常"; //默认设备正常
                StringBuffer sb = new StringBuffer();
                sb.append(errContent);
                if (checkItem.getAreaname().equals(areaname) && checkItem.getEquipname().equals(equipName)) { //找到checkItem
                    if (checkItem.getReportstate().equals("1")) { //报告内容有异常时
                        errContent = checkItem.getErrcontent() + "," + JsonUtil.toJSON(checkItem.getImg() + "," + JsonUtil.toJSON(checkItem.getAudio())
                                + "," + JsonUtil.toJSON(checkItem.getVideo()));
                    }

                    rowTemp.createCell(cell).setCellValue(errContent);
                    values.add(errContent);
                    keys.add(equipName);
                    cell++;
                }
            }
        }
        return rowTemp;
    }

    /**
     * 导出区域间隔时间
     *
     * @param checkItem 巡检项
     * @param sheet     sheet表
     * @param map       //map里的key是reportId,value是集合
     * @param df1       时间格式 年月日
     * @param df2       时间格式 时分秒
     */
    private void createrowData3(ExprotReportContent checkItem, Sheet sheet, Map<String, List<List>> map, DateFormat df1, DateFormat df2) {
        Long reportId = Long.valueOf(checkItem.getReportid()); //获取巡检项的任务报告id
        List<List> ll = map.get(reportId + ""); //根据报告id拿到对应的区域时间间隔集合和区域名称集合
        List list1 = ll.get(0); //任务报告中的时间间隔list
        List list2 = ll.get(1); //任务报告中的区域名list ,因为要导出区域耗时，要按照taskReportInfo中的areainouttime来绘制cell，这两list会告诉我们一个任务报告中区域的顺序

        JSONArray array = JSONArray.fromObject(checkItem.getAreainouttime());

        for (int i = 0; i < list2.size(); i++) {//根据需求，每个区域得绘制一行
            if (list2.get(i).equals(checkItem.getAreaname())) {
                JSONObject area = array.getJSONObject(i);
                JSONArray times = area.getJSONArray("districtTime");
                JSONObject time = times.getJSONObject(0);
//                List<Map> timeListMap = JsonUtil.toObject(area.getString("districtTime"),List.class);
                rowTemp = sheet.getRow(rowNumber + 1) != null ? sheet.getRow(++rowNumber) : sheet.createRow(++rowNumber); //绘制新行，导出这种表几乎是reontent表里拿到最终数据的，绘制逻辑较复杂
//                rowTemp.createCell(0).setCellValue(df1.format(checkItem.get("executetime"))); //日期
//                rowTemp.createCell(1).setCellValue(df2.format(checkItem.get("executetime"))); //时间
                rowTemp.createCell(0).setCellValue(df1.format(new Date(Long.parseLong(time.getString("startTime"))))); //日期
                rowTemp.createCell(1).setCellValue(df2.format(new Date(Long.parseLong(time.getString("startTime"))))); //时间
                cell = 2;
                rowTemp.createCell(cell++).setCellValue(checkItem.getAreaname());//巡逻点
                rowTemp.createCell(cell++).setCellValue(list1.get(i).toString()); //间隔时间
                rowTemp.createCell(cell++).setCellValue(checkItem.getWorker()); //巡逻员
                rowTemp.createCell(cell++).setCellValue(checkItem.getEquipname()); //设备名
                rowTemp.createCell(cell++).setCellValue(checkItem.getEnumitem()); //输油枚举项
                break;
            }
        }
        String desc = checkItem.getEquipmentskipdesc() == null ? null : checkItem.getEquipmentskipdesc();
        String errContent = "巡检";
        if (desc != null) {
            if (desc.contains("被动终止")) {
                errContent = "超时";
            } else if (desc.contains("主动终止")) {
                errContent = "主动终止";
            } else if (desc.contains("跳检")) {
                errContent = "跳检";
            }
        }
        rowTemp.createCell(cell++).setCellValue(errContent); //备注

    }

    /**
     * 解析tempreportinfo的areainouttime，获得区域名称List和区域时间间隔List。区域耗时间隔（当前区域的开始时间-上一个区域的开始时间）
     *
     * @param timeTemp 计算时间间隔的中间变量
     * @param json     areainouttime字段的Json字符串
     * @param listAll
     * @return
     */
    private static List getIntervalTimeList(Long timeTemp, String json, List<List> listAll) {
        List<String> list1 = new ArrayList<>(); //装时间间隔
        List<String> list2 = new ArrayList<>(); //装区域名字
        list1.add("空");
        JSONArray array = JSONArray.fromObject(json); //开始解析
        for (int i = 0; i < array.size(); i++) {
            JSONObject area = array.getJSONObject(i);
            JSONArray times = area.getJSONArray("districtTime");
            String areaName = area.getString("districtName");
            list2.add(areaName);
            JSONObject time = times.getJSONObject(0);
            if (i > 0) { //到第二个区域时，才开始计算区域耗时间隔
                long interval = Long.parseLong(time.getString("startTime")) - timeTemp;
                list1.add(getDatePoor(interval));
            }
            timeTemp = Long.parseLong(time.getString("startTime"));
        }
        listAll.add(list1);
        listAll.add(list2);
        return listAll;
    }


    private static HashMap<String, List<String>> getIntervalTimeList2(Long timeTemp, JSONArray array,
                                                                      List<ExprotReport2Content> listAll) {

        List<String> list1 = new ArrayList<>(); //装时间间隔
        List<String> list2 = new ArrayList<>(); //装区域名字
        List<String> list3 = new ArrayList<>(); //装区域开始时间
        list1.add("00:00:00");
        for (int i = 0; i < array.size(); i++) {

            JSONObject area = array.getJSONObject(i);
            JSONArray times = area.getJSONArray("deviceTime");
            String areaName = area.getString("districtName");
            list2.add(areaName);
            for (int i1 = 0; i1 < times.size(); i1++) {
                if (listAll.get(0).getEquipname().equals(times.getJSONObject(i1).getString("deviceName"))) {
                    JSONObject time = times.getJSONObject(i1).getJSONArray("deviceTime").getJSONObject(0);
                    if (time.getString("startTime") != null && !time.getString("startTime").isEmpty()) {
                        if (i > 0) { //到第二个区域时，才开始计算区域耗时间隔
                            long interval = Long.parseLong(time.getString("startTime")) - timeTemp;
                            list1.add(getDatePoor(interval));
                        }
                        timeTemp = Long.parseLong(time.getString("startTime"));
                        list3.add(String.valueOf(timeTemp));
                    } else {
                        JSONObject time1 = times.getJSONObject(0).getJSONArray("deviceTime").getJSONObject(0);
                        if (time1.getString("startTime") != null && !time1.getString("startTime").isEmpty()) {
                            if (i > 0) { //到第二个区域时，才开始计算区域耗时间隔
                                long interval = Long.parseLong(time1.getString("startTime")) - timeTemp;
                                list1.add(getDatePoor(interval));
                            }
                            timeTemp = Long.parseLong(time1.getString("startTime"));
                            list3.add(String.valueOf(timeTemp));
                        }

                    }
                }
            }
        }
        HashMap<String, List<String>> hashMap = new HashMap<>();
        hashMap.put("time", list1);//所有任务的间隔时间
        hashMap.put("name", list2);//所有任务的区域名称
        hashMap.put("startTime", list3);
        return hashMap;
    }

    /**
     * 根据两个时间的毫秒时间差异获得格式是时分秒的差异
     *
     * @param diff 毫秒时间差
     */
    public static String getDatePoor(Long diff) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long ns = 1000;
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        long sec = diff % nd % nh % nm / ns;
        StringBuffer sb = new StringBuffer();
        if (day != 0) {
            sb.append(day + "天");
        }
        if (hour != 0) {
            sb.append(hour + ":");
        }
        if (min != 0) {
            sb.append(min + ":");
        }
        if (sec != 0) {
            sb.append(sec);
        }
        return sb.toString();
    }

    //绘制漏检
    public synchronized void drawMissingInspection(Date datetime, Sheet sheet,
                                                   DateFormat df1, DateFormat df2, List<ExportReport3Content> exportReport3ContentList) {

        rowTemp = sheet.createRow(++rowNumber); //创建新行
        cell = 0;
        rowTemp.createCell(cell++).setCellValue(df1.format(datetime)); //日期
        rowTemp.createCell(cell++).setCellValue(df2.format(datetime)); //时间
        //开始绘制一个任务执行记录
        for (int i = 0; i < exportReport3ContentList.size(); i++) {
            rowTemp.createCell(cell++).setCellValue("-");
        }
        rowTemp.createCell(cell).setCellValue("漏检"); //在excel记录行尾加上工人名字

    }

    //绘制非漏检的
    public synchronized void drawReport(Workbook workbook, String taskcode, Date updatetime,
                                        List<ExprotReportContent> allCheckitems, Sheet sheet, DateFormat df1,
                                        DateFormat df2, List<ExportReport3Content> exportReport3ContentList) {

        rowTemp = sheet.createRow(++rowNumber); //创建新行
        cell = 0;
        rowTemp.createCell(cell++).setCellValue(df1.format(updatetime)); //日期
        rowTemp.createCell(cell++).setCellValue(df2.format(updatetime)); //时间

        String worker = "";
        int index = 0;
        //开始绘制一个任务执行记录
        for (int i = 0; i < exportReport3ContentList.size(); i++) {
            for (ExprotReportContent checkItem : allCheckitems) {
                if (taskcode.equals(checkItem.getTaskcode())) {
                    worker = checkItem.getWorker();
                    if (exportReport3ContentList.get(i).getEquipname().equals(checkItem.getEquipname()) &&
                            exportReport3ContentList.get(i).getCheckname().equals(checkItem.getCheckname())) {
                        String value = null;
                        index++;
                        if (checkItem.getEquipmentskipdesc() != null && !checkItem.getEquipmentskipdesc().isEmpty()) {
                            rowTemp.createCell(cell++).setCellValue(checkItem.getEquipmentskipdesc()); //内容
                        } else if (1 == exportReport3ContentList.get(i).getChecktype()) {  //状态项
                            if (checkItem.getReportstate().equals("1")) {
                                if (checkItem.getErrcontent() != null && !checkItem.getErrcontent().isEmpty()) {
                                    if (checkItem.getImg().contains("images")
                                            || checkItem.getAudio().contains("audio")
                                            || checkItem.getVideo().contains("video")) {
                                        //生成单元格样式
                                        HSSFCellStyle cellStyle = (HSSFCellStyle) workbook.createCellStyle();
                                        //新建font实体
                                        HSSFFont hssfFont = (HSSFFont) workbook.createFont();
                                        //设置字体颜色
                                        hssfFont.setColor(HSSFColor.BLUE.index);
                                        //设置删除线   strikeout（删除线）
//                                hssfFont.setStrikeout(true);
                                        //设置是否斜体
//                                hssfFont.setItalic(true);
                                        //字体大小
//                                hssfFont.setFontHeightInPoints((short) 24);
//                                hssfFont.setFontName("楷体");
                                        //粗体
//                                hssfFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
                                        //设置下滑线   1:有下滑线 0：没有

                                        hssfFont.setUnderline((byte) 1);
                                        cellStyle.setFont(hssfFont);
                                        Cell cell = rowTemp.createCell(this.cell++);
                                        cell.setCellValue(checkItem.getErrcontent());
                                        HSSFHyperlink link = new HSSFHyperlink(HSSFHyperlink.LINK_URL);//建一个HSSFHyperlink实体，指明链接类型为URL（这里是枚举，可以根据需求自行更改）
                                        link.setAddress("http://" + ip + "/toException?img=" + checkItem.getImg() +
                                                "&audio=" + checkItem.getAudio() +
                                                "&video=" + checkItem.getVideo());//给HSSFHyperlink的地址赋值
                                        cell.setHyperlink(link);//将链接方式赋值给单元格的Hyperlink即可将链接附加到单元格上

                                        cell.setCellStyle(cellStyle);
                                    } else {
                                        rowTemp.createCell(cell++).setCellValue(checkItem.getErrcontent()); //内容
                                    }
                                } else {
                                    value = "未填写";
                                    rowTemp.createCell(cell++).setCellValue(value); //内容
                                }
                            } else {
                                value = "正常";
                                rowTemp.createCell(cell++).setCellValue(value); //内容
                            }
                        } else if (2 == exportReport3ContentList.get(i).getChecktype()) {     //记录项
                            value = checkItem.getNumvalue() == null ? "0" : checkItem.getNumvalue();
                            rowTemp.createCell(cell++).setCellValue(value); //内容
                        } else if (3 == exportReport3ContentList.get(i).getChecktype()) {     //枚举项
                            value = checkItem.getEnumitem();
                            rowTemp.createCell(cell++).setCellValue(value); //内容
                        } else {
                            rowTemp.createCell(cell++).setCellValue("-");
                        }

                    }
                }
            }
        }
        if (index != exportReport3ContentList.size()) {
            for (int i = index; i < exportReport3ContentList.size(); i++) {
                rowTemp.createCell(cell++).setCellValue("-");
            }
        }
        rowTemp.createCell(cell++).setCellValue(worker); //在excel记录行尾加上工人名字
    }


    //绘制视频 隐患 正常完成， 超时， 漏检
    public synchronized void drawReport2(Workbook workbook, String taskcode, Date updatetime, List<ExprotReportContent> allCheckitems, Sheet sheet, DateFormat df1,
                                         DateFormat df2, List<ExportReport3Content> exportReport3ContentList) {

        rowTemp = sheet.createRow(++rowNumber); //创建新行
        cell = 0;
        rowTemp.createCell(cell++).setCellValue(df1.format(updatetime)); //日期
        rowTemp.createCell(cell++).setCellValue(df2.format(updatetime)); //时间

        String worker = "";
        //开始绘制一个任务执行记录
        for (int i = 0; i < exportReport3ContentList.size(); i++) {
            for (ExprotReportContent checkItem : allCheckitems) {
                if (taskcode.equals(checkItem.getTaskcode())) {
                    worker = checkItem.getWorker();
                    if (exportReport3ContentList.get(i).getEquipname().equals(checkItem.getAreaname())) {
                        String value = null;
                        if (checkItem.getEquipmentskipdesc() != null && !checkItem.getEquipmentskipdesc().isEmpty()) {
                            rowTemp.createCell(cell++).setCellValue(checkItem.getEquipmentskipdesc()); //内容
                        } else if (1 == exportReport3ContentList.get(i).getChecktype()) {  //状态项
                            if (checkItem.getReportstate().equals("1")) {
                                if (checkItem.getErrcontent() != null && !checkItem.getErrcontent().isEmpty()) {
                                    if (checkItem.getImg().contains("images")
                                            || checkItem.getAudio().contains("audio")
                                            || checkItem.getVideo().contains("video")) {
                                        value = checkItem.getErrcontent();
                                        //生成单元格样式
                                        HSSFCellStyle cellStyle = (HSSFCellStyle) workbook.createCellStyle();
                                        //新建font实体
                                        HSSFFont hssfFont = (HSSFFont) workbook.createFont();
                                        //设置字体颜色
                                        hssfFont.setColor(HSSFColor.BLUE.index);
                                        //设置删除线   strikeout（删除线）
//                                hssfFont.setStrikeout(true);
//                                    设置是否斜体
//                                hssfFont.setItalic(true);
                                        //字体大小
//                                hssfFont.setFontHeightInPoints((short) 24);
//                                hssfFont.setFontName("楷体");
                                        //粗体
//                                hssfFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
                                        //设置下滑线   1:有下滑线 0：没有
                                        hssfFont.setUnderline((byte) 1);
                                        cellStyle.setFont(hssfFont);
                                        Cell cell = rowTemp.createCell(this.cell++);
                                        cell.setCellValue(value);
                                        HSSFHyperlink link = new HSSFHyperlink(HSSFHyperlink.LINK_URL);//建一个HSSFHyperlink实体，指明链接类型为URL（这里是枚举，可以根据需求自行更改）
                                        link.setAddress("http://" + ip + "/toException?img=" + checkItem.getImg() +
                                                "&audio=" + checkItem.getAudio() +
                                                "&video=" + checkItem.getVideo());//给HSSFHyperlink的地址赋值
                                        cell.setHyperlink(link);//将链接方式赋值给单元格的Hyperlink即可将链接附加到单元格上
                                        cell.setCellStyle(cellStyle);
                                    } else {
                                        rowTemp.createCell(cell++).setCellValue(checkItem.getErrcontent()); //内容
                                    }
//                                cell.setCellType(HSSFCell.CELL_TYPE_FORMULA);
                                } else {
                                    value = "未填写";
                                    rowTemp.createCell(cell++).setCellValue(value); //内容
                                }
                            } else {
                                value = "正常";
                                rowTemp.createCell(cell++).setCellValue(value); //内容
                            }
                        } else if (2 == exportReport3ContentList.get(i).getChecktype()) {     //记录项
                            value = checkItem.getNumvalue() == null ? "0" : checkItem.getNumvalue();
                            rowTemp.createCell(cell++).setCellValue(value); //内容
                        } else if (3 == exportReport3ContentList.get(i).getChecktype()) {     //枚举项
                            value = checkItem.getEnumitem();
                            rowTemp.createCell(cell++).setCellValue(value); //内容
                        } else
                            rowTemp.createCell(cell++).setCellValue("-");

                    }
                }
            }
        }
        rowTemp.createCell(cell++).setCellValue(worker); //在excel记录行尾加上工人名字
    }


}
