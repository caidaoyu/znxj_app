package com.niule.znxj.web.service;

import com.niule.znxj.web.model.Areainfo;
import com.niule.znxj.web.model.AreainfoExample;

import java.util.HashMap;
import java.util.List;

/**
 * Created by administor on 2017/3/21.
 */
public interface AreaService {
    /*public List<Areainfo> findByPageArea(int page, int pagesize);
    public int countArea();*/
    public List<Areainfo> findByPageArea(HashMap<String, Object> map);
    public int countArea(HashMap<String, Object> map);

    public List<Areainfo> findByPageArea2(HashMap<String, Object> map);
    public int countArea2(HashMap<String, Object> map);

    int deleteByPrimaryKey(Long id);
    List<Areainfo> selectByExample();
    int insert(Areainfo record);
    Areainfo selectByPrimaryKey(Long id);
    //int updateByExampleSelective(Areainfo record);
    int updateByPrimaryKeySelective(Areainfo record);

    List<Areainfo> selectByExample1(Integer siteid);

    List<Areainfo>sitearea(HashMap<String, Object> map);
}
