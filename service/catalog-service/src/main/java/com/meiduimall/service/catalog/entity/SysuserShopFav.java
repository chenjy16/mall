package com.meiduimall.service.catalog.entity;

public class SysuserShopFav {
    private Integer snotifyId;

    private Integer shopId;

    private Integer userId;

    private String shopName;

    private String shopLogo;

    private Integer createTime;

    public Integer getSnotifyId() {
        return snotifyId;
    }

    public void setSnotifyId(Integer snotifyId) {
        this.snotifyId = snotifyId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public String getShopLogo() {
        return shopLogo;
    }

    public void setShopLogo(String shopLogo) {
        this.shopLogo = shopLogo == null ? null : shopLogo.trim();
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }
}