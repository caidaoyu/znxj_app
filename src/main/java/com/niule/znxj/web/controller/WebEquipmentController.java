package com.niule.znxj.web.controller;

import com.niule.znxj.core.util.PageBean;
import com.niule.znxj.web.model.*;
import com.niule.znxj.web.service.*;
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
 * Created by administor on 2017/3/21.
 */
@Controller
public class WebEquipmentController {
    @Resource
    private EquipmentService equipmentService;
    @Resource
    private NfcService nfcService;
    @Resource
    private AreaService areaService;
    @Resource
    private OperateLogService operateLogService;
    @Resource
    private SiteService siteService;

    protected PageBean pageBean = new PageBean();

    public PageBean getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean pageBean) {
        this.pageBean = pageBean;
    }
    @RequestMapping("showallequipment")
    @RequiresPermissions("item:equip")
    public String showallequipment(int page, Model m,String name,HttpServletRequest request){
        if(page<=0){
            page=1;
        }
        int pagesieze=15;

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
        HashMap<String,Object> map0=new HashMap<String,Object>();
        map0.put("ids",ids);
        List<Areainfo> areainfos=areaService.sitearea(map0);
        List areaids=new ArrayList() ;
        if(areainfos.size()>0){
            for(Areainfo areainfo:areainfos){
                areaids.add(areainfo.getId());
            }
        }else
            areaids=null;
        map.put("page",(page-1)*pagesieze);
        map.put("pagesize",pagesieze);
        map.put("name","%"+name+"%");
        map.put("areaids",areaids);
        page = PageBean.countCurrentPage(page);
        List<Equipmentinfo> equipmentinfos;
        long totalpage =0;
        if(name==null||"".equals(name) ){
            equipmentinfos = equipmentService.findByPageEquipment(map);
            int rows1 = equipmentService.countEquipment(map);
            totalpage = PageBean.counTotalPage(pagesieze, rows1);
        }else{

            equipmentinfos = equipmentService.findByPageEquipment2(map);
            int rows2 = equipmentService.countEquipment2(map);
            totalpage = PageBean.counTotalPage(pagesieze, rows2);
        }
        pageBean.setList(equipmentinfos);
        pageBean.setTotalPage((int) totalpage);
        pageBean.setCurrentPage(page);
        m.addAttribute("pageBean",pageBean);
        m.addAttribute("name",name);
        return "showequipmentinfo";
    }
    @RequestMapping("toaddequip")
    @RequiresPermissions("add:equip")
    public String toaddequip(Model m,HttpServletRequest request){
        List<Nfcinfo> nfcinfos=nfcService.queryAllNfc();
        Admininfo admininfo=(Admininfo) request.getSession().getAttribute("userInfo");

        List<Siteareainfo> siteareainfos=siteService.selectByExample2(admininfo.getSiteid());
        List<Areainfo> areainfos=areaService.selectByExample1(admininfo.getSiteid());

        m.addAttribute("nfcinfos",nfcinfos);
        m.addAttribute("areainfos",areainfos);
        m.addAttribute("siteareainfos",siteareainfos);

        return "addequipment";
    }
    @RequestMapping("addequipment")
    public String addequipment(Equipmentinfo equipmentinfo, HttpSession session){
        int addresult=equipmentService.insert(equipmentinfo);

        int addresult2=0;
        if(equipmentinfo.getNfcid()!=null){
            Long equipmentinfoId=equipmentinfo.getId();
            Long nfcid=equipmentinfo.getNfcid();
            addresult2=nfcService.updateequipmentid(equipmentinfoId,nfcid);
        }else{
            addresult2=1;
        }
        String name="新增了设备"+equipmentinfo.getName();
        Admininfo logadmininfo=(Admininfo)session.getAttribute("userInfo");
        String username=logadmininfo.getUsername();
        int addlogres=operateLogService.insertSelective(username,name);
        if(addresult>0 && addresult2>0&&addlogres>0){
            return "redirect:showallequipment?page=1";
        }
        return "addequipment";
    }
    @RequestMapping("delequipment")
    @RequiresPermissions("del:equip")
    @ResponseBody
    public int delequipment(Long id, HttpServletRequest request){
        HttpSession session=request.getSession();
        String eqname=request.getParameter("eqname");
        int deleq= equipmentService.deleteByPrimaryKey(id);
        String name="删除了设备"+eqname;
        Admininfo logadmininfo=(Admininfo)session.getAttribute("userInfo");
        String username=logadmininfo.getUsername();
        int addlogres=operateLogService.insertSelective(username,name);
        if(deleq>0&&addlogres>0){
            return 1;
        }else
            return 0;
    }
    @RequestMapping("findbyequip")
    @RequiresPermissions("upd:equip")
    public String findbyequip(Long id,Model m,HttpServletRequest request,int page){
        Equipmentinfo equipmentinfo=equipmentService.selectByPrimaryKey(id);
        List<Nfcinfo> nfcinfos=nfcService.queryAllNfc();
        Admininfo admininfo=(Admininfo) request.getSession().getAttribute("userInfo");
        List<Siteareainfo> siteareainfos=siteService.selectByExample2(admininfo.getSiteid());
        List<Areainfo> areainfos=areaService.selectByExample1(admininfo.getSiteid());

        m.addAttribute("equipmentinfo",equipmentinfo);
        m.addAttribute("nfcinfos",nfcinfos);
        m.addAttribute("areainfos",areainfos);
        m.addAttribute("siteareainfos",siteareainfos);
        m.addAttribute("page",page);

        return "updateequipment";
    }

    @RequestMapping("updequipment")
    public String updequipment(Equipmentinfo equipmentinfo,HttpSession session,int page){
        int updresult2=0;
        Long equipmentid=equipmentinfo.getId();
        if(equipmentinfo.getNfcid()!=null){
            Long nfcid=equipmentinfo.getNfcid();/*
            nfcService.updatebyequipmentid(equipmentid);*/
            Nfcinfo nfc=nfcService.selectByPrimaryKey(nfcid);
            equipmentinfo.setNfc(nfc);
            updresult2=nfcService.updateequipmentid(equipmentid,nfcid);
        }else{
            nfcService.updatebyequipmentid(equipmentid);
            updresult2=1;
        }
        int updresult1=equipmentService.updateByPrimaryKeySelective(equipmentinfo);
        String name="修改了设备"+equipmentinfo.getName()+"的信息";
        Admininfo logadmininfo=(Admininfo)session.getAttribute("userInfo");
        String username=logadmininfo.getUsername();
        int addlogres=operateLogService.insertSelective(username,name);
        if(updresult1>0 && updresult2>0&&addlogres>0){
            return "redirect:showallequipment?page="+page;
        }
        return "redirect:findbyequip?id=" + equipmentid+"&page="+page;
    }
    @RequestMapping("queryequipdetail")
    /*@RequiresPermissions("item:equipdetail")*/
    public String queryequipdetail(Long id,Model m){
        Equipmentinfo equipmentinfo=equipmentService.selectByPrimaryKey(id);
        m.addAttribute("equipmentinfo",equipmentinfo);
        return "showequipmentdetail";
    }
    @RequestMapping("getequipcount")
    @ResponseBody
    public int getequipcount(Long areaid){
        return equipmentService.selectByExample1(areaid);
    }
}
