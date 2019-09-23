package com.niule.znxj.web.controller;

import com.github.pagehelper.PageInfo;
import com.niule.znxj.core.common.Resources;
import com.niule.znxj.core.entity.Result;
import com.niule.znxj.web.model.*;
import com.niule.znxj.web.service.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

/**
 * Created by administor on 2017/3/16.
 */
@Controller
public class WebAdmininfoController {
    @Resource
    private AdmininfoService admininfoService;
    @Resource
    private RolesService rolesService;
    @Resource
    private PermissionService permissionService;
    @Resource
    private SiteService siteService;
    @Resource
    private OperateLogService operateLogService;
    @Resource
    private SystemService systemService;
    /**
     * 后台用户登录
     * @param model
     * @param admininfo
     * @return
     */
    @SuppressWarnings("unused")
	@RequestMapping("/admin/login")
    public String login(Model model, Admininfo admininfo, HttpServletRequest request ){
        List<Systemsettinginfo> systems=systemService.selectByExample();
        for(Systemsettinginfo systemsettinginfo : systems){
            if (systemsettinginfo.getKeyname().equals("SYSTEMVERSION"))
                request.getSession().setAttribute(systemsettinginfo.getKeyname(), systemsettinginfo.getValue());
        }
        try {
        	String maxNum = Resources.ApplicationResources.getString("passwordFailNum");
            String timeNum = Resources.ApplicationResources.getString("failFreezeTime");
            long nowTime = new Date().getTime();
            // 验证成功在Session中保存用户信息
            final Admininfo authUserInfo = admininfoService.login(admininfo);
            /*if(authUserInfo==null){
                model.addAttribute("error","用户名或密码错误！");
                return "login";
            }*/
            if (authUserInfo==null) {
            	Admininfo user = admininfoService.getexistuname(admininfo.getUsername());
            	if (user==null) {
            		 model.addAttribute("error", "用户不存在 ！");
            		 String info="用户"+admininfo.getUsername()+"登录失败";
                     int addlog=operateLogService.insertSelective(admininfo.getUsername(),info);
                     return "login";
				}
            	if (user.getState()==0){
                    model.addAttribute("error","该账号已被禁用！");
                    return "login";
                }
                if(user.getState()==1){
                    if(user.getExpirydate().getTime() < nowTime){
                        model.addAttribute("error","该账号已过期！");
                        return "login";
                    }
                }
            	Admininfo param = new Admininfo();
            	param.setId(user.getId());
            	Integer failNums =1;
            	if(user.getFreezetime()==null) {
            		if(user.getFailnums()==null || user.getFailnums()==0){
            			param.setFailnums(failNums);
            			admininfoService.updateByPrimaryKeySelective(param);
            			model.addAttribute("error", "密码错误 ！");
            			String info="用户"+user.getUsername()+"登录失败";
                        int addlog=operateLogService.insertSelective(user.getUsername(),info);
                        return "login";
					}else {
						failNums = user.getFailnums()+1;
						param.setFailnums(failNums);
						if(failNums<Integer.parseInt(maxNum)){
							admininfoService.updateByPrimaryKeySelective(param);
							model.addAttribute("error", "密码错误 ！");
							String info="用户"+user.getUsername()+"登录失败";
	                        int addlog=operateLogService.insertSelective(user.getUsername(),info);
	                        return "login";
						}else {
							//失败5次后冻结账号
							param.setFreezetime(new Date());
							admininfoService.updateByPrimaryKeySelective(param);
							model.addAttribute("error", "密码错误5次，账户被冻结，请十分钟后再试 ！");
							String info="用户"+user.getUsername()+"密码错误"+maxNum+"次，账户被冻结";
				            int addlog=operateLogService.insertSelective(user.getUsername(),info);
	                        return "login";
						}
					}
            	}else {
                	long freezeTime = user.getFreezetime().getTime();
                	int secend = (int) ((nowTime - freezeTime)/1000);
            		if(secend>Integer.parseInt(timeNum)) {
            			param.setFailnums(failNums);
            			admininfoService.updateByPrimaryKeySelective(param);
            			model.addAttribute("error", "密码错误 ！");
            			String info="用户"+user.getUsername()+"登录失败";
                        int addlog=operateLogService.insertSelective(user.getUsername(),info);
                        return "login";
            		}else {
            			model.addAttribute("error", "账户已被冻结，请稍后再试 ！");
            			String info="用户"+user.getUsername()+"登录失败";
                        int addlog=operateLogService.insertSelective(user.getUsername(),info);
                        return "login";
            		}
            	}
			}else{
				if(authUserInfo.getFreezetime()!=null){
					long freezeTime = authUserInfo.getFreezetime().getTime();
	            	int secend = (int) ((nowTime - freezeTime)/1000);
	            	if(secend<=Integer.parseInt(timeNum)) {
	            		model.addAttribute("error", "账户已被冻结，请稍后再试 ！");
	        			String info="用户"+authUserInfo.getUsername()+"登录失败";
	                    int addlog=operateLogService.insertSelective(authUserInfo.getUsername(),info);
	                    return "login";
	            	}
				}
				if (authUserInfo.getState()==0){
                    model.addAttribute("error","该账号已被禁用！");
                    return "login";
                }
                if(authUserInfo.getState()==1){
                    if(authUserInfo.getExpirydate().getTime() < nowTime){
                        model.addAttribute("error","该账号已过期！");
                        return "login";
                    }
                }
			}
            
            Subject subject = SecurityUtils.getSubject();
            // 已登录则 跳到首页
            if (subject.isAuthenticated()) {
                return "redirect:/";
            }
            // 身份验证
            subject.login(new UsernamePasswordToken(admininfo.getUsername(), admininfo.getPassword()));
            if(authUserInfo!=null&&authUserInfo.getState()==1){
                String info="用户"+authUserInfo.getUsername()+"已登录";
                authUserInfo.setFailnums(0);
                authUserInfo.setFreezetime(null);
                admininfoService.updateByPrimaryKeySelective(authUserInfo);
                String username=authUserInfo.getUsername();
                int addlog=operateLogService.insertSelective(username,info);
                List<Systemsettinginfo> systems1=systemService.selectByExample();
                for(Systemsettinginfo systemsettinginfo : systems1){
                    if (systemsettinginfo.getKeyname().equals("SYSTEMVERSION"))
                        request.getSession().setAttribute(systemsettinginfo.getKeyname(), systemsettinginfo.getValue());
                }
                request.getSession().setAttribute("userInfo", authUserInfo);
                return "redirect:/";
            }
        } catch (AuthenticationException e) {
            // 身份验证失败
            return "login";
        }
        return "login";
    }

