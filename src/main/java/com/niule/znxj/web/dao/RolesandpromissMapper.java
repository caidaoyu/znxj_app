package com.niule.znxj.web.dao;

import com.niule.znxj.web.model.Rolesandpromiss;
import com.niule.znxj.web.model.RolesandpromissExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RolesandpromissMapper {
    int countByExample(RolesandpromissExample example);

    int deleteByExample(RolesandpromissExample example);

    int deleteByPrimaryKey(Integer roleandpowerid);

    int insert(Rolesandpromiss record);

    int insertSelective(Rolesandpromiss record);

    List<Rolesandpromiss> selectByExample(RolesandpromissExample example);

    Rolesandpromiss selectByPrimaryKey(Integer roleandpowerid);

    int updateByExampleSelective(@Param("record") Rolesandpromiss record, @Param("example") RolesandpromissExample example);

    int updateByExample(@Param("record") Rolesandpromiss record, @Param("example") RolesandpromissExample example);

    int updateByPrimaryKeySelective(Rolesandpromiss record);

    int updateByPrimaryKey(Rolesandpromiss record);
}