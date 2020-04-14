package com.niule.znxj.web.controller;

import com.niule.znxj.core.common.Resources;
import com.niule.znxj.core.entity.JSONResult;
import com.niule.znxj.core.entity.Result;
import com.niule.znxj.web.model.Quickreport;
import com.niule.znxj.web.model.Taskreportinfo;
import com.niule.znxj.web.model.Userinfo;
import com.niule.znxj.web.model.response.LoginResponse;
import com.niule.znxj.web.service.CommonService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by MrD on 2017/3/22.
 */
@Controller
public class AppController {
    @Resource
    private CommonService commonService;
    /**
     * 获取登录设置
     * @return
     */
    @RequestMapping(value = "/getLoginConfig",method = RequestMethod.GET)
    @ResponseBody
    public Result getLoginConfig(){
        return commonService.getLoginConfig();
    }
    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    @ResponseBody
    public Result userLogin(String username, String password){
        LoginResponse response = new LoginResponse();
        try {
            Userinfo userinfo = commonService.userLogin(username, password);
            if (userinfo == null)
                return new JSONResult<>("用户名或密码错误");
            response.setUserinfo(userinfo);
            response.setWarningtypes(commonService.getWarningTypeOrLevels(0));
            response.setWarninglevels(commonService.getWarningTypeOrLevels(1));
            response.setStopreasons(commonService.getWarningTypeOrLevels(2));
            return new JSONResult<>(response);
        }catch (Exception e){
            return new JSONResult<>("用户重复");
           // e.printStackTrace();
        }
    }

    /**
     * 获取用户厂区集合
     * @return
     */
    @RequestMapping(value = "/getSiteList",method = RequestMethod.GET)
    @ResponseBody
    public Result getSiteList(Long userId){
        return commonService.getSiteList(userId);
    }

    /**
     * 获取任务列表
     * @param type 任务类型  0日常巡检 1计划巡检 2隐患排查 3视频巡检 4临时任务
     * @param state 执行状态 0 未执行 1 进行中 2 已完成 3 已终止
     * @param classId 所属班组id
     * @param userId 用户id
     */
    @RequestMapping(value = "/task/getTasks",method = RequestMethod.GET)
    @ResponseBody
        public Result getTasks(Long userId,Long classId, Integer type ,Integer state ,Integer page,Integer size){
        return commonService.getTasks(userId,classId,type,state,page,size);
    }

    /**
     * 获取任务的状态
     * @param taskcode
     * @return
     */
    @RequestMapping(value = "/task/getTaskTempState",method = RequestMethod.POST)
    @ResponseBody
    public Result getTaskTempState(String taskcode){
        return commonService.getTaskTempState(taskcode);
    }

    /**
     * 获取终止任务的原因
     * @param tasktempid
     * @return
     */
    @RequestMapping(value = "/task/getTaskStop",method = RequestMethod.POST)
    @ResponseBody
    public Result getTaskStop(Long tasktempid){
        return commonService.getStopReason(tasktempid);
    }
    /**
     * 设置任务执行中
     * @param userId 用户id
     * @param tempId 任务id
     * @param state 执行状态 0 未执行 1 进行中 2 已完成
     */
    @RequestMapping(value = "/task/setTaskState",method = RequestMethod.POST)
    @ResponseBody
    public Result setTaskState(Long userId, Long tempId ,Integer state,Integer operationstate,Integer stopstate){
        return commonService.setTaskState(userId,tempId,state,operationstate,stopstate);
    }

    /**
     * 点击开始执行任务 生成执行中子任务
     */
    @RequestMapping(value = "/task/doTask",method = RequestMethod.POST)
    @ResponseBody
    public Result doTask(Long userId ,Long taskId){
        return commonService.doTask(userId,taskId);
    }

    /**
     * 设置终止任务
     * @param userId 用户id
     * @param tempId 任务id
     *
     */
    @RequestMapping(value = "/task/setTaskStop",method = RequestMethod.POST)
    @ResponseBody
    public Result setTaskStop(Long userId, Long tempId ,String reason,String content,String classname,String directorname){
        return commonService.taskStop(userId,tempId,reason,content,classname,directorname);
    }
    /**
     * 生成任务列表
     */
    @RequestMapping(value = "/task/generate")
    @ResponseBody
    public Result generate(){
        commonService.generate();
        return new JSONResult<>();
    }


    /**
     * 上传任务报告
     */
    @RequestMapping(value = "/task/uploadReport",method = RequestMethod.POST)
    @ResponseBody
    public Result uploadReport(@RequestBody Taskreportinfo taskreportinfo)throws Exception{
        System.out.printf("areainouttime:"+taskreportinfo.getAreainouttime());
        return commonService.uploadReport(taskreportinfo);
    }


