package com.niule.znxj.web.security;

/**
 * 权限标识配置类, <br>
 * 与 permission 权限表 中的 permission_sign 字段 相对应 <br>
 * 使用:
 * 
 * <pre>
 * &#064;RequiresPermissions(value = PermissionConfig.USER_CREATE)
 * public String create() {
 *     return &quot;拥有user:create权限,能访问&quot;;
 * }
 * </pre>
 * 
 * @author StarZou
 * @since 2014年6月17日 下午3:58:51
 **/
public class PermissionSign {

    /**
     * 经理编辑权限 标识
     */
    public static final String MANAGER_EDIT = "manager:edit";

    /**
     * 客服编辑权限 标识
     */
    public static final String SERVER_EDIT= "server:edit";

    /**
     * 添加更多...
     */

}
