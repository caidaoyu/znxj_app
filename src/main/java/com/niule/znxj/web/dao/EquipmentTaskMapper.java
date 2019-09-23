package com.niule.znxj.web.dao;

import com.niule.znxj.web.model.EquipmentTask;
import com.niule.znxj.web.model.EquipmentTaskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EquipmentTaskMapper {
    int countByExample(EquipmentTaskExample example);

    int deleteByExample(EquipmentTaskExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EquipmentTask record);

    int insertSelective(EquipmentTask record);

    List<EquipmentTask> selectByExample(EquipmentTaskExample example);

    EquipmentTask selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EquipmentTask record, @Param("example") EquipmentTaskExample example);

    int updateByExample(@Param("record") EquipmentTask record, @Param("example") EquipmentTaskExample example);

    int updateByPrimaryKeySelective(EquipmentTask record);

    int updateByPrimaryKey(EquipmentTask record);
}