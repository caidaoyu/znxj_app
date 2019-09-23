package com.niule.znxj.web.dao;

import com.niule.znxj.web.model.Sendemail;
import com.niule.znxj.web.model.SendemailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SendemailMapper {
    int countByExample(SendemailExample example);

    int deleteByExample(SendemailExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Sendemail record);

    int insertSelective(Sendemail record);

    List<Sendemail> selectByExample(SendemailExample example);

    Sendemail selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Sendemail record, @Param("example") SendemailExample example);

    int updateByExample(@Param("record") Sendemail record, @Param("example") SendemailExample example);

    int updateByPrimaryKeySelective(Sendemail record);

    int updateByPrimaryKey(Sendemail record);
}