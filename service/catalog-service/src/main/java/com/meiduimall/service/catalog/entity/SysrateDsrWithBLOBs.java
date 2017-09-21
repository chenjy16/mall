package com.meiduimall.service.catalog.entity;

public class SysrateDsrWithBLOBs extends SysrateDsr {
    private String tallyDsr;

    private String attitudeDsr;

    private String deliverySpeedDsr;

    public String getTallyDsr() {
        return tallyDsr;
    }

    public void setTallyDsr(String tallyDsr) {
        this.tallyDsr = tallyDsr == null ? null : tallyDsr.trim();
    }

    public String getAttitudeDsr() {
        return attitudeDsr;
    }

    public void setAttitudeDsr(String attitudeDsr) {
        this.attitudeDsr = attitudeDsr == null ? null : attitudeDsr.trim();
    }

    public String getDeliverySpeedDsr() {
        return deliverySpeedDsr;
    }

    public void setDeliverySpeedDsr(String deliverySpeedDsr) {
        this.deliverySpeedDsr = deliverySpeedDsr == null ? null : deliverySpeedDsr.trim();
    }
}