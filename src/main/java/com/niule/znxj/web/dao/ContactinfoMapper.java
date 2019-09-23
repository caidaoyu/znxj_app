package com.niule.znxj.web.dao;

import com.niule.znxj.web.model.Contactinfo;
import com.niule.znxj.web.model.ContactinfoExample;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ContactinfoMapper {
    int countByExample(ContactinfoExample example);

    int deleteByExample(ContactinfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Contactinfo record);

    int insertSelective(Contactinfo record);

    List<Contactinfo> selectByExample(ContactinfoExample example);

    Contactinfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Contactinfo record, @Param("example") ContactinfoExample example);

    int updateByExample(@Param("record") Contactinfo record, @Param("example") ContactinfoExample example);

    int updateByPrimaryKeySelective(Contactinfo record);

    int updateByPrimaryKey(Contactinfo record);

    public List<Contactinfo> findByPageCon(int page, int pagesize);
    public int countCon();

    public List<Contactinfo> findByPageCon2(HashMap<String, Object> map);
    public int countCon2(HashMap<String, Object> map);
}