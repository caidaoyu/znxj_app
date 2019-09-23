package com.niule.znxj.web.dao;

import com.niule.znxj.web.model.Admininfo;
import com.niule.znxj.web.model.AdmininfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdmininfoMapper {
    int countByExample(AdmininfoExample example);

    int deleteByExample(AdmininfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Admininfo record);

    int insertSelective(Admininfo record);

    List<Admininfo> selectByExample(AdmininfoExample example);

    Admininfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Admininfo record, @Param("example") AdmininfoExample example);

    int updateByExample(@Param("record") Admininfo record, @Param("example") AdmininfoExample example);

    int updateByPrimaryKeySelective(Admininfo record);

    int updateByPrimaryKey(Admininfo record);

    Admininfo login(Admininfo admininfo);

    Admininfo getexistuname(String username);

    int admincount(int roleid);

    Admininfo queryUserIsExistPower(String examUser, String examPwd);
}