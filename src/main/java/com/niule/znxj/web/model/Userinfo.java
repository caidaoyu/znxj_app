package com.niule.znxj.web.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Userinfo {
    private Long id;

    private Integer type;

    private String username;

    private String password;

    private String realname;

    private Integer sex;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthdate;

    private Long classid;

    private Long positionid;

    private Long commonterminalid;

    private Integer state;

    private Integer userstate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createtime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updatetime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date unenabletime;

    private Classinfo group;

    private Positioninfo pos;

    private Terminalinfo ter;

    public Terminalinfo getTer() {
        return ter;
    }

    public void setTer(Terminalinfo ter) {
        this.ter = ter;
    }

    public void setPos(Positioninfo pos) {

        this.pos = pos;
    }

    public Positioninfo getPos() {
        return pos;
    }

    public Classinfo getGroup() {
        return group;
    }

    public void setGroup(Classinfo group) {
        this.group = group;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
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

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Long getClassid() {
        return classid;
    }

    public void setClassid(Long classid) {
        this.classid = classid;
    }

    public Long getPositionid() {
        return positionid;
    }

    public void setPositionid(Long positionid) {
        this.positionid = positionid;
    }

    public Long getCommonterminalid() {
        return commonterminalid;
    }

    public void setCommonterminalid(Long commonterminalid) {
        this.commonterminalid = commonterminalid;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getUserstate() {
        return userstate;
    }

    public void setUserstate(Integer userstate) {
        this.userstate = userstate;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Date getUnenabletime() {
        return unenabletime;
    }

    public void setUnenabletime(Date unenabletime) {
        this.unenabletime = unenabletime;
    }
}