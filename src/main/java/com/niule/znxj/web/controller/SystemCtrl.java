package com.niule.znxj.web.controller;

import com.alibaba.druid.sql.visitor.functions.If;
import com.niule.znxj.web.model.Doublereportsetting;
import com.niule.znxj.web.model.Reportsetting;
import com.niule.znxj.web.model.Systemsettinginfo;
import com.niule.znxj.web.service.SystemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by administor on 2017/5/16.
 */
@Controller
public class SystemCtrl {
    @Resource
    private SystemService systemService;

    /**
     * 显示系统设置
     * @param m
     * @param type
     * @return
     */
    @RequestMapping("showsystem")
    public String showsystem(Model m,int type){
        List<Systemsettinginfo> systems=systemService.selectByExample();
        for(Systemsettinginfo info : systems){
            m.addAttribute(info.getKeyname(),info.getValue());
        }
        String page="";
        if(type==1){
            page= "system";
        }
        if(type==2){
            page= "system2";
        }
        if(type==3) {
            page= "email";
        }
        if(type==4) {
            page= "imgserver";
        }
        m.addAttribute("type",type);
        return page;
    }

    /**
     * 更新系统设置
     * @param REMEMBER_NAME
     * @param REMEMBER_PSW
     * @param SHOW_COPYRIGHT
     * @param COPYRIGHT_CONTENT
     * @param REPORTCACHE
     * @param WIFIUPLOAD
     * @param FLUCTUATE
     * @param IMGSERVER
     * @param STORAGEPATH
     * @param SYSTEMNAME
     * @param SYSTEMDESC
     * @param type
     * @return
     */
    @RequestMapping("updsystem")
    @ResponseBody
    public int updsystem(String REMEMBER_NAME,String REMEMBER_PSW,String SHOW_COPYRIGHT,String COPYRIGHT_CONTENT,
                         String REPORTCACHE,String WIFIUPLOAD,String FLUCTUATE,String IMGSERVER,String STORAGEPATH,
                         String SYSTEMNAME,String SYSTEMDESC,String SYSTEMVERSION,String APPVERSION,int type){
        String key1="REMEMBER_NAME";
        String key2="REMEMBER_PSW";
        String key3="SHOW_COPYRIGHT";
        String key4="COPYRIGHT_CONTENT";
        String key5="REPORTCACHE";
        String key6="WIFIUPLOAD";
        String key7="FLUCTUATE";
        String key10="IMGSERVER";
        String key11="STORAGEPATH";
        String key12="SYSTEMNAME";
        String key13="SYSTEMDESC";
        String key14="SYSTEMVERSION";
        String key15="APPVERSION";
        int result=0;
        if(type==1){
            int updresult1=systemService.updSysByKey(key1,REMEMBER_NAME);
            int updresult2=systemService.updSysByKey(key2,REMEMBER_PSW);
            int updresult3=systemService.updSysByKey(key3,SHOW_COPYRIGHT);
            int updresult4=systemService.updSysByKey(key4,COPYRIGHT_CONTENT);
            int updresult5=systemService.updSysByKey(key5,REPORTCACHE);
            int updresult6=systemService.updSysByKey(key6,WIFIUPLOAD);
            int updresult14=systemService.updSysByKey(key14,SYSTEMVERSION);
            int updresult15=systemService.updSysByKey(key15,APPVERSION);
            if(updresult1>0&&updresult2>0&&updresult3>0&updresult4>0&&updresult5>0&&updresult6>0&&updresult14>0&&updresult15>0){
                result=1;
                return result;
            }
        }
        if(type==2){
            int updresult12=systemService.updSysByKey(key12,SYSTEMNAME);
            int updresult13=systemService.updSysByKey(key13,SYSTEMDESC);
            if(updresult12>0&&updresult13>0){
                result=1;
                return result;
            }
        }
        if(type==3){
            int updresult7=systemService.updSysByKey(key7,FLUCTUATE);
            if(updresult7>0){
                result=1;
                return result;
            }
        }
        if(type==4){
            int updresult10=systemService.updSysByKey(key10,IMGSERVER);
            int updresult11=systemService.updSysByKey(key11,STORAGEPATH);
            if(updresult10>0&&updresult11>0){
                result=1;
                return result;
            }
        }
        return result;
    }

    /**
     *显示单次任务报告设置
     * @param model
     * @return
     */
    @RequestMapping("/showReportSetting")
    public String showReportSetting(Model model){
        List<Reportsetting> reportsettings = systemService.showReportSetting();
        for (Reportsetting reportsetting:reportsettings){
            model.addAttribute(reportsetting.getName(),reportsetting.getIsshow());
        }
        return "showReportSetting";
    }
    /**
     *显示任务报告汇总设置
     * @param model
     * @return
     */
    @RequestMapping("/showDoubleReportSetting")
    public String showDoubleReportSetting(Model model){
        List<Doublereportsetting> doublereportsettings = systemService.showDoubleReportSetting();
        for (Doublereportsetting reportsetting:doublereportsettings){
            model.addAttribute(reportsetting.getName(),reportsetting.getIsshow());
        }
        return "showDoubleReportSetting";
    }

