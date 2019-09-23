package com.niule.znxj.web.model;

public class Knowledge {
    private Integer id;

    private String title;

    private String descontent;

    private Integer typeid;

    private Integer equipid;

    private String attachment;

    private Knowledgetype ktype;

    private Equipmentinfo equip;

    public Equipmentinfo getEquip() {
        return equip;
    }

    public void setEquip(Equipmentinfo equip) {
        this.equip = equip;
    }

    public Knowledgetype getKtype() {
        return ktype;
    }

    public void setKtype(Knowledgetype ktype) {
        this.ktype = ktype;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDescontent() {
        return descontent;
    }

    public void setDescontent(String descontent) {
        this.descontent = descontent == null ? null : descontent.trim();
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public Integer getEquipid() {
        return equipid;
    }

    public void setEquipid(Integer equipid) {
        this.equipid = equipid;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment == null ? null : attachment.trim();
    }
}