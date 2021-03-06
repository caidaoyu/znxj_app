package com.niule.znxj.web.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.niule.znxj.core.common.Resources;
import com.niule.znxj.core.entity.JSONResult;
import com.niule.znxj.core.entity.Result;
import com.niule.znxj.core.util.*;
import com.niule.znxj.core.util.json.JsonUtil;
import com.niule.znxj.web.dao.*;
import com.niule.znxj.web.model.*;
import com.niule.znxj.web.model.response.TaskSimpleReport;
import com.niule.znxj.web.model.taskcontent.TaskArea;
import com.niule.znxj.web.model.taskcontent.TaskContent;
import com.niule.znxj.web.model.taskcontent.TaskEquipment;
import com.niule.znxj.web.model.taskresponse.TaskTempRes;
import com.niule.znxj.web.service.CommonService;
import com.niule.znxj.web.service.SendEmailService;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by MrD on 2017/3/22.
 */
@Service(value = "commonService")
public class CommonServiceImpl implements CommonService {

    private String ip = Resources.ApplicationResources.getString("ip");

    @Resource
    private UserinfoMapper userinfoMapper;
    @Resource
    private SystemsettinginfoMapper systemsettinginfoMapper;
    @Resource
    private TaskplaninfoMapper taskplaninfoMapper;
    @Resource
    private TasktempinfoMapper tasktempinfoMapper;
    @Resource
    private QuickreportMapper quickreportMapper;
    @Resource
    private TaskreportinfoMapper taskreportinfoMapper;
    @Resource
    private WarningtasktypeMapper warningtasktypeMapper;
    @Resource
    private TaskstopinfoMapper taskstopinfoMapper;
    @Resource
    private AppversionMapper appversionMapper;
    @Resource
    private KnowledgetypeMapper knowledgetypeMapper;
    @Resource
    private KnowledgeMapper knowledgeMapper;
    @Resource
    private SendEmailService sendEmailService;
    @Resource
    private ReportcontentMapper reportcontentMapper;
    @Resource
    private ContactinfoMapper contactinfoMapper;
    @Resource
    private ExceptionhandlerinfoMapper exceptionhandlerinfoMapper;
    @Resource
    private UploadtaskinfoMapper uploadtaskinfoMapper;
    @Resource
    private  TaskuploadconfigMapper taskuploadconfigMapper;
    @Resource
    private  DangerhandlerinfoMapper dangerhandlerinfoMapper;
    @Resource
    private  AdmininfoMapper admininfoMapper;
    @Resource
    private SiteareainfoMapper siteareainfoMapper;

    @Override
    public Userinfo userLogin(String username, String password) {
        return userinfoMapper.authUser(username, password);
    }
    private static Logger logger = Logger.getLogger(HttpRequestUtil.class);

    @Override
    public Result getLoginConfig() {
        SystemsettinginfoExample systemsettinginfoExample = new SystemsettinginfoExample();
        systemsettinginfoExample.createCriteria().andKeynameIn(Arrays.asList("REMEMBER_NAME", "REMEMBER_PSW", "SHOW_COPYRIGHT", "COPYRIGHT_CONTENT", "REPORTCACHE", "WIFIUPLOAD", "SYSTEMVERSION", "APPVERSION"));


        return new JSONResult<>(systemsettinginfoMapper.selectByExample(systemsettinginfoExample));
    }

    @Override
    public Result getSiteList(Long userId) {
        Siteareainfo siteareainfo = siteareainfoMapper.selectByUserId(userId);
        List<Siteareainfo> siteareainfoList =  siteareainfoMapper.selectSiteListByDistrictId(siteareainfo.getDistrictid());
        for(Siteareainfo info: siteareainfoList){
            if(info.getId()==siteareainfo.getId()){
                info.setCurrentSite("1");
            }
        }
        return new JSONResult<>(siteareainfoList);
    }

    @Override
    public Result getStopReason(Long tasktempid) {
        TaskstopinfoExample taskstopinfoExample = new TaskstopinfoExample();
        taskstopinfoExample.createCriteria().andTasktempidEqualTo(tasktempid);
        return new JSONResult<>(taskstopinfoMapper.selectByExample(taskstopinfoExample));
    }

    @Override
    public Result getExceptionReport(String taskCode) {
        TaskreportinfoExample example = new TaskreportinfoExample();
        example.createCriteria().andTaskcode2EqualTo(taskCode);
        List<Taskreportinfo> taskreportinfos = taskreportinfoMapper.selectByExample(example);
        List<Reportcontent> reportcontents = ReportContentUtils.getReportConentList(reportcontentMapper, taskreportinfos.get(0).getId());

        ArrayList list = new ArrayList();
        for (int i = 0; i < reportcontents.size(); i++) {
            String ss = reportcontents.get(i).getReportstate();
            if (ss.equals("1")) {
                Reportcontent aa = reportcontents.get(i);
                list.add(aa);
            }
        }
        return new JSONResult<>(list);
    }

    @Override
    public Result getLastestAppVersion() {
        return new JSONResult<>(appversionMapper.getLastestAppVersion());
    }

