package com.niule.znxj.web.controller;

import com.github.pagehelper.PageInfo;
import com.niule.znxj.core.common.Resources;
import com.niule.znxj.core.entity.JSONResult;
import com.niule.znxj.core.entity.Result;
import com.niule.znxj.core.util.DateUtils;
import com.niule.znxj.core.util.PageBean;
import com.niule.znxj.core.util.PageUtil;
import com.niule.znxj.core.util.json.JsonUtil;
import com.niule.znxj.web.dao.*;
import com.niule.znxj.web.model.*;
import com.niule.znxj.web.model.response.ShowReportParam;
import com.niule.znxj.web.model.response.WebChartRes2;
import com.niule.znxj.web.service.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by administor on 2017/4/6.
 */
@Controller
public class WebTaskreportController {


    @Resource
    private TaskreportService taskreportService;
    @Resource
    private TaskstopinfoMapper taskstopinfoMapper;
    @Resource
    private OperateLogService operateLogService;
    @Resource
    private SiteService siteService;
    @Resource
    private TaskPlanService taskPlanService;
    @Resource
    private AreaService areaService;
    @Resource
    private EquipmentService equipmentService;
    @Resource
    private CheckInfoService checkInfoService;
    @Resource
    private EquipmentTaskMapper equipmentTaskMapper;
    @Resource
    private CheckitemTaskMapper checkitemTaskMapper;
    @Resource
    private ReportcontentMapper reportcontentMapper;
    @Resource
    private TasktempinfoMapper tasktempinfoMapper;
    @Resource
    private SystemService systemService;
    private String ip = Resources.ApplicationResources.getString("ip");
    protected PageBean pageBean = new PageBean();


