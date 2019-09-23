package com.niule.znxj.web.model;

public class Roles {
    private Integer roleid;

    private String rolename;

    private String rolestate;

    private String rolessign;

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }

    public String getRolestate() {
        return rolestate;
    }

    public void setRolestate(String rolestate) {
        this.rolestate = rolestate == null ? null : rolestate.trim();
    }

    public String getRolessign() {
        return rolessign;
    }

    public void setRolessign(String rolessign) {
        this.rolessign = rolessign == null ? null : rolessign.trim();
    }
}