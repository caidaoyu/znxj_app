package com.niule.znxj.web.service;

import com.niule.znxj.web.model.Nfcinfo;

import java.util.HashMap;
import java.util.List;

/**
 * Created by administor on 2017/3/21.
 */
public interface NfcService {
    public List<Nfcinfo> findByPageNfc(int page, int pagesize);
    public int countNfc();
    public List<Nfcinfo> findByPageNfc2(HashMap<String, Object> map);
    public int countNfc2(HashMap<String, Object> map);

    List<Nfcinfo> queryAllNfc();

    int deleteByPrimaryKey(Long id);

    int insert(Nfcinfo record);

    Nfcinfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Nfcinfo record);

    int updateareaid(Long areaid, Long nfcid);
    int updateequipmentid(Long equipmentid, Long nfcid);

    int updatebyareaid(Long areaid);
    int updatebyequipmentid(Long equipmentid);
}