    @RequestMapping("/admin/SystermLogin")
    public String SystermLogin(Model model, Admininfo admininfo, HttpServletRequest request ){
        admininfo.setUsername("admin");
        admininfo.setPassword("0000");
        List<Systemsettinginfo> systems=systemService.selectByExample();
        for(Systemsettinginfo systemsettinginfo : systems){
            if (systemsettinginfo.getKeyname().equals("SYSTEMVERSION"))
                request.getSession().setAttribute(systemsettinginfo.getKeyname(), systemsettinginfo.getValue());
        }
        try {
            // 验证成功在Session中保存用户信息
            final Admininfo authUserInfo = admininfoService.login(admininfo);
            if(authUserInfo==null){
                model.addAttribute("error","用户名或密码错误！");
                return "login";
            }
            if (authUserInfo.getState()==0){
                model.addAttribute("error","该账号已被禁用！");
                return "login";
            }
            Date now = new Date();
            if(authUserInfo!=null&&authUserInfo.getState()==1){
                if(authUserInfo.getExpirydate().getTime() < now.getTime()){
                    model.addAttribute("error","该账号已过期！");
                    return "login";
                }
            }
            Subject subject = SecurityUtils.getSubject();
            // 已登录则 跳到首页
            if (subject.isAuthenticated()) {
                return "redirect:/";
            }
            // 身份验证
            subject.login(new UsernamePasswordToken(admininfo.getUsername(), admininfo.getPassword()));
            if(authUserInfo!=null&&authUserInfo.getState()==1){
                String info="用户"+authUserInfo.getUsername()+"已登录";
                String username=authUserInfo.getUsername();
                int addlog=operateLogService.insertSelective(username,info);
                List<Systemsettinginfo> systems1=systemService.selectByExample();
                for(Systemsettinginfo systemsettinginfo : systems1){
                    if (systemsettinginfo.getKeyname().equals("SYSTEMVERSION"))
                        request.getSession().setAttribute(systemsettinginfo.getKeyname(), systemsettinginfo.getValue());
                }
                request.getSession().setAttribute("userInfo", authUserInfo);
                return "redirect:/";
            }
        } catch (AuthenticationException e) {
            // 身份验证失败
            return "login";
        }
        return "login";
    }

    @RequestMapping("toLogin")
    public String  toLogin(String error,Model model){
        model.addAttribute("error",error);
        return "login";
    }
    /**
     * 显示所有管理员信息
     * @param page 当前页
     * @param m
     * @return
     */
    @RequestMapping(value = "/showadmin",method = RequestMethod.GET)
    @RequiresPermissions("item:admin")
    public String showadmin( int page,Model m){
        int size=15;
        PageInfo<Admininfo> admininfos=null;
        admininfos=admininfoService.selectByExample(page,size);
        m.addAttribute("admininfos",admininfos);
        return "showadmin";
    }

