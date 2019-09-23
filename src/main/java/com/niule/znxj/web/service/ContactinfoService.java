package com.niule.znxj.web.service;

import com.niule.znxj.web.model.Contactinfo;
import com.niule.znxj.web.model.ContactinfoExample;

import java.util.HashMap;
import java.util.List;

/**
 * Created by administor on 2017/3/20.
 */
public interface ContactinfoService {
    //分页查询
    public List<Contactinfo> findByPageCon(int page, int pagesize);
    public int countCon();
    //添加联系人
    int insert(Contactinfo record);
    //删除联系人
    int deleteByPrimaryKey(Long id);
    //查询联系人详细信息
    Contactinfo selectByPrimaryKey(Long id);
    //修改联系人
    int updateByPrimaryKey(Contactinfo record);


    public List<Contactinfo> findByPageCon2(HashMap<String, Object> map);
    public int countCon2(HashMap<String, Object> map);

    public List<Contactinfo> sendAllPersion();

    List<Contactinfo> selectByExample(ContactinfoExample example);

}
