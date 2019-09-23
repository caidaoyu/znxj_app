package com.niule.znxj.web.controller;

import com.github.pagehelper.PageInfo;
import com.niule.znxj.core.common.Resources;
import com.niule.znxj.core.entity.JSONResult;
import com.niule.znxj.core.feature.orm.mybatis.Page;
import com.niule.znxj.web.model.Admininfo;
import com.niule.znxj.web.model.Appversion;
import com.niule.znxj.web.model.Operatelog;
import com.niule.znxj.web.service.AppversionService;
import com.niule.znxj.web.service.OperateLogService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by administor on 2017/5/10.
 */
@Controller
public class WebAppversionCtrl {
    @Resource
    private AppversionService appversionService;
    @Resource
    private OperateLogService operateLogService;

    /**
     * 查看apk版本信息
     * @param page
     * @param m
     * @return
     */
    @RequestMapping(value = "showappversion")
    public String showappversion(int page, Model m){
        int size=15;
        PageInfo info = new PageInfo<>(appversionService.selectByExample(page,size));
        m.addAttribute("info",info);
        return "appversion/showappversion";
    }

    /**
     * 添加新的apk
     * @param request
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/addappversion")
//    @RequiresPermissions("add:apk")
    @ResponseBody
    public int addappversion(HttpServletRequest request)throws ServletException, IOException {
        Appversion appversion=new Appversion();
        String savePath = request.getSession().getServletContext().getRealPath("/apk");
        File file = new File(savePath);
        if(!file.exists()&&!file.isDirectory()){
            System.out.println("目录或文件不存在！");
            file.mkdir();
        }
        //消息提示
        int message = 0;
        try {
            //使用Apache文件上传组件处理文件上传步骤：
            //1、创建一个DiskFileItemFactory工厂
            DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
            //2、创建一个文件上传解析器
            ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);
            //解决上传文件名的中文乱码
            fileUpload.setHeaderEncoding("UTF-8");
            //3、判断提交上来的数据是否是上传表单的数据
            if(!fileUpload.isMultipartContent(request)){
                //按照传统方式获取数据
                message=1;
            }
            //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
            List<FileItem> list = fileUpload.parseRequest(request);
            for (FileItem item : list) {
                //如果fileitem中封装的是普通输入项的数据
                if(item.isFormField()){
                    String name = item.getFieldName();
                    //解决普通输入项的数据的中文乱码问题
                    String value = item.getString("UTF-8");
                    String value1 = new String(name.getBytes("iso8859-1"),"UTF-8");
                    switch (name){
                        case "id":
                            appversion.setId(Integer.parseInt(value));
                            break;
                        case "versionname":
                            appversion.setVersionname(value);
                            break;
                        case "versioncode":
                            appversion.setVersioncode(Integer.parseInt(value));
                            break;
                        case "versiondesc":
                            appversion.setVersiondesc(value);
                            break;
                    }
                }else{
                    //如果fileitem中封装的是上传文件，得到上传的文件名称，
                    String fileName = item.getName();
                    System.out.println(fileName);
                    if(fileName==null||fileName.trim().equals("")){
                        continue;
                    }
                    //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
                    //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
                    fileName = fileName.substring(fileName.lastIndexOf(File.separator)+1);
                    String fileExtName = fileName.substring(fileName.lastIndexOf(".") + 1);
                    /*if ("apk"!=fileExtName) {
                       message=1;
                        break;
                    }*/
                    if("apk".equals(fileExtName)){
                        //获取item中的上传文件的输入流
                        InputStream is = item.getInputStream();
                        //创建一个文件输出流
                        FileOutputStream fos = new FileOutputStream(savePath+File.separator+fileName);
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
                        message = 2;
                        break;
                    }else{
                        message=1;
                        break;
                    }
                }
            }
        } catch (FileUploadException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            message = 1;
        }
        int result;
        if(message==2){
            result=appversionService.insert(appversion);
        }else{
            result=0;
        }
        return result;
    }

    /**
     * 删除apk
     * @param id
     * @param session
     * @param request
     * @return
     */
    @RequestMapping("/delappversion")
    @ResponseBody
//    @RequiresPermissions("del:apk")
    public int delappversion(int id,HttpSession session,HttpServletRequest request){
        int delappversion=appversionService.deleteByPrimaryKey(id);
        //获取登录用户的信息
        String appversion=request.getParameter("name");
        String info="删除了apk"+appversion+"的信息";
        Admininfo logadmininfo=(Admininfo)session.getAttribute("userInfo");
        String username=logadmininfo.getUsername();
        int addlog=operateLogService.insertSelective(username,info);
        if(delappversion>0&&addlog>0){
            return 1;
        }else
            return 0;
    }

   /* @RequestMapping("queryByappversionid")
    public String queryByappversionid(int id ,Model m){
        Appversion appversion=appversionService.selectByPrimaryKey(id);

        m.addAttribute("appversion",appversion);

        return "updappversion";
    }
    @RequestMapping("updappversion")
    public String updappversion(Appversion appversion){
        int result=appversionService.updateByPrimaryKey(appversion);
        if(result>0){
            return "redirect:/showappversion?page=1";
        }else
            return "error";
    }*/
}
