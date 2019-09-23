package com.niule.znxj.web.dao;

import com.niule.znxj.web.model.Nfcinfo;
import com.niule.znxj.web.model.NfcinfoExample;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NfcinfoMapper {
    int countByExample(NfcinfoExample example);

    int deleteByExample(NfcinfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Nfcinfo record);

    int insertSelective(Nfcinfo record);

    List<Nfcinfo> selectByExample(NfcinfoExample example);

    Nfcinfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Nfcinfo record, @Param("example") NfcinfoExample example);

    int updateByExample(@Param("record") Nfcinfo record, @Param("example") NfcinfoExample example);

    int updateByPrimaryKeySelective(Nfcinfo record);

    int updateByPrimaryKey(Nfcinfo record);

    public List<Nfcinfo> findByPageNfc(int page, int pagesize);
    public int countNfc();

    public List<Nfcinfo> findByPageNfc2(HashMap<String, Object> map);
    public int countNfc2(HashMap<String, Object> map);

    List<Nfcinfo> queryAllNfc();

    int updateareaid(Long areaid, Long nfcid);
    int updateequipmentid(Long equipmentid, Long nfcid);

    int updatebyareaid(Long areaid);
    int updatebyequipmentid(Long equipmentid);
}