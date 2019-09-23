package com.niule.znxj.web.model;

public class Checkiteminfo {
    private Long id;

    private String customid;

    private String itemname;

    private String keyword;

    private Integer type;

    private Double normalmin;

    private Double normalmax;

    private Double upperwarning;

    private Double lowerwarning;

    private Long recordid;

    private Daterecordinfo daterecord;


    public Daterecordinfo getDaterecord() {
        return daterecord;
    }

    public void setDaterecord(Daterecordinfo daterecord) {
        this.daterecord = daterecord;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomid() {
        return customid;
    }

    public void setCustomid(String customid) {
        this.customid = customid == null ? null : customid.trim();
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname == null ? null : itemname.trim();
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Double getNormalmin() {
        return normalmin;
    }

    public void setNormalmin(Double normalmin) {
        this.normalmin = normalmin;
    }

    public Double getNormalmax() {
        return normalmax;
    }

    public void setNormalmax(Double normalmax) {
        this.normalmax = normalmax;
    }

    public Double getUpperwarning() {
        return upperwarning;
    }

    public void setUpperwarning(Double upperwarning) {
        this.upperwarning = upperwarning;
    }

    public Double getLowerwarning() {
        return lowerwarning;
    }

    public void setLowerwarning(Double lowerwarning) {
        this.lowerwarning = lowerwarning;
    }

    public Long getRecordid() {
        return recordid;
    }

    public void setRecordid(Long recordid) {
        this.recordid = recordid;
    }
}