package com.niule.znxj.web.model;

/**
 * Created by administor on 2017/6/26.
 */
public class OwnPower {
    private Power power;
    private Boolean hasPower;


    public Power getPower() {
        return power;
    }

    public void setPower(Power power) {
        this.power = power;
    }

    public OwnPower() {
    }

    public OwnPower(Power power) {
        this.power = power;
    }

    public Boolean getHasPower() {
        return hasPower;
    }

    public void setHasPower(Boolean hasPower) {
        this.hasPower = hasPower;
    }
}
