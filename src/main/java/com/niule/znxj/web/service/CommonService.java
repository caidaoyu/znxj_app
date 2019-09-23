package com.niule.znxj.web.service;

import com.niule.znxj.core.entity.Result;
import com.niule.znxj.web.model.*;
import com.niule.znxj.web.model.taskcontent.TaskCheckItem;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Created by MrD on 2017/3/22.
 */
@Service
public interface CommonService {
    //登陆
    Userinfo userLogin(String username, String password);
    //获取登陆配置
    Result getLoginConfig();
    //获取任务列表
    Result getTasks(Long userId, Long classId, Integer type, Integer state, Integer page, Integer size);

    //设置任务状态
    Result setTaskState(Long userId, Long tempId, Integer state, Integer operationstate, Integer stopstate);

    void generate();

    void doGenerate();

    void generateByTask(Taskplaninfo info);


    Result uploadReport(Taskreportinfo taskreportinfo)throws Exception;

    Result uploadQuickReport(Quickreport quickreport);

    Result getWarningTypeOrLevel(int type);

    List<Warningtasktype> getWarningTypeOrLevels(int type);

    /*任务终止执行*/
    Result taskStop(Long userId, Long tempId, String reason, String conten, String classname, String directornamet);

    //查看任务的状态
    Result getTaskTempState(String taskcode);


    Result getStopReason(Long tasktempid);

    Result getExceptionReport(String taskCode);

    Result getLastestAppVersion();

    Result getTaskSimpleReport(String taskCode, Long taskid);

    /*获取知识库类别*/
    List<Knowledgetype> getKnowledgeType();
    /*根据类别获取相关的知识*/
    List<Knowledge> getKnowledge(int typeid);

    List<Knowledge>getKnowledgeByParam(String str);

    List<Tasktempinfo> selectByExample(Long taskid);
    //定时发送日报
    void senddayreport();
    void sendweekemail();
    void sendmonthemail();
    void automaticExamine();

    Result doTask(Long userId, Long taskId);

}