    /**
     * 上传任务报告-即拍即传
     */
    @RequestMapping(value = "/task/uploadQuickReport",method = RequestMethod.POST)
    @ResponseBody
    public Result uploadQuickReport(@RequestBody Quickreport quickreport){
        quickreport.setUploadtime(new Date());
        return commonService.uploadQuickReport(quickreport);
    }

    /**
     * 上传文件
     *
     * @param request
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/file/upload", method = RequestMethod.POST)
    @ResponseBody
    public Result upload(HttpServletRequest request) throws ServletException, IOException {
        File file = new File(Resources.ApplicationResources.getString("tempfile.path"));
        List<String> urllist;
        if (!file.exists() && !file.isDirectory()) {
            System.out.println("目录或文件不存在！");
            file.mkdir();
        }
        try {
            //使用Apache文件上传组件处理文件上传步骤：
            //1、创建一个DiskFileItemFactory工厂
            DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
            //设置工厂的缓冲区的大小，当上传的文件大小超过缓冲区的大小时，就会生成一个临时文件存放到指定的临时目录当中。
            diskFileItemFactory.setSizeThreshold(1024 * 100);
            //设置上传时生成的临时文件的保存目录
            diskFileItemFactory.setRepository(file);
            //2、创建一个文件上传解析器
            ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);
            //解决上传文件名的中文乱码
            fileUpload.setHeaderEncoding("UTF-8");
            //监听文件上传进度
            fileUpload.setProgressListener(new ProgressListener() {
                public void update(long pBytesRead, long pContentLength, int arg2) {
                    System.out.println("文件大小为：" + pContentLength + ",当前已处理：" + pBytesRead);
                }
            });
            //3、判断提交上来的数据是否是上传表单的数据
            if (!fileUpload.isMultipartContent(request)) {
                //按照传统方式获取数据
                return new JSONResult<>("上传失败");
            }
            //设置上传单个文件的大小的最大值，目前是设置为1024*1024*10字节，也就是10MB
            fileUpload.setFileSizeMax(1024 * 1024 * 10);
            //设置上传文件总量的最大值，最大值=同时上传的多个文件的大小的最大值的和，目前设置为10MB
            fileUpload.setSizeMax(1024 * 1024 * 10);
            //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
            List<FileItem> list = fileUpload.parseRequest(request);
            urllist = new ArrayList<>();
            for (FileItem item : list) {
                //如果fileitem中封装的是普通输入项的数据
                if (item.isFormField()) {
                    String name = item.getFieldName();
                    //解决普通输入项的数据的中文乱码问题
                    String value = item.getString("UTF-8");
                    String value1 = new String(name.getBytes("iso8859-1"), "UTF-8");
                    System.out.println(name + "  " + value);
                    System.out.println(name + "  " + value1);
                } else {
                    //如果fileitem中封装的是上传文件，得到上传的文件名称，
                    String fileName = item.getName();
                    System.out.println(fileName);
                    if (fileName == null || fileName.trim().equals("")) {
                        continue;
                    }
                    //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
                    //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
                    fileName = fileName.substring(fileName.lastIndexOf(File.separator) + 1);
                    //得到上传文件的扩展名
                    String fileExtName = fileName.substring(fileName.lastIndexOf(".") + 1);
                    if ("zip".equals(fileExtName) || "rar".equals(fileExtName) || "tar".equals(fileExtName) || "jar".equals(fileExtName)) {
                        return new JSONResult<>("上传文件的类型不符合！！！");
                    }
                    //如果需要限制上传的文件类型，那么可以通过文件的扩展名来判断上传的文件类型是否合法
                    System.out.println("上传文件的扩展名为:" + fileExtName);
                    //获取item中的上传文件的输入流
                    InputStream is = item.getInputStream();
                    //得到文件保存的名称
                    fileName = mkFileName(fileName);
                    //得到文件保存的路径+url
                    StringBuffer url=new StringBuffer();
                    String savePathStr = mkFilePath(fileExtName,url);
                    System.out.println("保存路径为:" + savePathStr + "url = "+url.toString());
                    //创建一个文件输出流
                    FileOutputStream fos = new FileOutputStream(savePathStr + File.separator + fileName);

                    //创建一个缓冲区
                    byte buffer[] = new byte[1024];
                    //判断输入流中的数据是否已经读完的标识
                    int length = 0;
                    //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
                    while((length = is.read(buffer))>0){
                        //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
                        fos.write(buffer, 0, length);
                    }
                    //关闭输入流
                    is.close();
                    //关闭输出流
                    fos.close();
                    //删除处理文件上传时生成的临时文件
                    item.delete();
                    urllist.add(url.append("/"+fileName).toString());
                }
            }
        } catch (FileUploadBase.FileSizeLimitExceededException e) {
            e.printStackTrace();
            return new JSONResult<>("单个文件超出最大值！！！");
        } catch (FileUploadBase.SizeLimitExceededException e) {
            e.printStackTrace();
            return new JSONResult<>("上传文件的总的大小超出限制的最大值！！！");
        } catch (FileUploadException e) {
            e.printStackTrace();
            return new JSONResult<>("文件上传失败");
        }
        return new JSONResult<>(urllist);
    }

    //生成上传文件的文件名，文件名以：uuid+"_"+文件的原始名称
    public String mkFileName(String fileName) {
        return UUID.randomUUID().toString() + "_" + fileName;
    }

    public String mkFilePath(String fileExtName, StringBuffer url) throws IOException {
        String savePath;
        //0 图片 1 语音 2 视频
        switch (fileExtName) {
            case "jpg":
                savePath = Resources.ApplicationResources.getString("imagefile.path");
                url.append("/images/");
                break;
            case "mp3":
                savePath = Resources.ApplicationResources.getString("voicefile.path");
                url.append("/voice/");
                break;
            case "mp4":
                savePath = Resources.ApplicationResources.getString("videofile.path");
                url.append("/video/");
                break;
            default:
                savePath = Resources.ApplicationResources.getString("imagefile.path");
                url.append("/images/");
                break;
        }
        Calendar now = Calendar.getInstance();
        int dir1 = now.get(Calendar.YEAR);
        int dir2 = now.get(Calendar.MONTH) + 1;
        int dir3 = now.get(Calendar.DAY_OF_MONTH);
        //构造新的保存目录
        String dir = savePath + "//" + dir1 + "//" + dir2 + "//" + dir3 + "//";
        String dirpath1 = savePath + "//" + dir1 + "//";
        String dirpath2 = savePath + "//" + dir1 + "//" + dir2 + "//";
        url.append(dir1).append("/").append(dir2).append("/").append(dir3);
        //File既可以代表文件也可以代表目录
        File file = new File(dir);
        if (!file.exists()) {
            file.mkdirs();
            Runtime.getRuntime().exec("chmod 755 -R " + dirpath1);
            Runtime.getRuntime().exec("chmod 755 -R " + dirpath2);
            Runtime.getRuntime().exec("chmod 755 -R " + dir);
        }
        return dir;
    }

    /**
     * 获取隐患排查类型或等级
     */
    @RequestMapping(value = "/getWarningTaskTypeOrLevel",method = RequestMethod.GET)
    @ResponseBody
    public Result getWarningTaskTypeOrLevel(int type){
        return commonService.getWarningTypeOrLevel(type);
    }


