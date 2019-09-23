package com.niule.znxj.web.service;

import com.github.pagehelper.PageInfo;
import com.niule.znxj.web.model.Positioninfo;
import com.niule.znxj.web.model.Roles;

import java.util.List;

/**
 * Created by administor on 2017/5/4.
 */
public interface RolesService {
    List<Roles> selectByExample(int page, int size);
    List<Roles> selectByExample1();

    int insert(Roles record);
    int updateByPrimaryKeySelective(Roles record);
    Roles selectByPrimaryKey(Integer roleid);

    int deleteByPrimaryKey(Integer roleid);
}
