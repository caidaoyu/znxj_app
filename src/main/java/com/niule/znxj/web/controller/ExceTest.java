package com.niule.znxj.web.controller;

import org.apache.poi.hssf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/4/12.
 */
public class ExceTest {
    /**
     * @功能：手工构建一个简单格式的Excel
     */
    private static List<TestUser> getUser() throws ParseException {
        List list = new ArrayList();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        TestUser user1 = new TestUser(1,"张三",12,sdf.parse("2018-04-12"));
        TestUser user2 = new TestUser(2,"李四",15,sdf.parse("2018-04-11"));
        TestUser user3 = new TestUser(3,"王五",17,sdf.parse("2018-04-10"));
        TestUser user4 = new TestUser(4,"赵六",15,sdf.parse("2018-04-09"));
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        return list;
    }

   /* public static void main(String[] args ) throws ParseException, FileNotFoundException {
        //第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        //第二步，在webbook中添加一个sheet，对应Excel文件中的sheet
        HSSFSheet sheet = workbook.createSheet();
        //第三步，在sheet中添加表头的第0行
        HSSFRow row = sheet.createRow(0);
        //第四步，创建单元格，并设置表头，以及设置表头居中
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        HSSFCell cell = row.createCell(0);
        cell.setCellValue("编号");
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue("姓名");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("年龄");
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue("生日");
        cell.setCellStyle(style);

        //第五步，写入实体数据，实际应用中这些数据从数据库得到
        List list = ExceTest.getUser();

        for(int i = 0 ; i < list.size() ;i++){
            row = sheet.createRow(i+1);
            TestUser user = (TestUser) list.get(i);
            row.createCell(0).setCellValue(user.getId());
            row.createCell(1).setCellValue(user.getName());
            row.createCell(2).setCellValue(user.getAge());
            row.createCell(3).setCellValue(user.getBrith());
        }
        FileOutputStream fileOutputStream = new FileOutputStream("E:/TestUser.xls");
        try {
            workbook.write(fileOutputStream);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/


    /**
     * @功能 下载临时素材接口
     * @param filePath 文件将要保存的目录
     * @param method 请求方法，包括POST和GET
     * @param url 请求的路径
     * @return
     */

    public static File saveUrlAs(String url, String filePath, String method){
        //System.out.println("fileName---->"+filePath);
        //创建不同的文件夹目录
        File file=new File(filePath);
        //判断文件夹是否存在
        if (!file.exists())
        {
            //如果文件夹不存在，则创建新的的文件夹
            file.mkdirs();
        }
        FileOutputStream fileOut = null;
        HttpURLConnection conn = null;
        InputStream inputStream = null;
        try
        {
            // 建立链接
            URL httpUrl=new URL(url);
            conn=(HttpURLConnection) httpUrl.openConnection();
            //以Post方式提交表单，默认get方式
            conn.setRequestMethod(method);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            // post方式不能使用缓存
            conn.setUseCaches(false);
            //连接指定的资源
            conn.connect();
            //获取网络输入流
            inputStream=conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            //判断文件的保存路径后面是否以/结尾
            if (!filePath.endsWith("/")) {

                filePath += "/";

            }
            //写入到文件（注意文件保存路径的后面一定要加上文件的名称）
            fileOut = new FileOutputStream(filePath+"123.png");
            BufferedOutputStream bos = new BufferedOutputStream(fileOut);

            byte[] buf = new byte[4096];
            int length = bis.read(buf);
            //保存文件
            while(length != -1)
            {
                bos.write(buf, 0, length);
                length = bis.read(buf);
            }
            bos.close();
            bis.close();
            conn.disconnect();
        } catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("抛出异常！！");
        }

        return file;

    }

    public static void main(String[] args)
    {
        String photoUrl = "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png";
        String fileName = photoUrl.substring(photoUrl.lastIndexOf("/"));
        //System.out.println("fileName---->"+fileName);
        String filePath = "d:";
        File file = saveUrlAs(photoUrl, filePath + fileName,"GET");
        System.out.println("Run ok!/n<BR>Get URL file " + file);

        /* //处理get请求过来的中文乱码
        filename=new String(filename.getBytes("ISO-8859-1"), "UTF-8");
        //设置下载中文乱码
        response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(filename, "UTF-8"));


        //创建输入流
        String path=request.getSession().getServletContext().getRealPath("/")+"files/"+filename;
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
*/

    }

    /*public static void downLoad(String filePath, HttpServletResponse response, boolean isOnLine) throws Exception {
        File f = new File(filePath);
        if (!f.exists()) {
            response.sendError(404, "File not found!");
            return;
        }
        BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
        byte[] buf = new byte[1024];
        int len = 0;

        response.reset(); // 非常重要
        if (isOnLine) { // 在线打开方式
            URL u = new URL("file:///" + filePath);
            response.setContentType(u.openConnection().getContentType());
            response.setHeader("Content-Disposition", "inline; filename=" + f.getName());
            // 文件名应该编码成UTF-8
        } else { // 纯下载方式
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment; filename=" + f.getName());
        }
        OutputStream out = response.getOutputStream();
        while ((len = br.read(buf)) > 0)
            out.write(buf, 0, len);
        br.close();
        out.close();
    }*/

//    public static void main(String[] args) {
//        downLoad("2c62c4c7-a7fd-4a77-98b0-92c193392464_新建文本文档.txt",,true);
//    }


}
