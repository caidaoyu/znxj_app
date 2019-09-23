package com.niule.znxj.web.dao;

import com.niule.znxj.web.model.Warningtasktype;
import com.niule.znxj.web.model.WarningtasktypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WarningtasktypeMapper {
    int countByExample(WarningtasktypeExample example);

    int deleteByExample(WarningtasktypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Warningtasktype record);

    int insertSelective(Warningtasktype record);

    List<Warningtasktype> selectByExample(WarningtasktypeExample example);

    Warningtasktype selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Warningtasktype record, @Param("example") WarningtasktypeExample example);

    int updateByExample(@Param("record") Warningtasktype record, @Param("example") WarningtasktypeExample example);

    int updateByPrimaryKeySelective(Warningtasktype record);

    int updateByPrimaryKey(Warningtasktype record);
}