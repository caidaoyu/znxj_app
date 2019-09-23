package com.niule.znxj.web.service;

import com.niule.znxj.web.model.Warningtasktype;

import java.util.List;

/**
 * Created by administor on 2017/5/11.
 */
public interface WarnTypeService {
    List<Warningtasktype> showAllWarnType(int page, int size);
    List<Warningtasktype> selectByExample();

    int insert(Warningtasktype record);
    int updateByPrimaryKeySelective(Warningtasktype record);

    int deleteByPrimaryKey(Integer id);

    Warningtasktype selectByPrimaryKey(Integer id);
}
