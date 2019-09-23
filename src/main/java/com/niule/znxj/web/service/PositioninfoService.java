package com.niule.znxj.web.service;

import com.github.pagehelper.PageInfo;
import com.niule.znxj.web.model.Admininfo;
import com.niule.znxj.web.model.Positioninfo;
import com.niule.znxj.web.model.PositioninfoExample;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by administor on 2017/3/17.
 */
@Repository
public interface PositioninfoService {
    //查询所有的管理员
    public List<Positioninfo> findByPagePos(int page, int pagesize);
    public int countPos();
    //添加新的职位
    int insert(Positioninfo record);
    int deleteByPrimaryKey(Long id);
    Positioninfo selectByPrimaryKey(Long id);
    int updateByPrimaryKey(Positioninfo record);

    List<Positioninfo> selectByExample();
    PageInfo<Positioninfo> selectByExample(int page, int size);
    PageInfo<Positioninfo> selectByExample1(int page, int size, String name);



    int countByExample();
}
