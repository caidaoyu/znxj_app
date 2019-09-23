package com.niule.znxj.web.service;

import com.niule.znxj.web.model.Classinfo;
import com.niule.znxj.web.model.ClassinfoExample;
import com.niule.znxj.web.model.Positioninfo;

import java.util.HashMap;
import java.util.List;

/**
 * Created by administor on 2017/3/17.
 */
public interface ClassinfoService {
   /* List<Classinfo> findByPageClass(int page, int pagesize);*/
    List<Classinfo> findByPageClass(HashMap<String, Object> map);
    int countClass(HashMap<String, Object> map);

    int insert(Classinfo record);

    int deleteByPrimaryKey(Long id);

    Classinfo selectByPrimaryKey(Long id);

    int updateByPrimaryKey(Classinfo record);

    List<Classinfo> queryusername(int uid);

    List<Classinfo> selectByExample();

    List<Classinfo> queryclassbysiteid(Long siteid);

    List<Classinfo> classbyuserid(Long directorid);
    List<Classinfo> selectByExample2(Integer siteid);


 /**
  * 判断厂区下边是否存在改班组
  * @param customName 班组名
  * @param siteareaid 厂区
  * @param id
  * @return
  */
    int isClassIdExist(String customName, Long siteareaid, Long id);

}
