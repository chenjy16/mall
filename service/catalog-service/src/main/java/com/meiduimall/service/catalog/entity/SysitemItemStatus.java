package com.meiduimall.service.catalog.entity;

public class SysitemItemStatus {
    private Integer itemId;

    private Integer shopId;

    private String approveStatus;

    private Integer listTime;

    private Integer delistTime;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus == null ? null : approveStatus.trim();
    }

    public Integer getListTime() {
        return listTime;
    }

    public void setListTime(Integer listTime) {
        this.listTime = listTime;
    }

    public Integer getDelistTime() {
        return delistTime;
    }

    public void setDelistTime(Integer delistTime) {
        this.delistTime = delistTime;
    }
}