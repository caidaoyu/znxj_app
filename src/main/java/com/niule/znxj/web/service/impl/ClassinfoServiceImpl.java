package com.niule.znxj.web.service.impl;

import com.niule.znxj.web.dao.ClassinfoMapper;
import com.niule.znxj.web.model.Classinfo;
import com.niule.znxj.web.model.ClassinfoExample;
import com.niule.znxj.web.service.ClassinfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * Created by administor on 2017/3/17.
 */
@Service
public class ClassinfoServiceImpl implements ClassinfoService{
    @Resource
    private ClassinfoMapper classinfoMapper;

    @Override
    public List<Classinfo> selectByExample() {
        ClassinfoExample example=new ClassinfoExample();
        return classinfoMapper.selectByExample(example);
    }
    @Override
    public List<Classinfo> selectByExample2(Integer siteid) {
        ClassinfoExample example=new ClassinfoExample();
        if(siteid!=null){
            example.createCriteria().andSiteareaidEqualTo(Long.valueOf(siteid));
        }
        return classinfoMapper.selectByExample(example);
    }


    @Override
    public List<Classinfo> queryclassbysiteid(Long siteid) {
        ClassinfoExample example=new ClassinfoExample();
        example.createCriteria().andSiteareaidEqualTo(siteid);
        return classinfoMapper.selectByExample(example);
    }

   /* @Override
    public List<Classinfo> findByPageClass(int page, int pagesize) {
        return classinfoMapper.findByPageClass((page-1)*pagesize,pagesize);
    }*/

    @Override
    public List<Classinfo> findByPageClass(HashMap<String, Object> map) {
        return classinfoMapper.findByPageClass(map);
    }

    @Override
    public int countClass(HashMap<String,Object> map) {
        return classinfoMapper.countClass(map);
    }

    @Override
    public int insert(Classinfo record) {
        return classinfoMapper.insert(record);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return classinfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Classinfo selectByPrimaryKey(Long id) {
        return classinfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(Classinfo record) {
        return classinfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Classinfo> queryusername(int uid) {
        return classinfoMapper.queryusername(uid);
    }

    @Override
    public List<Classinfo> classbyuserid(Long directorid) {
        ClassinfoExample classinfoExample=new ClassinfoExample();
        classinfoExample.createCriteria().andDirectoridEqualTo(directorid);
        return classinfoMapper.selectByExample(classinfoExample);
    }

    /**
     * 判断该厂区下是否存在该班组
     * @param customName 班组名
     * @param siteareaid 厂区
     * @param id
     * @return
     */
    @Override
    public int isClassIdExist(String customName, Long siteareaid, Long id) {
        ClassinfoExample classinfoExample = new ClassinfoExample();
        ClassinfoExample.Criteria criteria = classinfoExample.createCriteria();

        if(id != null){
            criteria.andIdNotEqualTo(id);
        }
        criteria.andCustomidEqualTo(customName).andSiteareaidEqualTo(siteareaid);

        return classinfoMapper.countByExample(classinfoExample);
    }
}
