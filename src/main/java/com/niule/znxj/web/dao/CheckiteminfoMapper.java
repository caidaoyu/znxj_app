package com.niule.znxj.web.dao;

import com.niule.znxj.web.model.Areainfo;
import com.niule.znxj.web.model.Checkiteminfo;
import com.niule.znxj.web.model.CheckiteminfoExample;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CheckiteminfoMapper {
    int countByExample(CheckiteminfoExample example);

    int deleteByExample(CheckiteminfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Checkiteminfo record);

    int insertSelective(Checkiteminfo record);
    List<Checkiteminfo> selectByRecordid(CheckiteminfoExample example);
    List<Checkiteminfo> selectByExample(CheckiteminfoExample example);

    Checkiteminfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Checkiteminfo record, @Param("example") CheckiteminfoExample example);

    int updateByExample(@Param("record") Checkiteminfo record, @Param("example") CheckiteminfoExample example);

    int updateByPrimaryKeySelective(Checkiteminfo record);

    int updateByPrimaryKey(Checkiteminfo record);

    public List<Checkiteminfo> findByPageCheck(int page, int pagesize);
    public int countCheck();

    public List<Checkiteminfo> findByPageCheck2(HashMap<String, Object> map);
    public int countCheck2(HashMap<String, Object> map);

    Checkiteminfo queryByRecord(int record);

    List<Checkiteminfo> getcheckinfo(HashMap<String, Object> map);

    List<Areainfo> queryAllChickitem(Long siteId);
}