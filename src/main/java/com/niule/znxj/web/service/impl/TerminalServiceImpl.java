package com.niule.znxj.web.service.impl;

import com.niule.znxj.web.dao.TerminalinfoMapper;
import com.niule.znxj.web.model.Terminalinfo;
import com.niule.znxj.web.model.TerminalinfoExample;
import com.niule.znxj.web.service.TerminalService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * Created by administor on 2017/3/24.
 */
@Service
public class TerminalServiceImpl implements TerminalService{
    @Resource
    private TerminalinfoMapper terminalinfoMapper;

    @Override
    public List<Terminalinfo> findByPageTerminal(int page, int pagesize) {
        return terminalinfoMapper.findByPageTerminal((page-1)*pagesize,pagesize);
    }

    @Override
    public List<Terminalinfo> selectByexample() {
        TerminalinfoExample terminalinfoExample=new TerminalinfoExample();
        return terminalinfoMapper.selectByExample(terminalinfoExample);
    }

    @Override
    public int countTerminal() {
        return terminalinfoMapper.countTerminal();
    }

    @Override
    public List<Terminalinfo> findByPageTerminal2(HashMap<String, Object> map) {
        return terminalinfoMapper.findByPageTerminal2(map);
    }

    @Override
    public int insert(Terminalinfo record) {
        return terminalinfoMapper.insert(record);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return terminalinfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Terminalinfo selectByPrimaryKey(Long id) {
        return terminalinfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Terminalinfo record) {
        return terminalinfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int countTerminal2(HashMap<String, Object> map) {
        return terminalinfoMapper.countTerminal2(map);
    }
}
