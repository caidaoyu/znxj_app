package com.niule.znxj.web.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.github.pagehelper.PageInfo;
import com.niule.znxj.core.entity.JSONResult;
import com.niule.znxj.core.entity.Result;
import com.niule.znxj.core.util.PageBean;
import com.niule.znxj.core.util.json.JsonUtil;
import com.niule.znxj.web.dao.CheckitemTaskMapper;
import com.niule.znxj.web.dao.TaskstopinfoMapper;
import com.niule.znxj.web.dao.TasktempinfoMapper;
import com.niule.znxj.web.model.*;
import com.niule.znxj.web.model.taskcontent.TaskArea;
import com.niule.znxj.web.model.taskcontent.TaskContent;
import com.niule.znxj.web.service.*;
import net.sf.json.JSON;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by administor on 2017/3/27.
 */
@Controller
public class WebTaskPlanController {
    @Resource
    private TaskPlanService taskPlanService;
    @Resource
    private ClassinfoService classinfoService;
    @Resource
    private UserinfoService userinfoService;
    @Resource
    private AreaService areaService;
    @Resource
    private EquipmentService equipmentService;
    @Resource
    private CheckInfoService checkInfoService;
    @Resource
    private CommonService commonService;
    @Resource
    private SiteService siteService;
    @Resource
    private TasktempinfoMapper tasktempinfoMapper;
    @Resource
    private TaskstopinfoMapper taskstopinfoMapper;
    @Resource
    private OperateLogService operateLogService;
    @Resource
    private CheckitemTaskMapper checkitemTaskMapper;
    protected PageBean pageBean = new PageBean();

