package com.niule.znxj.web.dao;

import com.niule.znxj.web.model.Equipmentinfo;
import com.niule.znxj.web.model.EquipmentinfoExample;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EquipmentinfoMapper {
    int countByExample(EquipmentinfoExample example);

    int deleteByExample(EquipmentinfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Equipmentinfo record);

    int insertSelective(Equipmentinfo record);

    List<Equipmentinfo> selectByExample(EquipmentinfoExample example);

    Equipmentinfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Equipmentinfo record, @Param("example") EquipmentinfoExample example);

    int updateByExample(@Param("record") Equipmentinfo record, @Param("example") EquipmentinfoExample example);

    int updateByPrimaryKeySelective(Equipmentinfo record);

    int updateByPrimaryKey(Equipmentinfo record);

    public List<Equipmentinfo> findByPageEquipment(HashMap<String, Object> map);
    public int countEquipment(HashMap<String, Object> map);

    public List<Equipmentinfo>findByPageEquipment2(HashMap<String, Object> map);
    public int countEquipment2(HashMap<String, Object> map);

    List<Equipmentinfo> queryequip(Integer areaid);
}