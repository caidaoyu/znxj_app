package com.niule.znxj.web.controller;

import com.github.pagehelper.PageInfo;
import com.niule.znxj.core.util.PageBean;
import com.niule.znxj.web.model.Admininfo;
import com.niule.znxj.web.model.Siteareainfo;
import com.niule.znxj.web.service.OperateLogService;
import com.niule.znxj.web.service.SiteService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

/**
 * Created by administor on 2017/3/24.
 */
@Controller
public class WebSiteController {
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
    @RequestMapping("/showsite")
    @RequiresPermissions("item:site")
    public String  showsite(int page,Model model,HttpServletRequest request) {
        if(page<=0){
            page=1;
        }
        int size=15;
        HashMap<String,Object> map=new HashMap<>();
        Admininfo admininfo=(Admininfo) request.getSession().getAttribute("userInfo");
        List<Siteareainfo> siteareainfos = siteService.queryAllSite2(page,size);
        model.addAttribute("pageBean",new PageInfo<Siteareainfo>(siteareainfos));
        model.addAttribute("siteid",admininfo.getSiteid());
        return "showsitearea";
    }
    @RequestMapping("addsite")
    @RequiresPermissions("add:site")
    public String addsite(Siteareainfo siteareainfo, HttpSession session){
        int addresult=siteService.insert(siteareainfo);
        String info="添加了厂区"+siteareainfo.getCustomid();
        Admininfo logadmininfo=(Admininfo)session.getAttribute("userInfo");
        String username=logadmininfo.getUsername();
        int addlog=operateLogService.insertSelective(username,info);
        if(addresult>0&&addlog>0){
            return "redirect:showsite?page=1";
        }
        return "addsite";
    }
    @RequestMapping("delsite")
    @RequiresPermissions("del:site")
    @ResponseBody
    public int delsite(Long id, HttpServletRequest request){
        HttpSession session=request.getSession();
        String sitename=request.getParameter("sitename");
        int delsite= siteService.deleteByPrimaryKey(id);
        String info="删除了厂区"+sitename;
        Admininfo logadmininfo=(Admininfo)session.getAttribute("userInfo");
        String username=logadmininfo.getUsername();
        int addlog=operateLogService.insertSelective(username,info);
        if(delsite>0&&addlog>0){
            return 1;
        }else
            return 0;
    }
    @RequestMapping("findbysiteid")
    @RequiresPermissions("upd:site")
    public String findbysiteid(Long id,Model m,int page){
        Siteareainfo siteareainfo=siteService.selectByPrimaryKey(id);
        m.addAttribute("siteareainfo",siteareainfo);
        m.addAttribute("page",page);
        return "updatesitearea";
    }
    @RequestMapping("updsite")
    public String updsite(Siteareainfo siteareainfo,HttpSession session,int page){
        Long siteid=siteareainfo.getId();
        int updresult=siteService.updateByPrimaryKeySelective(siteareainfo);
        String info="修改了厂区"+siteareainfo.getCustomid();
        Admininfo logadmininfo=(Admininfo)session.getAttribute("userInfo");
        String username=logadmininfo.getUsername();
        int addlog=operateLogService.insertSelective(username,info);
        if(updresult>0&&addlog>0){
            return "redirect:showsite?page="+page;
        }
        return "redirect:findbysiteid?id="+siteid;
    }
    @RequestMapping("querysitedetail")
   /* @RequiresPermissions("item:sitedetail")*/
    public String querysitedetail(Long id,Model m){
        Siteareainfo siteareainfo=siteService.selectByPrimaryKey(id);
        m.addAttribute("siteareainfo",siteareainfo);
        return "showsitedetail";
    }

    /**
     * 判断是否已经存在厂区
     * @param customName
     * @param id
     * @return
     */
    @RequestMapping("isSiteExist")
    @ResponseBody
    public String  isSiteExist(String customName,Long id){
        int count  = siteService.isSiteExist(customName,id);
        if(count > 0 ){
            return  "1";
        }
        return "0";
    }

}
