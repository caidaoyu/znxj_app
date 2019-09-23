package com.niule.znxj.web.service;

import com.niule.znxj.web.model.Knowledgetype;
import com.niule.znxj.web.model.Roles;

import java.util.List;

/**
 * Created by administor on 2017/5/8.
 */
public interface KonwledgeTypeService {
    List<Knowledgetype> selectByExample(int page, int size);

    int insert(Knowledgetype record);
    int updateByPrimaryKeySelective(Knowledgetype record);

    int deleteByPrimaryKey(Integer typeid);

    Knowledgetype selectByPrimaryKey(Integer typeid);

    List<Knowledgetype> selectByExample1();
}
