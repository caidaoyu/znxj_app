package com.niule.znxj.web.model;

public class Sendemail {
    private Long id;

    private Long taskid;

    private String contactid;

    private Integer state;

    private Integer type;

    private String email;

    private String pwd;

    private String smtpAddress;

    private String smtpPort;

    private Integer sendexception;

    private Taskplaninfo task;

    public Taskplaninfo getTask() {
        return task;
    }

    public void setTask(Taskplaninfo task) {
        this.task = task;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTaskid() {
        return taskid;
    }

    public void setTaskid(Long taskid) {
        this.taskid = taskid;
    }

    public String getContactid() {
        return contactid;
    }

    public void setContactid(String contactid) {
        this.contactid = contactid == null ? null : contactid.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public String getSmtpAddress() {
        return smtpAddress;
    }

    public void setSmtpAddress(String smtpAddress) {
        this.smtpAddress = smtpAddress == null ? null : smtpAddress.trim();
    }

    public String getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(String smtpPort) {
        this.smtpPort = smtpPort == null ? null : smtpPort.trim();
    }

    public Integer getSendexception() {
        return sendexception;
    }

    public void setSendexception(Integer sendexception) {
        this.sendexception = sendexception;
    }
}