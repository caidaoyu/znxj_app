package com.niule.znxj.web.service;

import com.niule.znxj.core.entity.Result;
import com.niule.znxj.web.model.*;
import org.springframework.stereotype.Service;

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
    //生成任务附表
    void generate();
    //生成任务附表
    void generateByTask(Taskplaninfo info);
    //上传任务报告
    Result uploadReport(Taskreportinfo taskreportinfo)throws Exception;
    //上传即拍即传任务
    Result uploadQuickReport(Quickreport quickreport);

    Result getWarningTypeOrLevel(int type);

    List<Warningtasktype> getWarningTypeOrLevels(int type);

    /*任务终止执行*/
    Result taskStop(Long userId, Long tempId, String reason, String conten, String classname, String directornamet);

    //查看任务的状态
    Result getTaskTempState(String taskcode);

    Result getStopReason(Long tasktempid);

    //获取异常报告
    Result getExceptionReport(String taskCode);

    Result getLastestAppVersion();

    //获取任务简报
    Result getTaskSimpleReport(String taskCode, Long taskid);

    /*获取知识库类别*/
    List<Knowledgetype> getKnowledgeType();
    /*根据类别获取相关的知识*/
    List<Knowledge> getKnowledge(int typeid);

    List<Knowledge>getKnowledgeByParam(String str);

    List<Tasktempinfo> selectByExample(Long taskid);

    //定时发送日报
    void senddayreport();
    //定时发送周报
    void sendweekemail();
    //定时发送月报
    void sendmonthemail();
    //定时自动审核
    void automaticExamine();

    //发送任务报告（异常）
    void sendReportEmail(Long taskid, Long reportid);

    Result doTask(Long userId, Long taskId);

}

