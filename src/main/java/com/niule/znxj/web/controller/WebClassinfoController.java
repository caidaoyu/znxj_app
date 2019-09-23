package com.niule.znxj.web.controller;

import com.niule.znxj.core.util.PageBean;
import com.niule.znxj.web.model.Admininfo;
import com.niule.znxj.web.model.Classinfo;
import com.niule.znxj.web.model.Siteareainfo;
import com.niule.znxj.web.model.Userinfo;
import com.niule.znxj.web.service.ClassinfoService;
import com.niule.znxj.web.service.OperateLogService;
import com.niule.znxj.web.service.SiteService;
import com.niule.znxj.web.service.UserinfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by administor on 2017/3/17.
 */
@Controller
public class WebClassinfoController {
    @Resource
    private ClassinfoService classinfoService;
    @Resource
    private UserinfoService userinfoService;
    @Resource
    private SiteService siteService;
    @Resource
    private OperateLogService operateLogService;
    protected PageBean pageBean = new PageBean();

    public PageBean getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean pageBean) {
        this.pageBean = pageBean;
    }

    @RequestMapping("/showallclass")
    @RequiresPermissions("item:class")
    public String showallclass(Model m, int page, HttpServletRequest request) {
        if (page <= 0) {
            page = 1;
        }
        int pagesieze = 15;

        Admininfo admininfo = (Admininfo) request.getSession().getAttribute("userInfo");

        List ids = new ArrayList();
        if (admininfo.getSiteid() == null) {
            List<Siteareainfo> sites = siteService.queryAllSite();
            if (sites.size() > 0) {
                for (Siteareainfo siteareainfo : sites) {
                    ids.add(siteareainfo.getId());
                }
            } else
                ids = null;

        } else {
            ids.add(admininfo.getSiteid());
        }
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("page", (page - 1) * pagesieze);
        map.put("size", pagesieze);
        map.put("ids", ids);

        page = PageBean.countCurrentPage(page);
        List<Classinfo> classinfos = classinfoService.findByPageClass(map);
        int rows = classinfoService.countClass(map);
        long totalpage = PageBean.counTotalPage(pagesieze, rows);

        pageBean.setList(classinfos);
        pageBean.setTotalPage((int) totalpage);
        pageBean.setCurrentPage(page);
        m.addAttribute("pageBean", pageBean);
        return "showclass";
    }

    @RequestMapping("toaddclass")
    @RequiresPermissions("add:class")
    public String toaddclass(Model m, HttpServletRequest request) {
        List<Userinfo> userinfos = userinfoService.selectByExample();
        Admininfo admininfo = (Admininfo) request.getSession().getAttribute("userInfo");
        List<Siteareainfo> siteareainfos = siteService.selectByExample2(admininfo.getSiteid());

        m.addAttribute("userinfos", userinfos);
        m.addAttribute("siteareainfos", siteareainfos);
        return "addclass";
    }

    @RequestMapping("/addclass")
    public String addclass(Classinfo classinfo, HttpSession session) {
        int addresult = classinfoService.insert(classinfo);
        //当前登陆的管理员信息

        String info = "新增了班组" + classinfo.getCustomid();
        Admininfo logadmininfo = (Admininfo) session.getAttribute("userInfo");
        String username = logadmininfo.getUsername();
        int addlog = operateLogService.insertSelective(username, info);
        if (addresult > 0) {
            return "redirect:showallclass?page=1";
        }
        return "addclass";
    }

    @RequestMapping("/delclass")
    @RequiresPermissions("del:class")
    @ResponseBody
    public int delclass(Long id, HttpServletRequest request) {
        String classname = request.getParameter("classname");
        //删除管理员
        int delclass = classinfoService.deleteByPrimaryKey(id);
        //添加操作日志
        HttpSession session = request.getSession();
        String info = "删除了班组" + classname + "的信息";
        Admininfo logadmininfo = (Admininfo) session.getAttribute("userInfo");
        String username = logadmininfo.getUsername();
        int addlog = operateLogService.insertSelective(username, info);
        if (delclass > 0 && addlog > 0) {
            return 1;
        } else
            return 0;
    }

    @RequestMapping("/selectbyclassid")
    @RequiresPermissions("upd:class")
    public String selectbyposid(Long id, Model m, HttpServletRequest request, int page) {
        Classinfo classinfo = classinfoService.selectByPrimaryKey(id);
        List<Userinfo> userinfos = userinfoService.selectByExample();
        Admininfo admininfo = (Admininfo) request.getSession().getAttribute("userInfo");
        List<Siteareainfo> siteareainfos = siteService.selectByExample2(admininfo.getSiteid());
        if (classinfo != null)
            m.addAttribute("classinfo", classinfo);
        m.addAttribute("userinfos", userinfos);
        m.addAttribute("siteareainfos", siteareainfos);
        m.addAttribute("page", page);
        return "updateclass";
    }

    @RequestMapping("/updclass")
    public String updclass(Classinfo classinfo, HttpSession session, int page) {
        int updresult = classinfoService.updateByPrimaryKey(classinfo);
        //获取登录用的信息

        String info = "修改了班组" + classinfo.getCustomid() + "的基本信息";
        Admininfo logadmininfo = (Admininfo) session.getAttribute("userInfo");
        String username = logadmininfo.getUsername();
        int addlog = operateLogService.insertSelective(username, info);
        if (updresult > 0 && addlog > 0) {
            return "redirect:showallclass?page=" + page;
        }
        return "updateclass";
    }

    @RequestMapping("/queryclassdetailbyid")
/*    @RequiresPermissions("item:classdetail")*/
    public String queryclassdetailbyid(Long id, Model m) {
        Classinfo detailclass = classinfoService.selectByPrimaryKey(id);
        if (detailclass != null)
            m.addAttribute("detailclass", detailclass);
        return "showclassdetail";
    }

    @RequestMapping("queryclassbysiteid")
    @ResponseBody
    public List<Classinfo> queryclassbysiteid(Long siteid) {
        return classinfoService.queryclassbysiteid(siteid);
    }

    /**
     * 统计出某个班组有多少人
     *
     * @param classid
     * @return
     */
    @RequestMapping("countuser")
    @ResponseBody
    public int countuser(Long classid) {
        return userinfoService.countuser(classid);
    }

    @RequestMapping("classbyuserid")
    @ResponseBody
    public List<Classinfo> classbyuserid(Long directorid) {
        return classinfoService.classbyuserid(directorid);
    }


    @RequestMapping("isClassIdExist")
    @ResponseBody
    public int isClassIdExist(String customName, Long siteareaid, Long id) {
        int count = classinfoService.isClassIdExist(customName, siteareaid, id);
        if (count > 0) {
            return 1;
        }
        return 0;
    }
}
