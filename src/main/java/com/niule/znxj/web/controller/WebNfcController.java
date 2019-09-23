package com.niule.znxj.web.controller;

import com.github.pagehelper.PageInfo;
import com.niule.znxj.core.util.PageBean;
import com.niule.znxj.web.model.Admininfo;
import com.niule.znxj.web.model.Areainfo;
import com.niule.znxj.web.model.Nfcinfo;
import com.niule.znxj.web.model.Operatelog;
import com.niule.znxj.web.service.NfcService;
import com.niule.znxj.web.service.OperateLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
 * Created by administor on 2017/3/21.
 */
@Controller
public class WebNfcController {
    @Resource
    private NfcService nfcService;
    @Resource
    private OperateLogService operateLogService;
    protected PageBean pageBean = new PageBean();

    public PageBean getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean pageBean) {
        this.pageBean = pageBean;
    }
    @RequestMapping("showallnfc")
    @RequiresPermissions("item:nfc")
    public String showallnfc(int page, Model m,String customid,HttpServletRequest request){
        if(page<=0){
            page=1;
        }
        int pagesieze=15;
        HashMap<String,Object> map=new HashMap<String,Object>();
        map.put("page",page);
        map.put("pagesize",pagesieze);
        map.put("customid",customid==null?"":"%"+customid+"%");
        Admininfo admininfo=(Admininfo) request.getSession().getAttribute("userInfo");
        map.put("siteid",admininfo.getSiteid());
        page = PageBean.countCurrentPage(page);
        List<Nfcinfo> nfcinfos=null ;
        nfcinfos = nfcService.findByPageNfc2(map);
        pageBean.setCurrentPage(page);
        m.addAttribute("pageBean",new PageInfo<Nfcinfo>(nfcinfos));
        m.addAttribute("customid",customid);
        return "shownfcinfo";
    }
    @RequestMapping("addnfc")
    @RequiresPermissions("add:nfc")
    public String addnfc(Nfcinfo nfcinfo, HttpSession session){
        int addresult=nfcService.insert(nfcinfo);
        String name="添加了nfc"+nfcinfo.getCustomid()+"的信息";
        Admininfo logadmininfo=(Admininfo)session.getAttribute("userInfo");
        String username=logadmininfo.getUsername();
        int addlogres=operateLogService.insertSelective(username,name);
        if(addresult>0&&addlogres>0){
            return "redirect:showallnfc?page=1";
        }
        return "addnfcinfo";
    }
    @RequestMapping("delnfc")
    @RequiresPermissions("del:nfc")
    @ResponseBody
    public int delnfc(Long id, HttpServletRequest request){
        String nfc=request.getParameter("nfc");
        String info ="删除了nfc"+nfc+"的信息";
       int delnfc= nfcService.deleteByPrimaryKey(id);
        HttpSession session=request.getSession();
        Admininfo logadmininfo=(Admininfo)session.getAttribute("userInfo");
        String username=logadmininfo.getUsername();
        int addlog=operateLogService.insertSelective(username,info);
        if(delnfc>0&&addlog>0){
            return 1;
        }else
            return 0;
    }
    @RequestMapping("querybynfcid")
    @RequiresPermissions("upd:nfc")
    public String querybynfcid(Long id,Model m,int page){
        Nfcinfo nfcinfo=nfcService.selectByPrimaryKey(id);
        m.addAttribute("nfcinfo",nfcinfo);
        m.addAttribute("page",page);
        return "updatenfcinfo";
    }
    @RequestMapping("updatenfc")
    public String updatenfc(Nfcinfo nfcinfo,HttpSession session,int page){
        int updresult=nfcService.updateByPrimaryKeySelective(nfcinfo);
        String name="修改了nfc"+nfcinfo.getCustomid()+"的信息";
        Admininfo logadmininfo=(Admininfo)session.getAttribute("userInfo");
        String username=logadmininfo.getUsername();
        int addlogres=operateLogService.insertSelective(username,name);
        if(updresult>0&&addlogres>0){
           return "redirect:showallnfc?page="+page;
       }
        return "updatenfcinfo";
    }
    @RequestMapping("querynfcdetail")
 /*   @RequiresPermissions("item:nfcdetail")*/
    public String querynfcdetail(Long id,Model m){
        Nfcinfo nfcinfo=nfcService.selectByPrimaryKey(id);
        m.addAttribute("nfcinfo",nfcinfo);
        return "shownfcinfodetail";
    }
}
