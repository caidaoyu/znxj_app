package com.niule.znxj.web.service;

import com.niule.znxj.web.model.Siteareainfo;
import com.niule.znxj.web.model.SiteareainfoExample;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * Created by administor on 2017/3/21.
 */
public interface SiteService {
    /**
     *
     * 查询所有厂区
     * @return
     */
    List<Siteareainfo> queryAllSite();
    public List<Siteareainfo> findByPageSite(HashMap<String, Object> map);
//    public int countSite();
    int deleteByPrimaryKey(Long id);

    int insert(Siteareainfo record);
    Siteareainfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Siteareainfo record);
    List<Siteareainfo> selectByExample();
    List<Siteareainfo> selectByExample2(Integer siteid);
    List<Siteareainfo> queryAllSite2(int page, int size);
    List<Siteareainfo> selectSiteByUser(SiteareainfoExample example);

    /**
     * 判断是否已经存在厂区名称
     * @param customid 厂区名称
     * @return
     */
    int isSiteExist(String customid, Long id);
}
