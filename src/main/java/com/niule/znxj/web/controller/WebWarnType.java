package com.niule.znxj.web.controller;

import com.github.pagehelper.PageInfo;
import com.niule.znxj.web.model.Warningtasktype;
import com.niule.znxj.web.service.WarnTypeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by administor on 2017/5/11.
 */
@Controller
public class WebWarnType {
    @Resource
    private WarnTypeService warnTypeService;

    @RequestMapping("showwarntype")
    public String showwarntype(int page, Model m){
        PageInfo<Warningtasktype> info=new PageInfo<>(warnTypeService.showAllWarnType(page,15));

        m.addAttribute("info",info);

        return "warn/showwarntype";
    }
    @RequestMapping("addwarntype")
    @RequiresPermissions("add:warntype")
    public String addwarntype(Warningtasktype warningtasktype){
        int addwarnresult= warnTypeService.insert(warningtasktype);
        if(addwarnresult>0){
            return "redirect:/showwarntype?page=1";
        }else
            return "taskfinalline";
    }
    @RequestMapping("delwarntype")
    @RequiresPermissions("del:warntype")
    @ResponseBody
    public int delwarntype(int id){
        return warnTypeService.deleteByPrimaryKey(id);
    }

    @RequestMapping("querywarnbyid")
    public  String qerywarnbyid(int id,Model m,int page){
        Warningtasktype warningtasktype= warnTypeService.selectByPrimaryKey(id);
        m.addAttribute("warntype",warningtasktype);
        m.addAttribute("page",page);
        return "warn/updwarn";
    }
    @RequestMapping("updwarntype")
    public String updwarntype(Warningtasktype warningtasktype,int page){
        int updwarnresult=warnTypeService.updateByPrimaryKeySelective(warningtasktype);
        if(updwarnresult>0){
            return "redirect:/showwarntype?page="+page;
        }else
            return "taskfinalline";
    }

}
