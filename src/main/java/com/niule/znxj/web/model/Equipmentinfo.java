package com.niule.znxj.web.model;

public class Equipmentinfo {
    private Long id;

    private String customid;

    private String name;

    private String type;

    private String longitude;

    private String latitude;

    private String desccontent;

    private Long nfcid;

    private Long areaid;

    private Nfcinfo nfc;

    private Areainfo areainfo;

    private Boolean isChecked;

    public Boolean getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(Boolean checked) {
        isChecked = checked;
    }

    public Areainfo getAreainfo() {
        return areainfo;
    }

    public void setAreainfo(Areainfo areainfo) {
        this.areainfo = areainfo;
    }

    public Nfcinfo getNfc() {
        return nfc;
    }

    public void setNfc(Nfcinfo nfc) {
        this.nfc = nfc;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    public String getDesccontent() {
        return desccontent;
    }

    public void setDesccontent(String desccontent) {
        this.desccontent = desccontent == null ? null : desccontent.trim();
    }

    public Long getNfcid() {
        return nfcid;
    }

    public void setNfcid(Long nfcid) {
        this.nfcid = nfcid;
    }

    public Long getAreaid() {
        return areaid;
    }

    public void setAreaid(Long areaid) {
        this.areaid = areaid;
    }
}