package com.meiduimall.service.catalog.entity;

public class SysitemItemDesc {
    private Integer itemId;

    private String pcDesc;

    private String wapDesc;

    private String wirelessDesc;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getPcDesc() {
        return pcDesc;
    }

    public void setPcDesc(String pcDesc) {
        this.pcDesc = pcDesc == null ? null : pcDesc.trim();
    }

    public String getWapDesc() {
        return wapDesc;
    }

    public void setWapDesc(String wapDesc) {
        this.wapDesc = wapDesc == null ? null : wapDesc.trim();
    }

    public String getWirelessDesc() {
        return wirelessDesc;
    }

    public void setWirelessDesc(String wirelessDesc) {
        this.wirelessDesc = wirelessDesc == null ? null : wirelessDesc.trim();
    }
}