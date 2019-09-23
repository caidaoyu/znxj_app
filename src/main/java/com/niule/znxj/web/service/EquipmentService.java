package com.niule.znxj.web.service;

import com.niule.znxj.web.model.Areainfo;
import com.niule.znxj.web.model.Equipmentinfo;
import com.niule.znxj.web.model.EquipmentinfoExample;

import javax.annotation.Resources;
import java.util.HashMap;
import java.util.List;

/**
 * Created by administor on 2017/3/21.
 */
public interface EquipmentService {
    public List<Equipmentinfo> findByPageEquipment(HashMap<String, Object> map);
    public int countEquipment(HashMap<String, Object> map);
    public List<Equipmentinfo>findByPageEquipment2(HashMap<String, Object> map);
    public int countEquipment2(HashMap<String, Object> map);

    List<Equipmentinfo> selectByExample(EquipmentinfoExample example);
    List<Equipmentinfo> queryequip(Integer areaid);
    int insert(Equipmentinfo record);
    int deleteByPrimaryKey(Long id);
    Equipmentinfo selectByPrimaryKey(Long id);
    int updateByPrimaryKeySelective(Equipmentinfo record);
    //统计出某个区域的设备数
    int selectByExample1(Long areaid);
}
