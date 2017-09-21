package com.meiduimall.service.catalog.entity;

public class SysshopShop {
    private Integer shopId;

    private String shopName;

    private String shopType;

    private Integer sellerId;

    private String status;

    private Integer openTime;

    private Integer closeTime;

    private String closeReason;

    private String shopLogo;

    private String shopuserName;

    private String qq;

    private String wangwang;

    private String email;

    private String mobile;

    private String shopuserIdentity;

    private String shopuserIdentityImg;

    private String shopArea;

    private String bulletin;

    private Integer checkGoods;

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public String getShopType() {
        return shopType;
    }

    public void setShopType(String shopType) {
        this.shopType = shopType == null ? null : shopType.trim();
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Integer openTime) {
        this.openTime = openTime;
    }

    public Integer getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Integer closeTime) {
        this.closeTime = closeTime;
    }

    public String getCloseReason() {
        return closeReason;
    }

    public void setCloseReason(String closeReason) {
        this.closeReason = closeReason == null ? null : closeReason.trim();
    }

    public String getShopLogo() {
        return shopLogo;
    }

    public void setShopLogo(String shopLogo) {
        this.shopLogo = shopLogo == null ? null : shopLogo.trim();
    }

    public String getShopuserName() {
        return shopuserName;
    }

    public void setShopuserName(String shopuserName) {
        this.shopuserName = shopuserName == null ? null : shopuserName.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getWangwang() {
        return wangwang;
    }

    public void setWangwang(String wangwang) {
        this.wangwang = wangwang == null ? null : wangwang.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getShopuserIdentity() {
        return shopuserIdentity;
    }

    public void setShopuserIdentity(String shopuserIdentity) {
        this.shopuserIdentity = shopuserIdentity == null ? null : shopuserIdentity.trim();
    }

    public String getShopuserIdentityImg() {
        return shopuserIdentityImg;
    }

    public void setShopuserIdentityImg(String shopuserIdentityImg) {
        this.shopuserIdentityImg = shopuserIdentityImg == null ? null : shopuserIdentityImg.trim();
    }

    public String getShopArea() {
        return shopArea;
    }

    public void setShopArea(String shopArea) {
        this.shopArea = shopArea == null ? null : shopArea.trim();
    }

    public String getBulletin() {
        return bulletin;
    }

    public void setBulletin(String bulletin) {
        this.bulletin = bulletin == null ? null : bulletin.trim();
    }

    public Integer getCheckGoods() {
        return checkGoods;
    }

    public void setCheckGoods(Integer checkGoods) {
        this.checkGoods = checkGoods;
    }
}