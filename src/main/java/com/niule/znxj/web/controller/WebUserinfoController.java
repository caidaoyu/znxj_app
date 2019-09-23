package com.niule.znxj.web.controller;

import com.niule.znxj.core.util.PageBean;
import com.niule.znxj.web.dao.ClassinfoMapper;
import com.niule.znxj.web.model.*;
import com.niule.znxj.web.service.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
public class WebUserinfoController {
    @Resource
    private UserinfoService userinfoService;
    @Resource
    private ClassinfoMapper classinfoMapper;
    @Resource
    private PositioninfoService positioninfoService;
    @Resource
    private TerminalService terminalService;
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

    /**
     * 分页显示用户信息
     * @param m
     * @param page 当前页
     * @param realname  用户名称
     * @param type 用户类型
     * @param userstate 用户状态
     * @return
     */
    @RequestMapping("/showallusers")
    public String showallusers(Model m,int page,String realname,String type,String userstate,HttpServletRequest request){
        if(page<=0){
            page=1;
        }
        int pagesieze=15;
        Admininfo admininfo=(Admininfo) request.getSession().getAttribute("userInfo");

        List classids=new ArrayList() ;
        if(admininfo.getSiteid()==null){
            List<Siteareainfo> sites=siteService.queryAllSite();
            if(sites.size()>0){
                for(Siteareainfo siteareainfo:sites){
                    classids.add(siteareainfo.getId());
                }
            }else
                classids=null;

        }else{
            classids.add(admininfo.getSiteid());
        }

        HashMap<String,Object> map=new HashMap<String,Object>();
        map.put("page",(page-1)*pagesieze);
        map.put("pagesize",pagesieze);
        map.put("realname","%"+realname+"%");
        map.put("type",type);
        map.put("userstate",userstate);
        map.put("classids",classids);
        page = PageBean.countCurrentPage(page);
        List<Userinfo> userinfos=null;
        long totalpage=0;
        if((realname==null||"".equals(realname)) && (type==null || "".equals(type)) &&(userstate==null||"".equals(userstate))){
            userinfos = userinfoService.findByPageUser(map);
            int rows1 = userinfoService.countUser(map);
            totalpage = PageBean.counTotalPage(pagesieze, rows1);
            type="";
            userstate="";
        }else{
            userinfos = userinfoService.findByPageUser2(map);
            int rows2 = userinfoService.countUser2(map);
            totalpage = PageBean.counTotalPage(pagesieze, rows2);
        }
        pageBean.setList(userinfos);
        pageBean.setTotalPage((int) totalpage);
        pageBean.setCurrentPage(page);
        m.addAttribute("pageBean",pageBean);
        m.addAttribute("realname",realname);
        m.addAttribute("type",type);
        m.addAttribute("userstate",userstate);
        return "showuser";
    }

    /**
     * 添加新用户
     * @param userinfo 用户对象
     * @return
     */
    @RequestMapping("/adduser")
    public String adduser(Userinfo userinfo, HttpSession session){
        int addresult=userinfoService.insert(userinfo);
        String name="添加用户"+userinfo.getRealname()+"的信息";
        Admininfo logadmininfo=(Admininfo)session.getAttribute("userInfo");
        String username=logadmininfo.getUsername();
        int addlogres=operateLogService.insertSelective(username,name);
        if(addresult>0&addlogres>0){
            return "redirect:showallusers?page=1";
        }
        return "adduser";
    }

    /**
     * 跳转到添加用户页面
     * @param m
     * @return
     */
    @RequestMapping("toadd")
    @RequiresPermissions("add:user")
    public String toadd(Model m){
        List<Classinfo> classinfos=classinfoMapper.queryCustomid();
        List<Positioninfo> positioninfos=positioninfoService.selectByExample();
        List<Terminalinfo> terminalinfos=terminalService.selectByexample();
        List<Siteareainfo> siteareainfos=siteService.queryAllSite();

        m.addAttribute("classinfos",classinfos);
        m.addAttribute("positioninfos",positioninfos);
        m.addAttribute("terminalinfos",terminalinfos);
        m.addAttribute("siteareainfos",siteareainfos);
        return "adduser";
    }
    @RequestMapping("/deluser")
    @RequiresPermissions("del:user")
    @ResponseBody
    public int deluser(Long id, HttpServletRequest request){
        HttpSession session=request.getSession();
        String uname=request.getParameter("uname");
        int deluser= userinfoService.deleteByPrimaryKey(id);
        String name="删除用户"+uname+"的信息";
        Admininfo logadmininfo=(Admininfo)session.getAttribute("userInfo");
        String username=logadmininfo.getUsername();
        int addlogres=operateLogService.insertSelective(username,name);
        if(deluser>0&&addlogres>0){
            return 1;
        }else
            return 0;
    }
    @RequestMapping("/querybyUserId")
    @RequiresPermissions("upd:user")
    public String querybyUserId(Long id,Model m,int page){
        Userinfo userinfo=userinfoService.selectByPrimaryKey(id);
        userinfo.setClassid(userinfo.getGroup().getId());
        userinfo.setPositionid(userinfo.getPos().getId());
        List<Classinfo> classinfos=classinfoMapper.queryCustomid();
        List<Positioninfo> positioninfos=positioninfoService.selectByExample();
        List<Terminalinfo> terminalinfos=terminalService.selectByexample();
        List<Siteareainfo> siteareainfos=siteService.queryAllSite();
        Classinfo classinfo = classinfoMapper.selectByPrimaryKey(userinfo.getClassid());
        m.addAttribute("userinfo",userinfo);
        m.addAttribute("classinfo",classinfo);
        m.addAttribute("classinfos",classinfos);
        m.addAttribute("positioninfos",positioninfos);
        m.addAttribute("terminalinfos",terminalinfos);
        m.addAttribute("siteareainfos",siteareainfos);
        m.addAttribute("page",page);
        return "updateuser";
    }
    @RequestMapping("/upduser")
    public String upduser(Userinfo userinfo,HttpSession session,int page){
       int updresult=userinfoService.updateByPrimaryKeySelective(userinfo);
        String name="修改用户"+userinfo.getRealname()+"的信息";
        Admininfo logadmininfo=(Admininfo)session.getAttribute("userInfo");
        String username=logadmininfo.getUsername();
        int addlogres=operateLogService.insertSelective(username,name);
        if(addlogres>0&&updresult>0){
            return "redirect:showallusers?page="+page;
        }else
            return "taskfinalline";

    }
    @RequestMapping("/queryuserdetail")
    public String queryuserdetail(Long id,Model m){
        Userinfo userinfo=userinfoService.selectByPrimaryKey(id);

        m.addAttribute("userinfo",userinfo);
        return "showuserdetail";
    }

    /*@RequestMapping("isUserExist")
    @ResponseBody
    public int isUserExist(String userName,Long classid,Long id,Long oldClassId){
        int count  = userinfoService.isUserExist(userName,classid,id,oldClassId);
        if(count > 0 ){
            return 1;
        }
        return 0;
    }*/
    @RequestMapping("isUserExist")
    @ResponseBody
    public int isUserExist(String userName,Long id){
        int count  = userinfoService.isUserExist(userName,id);
        if(count > 0 ){
            return 1;
        }
        return 0;
    }
}
