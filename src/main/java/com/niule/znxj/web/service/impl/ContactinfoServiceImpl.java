package com.niule.znxj.web.service.impl;

import com.niule.znxj.web.dao.ContactinfoMapper;
import com.niule.znxj.web.model.Contactinfo;
import com.niule.znxj.web.model.ContactinfoExample;
import com.niule.znxj.web.service.ContactinfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * Created by administor on 2017/3/20.
 */
@Service
public class ContactinfoServiceImpl implements ContactinfoService{
    @Resource
    private ContactinfoMapper contactinfoMapper;
    @Override
    public List<Contactinfo> findByPageCon(int page, int pagesize) {
        return contactinfoMapper.findByPageCon((page-1)*pagesize,pagesize);
    }

    @Override
    public int countCon() {
        return contactinfoMapper.countCon();
    }

    @Override
    public int insert(Contactinfo record) {
        return contactinfoMapper.insert(record);
    }

    @Override
    public List<Contactinfo> findByPageCon2(HashMap<String,Object> map) {
        return contactinfoMapper.findByPageCon2(map);
    }

    @Override
    public List<Contactinfo> sendAllPersion() {
        ContactinfoExample example=new ContactinfoExample();
        return contactinfoMapper.selectByExample(example);
    }

    @Override
    public int countCon2(HashMap<String,Object> map) {
        return contactinfoMapper.countCon2(map);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return contactinfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Contactinfo selectByPrimaryKey(Long id) {
        return contactinfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(Contactinfo record) {
        return contactinfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Contactinfo> selectByExample(ContactinfoExample example) {
        return contactinfoMapper.selectByExample(example);
    }
}