    public PageBean getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean pageBean) {
        this.pageBean = pageBean;
    }


    @RequestMapping("/toshowreport")
    public String toshowreport(Model m, int tasktype, HttpServletRequest request) {
        Admininfo admininfo = (Admininfo) request.getSession().getAttribute("userInfo");
        List ids = new ArrayList();
        if (admininfo.getSiteid() == null) {
            List<Siteareainfo> sites = siteService.queryAllSite();
            for (Siteareainfo siteareainfo : sites) {
                ids.add(siteareainfo.getId());
            }
        } else {
            ids.add(admininfo.getSiteid());
        }
        HashMap<String, Object> map = new HashMap<String, Object>();
        List<Siteareainfo> siteareainfos = siteService.selectByExample2(admininfo.getSiteid());

        m.addAttribute("type", tasktype);
        m.addAttribute("sites", siteareainfos);
        m.addAttribute("roleid", admininfo.getRoleid());
        map.put("tasktype", tasktype);
        map.put("ids", ids);
        return "showreport1";
    }

    /**
     * 分页显示所有任务的报告 - 列表
     *
     * @param m
     * @param page         当前页
     * @param tasktype     任务状态 0日常巡检 1计划巡检 2隐患排查 3视频巡检 4临时任务
     * @param taskCcode    任务编号
     * @param reportSstate 报告状态 0 正常 1 异常
     * @param time1        开始时间
     * @param time2        结束时间
     * @return
     */
    @RequestMapping("/showallreport1")
    public String showallreport1(Model m, int page, int tasktype, String taskCcode,
                                 String reportSstate, String time1, String time2,
                                 HttpServletRequest request, String operationstate, Long siteid, Integer searchtype) {
        if (page <= 0) {
            page = 1;
        }
        page = PageBean.countCurrentPage(page);
        List<Taskreportinfo> taskreportinfos = null;
        long totalpage = 0;
        int pagesize = 15;
        Admininfo admininfo = (Admininfo) request.getSession().getAttribute("userInfo");

        List ids = new ArrayList();
        //查询出登录用户所有的厂区
        if (admininfo.getSiteid() == null) {
            if (siteid == null) {
                //查询所有厂区
                List<Siteareainfo> sites = siteService.queryAllSite();
                for (Siteareainfo siteareainfo : sites) {
                    ids.add(siteareainfo.getId());
                }
            } else {
                ids.add(siteid);
            }
        } else {
            ids.add(admininfo.getSiteid());
        }
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("page", (page - 1) * pagesize);
        map.put("pagesize", pagesize);
        if (taskCcode == null) {//任务号
            taskCcode = "";
        }
        map.put("taskcode", taskCcode);
        map.put("reportstate", reportSstate);
        map.put("time1", time1);//开始时间
        map.put("time2", time2);//结束时间
        map.put("tasktype", tasktype);//任务类型
        map.put("ids", ids);//厂区
        if ((taskCcode == null || "".equals(taskCcode)) &&
                (reportSstate == null || "".equals(reportSstate)) &&
                (time1 == null || "".equals(time1)) &&
                (time2 == null || "".equals(time2)) && (operationstate == null || "".equals(operationstate))) {
            taskreportinfos = taskreportService.findByPageReport(map);
            int rows1 = taskreportService.countReport(map);
            totalpage = PageBean.counTotalPage(pagesize, rows1);
            reportSstate = "";
        } else {
            List<Integer> stateList = new ArrayList<>();
//            int state = 2;
            if (operationstate.equals("5")) {
//                state = 3;
                stateList.add(3);
                map.put("stopstate", 1);
                map.put("operationstate", null);
            } else {
                if (operationstate.isEmpty()) {
//                    stateList.add(0);
                    stateList.add(3);
                    stateList.add(2);
                } else if (operationstate.equals("4")) {
                    stateList.add(3);
                } else
                    stateList.add(2);
                map.put("stopstate", null);
                map.put("operationstate", operationstate);
            }

            map.put("state", stateList);
            taskreportinfos = taskreportService.findByPageReport2(map);
            int rows2 = taskreportService.countReport2(map);
            totalpage = PageBean.counTotalPage(pagesize, rows2);
        }
        List<Siteareainfo> siteareainfos = siteService.selectByExample2(admininfo.getSiteid());

        pageBean.setList(taskreportinfos);
        pageBean.setTotalPage((int) totalpage);
        pageBean.setCurrentPage(page);
        m.addAttribute("pageBean", pageBean);
        m.addAttribute("type", tasktype);
        m.addAttribute("taskCcode", taskCcode);
        m.addAttribute("siteid", siteid);
        m.addAttribute("searchtype", searchtype);
        m.addAttribute("reportSstate", reportSstate);
        m.addAttribute("time1", time1);
        m.addAttribute("time2", time2);
        m.addAttribute("operationstate", operationstate);
        m.addAttribute("sites", siteareainfos);
        m.addAttribute("roleid", admininfo.getRoleid());
        ShowReportParam param = new ShowReportParam(page, tasktype, taskCcode, reportSstate, time1, time2, operationstate, siteid, searchtype);
        request.getSession().setAttribute("reportParam", param);
        return "showreport1";
    }

    @RequestMapping("/firstPage")
    public String firstPage(HttpSession session, Integer page) {
        ShowReportParam param = (ShowReportParam) session.getAttribute("reportParam");
        String a = "";
        if (param == null) {
            a = "redirect:/showallreport1?page=1&tasktype=0";
        } else {
            if (page == null) {
                param.setPage(1);
            } else {
                param.setPage(page);
            }
            if (param.getSiteid() == null)
                param.setSiteid(Long.parseLong(""));
            if (param.getSearchtype() == null)
                param.setSearchtype(Integer.valueOf(""));
            a = "redirect:/showallreport1?page=" + param.getPage() + "&tasktype=" + param.getTasktype()
                    + "&taskCcode=" + param.getTaskCcode() + "&time1=" + param.getTime1() + "&time2=" + param.getTime2()
                    + "&operationstate=" + param.getOperationstate() + "&siteid=" + param.getSiteid() + "&searchtype=" + param.getSearchtype();
        }
        return a;
    }

    /**
     * 删除指定的任务报告
     *
     * @param id
     * @return
     */
    @RequestMapping("/deltaskreport")
    @RequiresPermissions("del:taskreport")
    @ResponseBody
    public int deltaskreport(Integer id, Long tempid, HttpServletRequest request) {
        int delreoprt = 0;
        int deltemp = 0;
        if (id != 0) {
            delreoprt = taskreportService.deleteByPrimaryKey(Long.valueOf(id));
            deltemp = tasktempinfoMapper.updateTempState2(tempid);
        } else {
            deltemp = tasktempinfoMapper.updateTempState2(tempid);
            delreoprt = 1;
        }
//        HttpSession session=request.getSession();
//        String report=request.getParameter("report");
//        String name="删除报告"+report+"的信息";
//        Admininfo logadmininfo=(Admininfo)session.getAttribute("userInfo");
//        String username=logadmininfo.getUsername();
//        int addlogres=operateLogService.insertSelective(username,name);
        if (deltemp > 0 && delreoprt > 0) {
            return 1;
        } else
            return 0;
    }

    /**
     * 任务报告的详细信息
     *
     * @param id   任务报告编号
     * @param m
     * @param type 任务类型
     * @return
     */
    @RequestMapping("querytaskreportdetail")
    public String querytaskreportdetail(Long id, Model m, int type, int page, Integer type2) throws Exception {
        //得到报告明细
        Taskreportinfo taskreportinfo = taskreportService.selectByPrimaryKey(id);
        //得到前一次报告
        List<Taskreportinfo> taskreportinfos = getTaskCode(taskreportinfo.getTaskcode());
        List<Reportcontent> reportcontentList = new ArrayList<>();
        if (taskreportinfos.size() > 0) {
            reportcontentList = getReportContentByReportId(taskreportinfos.get(0).getId());
        }

        //得到后一次子任务
//        List<Tasktempinfo> tasktempinfos = getTaskTemp(taskreportinfo.getTaskcode());
        //根据任务报告ID得到所有报告内容reportcontent
        ReportcontentExample example = new ReportcontentExample();
        example.createCriteria().andReportidEqualTo(id);
        List<Reportcontent> reportcontents = reportcontentMapper.selectByExample(example);
        //得到波动值
        List<Systemsettinginfo> systems = systemService.selectByExample();
        Double fluctudte = 0.0;
        for (Systemsettinginfo info : systems) {
            m.addAttribute(info.getKeyname(), info.getValue());
            if (info.getKeyname().equals("FLUCTUATE"))
                fluctudte = Double.valueOf(info.getValue());
        }

        for (int k = 0; k < reportcontents.size(); k++) {
            Reportcontent reportcontent = reportcontents.get(k);
            String bodongzhi = "";
            if (reportcontentList.size() > 0 && k < reportcontentList.size()) {
                Reportcontent reportcontent2 = reportcontentList.get(k);
                if (taskreportinfo.getExamstate() != 0) {
                    if (reportcontent.getCheckvalue() != null && !reportcontent.getCheckvalue().isEmpty() && reportcontent2.getCheckvalue() != null && !reportcontent2.getCheckvalue().isEmpty()) {
                        double maxfluctudte = 1 + fluctudte;
                        double minfluctudte = 1 - fluctudte;
                        if (Double.parseDouble(reportcontent.getCheckvalue()) / Double.parseDouble(reportcontent2.getCheckvalue()) > maxfluctudte) {
                            bodongzhi = "/↑↑↑";
                        }
                        if (Double.parseDouble(reportcontent.getCheckvalue()) / Double.parseDouble(reportcontent2.getCheckvalue()) < minfluctudte) {
                            bodongzhi = "/↓↓↓";
                        }
                    }
                } else {
                    if (reportcontent.getNumvalue() != null && !reportcontent.getNumvalue().isEmpty() && reportcontentList.get(k).getCheckvalue() != null && !reportcontentList.get(k).getCheckvalue().isEmpty()) {
                        if (Double.parseDouble(reportcontent.getNumvalue()) / Double.parseDouble(reportcontentList.get(k).getCheckvalue()) > (1 + fluctudte)) {
                            bodongzhi = "/↑↑↑";
                        }
                        if (Double.parseDouble(reportcontent.getNumvalue()) / Double.parseDouble(reportcontentList.get(k).getCheckvalue()) < (1 - fluctudte)) {
                            bodongzhi = "/↓↓↓";
                        }
                    }
                }
            }
            if (taskreportinfo.getExamstate() != 0 && reportcontent.getCheckvalue() != null && !reportcontent.getCheckvalue().isEmpty()) {
                //大于低值 小于高值
                if (!reportcontent.getNormalmin().equals("-") && !reportcontent.getNormalmax().equals("-") &&
                        Double.parseDouble(reportcontent.getNormalmin()) <= Double.parseDouble(reportcontent.getCheckvalue()) &&
                        Double.parseDouble(reportcontent.getCheckvalue()) <= Double.parseDouble(reportcontent.getNormalmax())) {
                    if (taskreportinfo.getReportstate() == 1 && reportcontent.getErrcontent() != null) {
                        reportcontent.setErrcontent(reportcontent.getErrcontent());
                    } else
                        reportcontent.setErrcontent("-" + bodongzhi);
                }
                //大于最低值  小于低值
                else if (!reportcontent.getNormalmin().equals("-")
                        && Double.parseDouble(reportcontent.getCheckvalue()) < Double.parseDouble(reportcontent.getNormalmin())) {
                    if (taskreportinfo.getReportstate() == 1 && reportcontent.getErrcontent() != null) {
                        reportcontent.setErrcontent(reportcontent.getErrcontent());
                    } else reportcontent.setErrcontent("↓" + bodongzhi);
                }
                //大于高值  小于最高值值
                else if (!reportcontent.getNormalmax().equals("-")
                        && Double.parseDouble(reportcontent.getCheckvalue()) > Double.parseDouble(reportcontent.getNormalmax())) {
                    if (taskreportinfo.getReportstate() == 1 && reportcontent.getErrcontent() != null) {
                        reportcontent.setErrcontent(reportcontent.getErrcontent());
                    } else reportcontent.setErrcontent("↑" + bodongzhi);
                }
                //大于最高上限值
                else if (!reportcontent.getUpperwarning().equals("-") &&
                        Double.parseDouble(reportcontent.getCheckvalue()) > Double.parseDouble(reportcontent.getUpperwarning())) {
                    if (taskreportinfo.getReportstate() == 1 && reportcontent.getErrcontent() != null) {
                        reportcontent.setErrcontent(reportcontent.getErrcontent());
                    } else
                        reportcontent.setErrcontent("↑↑" + bodongzhi);
                }
                //小于最低上限值
                else if (!reportcontent.getLowerwarning().equals("-") &&
                        Double.parseDouble(reportcontent.getCheckvalue()) < Double.parseDouble(reportcontent.getLowerwarning())) {
                    if (taskreportinfo.getReportstate() == 1 && reportcontent.getErrcontent() != null) {
                        reportcontent.setErrcontent(reportcontent.getErrcontent());
                    } else
                        reportcontent.setErrcontent("↓↓" + bodongzhi);
                } else {
                    if (taskreportinfo.getReportstate() == 1 && reportcontent.getErrcontent() != null) {
                        reportcontent.setErrcontent(reportcontent.getErrcontent());
                    } else reportcontent.setErrcontent("-" + bodongzhi);
                }
            } else if (taskreportinfo.getExamstate() == 0 && !reportcontent.getNumvalue().isEmpty()) {
                if (taskreportinfo.getReportstate() == 1 && reportcontent.getErrcontent() != null) {
                    reportcontent.setErrcontent(reportcontent.getErrcontent());
                } else reportcontent.setErrcontent("-");
                //大于低值 小于高值
                if (!reportcontent.getNormalmin().equals("-") && !reportcontent.getNormalmax().equals("-") &&
                        Double.parseDouble(reportcontent.getNormalmin()) <= Double.parseDouble(reportcontent.getNumvalue()) &&
                        Double.parseDouble(reportcontent.getNumvalue()) <= Double.parseDouble(reportcontent.getNormalmax())) {
                    if (taskreportinfo.getReportstate() == 1 && reportcontent.getErrcontent() != null) {
                        reportcontent.setErrcontent(reportcontent.getErrcontent());
                    } else reportcontent.setErrcontent("-" + bodongzhi);
                }
                // 小于低值
                if (!reportcontent.getNormalmin().equals("-")
                        && Double.parseDouble(reportcontent.getNumvalue()) < Double.parseDouble(reportcontent.getNormalmin())) {
                    if (taskreportinfo.getReportstate() == 1 && reportcontent.getErrcontent() != null) {
                        reportcontent.setErrcontent(reportcontent.getErrcontent());
                    } else reportcontent.setErrcontent("↓" + bodongzhi);
                }
                //大于高值
                if (!reportcontent.getNormalmax().equals("-")
                        && Double.parseDouble(reportcontent.getNumvalue()) > Double.parseDouble(reportcontent.getNormalmax())) {
                    if (taskreportinfo.getReportstate() == 1 && reportcontent.getErrcontent() != null) {
                        reportcontent.setErrcontent(reportcontent.getErrcontent());
                    } else reportcontent.setErrcontent("↑" + bodongzhi);
                }
                //大于最高上限值
                if (!reportcontent.getUpperwarning().equals("-") &&
                        Double.parseDouble(reportcontent.getNumvalue()) > Double.parseDouble(reportcontent.getUpperwarning())) {
                    if (taskreportinfo.getReportstate() == 1 && reportcontent.getErrcontent() != null) {
                        reportcontent.setErrcontent(reportcontent.getErrcontent());
                    } else reportcontent.setErrcontent("↑↑" + bodongzhi);
                }
                //小于最低上限值
                if (!reportcontent.getLowerwarning().equals("-") &&
                        Double.parseDouble(reportcontent.getNumvalue()) < Double.parseDouble(reportcontent.getLowerwarning())) {
                    if (taskreportinfo.getReportstate() == 1 && reportcontent.getErrcontent() != null) {
                        reportcontent.setErrcontent(reportcontent.getErrcontent());
                    } else reportcontent.setErrcontent("↓↓" + bodongzhi);
                }

            } else {
                if (taskreportinfo.getReportstate() == 1 && reportcontent.getErrcontent() != null) {
                    reportcontent.setErrcontent(reportcontent.getErrcontent());
                } else reportcontent.setErrcontent("-");
            }
        }
        m.addAttribute("reportinfos", reportcontents);
        m.addAttribute("type", type);
        m.addAttribute("taskreportid", id);
        m.addAttribute("taskcode", taskreportinfo.getTaskcode());
        Date now = new Date();
        if (taskreportinfos.size() > 0) {
            m.addAttribute("taskreport", taskreportinfos.get(0));
        } else {
            m.addAttribute("taskreport", null);
        }
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long diff = now.getTime() - taskreportinfo.getDonetime().getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        long hours = day * 24 + hour;
        if (hours <= 36) {
            if (taskreportinfo.getExamstate() == 0)
                m.addAttribute("examtime", 0);
            else
                m.addAttribute("examtime", 1);
        }
//        if (tasktempinfos.size() > 0) {
//            //如果当前时间在下一次子任务执行之前，有复核权限的用户可以对报告进行复核
//            if (now.getTime() <= tasktempinfos.get(0).getExecutetime().getTime())
//                m.addAttribute("examtime", 0);
//            else
//                m.addAttribute("examtime", 1);
//        } else{
//            if(taskreportinfo.getExamtime()==null&&taskreportinfo.getExamstate()==0)
//                m.addAttribute("examtime", 0);
//            else
//                m.addAttribute("examtime", 1);
//        }
        List<Reportsetting> reportsettings = systemService.showReportSetting();
        for (Reportsetting reportsetting : reportsettings) {
            m.addAttribute(reportsetting.getName(), reportsetting.getIsshow());
        }
        m.addAttribute("taskreportinfo", taskreportinfo);
        m.addAttribute("page", page);
        m.addAttribute("type2", type2);
        return "report3";
    }

    public List<Taskreportinfo> getTaskCode(String taskCode) throws Exception {
        String code = taskCode.substring(0, taskCode.lastIndexOf("-"));
        //得到当前子任务是第几次执行
        Integer i = Integer.valueOf(taskCode.substring(taskCode.lastIndexOf("-") + 1));
        String oldTaskCode = "";
        if (i != 1) {
            oldTaskCode = code + "-" + (i - 1);
        } else {
            //如果当前子任务是第一次执行，获取到昨天当前任务最后一次执行的任务报告
            int index = taskCode.indexOf("-");
//            //根据第一个点的位置 获得第二个点的位置
            index = taskCode.indexOf("-", index + 1);
            int taskNameIndex = taskCode.indexOf("-");
            String taskName = taskCode.substring(0, taskNameIndex);
            String time = taskCode.substring(index + 1, taskCode.lastIndexOf("-"));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Date date = sdf.parse(time);
            Calendar now = Calendar.getInstance();
            now.setTime(date);
            now.set(Calendar.DATE, now.get(Calendar.DATE) - 1);
            String time2 = sdf.format(now.getTime());
            TaskreportinfoExample example = new TaskreportinfoExample();
            example.createCriteria().andTaskcode2Like(taskName + "%" + time2 + "%");
            List<Taskreportinfo> taskreportinfos = taskreportService.selectByExample(example);
            Integer k = null;
            String taskversion = "";
            if (taskreportinfos.size() > 0) {
                String newTaskCode = taskreportinfos.get(0).getTemp().getTaskcode();
                int aa = newTaskCode.indexOf("-");
                int bb = newTaskCode.indexOf("-", aa + 1);
                taskversion = newTaskCode.substring(aa + 1, bb);//昨天的任务版本
                List<Integer> counts = new ArrayList<>();
                for (Taskreportinfo taskreportinfo : taskreportinfos) {
                    String taskcode2 = taskreportinfo.getTemp().getTaskcode();
                    Integer j = Integer.valueOf(taskcode2.substring(taskcode2.lastIndexOf("-") + 1));
                    counts.add(j);
                }
                k = Collections.max(counts);
            }
            if (k == null)
                oldTaskCode = null;
            else
                oldTaskCode = taskName + "-" + taskversion + "-" + time2 + "-" + k;//昨天最后一个子任务
        }
        List<Taskreportinfo> taskreportinfos = new ArrayList<>();
        if (oldTaskCode != null) {
            TaskreportinfoExample taskreportinfoExample = new TaskreportinfoExample();
            taskreportinfoExample.createCriteria().andTaskcode2EqualTo(oldTaskCode);
            taskreportinfos = taskreportService.selectByExample(taskreportinfoExample);
        }
        return taskreportinfos;
    }

    public List<Tasktempinfo> getTaskTemp(String taskCode) throws Exception {
        String code = taskCode.substring(0, taskCode.lastIndexOf("-"));
        Integer i = Integer.valueOf(taskCode.substring(taskCode.lastIndexOf("-") + 1));
        List<Tasktempinfo> tasktempinfoList = new ArrayList<>();
        if (!code.isEmpty()) {
            TasktempinfoExample example = new TasktempinfoExample();
            example.createCriteria().andTaskcodeLike("%" + code + "%").andState2EqualTo(2);
            List<Tasktempinfo> tasktempinfos = tasktempinfoMapper.selectByExample(example);
            List<Integer> counts = new ArrayList<>();
            for (Tasktempinfo tasktempinfo : tasktempinfos) {
                String taskcode2 = tasktempinfo.getTaskcode();
                Integer j = Integer.valueOf(taskcode2.substring(taskcode2.lastIndexOf("-") + 1));
                counts.add(j);
            }
            Integer k = Collections.max(counts);
            String lastCode = "";
            if (i == k) {
                //taskCode  ZZ01-6-20170801-1
                int index = taskCode.indexOf("-");
//            //根据第一个点的位置 获得第二个点的位置
                index = taskCode.indexOf("-", index + 1);
                String taskversion = taskCode.substring(0, index);
                String time = taskCode.substring(index + 1, taskCode.lastIndexOf("-"));
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                Date date = sdf.parse(time);
                Calendar now = Calendar.getInstance();
                now.setTime(date);
                now.set(Calendar.DATE, now.get(Calendar.DATE) + 1);
                String time2 = sdf.format(now.getTime());
                lastCode = taskversion + "-" + time2;
            } else
                lastCode = code + "-" + (i + 1);

            TasktempinfoExample tasktempinfoExample = new TasktempinfoExample();
            tasktempinfoExample.createCriteria().andTaskcodeEqualTo(lastCode).andState2EqualTo(2);
            tasktempinfoList = tasktempinfoMapper.selectByExample(tasktempinfoExample);
        }
        return tasktempinfoList;
    }

    /**
     * 得到前次复核列
     *
     * @param taskreportid 报告ID
     * @return
     */
    @RequestMapping(value = "getReportContentByReportId", method = RequestMethod.POST)
    @ResponseBody
    public List<Reportcontent> getReportContentByReportId(Long taskreportid) {
        List<Reportcontent> reportcontentList = new ArrayList<>();
        if (taskreportid != null) {
            ReportcontentExample example = new ReportcontentExample();
            example.createCriteria().andReportidEqualTo(taskreportid);
            reportcontentList = reportcontentMapper.selectByExample(example);
        }
        return reportcontentList;
    }

    //报告审核
    @RequestMapping(value = "updReportContent", method = RequestMethod.POST)
    @ResponseBody
    public int updReportContent(String reportData, Long reportId, String examuser, String taskcode) {
        return taskreportService.updReportContent(reportData, reportId, examuser, taskcode);
    }
