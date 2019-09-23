package com.niule.znxj.web.controller;

import com.niule.znxj.core.util.PageBean;
import com.niule.znxj.web.model.Admininfo;
import com.niule.znxj.web.model.Positioninfo;
import com.niule.znxj.web.service.AdmininfoService;
import com.niule.znxj.web.service.OperateLogService;
import com.niule.znxj.web.service.PositioninfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class WebPositioninfoController {
    @Resource
    private PositioninfoService positioninfoService;
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
     * 显示所有职位信息
     * @param model
     * @return
     */

    @RequestMapping("/showposition")
    @RequiresPermissions("item:pos")
    public String  showposition(int page,Model model) {
        if(page<=0){
            page=1;
        }
        page = PageBean.countCurrentPage(page);
        List<Positioninfo> positioninfos = positioninfoService.findByPagePos(page,15);
        int rows = positioninfoService.countPos();
        long totalpage = PageBean.counTotalPage(5, rows);

        pageBean.setList(positioninfos);
        pageBean.setTotalPage((int) totalpage);
        pageBean.setCurrentPage(page);
        model.addAttribute("pageBean",pageBean);
        return "showpos";
    }
    /*
     * 添加新的职位信息
     * @param model
     * @param positioninfo
     * @return
    */

    @RequestMapping("/addpos")
    @RequiresPermissions("add:pos")
    public String addpos(Positioninfo positioninfo,Model model,HttpSession session) {
        int addresult=positioninfoService.insert(positioninfo);
        String name="添加岗位"+positioninfo.getPositionname()+"的信息";
        Admininfo logadmininfo=(Admininfo)session.getAttribute("userInfo");
        String username=logadmininfo.getUsername();
        int addlogres=operateLogService.insertSelective(username,name);
        if(addresult>0&&addlogres>0){
            return "redirect:showposition?page=1";
        }else
            return "addpos";
    }
    /*
     * 删除职位
     * @param id
     * @return
    */

    @RequestMapping("/delpos")
    @ResponseBody
    @RequiresPermissions("del:pos")
    public int delpos(Long id,HttpServletRequest request) {
        int delpos= positioninfoService.deleteByPrimaryKey(id);
        String posname=request.getParameter("posname");
        String name="删除了岗位"+posname+"的信息";
        HttpSession session=request.getSession();
        Admininfo logadmininfo=(Admininfo)session.getAttribute("userInfo");
        String username=logadmininfo.getUsername();
        int addlogres=operateLogService.insertSelective(username,name);
        if(delpos>0&&addlogres>0){
            return 1;
        }else
            return  0;
    }
    /*
     * 查询职位的详细资料
     * @param id
     * @param m
     * @return
    */

    @RequestMapping("/selectbyposid")
    @RequiresPermissions("upd:pos")
    public String selectbyposid(Long id,Model m,int page){
        Positioninfo positioninfo=positioninfoService.selectByPrimaryKey(id);
        m.addAttribute("page",page);
        if(positioninfo!=null)
            m.addAttribute("positioninfo",positioninfo);
        return "updatepos";
    }
    @RequestMapping("/updpos")
    public String updpos(Positioninfo positioninfo,HttpSession session,int page){
        int updresult=positioninfoService.updateByPrimaryKey(positioninfo);
        String name="修改了岗位"+positioninfo.getPositionname()+"的信息";
        Admininfo logadmininfo=(Admininfo)session.getAttribute("userInfo");
        String username=logadmininfo.getUsername();
        int addlogres=operateLogService.insertSelective(username,name);
        if(updresult>0&&addlogres>0){
            return "redirect:showposition?page="+page;
        }
        return "updatepos";
    }
    @RequestMapping("/queryposdetailbyid")/*
    @RequiresPermissions("item:posdetail")*/
    public String queryposdetailbyid(Long id,Model m){
        Positioninfo p=positioninfoService.selectByPrimaryKey(id);
        if(p!=null)
            m.addAttribute("p",p);
        return "showposdetail";
    }
}
