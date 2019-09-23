package com.niule.znxj.web.controller;

import com.niule.znxj.core.util.ReportContentUtils;
import com.niule.znxj.core.util.json.JsonUtil;
import com.niule.znxj.web.dao.ReportcontentMapper;
import com.niule.znxj.web.dao.TaskreportinfoMapper;
import com.niule.znxj.web.model.*;
import com.niule.znxj.web.model.taskcontent.TaskContent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by administor on 2017/3/15.
 */
@Controller
public class GroupController {
    @Resource
    private ReportcontentMapper reportcontentMapper;
    @Resource
    private TaskreportinfoMapper taskreportinfoMapper;


    @RequestMapping("addreportcontent")
    @ResponseBody
    public int addreportcontent(){

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String aa="[{\"areaname\":\"H13\",\"areaskip\":0,\"checkname\":\"工作状态\",\"checktype\":\"状态项\",\"equipmentskip\":0,\"equipname\":\"换热器\",\"errcontent\":\"adfgrgdfg\",\"numvalue\":\"\",\"operationtime\":1496736196459,\"recordname\":\"\",\"reportstate\":\"1\",\"unitname\":\"\"},{\"areaname\":\"C14\",\"areaskip\":0,\"checkname\":\"冷凝水排放状态\",\"checktype\":\"状态项\",\"equipmentskip\":0,\"equipname\":\"压缩机\",\"errcontent\":\"sdfef\",\"img\":[\"/images/2017/6/6/e03a57e8-3c20-4bea-beee-f4b12e7a8c79_1494237444825.jpg\"],\"numvalue\":\"\",\"operationtime\":1496736209301,\"recordname\":\"\",\"reportstate\":\"1\",\"unitname\":\"\"},{\"areaname\":\"C10A\",\"areaskip\":0,\"checkname\":\"工作状态\",\"checktype\":\"状态项\",\"equipmentskip\":0,\"equipname\":\"二级压缩机\",\"numvalue\":\"\",\"operationtime\":1496736223314,\"recordname\":\"\",\"reportstate\":\"0\",\"unitname\":\"\"},{\"areaname\":\"C10B\",\"areaskip\":0,\"audio\":[],\"checkname\":\"压差\",\"checktype\":\"记录项\",\"equipmentskip\":0,\"equipname\":\"二级联轴\",\"errcontent\":\"\",\"img\":[],\"lowerwarning\":\"1\",\"normalmax\":\"2\",\"normalmin\":\"1\",\"numvalue\":\"3\",\"operationtime\":1496736231043,\"recordname\":\"压差\",\"reportstate\":\"\",\"unitname\":\"%\",\"upperwarning\":\"2\",\"video\":[]}]";
//        String a="148";
//        Long taskid=Long.parseLong(a);
        TaskreportinfoExample taskreportinfoExample=new TaskreportinfoExample();
        taskreportinfoExample.createCriteria().andIdEqualTo(Long.valueOf("9675"));
        List<Taskreportinfo> taskreportinfos=taskreportinfoMapper.selectByExample(taskreportinfoExample);
        int b=0;
        List<Reportcontent> reportcontents ;
        List<List<Reportcontent>> lists = new ArrayList<>();

       /* for (Taskreportinfo info : taskreportinfos) {
            ReportcontentExample example = new ReportcontentExample();
            example.createCriteria().andReportidEqualTo(info.getId());
            reportcontents = reportcontentMapper.selectByExample(example);
            System.out.print("test"+info.getTaskcode()+"-----"+reportcontents.size());
            lists.add(reportcontents);
        }*/
        for(Taskreportinfo taskreportinfo:taskreportinfos){
            List<Reportcontent> reportcontentList = ReportContentUtils.getReportConentList(reportcontentMapper,taskreportinfo.getId());

//            String content = "{" + "\"res\":" + taskreportinfo.getContent() + "}";
            if (reportcontentList.size()!= 0 && reportcontentList != null) {
//            if (content != null) {
//                TaskReportRes res = JsonUtil.toObject(content, TaskReportRes.class);
                for (Reportcontent item : reportcontentList) {
                    String checkitype = item.getChecktype();
                    Float normalmin = Float.valueOf(item.getNormalmin());
                    Float normalmax = Float.valueOf(item.getNormalmax());
                    Float lowerwarning = Float.valueOf(item.getLowerwarning());
                    Float upperwarning = Float.valueOf(item.getUpperwarning());
                    String a = "";
                    String e = "";
                    String c = "";
                    String d = "";
                    if (checkitype.equals("记录项")) {
                        if (normalmin == 0 || normalmin == null) {
                            a = "-";
                        } else
                            a = normalmin + "";
                        if (normalmax == 0 || normalmax == null) {
                            e = "-";
                        } else
                            e = normalmax + "";
                        if (lowerwarning == 0 || lowerwarning == null) {
                            c = "-";
                        } else
                            c = lowerwarning + "";
                        if (upperwarning == 0 || upperwarning == null) {
                            d = "-";
                        } else
                            d = upperwarning + "";
                    }
                    if (checkitype.equals("状态项")) {
                        a = "-";
                        e = "-";
                        c = "-";
                        d = "-";
                    }
                    if (checkitype.equals("")) {
                        a = "-";
                        e = "-";
                        c = "-";
                        d = "-";
                    }
                    Reportcontent reportcontent = new Reportcontent(item.getAreaname(), item.getEquipname(), item.getCheckname(), item.getChecktype(), item.getNumvalue(),
                            item.getRecordname(), item.getUnitname(), item.getReportstate(), item.getErrcontent(), item.getAreaskip(), item.getEquipmentskip(),
                            item.getAreaskipdesc(), item.getEquipmentskipdesc(), sdf.format(item.getOperationtime()),
                            taskreportinfo.getId(), c, e, a, d, JsonUtil.toJSON(item.getVideo()), JsonUtil.toJSON(item.getAudio()), JsonUtil.toJSON(item.getImg()));
                    reportcontent.setReportid(taskreportinfo.getId());
                    reportcontentMapper.insert(reportcontent);
                }
            }
        }

        return b;
    }

}
