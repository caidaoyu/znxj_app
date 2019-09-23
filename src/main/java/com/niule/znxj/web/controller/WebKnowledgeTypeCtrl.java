package com.niule.znxj.web.controller;

import com.github.pagehelper.PageInfo;
import com.niule.znxj.web.model.Admininfo;
import com.niule.znxj.web.model.Knowledgetype;
import com.niule.znxj.web.service.KonwledgeTypeService;
import com.niule.znxj.web.service.OperateLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by administor on 2017/5/8.
 */
@Controller
public class WebKnowledgeTypeCtrl {
    @Resource
    private KonwledgeTypeService konwledgeTypeService;
    @Resource
    private OperateLogService operateLogService;

    @RequestMapping("showknowledgetype")
    @RequiresPermissions("item:knowtype")
    public  String showknowledgetype(int page, Model m){
        int size=15;
        List<Knowledgetype> knowtypes=null;
        PageInfo info = new PageInfo<>(konwledgeTypeService.selectByExample(page,size));
        m.addAttribute("info",info);
        return "knowledge/showknowledgetype";
    }
    @RequestMapping("/addknowledgetype")
    @RequiresPermissions("add:knowtype")
    public String addknowledgetype(Knowledgetype knowledgetype, HttpSession session){
        int result=konwledgeTypeService.insert(knowledgetype);
        String info="添加了知识类型"+knowledgetype.getTypename()+"的基本信息";
        Admininfo logadmininfo=(Admininfo)session.getAttribute("userInfo");
        String username=logadmininfo.getUsername();
        int addlog=operateLogService.insertSelective(username,info);
        if(result>0&&addlog>0){
            return "redirect:/showknowledgetype?page=1";
        }else
            return "taskfinalline";
    }
    @RequestMapping("/delknowledgetype")
    @RequiresPermissions("del:knowtype")
    @ResponseBody
    public int delknowledgetype(Integer typeid, HttpServletRequest request){
        HttpSession session=request.getSession();
        String name=request.getParameter("typename");
       int del= konwledgeTypeService.deleteByPrimaryKey(typeid);
        String info="删除了知识类型"+name+"的基本信息";
        Admininfo logadmininfo=(Admininfo)session.getAttribute("userInfo");
        String username=logadmininfo.getUsername();
        int addlog=operateLogService.insertSelective(username,info);
        if(del>0&&addlog>0){
            return 1;
        }else
            return 0;
    }
    @RequestMapping("getknowledgetypebyid")
    @RequiresPermissions("upd:knowtype")
    public String getknowledgetypebyid(Integer typeid,Model m){
        Knowledgetype knowledgetype=konwledgeTypeService.selectByPrimaryKey(typeid);
        m.addAttribute("knowledgetype",knowledgetype);
        return "knowledge/updknowledgetype";
    }
    @RequestMapping("/updknowledgetype")
    public String updknowledgetype(Knowledgetype knowledgetype,HttpSession session){
        int result=konwledgeTypeService.updateByPrimaryKeySelective(knowledgetype);
        String info="修改了知识类型"+knowledgetype.getTypename()+"的基本信息";
        Admininfo logadmininfo=(Admininfo)session.getAttribute("userInfo");
        String username=logadmininfo.getUsername();
        int addlog=operateLogService.insertSelective(username,info);
        if(result>0&&addlog>0){
            return "redirect:/showknowledgetype?page=1";
        }else
            return "taskfinalline";
    }
}
