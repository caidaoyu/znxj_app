package com.niule.znxj.web.service;

import com.niule.znxj.web.model.Checkiteminfo;
import com.niule.znxj.web.model.CheckiteminfoExample;

import java.util.HashMap;
import java.util.List;

/**
 * Created by administor on 2017/3/22.
 */
public interface CheckInfoService {
    public List<Checkiteminfo> findByPageCheck(int page, int pagesize);
    public int countCheck();

    public List<Checkiteminfo> findByPageCheck2(HashMap<String, Object> map);
    public int countCheck2(HashMap<String, Object> map);

    int deleteByPrimaryKey(Long id);

    int insert(Checkiteminfo record);

    Checkiteminfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Checkiteminfo record);

    List<Checkiteminfo> selectByExample();

    Checkiteminfo queryByRecord(int record);

    int selectByExample2(Long recordid);
    int selectByExample3(Long id);

    List<Checkiteminfo>getcheckinfo(HashMap<String, Object> map);
}
