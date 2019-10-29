package com.niule.znxj.web.security;

/**
 * 角色标识配置类, <br>
 * 与 role_info 角色表中的 role_sign 字段 相对应 <br>
 * 使用:
 * 
 * <pre>
 * &#064;RequiresRoles(value = RoleSign.ADMIN)
 * public String admin() {
 *     return &quot;拥有admin角色,能访问&quot;;
 * }
 * </pre>
 * 
 * @author StarZou
 * @since 2014年6月17日 下午3:58:51
 **/
public class RoleSign {

    /**
     * 后台管理员 标识
     */
    public static final String ADMIN = "admin";

    /**
     * 经理 标识
     */
    public static final String MANAGER = "manager";

    /**
     * 客服 标识
     */
    public static final String SERVER = "server";


    /**
     * 添加更多...
     */

}
