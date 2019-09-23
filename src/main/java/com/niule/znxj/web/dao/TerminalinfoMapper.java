package com.niule.znxj.web.dao;

import com.niule.znxj.web.model.Areainfo;
import com.niule.znxj.web.model.Terminalinfo;
import com.niule.znxj.web.model.TerminalinfoExample;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TerminalinfoMapper {
    int countByExample(TerminalinfoExample example);

    int deleteByExample(TerminalinfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Terminalinfo record);

    int insertSelective(Terminalinfo record);

    List<Terminalinfo> selectByExample(TerminalinfoExample example);

    Terminalinfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Terminalinfo record, @Param("example") TerminalinfoExample example);

    int updateByExample(@Param("record") Terminalinfo record, @Param("example") TerminalinfoExample example);

    int updateByPrimaryKeySelective(Terminalinfo record);

    int updateByPrimaryKey(Terminalinfo record);

    public List<Terminalinfo> findByPageTerminal(int page, int pagesize);
    public int countTerminal();

    public List<Terminalinfo> findByPageTerminal2(HashMap<String, Object> map);
    public int countTerminal2(HashMap<String, Object> map);
}