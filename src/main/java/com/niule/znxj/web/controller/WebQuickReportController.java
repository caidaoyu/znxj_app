package com.niule.znxj.web.controller;

import com.github.pagehelper.PageInfo;
import com.niule.znxj.core.util.PageBean;
import com.niule.znxj.web.model.Admininfo;
import com.niule.znxj.web.model.Positioninfo;
import com.niule.znxj.web.model.Quickreport;
import com.niule.znxj.web.service.QuickReportService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * Created by administor on 2017/4/18.
 */
@Controller
public class WebQuickReportController {
    @Resource
    private QuickReportService quickReportService;
    protected PageBean pageBean = new PageBean();

    public PageBean getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean pageBean) {
        this.pageBean = pageBean;
    }
    /**
     * 查看所有即拍即传信息
     * @param m
     * @return
     */
    @RequestMapping("showQuickReport")
    @RequiresPermissions("item:quick")
    public String showQuickReport(int page, Model m, String reportcode, String uploadtime, HttpServletRequest request){
        int size=15;
        Admininfo admininfo=(Admininfo) request.getSession().getAttribute("userInfo");
        HashMap<String,Object> map=new HashMap<>();
        map.put("page",page);
        map.put("size",size);
        map.put("reportcode",reportcode==null||reportcode.isEmpty()?"":"%"+reportcode+"%");
        map.put("time",uploadtime);
        map.put("siteid",admininfo.getSiteid());
        PageInfo<Quickreport> info=new PageInfo<>(quickReportService.showQuickreport2(map));
//        if((reportcode==null||"".equals(reportcode))&&(uploadtime==null||"".equals(uploadtime))){
//            info= new PageInfo<>(quickReportService.showQuickreport(page,size));
//        }else{
//            info=
//        }
        m.addAttribute("info",info);
        m.addAttribute("reportcode",reportcode);
        return "showreport4";
    }
    @RequestMapping("querybyquickid")
    /*@RequiresPermissions("item:quickdetail")*/
    @ResponseBody
    public Quickreport querybyquickid(Long id){
        return quickReportService.selectByPrimaryKey(id);
    }

    @RequestMapping("delbyquickid")
    @ResponseBody
    public int delbyquickid(Long id){
        return quickReportService.deleteByPrimaryKey(id);
    }
    /**
     * 隐患排查即拍即传任务报告列表
     * @param page
     * @param m
     * @param qtype
     * @param type
     * @return
     */
    @RequestMapping("showQuickReport1")
    @RequiresPermissions("item:reportquick")
    public String showQuickReport1(int page,Model m,int qtype,int type){
        if(page<=0){
            page=1;
        }
        int pagesize=10;
        page = PageBean.countCurrentPage(page);
        List<Quickreport> quickreports=quickReportService.selectByExample(page,pagesize,qtype);
        int rows =quickReportService.countByExample(qtype);
        long totalpage = PageBean.counTotalPage(pagesize, rows);

        pageBean.setList(quickreports);
        pageBean.setTotalPage((int) totalpage);
        pageBean.setCurrentPage(page);
        m.addAttribute("pageBean",pageBean);
        m.addAttribute("type",type);
        return "showtaskplan4";
    }

    @RequestMapping("querywarningdetail")
    /*@RequiresPermissions("item:reportquickdetail")*/
    @ResponseBody
    public Quickreport querywarningdetail(Long id){
        return quickReportService.selectByPrimaryKey(id);
    }

}
