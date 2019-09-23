package com.niule.znxj.web.model.response;

import com.niule.znxj.web.model.Roles;

/**
 * Created by administor on 2017/5/4.
 */
public class RoleRes {
    private Roles roles;

    private String permissionstr;

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public String getPermissionstr() {
        return permissionstr;
    }

    public void setPermissionstr(String permissionstr) {
        this.permissionstr = permissionstr;
    }
}
