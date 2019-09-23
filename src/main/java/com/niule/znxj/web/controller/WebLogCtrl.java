package com.niule.znxj.web.controller;

import com.github.pagehelper.PageInfo;
import com.niule.znxj.web.model.Operatelog;
import com.niule.znxj.web.service.OperateLogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * Created by administor on 2017/5/22.
 */
@Controller
public class WebLogCtrl {
    @Resource
    private OperateLogService operateLogService;

    @RequestMapping("showlog")
    public String showlog(int page, Model m,String username,String time){
        if(page<=0){
            page=1;
        }
        int size=15;
        HashMap<String,Object> map=new HashMap<>();
        map.put("page",page);
        map.put("username","%"+username+"%");
        map.put("time2",time);
        map.put("size",size);
        PageInfo<Operatelog> logs=null;
        if((username==null||"".equals(username))&&(time==null||"".equals(time))){
            logs=operateLogService.findByPage1(page,size);
        }else{

            logs=operateLogService.findByPage2(map);
        }
        m.addAttribute("uname",username);
        m.addAttribute("time",time);
        m.addAttribute("pageBean",logs);
        return "showlog";
    }
    @RequestMapping("dellog")
    @ResponseBody
    public int dellog(int id){
        return operateLogService.deleteByPrimaryKey(id);
    }
}
