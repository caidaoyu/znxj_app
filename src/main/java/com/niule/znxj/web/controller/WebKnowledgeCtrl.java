package com.niule.znxj.web.controller;

import com.github.pagehelper.PageInfo;
import com.niule.znxj.core.common.Resources;
import com.niule.znxj.core.entity.JSONResult;
import com.niule.znxj.core.entity.Result;
import com.niule.znxj.core.util.json.JsonUtil;
import com.niule.znxj.web.model.*;
import com.niule.znxj.web.service.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.aspectj.weaver.ast.Var;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by administor on 2017/5/8.
 */
@Controller
public class WebKnowledgeCtrl {
    @Resource
    private KonwledgeService konwledgeService;
    @Resource
    private KonwledgeTypeService konwledgeTypeService;
    @Resource
    private EquipmentService equipmentService;
    @Resource
    private SiteService siteService;
    @Resource
    private AreaService areaService;
    @Resource
    private OperateLogService operateLogService;

    private String ip = Resources.ApplicationResources.getString("ip");

    @RequestMapping(" showknowledge")
    @RequiresPermissions("item:knowledge")
    public  String showknowledge(int page,Model m,String title,HttpServletRequest request){
        if(page<=0){page=1;}
        int size=15;
        Admininfo admininfo=(Admininfo) request.getSession().getAttribute("userInfo");
        String title2="";
        if(title==null||title.equals("")){
            title2="";
        }else{
            title2="%"+title+"%";
        }
        HashMap<String,Object> map=new HashMap<>();
        map.put("page",page);
        map.put("size",size);
        map.put("title",title2);
        map.put("siteid",admininfo.getSiteid());
        PageInfo<Knowledge> info=null;
//        if(title==null||"".equals(title)){
//            info= new PageInfo<>(konwledgeService.selectByExample(map));
//        }
//        else {
           info = new PageInfo<>(konwledgeService.selectByExample1(map));
//       }
        m.addAttribute("info",info);
        m.addAttribute("title",title);
        m.addAttribute("siteid",admininfo.getSiteid());
        return "knowledge/showknowledge";
    }
    @RequestMapping("toaddknowledge")
    @RequiresPermissions("add:knowledge")
    public String toaddknowledge(Model m,HttpServletRequest request){
        List<Knowledgetype> knowledgetypes=konwledgeTypeService.selectByExample1();
        EquipmentinfoExample equipmentinfoExample=new EquipmentinfoExample();
        List<Equipmentinfo> equipmentinfos=equipmentService.selectByExample(equipmentinfoExample);
        Admininfo admininfo=(Admininfo) request.getSession().getAttribute("userInfo");
        List<Siteareainfo> siteareainfos=siteService.selectByExample2(admininfo.getSiteid());
        List<Areainfo> areainfos=areaService.selectByExample1(admininfo.getSiteid());

        m.addAttribute("knowledgetypes",knowledgetypes);
        m.addAttribute("equipmentinfos",equipmentinfos);
        m.addAttribute("siteareainfos",siteareainfos);
        m.addAttribute("areainfos",areainfos);

        return "knowledge/addknowledge2";
    }
    @RequestMapping("addknowledges")
    @ResponseBody
    public int addknowledges(Knowledge knowledge){
        List<String> attach=new ArrayList<>();
        String [] stringArr= knowledge.getAttachment().split(",");
        for(int i=0;i<stringArr.length;i++){
            attach.add(stringArr[i]);
        }
        knowledge.setAttachment(JsonUtil.toJSON(attach));
       int result=konwledgeService.insert(knowledge);
        return result;
    }
//    public Result addknowledges(HttpServletRequest request) throws ServletException, IOException {
//        HttpSession session=request.getSession();
//        Knowledge knowledge=new Knowledge();
//        File file = new File(Resources.ApplicationResources.getString("tempfile.path"));
//        List<String> urllist;
//        if (!file.exists() && !file.isDirectory()) {
//            System.out.println("目录或文件不存在！");
//            file.mkdir();
//        }
//        try {
//            //使用Apache文件上传组件处理文件上传步骤：
//            //1、创建一个DiskFileItemFactory工厂
//            DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
//            //设置工厂的缓冲区的大小，当上传的文件大小超过缓冲区的大小时，就会生成一个临时文件存放到指定的临时目录当中。
//            diskFileItemFactory.setSizeThreshold(1024 * 100);
//            //设置上传时生成的临时文件的保存目录
//            diskFileItemFactory.setRepository(file);
//            //2、创建一个文件上传解析器
//            ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);
//            //解决上传文件名的中文乱码
//            fileUpload.setHeaderEncoding("UTF-8");
//            //监听文件上传进度
//            fileUpload.setProgressListener(new ProgressListener() {
//                public void update(long pBytesRead, long pContentLength, int arg2) {
//                    System.out.println("文件大小为：" + pContentLength + ",当前已处理：" + pBytesRead);
//                }
//            });
//            //3、判断提交上来的数据是否是上传表单的数据
////            if (!fileUpload.isMultipartContent(request)) {
//                //按照传统方式获取数据
////                return new JSONResult<>("上传失败");
////            }
//            //设置上传单个文件的大小的最大值，目前是设置为1024*1024*10字节，也就是10MB
//            fileUpload.setFileSizeMax(1024 * 1024 * 10);
//            //设置上传文件总量的最大值，最大值=同时上传的多个文件的大小的最大值的和，目前设置为10MB
//            fileUpload.setSizeMax(1024 * 1024 * 10);
//            //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
//            List<FileItem> list = fileUpload.parseRequest(request);
//            urllist = new ArrayList<>();
//
//            int size=list.size();
//            for (FileItem item : list) {
//                //如果fileitem中封装的是普通输入项的数据
//                if (item.isFormField()) {
//
//                    String name = item.getFieldName();
//                    //解决普通输入项的数据的中文乱码问题
//                    String value = item.getString("UTF-8");
//                    String value1 = new String(value.getBytes("iso8859-1"), "UTF-8");
//                    switch (name){
//                        case "id":
//                            knowledge.setId(Integer.parseInt(value));
//                            break;
//                        case "title":
//                            knowledge.setTitle(value);
//                            break;
//                        case "descontent":
//                            knowledge.setDescontent(value);
//                            break;
//                        case "equipid":
//                            knowledge.setEquipid(Integer.parseInt(value));
//                            break;
//                        case "typeid":
//                            knowledge.setTypeid(Integer.parseInt(value));
//                            break;
//                    }
//                    System.out.println("title"+knowledge.getTitle());
//                } else {
//                    //如果fileitem中封装的是上传文件，得到上传的文件名称，
//                    String fileName = item.getName();
//
//                    /*if(a[i]==fileName){
//                        i=i;
//                        a[0]=fileName;
//                    }else
//                        i++;*/
//
//                    if (fileName == null || fileName.trim().equals("")) {
//                        continue;
//                    }
//                    //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
//                    //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
//                    fileName = fileName.substring(fileName.lastIndexOf(File.separator) + 1);
//                    //得到上传文件的扩展名
//                    String fileExtName = fileName.substring(fileName.lastIndexOf(".") + 1);
//                    if ("zip".equals(fileExtName)||"apk".equals(fileExtName) || "rar".equals(fileExtName) || "tar".equals(fileExtName) || "jar".equals(fileExtName)) {
//                        return new JSONResult<>("上传文件的类型不符合！！！");
//                    }
//                    //如果需要限制上传的文件类型，那么可以通过文件的扩展名来判断上传的文件类型是否合法
//                    System.out.println("上传文件的扩展名为:" + fileExtName);
//                    //获取item中的上传文件的输入流
//                    InputStream is = item.getInputStream();
//                    //得到文件保存的名称
//                    fileName = mkFileName(fileName);
//                    //得到文件保存的路径+url
//                    StringBuffer url=new StringBuffer();
//                    String savePathStr = mkFilePath(fileExtName,url);
//
//                    //创建一个文件输出流
//                    FileOutputStream fos = new FileOutputStream(savePathStr + File.separator + fileName);
//
//                    //创建一个缓冲区
//                    byte buffer[] = new byte[1024];
//                    //判断输入流中的数据是否已经读完的标识
//                    int length = 0;
//                    //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
//                    while((length = is.read(buffer))>0){
//                        //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
//                        fos.write(buffer, 0, length);
//                    }
//
//                    //关闭输入流
//                    is.close();
//                    //关闭输出流
//                    fos.close();
//                    //删除处理文件上传时生成的临时文件
//                    item.delete();
//                    urllist.add(url.append("/"+fileName).toString());
//                }
//            }
//        } catch (FileUploadBase.FileSizeLimitExceededException e) {
//            e.printStackTrace();
//            return new JSONResult<>("单个文件超出最大值！！！");
//        } catch (FileUploadBase.SizeLimitExceededException e) {
//            e.printStackTrace();
//            return new JSONResult<>("上传文件的总的大小超出限制的最大值！！！");
//        } catch (FileUploadException e) {
//            e.printStackTrace();
//            return new JSONResult<>("文件上传失败");
//        }
//        knowledge.setAttachment(JsonUtil.toJSON(urllist));
//       int result=konwledgeService.insert(knowledge);
//        String info="添加了知识"+knowledge.getTitle()+"的基本信息";
//        Admininfo logadmininfo=(Admininfo)session.getAttribute("userInfo");
//        String username=logadmininfo.getUsername();
//        operateLogService.insertSelective(username,info);
//        return new JSONResult<>(result);
//    }
//    //生成上传文件的文件名，文件名以：uuid+"_"+文件的原始名称
    public String mkFileName(String fileName) {
        return UUID.randomUUID().toString() + "_" + fileName;
    }

