package com.niule.znxj.web.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Quickreport {
    private Long id;

    private String reportcode;

    private Long userid;

    private String content;

    private String img;

    private String audio;

    private String video;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date uploadtime;

    private Integer type;

    private Integer state;

    private String upload;
    private Userinfo u;
    private Classinfo group;
    private Siteareainfo site;

    public Classinfo getGroup() {
        return group;
    }

    public void setGroup(Classinfo group) {
        this.group = group;
    }

    public Siteareainfo getSite() {
        return site;
    }

    public void setSite(Siteareainfo site) {
        this.site = site;
    }

    public Userinfo getU() {
        return u;
    }

    public void setU(Userinfo u) {
        this.u = u;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReportcode() {
        return reportcode;
    }

    public void setReportcode(String reportcode) {
        this.reportcode = reportcode == null ? null : reportcode.trim();
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio == null ? null : audio.trim();
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video == null ? null : video.trim();
    }

    public Date getUploadtime() {
        return uploadtime;
    }

    public void setUploadtime(Date uploadtime) {
        this.uploadtime = uploadtime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getUpload() {
        return upload;
    }

    public void setUpload(String upload) {
        this.upload = upload == null ? null : upload.trim();
    }
}