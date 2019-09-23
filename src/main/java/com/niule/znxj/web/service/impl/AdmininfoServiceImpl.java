package com.niule.znxj.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.niule.znxj.core.entity.JSONResult;
import com.niule.znxj.core.entity.Result;
import com.niule.znxj.web.dao.AdmininfoMapper;
import com.niule.znxj.web.dao.PowerMapper;
import com.niule.znxj.web.model.Admininfo;
import com.niule.znxj.web.model.AdmininfoExample;
import com.niule.znxj.web.model.PositioninfoExample;
import com.niule.znxj.web.model.Power;
import com.niule.znxj.web.service.AdmininfoService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by administor on 2017/3/16.
 */
@Service
public class AdmininfoServiceImpl implements AdmininfoService{
    @Resource
    private AdmininfoMapper admininfoMapper;
    @Resource
    private PowerMapper powerMapper;

   /* @Override
    public List<Admininfo> findByPage(int page, int pagesize) {
        return admininfoMapper.findByPage((page-1)*pagesize,pagesize);
    }*/


    @Override
    public int admincount(int roleid) {
        return admininfoMapper.admincount(roleid);
    }

    @Override
    public PageInfo<Admininfo> selectByExample(int page, int size) {
        PageHelper.startPage(page,size);
        AdmininfoExample example=new AdmininfoExample();
        example.setOrderByClause("a.id desc");
        return new PageInfo<>(admininfoMapper.selectByExample(example));
    }

    @Override
    public Admininfo getexistuname(String username) {
        return admininfoMapper.getexistuname(username);
    }

    public int addAdmininfo(Admininfo admininfo){
        return admininfoMapper.insert(admininfo);
    }
    public  Admininfo login(Admininfo admininfo){
        Admininfo admininfo1 = admininfoMapper.login(admininfo);
        return admininfo1;
    }
    public  int insert(Admininfo record){
        return admininfoMapper.insert(record);
    }
    public int deleteByPrimaryKey(Long id){
        return admininfoMapper.deleteByPrimaryKey(id);
    }
    public Admininfo selectByPrimaryKey(Long id){
        return admininfoMapper.selectByPrimaryKey(id);
    }
    public int updateByPrimaryKeySelective(Admininfo record){
        return admininfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public Result queryUserIsExistPower(String examUser, String examPwd) {
        Admininfo admininfo = admininfoMapper.queryUserIsExistPower(examUser,examPwd);
        Date now = new Date();
        if (admininfo==null)
            return new JSONResult<>("用户名不存在或用户名密码错误");
        else{
            if (admininfo.getState()==0){
                return new JSONResult<>("该账号已被禁用！");
            }else if(admininfo.getExpirydate().getTime() < now.getTime()){
                return new JSONResult<>("error","该账号已过期！");
            }else {
                Integer roleId = admininfo.getRoleid();
                List<Power> powers = powerMapper.selectByRoleid(roleId);
                int result = 0;
                for (Power power : powers) {
                    if (power.getPermissionsign().equals("item:reportexam"))
                        result = 1;
                }
                if (result == 1) {
                    JSONResult jsonResult = new JSONResult();
                    jsonResult.setSuccess(true);
                    jsonResult.setData(admininfo.getUsername());
                    return jsonResult;
                } else
                    return new JSONResult<>("你没有复核报告的权限");
            }
        }

    }
}