    /**
     * 获取任务异常
     */
    @RequestMapping(value = "/getExceptionReport",method = RequestMethod.POST)
    @ResponseBody
    public Result getExceptionReport(String taskCode){
        return commonService.getExceptionReport(taskCode);
    }

    /**
     * 获取APP最新版本信息
     */
    @RequestMapping(value = "/getLastestAppVersion",method = RequestMethod.GET)
    @ResponseBody
    public Result getLatestAppVersion(){
        return commonService.getLastestAppVersion();
    }

    /**
     * 获取任务简报
     */
    @RequestMapping(value = "/getTaskSimpleReport",method = RequestMethod.POST)
    @ResponseBody
    public Result getTaskSimpleReport(String taskCode,Long taskid){
        return commonService.getTaskSimpleReport(taskCode,taskid);
    }

    /**
     * 获取知识类别
     * @return
     */
    @RequestMapping(value = "/getKnowledgeType",method = RequestMethod.POST)
    @ResponseBody
    public Result getKnowledgeType(){
        return new JSONResult<>(commonService.getKnowledgeType());
    }
    /**
     * 根据类别获取相关的知识
     * @param typeid 类别编号
     * @return
     */
    @RequestMapping(value = "/getKnowledgeByTypeId",method = RequestMethod.POST)
    @ResponseBody
    public Result getKnowledgeByTypeId(int typeid){
        return new JSONResult<>(commonService.getKnowledge(typeid));
    }
    /**
     * 根据参数获取相关的知识
     * @param searchstr 搜索字符串
     * @return
     */
    @RequestMapping(value = "/getKnowledgeByParam",method = RequestMethod.POST)
    @ResponseBody
    public Result getKnowledgeByParam(String searchstr){
        return new JSONResult<>(commonService.getKnowledgeByParam(searchstr));
    }


    @RequestMapping(value = "/testGe",method = RequestMethod.GET)
    @ResponseBody
    public Result testGe(){
        commonService.generate();
        return new JSONResult<>();
    }

}

