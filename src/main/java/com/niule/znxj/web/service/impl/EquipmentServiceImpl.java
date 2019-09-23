package com.niule.znxj.web.service.impl;

import com.niule.znxj.core.util.json.JsonUtil;
import com.niule.znxj.web.dao.EquipmentTaskMapper;
import com.niule.znxj.web.dao.EquipmentinfoMapper;
import com.niule.znxj.web.dao.TaskplaninfoMapper;
import com.niule.znxj.web.model.*;
import com.niule.znxj.web.model.taskcontent.TaskArea;
import com.niule.znxj.web.model.taskcontent.TaskContent;
import com.niule.znxj.web.model.taskcontent.TaskEquipment;
import com.niule.znxj.web.service.EquipmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by administor on 2017/3/21.
 */
@Service
public class EquipmentServiceImpl implements EquipmentService{
    @Resource
    private EquipmentinfoMapper equipmentinfoMapper;
    @Resource
    private EquipmentTaskMapper equipmentTaskMapper;
    @Resource
    private TaskplaninfoMapper taskplaninfoMapper;

    @Override
    public List<Equipmentinfo> findByPageEquipment(HashMap<String,Object> map) {
        return equipmentinfoMapper.findByPageEquipment(map);
    }

    @Override
    public int countEquipment(HashMap<String,Object> map) {
        return equipmentinfoMapper.countEquipment(map);
    }

    @Override
    public int insert(Equipmentinfo record) {
        return equipmentinfoMapper.insert(record);
    }

    @Override
    public List<Equipmentinfo> findByPageEquipment2(HashMap<String, Object> map) {
        return equipmentinfoMapper.findByPageEquipment2(map);
    }

    @Override
    public int countEquipment2(HashMap<String, Object> map) {
        return equipmentinfoMapper.countEquipment2(map);
    }

    @Override
    public Equipmentinfo selectByPrimaryKey(Long id) {
        return equipmentinfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Equipmentinfo record) {
        EquipmentTaskExample example =  new EquipmentTaskExample();
        example.createCriteria().andEquipmentidEqualTo(record.getId());
        List<EquipmentTask> equipmentTasks = equipmentTaskMapper.selectByExample(example);
        for(EquipmentTask equipmentTask : equipmentTasks){
            //找出关联任务
            Taskplaninfo task =taskplaninfoMapper.selectByPrimaryKey(equipmentTask.getTaskid());
            if(task==null){continue;}
            TaskContent content = JsonUtil.toObject(task.getTaskcontent(),TaskContent.class);
            //遍历任务区域
            for(TaskArea area : content.getAreas()){
                for(int i = 0 ; i < area.getEquipments().size() ; i++){
                    if(area.getEquipments().get(i).getEquipment().getId().equals(record.getId())){
                        area.getEquipments().get(i).setEquipment(record);
                    }
                }
            }
            task.setTaskcontent(JsonUtil.toJSON(content));
            taskplaninfoMapper.updateByPrimaryKeySelective(task);
        }
        return equipmentinfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Equipmentinfo> queryequip(Integer areaid) {
        if(areaid==null)
            return equipmentinfoMapper.selectByExample(new EquipmentinfoExample());
        return equipmentinfoMapper.queryequip(areaid);
    }

    @Override
    public List<Equipmentinfo> selectByExample(EquipmentinfoExample example) {
        return equipmentinfoMapper.selectByExample(example);
    }

    @Override
    public int selectByExample1(Long areaid) {
        EquipmentinfoExample equipmentinfoExample=new EquipmentinfoExample();
        equipmentinfoExample.createCriteria().andAreaidEqualTo(areaid);
        return equipmentinfoMapper.selectByExample(equipmentinfoExample).size();
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        EquipmentTaskExample example =  new EquipmentTaskExample();
        example.createCriteria().andEquipmentidEqualTo(id);
        List<EquipmentTask> equipmentTasks = equipmentTaskMapper.selectByExample(example);
        for(EquipmentTask equipmentTask : equipmentTasks){
            //找出关联任务
            Taskplaninfo task =taskplaninfoMapper.selectByPrimaryKey(equipmentTask.getTaskid());
            TaskContent content = JsonUtil.toObject(task.getTaskcontent(),TaskContent.class);
            //遍历任务区域
            for(TaskArea area : content.getAreas()){
                List<TaskEquipment> tempequipments = new ArrayList<>();
                for(int i = 0 ; i < area.getEquipments().size() ; i++){
                    if(!area.getEquipments().get(i).getEquipment().getId().equals(id)){
                        tempequipments.add(area.getEquipments().get(i));
                    }
                }
                area.setEquipments(tempequipments);
            }
            task.setTaskcontent(JsonUtil.toJSON(content));
            taskplaninfoMapper.updateByPrimaryKeySelective(task);
        }
        //删除 equiment_task 表中相关信息
        equipmentTaskMapper.deleteByExample(example);
        return equipmentinfoMapper.deleteByPrimaryKey(id);
    }
}
