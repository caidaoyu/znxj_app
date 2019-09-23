package com.niule.znxj.web.service;
import com.niule.znxj.web.model.Userinfo;
import com.niule.znxj.web.model.UserinfoExample;

import java.util.HashMap;
import java.util.List;

/**
 * Created by administor on 2017/3/20.
 */
public interface UserinfoService {
    public List<Userinfo> findByPageUser(HashMap<String, Object> map);
    public int countUser(HashMap<String, Object> map);

    public List<Userinfo> findByPageUser2(HashMap<String, Object> map);
    public int countUser2(HashMap<String, Object> map);

    int insert(Userinfo record);
    int deleteByPrimaryKey(Long id);
    Userinfo selectByPrimaryKey(Long id);
    int updateByPrimaryKeySelective(Userinfo record);
    List<Userinfo> selectByExample();

    List<Userinfo> querybyUserid(int uid);
    Userinfo queryRealname(Long id);

    int countuser(Long classid);

    /**
     * 判断该班组下是否已存在该用户
     * @param userName 用户名
     * @return
     */
//    int isUserExist(String userName, Long classid, Long id, Long oldClassId);
    int isUserExist(String userName, Long id);
}
