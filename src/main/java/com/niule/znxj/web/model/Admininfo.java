package com.niule.znxj.web.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Admininfo {
    private Long id;

    private String username;

    private String password;

    private Integer state;

    private Long createuser;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expirydate;
    private Integer failnums;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date freezetime;

    private String email;

    private String realname;

    private Integer roleid;

    private Integer siteid;
    private Roles roles;
    private Siteareainfo site;

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public Siteareainfo getSite() {
        return site;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSite(Siteareainfo site) {
        this.site = site;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public Integer getFailnums() {
		return failnums;
	}

	public void setFailnums(Integer failnums) {
		this.failnums = failnums;
	}

	public Date getFreezetime() {
		return freezetime;
	}

	public void setFreezetime(Date freezetime) {
		this.freezetime = freezetime;
	}

	public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getCreateuser() {
        return createuser;
    }

    public void setCreateuser(Long createuser) {
        this.createuser = createuser;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getExpirydate() {
        return expirydate;
    }

    public void setExpirydate(Date expirydate) {
        this.expirydate = expirydate;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public Integer getSiteid() {
        return siteid;
    }

    public void setSiteid(Integer siteid) {
        this.siteid = siteid;
    }
    public Admininfo() {
    }

    public Admininfo(String username, String password) {
        this.username = username;
        this.password = password;
    }
}