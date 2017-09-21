package com.meiduimall.service.catalog.entity;

import java.util.Date;

/**
 * 表结构对象--商品推荐表
 * @author yangchang
 *
 */
public class SysitemItemRecommend {
    private Integer id;

    private Integer itemId;

    private Date recoTime;

    private Integer recoLevel;

    private Integer recoType;

    private String optUser;

    private String ip;

    private Date currTime;

    private String recoDesc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Date getRecoTime() {
        return recoTime;
    }

    public void setRecoTime(Date recoTime) {
        this.recoTime = recoTime;
    }

    public Integer getRecoLevel() {
        return recoLevel;
    }

    public void setRecoLevel(Integer recoLevel) {
        this.recoLevel = recoLevel;
    }

    public Integer getRecoType() {
        return recoType;
    }

    public void setRecoType(Integer recoType) {
        this.recoType = recoType;
    }

    public String getOptUser() {
        return optUser;
    }

    public void setOptUser(String optUser) {
        this.optUser = optUser == null ? null : optUser.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Date getCurrTime() {
        return currTime;
    }

    public void setCurrTime(Date currTime) {
        this.currTime = currTime;
    }

    public String getRecoDesc() {
        return recoDesc;
    }

    public void setRecoDesc(String recoDesc) {
        this.recoDesc = recoDesc == null ? null : recoDesc.trim();
    }
}