    @Override
    public Result getTaskSimpleReport(String taskCode, Long taskid) {
        int areaCnt = 0, equipmentCnt = 0, itemCnt = 0, errorCnt = 0;
        TaskSimpleReport simpleReport = new TaskSimpleReport();
        //获取任务
        Taskplaninfo taskplaninfo = taskplaninfoMapper.selectByPrimaryKey(taskid);
        TaskContent taskcontent = JsonUtil.toObject(taskplaninfo.getTaskcontent(), TaskContent.class);
        //赋值任务名
        simpleReport.setTaskname(taskplaninfo.getCustomid());
        //区域数
        if(taskcontent!=null && taskcontent.getAreas()!=null){
            areaCnt = taskcontent.getAreas().size();
            for (TaskArea area : taskcontent.getAreas()) {
                //设备数
                equipmentCnt += area.getEquipments().size();
                //巡检项数
                for (TaskEquipment equipment : area.getEquipments()) {
                    itemCnt += equipment.getCheckItems().size();
                }
            }
        }

        //设置区域数 设备数 项目数
        simpleReport.setAreaCnt(areaCnt);
        simpleReport.setEquipmentCnt(equipmentCnt);
        simpleReport.setItemCnt(itemCnt);

        //获取任务报告
        TaskreportinfoExample taskreportinfoExample = new TaskreportinfoExample();
        taskreportinfoExample.createCriteria().andTaskcode2EqualTo(taskCode);
        List<Taskreportinfo> taskreportinfos = taskreportinfoMapper.selectByExample(taskreportinfoExample);
        if (taskreportinfos != null) {
            Taskreportinfo taskreportinfo = taskreportinfos.get(0);
            if (taskreportinfo == null) {
                simpleReport.setUploadState(0);
            } else {
                List<Reportcontent> reportcontents = ReportContentUtils.getReportConentList(reportcontentMapper, taskreportinfo.getId());
                //异常数
                for (Reportcontent reportContent : reportcontents) {
                    if ("1".equals(reportContent.getReportstate())) {
                        errorCnt++;
                    }
                }
                simpleReport.setErrorCnt(errorCnt);
                simpleReport.setTaskcode(taskCode);
                simpleReport.setStarttime(taskreportinfo.getStarttime());
                simpleReport.setEndtime(taskreportinfo.getEndtime());
                simpleReport.setExecuteState(taskreportinfo.getReportstate());
                simpleReport.setUploadState(1);
                return new JSONResult<>(simpleReport);
            }
        }
        return new JSONResult<>("没查到数据");
    }


    /**
     * 获取任务列表
     *
     * @param classId
     * @param type
     * @return
     */
    @Override
    public Result getTasks(Long userId, Long classId, Integer type, Integer state, Integer page, Integer size) {
        TasktempinfoExample example = new TasktempinfoExample();
        TasktempinfoExample.Criteria criteria = example.createCriteria();
        criteria.andStateEqualTo(state);
        criteria.andTypeEqualTo(type);
        criteria.andClassIdEqualto(classId);
        PageHelper.startPage(page, size);
        List<TaskTempRes> dataList = null;
        if (state == 0) {
            HashMap<String, Object> map1 = new HashMap<>();
            map1.put("state",state);
            map1.put("type", type);
            map1.put("classid", classId);
            dataList= taskplaninfoMapper.getTakTempsExecutingByExample2(map1);
        } else if(state==1) {
            criteria.andUseridEqualTo(userId);
            dataList= taskplaninfoMapper.getTakTempsExecutingByExample(example);
        }else{
            criteria.andExecutetimeGreaterThan(DateUtils.getWholePointDate(7));
            example.setOrderByClause("t1.executetime desc");
            dataList= taskplaninfoMapper.getTakTempsExecutingByExample(example);
        }
        return new JSONResult<>(new PageInfo<>(dataList));
    }


    /**
     * 设置任务状态
     *
     * @param
     * @param state          执行状态 0 未执行 1 进行中 2 已完成
     * @param operationstate 0 未执行 1 漏检 2跳检 3正常 4超时
     * @param stopstate      0 无 1 主动终止 2 被动终止
     */
    @Override
    public synchronized Result setTaskState(Long userId, Long tempId, Integer state, Integer operationstate, Integer stopstate) {
        Tasktempinfo tasktempinfo = tasktempinfoMapper.selectByPrimaryKey(tempId);
        if (tasktempinfo == null) {
            return new JSONResult<>("子任务不存在");
        }
        if (tasktempinfo.getOperationstate() != 0 && tasktempinfo.getOperationstate() != 5) {
            return new JSONResult<>("操作失败，任务已上传，不能重复设置任务状态");
        }
        tasktempinfo.setState(state);
        tasktempinfo.setUserid(userId);
        tasktempinfo.setOperationstate(operationstate);
        tasktempinfo.setStopstate(stopstate);
        tasktempinfo.setUpdatetime(new Date());
        tasktempinfoMapper.updateByPrimaryKeySelective(tasktempinfo);
        if (operationstate == 5) { // 部分执行任务返回
            TaskreportinfoExample example = new TaskreportinfoExample();
            TaskreportinfoExample.Criteria criteria = example.createCriteria();
            criteria.andTaskcodeEqualTo(tasktempinfo.getTaskcode());
            Taskreportinfo taskreportinfo = null;
            List<TaskreportinfoWithBLOBs> list = taskreportinfoMapper.selectByExampleWithBLOBs(example);
            if (list != null && list.size() > 0) { //继续执行分段任务taskreportinfo.getContent()
                taskreportinfo = list.get(0);
                if (taskreportinfo.getContent() != null && !taskreportinfo.getContent().isEmpty()) {
                    Object o = JSONUtils.parse(taskreportinfo.getContent());
                    JSONObject jsonObject = JSONObject.fromObject(o);
                    jsonObject.put("logcat", taskreportinfo.getLogcat());
                    return new JSONResult<>(state == 1 ? jsonObject : null);
                } else { //分段任务上传没有报告
                    Object o = JSONUtils.parse(taskplaninfoMapper.selectByPrimaryKey(tasktempinfo.getTaskid()).getTaskcontent());
                    JSONObject jsonObject = JSONObject.fromObject(o);
                    jsonObject.put("logcat", taskreportinfo.getLogcat());
                    return new JSONResult<>(state == 1 ? jsonObject : null);
                }
            } else { //分段任务上传没有报告
                Object o = JSONUtils.parse(taskplaninfoMapper.selectByPrimaryKey(tasktempinfo.getTaskid()).getTaskcontent());
                return new JSONResult<>(state == 1 ? o : null);
            }
        } else {//app 37及以下版本才有在
            return new JSONResult<>(state == 1 ? (JSONUtils.parse(taskplaninfoMapper.selectByPrimaryKey(tasktempinfo.getTaskid()).getTaskcontent())) : null);
        }
    }

