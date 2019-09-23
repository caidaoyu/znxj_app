package com.niule.znxj.web.service;

import com.niule.znxj.web.model.Permission;
import com.niule.znxj.web.model.PermissionExample;

import java.util.List;

/**
 * Created by administor on 2017/5/4.
 */
public interface PermissionService {
    List<Permission> selectByExample();
    List<Permission> selectByRoleid(int roleid);
}