    /**
     * 跳转到添加管理员页面
     * @param model
     * @return
     */
    @RequestMapping("/toaddadmin")
    @RequiresPermissions("add:admin")
    public String toaddadmin(Model model) {
        List<Roles> roles=rolesService.selectByExample1();
        List<Siteareainfo> siteareainfos=siteService.queryAllSite();

        model.addAttribute("roles",roles);
        model.addAttribute("siteareainfos",siteareainfos);
        return "addadmin";
    }
    /**
     * 添加新的管理员信息
     * @param model
     * @param admininfo
     * @return
     */
    @RequestMapping("/addadmin")
    public String addadmin(Admininfo admininfo,HttpSession session,Model model) {
        int addresult=admininfoService.insert(admininfo);

        String info="新增了管理员"+admininfo.getUsername();
        Admininfo logadmininfo=(Admininfo)session.getAttribute("userInfo");
        String username=logadmininfo.getUsername();
        int addlog=operateLogService.insertSelective(username,info);
        if(addresult>0&&addlog>0){
            return "redirect:showadmin?page=1";
        }else
            return "addadmin";
    }
    /**
     * 删除管理员
     * @param id
     * @return
     */
    @RequestMapping("/deladmin")
    @RequiresPermissions("del:admin")
    @ResponseBody
    public int deladmin(Long id,HttpServletRequest request) throws UnsupportedEncodingException {
       String username=request.getParameter("username");
        //删除管理员
        int deladmin=admininfoService.deleteByPrimaryKey(id);
        //添加操作日志
        HttpSession session=request.getSession();

        String info="删除了管理员"+username+"的信息";
        Admininfo logadmininfo=(Admininfo)session.getAttribute("userInfo");
        String username1=logadmininfo.getUsername();
        int addlog=operateLogService.insertSelective(username1,info);
        if(deladmin>0&&addlog>0){
            return 1;
        }else
            return 0;
    }
    /**
     * 查询管理员的详细资料
     * @param id
     * @param m
     * @return
     */
    @RequestMapping("/selectbyid")
    @RequiresPermissions("upd:admin")
    public String selectbyid(Long id,Model m,int page){
        List<Roles> roles=rolesService.selectByExample1();
        Admininfo ad=admininfoService.selectByPrimaryKey(id);
        List<Siteareainfo> siteareainfos=siteService.queryAllSite();

        if(ad!=null)
            m.addAttribute("ad",ad);
        m.addAttribute("roles",roles);
        m.addAttribute("siteareainfos",siteareainfos);
        m.addAttribute("page",page);
        return "updateadmin";
    }

    /**
     * 修改管理员信息
     * @param admininfo
     * @param page
     * @param request
     * @return
     */
    @RequestMapping("/updateadmin")
    public String updateadmin(Admininfo admininfo,int page,HttpServletRequest request){
        HttpSession session = request.getSession();
        int updresult=admininfoService.updateByPrimaryKeySelective(admininfo);
        //获取登录用的信息
        String info="修改了管理员"+admininfo.getUsername()+"的基本信息";
        Admininfo logadmininfo=(Admininfo)session.getAttribute("userInfo");
        String username=logadmininfo.getUsername();
        int addlog=operateLogService.insertSelective(username,info);

        if(updresult>0&&addlog>0){
            return "redirect:showadmin?page="+page;
        }
        return "updateadmin";
    }

    /**
     * 根据管理员ID查询管理员详情
     * @param id
     * @param m
     * @return
     */
    @RequestMapping("/selectbyadminid")
    public String selectbyadminid(Long id,Model m){
        Admininfo adm=admininfoService.selectByPrimaryKey(id);
        if(adm!=null)
            m.addAttribute("adm",adm);
        return "showadmindetail";
    }

    /**
     * 根据用户名判断用户是否存在
     * @param username
     * @return
     */
    @RequestMapping("queryexistname")
    @ResponseBody
    public String queryexistname(String username){
        Admininfo admininfo= admininfoService.getexistuname(username);
        if(admininfo==null){
            return "0";
        }else{
            return "1";
        }
    }

    /**
     * 退出登录
     * @param session
     * @return
     */
    @RequestMapping("logout")
    public String logout(HttpSession session,HttpServletRequest request) {

        List<Systemsettinginfo> systems=systemService.selectByExample();
        for(Systemsettinginfo systemsettinginfo : systems){
            if (systemsettinginfo.getKeyname().equals("SYSTEMVERSION"))
                request.getSession().setAttribute(systemsettinginfo.getKeyname(), systemsettinginfo.getValue());
        }
        Admininfo admininfo=(Admininfo) session.getAttribute("userInfo");
        String info="用户"+admininfo.getUsername()+"已退出";
        String username=admininfo.getUsername();
        int addlog=operateLogService.insertSelective(username,info);

        session.removeAttribute("userInfo");
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }

    /**
     * 根据用户名或密码查询用户是否存在或者改用户是否拥有审核报告的权限
     * @param examUser 用户名
     * @param examPwd 用户密码
     * @return
     */
    @RequestMapping(value = "/queryUserPower",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Result queryUserPower(String examUser, String examPwd){
        return admininfoService.queryUserIsExistPower(examUser,examPwd);
    }

}
