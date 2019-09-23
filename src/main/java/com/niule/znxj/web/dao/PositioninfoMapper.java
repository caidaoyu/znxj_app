package com.niule.znxj.web.dao;

import com.niule.znxj.web.model.Admininfo;
import com.niule.znxj.web.model.Positioninfo;
import com.niule.znxj.web.model.PositioninfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PositioninfoMapper {
    int countByExample(PositioninfoExample example);

    int deleteByExample(PositioninfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Positioninfo record);

    int insertSelective(Positioninfo record);

    List<Positioninfo> selectByExample(PositioninfoExample example);

    Positioninfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Positioninfo record, @Param("example") PositioninfoExample example);

    int updateByExample(@Param("record") Positioninfo record, @Param("example") PositioninfoExample example);

    int updateByPrimaryKeySelective(Positioninfo record);

    int updateByPrimaryKey(Positioninfo record);

    public List<Positioninfo> findByPagePos(int page, int pagesize);
    public int countPos();
}