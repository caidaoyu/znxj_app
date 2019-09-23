package com.niule.znxj.web.service.impl;

import com.niule.znxj.core.util.json.JsonUtil;
import com.niule.znxj.web.dao.AreaTaskMapper;
import com.niule.znxj.web.dao.AreainfoMapper;
import com.niule.znxj.web.dao.EquipmentinfoMapper;
import com.niule.znxj.web.dao.TaskplaninfoMapper;
import com.niule.znxj.web.model.*;
import com.niule.znxj.web.model.taskcontent.TaskArea;
import com.niule.znxj.web.model.taskcontent.TaskContent;
import com.niule.znxj.web.service.AreaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by administor on 2017/3/21.
 */
@Service
public class AreaServiceImpl implements AreaService{
    @Resource
    private AreainfoMapper areainfoMapper;
    @Resource
    private TaskplaninfoMapper taskplaninfoMapper;

    @Resource
    private AreaTaskMapper areaTaskMapper;

    @Resource
    private EquipmentinfoMapper equipmentinfoMapper;

    @Override
    public List<Areainfo> selectByExample1(Integer siteid) {
        AreainfoExample areainfoExample=new AreainfoExample();
        if(siteid!=null){
            areainfoExample.createCriteria().andPlantEqualTo(Long.valueOf(siteid));
        }
        return areainfoMapper.selectByExample(areainfoExample);
    }

  /*  @Override
    public List<Areainfo> findByPageArea(int page, int pagesize) {
        return areainfoMapper.findByPageArea((page-1)*pagesize,pagesize);
    }

    @Override
    public int countArea() {
        return areainfoMapper.countArea();
    }
*/

    @Override
    public List<Areainfo> findByPageArea(HashMap<String, Object> map) {
        return areainfoMapper.findByPageArea(map);
    }

    @Override
    public int countArea(HashMap<String,Object> map) {
        return areainfoMapper.countArea(map);
    }

    @Override
    public List<Areainfo> findByPageArea2(HashMap<String, Object> map) {
        return areainfoMapper.findByPageArea2(map);
    }

    @Override
    public int countArea2(HashMap<String, Object> map) {
        return areainfoMapper.countArea2(map);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        AreaTaskExample example =  new AreaTaskExample();
        example.createCriteria().andAreaidEqualTo(id);
        List<AreaTask> areaTasks = areaTaskMapper.selectByExample(example);
        for(AreaTask areaTask : areaTasks){
            //找出关联任务
            Taskplaninfo task =taskplaninfoMapper.selectByPrimaryKey(areaTask.getTaskid());
            TaskContent content = JsonUtil.toObject(task.getTaskcontent(),TaskContent.class);
            List<TaskArea> tempareas = new ArrayList<>();
            for(int i = 0 ; i < content.getAreas().size() ;i++){
                if(!content.getAreas().get(i).getArea().getId().equals(id)){
                    tempareas.add(content.getAreas().get(i));
                }
            }
            content.setAreas(tempareas);
            task.setTaskcontent(JsonUtil.toJSON(content));
            taskplaninfoMapper.updateByPrimaryKeySelective(task);
        }
        //删除 area_task 表中相关信息
        areaTaskMapper.deleteByExample(example);
        //删除 设备表中与area 相关联信息
        EquipmentinfoExample equipmentinfoExample = new EquipmentinfoExample();
        equipmentinfoExample.createCriteria().andAreaidEqualTo(id);
        equipmentinfoMapper.deleteByExample(equipmentinfoExample);

        return areainfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Areainfo> selectByExample() {
        AreainfoExample example = new AreainfoExample();
        return areainfoMapper.selectByExample(example);
    }
    @Override
    public Areainfo selectByPrimaryKey(Long id) {
        return areainfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Areainfo record) {
        AreaTaskExample example =  new AreaTaskExample();
        example.createCriteria().andAreaidEqualTo(record.getId());
        List<AreaTask> areaTasks = areaTaskMapper.selectByExample(example);
        for(AreaTask areaTask : areaTasks){
            //找出关联任务
            Taskplaninfo task =taskplaninfoMapper.selectByPrimaryKey(areaTask.getTaskid());
            TaskContent content = JsonUtil.toObject(task.getTaskcontent(),TaskContent.class);
            for(int i = 0 ; i < content.getAreas().size() ;i++){
                if(content.getAreas().get(i).getArea().getId().equals(record.getId())){
                    content.getAreas().get(i).setArea(record);
                }
            }
            task.setTaskcontent(JsonUtil.toJSON(content));
            taskplaninfoMapper.updateByPrimaryKeySelective(task);
        }
        return areainfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int insert(Areainfo record) {
        return areainfoMapper.insert(record);
    }

    @Override
    public List<Areainfo> sitearea(HashMap<String, Object> map) {
        return areainfoMapper.sitearea(map);
    }
}