    public String mkFilePath(String fileExtName, StringBuffer url) throws IOException {
        String savePath;
        savePath = Resources.ApplicationResources.getString("file.path");
        url.append("/file/");
//        //得到文件名的hashCode的值，得到的就是filename这个字符串对象在内存中的地址
//        int hashcode = fileName.hashCode();
//        int dir1 = hashcode&0xf;
//        int dir2 = (hashcode&0xf0)>>4;
        Calendar now = Calendar.getInstance();
        int dir1 = now.get(Calendar.YEAR);
        int dir2 = now.get(Calendar.MONTH) + 1;
        int dir3 = now.get(Calendar.DAY_OF_MONTH);
        //构造新的保存目录
        String dir = savePath + "//" + dir1 + "//" + dir2 + "//" + dir3 + "//";
        String dirpath1 = savePath + "//" + dir1 + "//";
        String dirpath2 = savePath + "//" + dir1 + "//" + dir2 + "//";
//        //构造新的保存目录
//        String dir = savePath + "\\" + dir1 + "\\" + dir2 + "\\" + dir3 + "\\";
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

    @RequestMapping("/delknowledge")
    @ResponseBody
    public int delknowledge(Integer id,HttpServletRequest request){
        HttpSession session=request.getSession();
       int delk= konwledgeService.deleteByPrimaryKey(id);
        String title=request.getParameter("title");
        String info="删除了知识"+title+"的基本信息";
        Admininfo logadmininfo=(Admininfo)session.getAttribute("userInfo");
        String username=logadmininfo.getUsername();
        int addlog=operateLogService.insertSelective(username,info);
        if(delk>0&&addlog>0){
            return 1;
        }else
            return 0;
    }
    @RequestMapping("getknowledgebyid")
    public String getknowledgebyid(Integer id,Model m,HttpServletRequest request){
        List<Knowledgetype> knowledgetypes=konwledgeTypeService.selectByExample1();
        EquipmentinfoExample equipmentinfoExample=new EquipmentinfoExample();
        List<Equipmentinfo> equipmentinfos=equipmentService.selectByExample(equipmentinfoExample);
        Admininfo admininfo=(Admininfo) request.getSession().getAttribute("userInfo");
        List<Siteareainfo> siteareainfos=siteService.selectByExample2(admininfo.getSiteid());
        List<Areainfo> areainfos=areaService.selectByExample1(admininfo.getSiteid());
        Knowledge knowledge=konwledgeService.selectByPrimaryKey(id);

        m.addAttribute("knowledgetypes",knowledgetypes);
        m.addAttribute("equipmentinfos",equipmentinfos);
        m.addAttribute("siteareainfos",siteareainfos);
        m.addAttribute("areainfos",areainfos);
        m.addAttribute("knowledge",knowledge);
        return "knowledge/updknowledge2";
    }
    @RequestMapping("/updknowledge")
    @ResponseBody
    public int updknowledge(Knowledge knowledge){
        List<String> attach=new ArrayList<>();
        String [] stringArr= knowledge.getAttachment().split(",");
        for(int i=0;i<stringArr.length;i++){
            attach.add(stringArr[i]);
        }
        knowledge.setAttachment(JsonUtil.toJSON(attach));
        int result=konwledgeService.updateByPrimaryKeySelective(knowledge);
        return result;
    }
//    public Result updknowledge(HttpServletRequest request)throws ServletException, IOException{
//        HttpSession session=request.getSession();
//        Knowledge knowledge=new Knowledge();
//        File file = new File(Resources.ApplicationResources.getString("tempfile.path"));
//        List<String> urllist;
//        if (!file.exists() && !file.isDirectory()) {
//            System.out.println("目录或文件不存在！");
//            file.mkdir();
//        }
//        try {
//            //使用Apache文件上传组件处理文件上传步骤：
//            //1、创建一个DiskFileItemFactory工厂
//            DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
//            //设置工厂的缓冲区的大小，当上传的文件大小超过缓冲区的大小时，就会生成一个临时文件存放到指定的临时目录当中。
//            diskFileItemFactory.setSizeThreshold(1024 * 100);
//            //设置上传时生成的临时文件的保存目录
//            diskFileItemFactory.setRepository(file);
//            //2、创建一个文件上传解析器
//            ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);
//            //解决上传文件名的中文乱码
//            fileUpload.setHeaderEncoding("UTF-8");
//            //监听文件上传进度
//            fileUpload.setProgressListener(new ProgressListener() {
//                public void update(long pBytesRead, long pContentLength, int arg2) {
//                    System.out.println("文件大小为：" + pContentLength + ",当前已处理：" + pBytesRead);
//                }
//            });
//            //3、判断提交上来的数据是否是上传表单的数据
////            if (!fileUpload.isMultipartContent(request)) {
//                //按照传统方式获取数据
////                return new JSONResult<>("上传失败");
////            }
//            //设置上传单个文件的大小的最大值，目前是设置为1024*1024*10字节，也就是10MB
//            fileUpload.setFileSizeMax(1024 * 1024 * 10);
//            //设置上传文件总量的最大值，最大值=同时上传的多个文件的大小的最大值的和，目前设置为10MB
//            fileUpload.setSizeMax(1024 * 1024 * 10);
//            //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
//            List<FileItem> list = fileUpload.parseRequest(request);
//            urllist = new ArrayList<>();
//
//            int size=list.size();
//            for (FileItem item : list) {
//                //如果fileitem中封装的是普通输入项的数据
//                if (item.isFormField()) {
//
//                    String name = item.getFieldName();
//                    //解决普通输入项的数据的中文乱码问题
//                    String value = item.getString("UTF-8");
//                    String value1 = new String(value.getBytes("iso8859-1"), "UTF-8");
//                    switch (name){
//                        case "id":
//                            knowledge.setId(Integer.parseInt(value));
//                            break;
//                        case "title":
//                            knowledge.setTitle(value);
//                            break;
//                        case "descontent":
//                            knowledge.setDescontent(value);
//                            break;
//                        case "equipid":
//                            knowledge.setEquipid(Integer.parseInt(value));
//                            break;
//                        case "typeid":
//                            knowledge.setTypeid(Integer.parseInt(value));
//                            break;
//
//                    }
//                } else {
//                    //如果fileitem中封装的是上传文件，得到上传的文件名称，
//                    String fileName = item.getName();
//                    if (fileName == null || fileName.trim().equals("")) {
//                        continue;
//                    }
//                    //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
//                    //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
//                    fileName = fileName.substring(fileName.lastIndexOf(File.separator) + 1);
//                    //得到上传文件的扩展名
//                    String fileExtName = fileName.substring(fileName.lastIndexOf(".") + 1);
//                    if ("zip".equals(fileExtName) || "rar".equals(fileExtName) || "tar".equals(fileExtName) || "jar".equals(fileExtName)) {
//                        return new JSONResult<>("上传文件的类型不符合！！！");
//                    }
//                    //如果需要限制上传的文件类型，那么可以通过文件的扩展名来判断上传的文件类型是否合法
//                    System.out.println("上传文件的扩展名为:" + fileExtName);
//                    //获取item中的上传文件的输入流
//                    InputStream is = item.getInputStream();
//                    //得到文件保存的名称
//                    fileName = mkFileName(fileName);
//                    //得到文件保存的路径+url
//                    StringBuffer url=new StringBuffer();
//                    String savePathStr = mkFilePath(fileExtName,url);
//
//                    //创建一个文件输出流
//                    FileOutputStream fos = new FileOutputStream(savePathStr + File.separator + fileName);
//
//                    //创建一个缓冲区
//                    byte buffer[] = new byte[1024];
//                    //判断输入流中的数据是否已经读完的标识
//                    int length = 0;
//                    //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
//                    while((length = is.read(buffer))>0){
//                        //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
//                        fos.write(buffer, 0, length);
//                    }
//
//                    //关闭输入流
//                    is.close();
//                    //关闭输出流
//                    fos.close();
//                    //删除处理文件上传时生成的临时文件
//                    item.delete();
//                    urllist.add(url.append("/"+fileName).toString());
//                }
//            }
//        } catch (FileUploadBase.FileSizeLimitExceededException e) {
//            e.printStackTrace();
//            return new JSONResult<>("单个文件超出最大值！！！");
//        } catch (FileUploadBase.SizeLimitExceededException e) {
//            e.printStackTrace();
//            return new JSONResult<>("上传文件的总的大小超出限制的最大值！！！");
//        } catch (FileUploadException e) {
//            e.printStackTrace();
//            return new JSONResult<>("文件上传失败");
//        }
//        knowledge.setAttachment(JsonUtil.toJSON(urllist));
//        int result=konwledgeService.updateByPrimaryKeySelective(knowledge);
//        String info="修改了知识"+knowledge.getTitle()+"的基本信息";
//        Admininfo logadmininfo=(Admininfo)session.getAttribute("userInfo");
//        String username=logadmininfo.getUsername();
//        operateLogService.insertSelective(username,info);
//        return new JSONResult<>(result);
//    }
    @RequestMapping("/imgupload")
    @ResponseBody
    public Result imgupload(HttpServletRequest request)throws ServletException, IOException{
        HttpSession session=request.getSession();
        File file = new File(Resources.ApplicationResources.getString("tempfile.path"));
        String urllist="";
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
    //            if (!fileUpload.isMultipartContent(request)) {
            //按照传统方式获取数据
    //                return new JSONResult<>("上传失败");
    //            }
            //设置上传单个文件的大小的最大值，目前是设置为1024*1024*10字节，也就是10MB
            fileUpload.setFileSizeMax(1024 * 1024 * 10);
            //设置上传文件总量的最大值，最大值=同时上传的多个文件的大小的最大值的和，目前设置为10MB
            fileUpload.setSizeMax(1024 * 1024 * 10);
            //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
            List<FileItem> list = fileUpload.parseRequest(request);
//            urllist = new ArrayList<>();

            int size=list.size();
            for (FileItem item : list) {
                //如果fileitem中封装的是普通输入项的数据
                if (item.isFormField()) {
                    String name = item.getFieldName();
                    //解决普通输入项的数据的中文乱码问题
                    String value = item.getString("UTF-8");
                    String value1 = new String(value.getBytes("iso8859-1"), "UTF-8");
                } else {
                    //如果fileitem中封装的是上传文件，得到上传的文件名称，
                    String fileName = item.getName();
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
                    urllist=url.append("/"+fileName).toString();
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
    @RequestMapping("queryareabysiteid")
    @ResponseBody
    public List<Areainfo> queryareabysiteid(Integer siteid){
        return areaService.selectByExample1(siteid);
    }
    @RequestMapping("queryequipment")
    @ResponseBody
    public List<Equipmentinfo> queryequipment(Integer areaid){
        return equipmentService.queryequip(areaid);
    }

    @RequestMapping("getknowledgedetail")
    public String getknowledgedetail(int id,Model m){
        Knowledge knowledge=konwledgeService.selectByPrimaryKey(id);

        m.addAttribute("knowledge",knowledge);
        m.addAttribute("ip",ip.toString());
        return "knowledge/showknowledgedetail";
    }

    /*@RequestMapping("/downLoadFile")
    public String downLoadFile(String filename,HttpServletRequest request,HttpServletResponse response) throws IOException{//获取文件下载路径
        //处理get请求过来的中文乱码
        filename=new String(filename.getBytes("ISO-8859-1"), "UTF-8");
        //设置下载中文乱码
        response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(filename, "UTF-8"));


        //创建输入流

//        String savePath;
//        savePath = Resources.ApplicationResources.getString("file.path");
        String path="http://localhost:8089/"+filename;
        File f=new File(path);
        InputStream is=new FileInputStream(f);
        //创建响应输出流
        OutputStream os=response.getOutputStream();

        //边读边写
        byte[] bt=new byte[1024];//存放每次读取的内容，1024代表每次最多读取1KB
        int size=0;
        while( (size=is.read(bt))!=-1 ){//读
            //写
            os.write(bt, 0, size);//读多少写多少
            bt=new byte[1024];//避免出现重复数据
        }

        //关闭流
        os.close();
        is.close();
        return null;


    }*/
}