    /**
     * 任务终止
     *
     * @param userId
     * @param tempId
     * @param reason
     * @param content
     * @return
     */
    @Override
    public synchronized Result taskStop(Long userId, Long tempId, String reason, String content, String classname, String
            directorname) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Tasktempinfo tasktempinfo = tasktempinfoMapper.selectByPrimaryKey(tempId);
        if (tasktempinfo.getState() == 3)
            return new JSONResult<>("操作失败，请勿重复终止任务");
        tasktempinfo.setState(3);
        tasktempinfo.setUserid(userId);
        tasktempinfo.setUpdatetime(new Date());
        tasktempinfoMapper.updateByPrimaryKeySelective(tasktempinfo);
        Taskstopinfo info = new Taskstopinfo();
        info.setTasktempid(tempId);
        info.setReason(reason);
        info.setContent(content);
        info.setStoptime(new Date());
        info.setClassname(classname);
        info.setDirectorname(directorname);
        taskstopinfoMapper.insert(info);
        return new JSONResult<>();
    }

    /**
     * 生成任务附表(taskteminfo)
     *
     * @return
     */
    @Override
    public void generate() {

        TaskplaninfoExample example = new TaskplaninfoExample();
        example.createCriteria().andStateEqualTo(1);
        Tasktempinfo temp;
        for (Taskplaninfo info : taskplaninfoMapper.selectByExample(example)) {
            if (!info.getWeeklytime().contains(getWeekStr()))
                continue;
            List<String> timeList = (List<String>) JSONUtils.parse(info.getImplementtime());
            int i = 1;
            for (String str : timeList) {
                SimpleDateFormat dtf = new SimpleDateFormat("yyyyMMdd");
                String code = info.getIdentifyingid() + "-" + info.getTaskversion() + "-" + dtf.format(new Date()) + "-" + i;
                ++i;
                TasktempinfoExample tasktempinfoExample = new TasktempinfoExample();
                tasktempinfoExample.createCriteria().andTaskcodeEqualTo(code);
                List<Tasktempinfo> tasktempinfoList = tasktempinfoMapper.selectByExample(tasktempinfoExample);
                if (tasktempinfoList == null || tasktempinfoList.size() == 0) {
                    temp = new Tasktempinfo(info.getId(), DateUtils.setTime(StringUtils.getHours(str), StringUtils.getMinutes(str)), 0, code, info.getType(), new Date());
                    tasktempinfoMapper.insertSelective(temp);
                    System.out.println("生成任务附表:" + code);
                }
            }
        }
    }

    /**
     * 查看任务的执行状态
     *
     * @param taskcode
     * @return
     */
    @Override
    public Result getTaskTempState(String taskcode) {
        TasktempinfoExample tasktempinfoExample = new TasktempinfoExample();
        tasktempinfoExample.createCriteria().andTaskcodeEqualTo(taskcode);
        return new JSONResult<>(tasktempinfoMapper.selectByExample(tasktempinfoExample));
    }

    /**
     * 生成任务附表
     *
     * @return
     */
    @Override
    public void generateByTask(Taskplaninfo info) {
        if (!info.getWeeklytime().contains(getWeekStr()))
            return;
        Tasktempinfo temp;
        List<String> timeList = (List<String>) JSONUtils.parse(info.getImplementtime());
        int i = 1;
        for (String str : timeList) {
            SimpleDateFormat dtf = new SimpleDateFormat("yyyyMMdd");
            String code = info.getIdentifyingid() + "-" + info.getTaskversion() + "-" + dtf.format(new Date()) + "-" + i;
            ++i;
            temp = new Tasktempinfo(info.getId(), DateUtils.setTime(StringUtils.getHours(str), StringUtils.getMinutes(str)), 0, code, info.getType(), new Date());
            tasktempinfoMapper.insertSelective(temp);
        }
    }

    /**
     * 上传任务报告
     *
     * @return
     */
    @Override
    public Result uploadReport(final Taskreportinfo taskreportinfo) throws Exception {
        System.out.print("====================taskcode:" + taskreportinfo.getTaskcode());
        System.out.print("====================content:" + taskreportinfo.getContent());
        if (taskreportinfo.getEndtime() == null)
            taskreportinfo.setDonetime(new Date());
        else
            taskreportinfo.setDonetime(taskreportinfo.getEndtime());
        if (taskreportinfo.getStarttime() == null)
            taskreportinfo.setStarttime(new Date());
        else
            taskreportinfo.setStarttime(taskreportinfo.getStarttime());
        taskreportinfo.setUploadedtime(new Date());
        taskreportinfo.setUploadtime(new Date());
        taskreportinfo.setExamstate(0);
        taskreportinfo.setExamtime(null);
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //删除已有的相同 taskckcode 报告和 reportContent
        TaskreportinfoExample example = new TaskreportinfoExample();
        example.createCriteria().andTaskcodeEqualTo(taskreportinfo.getTaskcode());
        List<Taskreportinfo> oldReportInfoList = taskreportinfoMapper.selectByExample(example);
        if(oldReportInfoList!=null && oldReportInfoList.size()>0){
            taskreportinfoMapper.deleteByExample(example);
            for(Taskreportinfo info : oldReportInfoList){
                ReportcontentExample reportcontentExample = new ReportcontentExample();
                reportcontentExample.createCriteria().andReportidEqualTo(info.getId());
                reportcontentMapper.deleteByExample(reportcontentExample);
            }
        }

        //不存在子任务 上传失败
        TasktempinfoExample example1 = new TasktempinfoExample();
        example1.createCriteria().andTaskcodeEqualTo(taskreportinfo.getTaskcode());
        if (tasktempinfoMapper.countByExample(example1) == 0)
            return new JSONResult<>("操作失败，子任务不存在");

        Result result;
        if (taskreportinfo.getOperationstate() == 5) { //任务部分上传不插入报告内容
            result = taskreportinfoMapper.insert(taskreportinfo) == 1 ? new JSONResult<>() : new JSONResult<>("操作失败");
            return result;
        }
        final String content1 = taskreportinfo.getContent();
        taskreportinfo.setContent(null);   //任务上传完成后，去掉这个值，节省空间
        result = taskreportinfoMapper.insert(taskreportinfo) == 1 ? new JSONResult<>() : new JSONResult<>("操作失败");

        //判断任务是否需要上传，如果需要则初始化巡检记录上传情况表（uploadtaskinfo）
        final Taskuploadconfig taskuploadconfig = taskuploadconfigMapper.selectByTaskId(taskreportinfo.getTaskid());
        if(taskuploadconfig!=null){
            //删除已有的上传记录（reportid）
            UploadtaskinfoExample exampleupload = new UploadtaskinfoExample();
            exampleupload.createCriteria().andReportidEqualTo(taskreportinfo.getId());
            uploadtaskinfoMapper.deleteByExample(exampleupload);
            ExceptionhandlerinfoExample examplehandler = new ExceptionhandlerinfoExample();
            examplehandler.createCriteria().andReportidEqualTo(taskreportinfo.getId());
            exceptionhandlerinfoMapper.deleteByExample(examplehandler);

            Uploadtaskinfo uploadtaskinfo = new Uploadtaskinfo();
            uploadtaskinfo.setSiteid(taskuploadconfig.getSiteid());
            uploadtaskinfo.setTaskid(taskuploadconfig.getTaskid());
            uploadtaskinfo.setReportid(taskreportinfo.getId());
            uploadtaskinfo.setEnginetype(taskuploadconfig.getEnginetype());
            String address = "http://"+taskuploadconfig.getIp()+":"+taskuploadconfig.getPort()+"/";
            uploadtaskinfo.setAddress(address);
            uploadtaskinfo.setEmail(taskuploadconfig.getEmail());
            uploadtaskinfo.setCreatetime(new Date());
            uploadtaskinfo.setState(0);
            uploadtaskinfoMapper.insert(uploadtaskinfo);
            System.out.println("======初始化巡检记录表，报告ID:"+taskreportinfo.getId());
        }

        //查询历史报告（自动复核）
        List<Taskreportinfo> taskreportinfos = null;
        try {
            taskreportinfos = getTaskCode2(taskreportinfo.getTaskcode());
        } catch (Exception e) {
            e.printStackTrace();
        }

        ThreadPoolExecutorFactory.getInstance().run(new Runnable() {
            @Override
            public void run() {
                Boolean hasException = false;
                String content = "{" + "\"res\":" + content1 + "}";
                if (content != null) {
                    //插入报告内容单项 到 reportcontent
                    TaskReportRes res = JsonUtil.toObject(content, TaskReportRes.class);
                    for (TaskReportContent item : res.getRes()) {
                        //寻找错误的状态项数据
                        if ("1".equals(item.getReportstate()) && "状态项".equals(item.getChecktype()) &&
                                (item.getErrcontent() == null || item.getErrcontent().isEmpty())){
                            logger.debug("app端上传了错误的状态项数据->errcontent为空,reportid->" + taskreportinfo.getId());
                        }
                        //寻找normalmax和normalmin相同的数据
                        if (!"-".equals(item.getNormalmax()) && "记录项".equals(item.getChecktype()) &&
                                item.getNormalmax() == item.getNormalmin()){
                            logger.debug("app端上传了错误的记录项数据->normalmax和normalmin相同,reportid->" + taskreportinfo.getId());
                        }
                        String checkitype = item.getChecktype();
                        Float normalmin = item.getNormalmin();
                        Float normalmax = item.getNormalmax();
                        Float lowerwarning = item.getLowerwarning();
                        Float upperwarning = item.getUpperwarning();
                        String a = "";
                        String e = "";
                        String c = "";
                        String d = "";
                        if (checkitype.equals("记录项")) {
                            if (normalmin == null || normalmin == 0) {
                                a = "-";
                            } else
                                a = normalmin + "";
                            if (normalmax == null || normalmax == 0) {
                                e = "-";
                            } else
                                e = normalmax + "";
                            if (lowerwarning == null || lowerwarning == 0) {
                                c = "-";
                            } else
                                c = lowerwarning + "";
                            if (upperwarning == null || upperwarning == 0) {
                                d = "-";
                            } else
                                d = upperwarning + "";
                            if(item.getNumvalue()!=null && !"".equals(item.getNumvalue())){
                                Float numvalue = Float.valueOf(item.getNumvalue());
                                if (!"".equals(a) && !"-".equals(a) && !"".equals(e) && !"-".equals(e)){
                                    if(numvalue >normalmax || numvalue<normalmin){
                                        item.setReportstate("1");
                                    }
                                }
                            }
                        } else if (checkitype.equals("状态项")) {
                            a = "-";
                            e = "-";
                            c = "-";
                            d = "-";
                        } else if (checkitype.equals("枚举项")) {
                            a = "-";
                            e = "-";
                            c = "-";
                            d = "-";
                        }

                        Reportcontent reportcontent = new Reportcontent(
                                item.getAreaname(),
                                item.getEquipname(),
                                item.getCheckname(),
                                item.getChecktype(),
                                item.getNumvalue(),
                                item.getRecordname(),
                                item.getUnitname(),
                                item.getReportstate(),
                                item.getErrcontent(),
                                item.getAreaskip(),
                                item.getEquipmentskip(),
                                item.getAreaskipdesc(),
                                item.getEquipmentskipdesc(),
                                sdf.format(item.getOperationtime()),
                                taskreportinfo.getId(),
                                c,
                                e,
                                a,
                                d,
                                JsonUtil.toJSON(item.getVideo()),
                                JsonUtil.toJSON(item.getAudio()),
                                JsonUtil.toJSON(item.getImg()),
                                item.getEnumitem(),
                                item.getEquipinouttime(),
                                item.getAreainouttime(),
                                item.getUserid()
                        );
                        reportcontent.setReportid(taskreportinfo.getId());
                        reportcontentMapper.insert(reportcontent);
                        //初始化异常巡检项汇总表（exceptionhandlerinfo） qbxu add 20191113
                        if(reportcontent.getReportstate()!=null && "1".equals(reportcontent.getReportstate())){
                            Exceptionhandlerinfo exceptionhandlerinfo = new Exceptionhandlerinfo();
                            exceptionhandlerinfo.setReportid(taskreportinfo.getId());
                            exceptionhandlerinfo.setReportcontentid(reportcontent.getId().longValue());
                            exceptionhandlerinfo.setReporttime(new Date());
                            exceptionhandlerinfo.setExceptionstate(0);
                            exceptionhandlerinfo.setUploadstate(0);
                            exceptionhandlerinfoMapper.insert(exceptionhandlerinfo);
                        }

                        if (!hasException && "1".equals(item.getReportstate())) {
                            hasException = true;
                        }
                    }
                }
                //存在异常发送邮件
                if (hasException) {
                    sendExceptionEmail(taskreportinfo.getId(), taskreportinfo.getTaskid());
                }
            }
        });

        if (taskreportinfos != null && taskreportinfos.size() > 0) {
            TaskreportinfoWithBLOBs taskreportinfoWithBLOBs = new TaskreportinfoWithBLOBs();
            taskreportinfoWithBLOBs.setId(taskreportinfos.get(0).getId());
            taskreportinfoWithBLOBs.setExamstate(2);
            taskreportinfoWithBLOBs.setExamtime(new Date());
            int updCheckTime = taskreportinfoMapper.updateByPrimaryKeySelective(taskreportinfoWithBLOBs);
            if (updCheckTime > 0) {
                ReportcontentExample example2 = new ReportcontentExample();
                example2.createCriteria().andReportidEqualTo(taskreportinfos.get(0).getId());
                List<Reportcontent> reportcontents = reportcontentMapper.selectByExample(example2);
                for (Reportcontent reportcontent : reportcontents) {
                    reportcontent.setCheckvalue(reportcontent.getNumvalue());
                    reportcontentMapper.updateByPrimaryKeySelective(reportcontent);
                }
            }
        }
        return result;
    }

    /**
     * 发送异常邮件
     */
    public void sendExceptionEmail(Long reportId, Long taskId) {
        try {
            //获取发件人邮箱和授权码
            List<Sendemail> sendemails = getSendExceptionList(taskId);
            for (Sendemail send : sendemails) {
                //获取要发送的邮箱列表
                String[] contactidsstr = send.getContactid().split(",");
                List<Long> contactids = new ArrayList<>();
                for (String contactid : contactidsstr) {
                    contactids.add(Long.parseLong(contactid));
                }
                List<Contactinfo> contactinfolist = queryContactEmail(contactids);
                List<String> emails = new ArrayList<>();
                for (Contactinfo item : contactinfolist) {
                    emails.add(item.getEmail());
                }
                String content = "http://" + ip + "/exceptionReport?reportId=" + reportId;
                String res = HttpRequestUtil.get(content, null, 3000, 60000, "utf-8");
                EmailUtils.sendEmails(send.getEmail(), send.getPwd(), send.getSmtpAddress(), send.getSmtpPort(), (String[]) emails.toArray(new String[emails.size()]), "智能巡检异常报告", res);
            }
        } catch (Exception ex) {
            System.err.println("邮件发送失败的原因是：" + ex.getMessage());
            System.err.println("具体的错误原因");
            ex.printStackTrace(System.err);
        }
    }

    public List<Taskreportinfo> getTaskCode(String taskCode) throws Exception {
        String code = taskCode.substring(0, taskCode.lastIndexOf("-"));
        //得到当前子任务是第几次执行
        Integer i = Integer.valueOf(taskCode.substring(taskCode.lastIndexOf("-") + 1));
        String oldTaskCode = "";
        if (i != 1) {
            oldTaskCode = code + "-" + (i - 1);
        } else {
            //如果当前子任务是第一次执行，获取到昨天当前任务最后一次执行的任务报告
            int index = taskCode.indexOf("-");
            //根据第一个点的位置 获得第二个点的位置
            index = taskCode.indexOf("-", index + 1);
            int taskNameIndex = taskCode.indexOf("-");
            String taskName = taskCode.substring(0, taskNameIndex);//截取任务号
            String time = taskCode.substring(index + 1, taskCode.lastIndexOf("-"));//截取时间
            //时间减取一天
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Date date = sdf.parse(time);
            Calendar now = Calendar.getInstance();
            now.setTime(date);
            now.set(Calendar.DATE, now.get(Calendar.DATE) - 1);
            String time2 = sdf.format(now.getTime());
            //查询昨天的所有报告
            TaskreportinfoExample example = new TaskreportinfoExample();
            example.createCriteria().andTaskcode2Like(taskName + "%" + time2 + "%");
            List<Taskreportinfo> taskreportinfos = taskreportinfoMapper.selectByExample(example);
            Integer k = null;
            String taskversion = "";
            if (taskreportinfos.size() > 0) {
                String newTaskCode = taskreportinfos.get(0).getTaskcode();
                int aa = newTaskCode.indexOf("-");
                int bb = newTaskCode.indexOf("-", aa + 1);
                taskversion = newTaskCode.substring(aa + 1, bb);//昨天的任务版本
                List<Integer> counts = new ArrayList<>();
                for (Taskreportinfo taskreportinfo : taskreportinfos) {
                    String taskcode2 = taskreportinfo.getTaskcode();
                    Integer j = Integer.valueOf(taskcode2.substring(taskcode2.lastIndexOf("-") + 1));
                    counts.add(j);
                }
                k = Collections.max(counts);
            }
            if (k == null)
                oldTaskCode = null;
            else
                oldTaskCode = taskName + "-" + taskversion + "-" + time2 + "-" + k;//昨天最后一个子任务
        }
        List<Taskreportinfo> taskreportinfos = new ArrayList<>();
        if (oldTaskCode != null) {
            TaskreportinfoExample taskreportinfoExample = new TaskreportinfoExample();
            taskreportinfoExample.createCriteria().andTaskcode2EqualTo(oldTaskCode).andExamstateEqualTo(0);
            taskreportinfos = taskreportinfoMapper.selectByExample(taskreportinfoExample);
        }
        return taskreportinfos;
    }

    public List<Taskreportinfo> getTaskCode2(String taskCode) throws Exception {

        //如果当前子任务是第一次执行，获取到昨天当前任务最后一次执行的任务报告
        int index = taskCode.indexOf("-");
        //根据第一个点的位置 获得第二个点的位置
        index = taskCode.indexOf("-", index + 1);
        int taskNameIndex = taskCode.indexOf("-");
        String taskName = taskCode.substring(0, taskNameIndex);//截取任务号
        String time = taskCode.substring(index + 1, taskCode.lastIndexOf("-"));//截取时间
        String endTime = time.substring(0, 4) + "-" + time.substring(4, 6) + "-" + time.substring(6, 8);

        //时间减取一天
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(endTime);

        //如何添加数据时，没有选择开始时间和结束时间，则在创建时间上加上十年
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -2);
        date = calendar.getTime();
        String startTime = sdf.format(date);

        //查询当天和昨天和前天的所有报告
        HashMap<String, Object> map = new HashMap<>();
        map.put("taskName", "%" + taskName + "%");
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        List<Taskreportinfo> taskreportinfos = taskreportinfoMapper.selectByExample2(map);

        if (taskreportinfos != null && taskreportinfos.size() > 0) {
            Iterator<Taskreportinfo> iterator = taskreportinfos.iterator();
            while (iterator.hasNext()) {
                Taskreportinfo taskreportinfo = iterator.next();
                if (taskreportinfo.getExamstate() != 0 || taskCode.equals(taskreportinfo.getTaskcode())) {
                    iterator.remove();
                }
            }
        }

        return taskreportinfos;
    }

    @Override
    public Result uploadQuickReport(Quickreport quickreport) {
        Result result = quickreportMapper.insertSelective(quickreport) == 1 ? new JSONResult<>() : new JSONResult<>("上传失败");
        //add qbxu 20191119(上传隐患报告时初始化隐患闭环处理表)
        Dangerhandlerinfo dangerhandlerinfo = new Dangerhandlerinfo();
        dangerhandlerinfo.setReportid(quickreport.getId());
        dangerhandlerinfo.setReporttime(new Date());
        dangerhandlerinfo.setDangerstate(0);
        dangerhandlerinfoMapper.insertSelective(dangerhandlerinfo);
        //发送邮件给厂区复核员
        sendDangerEmail(quickreport.getId());
        return  result;
    }

    public void sendDangerEmail(Long reportid) {
        try {
            Quickreport quickreport = quickreportMapper.selectByPrimaryKey2(reportid);
            //查询对应厂区的复核员email
            List<Admininfo>  userList =admininfoMapper.selectByExampleBySiteId(quickreport.getSite().getId());

            //获取发件人邮箱和授权码
            List<Sendemail> sendemails = querySendEmailByType(0); //按照日报的获取
            List<String> emails = new ArrayList<>();
            if(sendemails!=null && sendemails.size()>0){
                Sendemail sendemail = sendemails.get(0);
                for (Admininfo item : userList) {
                    if(!item.getEmail().isEmpty()){
                        emails.add(item.getEmail());
                    }
                }
                String content = "隐患任务报告为："+quickreport.getReportcode()+" 的任务已上传，请及时登录巡检系统查看！";
                EmailUtils.sendEmails(sendemail.getEmail(), sendemail.getPwd(), sendemail.getSmtpAddress(), sendemail.getSmtpPort(), (String[]) emails.toArray(new String[emails.size()]), "隐患巡检报告", content);
            }

        } catch (Exception ex) {
            System.err.println("邮件发送失败的原因是：" + ex.getMessage());
            System.err.println("具体的错误原因");
            ex.printStackTrace(System.err);
        }
    }

    @Override
    public Result getWarningTypeOrLevel(int type) {
        WarningtasktypeExample example = new WarningtasktypeExample();
        example.createCriteria().andTypeEqualTo(type);
        return new JSONResult<>(warningtasktypeMapper.selectByExample(example));
    }

    @Override
    public List<Warningtasktype> getWarningTypeOrLevels(int type) {
        WarningtasktypeExample example = new WarningtasktypeExample();
        example.createCriteria().andTypeEqualTo(type);
        return warningtasktypeMapper.selectByExample(example);
    }

    public String getWeekStr() {
        String weekStr;
        Calendar c = Calendar.getInstance();
        switch (c.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.MONDAY:
                weekStr = "周一";
                break;
            case Calendar.TUESDAY:
                weekStr = "周二";
                break;
            case Calendar.WEDNESDAY:
                weekStr = "周三";
                break;
            case Calendar.THURSDAY:
                weekStr = "周四";
                break;
            case Calendar.FRIDAY:
                weekStr = "周五";
                break;
            case Calendar.SATURDAY:
                weekStr = "周六";
                break;
            case Calendar.SUNDAY:
                weekStr = "周日";
                break;
            default:
                weekStr = "周一";
                break;
        }
        return weekStr;
    }

    /**
     * 获取知识库类别
     *
     * @return
     */
    @Override
    public List<Knowledgetype> getKnowledgeType() {
        KnowledgetypeExample example = new KnowledgetypeExample();
        return knowledgetypeMapper.selectByExample(example);
    }

    /**
     * 根据类别获取相关的知识
     *
     * @param typeid 类别编号
     * @return
     */
    @Override
    public List<Knowledge> getKnowledge(int typeid) {
        return knowledgeMapper.getKnowledgeByTypeid(typeid);
    }

    @Override
    public List<Knowledge> getKnowledgeByParam(String str) {
        return knowledgeMapper.getKnowledgeByParam("%" + str + "%");
    }

    @Override
    public List<Tasktempinfo> selectByExample(Long taskid) {
        TasktempinfoExample example = new TasktempinfoExample();
        example.createCriteria().andTaskidEqualTo(taskid);
        return tasktempinfoMapper.selectByExample(example);
    }

    @Override
    public void senddayreport() {
        try {
            //获取发件人邮箱和授权码
            List<Sendemail> sendemails = querySendEmailByType(0);
            for (Sendemail send : sendemails) {
                //获取要发送的邮箱列表
                String[] contactidsstr = send.getContactid().split(",");
                List<Long> contactids = new ArrayList<>();
                for (String contactid : contactidsstr) {
                    contactids.add(Long.parseLong(contactid));
                }
                List<Contactinfo> contactinfolist = queryContactEmail(contactids);
                List<String> emails = new ArrayList<>();
                for (Contactinfo item : contactinfolist) {
                    emails.add(item.getEmail());
                }
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date time1 = DateUtils.getWholePointDate(1);
                String date1 = sdf.format(time1);
                Taskplaninfo taskplaninfo = taskplaninfoMapper.selectByPrimaryKey(send.getTaskid());
                if (taskplaninfo != null) {
                    String content = "http://" + ip + "/dadiyreport?date1=" + date1 + "&taskidstr=" + send.getTaskid();
                    String res = HttpRequestUtil.get(content, null, 3000, 60000, "utf-8");
                    EmailUtils.sendEmails(send.getEmail(), send.getPwd(), send.getSmtpAddress(), send.getSmtpPort(), (String[]) emails.toArray(new String[emails.size()]), "智能巡检日报", res);
                }
            }
        } catch (Exception ex) {
            System.err.println("邮件发送失败的原因是：" + ex.getMessage());
            System.err.println("具体的错误原因");
            ex.printStackTrace(System.err);
        }
    }

    @Override
    public void sendweekemail() {
        try {
            List<Sendemail> sendemails = querySendEmailByType(1);
            for (Sendemail send : sendemails) {
                //获取要发送的邮箱列表
                String[] contactidsstr = send.getContactid().split(",");
                List<Long> contactids = new ArrayList<>();
                for (String contactid : contactidsstr) {
                    contactids.add(Long.parseLong(contactid));
                }
                List<Contactinfo> contactinfolist = queryContactEmail(contactids);
                List<String> emails = new ArrayList<>();
                for (Contactinfo item : contactinfolist) {
                    emails.add(item.getEmail());
                }

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date time1 = DateUtils.getWholePointDate(8);
                String date1 = sdf.format(time1);
                Date time2 = DateUtils.getWholePointDate(1);
                String date2 = sdf.format(time2);
                Taskplaninfo taskplaninfo = taskplaninfoMapper.selectByPrimaryKey(send.getTaskid());
                if (taskplaninfo != null) {
                    String content = "http://" + ip + "/weekreport?date1=" + date1 + "&date2=" + date2 + "&taskidstr=" + send.getTaskid() + "&type=0";
                    String res = HttpRequestUtil.get(content, null, 3000, 60000, "utf-8");
                    EmailUtils.sendEmails(send.getEmail(), send.getPwd(), send.getSmtpAddress(), send.getSmtpPort(), (String[]) emails.toArray(new String[emails.size()]), "智能巡检周报", res);
                }
            }
        } catch (Exception ex) {
            System.err.println("邮件发送失败的原因是：" + ex.getMessage());
            System.err.println("具体的错误原因");
            ex.printStackTrace(System.err);
        }
    }

    public void sendmonthemail() {
        try {
            List<Sendemail> sendemails = querySendEmailByType(2);
            for (Sendemail send : sendemails) {
                //获取要发送的邮箱列表
                String[] contactidsstr = send.getContactid().split(",");
                List<Long> contactids = new ArrayList<>();
                for (String contactid : contactidsstr) {
                    contactids.add(Long.parseLong(contactid));
                }
                List<Contactinfo> contactinfolist = queryContactEmail(contactids);
                List<String> emails = new ArrayList<>();
                for (Contactinfo item : contactinfolist) {
                    emails.add(item.getEmail());
                }
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date time1 = DateUtils.getWholePointDate(31);
                String date1 = sdf.format(time1);
                Date time2 = DateUtils.getWholePointDate(1);
                String date2 = sdf.format(time2);
                Taskplaninfo taskplaninfo = taskplaninfoMapper.selectByPrimaryKey(send.getTaskid());
                if (taskplaninfo != null) {
                    String content = "http://" + ip + "/weekreport?date1=" + date1 + "&date2=" + date2 + "&taskidstr=" + send.getTaskid() + "&type=1";
                    String res = HttpRequestUtil.get(content, null, 3000, 60000, "utf-8");
                    EmailUtils.sendEmails(send.getEmail(), send.getPwd(), send.getSmtpAddress(), send.getSmtpPort(), (String[]) emails.toArray(new String[emails.size()]), "智能巡检月报", res);
                }
            }
        } catch (Exception ex) {
            System.err.println("邮件发送失败的原因是：" + ex.getMessage());
            System.err.println("具体的错误原因");
            ex.printStackTrace(System.err);
        }
    }

    public List<Contactinfo> queryContactEmail(List<Long> conids) {
        ContactinfoExample example = new ContactinfoExample();
        example.createCriteria().andIdIn(conids);
        return contactinfoMapper.selectByExample(example);
    }

    public List<Sendemail> querySendEmailByTypeAndTask(int type, Long taskid) {
        SendemailExample sendemailExample = new SendemailExample();
        sendemailExample.createCriteria().andTypeEqualTo(type).andTaskidEqualTo(taskid);
        List<Sendemail> sendemails = sendEmailService.selectByExample(sendemailExample);
        return sendemails;
    }

    public List<Sendemail> querySendEmailByType(int type) {
        SendemailExample sendemailExample = new SendemailExample();
        sendemailExample.createCriteria().andTypeEqualTo(type);
        List<Sendemail> sendemails = sendEmailService.selectByExample(sendemailExample);
        return sendemails;
     }

    /**
     * 获取需要发送异常的邮箱
     *
     * @return
     */
    public List<Sendemail> getSendExceptionList(Long taskId) {
        SendemailExample sendemailExample = new SendemailExample();
        sendemailExample.createCriteria().andTypeEqualTo(3).andTaskidEqualTo(taskId);
        List<Sendemail> sendemails = sendEmailService.selectByExample(sendemailExample);
        return sendemails;
    }

    @Override
    public void automaticExamine() {
        TaskreportinfoExample taskreportinfoExample = new TaskreportinfoExample();
        taskreportinfoExample.createCriteria().andExamstateEqualTo(0);
        List<Taskreportinfo> taskreportinfos = taskreportinfoMapper.selectByExample(taskreportinfoExample);
        Date now = new Date();
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        if (taskreportinfos.size() > 0) {
            for (Taskreportinfo taskreportinfo : taskreportinfos) {
                long diff = now.getTime() - taskreportinfo.getDonetime().getTime();
                // 计算差多少天
                long day = diff / nd;
                // 计算差多少小时
                long hour = diff % nd / nh;
                long hours = day * 24 + hour;
                if (hours > 36) {
                    TaskreportinfoWithBLOBs taskreportinfoWithBLOBs = new TaskreportinfoWithBLOBs();
                    taskreportinfoWithBLOBs.setId(taskreportinfo.getId());
                    taskreportinfoWithBLOBs.setExamstate(2);
                    taskreportinfoWithBLOBs.setExamtime(new Date());
                    int updCheckTime = taskreportinfoMapper.updateByPrimaryKeySelective(taskreportinfoWithBLOBs);
                    ReportcontentExample example = new ReportcontentExample();
                    example.createCriteria().andReportidEqualTo(taskreportinfo.getId());
                    List<Reportcontent> reportcontents = reportcontentMapper.selectByExample(example);
                    for (Reportcontent reportcontent : reportcontents) {
                        reportcontent.setCheckvalue(reportcontent.getNumvalue());
                        reportcontentMapper.updateByPrimaryKeySelective(reportcontent);
                    }
                }
            }
        }
    }

    @Override
    public void sendReportEmail(Long taskid, Long reportid) {
        try {
            //获取发件人邮箱和授权码
            List<Sendemail> sendemails = querySendEmailByTypeAndTask(3,taskid);
            for (Sendemail send : sendemails) {
                //获取要发送的邮箱列表
                String[] contactidsstr = send.getContactid().split(",");
                List<Long> contactids = new ArrayList<>();
                for (String contactid : contactidsstr) {
                    contactids.add(Long.parseLong(contactid));
                }
                List<Contactinfo> contactinfolist = queryContactEmail(contactids);
                List<String> emails = new ArrayList<>();
                for (Contactinfo item : contactinfolist) {
                    emails.add(item.getEmail());
                }
                String content = "http://" + ip + "/exceptionReport?reportId=" + reportid;
                String res = HttpRequestUtil.get(content, null, 3000, 60000, "utf-8");
                EmailUtils.sendEmails(send.getEmail(), send.getPwd(), send.getSmtpAddress(), send.getSmtpPort(), (String[]) emails.toArray(new String[emails.size()]), "智能巡检异常报告", res);
            }
        } catch (Exception ex) {
            System.err.println("邮件发送失败的原因是：" + ex.getMessage());
            System.err.println("具体的错误原因");
            ex.printStackTrace(System.err);
        }
    }

    @Override
    public Result doTask(Long userId, Long taskId) {

        TasktempinfoExample example = new TasktempinfoExample();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        example.createCriteria().andTaskidNoAliasEqualTo(taskId).andExecutetimeDoTaskEqualTo(sdf.format(now));
        int code = tasktempinfoMapper.countByExample(example) + 1;
        Taskplaninfo taskplaninfo = taskplaninfoMapper.selectByPrimaryKey(taskId);
        SimpleDateFormat dtf = new SimpleDateFormat("yyyyMMdd");
        String codeStr = taskplaninfo.getIdentifyingid() + "-" + taskplaninfo.getTaskversion() + "-" + dtf.format(new Date()) + "-" + code;
        Tasktempinfo temp = new Tasktempinfo(taskplaninfo.getId(), userId, new Date(), 1, codeStr, taskplaninfo.getType(), new Date());
        tasktempinfoMapper.insertSelective(temp);
        TasktempinfoExample example1 = new TasktempinfoExample();
        TasktempinfoExample.Criteria criteria = example1.createCriteria();
        criteria.andUseridEqualTo(userId);
        example1.setOrderByClause("t1.executetime asc");
        criteria.andStateEqualTo(1);
        criteria.andTypeEqualTo(taskplaninfo.getType());
        criteria.andClassIdEqualto(taskplaninfo.getClassid());
        List<TaskTempRes> list1 = taskplaninfoMapper.getTakTempsExecutingByExample(example1);
        if (list1 != null && list1.size() > 0) {
            return new JSONResult<>(list1.get(list1.size() - 1));
        } else {
            return new JSONResult<>("没有子任务");
        }
    }

}
