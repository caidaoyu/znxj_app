package com.niule.znxj.web.service.impl;

import com.niule.znxj.web.dao.UserinfoMapper;
import com.niule.znxj.web.model.Userinfo;
import com.niule.znxj.web.model.UserinfoExample;
import com.niule.znxj.web.service.UserinfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * Created by administor on 2017/3/20.
 */
@Service
public class UserinfoServiceImpl implements UserinfoService{
    @Resource
    private UserinfoMapper userinfoMapper;

    @Override
    public List<Userinfo> findByPageUser(HashMap<String, Object> map) {
        return userinfoMapper.findByPageUser(map);
    }

    @Override
    public int countUser(HashMap<String, Object> map) {
        return userinfoMapper.countUser(map);
    }

    @Override
    public List<Userinfo> findByPageUser2(HashMap<String, Object> map) {
        return userinfoMapper.findByPageUser2(map);
    }

    @Override
    public int countUser2(HashMap<String, Object> map) {
        return userinfoMapper.countUser2(map);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return userinfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Userinfo selectByPrimaryKey(Long id) {
        return userinfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public  Userinfo queryRealname(Long id) {
        return userinfoMapper.queryRealname(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Userinfo record) {
        return userinfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<Userinfo> selectByExample() {
        UserinfoExample example=new UserinfoExample();
        return userinfoMapper.selectByExample(example);
    }

    @Override
    public List<Userinfo> querybyUserid(int uid) {
        return userinfoMapper.querybyUserid(uid);
    }

    @Override
    public int insert(Userinfo record) {
        return userinfoMapper.insert(record);
    }

    @Override
    public int countuser(Long classid) {
        UserinfoExample example=new UserinfoExample();
        example.createCriteria().andClassidEqualTo(classid);
        return userinfoMapper.selectByExample(example).size();
    }

    /**
     * 判断该班组下是否已存在该用户
     * @param userName 用户名
     * @return
     */
   /* @Override
    public int isUserExist(String userName, Long classid,Long id,Long oldClassId) {
        UserinfoExample userinfoExample = new UserinfoExample();
        UserinfoExample.Criteria criteria = userinfoExample.createCriteria();
        if(id != null && classid == oldClassId){
           criteria.andIdNotEqualTo(id);
        }
        criteria.andClassidEqualTo(classid).andUsernameEqualTo(userName);
        return userinfoMapper.countByExample(userinfoExample);
    }*/
    @Override
    public int isUserExist(String userName, Long id) {
        UserinfoExample userinfoExample = new UserinfoExample();
        UserinfoExample.Criteria criteria = userinfoExample.createCriteria();
        if(id != null){
           criteria.andIdNotEqualTo(id);
        }
        criteria.andUsernameEqualTo(userName);
        return userinfoMapper.countByExample(userinfoExample);
    }
}
