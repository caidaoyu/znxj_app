package com.niule.znxj.web.service;

import com.github.pagehelper.PageInfo;
import com.niule.znxj.core.entity.Result;
import com.niule.znxj.web.model.Admininfo;
import com.niule.znxj.web.model.AdmininfoExample;
import com.niule.znxj.web.model.Positioninfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by administor on 2017/3/16.
 */
@Repository
public interface AdmininfoService {
    //查询所有的管理员
    /*List<Admininfo> findByPage(int page,int pagesize);
    int count();*/
    //用户登录
    Admininfo login(Admininfo admininfo);
    //添加新的管理员
    int insert(Admininfo record);
    int deleteByPrimaryKey(Long id);
    Admininfo selectByPrimaryKey(Long id);
    int updateByPrimaryKeySelective(Admininfo record);

    PageInfo<Admininfo> selectByExample(int page, int size);

    Admininfo getexistuname(String username);

    int admincount(int roleid);

    Result queryUserIsExistPower(String examUser, String examPwd);
}
