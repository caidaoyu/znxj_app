package com.niule.znxj.web.controller;

import com.niule.znxj.core.util.PageBean;
import com.niule.znxj.web.model.*;
import com.niule.znxj.web.service.DateRecordService;
import com.niule.znxj.web.service.OperateLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.web.session.HttpServletSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by administor on 2017/3/27.
 */
@Controller
public class WebDateRecordController {
    @Resource
    private DateRecordService dateRecordService;
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
     * 显示所有可读数据信息
     * @param m
     * @return
     */

    @RequestMapping("/showdaterecord")
    @RequiresPermissions("item:data")
    public String  showdaterecord(int page,String name,Model m) {
        if(page<=0){
            page=1;
        }
        int pagesieze=10;
        page = PageBean.countCurrentPage(page);
        List<Daterecordinfo> daterecordinfos =null;
        long totalpage=0;
        if(name==null||"".equals(name) ){
            daterecordinfos = dateRecordService.findByPageData(page,pagesieze);
            int rows1 =dateRecordService.countData();
            totalpage = PageBean.counTotalPage(pagesieze, rows1);
        }else{
            HashMap<String,Object> map=new HashMap<String,Object>();
            map.put("page",(page-1)*pagesieze);
            map.put("pagesize",pagesieze);
            map.put("name","%"+name+"%");
            daterecordinfos = dateRecordService.findByPageData2(map);
            int rows2 = dateRecordService.countData2(map);
            totalpage = PageBean.counTotalPage(pagesieze, rows2);
        }
        pageBean.setList(daterecordinfos);
        pageBean.setTotalPage((int) totalpage);
        pageBean.setCurrentPage(page);
        m.addAttribute("pageBean",pageBean);
        m.addAttribute("name",name);
        return "showdaterecord";
    }
   /* @RequestMapping("addrecord")
    @RequiresPermissions("add:data")
    public String addrecord(Daterecordinfo daterecordinfo,HttpSession session){
        daterecordinfo.setUnitname(null);
        int addresult=dateRecordService.insert(daterecordinfo);
        String name="新增了可读数据"+daterecordinfo.getName();
        Admininfo logadmininfo=(Admininfo)session.getAttribute("userInfo");
        String username=logadmininfo.getUsername();
        int addlog=operateLogService.insertSelective(username,name);
        if(addresult>0&&addlog>0){
            return "redirect:showdaterecord?page=1";
        }
        return "adddaterecord";
    }*/
   @RequestMapping("addrecord")
   @RequiresPermissions("add:data")
   @ResponseBody
   public int addrecord(Daterecordinfo daterecordinfo,HttpSession session){
       if(daterecordinfo.getRecordtype() == 2){
           daterecordinfo.setUnitname(null);
       }
       int addresult=dateRecordService.insert(daterecordinfo);
       String name="新增了可读数据"+daterecordinfo.getName();
       Admininfo logadmininfo=(Admininfo)session.getAttribute("userInfo");
       String username=logadmininfo.getUsername();
       int addlog=operateLogService.insertSelective(username,name);
       if(addresult>0&&addlog>0){
           return 1;
       }
       return 0;
   }

    @RequestMapping("isNameExist")
    @ResponseBody
    public String  isNameExist(String dateName,Integer id){
        int count  = dateRecordService.isNameExist(dateName,id);
        if(count > 0 ){
            return  "1";
        }
        return "0";
    }

    @RequestMapping("queryRecordByType")
    @ResponseBody
    public List<Daterecordinfo> queryRecordByType(Integer recordType){
        List<Daterecordinfo> daterecordinfos=dateRecordService.selectByExample(recordType);
        return daterecordinfos;
    }
    @RequestMapping("delrecord")
    @RequiresPermissions("del:data")
    @ResponseBody
    public int delrecord(int id, HttpServletRequest request){
        String dataname=request.getParameter("dataname");
        //删除管理员
        int deladata=dateRecordService.deleteByPrimaryKey(id);
        //添加操作日志
        HttpSession session=request.getSession();
        String info="删除了可读数据"+dataname+"的信息";
        Admininfo logadmininfo=(Admininfo)session.getAttribute("userInfo");
        String username=logadmininfo.getUsername();
        int addlog=operateLogService.insertSelective(username,info);
        if(deladata>0&&addlog>0){
            return 1;
        }else
            return 0;
    }
    @RequestMapping("querybydateid")
    @RequiresPermissions("upd:data")
    public String querybydateid(int id,Model m,int page){
        Daterecordinfo daterecordinfo=dateRecordService.selectByPrimaryKey(id);
        m.addAttribute("page",page);
        if(daterecordinfo!=null){
            m.addAttribute("daterecordinfo",daterecordinfo);
            return "updatedaterecord";
        }
        return "redirect:showdaterecord?page="+page;
    }

    @RequestMapping("updrecord")
    @ResponseBody
    public int updrecord(Daterecordinfo daterecordinfo, HttpSession session,int page){
        int updresult=dateRecordService.updateByPrimaryKeySelective(daterecordinfo);
        String info="修改了可读数据"+daterecordinfo.getName();
        Admininfo logadmininfo=(Admininfo)session.getAttribute("userInfo");
        String username=logadmininfo.getUsername();
        int addlog=operateLogService.insertSelective(username,info);
        if(updresult>0&&addlog>0){
            return 1;
        }
        return 0;
    }
    /*@RequestMapping("updrecord")
    public String updrecord(Daterecordinfo daterecordinfo, HttpSession session,int page){
        int updresult=dateRecordService.updateByPrimaryKeySelective(daterecordinfo);
        String info="修改了可读数据"+daterecordinfo.getName();
        Admininfo logadmininfo=(Admininfo)session.getAttribute("userInfo");
        String username=logadmininfo.getUsername();
        int addlog=operateLogService.insertSelective(username,info);
        if(updresult>0&&addlog>0){
            return "redirect:showdaterecord?page="+page;
        }
        return "redirect:querybydateid?id="+daterecordinfo.getId();
    }*/
}
