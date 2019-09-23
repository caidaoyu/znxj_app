package com.niule.znxj.core.common;//package com.niule.znxj.core.common;
//
////import com.niule.znxj.web.service.TokenInfoService;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.FileSystemXmlApplicationContext;
//import org.springframework.web.context.support.WebApplicationContextUtils;
//
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//
///**
// * Created by MrD on 2016/12/5.
// */
//public class FangBangDaiInitListner implements ServletContextListener {
//    @Override
//    public void contextInitialized(ServletContextEvent servletContextEvent) {
////        //获取容器和相关的Service
////        ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(servletContextEvent.getServletContext());
////        TokenInfoService service = (TokenInfoService) ac.getBean("tokenInfoService");
////        // 初始化token
////        setTokens(service);
//    }
//
//    @Override
//    public void contextDestroyed(ServletContextEvent servletContextEvent) {
//
//    }
//
////    private void setTokens(TokenInfoService service) {
////        TokenManager.loadTokenFromDB(service.getTokenList());
////    }
//}
