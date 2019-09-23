package com.niule.znxj.web.service;

import com.niule.znxj.web.model.Daterecordinfo;
import com.niule.znxj.web.model.DaterecordinfoExample;

import java.util.HashMap;
import java.util.List;

/**
 * Created by administor on 2017/3/27.
 */
public interface DateRecordService {
    public List<Daterecordinfo> findByPageData(int page, int pagesize);
    public int countData();
    public List<Daterecordinfo> findByPageData2(HashMap<String, Object> map);
    public int countData2(HashMap<String, Object> map);
    int insert(Daterecordinfo record);
    int deleteByPrimaryKey(Integer id);
    Daterecordinfo selectByPrimaryKey(int recordid);
    int updateByPrimaryKeySelective(Daterecordinfo record);
    List<Daterecordinfo> selectByExample(Integer recodeType);

    /**
     * 判断该类型名称是否存在
     * @param dateName
     * @param id
     * @return
     */
    int isNameExist(String dateName,Integer id);

}
