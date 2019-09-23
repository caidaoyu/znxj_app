package com.niule.znxj.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.niule.znxj.core.util.json.JsonUtil;
import com.niule.znxj.web.dao.*;
import com.niule.znxj.web.model.*;
import com.niule.znxj.web.model.taskcontent.TaskArea;
import com.niule.znxj.web.model.taskcontent.TaskCheckItem;
import com.niule.znxj.web.model.taskcontent.TaskContent;
import com.niule.znxj.web.model.taskcontent.TaskEquipment;
import com.niule.znxj.web.service.TaskPlanService;
import com.niule.znxj.web.service.TaskreportService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by administor on 2017/3/27.
 */
@Service
public class TaskPlanServiceImpl implements TaskPlanService{
    @Resource
    private TasktempinfoMapper tasktempinfoMapper;
    @Resource
    private TaskplaninfoMapper taskplaninfoMapper;

    @Resource
    private AreaTaskMapper areaTaskMapper;
    @Resource
    private EquipmentTaskMapper equipmentTaskMapper;
    @Resource
    private CheckitemTaskMapper checkitemTaskMapper;
    @Resource
    private DaterecordTaskMapper daterecordTaskMapper;
    @Resource
    private TaskstopinfoMapper taskstopinfoMapper;
    @Resource
    private TaskreportService taskreportService;
    @Override
    public int deleteByPrimaryKey(Long id) {
        /*删除 数值-任务 表中关联数据*/
        DaterecordTaskExample daterecordTaskExample=new DaterecordTaskExample();
        daterecordTaskExample.createCriteria().andTaskidEqualTo(id);
        daterecordTaskMapper.deleteByExample(daterecordTaskExample);

        /*删除 巡检项-任务 表中关联数据*/
        CheckitemTaskExample checkitemTaskExample=new CheckitemTaskExample();
        checkitemTaskExample.createCriteria().andTaskidEqualTo(id);
        checkitemTaskMapper.deleteByExample(checkitemTaskExample);

        /*删除 设备-任务 表中关联数据*/
        EquipmentTaskExample equipmentTaskExample=new EquipmentTaskExample();
        equipmentTaskExample.createCriteria().andTaskidEqualTo(id);
        equipmentTaskMapper.deleteByExample(equipmentTaskExample);

        /*删除 区域-任务 表中关联数据*/
        AreaTaskExample areaTaskExample=new AreaTaskExample();
        areaTaskExample.createCriteria().andTaskidEqualTo(id);
        areaTaskMapper.deleteByExample(areaTaskExample);
        //删除主任务的同时，子任务状态改为99不显示
        tasktempinfoMapper.updateTempState(id);
        return taskplaninfoMapper.updatestate(id);
    }

    @Override
    public int insert(Taskplaninfo record) {
        int res = taskplaninfoMapper.insert(record);
        if(res >0 ){
            //插入区域-任务关联
            TaskContent content = JsonUtil.toObject(record.getTaskcontent(),TaskContent.class);
            for(TaskArea area : content.getAreas()){
                if(areaTaskMapper.insert(new AreaTask(area.getArea().getId(),record.getId()))==0){
                    return 0;
                }
                //插入设备-任务关联
                for(TaskEquipment equipment:area.getEquipments()){
                    if(equipmentTaskMapper.insert(new EquipmentTask(equipment.getEquipment().getId(),record.getId()))==0){
                        return 0;
                    }
                    //插入巡检项-任务关联
                    for(TaskCheckItem checkItem:equipment.getCheckItems()){
                        if(checkitemTaskMapper.insert(new CheckitemTask(checkItem.getItem().getId(),record.getId()))==0){
                            return 0;
                        }
                        //插入巡检项内容-任务关联
                        if(checkItem.getItem().getType()==2){
                            if(daterecordTaskMapper.insert(new DaterecordTask(checkItem.getItem().getRecordid(),record.getId()))==0){
                                return 0;
                            }
                        }
                    }
                }
            }
        }
        return res;
    }

