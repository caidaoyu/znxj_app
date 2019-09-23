package com.niule.znxj.web.controller;

import com.niule.znxj.core.util.PageBean;
import com.niule.znxj.web.model.*;
import com.niule.znxj.web.service.ContactinfoService;
import com.niule.znxj.web.service.OperateLogService;
import com.niule.znxj.web.service.SiteService;
import com.niule.znxj.web.service.TaskPlanService;
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
 * Created by administor on 2017/3/20.
 */
@Controller
public class WebContactController {
    @Resource
    private ContactinfoService contactinfoService;
    @Resource
    private OperateLogService operateLogService;
    @Resource
    private TaskPlanService taskPlanService;
    @Resource
    private SiteService siteService;

    protected PageBean pageBean = new PageBean();

    public PageBean getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean pageBean) {
        this.pageBean = pageBean;
    }

    @RequestMapping("/showallcont")
    @RequiresPermissions("item:contact")
    public String showallcont(Model m, int page,String name,String roletype){
        if(page<=0){
            page=1;
        }
        int pagesieze=15;
        page = PageBean.countCurrentPage(page);
        List<Contactinfo> contactinfos=null;
        long totalpage=0;
        if((name==null||"".equals(name)) && (roletype==null || "".equals(roletype)) ){
            contactinfos = contactinfoService.findByPageCon(page, pagesieze);
            int rows1 = contactinfoService.countCon();
            totalpage = PageBean.counTotalPage(pagesieze, rows1);
            roletype="";
        }else{
            HashMap<String,Object> map=new HashMap<String,Object>();
            map.put("page",(page-1)*pagesieze);
            map.put("pagesize",pagesieze);
            map.put("name","%"+name+"%");
            map.put("roletype",roletype);
            contactinfos = contactinfoService.findByPageCon2(map);
            int rows2 = contactinfoService.countCon2(map);
            totalpage = PageBean.counTotalPage(pagesieze, rows2);
        }
        pageBean.setList(contactinfos);
        pageBean.setTotalPage((int) totalpage);
        pageBean.setCurrentPage(page);
        m.addAttribute("pageBean",pageBean);
        m.addAttribute("roletype",roletype);
        m.addAttribute("name",name);
        return "showpersion";
    }
    @RequestMapping("/addcont")
    @RequiresPermissions("add:contact")
    public String addcont(Contactinfo contactinfo,HttpSession session){
        contactinfo.setGroupid("0");
        int addresult=contactinfoService.insert(contactinfo);
        String info="新增了联系人"+contactinfo.getName();
        Admininfo logadmininfo=(Admininfo)session.getAttribute("userInfo");
        String username=logadmininfo.getUsername();
        int addlog=operateLogService.insertSelective(username,info);
        if(addresult>0){
            return "redirect:showallcont?page=1";
        }
        return "addpersion";
    }
    @RequestMapping("/delcont")
    @RequiresPermissions("del:contact")
    @ResponseBody
    public int delcont(Long id, HttpServletRequest request){
        String contname=request.getParameter("contname");
        //删除联系人
        int delcont=contactinfoService.deleteByPrimaryKey(id);
        //添加操作日志
        HttpSession session=request.getSession();

        String info="删除了联系人"+contname+"的信息";
        Admininfo logadmininfo=(Admininfo)session.getAttribute("userInfo");
        String username=logadmininfo.getUsername();
        int addlog=operateLogService.insertSelective(username,info);
        if(delcont>0&&addlog>0){
            return 1;
        }else
            return 0;
    }
    @RequestMapping("/queryById")
    @RequiresPermissions("upd:contact")
    public String queryById(Long id,Model m,int page){
        Contactinfo contactinfo=contactinfoService.selectByPrimaryKey(id);
        m.addAttribute("page",page);
        if(m!=null)
            m.addAttribute("contactinfo",contactinfo);
        return "updatepersion";
    }
    @RequestMapping("/updcont")
    public String updcont(Contactinfo contactinfo,String selectedgroup, HttpSession session,int page){
        contactinfo.setGroupid("0");
        int updresult=contactinfoService.updateByPrimaryKey(contactinfo);
        //获取登录用的信息
        //添加操作日志
        String info="修改了联系人"+contactinfo.getName()+"的基本信息";
        Admininfo logadmininfo=(Admininfo)session.getAttribute("userInfo");
        String username=logadmininfo.getUsername();
        int addlog=operateLogService.insertSelective(username,info);
        if(updresult>0&&addlog>0){
            return "redirect:showallcont?page="+page;
        }
        return "updatepersion";
    }
    @RequestMapping("/queryBycontId")
   /* @RequiresPermissions("item:contactdetail")*/
    public String queryBycontId(Long id,Model m){
        Contactinfo contactinfo=contactinfoService.selectByPrimaryKey(id);
        if(m!=null)
            m.addAttribute("contactinfo",contactinfo);
        return "showpersiondetail";
    }

    //新增推送内容
    @RequestMapping("sendpersion")
    public String sendpersion(Model m,HttpServletRequest request){
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
        List<Contactinfo> contactinfos=contactinfoService.sendAllPersion();
        TaskplaninfoExample taskplaninfoExample=new TaskplaninfoExample();
        taskplaninfoExample.createCriteria().andStateNotEqualTo(-1).andSiteidIn(ids);
        List<Taskplaninfo> taskplaninfos=taskPlanService.selectByExample(taskplaninfoExample);

        m.addAttribute("taskplaninfos",taskplaninfos);
        m.addAttribute("contactinfos",contactinfos);
        return "addsendemail";
    }

    @RequestMapping("showsendname")
    @ResponseBody
    public List<Contactinfo>  showsendname(String contactid){
        List<Long> contactids=new ArrayList<>();
        List<String> names=new ArrayList<>();
        String [] stringArr= contactid.split(",");
        for(int i=0;i<stringArr.length;i++){
            contactids.add(Long.valueOf(stringArr[i]));
        }
        ContactinfoExample contactinfoExample=new ContactinfoExample();
        contactinfoExample.createCriteria().andIdIn(contactids);
        return contactinfoService.selectByExample(contactinfoExample);
    }
}
