package com.niule.znxj.web.service;

import com.github.pagehelper.PageInfo;
import com.niule.znxj.web.model.Operatelog;
import com.niule.znxj.web.model.OperatelogExample;

import java.util.HashMap;
import java.util.List;

/**
 * Created by administor on 2017/5/22.
 */
public interface OperateLogService {
    PageInfo<Operatelog> findByPage1(int page, int size);
    PageInfo<Operatelog> findByPage2(HashMap<String, Object> map);
    int deleteByPrimaryKey(Integer logid);
    int insertSelective(String username, String operate);
}
