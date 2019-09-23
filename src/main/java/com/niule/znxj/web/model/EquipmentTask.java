package com.niule.znxj.web.model;

public class EquipmentTask {
    private Long id;

    private Long equipmentid;

    private Long taskid;

    public EquipmentTask(Long equipmentid, Long taskid) {
        this.equipmentid = equipmentid;
        this.taskid = taskid;
    }

    public EquipmentTask() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEquipmentid() {
        return equipmentid;
    }

    public void setEquipmentid(Long equipmentid) {
        this.equipmentid = equipmentid;
    }

    public Long getTaskid() {
        return taskid;
    }

    public void setTaskid(Long taskid) {
        this.taskid = taskid;
    }
}