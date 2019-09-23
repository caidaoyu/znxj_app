package com.niule.znxj.web.dao;

import com.niule.znxj.web.model.Operatelog;
import com.niule.znxj.web.model.OperatelogExample;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OperatelogMapper {
    int countByExample(OperatelogExample example);

    int deleteByExample(OperatelogExample example);

    int deleteByPrimaryKey(Integer logid);

    int insert(Operatelog record);

    int insertSelective(Operatelog record);

    List<Operatelog> selectByExample();

    Operatelog selectByPrimaryKey(Integer logid);

    int updateByExampleSelective(@Param("record") Operatelog record, @Param("example") OperatelogExample example);

    int updateByExample(@Param("record") Operatelog record, @Param("example") OperatelogExample example);

    int updateByPrimaryKeySelective(Operatelog record);

    int updateByPrimaryKey(Operatelog record);

    List<Operatelog> findByPage(HashMap<String, Object> map);
}