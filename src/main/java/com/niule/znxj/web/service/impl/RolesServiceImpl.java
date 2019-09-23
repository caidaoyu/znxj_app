package com.niule.znxj.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.niule.znxj.web.dao.RolesMapper;
import com.niule.znxj.web.model.AdmininfoExample;
import com.niule.znxj.web.model.Roles;
import com.niule.znxj.web.model.RolesExample;
import com.niule.znxj.web.service.RolesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by administor on 2017/5/4.
 */
@Service
public class RolesServiceImpl implements RolesService{
    @Resource
    private RolesMapper rolesMapper;

    @Override
    public int insert(Roles record) {
        return rolesMapper.insert(record);
    }

    @Override
    public Roles selectByPrimaryKey(Integer roleid) {
        return rolesMapper.selectByPrimaryKey(roleid);
    }

    @Override
    public int deleteByPrimaryKey(Integer roleid) {
        return rolesMapper.deleteByPrimaryKey(roleid);
    }

    @Override
    public List<Roles> selectByExample1() {
        RolesExample example=new RolesExample();
        return rolesMapper.selectByExample(example);
    }

    @Override
    public int updateByPrimaryKeySelective(Roles record) {
        return rolesMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<Roles> selectByExample(int page, int size) {
        PageHelper.startPage(page,size);
        RolesExample example=new RolesExample();
        example.setOrderByClause("roleid desc");
        return rolesMapper.selectByExample(example);
    }
}
