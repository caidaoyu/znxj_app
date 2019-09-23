package com.niule.znxj.web.controller;

import com.github.pagehelper.PageInfo;
import com.niule.znxj.core.common.Resources;
import com.niule.znxj.core.util.json.JsonUtil;
import com.niule.znxj.web.model.*;
import com.niule.znxj.web.service.ContactinfoService;
import com.niule.znxj.web.service.SendEmailService;
import com.niule.znxj.web.service.SiteService;
import com.niule.znxj.web.service.TaskPlanService;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by administor on 2017/6/21.
 */
@Controller
public class WebSendEmailCtrl {
    @Resource
    private SendEmailService sendEmailService;
    @Resource
    private ContactinfoService contactinfoService;
    @Resource
    private TaskPlanService taskPlanService;

    @Resource
    private SiteService siteService;

    private String ip = Resources.ApplicationResources.getString("ip");

    @RequestMapping("showsendemaillist")
    public String showsendemaillist(int page, Model m, HttpServletRequest request){
        if(page<=0){page=1;}
        int size=15;
        Admininfo admininfo = (Admininfo) request.getSession().getAttribute("userInfo");
        List ids  = new ArrayList();
        if(admininfo.getSiteid() == null){
            List<Siteareainfo> siteareainfos = siteService.queryAllSite();
            if(siteareainfos.size() > 0){
                for(Siteareainfo siteareainfo : siteareainfos){
                    ids.add(siteareainfo.getId());
                }
            }else{
                ids = null;
            }
        }else{
            ids.add(admininfo.getSiteid());
        }

        PageInfo pageInfo=new PageInfo<>(sendEmailService.showSendEmail(page,size,ids));
        m.addAttribute("pageInfo",pageInfo);
        return "showsendemail";
    }
    @RequestMapping("addsendemail")
    public String addsendemail(String selectedpersion,Sendemail sendemail){
        sendemail.setContactid(selectedpersion);
        int result= sendEmailService.addSendEmail(sendemail);
        if(result>0){
            return "redirect:showsendemaillist?page=1";
        }else
            return"redirect:sendpersion";

    }
    @RequestMapping("querysendbyid")
    public String querysendbyid(Long id,Model m){
        Sendemail sendemail=sendEmailService.showSendById(id);
        List<Contactinfo> contactinfos=contactinfoService.sendAllPersion();
        TaskplaninfoExample taskplaninfoExample=new TaskplaninfoExample();
        taskplaninfoExample.createCriteria().andStateNotEqualTo(-1);
        List<Taskplaninfo> taskplaninfos=taskPlanService.selectByExample(taskplaninfoExample);

        if(sendemail!=null){
            m.addAttribute("editsendemail",sendemail);
            m.addAttribute("taskplaninfos",taskplaninfos);
            m.addAttribute("contactinfos",contactinfos);
            return "updsendemail";
        }else
            return "redirect:showsendemaillist?page=1";
    }
    @RequestMapping("updsendemail")
    public String updsendemail(String selectedpersion,Sendemail sendemail){
        sendemail.setContactid(selectedpersion);
        int result=sendEmailService.updSendEmail(sendemail);
        if(result>0){
            return "redirect:showsendemaillist?page=1";
        }else
            return"redirect:querysendbyid?id="+sendemail.getId();
    }
    @RequestMapping("delsendemail")
    @ResponseBody
    public int delsendemail(Long id){
        return sendEmailService.delSendEmail(id);
    }

    @RequestMapping("/toException")
    public  String toException(String img,String audio,String video ,Model model){

        model.addAttribute("ip",ip );
//        model.addAttribute("img", getStringToReport(img));
//        model.addAttribute("audio",getStringToReport(audio));
//        model.addAttribute("video",getStringToReport(video));
        model.addAttribute("img", img);
        model.addAttribute("audio",audio);
        model.addAttribute("video",video);
        return "exception";
    }

    //给路径加上 /report 的
    public String getStringToReport(String str) {
        JSONArray pathList = new JSONArray();
        if (str != null && !str.equals("null")&&!str.equals("[]")) {
            JSONArray list = JSONArray.fromObject(str);
            for (Object aList : list) {
                if(!aList.toString().contains("report")) {
                    String path = "/report" + aList.toString();
                    pathList.add(path);
                }else {
                    pathList.add(aList.toString());
                }
            }
            str = pathList.toString();
//            System.out.println(str);
        }
        return str;
    }
}
