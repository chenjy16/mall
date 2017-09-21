package com.meiduimall.service.catalog.entity;

public class SysshopShopWithBLOBs extends SysshopShop {
    private String shopDescript;

    private String shopAddr;

    public String getShopDescript() {
        return shopDescript;
    }

    public void setShopDescript(String shopDescript) {
        this.shopDescript = shopDescript == null ? null : shopDescript.trim();
    }

    public String getShopAddr() {
        return shopAddr;
    }

    public void setShopAddr(String shopAddr) {
        this.shopAddr = shopAddr == null ? null : shopAddr.trim();
    }
}