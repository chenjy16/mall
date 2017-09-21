package com.meiduimall.service.financial.entity;

import java.util.Date;

/**
 * 下载渠道统计表
 * @author yangchang
 *
 */
public class DownloadStatistics {
	
    private Integer id;

    // 渠道编号
    private Integer portal;

    // 访问时间
    private Date requestTime;

    // 请求IP
    private String ip;

    // 时间戳
    private Date currTime;

    // 浏览器信息
    private String userAgent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPortal() {
        return portal;
    }

    public void setPortal(Integer portal) {
        this.portal = portal;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
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

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent == null ? null : userAgent.trim();
    }
}