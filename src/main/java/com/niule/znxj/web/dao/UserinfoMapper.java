package com.niule.znxj.web.dao;

import com.niule.znxj.web.model.Userinfo;
import com.niule.znxj.web.model.UserinfoExample;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserinfoMapper {
    int countByExample(UserinfoExample example);

    int deleteByExample(UserinfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Userinfo record);

    int insertSelective(Userinfo record);

    List<Userinfo> selectByExample(UserinfoExample example);

    Userinfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Userinfo record, @Param("example") UserinfoExample example);

    int updateByExample(@Param("record") Userinfo record, @Param("example") UserinfoExample example);

    int updateByPrimaryKeySelective(Userinfo record);

    int updateByPrimaryKey(Userinfo record);

    public List<Userinfo> findByPageUser(HashMap<String, Object> map);
    public int countUser(HashMap<String, Object> map);

    public List<Userinfo> findByPageUser2(HashMap<String, Object> map);
    public int countUser2(HashMap<String, Object> map);

    List<Userinfo> querybyUserid(int uid);

    Userinfo authUser(String username, String password);

    Userinfo queryRealname(Long id);
}