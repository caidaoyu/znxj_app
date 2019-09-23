package com.niule.znxj.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.niule.znxj.web.dao.OperatelogMapper;
import com.niule.znxj.web.model.Admininfo;
import com.niule.znxj.web.model.Operatelog;
import com.niule.znxj.web.model.OperatelogExample;
import com.niule.znxj.web.service.OperateLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by administor on 2017/5/22.
 */
@Service
public class OperateLogServiceImpl implements OperateLogService {
    @Resource
    private OperatelogMapper operatelogMapper;

    @Override
    public int insertSelective(String username,String operate) {
        Operatelog operatelog=new Operatelog();
        operatelog.setUsername(username);
        operatelog.setTime(new Date());
        operatelog.setOperate(operate);
        return operatelogMapper.insertSelective(operatelog);
    }

    @Override
    /**
     * 查询所有的操作日志信息
     */
    public PageInfo<Operatelog> findByPage1(int page, int size) {
        PageHelper.startPage(page,size);
        List<Operatelog> operatelogs=operatelogMapper.selectByExample();
        return new PageInfo<>(operatelogs);
    }

    @Override
    /**
     * 根据条件查询操作日志信息
     */
    public PageInfo<Operatelog> findByPage2(HashMap<String,Object> map) {
        int page=Integer.parseInt(map.get("page").toString());
        int size=Integer.parseInt(map.get("size").toString());
        PageHelper.startPage(page,size);
        return new PageInfo<>(operatelogMapper.findByPage(map));
    }

    @Override
    /**
     * 删除操作日志
     */
    public int deleteByPrimaryKey(Integer logid) {
        return operatelogMapper.deleteByPrimaryKey(logid);
    }

}
