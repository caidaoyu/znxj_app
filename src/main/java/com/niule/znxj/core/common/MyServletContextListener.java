package com.niule.znxj.core.common;

import com.niule.znxj.web.service.CommonService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //获取容器和相关的Service
        ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(servletContextEvent.getServletContext());
        CommonService service = (CommonService) ac.getBean("commonService");
        service.doGenerate();

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {


    }
}
