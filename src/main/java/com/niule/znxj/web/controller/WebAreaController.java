package com.niule.znxj.web.controller;

import com.niule.znxj.core.util.PageBean;
import com.niule.znxj.web.model.*;
import com.niule.znxj.web.service.AreaService;
import com.niule.znxj.web.service.NfcService;
import com.niule.znxj.web.service.OperateLogService;
import com.niule.znxj.web.service.SiteService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by administor on 2017/3/21.
 */
@Controller
public class WebAreaController {
    @Resource
    private AreaService areaService;
    @Resource
    private NfcService nfcService;
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

    /**
     * 显示区域列表
     * @param page 当前页
     * @param name 区域名
     * @param m
     * @param request
     * @return
     */
    @RequestMapping("showallarea")
    @RequiresPermissions("item:area")
    public String showallarea(int page, String name, Model m, HttpServletRequest request){
        if(page<=0){
            page=1;
        }
        int pagesieze=15;
        page = PageBean.countCurrentPage(page);
        List<Areainfo> areainfos=null;
        long totalpage=0;

        Admininfo admininfo=(Admininfo) request.getSession().getAttribute("userInfo");
        List ids=new ArrayList() ;
        if(admininfo.getSiteid()==null){
            List<Siteareainfo> sites=siteService.queryAllSite();
            if(sites.size()>0){
                for(Siteareainfo siteareainfo:sites){
                    ids.add(siteareainfo.getId());
                }
            }else{
                ids=null;
            }
        }else{
            ids.add(admininfo.getSiteid());
        }
        HashMap<String,Object> map=new HashMap<String,Object>();
        map.put("page",(page-1)*pagesieze);
        map.put("size",pagesieze);
        map.put("ids",ids);
        map.put("name","%"+name+"%");

        if(name==null||"".equals(name) ){
            areainfos = areaService.findByPageArea(map);
            int rows1 = areaService.countArea(map);
            totalpage = PageBean.counTotalPage(pagesieze, rows1);
        }else{
            areainfos = areaService.findByPageArea2(map);
            int rows2 = areaService.countArea2(map);
            totalpage = PageBean.counTotalPage(pagesieze, rows2);
        }
        pageBean.setList(areainfos);
        pageBean.setTotalPage((int) totalpage);
        pageBean.setCurrentPage(page);
        m.addAttribute("pageBean",pageBean);
        m.addAttribute("name",name);
        return "showarea";
    }

    /**
     * 跳转到添加区域页面
     * @param m
     * @param request
     * @return
     */
    @RequestMapping("toaddarea")
//    @RequiresPermissions("add:area")
    public String toaddarea(Model m,HttpServletRequest request){
        List<Nfcinfo> nfcinfos=nfcService.queryAllNfc();
        Admininfo admininfo=(Admininfo) request.getSession().getAttribute("userInfo");
        List<Siteareainfo> siteareainfos=siteService.selectByExample2(admininfo.getSiteid());

        m.addAttribute("nfcinfos",nfcinfos);
        m.addAttribute("siteareainfos",siteareainfos);
        return "addarea";
    }

    /**
     * 添加区域
     * @param areainfo
     * @param session
     * @return
     */
    @RequestMapping("addarea")
    public String addarea(Areainfo areainfo, HttpSession session){
        //添加区域
        int addresult=areaService.insert(areainfo);
        int addresult2=0;
        if(areainfo.getNfctag()!=null){
            Long areaid=areainfo.getId();
            Long nfcid=areainfo.getNfctag();
            addresult2=nfcService.updateareaid(areaid,nfcid);
        }else{
            addresult2=1;
        }
        //获取登录用户的信息

        String info="新增了区域"+areainfo.getCustomid();
        Admininfo logadmininfo=(Admininfo)session.getAttribute("userInfo");
        String username=logadmininfo.getUsername();
        int addlog=operateLogService.insertSelective(username,info);

        if(addresult>0&&addresult2>0&&addlog>0){
            return "redirect:showallarea?page=1";
        }
        return "addarea";
    }

    /**
     * 删除区域
     * @param id 区域ID
     * @param request
     * @param session
     * @return
     */
    @RequestMapping("delarea")
    @RequiresPermissions("del:area")
    @ResponseBody
    public int delarea(Long id,HttpServletRequest request,HttpSession session){
        int delarea=areaService.deleteByPrimaryKey(id);
        //获取登录用户的信息
        String customid =request.getParameter("name");
        String info="删除了区域"+customid;
        Admininfo logadmininfo=(Admininfo)session.getAttribute("userInfo");
        String username=logadmininfo.getUsername();
        int addlog=operateLogService.insertSelective(username,info);
        if(delarea>0&&addlog>0){
            return 1;
        }else
            return 0;
    }
    @RequestMapping("addnfcajax")
    @ResponseBody
    public int addnfcajax(Nfcinfo nfcinfo){
        return nfcService.insert(nfcinfo);
    }
    @RequestMapping("shownfcjax")
    @ResponseBody
    public List<Nfcinfo> shownfcjax(){
        return nfcService.queryAllNfc();
    }
    @RequestMapping("findbyareaid")
    @RequiresPermissions("upd:area")
    public String findbyareaid(Long id,Model m,HttpServletRequest request,int page){
        Areainfo areainfo=areaService.selectByPrimaryKey(id);
        List<Nfcinfo> nfcinfos=nfcService.queryAllNfc();
        Admininfo admininfo=(Admininfo) request.getSession().getAttribute("userInfo");
        List<Siteareainfo> siteareainfos=siteService.selectByExample2(admininfo.getSiteid());

        m.addAttribute("nfcinfos",nfcinfos);
        m.addAttribute("siteareainfos",siteareainfos);
        m.addAttribute("areainfo",areainfo);
        m.addAttribute("page",page);
        return "updatearea";
    }
    @RequestMapping("updarea")
    public String updarea(Areainfo areainfo, Model model,HttpSession session,int page){
        int updresult2=0;
        Long areaid=areainfo.getId();
        if(areainfo.getNfctag()!=null){
            Long nfcid=areainfo.getNfctag();/*
            nfcService.updatebyareaid(areaid);*/
            Nfcinfo nfc=nfcService.selectByPrimaryKey(nfcid);
            areainfo.setNfc(nfc);
            updresult2=nfcService.updateareaid(areaid,nfcid);
        }else{
            nfcService.updatebyareaid(areaid);
            updresult2=1;
        }
        int updresult1=areaService.updateByPrimaryKeySelective(areainfo);

        //获取登录用户的信息

        String info="修改了区域"+areainfo.getCustomid();
        Admininfo logadmininfo=(Admininfo)session.getAttribute("userInfo");
        String username=logadmininfo.getUsername();
        int addlog=operateLogService.insertSelective(username,info);
        if(updresult1>0 && updresult2>0&&addlog>0){
            return "redirect:showallarea?page="+page;
        }
        return "redirect:findbyareaid?id="+ areaid+"&page="+page;
    }
    @RequestMapping("queryareadetail")/*
    @RequiresPermissions("item:areadetail")*/
    public String queryareadetail(Long id,Model m){
        Areainfo areainfo=areaService.selectByPrimaryKey(id);
        m.addAttribute("areainfo",areainfo);
        return "showareadetail";
    }
}