    /**
     * 修改单次任务报告设置
     * @param areaname 是否显示区域
     * @param equipname 是否显示设备
     * @param checkname 是否显示巡检项
     * @param checktype 是否显示巡检项类型
     * @param numvalue 是否显示数值
     * @param recordname 是否显示数据名称
     * @param unitname 是否显示单位
     * @param operationtime 是否显示执行时间
     * @param errcontent 是否显示异常描述
     * @param lowerwarning 是否显示下限警告值
     * @param normalmax 是否显示最高值
     * @param normalmin 是否显示最低值
     * @param upperwarning 是否显示上限警告值
     * @param checkvalue 是否显示复核值
     * @param firstval 是否显示前次复核值
     * @return
     */
    @RequestMapping("/updReportSetting")
    @ResponseBody
    public int updReportSetting(String areaname,String equipname,String checkname,String checktype,String numvalue,
                                   String recordname,String unitname,String operationtime,String errcontent,String lowerwarning,
                                   String normalmax,String normalmin,String upperwarning,String checkvalue,String firstval){
        String key1="areaname";
        String key2="equipname";
        String key3="checkname";
        String key4="checktype";
        String key5="numvalue";
        String key6="recordname";
        String key7="unitname";
        String key8="operationtime";
        String key9="errcontent";
        String key10="lowerwarning";
        String key11="normalmax";
        String key12="normalmin";
        String key13="upperwarning";
        String key14="checkvalue";
        String key15="firstval";
        int updresult1=systemService.updReportSettingByKey(key1,areaname);
        int updresult2=systemService.updReportSettingByKey(key2,equipname);
        int updresult3=systemService.updReportSettingByKey(key3,checkname);
        int updresult4=systemService.updReportSettingByKey(key4,checktype);
        int updresult5=systemService.updReportSettingByKey(key5,numvalue);
        int updresult6=systemService.updReportSettingByKey(key6,recordname);
        int updresult7=systemService.updReportSettingByKey(key7,unitname);
        int updresult8=systemService.updReportSettingByKey(key8,operationtime);
        int updresult9=systemService.updReportSettingByKey(key9,errcontent);
        int updresult10=systemService.updReportSettingByKey(key10,lowerwarning);
        int updresult11=systemService.updReportSettingByKey(key11,normalmax);
        int updresult12=systemService.updReportSettingByKey(key12,normalmin);
        int updresult13=systemService.updReportSettingByKey(key13,upperwarning);
        int updresult14=systemService.updReportSettingByKey(key14,checkvalue);
        int updresult15=systemService.updReportSettingByKey(key15,firstval);
        if (updresult1>0&&updresult2>0&&updresult3>0&&updresult4>0&&updresult5>0&&
                updresult6>0&&updresult7>0&&updresult8>0&&updresult9>0&&updresult10>0&&
                updresult11>0&&updresult12>0&&updresult13>0&&updresult14>0&&updresult15>0)
            return 1;
        else return 0;
    }
    /**
     * 修改任务报告汇总设置
     * @param areaname 是否显示区域
     * @param equipname 是否显示设备
     * @param checkname 是否显示巡检项
     * @param lowerwarning 是否显示下限警告值
     * @param normalmax 是否显示最高值
     * @param normalmin 是否显示最低值
     * @param upperwarning 是否显示上限警告值
     * @return
     */
    @RequestMapping("/updDoubleReportSetting")
    @ResponseBody
    public int updDoubleReportSetting(String areaname,String equipname,String checkname,String lowerwarning,
                                      String normalmax,String normalmin,String upperwarning){
        String key1="areaname";
        String key2="equipname";
        String key3="checkname";
        String key4="lowerwarning";
        String key5="normalmax";
        String key6="normalmin";
        String key7="upperwarning";
        int updresult1=systemService.updDoubleReportSettingByKey(key1,areaname);
        int updresult2=systemService.updDoubleReportSettingByKey(key2,equipname);
        int updresult3=systemService.updDoubleReportSettingByKey(key3,checkname);
        int updresult4=systemService.updDoubleReportSettingByKey(key4,lowerwarning);
        int updresult5=systemService.updDoubleReportSettingByKey(key5,normalmax);
        int updresult6=systemService.updDoubleReportSettingByKey(key6,normalmin);
        int updresult7=systemService.updDoubleReportSettingByKey(key7,upperwarning);
        if (updresult1>0&&updresult2>0&&updresult3>0&&updresult4>0&&updresult5>0&&
                updresult6>0&&updresult7>0)
            return 1;
        else return 0;
    }
}
