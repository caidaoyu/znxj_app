package com.niule.znxj.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.niule.znxj.web.dao.AreainfoMapper;
import com.niule.znxj.web.dao.EquipmentinfoMapper;
import com.niule.znxj.web.dao.NfcinfoMapper;
import com.niule.znxj.web.model.*;
import com.niule.znxj.web.service.AreaService;
import com.niule.znxj.web.service.EquipmentService;
import com.niule.znxj.web.service.NfcService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * Created by administor on 2017/3/21.
 */
@Service
public class NfcServiceImpl implements NfcService{
    @Resource
    private NfcinfoMapper nfcinfoMapper;
    @Resource
    private AreaService areaService;
    @Resource
    private AreainfoMapper areainfoMapper;
    @Resource
    private EquipmentService equipmentService;
    @Resource
    private EquipmentinfoMapper equipmentinfoMapper;
    @Override
    public int updatebyequipmentid(Long equipmentid) {
        return nfcinfoMapper.updatebyequipmentid(equipmentid);
    }

    @Override
    public Nfcinfo selectByPrimaryKey(Long id) {
        return nfcinfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateequipmentid(Long equipmentid, Long nfcid) {
        return nfcinfoMapper.updateequipmentid(equipmentid,nfcid);
    }

    @Override
    public int updatebyareaid(Long areaid) {
        return nfcinfoMapper.updatebyareaid(areaid);
    }

    @Override
    public int updateareaid(Long areaid, Long nfcid) {
        return nfcinfoMapper.updateareaid(areaid,nfcid);
    }

    @Override
    public int updateByPrimaryKeySelective(Nfcinfo record) {
        /*nfc区域关联*/
        AreainfoExample areainfoExample=new AreainfoExample();
        areainfoExample.createCriteria().andNfctagEqualTo(record.getId());
        List<Areainfo> areainfos=areainfoMapper.selectByExample(areainfoExample);
        for(Areainfo areainfo:areainfos){
            areainfo.setNfc(record);
            //更新巡检项（关联任务）
            areaService.updateByPrimaryKeySelective(areainfo);
        }
         /*nfc设备关联*/
        EquipmentinfoExample equipmentinfoExample=new EquipmentinfoExample();
        equipmentinfoExample.createCriteria().andNfcidEqualTo(record.getId());
        List<Equipmentinfo> equipmentinfos=equipmentService.selectByExample(equipmentinfoExample);
        for(Equipmentinfo equipmentinfo:equipmentinfos){
            equipmentinfo.setNfc(record);
            equipmentService.updateByPrimaryKeySelective(equipmentinfo);
        }
        return nfcinfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        AreainfoExample areainfoExample=new AreainfoExample();
        areainfoExample.createCriteria().andNfctagEqualTo(id);
        List<Areainfo> areainfos=areainfoMapper.selectByExample(areainfoExample);
        for(Areainfo areainfo:areainfos){
            areainfo.setNfctag(null);
            areainfo.setNfc(null);
            areainfoMapper.updateByPrimaryKey(areainfo);
            areaService.updateByPrimaryKeySelective(areainfo);
        }
        EquipmentinfoExample equipmentinfoExample=new EquipmentinfoExample();
        equipmentinfoExample.createCriteria().andNfcidEqualTo(id);
        List<Equipmentinfo> equipmentinfos=equipmentService.selectByExample(equipmentinfoExample);
        for(Equipmentinfo equipmentinfo:equipmentinfos){
            equipmentinfo.setNfcid(null);
            equipmentinfo.setNfc(null);
            equipmentinfoMapper.updateByPrimaryKey(equipmentinfo);
            equipmentService.updateByPrimaryKeySelective(equipmentinfo);
        }
        return nfcinfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Nfcinfo record) {
        return nfcinfoMapper.insert(record);
    }

    @Override
    public List<Nfcinfo> findByPageNfc(int page, int pagesize) {
        return nfcinfoMapper.findByPageNfc((page-1)*pagesize,pagesize);
    }

    @Override
    public int countNfc() {
        return nfcinfoMapper.countNfc();
    }

    @Override
    public List<Nfcinfo> findByPageNfc2(HashMap<String, Object> map) {
        int page=Integer.parseInt(map.get("page").toString());
        int size=Integer.parseInt(map.get("pagesize").toString());
        PageHelper.startPage(page,size);
        List<Nfcinfo> nfcinfos=nfcinfoMapper.findByPageNfc2(map);
        return nfcinfos;
    }
    @Override
    public int countNfc2(HashMap<String, Object> map) {
        return nfcinfoMapper.countNfc2(map);
    }

    @Override
    public List<Nfcinfo> queryAllNfc() {
        NfcinfoExample example = new NfcinfoExample();
        example.createCriteria().andAreaidIsNull().andEquipmentidIsNull().andStateEqualTo(1);
        return nfcinfoMapper.selectByExample(example);

//        return nfcinfoMapper.queryAllNfc();
    }
}
