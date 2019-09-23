package com.niule.znxj.web.controller;

import com.niule.znxj.web.model.Systemsettinginfo;
import com.niule.znxj.web.service.SystemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 视图控制器,返回jsp视图给前端
 * 
 * @author StarZou
 * @since 2014年5月28日 下午4:00:49
 **/
@Controller
@RequestMapping("/page")
public class PageController {
    @Resource
    private SystemService systemService;

    /**
     * 登录页
     */
    @RequestMapping("/login")
    public String login(HttpServletRequest request) {
        List<Systemsettinginfo> systems=systemService.selectByExample();
        for(Systemsettinginfo systemsettinginfo : systems){
            if (systemsettinginfo.getKeyname().equals("SYSTEMVERSION"))
                request.getSession().setAttribute(systemsettinginfo.getKeyname(), systemsettinginfo.getValue());
        }
        return "login";
    }

    /**
     * 404页
     */
    @RequestMapping("/404")
    public String error404() {
        return "404";
    }

    /**
     * 401页
     */
    @RequestMapping("/401")
    public String error401() {
        return "401";
    }

    /**
     * 500页
     */
    @RequestMapping("/500")
    public String error500() {
        return "500";
    }


    /**
     * testUpload
     */
    @RequestMapping("/testUpload")
    public String testUpload() {
        return "upload";
    }
}