package com.niule.znxj.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.niule.znxj.core.util.json.JsonUtil;
import com.niule.znxj.web.dao.CheckitemTaskMapper;
import com.niule.znxj.web.dao.CheckiteminfoMapper;
import com.niule.znxj.web.dao.DaterecordinfoMapper;
import com.niule.znxj.web.dao.TaskplaninfoMapper;
import com.niule.znxj.web.model.*;
import com.niule.znxj.web.model.taskcontent.TaskArea;
import com.niule.znxj.web.model.taskcontent.TaskCheckItem;
import com.niule.znxj.web.model.taskcontent.TaskContent;
import com.niule.znxj.web.model.taskcontent.TaskEquipment;
import com.niule.znxj.web.service.CheckInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by administor on 2017/3/22.
 */
@Service
public class CheckInfoServiceImpl implements CheckInfoService{
    @Resource
    private CheckiteminfoMapper checkiteminfoMapper;
    @Resource
    private CheckitemTaskMapper checkitemTaskMapper;
    @Resource
    private TaskplaninfoMapper taskplaninfoMapper;
    @Resource
    private DaterecordinfoMapper daterecordinfoMapper;
    @Override
    public List<Checkiteminfo> findByPageCheck(int page, int pagesize) {
        return checkiteminfoMapper.findByPageCheck((page-1)*pagesize,pagesize);
    }

    @Override
    public List<Checkiteminfo> selectByExample() {
        CheckiteminfoExample example=new CheckiteminfoExample();
        List<Checkiteminfo> list=checkiteminfoMapper.selectByExample(example);
        Checkiteminfo info=list.get(list.size()-1);
        System.out.printf(info.toString());
        return list;
    }
    @Override
    public int selectByExample2(Long recordid) {
        CheckiteminfoExample example=new CheckiteminfoExample();
        example.createCriteria().andRecordidEqualTo(recordid);
        return checkiteminfoMapper.selectByRecordid(example).size();
    }

    @Override
    public int selectByExample3(Long id) {
        //判断巡检项是否被使用了， 如果有就不允许删除   2018年7月16日16:46:40
        CheckitemTaskExample example = new CheckitemTaskExample();
        example.createCriteria().andCheckitemidEqualTo(id);
        List<CheckitemTask> checkitemTasks = checkitemTaskMapper.selectByExample(example);
        if (checkitemTasks != null && checkitemTasks.size() > 0)
            return checkitemTasks.size();
        return 0;
    }

    @Override
    public Checkiteminfo queryByRecord(int record) {
        return checkiteminfoMapper.queryByRecord(record);
    }

