package com.niule.znxj.web.service;

import com.niule.znxj.web.model.Terminalinfo;

import java.util.HashMap;
import java.util.List;

/**
 * Created by administor on 2017/3/24.
 */
public interface TerminalService {
    public List<Terminalinfo> findByPageTerminal(int page, int pagesize);
    public int countTerminal();

    public List<Terminalinfo> findByPageTerminal2(HashMap<String, Object> map);
    public int countTerminal2(HashMap<String, Object> map);

    int insert(Terminalinfo record);

    int deleteByPrimaryKey(Long id);

    Terminalinfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Terminalinfo record);

    List<Terminalinfo> selectByexample();
}
