package com.niule.znxj.web.controller;

import com.niule.znxj.web.dao.RolesandpromissMapper;
import com.niule.znxj.web.model.Permission;
import com.niule.znxj.web.model.Roles;
import com.niule.znxj.web.model.Rolesandpromiss;
import com.niule.znxj.web.model.RolesandpromissExample;
import com.niule.znxj.web.service.PermissionService;
import com.niule.znxj.web.service.RolesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by administor on 2017/5/4.
 */
@Controller
public class WebPermissionCtrl {
    @Resource
    private PermissionService permissionService;
    @Resource
    private RolesService rolesService;
    @Resource
    private RolesandpromissMapper rolesandpromissMapper;

    @RequestMapping("showpromiss")
    public String showpromission(int roleid, Model m){
        //根据角色编号查询当前角色
        Roles roles  = rolesService.selectByPrimaryKey(roleid);
        //根据角色编号查询当前角色的所有权限
        List<Permission> permissionlist = permissionService.selectByRoleid(roleid);
        //查询出所有权限
        List<Permission> allpermission =permissionService.selectByExample();

        m.addAttribute("roles",roles);
        m.addAttribute("allpermission", allpermission);
        m.addAttribute("permissionlist", permissionlist);

        return "updpermission";
    }

    @RequestMapping("addpermission")
    public String addpermission(int roleid,String selectedpowers){
        int rows=0;
        if(selectedpowers!="" && selectedpowers!=null) {
            String[] selectedList = selectedpowers.split(",");
            //删除角色权限表中的信息
            RolesandpromissExample example = new RolesandpromissExample();
            example.createCriteria().andRoleidEqualTo(roleid);
            rolesandpromissMapper.deleteByExample(example);
            for (int i = 0; i < selectedList.length; i++) {
                Rolesandpromiss rolesandpromiss = new Rolesandpromiss();
                rolesandpromiss.setRoleid(roleid);
                rolesandpromiss.setPromissid(Integer.parseInt(selectedList[i]));
                rows += rolesandpromissMapper.insert(rolesandpromiss);
            }
            if (rows > 0) {
                return "redirect:showroles?page=1";
            } else {
                return "taskfinalline";
            }
        }
        //删除角色权限表中的信息
        RolesandpromissExample example = new RolesandpromissExample();
        example.createCriteria().andRoleidEqualTo(roleid);
        rolesandpromissMapper.deleteByExample(example);
        return "redirect:showroles?page=1";
    }
}