    @Override
    public Checkiteminfo selectByPrimaryKey(Long id) {
        return checkiteminfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Checkiteminfo record) {

        CheckitemTaskExample example =  new CheckitemTaskExample();
        example.createCriteria().andCheckitemidEqualTo(record.getId());
        List<CheckitemTask> checkitemTasks = checkitemTaskMapper.selectByExample(example);
        Daterecordinfo daterecordinfo = null ;
        if (record.getDaterecord()!=null){
            daterecordinfo = record.getDaterecord();
        }else if ((record.getType() == 2) && (record.getRecordid()!=null)){
            daterecordinfo=daterecordinfoMapper.selectByPrimaryKey(record.getRecordid().intValue());
        }else
        for(CheckitemTask checkitemTask : checkitemTasks){
            //找出关联任务
            Taskplaninfo task =taskplaninfoMapper.selectByPrimaryKey(checkitemTask.getTaskid());
            if(task==null){continue;}
            TaskContent content = JsonUtil.toObject(task.getTaskcontent(),TaskContent.class);
            //遍历任务区域
            for(TaskArea area : content.getAreas()){
                for(int i = 0 ; i < area.getEquipments().size() ; i++){
                    for(int j = 0 ; j < area.getEquipments().get(i).getCheckItems().size();j++){
                            if(area.getEquipments().get(i).getCheckItems().get(j).getItem().getId().equals(record.getId())){
                                if(record.getType()==2){
                                    record.setNormalmin(area.getEquipments().get(i).getCheckItems().get(j).getItem().getNormalmin());
                                    record.setNormalmax(area.getEquipments().get(i).getCheckItems().get(j).getItem().getNormalmax());
                                    record.setLowerwarning(area.getEquipments().get(i).getCheckItems().get(j).getItem().getLowerwarning());
                                    record.setUpperwarning(area.getEquipments().get(i).getCheckItems().get(j).getItem().getUpperwarning());
                                }else if (record.getType()==1){
                                    record.setRecordid(null);
                                    record.setNormalmin(null);
                                    record.setNormalmax(null);
                                    record.setLowerwarning(null);
                                    record.setUpperwarning(null);
                                }
                                record.setDaterecord(daterecordinfo);
                                area.getEquipments().get(i).getCheckItems().get(j).setItem(record);
                            }
                    }
                }
            }
            task.setTaskcontent(JsonUtil.toJSON(content));
            taskplaninfoMapper.updateByPrimaryKeySelective(task);
        }
        int result;
        if(record.getType()==1){
            record.setNormalmin(null);
            record.setNormalmax(null);
            record.setUpperwarning(null);
            record.setLowerwarning(null);
            record.setRecordid(null);
            result=checkiteminfoMapper.updateByPrimaryKeySelective(record);
            System.out.print(record);
        }else{
            result=checkiteminfoMapper.updateByPrimaryKeySelective(record);
        }
        return result;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        //删除所有有无的巡检项
//        CheckitemTaskExample example =  new CheckitemTaskExample();
//        example.createCriteria().andCheckitemidEqualTo(id);
//        List<CheckitemTask> checkitemTasks = checkitemTaskMapper.selectByExample(example);
//        for(CheckitemTask checkitemTask : checkitemTasks){
//            //找出关联任务
//            Taskplaninfo task =taskplaninfoMapper.selectByPrimaryKey(checkitemTask.getTaskid());
//            TaskContent content = JsonUtil.toObject(task.getTaskcontent(), TaskContent.class);
//            //遍历任务区域
//            for (TaskArea area : content.getAreas()) {
//                List<TaskCheckItem> taskCheckItems = new ArrayList<>();
//                for (int i = 0; i < area.getEquipments().size(); i++) {
//                    for (int j = 0; j < area.getEquipments().get(i).getCheckItems().size(); j++) {
//                        if (!area.getEquipments().get(i).getCheckItems().get(j).getItem().getId().equals(id)) {
//                            taskCheckItems.add(area.getEquipments().get(i).getCheckItems().get(j));
//                        }
//                    }
//                    area.getEquipments().get(i).setCheckItems(taskCheckItems);
//                }
//            }
//            task.setTaskcontent(JsonUtil.toJSON(content));
//            taskplaninfoMapper.updateByPrimaryKeySelective(task);
//        }
//        //删除 equiment_task 表中相关信息
//        checkitemTaskMapper.deleteByExample(example);



        //判断巡检项是否被使用了， 如果有就不允许删除   2018年7月16日16:46:40
        CheckitemTaskExample example = new CheckitemTaskExample();
        example.createCriteria().andCheckitemidEqualTo(id);
        List<CheckitemTask> checkitemTasks = checkitemTaskMapper.selectByExample(example);
        if (checkitemTasks != null && checkitemTasks.size() > 0)
            return 2;
        return checkiteminfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Checkiteminfo record) {
        return checkiteminfoMapper.insert(record);
    }

    @Override
    public int countCheck() {
        return checkiteminfoMapper.countCheck();
    }

    @Override
    public List<Checkiteminfo> findByPageCheck2(HashMap<String, Object> map) {
        return checkiteminfoMapper.findByPageCheck2(map);
    }

    @Override
    public int countCheck2(HashMap<String, Object> map) {
        return checkiteminfoMapper.countCheck2(map);
    }

    @Override
    public List<Checkiteminfo> getcheckinfo(HashMap<String, Object> map) {
        return checkiteminfoMapper.getcheckinfo(map);
    }
}