//    @RequestMapping("querytaskreportdetail")
//    public String querytaskreportdetail(Long id, Model m, int type) {
//
//        Taskreportinfo taskreportinfo = taskreportService.selectByPrimaryKey(id);
//        List<List<String>> resList = new ArrayList<>();
//        List<String> childList = new ArrayList<>();
//        List<String> taskcode = new ArrayList<>();
//        childList.add("区域");
//        childList.add("设备");
//        childList.add("巡检项");
//        childList.add("巡检项类型");
//        childList.add("执行时间");
//        childList.add("低值");
//        childList.add("高值");
//        childList.add("告警下限");
//        childList.add("告警上限");
//        childList.add("数值");
//        childList.add("异常描述");
//        childList.add("数据名称");
//        childList.add("单位");
//        resList.add(childList);
//        String content = "{" + "\"res\":" + taskreportinfo.getContent() + "}";
//        TaskReportRes res = JsonUtil.toObject(content, TaskReportRes.class);
//        for (TaskReportContent item : res.getRes()) {
//            childList = new ArrayList<>();
//            String area = item.getAreaname();
//            String equipment = item.getEquipname();
//            String checkname = item.getCheckname();
//            String checkitype = item.getChecktype();
////            String executetime=item.getOperationtime();
//
//            String normalmin= "";
//            String normalmax =  "";
//            String lowerwarning = "";
//            String upperwarning ="";
//
//            childList.add(area);
//            childList.add(equipment);
//            childList.add(checkname);
//            childList.add(checkitype);
//            childList.add(checkitype);
//            if (item.getChecktype().equals("状态项")) {
//                normalmin = "-";
//                normalmax = "-";
//                lowerwarning = "-";
//                upperwarning = "-";
//                childList.add(normalmin);
//                childList.add(normalmax);
//                childList.add(lowerwarning);
//                childList.add(upperwarning);
//                if("0".equals(item.getReportstate())){
//                    childList.add("正常");
//                    childList.add("-");
//                }else {
//                    childList.add("<span style='background:red;color:white;display:inline-block;padding:5 10;'>"+"异常"+"</span>");
//                    childList.add("<a href='javascript:void(0);' onclick=showimg('" + JsonUtil.toJSON(item.getImg()) + "','" + JsonUtil.toJSON(item.getAudio()) + "','" + JsonUtil.toJSON(item.getVideo()) + "')>" + item.getErrcontent() + "</a>");
//                }
//            } else {
//                normalmin = item.getNormalmin()+"";
//                normalmax = item.getNormalmax()+"";
//                lowerwarning = item.getLowerwarning()+"";
//                upperwarning = item.getUpperwarning()+"";
//                childList.add(normalmin);
//                childList.add(normalmax);
//                childList.add(lowerwarning);
//                childList.add(upperwarning);
//                if (item.getNumvalue() != "") {
//                    if(item.getLowerwarning()!=0&&item.getUpperwarning()!=0){
//                        if (Double.parseDouble(item.getNumvalue()) > item.getUpperwarning()) {
//                            childList.add("<span style='color:red;'>" + item.getNumvalue() +  "</span>");
//                            childList.add("<span style='color:red;'>" + "↑↑" + "</span>");
//                        }
//                        if (Double.parseDouble(item.getNumvalue()) < item.getLowerwarning()) {
//                            childList.add("<span style='color:red;'>" + item.getNumvalue() + "</span>");
//                            childList.add("<span style='color:red;'>" +  "↓↓" + "</span>");
//                        }
//                        if (item.getLowerwarning() <= Double.parseDouble(item.getNumvalue()) &&
//                                Double.parseDouble(item.getNumvalue()) <= item.getUpperwarning()) {
//                            childList.add(item.getNumvalue());
//                            childList.add("-");
//                        }
//                    }else{
//                        if (Double.parseDouble(item.getNumvalue()) > item.getNormalmax()) {
//                            childList.add("<span style='color:red;'>" + item.getNumvalue()  + "</span>");
//                            childList.add("<span style='color:red;'>" +  "↑↑" + "</span>");
//                        }
//                        if (Double.parseDouble(item.getNumvalue()) < item.getNormalmin()) {
//                            childList.add("<span style='color:red;'>" + item.getNumvalue()  + "</span>");
//                            childList.add("<span style='color:red;'>" +"↓↓" + "</span>");
//                        }
//                        if (item.getNormalmin() <= Double.parseDouble(item.getNumvalue()) &&
//                                Double.parseDouble(item.getNumvalue()) <= item.getNormalmax()) {
//                            childList.add(item.getNumvalue());
//                            childList.add("-");
//                        }
//                    }
//                } else {
//                    childList.add("-");
//                    childList.add("-");
//                }
//            }
//            String recordname=item.getRecordname();
//            String unitname=item.getUnitname();
//            childList.add(recordname);
//            childList.add(unitname);
//            resList.add(childList);
//        }
//        m.addAttribute("reportinfos", resList);
//        m.addAttribute("type",type);
//        m.addAttribute("taskreportid",id);
//        m.addAttribute("taskcode",taskreportinfo.getTaskcode());
//        return "report3";
//    }

    /**
     * 任务报告汇总
     *
     * @param taskid 任务编号
     * @param m
     * @param type   任务类型
     * @return
     */
    @RequestMapping("reportcount2")
    public String reportcount2(int taskid, Model m, int type, String donetime, String taskname, int page) throws Exception {
//       Date date=new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK).parse(donetime);
//       SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//       String donetime2=sdf.format(date);
        List<Taskreportinfo> reportinfos = taskreportService.queryByTaskidAndDonetime2(type, taskid, donetime);
        List<Doublereportsetting> doublereportsettings = systemService.showDoubleReportSetting();
        int min = 0;
        int max = 0;
        int lowmin = 0;
        int uppermax = 0;
        int areaisshow = 0;
        int equipisshow = 0;
        int checkisshow = 0;

        List<Reportcontent> reportcontents = new ArrayList<>();
        List<List<Reportcontent>> lists = new ArrayList<>();

        for (Taskreportinfo info : reportinfos) {
//            if(info.getId()!=null){
            ReportcontentExample example = new ReportcontentExample();
            example.createCriteria().andReportidEqualTo(info.getId());
            reportcontents = reportcontentMapper.selectByExample(example);
            System.out.print("test" + info.getTaskcode() + "-----" + reportcontents.size());
            lists.add(reportcontents);
//            }

        }
//        testQF01-28-20171003-7-----127   6
        for (Doublereportsetting reportsetting : doublereportsettings) {
            if (reportsetting.getName().equals("areaname"))
                areaisshow = reportsetting.getIsshow();
            if (reportsetting.getName().equals("equipname"))
                equipisshow = reportsetting.getIsshow();
            if (reportsetting.getName().equals("checkname"))
                checkisshow = reportsetting.getIsshow();
            if (reportsetting.getName().equals("normalmin"))
                min = reportsetting.getIsshow();
            if (reportsetting.getName().equals("normalmax"))
                max = reportsetting.getIsshow();
            if (reportsetting.getName().equals("lowerwarning"))
                lowmin = reportsetting.getIsshow();
            if (reportsetting.getName().equals("upperwarning"))
                uppermax = reportsetting.getIsshow();
            m.addAttribute(reportsetting.getName(), reportsetting.getIsshow());
        }
        int count = 0;
        if (min == 1) count++;
        if (max == 1) count++;
        if (lowmin == 1) count++;
        if (uppermax == 1) count++;
        if (areaisshow == 1) count++;
        if (equipisshow == 1) count++;
        if (checkisshow == 1) count++;
        m.addAttribute("count", count);
        List<List<String>> resList = new ArrayList<>();
        List<String> childList = new ArrayList<>();
        List<String> taskcode = new ArrayList<>();
        if (areaisshow == 1)
            childList.add("区域");
        if (equipisshow == 1)
            childList.add("设备");
        if (checkisshow == 1)
            childList.add("巡检项");
        if (min == 1)
            childList.add("低值");
        if (max == 1)
            childList.add("高值");
        if (lowmin == 1)
            childList.add("告警下限");
        if (uppermax == 1)
            childList.add("告警上限");

        taskcode.add(taskname);
        if (reportinfos.size() > 0) {
            for (Taskreportinfo info : reportinfos) {
                childList.add("报告值");
                childList.add("异常描述");
                taskcode.add(info.getTaskcode());
            }
            resList.add(taskcode);
            resList.add(childList);

//            String content = "{" + "\"res\":" + reportinfos.get(0).getContent() + "}";
//            TaskReportRes res = JsonUtil.toObject(content, TaskReportRes.class);
//            ReportcontentExample example = new ReportcontentExample();
//            example.createCriteria().andReportidEqualTo(reportinfos.get(0).getId());
//            reportcontents = reportcontentMapper.selectByExample(example);
            for (Reportcontent item : lists.get(0)) {
                childList = new ArrayList<>();
                String area = item.getAreaname();
                String equipment = item.getEquipname();
                String checkname = item.getCheckname();
                String checkitype = item.getChecktype();
                String normalmin = item.getNormalmin() + "";
                String normalmax = item.getNormalmax() + "";
                String lowerwarning = item.getLowerwarning() + "";
                String upperwarning = item.getUpperwarning() + "";
                if (checkitype.equals("状态项")) {
                    normalmin = "-";
                    normalmax = "-";
                    lowerwarning = "-";
                    upperwarning = "-";
                }
                if (areaisshow == 1) childList.add(area);
                if (equipisshow == 1) childList.add(equipment);
                if (checkisshow == 1) childList.add(checkname);
                if (min == 1)
                    childList.add(normalmin);
                if (max == 1)
                    childList.add(normalmax);
                if (lowmin == 1)
                    childList.add(lowerwarning);
                if (uppermax == 1)
                    childList.add(upperwarning);
                for (int i = 0; i < lists.size(); i++) {
                    boolean hasValue = false;
                    List<Reportcontent> reportcontentList = lists.get(i);
                    List<Reportcontent> reportcontentList1 = new ArrayList<>();
                    if (i < lists.size() - 1)
                        reportcontentList1 = lists.get(i + 1);
                    for (int i1 = 0; i1 < reportcontentList.size(); i1++) {
                        Reportcontent childitem = reportcontentList.get(i1);
                        Reportcontent childitem1 = null;
                        if (reportcontentList1.size() > 0 && i1 < reportcontentList.size() - 1)
                            childitem1 = reportcontentList1.get(i1);
                        String bodongzhi = "";
                        if (childitem.getAreaname().equals(area) && childitem.getEquipname().equals(equipment) && childitem.getCheckname().equals(checkname)) {
                            hasValue = true;
                            switch (childitem.getChecktype()) {
                                case "状态项":
                                    if ("0".equals(childitem.getReportstate())) {
                                        childList.add("正常");
                                        childList.add("-");
                                    } else {
                                        childList.add("<span style='background:red;color:white;display:inline-block;padding:5 10;'>" + "异常" + "</span>");
                                        childList.add("<a href='javascript:void(0);' onclick=showimg(" + childitem.getImg() + "," + childitem.getAudio() + "," + childitem.getVideo() + ")>" + childitem.getErrcontent() + "</a>");
                                        System.out.print("aaaaaaaaaaaaaaaa" + childitem.getImg());
                                    }
                                    break;
                                case "枚举项":
                                    if (childitem.getEnumitem() != null || !childitem.getEnumitem().equals("")) {
                                        childList.add(childitem.getEnumitem());
                                        childList.add("-");
                                    } else {
                                        childList.add("-");
                                        childList.add("-");
                                    }
                                    break;
                                case "记录项":
                                    double maxfluctudte = 1 + 0.2;
                                    double minfluctudte = 1 - 0.2;
                                    if (childitem.getCheckvalue() != null && childitem1 != null && childitem1.getCheckvalue() != null) {
                                        if (childitem.getCheckvalue() != null && !childitem.getCheckvalue().isEmpty() && childitem1.getCheckvalue() != null && !childitem1.getCheckvalue().isEmpty()) {
                                            if (Double.parseDouble(childitem.getCheckvalue()) / Double.parseDouble(childitem1.getCheckvalue()) > maxfluctudte) {
                                                bodongzhi = "<span style='color:blue;'>" + "&nbsp;&nbsp;&nbsp;" + "↑↑↑" + "</span>";
                                            }
                                            if (Double.parseDouble(childitem.getCheckvalue()) / Double.parseDouble(childitem1.getCheckvalue()) < minfluctudte) {
                                                bodongzhi = "<span style='color:blue;'>" + "&nbsp;&nbsp;&nbsp;" + "↓↓↓" + "</span>";
                                            }
                                        }
                                    } else if (!childitem.getNumvalue().isEmpty() && childitem1 != null && childitem1.getCheckvalue() != null && !childitem1.getCheckvalue().isEmpty()) {
                                        if (Double.parseDouble(childitem.getNumvalue()) / Double.parseDouble(childitem1.getCheckvalue()) > maxfluctudte) {
                                            bodongzhi = "<span style='color:blue;'>" + "&nbsp;&nbsp;&nbsp;" + "↑↑↑" + "</span>";
                                        }
                                        if (Double.parseDouble(childitem.getNumvalue()) / Double.parseDouble(childitem1.getCheckvalue()) < minfluctudte) {
                                            bodongzhi = "<span style='color:blue;'>" + "&nbsp;&nbsp;&nbsp;" + "↓↓↓" + "</span>";
                                        }
                                    }
                                    if (childitem.getCheckvalue() != null && !childitem.getCheckvalue().isEmpty()) {
                                        //大于低值 小于高值
                                        if (!childitem.getNormalmin().equals("-") && !childitem.getNormalmax().equals("-") &&
                                                Double.parseDouble(childitem.getNormalmin()) <= Double.parseDouble(childitem.getCheckvalue()) &&
                                                Double.parseDouble(childitem.getCheckvalue()) <= Double.parseDouble(childitem.getNormalmax())) {
                                            childList.add("<span>" + childitem.getCheckvalue() + "</span>");
                                            if (!bodongzhi.isEmpty())
                                                childList.add("<span style='color:red;'>" + bodongzhi + "</span>");
                                            else
                                                childList.add("-");
                                        }
                                        //大于最低值  小于低值
                                        else if (!childitem.getNormalmin().equals("-")
                                                && Double.parseDouble(childitem.getCheckvalue()) < Double.parseDouble(childitem.getNormalmin())) {
                                            childList.add("<span>" + childitem.getCheckvalue() + "</span>");
                                            childList.add("<span style='color:red;'>" + "↓" + bodongzhi + "</span>");
                                        }//大于高值  小于最高值值
                                        else if (!childitem.getNormalmax().equals("-")
                                                && Double.parseDouble(childitem.getCheckvalue()) > Double.parseDouble(childitem.getNormalmax())) {
                                            childList.add("<span>" + childitem.getCheckvalue() + "</span>");
                                            childList.add("<span style='color:red;'>" + "↑" + bodongzhi + "</span>");
                                        } //大于最高上限值
                                        else if (!childitem.getUpperwarning().equals("-") &&
                                                Double.parseDouble(childitem.getCheckvalue()) > Double.parseDouble(childitem.getUpperwarning())) {
                                            childList.add("<span>" + childitem.getCheckvalue() + "</span>");
                                            childList.add("<span style='color:red;'>" + "↑↑" + bodongzhi + "</span>");
                                        }//小于最低上限值
                                        else if (!childitem.getLowerwarning().equals("-") &&
                                                Double.parseDouble(childitem.getCheckvalue()) < Double.parseDouble(childitem.getLowerwarning())) {
                                            childList.add("<span>" + childitem.getCheckvalue() + "</span>");
                                            childList.add("<span style='color:red;'>" + "↓↓" + bodongzhi + "</span>");
                                        } else {
                                            childList.add("<span>" + childitem.getCheckvalue() + "</span>");
                                            childList.add("<span style='color:red;'>" + "-" + bodongzhi + "</span>");
                                        }
                                    } else if (!childitem.getNumvalue().equals("")) {
                                        //大于低值 小于高值
                                        if (!childitem.getNormalmin().equals("-") && !childitem.getNormalmin().equals("-") && !childitem.getNormalmax().equals("-") &&
                                                Double.parseDouble(childitem.getNormalmin()) <= Double.parseDouble(childitem.getNumvalue()) &&
                                                Double.parseDouble(childitem.getNumvalue()) <= Double.parseDouble(childitem.getNormalmax())) {
                                            childList.add("<span>" + childitem.getNumvalue() + "</span>");
                                            childList.add("-");
                                        }//大于最低值  小于低值
                                        else if (!childitem.getNormalmin().equals("-")
                                                && Double.parseDouble(childitem.getNumvalue()) < Double.parseDouble(childitem.getNormalmin())) {
                                            childList.add("<span style='color:red;'>" + childitem.getNumvalue() + "</span>");
                                            childList.add("<span style='color:red;'>" + "↓" + bodongzhi + "</span>");
                                        }
                                        //大于高值  小于最高值值
                                        else if (!childitem.getNormalmax().equals("-")
                                                && Double.parseDouble(childitem.getNumvalue()) > Double.parseDouble(childitem.getNormalmax())) {
                                            childList.add("<span style='color:red;'>" + childitem.getNumvalue() + "</span>");
                                            childList.add("<span style='color:red;'>" + "↑" + bodongzhi + "</span>");
                                        }  //大于最高上限值
                                        else if (!childitem.getUpperwarning().equals("-") &&
                                                Double.parseDouble(childitem.getNumvalue()) > Double.parseDouble(childitem.getUpperwarning())) {
                                            childList.add("<span style='color:red;'>" + childitem.getNumvalue() + "</span>");
                                            childList.add("<span style='color:red;'>" + "↑↑" + bodongzhi + "</span>");
                                        }
                                        //小于最低上限值
                                        else if (!childitem.getLowerwarning().equals("-") &&
                                                Double.parseDouble(childitem.getNumvalue()) < Double.parseDouble(childitem.getLowerwarning())) {
                                            childList.add("<span style='color:red;'>" + childitem.getNumvalue() + "</span>");
                                            childList.add("<span style='color:red;'>" + "↓↓" + bodongzhi + "</span>");
                                        } else {
                                            childList.add("<span style='color:red;'>" + childitem.getNumvalue() + "</span>");
                                            childList.add("-");
                                        }
                                    } else {
                                        childList.add("-");
                                        childList.add("-");
                                    }
                                    break;
                            }
                            break;
                        }
                    }
                    if (!hasValue) {
                        childList.add("-");
                        childList.add("-");
                    }
                }
                resList.add(childList);
            }
        }

        m.addAttribute("reportinfos", resList);
        m.addAttribute("donetime", donetime);
        m.addAttribute("type", type);
        m.addAttribute("taskid", taskid);
        m.addAttribute("taskname", taskname);
        m.addAttribute("taskcode", taskcode);
        m.addAttribute("page", page);
        return "bb";
    }

    @RequestMapping("reportcount")
    public String reportcount(int taskid, Model m, int page, int type, String operationtime, String reportstate, String taskname) throws Exception {
//       Date date=new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK).parse(donetime);
//       SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//       String donetime2=sdf.format(date);
        if (page <= 0) {
            page = 1;
        }
        int size = 15;
        PageInfo<Reportcontent> reportinfos = taskreportService.queryByTaskidAndDonetime(page, size, taskid, operationtime, reportstate);

        m.addAttribute("reportinfos", reportinfos);
        m.addAttribute("donetime", operationtime);
//       m.addAttribute("donetime2",donetime2);
        m.addAttribute("type", type);
        m.addAttribute("taskid", taskid);
        m.addAttribute("taskname", taskname);
//        if(type2==0){
//            return "reportcount";
//        }else
        return "reportcount2";

    }

    /* @RequestMapping("findbyreportstate")
      public String findbyreportstate(int taskid,String reportstate,String donetime,Model m){
         HashMap<String,Object> map=new HashMap<String,Object>();
         map.put("taskid",taskid);
         map.put("reportstate",reportstate);
         map.put("donetime",donetime);
         List<Taskreportinfo> reportinfos=taskreportService.queryByState(map);
         m.addAttribute("reportinfos",reportinfos);
         m.addAttribute("donetime",donetime);
         m.addAttribute("taskid",taskid);
         return "taskcount";
     }*/
    @RequestMapping("gettaskreportlog")
    @RequiresPermissions("item:taskreportlog")
    public String gettaskreportlog(int page, Model m, String taskname, String time, HttpServletRequest request) {
        if (page <= 0) {
            page = 1;
        }
        int size = 15;
        Admininfo admininfo = (Admininfo) request.getSession().getAttribute("userInfo");
        HashMap<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("pagesize", size);
        map.put("taskname", taskname == null ? "" : "%" + taskname + "%");
        map.put("time", time);
        map.put("siteid", admininfo.getSiteid());
        PageInfo<Taskreportinfo> taskreportinfoPageInfo = taskreportService.gettaskreportlog2(map);
        m.addAttribute("allreport", taskreportinfoPageInfo);
        return "showallreportlog";
    }

    /**
     * 删除操作日志
     *
     * @param id
     * @return
     */
    @RequestMapping("deltasklog")
    @ResponseBody
    public int deltasklog(Long id) {
        return taskreportService.delTaskLog(id);
    }

    @RequestMapping("getreportlog")
    @ResponseBody
    public Taskreportinfo getreportlog(Long id) {
        Taskreportinfo taskreportinfo = taskreportService.selectByPrimaryKey(id);
        String logcat = taskreportinfo.getLogcat();
        if (!logcat.contains("\"")) {
            String[] strings = logcat.split(",");
            List<String> list = new ArrayList();
            for (String s : strings) {
                System.out.println("String " + s);
                list.add("\"" + s + "\"");
            }
            System.out.println("logcat " + list.toString());
            taskreportinfo.setLogcat(list.toString());
        }
        return taskreportinfo;
    }

    @RequestMapping("queryByTempid")
    @ResponseBody
    public Taskstopinfo queryByTempid(int tempid) throws ParseException {
        Taskstopinfo taskstopinfo = taskstopinfoMapper.queryStopByTempid(tempid);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String tiem = sdf.format(taskstopinfo.getStoptime());
        taskstopinfo.setStoptime2(tiem);
        return taskstopinfo;
    }

    /**
     * 任务完成情况饼图
     *
     * @param m
     * @param worker
     * @param tasktype
     * @param siteid
     * @param time1
     * @param time2
     * @param taskid
     * @return
     */
    @RequestMapping("/getStaticMachineRateByReport")
    @ResponseBody
    public Map<String, Object> getStaticMachineRateByReport(HttpServletRequest request, Model m, String worker, String tasktype, String siteid, String time1, String time2, String taskid) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("worker", "%" + worker + "%");
        map.put("tasktype", tasktype);
        map.put("siteid", siteid);
        map.put("time1", time1);
        map.put("time2", time2);
        map.put("taskid", taskid);
        List<Map<String, Integer>> machines;
        if ((worker == null || "".equals(worker)) &&
                (tasktype == null || "".equals(tasktype)) &&
                (siteid == null || "".equals(siteid)) &&
                (time1 == null || "".equals(time1)) &&
                (time2 == null || "".equals(time2))) {

            machines = taskreportService.getStaticMachineRateByReport();
        } else {
            machines = taskreportService.getMachineRateByParam(map);
        }
        Admininfo admininfo = (Admininfo) request.getSession().getAttribute("userInfo");
        List<Siteareainfo> siteareainfos = siteService.selectByExample2(admininfo.getSiteid());
        List<Siteareainfo> sites = siteService.selectByExample2(admininfo.getSiteid());
        map.put("machines", machines);
        map.put("sites", sites);
        map.put("tasktype", tasktype);
        map.put("worker", worker);
        map.put("siteid", siteid);
        map.put("time1", time1);
        map.put("time2", time2);
        map.put("taskid", taskid);
        return map;
    }

    /**
     * 任务完成情况折线统计
     *
     * @param worker   执行人
     * @param tasktype 任务类型
     * @param siteid   厂区编号
     * @param time1
     * @param time2
     * @param taskid   任务编号
     * @return
     */
    @RequestMapping("/linechart")
    @ResponseBody
    public List<Map<String, Integer>> linechart(String worker, String tasktype, String siteid, String time1, String time2, String taskid) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("worker", "%" + worker + "%");
        map.put("tasktype", tasktype);
        map.put("siteid", siteid);
        map.put("time1", time1);
        map.put("time2", time2);
        map.put("taskid", taskid);
        List<Map<String, Integer>> machines;
        if ((worker == null || "".equals(worker)) &&
                (tasktype == null || "".equals(tasktype)) &&
                (siteid == null || "".equals(siteid)) &&
                (time1 == null || "".equals(time1)) &&
                (time2 == null || "".equals(time2))) {

            machines = taskreportService.lineChart();
        } else {
            machines = taskreportService.lineChartByParam(map);
        }
        return machines;
    }

    /**
     * 任务完成时间折线统计
     *
     * @return
     */
    @RequestMapping("/avgtimelinechart")
    @ResponseBody
    public Result avgtimelinechart(Model m, String worker, String tasktype, String siteid, String time1, String time2, String taskid) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("worker", worker == null || "".equals(worker) ? "" : "%" + worker + "%");
        map.put("tasktype", tasktype);
        map.put("siteid", siteid);
        map.put("time1", time1);
        map.put("time2", time2);
        map.put("taskid", taskid);

        m.addAttribute("tasktype", tasktype);
        m.addAttribute("worker", worker);
        m.addAttribute("siteid", siteid);
        m.addAttribute("time1", time1);
        m.addAttribute("time2", time2);
        m.addAttribute("taskid", taskid);
        return taskreportService.avgTimeLine(map);
    }

    /**
     * 任务完成情况和任务完成时间的数据列表
     *
     * @param page
     * @param m
     * @param worker   执行人
     * @param tasktype 任务类型
     * @param siteid   厂区编号
     * @param time1
     * @param time2
     * @param taskid   任务编号
     * @param type     0任务完成情况数据列表 1 任务完成时间数据列表
     * @return
     */
    @RequestMapping("reportlist")
    public String reportlist(int page, Model m, String worker, String tasktype, String siteid, String time1, String time2, Long taskid, int type, HttpServletRequest request) throws Exception {
        if (page <= 0) {
            page = 1;
        }
        int size;
        if (type == 0) {
            size = 7;
        } else {
            size = 7;
        }
        Admininfo admininfo = (Admininfo) request.getSession().getAttribute("userInfo");
        if (admininfo.getSiteid() != null) {
            siteid = admininfo.getSiteid() + "";
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("size", size);
        map.put("worker", worker == null || "".equals(worker) ? "" : "%" + worker + "%");
        map.put("tasktype", tasktype);
        map.put("siteid", siteid);
        map.put("time1", time1);
        map.put("time2", time2);
        map.put("taskid", taskid);
        PageInfo<Taskreportinfo> reports = null;
        reports = taskreportService.reportList(map);
        List<Siteareainfo> siteareainfos = siteService.selectByExample2(admininfo.getSiteid());
        m.addAttribute("reports", reports);
        m.addAttribute("sites", siteareainfos);
        m.addAttribute("tasktype", tasktype);
        m.addAttribute("worker", worker);
        m.addAttribute("siteid", siteid);
        m.addAttribute("time1", time1);
        m.addAttribute("time2", time2);
        m.addAttribute("taskid", taskid);
        m.addAttribute("type", type);
        if (type == 0) {
            return "showtaskplan2";
        } else
            return "showtaskplan3";
    }

    @RequestMapping("toreportlist")
    public String toreportlist(Model m, int type, HttpServletRequest request) {
        Admininfo admininfo = (Admininfo) request.getSession().getAttribute("userInfo");
        List<Siteareainfo> siteareainfos = siteService.selectByExample2(admininfo.getSiteid());
        m.addAttribute("sites", siteareainfos);
        m.addAttribute("type", type);
        if (type == 0) {
            return "showtaskplan2";
        } else
            return "showtaskplan3";
    }

    /**
     * 根据厂区编号和任务类型查找任务
     *
     * @param siteid
     * @param type
     * @return
     */
    @RequestMapping("taskbysiteandtype")
    @ResponseBody
    public List<Taskplaninfo> taskbysiteandtype(Long siteid, Integer type) {
        return taskPlanService.selectByType(siteid, type);
       /* return taskreportService.getreportbytype(type);*/
    }

    /**
     * 将不同类型的任务报告导出到Excel
     *
     * @param type
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/exportExcel")
    @ResponseBody
    public ModelAndView exportExcel(int taskid, Model m, int type, HttpServletResponse response, String time1, String time2, String taskname, String checkcontent) throws Exception {
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        // 传递中文参数编码
        String name = "任务" + taskname + "汇总" + ".xls";
        response.setHeader("Content-Disposition", "attachment;filename=" + new String(name.getBytes("GBK"), "ISO8859_1"));

        List<Taskreportinfo> reportinfos = taskreportService.queryByTaskidAndDonetime3(type, taskid, time1, time2);
        if (reportinfos.size() <= 0)
            return null;
        List<Reportcontent> reportcontents;
        List<List<Reportcontent>> lists = new ArrayList<>();

        List<List<String>> resList = new ArrayList<>();
        List<String> childList = new ArrayList<>();
        List<String> taskcode = new ArrayList<>();
        childList.add("区域");
        childList.add("设备");
        childList.add("巡检项");
        childList.add("低值");
        childList.add("高值");
        childList.add("告警下限");
        childList.add("告警上限");
        taskcode.add(taskname);
        for (int i = 0; i < reportinfos.size(); i++) {
            Taskreportinfo info = reportinfos.get(i);
            ReportcontentExample example = new ReportcontentExample();
            example.createCriteria().andReportidEqualTo(info.getId());
            reportcontents = reportcontentMapper.selectByExample(example);
            lists.add(reportcontents);

            //添加表格里的表头
            childList.add("报告值");
            childList.add("异常描述");
            taskcode.add(info.getTemp().getTaskcode());
        }
        resList.add(taskcode);
        resList.add(childList);

//        String content = "{" + "\"res\":" + reportinfos.get(0).getContent() + "}";
//        TaskReportRes res = JsonUtil.toObject(content, TaskReportRes.class);
        for (Reportcontent item : lists.get(0)) {
            childList = new ArrayList<>();
            String area = item.getAreaname();
            String equipment = item.getEquipname();
            String checkname = item.getCheckname();
            String checkitype = item.getChecktype();
            String normalmin = item.getNormalmin() + "";
            String normalmax = item.getNormalmax() + "";
            String lowerwarning = item.getLowerwarning() + "";
            String upperwarning = item.getUpperwarning() + "";
            if (checkitype.equals("状态项")) {
                normalmin = "-";
                normalmax = "-";
                lowerwarning = "-";
                upperwarning = "-";
            }
            childList.add(area);
            childList.add(equipment);
            childList.add(checkname);
            childList.add(normalmin);
            childList.add(normalmax);
            childList.add(lowerwarning);
            childList.add(upperwarning);

            for (int i = 0; i < lists.size(); i++) {
                boolean hasValue = false;
                List<Reportcontent> reportcontentList = lists.get(i);
                List<Reportcontent> reportcontentList1 = new ArrayList<>();
                if (i < lists.size() - 1)
                    reportcontentList1 = lists.get(i + 1);
                for (int i1 = 0; i1 < reportcontentList.size(); i1++) {
                    if (i1 >= reportcontentList.size()) {
                        break;
                    }
                    Reportcontent childitem = reportcontentList.get(i1);
//                    int a = reportcontentList.size();
//                    int b= reportcontentList1.size();
//                    if(a!=b){
//                        String test="aaaa";
//                    }
                    Reportcontent childitem1 = null;
                    if (reportcontentList1.size() > 0)
                        childitem1 = reportcontentList1.get(i1);
                    String bodongzhi = "";
                    if (childitem.getAreaname().equals(area) && childitem.getEquipname().equals(equipment) && childitem.getCheckname().equals(checkname)) {
                        hasValue = true;
                        if (childitem.getChecktype().equals("状态项")) {
                            if ("0".equals(childitem.getReportstate())) {
                                childList.add("正常");
                                childList.add("-");
                            } else {
                                childList.add("异常");
                                childList.add(childitem.getErrcontent() + "," + JsonUtil.toJSON(childitem.getImg()) + "," + JsonUtil.toJSON(childitem.getAudio()) + "," + JsonUtil.toJSON(childitem.getVideo()));

                                //childList.add("<a href='javascript:void(0);' onclick=showimg('" + JsonUtil.toJSON(childitem.getImg()) + "','" + JsonUtil.toJSON(childitem.getAudio()) + "','" + JsonUtil.toJSON(childitem.getVideo()) + "')>" + childitem.getErrcontent() + "</a>");
                            }
//                            if (!childitem.getErrcontent().equals("")) {
                            // childList.add(childitem.getErrcontent() + "," + JsonUtil.toJSON(childitem.getImg()) + "," + JsonUtil.toJSON(childitem.getAudio()) + "," + JsonUtil.toJSON(childitem.getVideo()));
//                            } else {
//                                childList.add("-" + "," + JsonUtil.toJSON(childitem.getImg()) + "," + JsonUtil.toJSON(childitem.getAudio()) + "," + JsonUtil.toJSON(childitem.getVideo()));
//                            }
                        } else {
                            double maxfluctudte = 1 + 0.2;
                            double minfluctudte = 1 - 0.2;
                            if (childitem.getCheckvalue() != null && childitem1 != null && childitem1.getCheckvalue() != null) {
                                if (childitem.getCheckvalue() != null && !childitem.getCheckvalue().isEmpty() && childitem1.getCheckvalue() != null && !childitem1.getCheckvalue().isEmpty()) {
                                    if (Double.parseDouble(childitem.getCheckvalue()) / Double.parseDouble(childitem1.getCheckvalue()) > maxfluctudte) {
                                        bodongzhi = "    " + "↑↑↑";
                                    }
                                    if (Double.parseDouble(childitem.getCheckvalue()) / Double.parseDouble(childitem1.getCheckvalue()) < minfluctudte) {
                                        bodongzhi = "    " + "↓↓↓";
                                    }
                                }
                            } else if (!childitem.getNumvalue().isEmpty() && childitem1 != null && childitem1.getCheckvalue() != null) {
                                if (Double.parseDouble(childitem.getNumvalue()) / Double.parseDouble(childitem1.getCheckvalue()) > maxfluctudte) {
                                    bodongzhi = "    " + "↑↑↑";
                                }
                                if (Double.parseDouble(childitem.getNumvalue()) / Double.parseDouble(childitem1.getCheckvalue()) < minfluctudte) {
                                    bodongzhi = "    " + "↓↓↓";
                                }
                            }
                            if (childitem.getCheckvalue() != null && !childitem.getCheckvalue().isEmpty()) {
                                //大于低值 小于高值
                                if (!childitem.getNormalmin().equals("-") && !childitem.getNormalmax().equals("-") &&
                                        Double.parseDouble(childitem.getNormalmin()) <= Double.parseDouble(childitem.getCheckvalue()) &&
                                        Double.parseDouble(childitem.getCheckvalue()) <= Double.parseDouble(childitem.getNormalmax())) {
                                    childList.add(childitem.getCheckvalue());
                                    if (!bodongzhi.isEmpty())
                                        childList.add(bodongzhi);
                                    else
                                        childList.add("-");
                                }//大于最高上限值
                                else if (!childitem.getUpperwarning().equals("-") &&
                                        Double.parseDouble(childitem.getCheckvalue()) > Double.parseDouble(childitem.getUpperwarning())) {
                                    childList.add(childitem.getCheckvalue());
                                    childList.add("↑↑" + bodongzhi);
                                }//小于最低上限值
                                else if (!childitem.getLowerwarning().equals("-") &&
                                        Double.parseDouble(childitem.getCheckvalue()) < Double.parseDouble(childitem.getLowerwarning())) {
                                    childList.add(childitem.getCheckvalue());
                                    childList.add("↓↓" + bodongzhi);
                                }//大于最低值  小于低值
                                else if (!childitem.getNormalmin().equals("-") && !childitem.getLowerwarning().equals("-")
                                        && Double.parseDouble(childitem.getCheckvalue()) >= Double.parseDouble(childitem.getLowerwarning())
                                        && Double.parseDouble(childitem.getCheckvalue()) < Double.parseDouble(childitem.getNormalmin())) {
                                    childList.add(childitem.getCheckvalue());
                                    childList.add("↓" + bodongzhi);
                                }//大于高值  小于最高值值
                                else if (!childitem.getNormalmax().equals("-") && !childitem.getUpperwarning().equals("-") &&
                                        Double.parseDouble(childitem.getCheckvalue()) <= Double.parseDouble(childitem.getUpperwarning())
                                        && Double.parseDouble(childitem.getCheckvalue()) > Double.parseDouble(childitem.getNormalmax())) {
                                    childList.add(childitem.getCheckvalue());
                                    childList.add("↑" + bodongzhi);
                                } else {
                                    childList.add(childitem.getCheckvalue());
                                    childList.add("-");
                                }
                            } else if (!childitem.getNumvalue().equals("")) {
                                //大于低值 小于高值
                                if (!childitem.getNormalmin().equals("-") && !childitem.getNormalmin().equals("-") && !childitem.getNormalmax().equals("-") &&
                                        Double.parseDouble(childitem.getNormalmin()) <= Double.parseDouble(childitem.getNumvalue()) &&
                                        Double.parseDouble(childitem.getNumvalue()) <= Double.parseDouble(childitem.getNormalmax())) {
                                    childList.add(childitem.getNumvalue() + bodongzhi);
                                    childList.add("-");
                                }//大于最高上限值
                                else if (!childitem.getUpperwarning().equals("-") &&
                                        Double.parseDouble(childitem.getNumvalue()) > Double.parseDouble(childitem.getUpperwarning())) {
                                    childList.add(childitem.getNumvalue() + bodongzhi);
                                    childList.add("↑↑" + bodongzhi);
                                }//小于最低上限值
                                else if (!childitem.getLowerwarning().equals("-") &&
                                        Double.parseDouble(childitem.getNumvalue()) < Double.parseDouble(childitem.getLowerwarning())) {
                                    childList.add(childitem.getNumvalue());
                                    childList.add("↓↓" + bodongzhi);
                                }//大于最低值  小于低值
                                else if (!childitem.getNormalmin().equals("-") && !childitem.getLowerwarning().equals("-")
                                        && Double.parseDouble(childitem.getNumvalue()) >= Double.parseDouble(childitem.getLowerwarning())
                                        && Double.parseDouble(childitem.getNumvalue()) < Double.parseDouble(childitem.getNormalmin())) {
                                    childList.add(childitem.getNumvalue());
                                    childList.add("↓" + bodongzhi);
                                }//大于高值  小于最高值值
                                else if (!childitem.getNormalmax().equals("-") && !childitem.getUpperwarning().equals("-") &&
                                        Double.parseDouble(childitem.getNumvalue()) <= Double.parseDouble(childitem.getUpperwarning())
                                        && Double.parseDouble(childitem.getNumvalue()) > Double.parseDouble(childitem.getNormalmax())) {
                                    childList.add(childitem.getNumvalue());
                                    childList.add("↑" + bodongzhi);
                                } else {
                                    childList.add(childitem.getNumvalue());
                                    childList.add("-");
                                }
                            } else {
                                childList.add("-");
                                childList.add("-");
                            }
                        }
                        break;
                    }
                }
                if (!hasValue) {
                    childList.add("-");
                }
            }
            resList.add(childList);
        }
        List<String> checkExportContentList = new ArrayList<>();
        String[] stringArr = checkcontent.split(",");
        for (int i = 0; i < stringArr.length; i++) {
            checkExportContentList.add(stringArr[i]);
        }
        Map showMap = getShowColum(checkExportContentList);
//        List<Doublereportsetting> doublereportsettings = systemService.showDoubleReportSetting();
        int count = checkExportContentList.size() - 1;
        // 定义一个工作薄
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet(name);
        // 创建一行
        Row row = sheet.createRow(0);
        CellRangeAddress cra2 = new CellRangeAddress(0, 0, 0, 0);
        for (int i = 0; i < resList.size(); i++) {
            row = sheet.createRow(i);
            List<String> cols = resList.get(i);
            int colIndex = 0;
            for (int j = 0; j < cols.size(); j++) {
                if (i == 0) {
                    if (j == 0) {
                        row.createCell(colIndex).setCellValue(cols.get(0));
                        colIndex += count;
                        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, colIndex));
                        colIndex++;
                    } else {
                        row.createCell(colIndex).setCellValue(resList.get(i).get(j));
                        sheet.addMergedRegion(new CellRangeAddress(0, 0, colIndex, ++colIndex));
                        colIndex++;
                    }
                } else {
                    if (j < 7) {
                        if (showMap.containsKey(j)) {
                            row.createCell(colIndex++).setCellValue(resList.get(i).get(j));
                        }
                    } else {
                        row.createCell(colIndex++).setCellValue(resList.get(i).get(j));
                    }
                }
            }

        }
        OutputStream fOut = response.getOutputStream();
        workbook.write(fOut);
        fOut.flush();
        fOut.close();
        return null;
//        return 1;
    }

    /**
     * 任务报告明细导出excel
     *
     * @param id
     * @param response1
     * @return
     * @throws Exception
     */
    @RequestMapping("aa")
    public ModelAndView aa(Long id, HttpServletResponse response1, String checkcontent) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        response1.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response1.setCharacterEncoding("utf-8");
        //得到报告详情
        Taskreportinfo taskreportinfo = taskreportService.selectByPrimaryKey(id);

        ReportcontentExample reportcontentExample = new ReportcontentExample();
        reportcontentExample.createCriteria().andReportidEqualTo(id);
        List<Reportcontent> reportcontents = reportcontentMapper.selectByExample(reportcontentExample);

        //得到前一次报告
        List<Taskreportinfo> taskreportinfos = getTaskCode(taskreportinfo.getTaskcode());
        List<Reportcontent> reportcontentList = new ArrayList<>();
        if (taskreportinfos.size() > 0) {
            reportcontentList = getReportContentByReportId(taskreportinfos.get(0).getId());
        }
        String name = "";
        if (taskreportinfo.getTasktype() == 0) {
            name = "日常巡检报告_" + taskreportinfo.getTaskcode() + ".xls";
        } else if (taskreportinfo.getTasktype() == 1) {
            name = "计划巡检报告_" + taskreportinfo.getTaskcode() + ".xls";
        } else if (taskreportinfo.getTasktype() == 2) {
            name = "hse隐患排查报告_" + taskreportinfo.getTaskcode() + ".xls";
        } else if (taskreportinfo.getTasktype() == 3) {
            name = "视频巡检报告_" + taskreportinfo.getTaskcode() + ".xls";
        } else {
            name = "即拍即传报告_" + taskreportinfo.getTaskcode() + ".xls";
        }
        // 定义一个工作薄
        Workbook workbook = new HSSFWorkbook();
        /*workbook.setSheetName(0,(short)0); *///这里(short)1是解决中文乱码的关键；而第一个参数是工作表的索引号。
        // 创建一个sheet页
        Sheet sheet = workbook.createSheet(name);
        // 创建一行
        Row row = sheet.createRow(0);
        /*workbook.setSheetName(1, "中文", HSSFWorkbook.ENCODING_UTF_16);*/
        // 在本行赋值 以0开始
        List<String> checkExportContentList = new ArrayList<>();
        String[] stringArr = checkcontent.split(",");
        for (int i = 0; i < stringArr.length; i++) {
            checkExportContentList.add(stringArr[i]);
        }
        for (int i = 0; i < checkExportContentList.size(); i++) {
            row.createCell(i).setCellValue(checkExportContentList.get(i));
        }
        String areaname = "";
        String equipname = "";
        String checkname = "";
        String checktype = "";
        String reportstate = "";
        String errcontent = "";
        List<String> video;
        List<String> audio;
        String fuhe = "";
        row = sheet.createRow(1);
//        String content = "{" + "\"res\":" + taskreportinfo.getContent() + "}";
//        TaskReportRes res = JsonUtil.toObject(content, TaskReportRes.class);
        //得到波动值
        List<Systemsettinginfo> systems = systemService.selectByExample();
        Double fluctudte = 0.0;
        for (Systemsettinginfo info : systems) {
            if (info.getKeyname().equals("FLUCTUATE"))
                fluctudte = Double.valueOf(info.getValue());
        }


//        List<Reportcontent> reportcontents1 = getFirstReportContentByReportId(taskreportinfos.get(0).getId());
        for (Reportcontent reportcontent0 : reportcontents) {
            if (taskreportinfos.size() > 0 && taskreportinfos.get(0).getExamstate() != 0) {
                for (Reportcontent reportcontent1 : reportcontentList) {
                    if (reportcontent0.getAreaname().equals(reportcontent1.getAreaname()) &&
                            reportcontent0.getEquipname().equals(reportcontent1.getEquipname()) &&
                            reportcontent0.getCheckname().equals(reportcontent1.getCheckname()) &&
                            reportcontent0.getChecktype().equals(reportcontent1.getChecktype()))
                        reportcontent0.setFirstValue(reportcontent1.getCheckvalue());
                }
            }
        }
        int j = 1;
        for (int k = 0; k < reportcontents.size(); k++) {
            Reportcontent reportContent = reportcontents.get(k);
            int index = 0;
            row = sheet.createRow(j);
            areaname = reportContent.getAreaname();
            equipname = reportContent.getEquipname();
            checkname = reportContent.getCheckname();
            checktype = reportContent.getChecktype();
            String checkt = "";
            if (reportContent.getReportstate().equals("0")) {
                checkt = "正常";
            } else {
                checkt = "异常";
            }
            reportstate = checkt;
            Integer tasktype = taskreportinfo.getTasktype();
            String typename;
            if (tasktype == 0) {
                typename = "日常巡检";
            } else if (tasktype == 1) {
                typename = "计划巡检";
            } else if (tasktype == 2) {
                typename = "hse隐患排查";
            } else if (tasktype == 3) {
                typename = "视频巡检";
            } else {
                typename = "即拍即传";
            }
            for (int i = 0; i < checkExportContentList.size(); i++) {
                switch (checkExportContentList.get(i)) {
                    case "任务名称":
                        row.createCell(i).setCellValue(taskreportinfo.getTaskcode().substring(0, taskreportinfo.getTaskcode().indexOf("-")));
                        break;
                    case "任务编号":
                        row.createCell(i).setCellValue(taskreportinfo.getTaskcode());
                        break;
                    case "工人":
                        row.createCell(i).setCellValue(taskreportinfo.getWorker());
                        break;
                    case "任务类型":
                        row.createCell(i).setCellValue(typename);
                        break;
                    case "区域":
                        row.createCell(i).setCellValue(areaname);
                        break;
                    case "设备":
                        row.createCell(i).setCellValue(equipname);
                        break;
                    case "巡检项":
                        row.createCell(i).setCellValue(checkname);
                        break;
                    case "巡检项类型":
                        row.createCell(i).setCellValue(checktype);
                        break;
                    case "巡检项状态":
                        if (checktype.equals("记录项")) {
                            row.createCell(i).setCellValue("-");
                        } else {
                            row.createCell(i).setCellValue(reportstate);
                        }
                        break;
                    case "正常最低值":
                        if (checktype.equals("记录项")) {
                            row.createCell(i).setCellValue(reportContent.getNormalmin());
                        } else {
                            row.createCell(i).setCellValue("-");
                        }
                        break;
                    case "正常最高值":
                        if (checktype.equals("记录项")) {
                            row.createCell(i).setCellValue(reportContent.getNormalmax());
                        } else {
                            row.createCell(i).setCellValue("-");
                        }
                        break;
                    case "下限警告值":
                        if (checktype.equals("记录项")) {
                            row.createCell(i).setCellValue(reportContent.getLowerwarning());
                        } else {
                            row.createCell(i).setCellValue("-");
                        }
                        break;
                    case "上限警告值":
                        if (checktype.equals("记录项")) {
                            row.createCell(i).setCellValue(reportContent.getUpperwarning());
                        } else {
                            row.createCell(i).setCellValue("-");
                        }
                        break;
                    case "报告值":
                        if (checktype.equals("记录项")) {
                            if (!reportContent.getNumvalue().isEmpty()) {
                                row.createCell(i).setCellValue(reportContent.getNumvalue());
                            } else
                                row.createCell(i).setCellValue("-");
                        } else {
                            row.createCell(i).setCellValue("-");
                        }
                        break;
                    case "异常描述":
                        if (checktype.equals("记录项")) {
                            Reportcontent reportcontent = reportContent;
                            String bodongzhi = "";
                            if (reportcontentList.size() > 0) {
                                Reportcontent reportcontent2 = reportcontentList.get(k);
                                if (taskreportinfo.getExamstate() != 0) {
                                    if (reportcontent.getCheckvalue() != null && !reportcontent.getCheckvalue().isEmpty() && reportcontent2.getCheckvalue() != null && !reportcontent2.getCheckvalue().isEmpty()) {
                                        double maxfluctudte = 1 + fluctudte;
                                        double minfluctudte = 1 - fluctudte;
                                        if (Double.parseDouble(reportcontent.getCheckvalue()) / Double.parseDouble(reportcontent2.getCheckvalue()) > maxfluctudte) {
                                            bodongzhi = "   ↑↑↑";
                                        }
                                        if (Double.parseDouble(reportcontent.getCheckvalue()) / Double.parseDouble(reportcontent2.getCheckvalue()) < minfluctudte) {
                                            bodongzhi = "   ↓↓↓";
                                        }
                                    }
                                } else {
                                    if (reportcontent.getNumvalue() != null && !reportcontent.getNumvalue().isEmpty() && reportcontentList.get(i).getCheckvalue() != null && !reportcontentList.get(i).getCheckvalue().isEmpty()) {
                                        if (Double.parseDouble(reportcontent.getNumvalue()) / Double.parseDouble(reportcontentList.get(i).getCheckvalue()) > (1 + fluctudte)) {
                                            bodongzhi = "   ↑↑↑";
                                        }
                                        if (Double.parseDouble(reportcontent.getNumvalue()) / Double.parseDouble(reportcontentList.get(i).getCheckvalue()) < (1 - fluctudte)) {
                                            bodongzhi = "   ↓↓↓";
                                        }
                                    }
                                }
                            }
                            row.createCell(i).setCellValue("-" + bodongzhi);
                            if (taskreportinfo.getExamstate() != 0 && reportcontent.getCheckvalue() != null && !reportcontent.getCheckvalue().isEmpty()) {
                                //大于低值 小于高值
                                if (!reportcontent.getNormalmin().equals("-") && !reportcontent.getNormalmax().equals("-") &&
                                        Double.parseDouble(reportcontent.getNormalmin()) <= Double.parseDouble(reportcontent.getCheckvalue()) &&
                                        Double.parseDouble(reportcontent.getCheckvalue()) <= Double.parseDouble(reportcontent.getNormalmax())) {
                                    row.createCell(i).setCellValue("-" + bodongzhi);
                                }
                                //大于最高上限值
                                if (!reportcontent.getUpperwarning().equals("-") &&
                                        Double.parseDouble(reportcontent.getCheckvalue()) > Double.parseDouble(reportcontent.getUpperwarning())) {
                                    row.createCell(i).setCellValue("↑↑" + bodongzhi);
                                }
                                //小于最低上限值
                                if (!reportcontent.getLowerwarning().equals("-") &&
                                        Double.parseDouble(reportcontent.getCheckvalue()) < Double.parseDouble(reportcontent.getLowerwarning())) {
                                    row.createCell(i).setCellValue("↓↓" + bodongzhi);
                                }
                                //大于最低值  小于低值
                                if (!reportcontent.getNormalmin().equals("-") && !reportcontent.getLowerwarning().equals("-")
                                        && Double.parseDouble(reportcontent.getCheckvalue()) >= Double.parseDouble(reportcontent.getLowerwarning())
                                        && Double.parseDouble(reportcontent.getCheckvalue()) < Double.parseDouble(reportcontent.getNormalmin())) {
                                    row.createCell(i).setCellValue("↓" + bodongzhi);
                                }
                                //大于高值  小于最高值值
                                if (!reportcontent.getNormalmax().equals("-") && !reportcontent.getUpperwarning().equals("-") &&
                                        Double.parseDouble(reportcontent.getCheckvalue()) <= Double.parseDouble(reportcontent.getUpperwarning())
                                        && Double.parseDouble(reportcontent.getCheckvalue()) > Double.parseDouble(reportcontent.getNormalmax())) {
                                    row.createCell(i).setCellValue("↑" + bodongzhi);
                                }
                            } else if (taskreportinfo.getExamstate() == 0 && !reportcontent.getNumvalue().isEmpty()) {
                                //大于低值 小于高值
                                if (!reportcontent.getNormalmin().equals("-") && !reportcontent.getNormalmax().equals("-") &&
                                        Double.parseDouble(reportcontent.getNormalmin()) <= Double.parseDouble(reportcontent.getNumvalue()) &&
                                        Double.parseDouble(reportcontent.getNumvalue()) <= Double.parseDouble(reportcontent.getNormalmax())) {
                                    row.createCell(i).setCellValue("-" + bodongzhi);
                                }
                                //大于最高上限值
                                if (!reportcontent.getUpperwarning().equals("-") &&
                                        Double.parseDouble(reportcontent.getNumvalue()) > Double.parseDouble(reportcontent.getUpperwarning())) {
                                    row.createCell(i).setCellValue("↑↑" + bodongzhi);
                                }
                                //小于最低上限值
                                if (!reportcontent.getLowerwarning().equals("-") &&
                                        Double.parseDouble(reportcontent.getNumvalue()) < Double.parseDouble(reportcontent.getLowerwarning())) {
                                    row.createCell(i).setCellValue("↓↓" + bodongzhi);
                                }
                                //大于最低值  小于低值
                                if (!reportcontent.getNormalmin().equals("-") && !reportcontent.getLowerwarning().equals("-")
                                        && Double.parseDouble(reportcontent.getNumvalue()) >= Double.parseDouble(reportcontent.getLowerwarning())
                                        && Double.parseDouble(reportcontent.getNumvalue()) < Double.parseDouble(reportcontent.getNormalmin())) {
                                    row.createCell(i).setCellValue("↓" + bodongzhi);
                                }
                                //大于高值  小于最高值值
                                if (!reportcontent.getNormalmax().equals("-") && !reportcontent.getUpperwarning().equals("-") &&
                                        Double.parseDouble(reportcontent.getNumvalue()) <= Double.parseDouble(reportcontent.getUpperwarning())
                                        && Double.parseDouble(reportcontent.getNumvalue()) > Double.parseDouble(reportcontent.getNormalmax())) {
                                    row.createCell(i).setCellValue("↑" + bodongzhi);
                                }
                            }
                        } else {
                            if (reportContent.getReportstate().equals("1")) {
                                row.createCell(i).setCellValue(reportContent.getErrcontent() + "," + JsonUtil.toJSON(reportContent.getImg()) + "," + JsonUtil.toJSON(reportContent.getAudio()) + "," + JsonUtil.toJSON(reportContent.getVideo()));
                            } else {
                                row.createCell(i).setCellValue(reportstate);
                            }
                        }
                        break;
                    case "数据名称":
                        if (checktype.equals("记录项")) {
                            row.createCell(i).setCellValue(reportContent.getRecordname());
                        } else {
                            row.createCell(i).setCellValue("-");
                        }
                        break;
                    case "单位":
                        if (checktype.equals("记录项")) {
                            row.createCell(i).setCellValue(reportContent.getUnitname());
                        } else {
                            row.createCell(i).setCellValue("-");
                        }
                        break;
                    case "复核列":
                        if (checktype.equals("记录项")) {
                            row.createCell(i).setCellValue(reportContent.getCheckvalue());
                        } else {
                            row.createCell(i).setCellValue("-");
                        }
                        break;
                    case "前次复核值":
                        row.createCell(i).setCellValue(reportContent.getFirstValue());
                        break;
                    case "前次复核值(人工)":
                        row.createCell(i).setCellValue(reportContent.getFirstValue());
                        break;
                    case "前次复核值(自动)":
                        row.createCell(i).setCellValue(reportContent.getFirstValue());
                        break;
                    default:
                        break;
                }
            }
            j++;
        }
        response1.setHeader("Content-Disposition", "attachment;filename=" + new String(name.getBytes("GBK"), "ISO8859_1"));
        OutputStream fOut = response1.getOutputStream();
        workbook.write(fOut);
        fOut.flush();
        fOut.close();
        return null;
    }

    public List<Reportcontent> getFirstReportContentByReportId(Long taskreportid) {
        List<Reportcontent> reportcontentList = new ArrayList<>();
        if (taskreportid != null) {
            ReportcontentExample example = new ReportcontentExample();
            example.createCriteria().andReportidEqualTo(taskreportid);
            reportcontentList = reportcontentMapper.selectByExample(example);
        }
        return reportcontentList;
    }

    /**
     * 设备状态情况列表
     *
     * @param m
     * @param page
     * @param startTime
     * @param checkid
     * @param siteid
     * @param areaid
     * @param equipid
     * @param sitename
     * @param areaname
     * @param equipname
     * @param checkname
     * @return
     * @throws Exception
     */
    @RequestMapping("equipstateinfo")
    public String equipstateinfo(Model m, int page, String startTime, String endTime, String checkid, String siteid, String areaid,
                                 String equipid, String sitename, String areaname, String equipname, String checkname,
                                 Integer needline, HttpServletRequest request) throws Exception {
        if (page < 0) {
            page = 1;
        }
//        if(areaname=="所有区域"){
//            areaname="";
//        }
//        if(checkname=="所有巡检项"){
//            checkname="";
//        }
//        if(equipname=="所有设备"){
//            equipname="";
//        }
        PageInfo<Reportcontent> equipstates = taskreportService.equipSateInfo2(page, 10, checkname, startTime, endTime, sitename, areaname, equipname);
        Admininfo admininfo = (Admininfo) request.getSession().getAttribute("userInfo");
        //查询所有厂区
        List<Siteareainfo> sites = siteService.selectByExample2(admininfo.getSiteid());
        //查询所有区域
        List<Areainfo> areainfos = areaService.selectByExample();
        //查询所有设备
        EquipmentinfoExample equipmentinfoExample = new EquipmentinfoExample();
        List<Equipmentinfo> equipmentinfos = equipmentService.selectByExample(equipmentinfoExample);
        //查询所有巡检项
        List<Checkiteminfo> checkiteminfos = checkInfoService.selectByExample();

        m.addAttribute("equipstates", equipstates);
        m.addAttribute("sites", sites);
        m.addAttribute("areainfos", areainfos);
        m.addAttribute("equipmentinfos", equipmentinfos);
        m.addAttribute("checkiteminfos", checkiteminfos);

        m.addAttribute("startTime", startTime);
        m.addAttribute("endTime", endTime);
        m.addAttribute("siteid", siteid);
        m.addAttribute("areaid", areaid);
        m.addAttribute("equipid", equipid);
        m.addAttribute("checkid", checkid);
        m.addAttribute("checkname", checkname);
        m.addAttribute("needline", needline);
        m.addAttribute("sitename", sitename);
        m.addAttribute("areaname", areaname);
        m.addAttribute("equipname", equipname);

        return "showtaskplan6";
    }

    @RequestMapping("toequipstateinfo")
    public String toequipstateinfo(Model m, HttpServletRequest request) {
        Admininfo admininfo = (Admininfo) request.getSession().getAttribute("userInfo");
        //查询所有厂区
        List<Siteareainfo> sites = siteService.selectByExample2(admininfo.getSiteid());
        //查询所有区域
        List<Areainfo> areainfos = areaService.selectByExample();
        //查询所有设备
        EquipmentinfoExample equipmentinfoExample = new EquipmentinfoExample();
        List<Equipmentinfo> equipmentinfos = equipmentService.selectByExample(equipmentinfoExample);
        //查询所有巡检项
        List<Checkiteminfo> checkiteminfos = checkInfoService.selectByExample();

        m.addAttribute("sites", sites);
        m.addAttribute("areainfos", areainfos);
        m.addAttribute("equipmentinfos", equipmentinfos);
        m.addAttribute("checkiteminfos", checkiteminfos);

        return "showtaskplan6";
    }

    /**
     * 设备状态情况折线统计
     *
     * @param m
     * @param page
     * @param startTime
     * @param siteid
     * @param areaid
     * @param equipid
     * @param sitename
     * @param areaname
     * @param equipname
     * @param checkname
     * @return
     * @throws Exception
     */
    @RequestMapping("equipstatechart")
    @ResponseBody
    public Result equipstatechart(Model m, int page, String startTime, String endTime, String siteid, String areaid,
                                  String equipid, String sitename, String areaname, String equipname,
                                  String checkname) throws Exception {
        if (page <= 0) {
            page = 1;
        }
        WebChartRes2 chartRes2 = null;
        if (startTime != "" && startTime != null && checkname != null && checkname != "") {
            chartRes2 = taskreportService.equipStateChart(page, 7, checkname, startTime, endTime, areaname, equipname);
        } else
            chartRes2 = null;
        m.addAttribute("siteid", siteid);
        m.addAttribute("areaid", areaid);
        m.addAttribute("equipid", equipid);
        m.addAttribute("sitename", sitename);
        m.addAttribute("areaname", areaname);
        m.addAttribute("equipname", equipname);
        m.addAttribute("checkname", checkname);
        m.addAttribute("startTime", startTime);
        m.addAttribute("endTime", endTime);
        return new JSONResult<>(chartRes2);
    }


    @RequestMapping("getchecktask")
    @ResponseBody
    public List<Checkiteminfo> getchecktask(Long equipid) {
        if (equipid == null)
            return checkInfoService.selectByExample();
        List<Long> taskids = new ArrayList<>();
        List<Long> checkids = new ArrayList<>();
        EquipmentTaskExample equipmentTaskExample = new EquipmentTaskExample();
        equipmentTaskExample.createCriteria().andEquipmentidEqualTo(equipid);
        List<EquipmentTask> equipmentTasks = equipmentTaskMapper.selectByExample(equipmentTaskExample);
        for (EquipmentTask equiptask : equipmentTasks) {
            taskids.add(equiptask.getTaskid());
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("taskids", taskids);
        List<CheckitemTask> checkitemTasks = checkitemTaskMapper.getcheckid(map);
        for (CheckitemTask checktask : checkitemTasks) {
            checkids.add(checktask.getCheckitemid());
        }
        map.put("checkids", checkids);
        return checkInfoService.getcheckinfo(map);
    }

    @RequestMapping("dadiyreport")
    public String dadiyreport(Integer page1, Integer page2, String date1, Model m, Long taskidstr) throws ParseException {
        if (page1 == null || page1 <= 0) {
            page1 = 1;
        }
        if (page2 == null || page2 <= 0) {
            page2 = 1;
        }
        List<Tasktempinfo> tasktempinfos = taskreportService.dadiyreport(date1, taskidstr);
        List<Tasktempinfo> errortasktemp = new ArrayList<>();
        int num0 = 0;//总任务数
        int num1 = 0; //正常完成数
        int num2 = 0;//漏检数
        int num3 = 0;//超时数

        int num4 = 0;//发现异常数
        int num5 = 0;//总巡检项数
        int num6 = 0;//已完成数
        int num7 = 0;//未完成数

        int num8 = 0; // 未正常完成数
        int num9 = 0; // 未上传数
        num0 = tasktempinfos.size();
        for (Tasktempinfo item : tasktempinfos) {
            if (item.getState() != 2 && item.getOperationstate() != 3 || item.getOperationstate() == 1) {
                errortasktemp.add(item);
            }
            if (item.getOperationstate() == 4 || item.getOperationstate() == 1 || item.getOperationstate() == 2
                    || item.getStopstate() != 0) {
                num8++;
            }
            if (item.getOperationstate() == 0) {
                num9++;
            }
            if (item.getState() == 2 && item.getOperationstate() == 3) {
                num1++;
            }
            if (item.getState() == 2 && item.getOperationstate() == 1) {
                num2++;
            }
            if (item.getState() == 3 && item.getOperationstate() == 4) {
                num3++;
            }

        }
        List<Taskreportinfo> taskreportinfos = taskreportService.dadiyreportlist(taskidstr, date1, null);
        List<Long> reportid = new ArrayList<>();
        List<Reportcontent> errorcontents = new ArrayList<>();
        List<Reportcontent> reportcontents = new ArrayList<>();
        if (taskreportinfos.size() > 0) {
            for (Taskreportinfo content : taskreportinfos) {
                reportid.add(content.getId());
            }
            ReportcontentExample reportcontentExample = new ReportcontentExample();
            reportcontentExample.createCriteria().andOperationtimeLike(date1 + "%").andReportidIn(reportid);
            reportcontentExample.setOrderByClause("operationtime desc");
            reportcontents = reportcontentMapper.selectByExample(reportcontentExample);

            for (Reportcontent item : reportcontents) {
                if (item.getAreaskip() == 0 && item.getEquipmentskip() == 0) {
                    num6++;
                } else {
                    num7++;
                }
                if (item.getReportstate().equals("1")) {
                    num4++;
                    errorcontents.add(item);
                }
            }
        }
        num5 = reportcontents.size();
        m.addAttribute("num0", num0);
        m.addAttribute("num1", num1);
        m.addAttribute("num2", num2);
        m.addAttribute("num3", num3);
        m.addAttribute("num4", num4);
        m.addAttribute("num5", num5);
        m.addAttribute("num6", num6);
        m.addAttribute("num7", num7);

        m.addAttribute("num8", num8);
        m.addAttribute("num9", num9);

        m.addAttribute("date1", date1);
        m.addAttribute("page1", page1);
        m.addAttribute("page2", page2);
        m.addAttribute("taskidstr", taskidstr);
        PageUtil temppagebean = new PageUtil(errorcontents.size());//初始化PageBean对象
        PageUtil taskpagebean = new PageUtil(errortasktemp.size());
        List<Reportcontent> courseList = new ArrayList<>();
        List<Tasktempinfo> errortasklist = new ArrayList<>();

        //计算周期内所有任务的平均用时
        List<Long> times = new ArrayList<>();
        for (int i = 0; i < taskreportinfos.size(); i++) {
            long time = taskreportinfos.get(i).getDonetime().getTime() - taskreportinfos.get(i).getStarttime().getTime();
            times.add(time);
        }
        long avgTime = 0;
        for (Long t : times) {
            avgTime += t;
        }
        long timeAvgs = 0;
        if (taskreportinfos != null && taskreportinfos.size() > 0) {
            timeAvgs = avgTime / taskreportinfos.size(); //周期内所有任务的平均用时
        }
        long day = timeAvgs / (24 * 60 * 60 * 1000);
        long hour = (timeAvgs / (60 * 60 * 1000) - day * 24);
        long min = (timeAvgs / (60 * 1000) - day * 24 * 60 - hour * 60);
        long s = (timeAvgs / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        m.addAttribute("avgTimes", day + "天" + hour + "小时" + min + "分" + s + "秒");
        //任务检查的点位数
        int nfcCount = 0;
        for (Taskreportinfo taskreportinfo : taskreportinfos) {
            JSONArray jsonArray = JSONArray.fromObject(taskreportinfo.getAreainouttime());
            if (jsonArray.size() > 0) {
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    if (jsonObject != null)
                        if (jsonObject.get("nfcUse") != null)
                            if (jsonObject.get("nfcUse").equals(1)) {
                                nfcCount++;
                            }
                }
            }
        }
        m.addAttribute("nfcCount", nfcCount);


        if (page1 != null) {
            temppagebean.setCurPage(page1); //这里page是从页面上获取的一个参数，代表页数
            int pagesize = temppagebean.getPageSize();
            //获得分页数据在list集合中的索引
            int firstIndex = (page1 - 1) * pagesize;
            int toIndex = page1 * pagesize;
            if (toIndex > errorcontents.size()) {
                toIndex = errorcontents.size();
            }
            if (firstIndex > toIndex) {
                firstIndex = 0;
                temppagebean.setCurPage(1);
            }
            //截取数据集合，获得分页数据
            courseList = errorcontents.subList(firstIndex, toIndex);
        }
        if (page2 != null) {
            taskpagebean.setCurPage(page2); //这里page是从页面上获取的一个参数，代表页数
            int pagesize = taskpagebean.getPageSize();
            //获得分页数据在list集合中的索引
            int firstIndex = (page2 - 1) * pagesize;
            int toIndex = page2 * pagesize;
            if (toIndex > errortasktemp.size()) {
                toIndex = errortasktemp.size();
            }
            if (firstIndex > toIndex) {
                firstIndex = 0;
                taskpagebean.setCurPage(1);
            }
            //截取数据集合，获得分页数据
            errortasklist = errortasktemp.subList(firstIndex, toIndex);
        }
        m.addAttribute("courseList", courseList);
        m.addAttribute("errortasklist", errortasklist);
        m.addAttribute("temppagebean", temppagebean);
        m.addAttribute("taskpagebean", taskpagebean);
        m.addAttribute("identifyingid", errortasklist.size() > 0 ? errortasklist.get(0).getTask().getIdentifyingid() : null);

        m.addAttribute("exceptionCount", errorcontents.size());
        m.addAttribute("ip", ip);
        return "dadiyreport";
    }

    @RequestMapping("weekreport")
    public String weekreport(Integer page1, Integer page2, String date1, String date2, Model m, int type, Long taskidstr) throws ParseException {
        if (page1 == null || page1 <= 0) {
            page1 = 1;
        }
        if (page2 == null || page2 <= 0) {
            page2 = 1;
        }
        List<Tasktempinfo> tasktempinfos = taskreportService.dadiyreport2(date1, date2, taskidstr);
        List<Tasktempinfo> errortasktemp = new ArrayList<>();
        int num0 = 0;//总任务数
        int num1 = 0; //正常完成数
        int num2 = 0;//漏检数
        int num3 = 0;//超时数
        int num4 = 0;//发现异常数
        int num5 = 0;//总巡检项数
        int num6 = 0;//已完成数
        int num7 = 0;//未完成数
        int num8 = 0; // 未正常完成数
        int num9 = 0; // 未上传数
        num0 = tasktempinfos.size();
        if (tasktempinfos != null && tasktempinfos.size() > 0)
            for (Tasktempinfo item : tasktempinfos) {
                if (item.getState() != 2 && item.getOperationstate() != 3 || item.getOperationstate() == 1) {
                    errortasktemp.add(item);
                }
                if (item.getOperationstate() == 4 || item.getOperationstate() == 1 || item.getOperationstate() == 2
                        || item.getStopstate() != 0) {
                    num8++;
                }
                if (item.getOperationstate() == 0) {
                    num9++;
                }
                if (item.getState() == 2 && item.getOperationstate() == 3) {
                    num1++;
                }
                if (item.getState() == 2 && item.getOperationstate() == 1) {
                    num2++;
                }
                if (item.getState() == 3 && item.getOperationstate() == 4) {
                    num3++;
                }
            }
        List<Taskreportinfo> taskreportinfos = taskreportService.dadiyreportlist(taskidstr, null, null);
        List<Long> reportid = new ArrayList<>();
        //计算周期内所有任务的平均用时
        List<Long> times = new ArrayList<>();
        if (taskreportinfos != null && taskreportinfos.size() > 0)
            for (int i = 0; i < taskreportinfos.size(); i++) {
                long time = taskreportinfos.get(i).getDonetime().getTime() - taskreportinfos.get(i).getStarttime().getTime();
                times.add(time);
            }
        long avgTime = 0;
        for (Long t : times) {
            avgTime += t;
        }
        long timeAvgs = 0;
        if (taskreportinfos != null && taskreportinfos.size() > 0) {
            timeAvgs = avgTime / taskreportinfos.size(); //周期内所有任务的平均用时
            long day = timeAvgs / (24 * 60 * 60 * 1000);
            long hour = (timeAvgs / (60 * 60 * 1000) - day * 24);
            long min = (timeAvgs / (60 * 1000) - day * 24 * 60 - hour * 60);
            long s = (timeAvgs / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
            m.addAttribute("avgTimes", day + "天" + hour + "小时" + min + "分" + s + "秒");
        } else
            m.addAttribute("avgTimes", 0 + "天" + 0 + "小时" + 0 + "分" + 0 + "秒");

        //任务检查的点位数
        int nfcCount = 0;
        if (taskreportinfos != null && taskreportinfos.size() > 0)
            for (Taskreportinfo taskreportinfo : taskreportinfos) {
                JSONArray jsonArray = JSONArray.fromObject(taskreportinfo.getAreainouttime());
                if (jsonArray.size() > 0) {
                    for (int i = 0; i < jsonArray.size(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        if (jsonObject != null)
                            if (jsonObject.get("nfcUse") != null)
                                if (jsonObject.get("nfcUse").equals(1)) {
                                    nfcCount++;
                                }
                    }
                }
            }
        m.addAttribute("nfcCount", nfcCount);
        if (taskreportinfos != null && taskreportinfos.size() > 0)
            for (Taskreportinfo content : taskreportinfos) {
                reportid.add(content.getId());
            }

        List<Reportcontent> reportcontents = new ArrayList<>();
        List<Reportcontent> errorcontents = new ArrayList<>();
        if (taskreportinfos != null && taskreportinfos.size() > 0) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date time2 = DateUtils.getWholePointDate(0);

            ReportcontentExample reportcontentExample = new ReportcontentExample();
            reportcontentExample.createCriteria().andOperationtimeBetween(date1, sdf.format(time2)).andReportidIn(reportid);
            reportcontentExample.setOrderByClause("operationtime desc");
            reportcontents = reportcontentMapper.selectByExample(reportcontentExample);
            for (Reportcontent item : reportcontents) {
                if (item.getAreaskip() == 0 && item.getEquipmentskip() == 0) {
                    num6++;
                } else {
                    num7++;
                }
                if (item.getReportstate().equals("1")) {
                    num4++;
                    errorcontents.add(item);
                }
            }
        }

      /*  SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date start = sdf.parse("20180604141010");
        Date end = sdf.parse("20180603141010");
        long between = end.getTime() - start.getTime();
        long day = between / (24*60*60*1000);
        long hour = (between / (60*60*1000) -day*24);
        long min = (between / (60*1000) -day *24*60-hour*60);
        long s = (between / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        System.out.println(day+"天"+hour+"小时"+min+"分"+s+"秒");*/

        num5 = reportcontents.size();
        m.addAttribute("num0", num0);
        m.addAttribute("num1", num1);
        m.addAttribute("num2", num2);
        m.addAttribute("num3", num3);
        m.addAttribute("num4", num4);
        m.addAttribute("num5", num5);
        m.addAttribute("num6", num6);
        m.addAttribute("num7", num7);

        m.addAttribute("num8", num8);
        m.addAttribute("num9", num9);

        m.addAttribute("date1", date1);
        m.addAttribute("date2", date2);
        m.addAttribute("type", type);
        m.addAttribute("page1", page1);
        m.addAttribute("page2", page2);
        m.addAttribute("taskidstr", taskidstr);
        m.addAttribute("errorcontents", errorcontents);
        m.addAttribute("errortasktemp", errortasktemp);
        PageUtil temppagebean = new PageUtil(errorcontents.size());//初始化PageBean对象
        PageUtil taskpagebean = new PageUtil(errortasktemp.size());
        List<Reportcontent> courseList = new ArrayList<>();
        List<Tasktempinfo> errortasklist = new ArrayList<>();
        if (page1 != null) {
            temppagebean.setCurPage(page1); //这里page是从页面上获取的一个参数，代表页数
            int pagesize = temppagebean.getPageSize();
            //获得分页数据在list集合中的索引
            int firstIndex = (page1 - 1) * pagesize;
            int toIndex = page1 * pagesize;
            if (toIndex > errorcontents.size()) {
                toIndex = errorcontents.size();
            }
            if (firstIndex > toIndex) {
                firstIndex = 0;
                temppagebean.setCurPage(1);
            }
            //截取数据集合，获得分页数据
            courseList = errorcontents.subList(firstIndex, toIndex);
        }
        if (page2 != null) {
            taskpagebean.setCurPage(page2); //这里page是从页面上获取的一个参数，代表页数
            int pagesize = taskpagebean.getPageSize();
            //获得分页数据在list集合中的索引
            int firstIndex = (page2 - 1) * pagesize;
            int toIndex = page2 * pagesize;
            if (toIndex > errortasktemp.size()) {
                toIndex = errortasktemp.size();
            }
            if (firstIndex > toIndex) {
                firstIndex = 0;
                taskpagebean.setCurPage(1);
            }
            //截取数据集合，获得分页数据
            errortasklist = errortasktemp.subList(firstIndex, toIndex);
        }
        m.addAttribute("courseList", courseList);
        m.addAttribute("errortasklist", errortasklist);
        m.addAttribute("temppagebean", temppagebean);
        m.addAttribute("taskpagebean", taskpagebean);
        m.addAttribute("identifyingid", errortasklist.size() > 0 ? errortasklist.get(0).getTask().getIdentifyingid() : null);
        m.addAttribute("ip", ip);

        m.addAttribute("exceptionCount", errorcontents.size());
        if (type == 0) {
            return "weekreport";
        } else
            return "monthreport";
    }

    /**
     * 某一天的 异常邮件内容
     *
     * @param page1
     * @param m
     * @return
     */
    @RequestMapping("exceptionReport")
    public String exceptionReport(Integer page1, Model m, Long reportId) {
        if (page1 == null || page1 <= 0) {
            page1 = 1;
        }

        List<Reportcontent> reportcontents = new ArrayList<>();
        ReportcontentExample reportcontentExample = new ReportcontentExample();
        reportcontentExample.createCriteria().andReportidEqualTo(reportId).andReportstateWithAliasEqualTo("1");
        reportcontents = reportcontentMapper.selectByExample(reportcontentExample);

        m.addAttribute("page1", page1);
        m.addAttribute("reportId", reportId);

        PageUtil temppagebean = new PageUtil(reportcontents.size());//初始化PageBean对象
        List<Reportcontent> courseList = new ArrayList<>();
        if (page1 != null) {
            temppagebean.setCurPage(page1); //这里page是从页面上获取的一个参数，代表页数
            int pagesize = temppagebean.getPageSize();
            //获得分页数据在list集合中的索引
            int firstIndex = (page1 - 1) * pagesize;
            int toIndex = page1 * pagesize;
            if (toIndex > reportcontents.size()) {
                toIndex = reportcontents.size();
            }
            if (firstIndex > toIndex) {
                firstIndex = 0;
                temppagebean.setCurPage(1);
            }
            //截取数据集合，获得分页数据
            courseList = reportcontents.subList(firstIndex, toIndex);
        }

        Taskreportinfo taskreportinfos = taskreportService.selectByPrimaryKey(reportId);
        Taskplaninfo taskplaninfo = taskPlanService.selectByPrimaryKey(taskreportinfos.getTaskid());
        m.addAttribute("courseList", courseList);
        m.addAttribute("errortasklist", reportcontents);
        m.addAttribute("temppagebean", temppagebean);
//        m.addAttribute("taskpagebean", taskpagebean);
//        m.addAttribute("identifyingid", errortasklist.size()>0?errortasklist.get(0).getTask().getIdentifyingid():null);
        m.addAttribute("identifyingid", taskplaninfo != null ? taskplaninfo.getIdentifyingid() : null);
        m.addAttribute("ip", ip);
        return "exceptionReport";
    }

    /**
     * 获取要显示列的index 。
     *
     * @param list
     * @return
     */
    public Map getShowColum(List<String> list) {
        // 0区域 	1设备 2巡检项	 3低值	 4高值	5告警下限	6告警上限
        Map<Integer, String> showMap = new HashMap<>();
        for (String item : list) {
            Integer index = null;
            switch (item) {
                case "区域":
                    index = 0;
                    break;
                case "设备":
                    index = 1;
                    break;
                case "巡检项":
                    index = 2;
                    break;
                case "正常最低值":
                    index = 3;
                    break;
                case "正常最高值":
                    index = 4;
                    break;
                case "下限警告值":
                    index = 5;
                    break;
                case "上限警告值":
                    index = 6;
                    break;
            }
            showMap.put(index, item);
        }
        return showMap;
    }


}