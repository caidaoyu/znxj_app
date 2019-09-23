package com.niule.znxj.web.service.impl;

import com.niule.znxj.web.dao.PermissionMapper;
import com.niule.znxj.web.model.Permission;
import com.niule.znxj.web.model.PermissionExample;
import com.niule.znxj.web.service.PermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by administor on 2017/5/4.
 */
@Service
public class PermissionServiceImpl implements PermissionService{
    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> selectByExample() {
        PermissionExample example=new PermissionExample();
//        example.createCriteria().andPermissionidNotEqualTo(1);
        return permissionMapper.selectByExample(example);
    }

    @Override
    public List<Permission> selectByRoleid(int roleid) {
        return permissionMapper.selectByRoleid(roleid);
    }
}
