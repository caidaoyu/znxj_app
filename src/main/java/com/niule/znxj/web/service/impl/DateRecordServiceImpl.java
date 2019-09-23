package com.niule.znxj.web.service.impl;

import com.niule.znxj.web.dao.CheckiteminfoMapper;
import com.niule.znxj.web.dao.DaterecordinfoMapper;
import com.niule.znxj.web.model.Checkiteminfo;
import com.niule.znxj.web.model.CheckiteminfoExample;
import com.niule.znxj.web.model.Daterecordinfo;
import com.niule.znxj.web.model.DaterecordinfoExample;
import com.niule.znxj.web.service.CheckInfoService;
import com.niule.znxj.web.service.DateRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * Created by administor on 2017/3/27.
 */
@Service
public class DateRecordServiceImpl implements DateRecordService{
    @Resource
    private DaterecordinfoMapper daterecordinfoMapper;
    @Resource
    private CheckInfoService checkInfoService;
    @Resource
    private CheckiteminfoMapper checkiteminfoMapper;
    @Override
    public List<Daterecordinfo> findByPageData(int page, int pagesize) {
        return daterecordinfoMapper.findByPageData((page-1)*pagesize,pagesize);
    }

    @Override
    public int countData() {
        return daterecordinfoMapper.countData();
    }

    @Override
    public List<Daterecordinfo> findByPageData2(HashMap<String, Object> map) {
        return daterecordinfoMapper.findByPageData2(map);
    }

    @Override
    public int insert(Daterecordinfo record) {
        return daterecordinfoMapper.insert(record);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        CheckiteminfoExample example = new CheckiteminfoExample();
        example.createCriteria().andRecordidEqualTo(id.longValue());
        List<Checkiteminfo> checkiteminfos = checkiteminfoMapper.selectByRecordid(example);
      /*  List<Checkiteminfo> checkiteminfos = checkiteminfoMapper.selectByExample(id)*/
        for(Checkiteminfo item:checkiteminfos){
            //将checkitem recordid 置为空
            item.setRecordid(null);
            checkiteminfoMapper.updateByPrimaryKey(item);
            //将checkitem 相关datarecord 字段设置为空
            item.setDaterecord(null);
            //更新巡检项（关联任务）
            System.out.print(item);
            checkInfoService.updateByPrimaryKeySelective(item);
        }
        int aa=daterecordinfoMapper.deleteByPrimaryKey(id);
        return aa;
    }

    @Override
    public Daterecordinfo selectByPrimaryKey(int recordid) {
        return daterecordinfoMapper.selectByPrimaryKey(recordid);
    }

    @Override
    public List<Daterecordinfo> selectByExample(Integer recodeType) {
        if(recodeType==null)recodeType=0;
        DaterecordinfoExample example=new DaterecordinfoExample();
        example.createCriteria().andRecordtypeEqualTo(recodeType);
        return daterecordinfoMapper.selectByExample(example);
    }

    @Override
    public int updateByPrimaryKeySelective(Daterecordinfo record) {
        CheckiteminfoExample example = new CheckiteminfoExample();
        example.createCriteria().andRecordidEqualTo(record.getId().longValue());
        List<Checkiteminfo> checkiteminfos = checkiteminfoMapper.selectByRecordid(example);
        for(Checkiteminfo item:checkiteminfos){
            item.setDaterecord(record);
            //更新巡检项（关联任务）
            checkInfoService.updateByPrimaryKeySelective(item);
        }
        return daterecordinfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int countData2(HashMap<String, Object> map) {
        return daterecordinfoMapper.countData2(map);
    }

    /**
     * 判断该类型名称是否存在
     * @param dateName
     * @param id
     * @return
     */
    @Override
    public int isNameExist(String dateName, Integer id) {
        DaterecordinfoExample daterecordinfoExample = new DaterecordinfoExample();
        DaterecordinfoExample.Criteria criteria = daterecordinfoExample.createCriteria();
        if(id != null){
            criteria.andIdNotEqualTo(id);
        }
        criteria.andNameEqualTo(dateName);
        return daterecordinfoMapper.countByExample(daterecordinfoExample);
    }

}
