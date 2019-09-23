package com.niule.znxj.web.dao;

import com.niule.znxj.web.model.Areainfo;
import com.niule.znxj.web.model.AreainfoExample;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AreainfoMapper {
    int countByExample(AreainfoExample example);

    int deleteByExample(AreainfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Areainfo record);

    int insertSelective(Areainfo record);

    List<Areainfo> selectByExample(AreainfoExample example);
    Areainfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Areainfo record, @Param("example") AreainfoExample example);

    int updateByExample(@Param("record") Areainfo record, @Param("example") AreainfoExample example);

    int updateByPrimaryKeySelective(Areainfo record);

    int updateByPrimaryKey(Areainfo record);

    public List<Areainfo> findByPageArea(HashMap<String, Object> map);
    public int countArea(HashMap<String, Object> map);

    public List<Areainfo> findByPageArea2(HashMap<String, Object> map);
    public int countArea2(HashMap<String, Object> map);
    List<Areainfo>sitearea(HashMap<String, Object> map);
}