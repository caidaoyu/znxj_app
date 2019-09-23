package com.niule.znxj.web.controller;

import com.github.pagehelper.PageInfo;
import com.niule.znxj.web.dao.PowerMapper;
import com.niule.znxj.web.dao.RoleandpowerMapper;
import com.niule.znxj.web.dao.RolesandpromissMapper;
import com.niule.znxj.web.model.*;
import com.niule.znxj.web.model.response.RoleRes;
import com.niule.znxj.web.service.AdmininfoService;
import com.niule.znxj.web.service.OperateLogService;
import com.niule.znxj.web.service.PermissionService;
import com.niule.znxj.web.service.RolesService;
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
import java.util.Map;

/**
 * Created by administor on 2017/5/4.
 */
@Controller
public class WebRolesCtrl {
    @Resource
    private RolesService rolesService;
    @Resource
    private PermissionService permissionService;
    @Resource
    private PowerMapper powerMapper;
    @Resource
    private RoleandpowerMapper roleandpowerMapper;
    @Resource
    private RolesandpromissMapper rolesandpromissMapper;
    @Resource
    private AdmininfoService admininfoService;
    @Resource
    private OperateLogService operateLogService;
    @RequestMapping("showroles")
    @RequiresPermissions("item:roles")
    public String showroles( int page,Model m){
        int size=10;
        PageInfo info = new PageInfo<>(rolesService.selectByExample(page,size));
        List<Roles> roles = info.getList();
        List<RoleRes> res = new ArrayList<>();
        for(Roles role:roles){
            RoleRes item = new RoleRes();
            StringBuffer sb = new StringBuffer();
            item.setRoles(role);
            for(Power permission : powerMapper.selectByRoleid(role.getRoleid())){
                sb.append(permission.getPermissionname()+",");
            }
            if (sb.length() > 0)
                sb.deleteCharAt(sb.length() - 1);
            item.setPermissionstr(sb.toString());
            res.add(item);
        }
        info.setList(res);
        System.out.print(info);
        m.addAttribute("res",info);
        return "showroles";
    }
    @RequestMapping("toaddroles")
    @RequiresPermissions("add:roles")
    public String toaddroles(Model m){
        PowerExample powerExample=new PowerExample();
        List<Power> allpermission =powerMapper.selectByExample(powerExample);
        m.addAttribute("allpermission",allpermission);
        return "addroles2";
    }
    @RequestMapping("addroles")
    public String addroles(Roles roles, String selectedpowers, HttpSession session){
        int result=rolesService.insert(roles);

        int rows=0;
        if(selectedpowers!="" && selectedpowers!=null) {
            String[] selectedList = selectedpowers.split(",");
            for (int i = 0; i < selectedList.length; i++) {
                Roleandpower rolesandpromiss = new Roleandpower();
                rolesandpromiss.setRoleid(Long.valueOf(roles.getRoleid()));
                rolesandpromiss.setPersionid(selectedList[i]);
                rows += roleandpowerMapper.insert(rolesandpromiss);
            }
        }else{
            rows=1;
        }
//        Rolesandpromiss rolesandpromiss = new Rolesandpromiss();
//        rolesandpromiss.setRoleid(roles.getRoleid());
//        rolesandpromiss.setPromissid(1);
//        int res=rolesandpromissMapper.insert(rolesandpromiss);

        String info="添加角色"+roles.getRolename();
        Admininfo logadmininfo=(Admininfo)session.getAttribute("userInfo");
        String username=logadmininfo.getUsername();
        int addlog=operateLogService.insertSelective(username,info);
        if(result>0&&rows>0&addlog>0){
            return "redirect:showroles?page=1";
        }else
            return "taskfinalline";
    }
    @RequestMapping("getrolebyroleid")
//    @RequiresPermissions("upd:roles")
    public String getrolebyroleid(int roleid,Model m,int page){
        //根据角色编号查询当前角色
        Roles roles  = rolesService.selectByPrimaryKey(roleid);
        //根据角色编号查询当前角色的所有权限
        List<Power> permissionlist = powerMapper.selectByRoleid(roleid);

        //查询出所有权限
        PowerExample powerExample=new PowerExample();
        List<Power> allpermission =powerMapper.selectByExample(powerExample);

        List<OwnPower> userPermission = new ArrayList<>();

        Map<String,Power> powerMap = new HashMap<>();

        for(Power p:permissionlist){
            powerMap.put(p.getPersionid(),p);
        }

        for(Power item:allpermission){
            OwnPower power = new OwnPower(item);
            if(powerMap.containsKey(power.getPower().getPersionid()))
                power.setHasPower(true);
            userPermission.add(power);
        }


        m.addAttribute("roles",roles);
        m.addAttribute("page",page);
        m.addAttribute("userPermission", userPermission);

        return "updrole2";
    }
    @RequestMapping("updroles")
    public String updroles(Roles roles,String selectedpowers,HttpSession session,int page){
        int updresult=rolesService.updateByPrimaryKeySelective(roles);
        int rows=0;
        if(selectedpowers!="" && selectedpowers!=null) {
            String[] selectedList = selectedpowers.split(",");
            //删除角色权限表中的信息
            RoleandpowerExample example = new RoleandpowerExample();
            example.createCriteria().andRoleidEqualTo(Long.valueOf(roles.getRoleid()));
            roleandpowerMapper.deleteByExample(example);
            for (int i = 0; i < selectedList.length; i++) {
                Roleandpower roleandpower = new Roleandpower();
                roleandpower.setRoleid(Long.valueOf(roles.getRoleid()));
                roleandpower.setPersionid(selectedList[i]);
                rows += roleandpowerMapper.insert(roleandpower);
            }
        }else{
            //删除角色权限表中的信息
            RoleandpowerExample example = new RoleandpowerExample();
            example.createCriteria().andRoleidEqualTo(Long.valueOf(roles.getRoleid()));
            roleandpowerMapper.deleteByExample(example);
            rows=1;
        }
        String info="修改角色"+roles.getRolename();
        Admininfo logadmininfo=(Admininfo)session.getAttribute("userInfo");
        String username=logadmininfo.getUsername();
        int addlog=operateLogService.insertSelective(username,info);

        if (rows > 0&&updresult>0&&addlog>0) {
            return "redirect:showroles?page="+page;
        } else {
            return "taskfinalline";
        }

    }
    @RequestMapping("admincount")
    @ResponseBody
    public int admincount(int roleid){
        return admininfoService.admincount(roleid);
    }
    @RequestMapping("delrole")
    @ResponseBody
    public int delrole(int id, HttpServletRequest request){
        String rname=request.getParameter("rname");
        HttpSession session=request.getSession();
        int del1=rolesService.deleteByPrimaryKey(id);
        RoleandpowerExample example = new RoleandpowerExample();
        example.createCriteria().andRoleidEqualTo(Long.valueOf(id));
        int del2=roleandpowerMapper.deleteByExample(example);
        String info="删除了角色"+rname;
        Admininfo logadmininfo=(Admininfo)session.getAttribute("userInfo");
        String username=logadmininfo.getUsername();
        int addlog=operateLogService.insertSelective(username,info);
        if(del1>0&&addlog>0){
            return 1;
        }else
            return 0;
    }
}
