package com.niule.znxj.web.dao;

import com.niule.znxj.web.model.Roleandpower;
import com.niule.znxj.web.model.RoleandpowerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleandpowerMapper {
    int countByExample(RoleandpowerExample example);

    int deleteByExample(RoleandpowerExample example);

    int insert(Roleandpower record);

    int insertSelective(Roleandpower record);

    List<Roleandpower> selectByExample(RoleandpowerExample example);

    int updateByExampleSelective(@Param("record") Roleandpower record, @Param("example") RoleandpowerExample example);

    int updateByExample(@Param("record") Roleandpower record, @Param("example") RoleandpowerExample example);
}