    public PageBean getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean pageBean) {
        this.pageBean = pageBean;
    }
    /*日常任务巡检部分*/

    /**
     * 分页显示所有任务的信息
     *
     * @param page          当前页
     * @param identifyingid 任务编号
     * @param m
     * @param state         任务状态 0 停止运行 1 运行中
     * @param type          任务类型 0日常巡检 1计划巡检 2隐患排查 3视频巡检 4临时任务
     * @return
     */
    @RequestMapping("showtaskplan")/*
    @RequiresPermissions("item:task")*/
    public String showtaskplan(int page, String identifyingid, Model m, String state, int type, HttpServletRequest request) {
        if (page <= 0) {
            page = 1;
        }
        int pagesize = 15;
        page = PageBean.countCurrentPage(page);

        long totalpage = 0L;
        Admininfo admininfo = (Admininfo) request.getSession().getAttribute("userInfo");
        List<Long> ids = new ArrayList();
        if (admininfo.getSiteid() == null) {
            List<Siteareainfo> sites = siteService.queryAllSite();
            if (sites.size() > 0) {
                for (Siteareainfo siteareainfo : sites) {
                    ids.add(siteareainfo.getId());
                }
            } else
                ids = null;
        } else {
            ids.add(Long.valueOf(admininfo.getSiteid()));
        }
        HashMap map = new HashMap();
        map.put("page", (page - 1) * pagesize);
        map.put("size", pagesize);
        map.put("ids", ids);
        map.put("identifyingid", "%" + identifyingid + "%");
        map.put("state", state);
        map.put("type", type);
        List<Taskplaninfo> taskplaninfos = null;
        if (((identifyingid == null) || ("".equals(identifyingid))) && ((state == null) || ("".equals(state)))) {
//            if (page == 1) {
//            taskplaninfos = getTaskplaninfos(type, page, pagesize);

//            } else
                taskplaninfos = this.taskPlanService.findByPageTask(map);

            int rows1 = this.taskPlanService.countTask(map);
            totalpage = PageBean.counTotalPage(pagesize, rows1);
            state = "";
        } else {
            taskplaninfos = this.taskPlanService.findByPageTask2(map);
            int rows2 = this.taskPlanService.countTask2(map);
            totalpage = PageBean.counTotalPage(pagesize, rows2);
        }
//        if (taskplaninfos != null && taskplaninfos.size() > 0) {
//            List<Taskplaninfo> taskplaninfos2 = new ArrayList<>();
//            for (Taskplaninfo taskplaninfo : taskplaninfos) {
//                if (admininfo.getSiteid() != null && taskplaninfo.getSiteid() != null)
//                    if (!taskplaninfo.getSiteid().equals((long) admininfo.getSiteid())) {
//                        taskplaninfos2.add(taskplaninfo);
//                    }
//            }
//            taskplaninfos.removeAll(taskplaninfos2);
//        }
        this.pageBean.setList(taskplaninfos);
        this.pageBean.setTotalPage((int) totalpage);
        this.pageBean.setCurrentPage(page);
        m.addAttribute("pageBean", this.pageBean);
        m.addAttribute("type", type);
        m.addAttribute("identifyingid", identifyingid);
        m.addAttribute("states", state);
        return "showtaskplan";
    }


    List<Userinfo> userinfoList;
    List<Siteareainfo> siteareainfoList;

    //获取任务列表， 并分页
    private List<Taskplaninfo> getTaskplaninfos(int type, int page, int size) {

        TaskplaninfoExample example = new TaskplaninfoExample();
        example.createCriteria().andTypeEqualTo(type).andStateEqualTo(1);
        List<Taskplaninfo> taskplaninfos = this.taskPlanService.selectByExample(example);
        Collections.reverse(taskplaninfos);  //倒序

        //获取所有用户
        if (userinfoList == null)
            userinfoList = userinfoService.selectByExample();
        //获取所有厂区
        if (siteareainfoList == null)
            siteareainfoList = siteService.selectByExample();
        for (Taskplaninfo taskplaninfo : taskplaninfos) {
            if (userinfoList != null && userinfoList.size() > 0) {
                for (Userinfo userinfo : userinfoList) {
                    if (taskplaninfo.getDirectorid().equals(userinfo.getId())) {
                        taskplaninfo.setUsername(userinfo.getRealname());
                    }
                }
            }
            if (siteareainfoList != null && siteareainfoList.size() > 0) {
                for (Siteareainfo siteareainfo : siteareainfoList) {
                    if (taskplaninfo.getSiteid().equals(siteareainfo.getId())) {
                        taskplaninfo.setSite(siteareainfo);
                    }
                }
            }

        }

        List<Taskplaninfo> list = new ArrayList<>();
        for (int i = 0; i < taskplaninfos.size(); i++) {
            if (i >= (page - 1) * size) {
                list.add(taskplaninfos.get(i));
                if (list.size() == size) {
                    break;
                }
            }
        }

        return list;
    }

    /*跳转到新增日常巡检任务页面*/
    @RequestMapping("toaddtaskplan")
    public String toaddtaskplan(Model m, int type, HttpServletRequest request) {
        Admininfo admininfo = (Admininfo) request.getSession().getAttribute("userInfo");
        List<Classinfo> classinfos = classinfoService.selectByExample2(admininfo.getSiteid());
        List<Siteareainfo> siteareainfos = siteService.selectByExample2(admininfo.getSiteid());

        m.addAttribute("classinfos", classinfos);
        m.addAttribute("type", type);
        m.addAttribute("siteareainfos", siteareainfos);
        return "addtaskplan";
    }

    /*新增日常任务*/
    @RequestMapping("addtaskplan")
    @ResponseBody
    public Result addtaskplan(Taskplaninfo taskplaninfo, HttpSession session) {

        List<String> timeList = (List<String>) JSONUtils.parse(taskplaninfo.getImplementtime());
        //先排序秒
        paixumiao(timeList);
        paixushi(timeList);
        Collections.reverse(timeList); //倒序
        taskplaninfo.setImplementtime(JSONUtils.toJSONString(timeList));
        //插入任务
        taskPlanService.insert(taskplaninfo);
        //往temp表插入相关信息
        commonService.generateByTask(taskplaninfo);
        String info = "添加了任务" + taskplaninfo.getCustomid();
        Admininfo logadmininfo = (Admininfo) session.getAttribute("userInfo");
        String username = logadmininfo.getUsername();
        int addlog = operateLogService.insertSelective(username, info);
        return new JSONResult<>();
    }

    /*显示班组（级联负责人和班组）*/
    @RequestMapping("querydirectorid")
    @ResponseBody
    public Long querydirectorid(Long classid, Model m) {
        Classinfo classinfo = classinfoService.selectByPrimaryKey(classid);
        return classinfo.getDirectorid();
    }

    @RequestMapping("querydirectorname")
    @ResponseBody
    public Userinfo querydirectorname(Long uid, Model m) {
        Userinfo userinfo = userinfoService.queryRealname(uid);
        return userinfo;
    }

    /*显示所有区域*/
    @RequestMapping("showarea")
    @ResponseBody
    public List<Areainfo> showarea() {
        return areaService.selectByExample();
    }

    /*显示某个区域下的所有设备*/
    @RequestMapping("showequipment")
    @ResponseBody
    public List<Equipmentinfo> showequipment(int areaid) {
        return equipmentService.queryequip(areaid);
    }

    /*显示所有巡检项*/
    @RequestMapping("showcheck")
    @ResponseBody
    public List<Checkiteminfo> showcheck() {
        return checkInfoService.selectByExample();
    }

    /*显示巡检项内容*/
    @RequestMapping("showdatarecord")
    @ResponseBody
    public Checkiteminfo showdatarecord(int recordid) {
        return checkInfoService.queryByRecord(recordid);
    }

    /*删除指定任务*/
    @RequestMapping("deltask")
    @ResponseBody
    public int deltask(Long id, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String name = request.getParameter("taskname");
        int deltask = taskPlanService.deleteByPrimaryKey(id);

        if (deltask == 1) {     //2018年7月16日17:51:54
            CheckitemTaskExample example = new CheckitemTaskExample();
            example.createCriteria().andCheckitemidEqualTo(id);
            checkitemTaskMapper.deleteByExample(example);
        }
        String info = "删除了任务" + name;
        Admininfo logadmininfo = (Admininfo) session.getAttribute("userInfo");
        String username = logadmininfo.getUsername();
        int addlog = operateLogService.insertSelective(username, info);
        if (deltask > 0 && addlog > 0) {
            return 1;
        } else
            return 0;
    }

    /**
     * 根据编号id查询当前任务的详细信息
     *
     * @param id 编号id
     * @param m
     * @return 跳到修改日常页面
     */
    @RequestMapping("querybytaskid")
    public String querybytaskid(Long id, Model m, int type, HttpServletRequest request, int page) {
        Taskplaninfo taskplaninfo = taskPlanService.selectByPrimaryKey(id);
        List<Userinfo> userinfos = userinfoService.selectByExample();

        Admininfo admininfo = (Admininfo) request.getSession().getAttribute("userInfo");
        List<Classinfo> classinfos = classinfoService.selectByExample2(admininfo.getSiteid());
        List<Siteareainfo> siteareainfos = siteService.selectByExample2(admininfo.getSiteid());

        m.addAttribute("taskplaninfo", taskplaninfo);
        m.addAttribute("classinfos", classinfos);
        m.addAttribute("userinfos", userinfos);
        m.addAttribute("type", type);
        m.addAttribute("siteareainfos", siteareainfos);
        m.addAttribute("page", page);

        return "updatetaskplan";
    }

    /**
     * 修改日常巡检任务
     *
     * @param taskplaninfo
     * @return
     */
    @RequestMapping("updtaskplan")
    @ResponseBody
    public Result updtaskplan(Taskplaninfo taskplaninfo, String isfinal, HttpSession session, Integer nfc) {
        List<String> timeList = (List<String>) JSONUtils.parse(taskplaninfo.getImplementtime());
        paixumiao(timeList);
        paixushi(timeList);
        Collections.reverse(timeList); //倒序
        taskplaninfo.setImplementtime(JSONUtils.toJSONString(timeList));
        if (("0").equals(isfinal)) {
            taskPlanService.updateByPrimaryKeySelective(taskplaninfo);
            taskPlanService.deleteTaskTempByTaskId(taskplaninfo.getId());
            commonService.generateByTask(taskplaninfo);
            return new JSONResult<>();
        } else
            taskPlanService.updateByPrimaryKeySelective(taskplaninfo);
        String info = "修改了任务" + taskplaninfo.getCustomid();
        Admininfo logadmininfo = (Admininfo) session.getAttribute("userInfo");
        String username = logadmininfo.getUsername();
        int addlog = operateLogService.insertSelective(username, info);

        return new JSONResult<>();
    }

    /**
     * 查看日常巡检任务详情
     */
    @RequestMapping("taskplandetail")
    /* @RequiresPermissions("item:taskdetail")*/
    public String taskplandetail(Long id, Model m, int type) {
        Taskplaninfo taskplaninfo = taskPlanService.selectByPrimaryKey(id);
        List<Classinfo> classinfos = classinfoService.selectByExample();
        List<Userinfo> userinfos = userinfoService.selectByExample();

        m.addAttribute("taskplaninfo", taskplaninfo);
        m.addAttribute("classinfos", classinfos);
        m.addAttribute("userinfos", userinfos);
        m.addAttribute("type", type);
        return "taskplandetail";
    }

    @RequestMapping("showstoptask")
    public String showstoptask(int page, int state, Model m, int type) {
        int size = 15;
        PageInfo info = new PageInfo<>(taskPlanService.getStopTask(page, size, state, type));
        m.addAttribute("info", info);
        m.addAttribute("type", type);
        return "showstoptask";
    }

    @RequestMapping("deltemp")
    @ResponseBody
    public int deltemp(Long tempid) {
        TaskstopinfoExample taskstopinfoExample = new TaskstopinfoExample();
        taskstopinfoExample.createCriteria().andTasktempidEqualTo(tempid);
        int delstop = taskstopinfoMapper.deleteByExample(taskstopinfoExample);

        int deltemp = tasktempinfoMapper.deleteByPrimaryKey(tempid);

        if (delstop > 0 && deltemp > 0) {
            return 1;
        } else
            return 0;
    }


    public void paixumiao(List<String> timeList) {
        //先排序秒
        Collections.sort(timeList, new Comparator<String>() {
            /**
             * @param lhs
             * @param rhs
             * @return an integer < 0 if lhs is less than rhs, 0 if they are
             * equal, and > 0 if lhs is greater than rhs,比较数据大小时,这里比的是时间
             */
            @Override
            public int compare(String lhs, String rhs) {
                int s1 = Integer.parseInt(lhs.substring(3, 5));
                int s2 = Integer.parseInt(rhs.substring(3, 5));
                if (s1 > s2)
                    return 1;
                return -1;
            }
        });
    }


    public void paixushi(List<String> timeList) {
        //再排序小时
        Collections.sort(timeList, new Comparator<String>() {
            /**
             * @param lhs
             * @param rhs
             * @return an integer < 0 if lhs is less than rhs, 0 if they are
             * equal, and > 0 if lhs is greater than rhs,比较数据大小时,这里比的是时间
             */
            @Override
            public int compare(String lhs, String rhs) {
                int s1 = Integer.parseInt(rhs.substring(0, 2));
                int s2 = Integer.parseInt(lhs.substring(0, 2));
                if (s1 > s2)
                    return 1;
                return -1;
            }
        });
    }
}
