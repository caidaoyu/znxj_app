package com.niule.znxj.web.dao;

import com.niule.znxj.web.model.Adminoperatelog;
import com.niule.znxj.web.model.AdminoperatelogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminoperatelogMapper {
    int countByExample(AdminoperatelogExample example);

    int deleteByExample(AdminoperatelogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Adminoperatelog record);

    int insertSelective(Adminoperatelog record);

    List<Adminoperatelog> selectByExample(AdminoperatelogExample example);

    Adminoperatelog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Adminoperatelog record, @Param("example") AdminoperatelogExample example);

    int updateByExample(@Param("record") Adminoperatelog record, @Param("example") AdminoperatelogExample example);

    int updateByPrimaryKeySelective(Adminoperatelog record);

    int updateByPrimaryKey(Adminoperatelog record);
}