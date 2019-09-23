package com.niule.znxj.web.model;

public class Positioninfo {
    private Long id;

    private String positionname;

    private String positiondesc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPositionname() {
        return positionname;
    }

    public void setPositionname(String positionname) {
        this.positionname = positionname == null ? null : positionname.trim();
    }

    public String getPositiondesc() {
        return positiondesc;
    }

    public void setPositiondesc(String positiondesc) {
        this.positiondesc = positiondesc == null ? null : positiondesc.trim();
    }
}