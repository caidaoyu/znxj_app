package com.niule.znxj.web.controller;

import com.niule.znxj.core.util.PageBean;
import com.niule.znxj.web.model.Admininfo;
import com.niule.znxj.web.model.Terminalinfo;
import com.niule.znxj.web.service.OperateLogService;
import com.niule.znxj.web.service.TerminalService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

/**
 * Created by administor on 2017/3/24.
 */
@Controller
public class WebTerminalController {
    @Resource
    private TerminalService terminalService;
    @Resource
    private OperateLogService operateLogService;
    protected PageBean pageBean = new PageBean();

    public PageBean getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean pageBean) {
        this.pageBean = pageBean;
    }

    @RequestMapping("showallterm")
    @RequiresPermissions("item:ter")
    public String showallterm(int page, String name, Model m) {
        if (page <= 0) {
            page = 1;
        }
        int pagesieze = 15;
        page = PageBean.countCurrentPage(page);
        List<Terminalinfo> terminalinfos = null;
        long totalpage = 0;
        if (name == null || "".equals(name)) {
            terminalinfos = terminalService.findByPageTerminal(page, pagesieze);
            int rows1 = terminalService.countTerminal();
            totalpage = PageBean.counTotalPage(pagesieze, rows1);
        } else {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("page", (page - 1) * pagesieze);
            map.put("pagesize", pagesieze);
            map.put("name", "%" + name + "%");
            terminalinfos = terminalService.findByPageTerminal2(map);
            int rows2 = terminalService.countTerminal2(map);
            totalpage = PageBean.counTotalPage(pagesieze, rows2);
        }
        pageBean.setList(terminalinfos);
        pageBean.setTotalPage((int) totalpage);
        pageBean.setCurrentPage(page);
        m.addAttribute("pageBean", pageBean);
        return "showterminalinfo";
    }

    @RequestMapping("addterminal")
    @RequiresPermissions("add:ter")
    public String addterminal(Terminalinfo terminalinfo, HttpSession session) {
        int addresult = terminalService.insert(terminalinfo);
        String info = "添加了终端" + terminalinfo.getCustomid();
        Admininfo logadmininfo = (Admininfo) session.getAttribute("userInfo");
        String username = logadmininfo.getUsername();
        int addlog = operateLogService.insertSelective(username, info);
        if (addresult > 0 && addlog > 0) {
            return "redirect:showallterm?page=1";
        }
        return "addterminalinfo";
    }

    @RequestMapping("delterminal")
    @RequiresPermissions("del:ter")
    @ResponseBody
    public int delterminal(Long id, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String ter = request.getParameter("tername");
        int delter = terminalService.deleteByPrimaryKey(id);
        String info = "删除了终端" + ter;
        Admininfo logadmininfo = (Admininfo) session.getAttribute("userInfo");
        String username = logadmininfo.getUsername();
        int addlog = operateLogService.insertSelective(username, info);
        if (delter > 0 && addlog > 0) {
            return 1;
        } else
            return 0;
    }

    @RequestMapping("querybytermid")
    @RequiresPermissions("upd:ter")
    public String querybytermid(Long id, Model m, int page) {
        Terminalinfo terminalinfo = terminalService.selectByPrimaryKey(id);
        m.addAttribute("terminalinfo", terminalinfo);
        m.addAttribute("page", page);
        return "updateterm";
    }

    @RequestMapping("updterm")
    public String updterm(Terminalinfo terminalinfo, HttpSession session, int page) {
        int updresult = terminalService.updateByPrimaryKeySelective(terminalinfo);
        Long termid = terminalinfo.getId();
        String info = "修改了终端" + terminalinfo.getCustomid();
        Admininfo logadmininfo = (Admininfo) session.getAttribute("userInfo");
        String username = logadmininfo.getUsername();
        int addlog = operateLogService.insertSelective(username, info);
        if (updresult > 0 && addlog > 0) {
            return "redirect:showallterm?page=" + page;
        }
        return "querybytermid?id=" + termid + "&page=" + page;
    }

    @RequestMapping("querytermdetail")/*
    @RequiresPermissions("item:terdetail")*/
    public String querytermdetail(Long id, Model m) {
        Terminalinfo terminalinfo = terminalService.selectByPrimaryKey(id);
        m.addAttribute("terminalinfo", terminalinfo);
        return "showterminaldetail";
    }
}
