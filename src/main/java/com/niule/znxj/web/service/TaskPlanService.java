package com.niule.znxj.web.service;

import com.niule.znxj.core.entity.Result;
import com.niule.znxj.web.model.Taskplaninfo;
import com.niule.znxj.web.model.TaskplaninfoExample;
import com.niule.znxj.web.model.Taskreportinfo;
import com.niule.znxj.web.model.Taskstopinfo;

import java.util.HashMap;
import java.util.List;

/**
 * Created by administor on 2017/3/27.
 */
public interface TaskPlanService {
    public List<Taskplaninfo> findByPageTask(HashMap<String, Object> map);
    public int countTask(HashMap<String, Object> map);

    public List<Taskplaninfo> findByPageTask2(HashMap<String, Object> map);
    public int countTask2(HashMap<String, Object> map);

    int deleteByPrimaryKey(Long id);

    int insert(Taskplaninfo record);

    Taskplaninfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Taskplaninfo record);

    int deleteTaskTempByTaskId(Long taskid);

    List<Taskstopinfo> getStopTask(int page, int size, int state, int type);

    List<Taskplaninfo> selectByType(Long siteid, Integer type);

    List<Taskplaninfo> selectByExample(TaskplaninfoExample taskplaninfoExample);
}