    @Override
    public Taskplaninfo selectByPrimaryKey(Long id) {
        return taskplaninfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Taskplaninfo record) {
        int res = taskplaninfoMapper.updateByPrimaryKeySelective(record);
        //删除原有 区域-任务关联
        AreaTaskExample areaTaskExample =  new AreaTaskExample();
        areaTaskExample.createCriteria().andTaskidEqualTo(record.getId());
        areaTaskMapper.deleteByExample(areaTaskExample);
        //删除原有 设备-任务关联
        EquipmentTaskExample equipmentTaskExample =  new EquipmentTaskExample();
        equipmentTaskExample.createCriteria().andTaskidEqualTo(record.getId());
        equipmentTaskMapper.deleteByExample(equipmentTaskExample);
        //删除原有 巡检项-任务关联
        CheckitemTaskExample checkitemTaskExample =  new CheckitemTaskExample();
        checkitemTaskExample.createCriteria().andTaskidEqualTo(record.getId());
        checkitemTaskMapper.deleteByExample(checkitemTaskExample);

        //插入区域-任务关联
        TaskContent content = JsonUtil.toObject(record.getTaskcontent(),TaskContent.class);
        for(TaskArea area : content.getAreas()){
            if(areaTaskMapper.insert(new AreaTask(area.getArea().getId(),record.getId()))==0){
                return 0;
            }
            //插入设备-任务关联
            for(TaskEquipment equipment:area.getEquipments()){
                if(equipmentTaskMapper.insert(new EquipmentTask(equipment.getEquipment().getId(),record.getId()))==0){
                    return 0;
                }
                //插入巡检项-任务关联
                for(TaskCheckItem checkItem:equipment.getCheckItems()){
                    if(checkitemTaskMapper.insert(new CheckitemTask(checkItem.getItem().getId(),record.getId()))==0){
                        return 0;
                    }
                    //插入数值-任务关联
                    if(checkItem.getItem().getType()==2){
                        if(daterecordTaskMapper.insert(new DaterecordTask(checkItem.getItem().getRecordid(),record.getId()))==0){
                            return 0;
                        }
                    }
                }
            }
        }
        return res;
    }

    @Override
    public int deleteTaskTempByTaskId(Long taskid) {
        List<Integer> list2 = Arrays.asList(0,1);
        TasktempinfoExample example = new TasktempinfoExample();
        example.createCriteria().andTaskidEqualTo(taskid).andStateIn(list2);
        return tasktempinfoMapper.deleteByExample(example);
    }

  /*  @Override
    public List<Taskplaninfo> findByPageTask(int page, int pagesize,int type) {
        return taskplaninfoMapper.findByPageTask((page-1)*pagesize,pagesize,type);
    }

    @Override
    public int countTask(int type) {
        return taskplaninfoMapper.countTask(type);
    }*/

    @Override
    public List<Taskplaninfo> findByPageTask(HashMap<String, Object> map) {
        return taskplaninfoMapper.findByPageTask(map);
    }

    @Override
    public int countTask(HashMap<String, Object> map) {
        return taskplaninfoMapper.countTask(map);
    }

    @Override
    public List<Taskplaninfo> findByPageTask2(HashMap<String, Object> map) {
        return taskplaninfoMapper.findByPageTask2(map);
    }

    @Override
    public int countTask2(HashMap<String, Object> map) {
        return taskplaninfoMapper.countTask2(map);
    }

    @Override
    public List<Taskstopinfo> getStopTask(int page,int size,int state,int type) {
        PageHelper.startPage(page,size);
        return taskstopinfoMapper.getStopTask(state,type);
    }

    @Override
    public List<Taskplaninfo> selectByType(Long siteid,Integer type) {
        TaskplaninfoExample taskplaninfoExample=new TaskplaninfoExample();
        TaskplaninfoExample.Criteria criteria=taskplaninfoExample.createCriteria();
        if(siteid!=null){
            criteria.andSiteidEqualTo(siteid);
        }
        if(type!=null){
            criteria.andTypeEqualTo(type);
        }
        criteria.andStateEqualTo(1);
//        taskplaninfoExample.createCriteria().andSiteidEqualTo(siteid).andTypeEqualTo(type).andStateEqualTo(1);
        List<Taskplaninfo> taskplaninfos= taskplaninfoMapper.selectByExample(taskplaninfoExample);
        return taskplaninfos;
    }

    @Override
    public List<Taskplaninfo> selectByExample(TaskplaninfoExample taskplaninfoExample) {
        return taskplaninfoMapper.selectByExample(taskplaninfoExample);
    }
}
