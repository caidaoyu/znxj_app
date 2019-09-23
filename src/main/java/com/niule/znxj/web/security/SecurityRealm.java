package com.niule.znxj.web.security;

import com.niule.znxj.web.dao.PowerMapper;
import com.niule.znxj.web.model.Admininfo;
import com.niule.znxj.web.model.Permission;
import com.niule.znxj.web.model.Power;
import com.niule.znxj.web.model.Roles;
import com.niule.znxj.web.service.AdmininfoService;
import com.niule.znxj.web.service.PermissionService;
import com.niule.znxj.web.service.RolesService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.List;

/**
 * 用户身份验证,授权 Realm 组件
 *
 * @author StarZou
 * @since 2014年6月11日 上午11:35:28
 **/
@Component(value = "securityRealm")
public class SecurityRealm extends AuthorizingRealm {
    @Resource
    private AdmininfoService admininfoService;
    @Resource
    private RolesService rolesService;
    @Resource
    private PowerMapper powerMapper;
    /**
     * 权限检查
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String username = String.valueOf(principals.getPrimaryPrincipal());

        final Admininfo admin = admininfoService.getexistuname(username);
        final Roles role = rolesService.selectByPrimaryKey(admin.getRoleid());
            // 添加角色
           /* authorizationInfo.addRole(role.getRolessign());*/
            final List<Power> permissions = powerMapper.selectByRoleid(role.getRoleid());
            for (Power permission : permissions) {// 添加权限
                authorizationInfo.addStringPermission(permission.getPermissionsign());
            }
        return authorizationInfo;
    }

    /**
     * 登录验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = String.valueOf(token.getPrincipal());
        String password = new String((char[]) token.getCredentials());
        // 通过数据库进行验证
        final Admininfo authentication = admininfoService.login(new Admininfo(username, password));
        if (authentication == null) {
            throw new AuthenticationException("用户名或密码错误.");
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, password, getName());
        return authenticationInfo;
    }
    /*protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
　　　　Subject subject = getSubject(request, response);
        String redirectUrl = getred(request, response, subject);
        try {

            subject.logout();

        } catch (SessionException ise) {

            ise.printStackTrace();

        }

        issueRedirect(request, response, redirectUrl);

　　　　　//返回false表示不执行后续的过滤器，直接返回跳转到登录页面

        return false;

    }*/
}
