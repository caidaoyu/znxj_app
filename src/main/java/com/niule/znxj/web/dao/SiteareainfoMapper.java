package com.niule.znxj.web.dao;

import com.niule.znxj.web.model.Siteareainfo;
import com.niule.znxj.web.model.SiteareainfoExample;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;

public interface SiteareainfoMapper {
    int countByExample(SiteareainfoExample example);

    int deleteByExample(SiteareainfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Siteareainfo record);

    int insertSelective(Siteareainfo record);

    List<Siteareainfo> selectByExample(SiteareainfoExample example);

    Siteareainfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Siteareainfo record, @Param("example") SiteareainfoExample example);

    int updateByExample(@Param("record") Siteareainfo record, @Param("example") SiteareainfoExample example);

    int updateByPrimaryKeySelective(Siteareainfo record);

    int updateByPrimaryKey(Siteareainfo record);
    public List<Siteareainfo> findByPageSite(HashMap<String, Object> map);
//    public int countSite();

}