package com.meiduimall.service.financial.entity;

/**
 * 渠道统计结果封装
 * @author yangchang
 *
 */
public class DownloadStatisticsResult {

	// 渠道编码
	private int portal;
	
	// 每个渠道对应的下载数量
	private int count;
	
	public int getPortal() {
		return portal;
	}
	public void setPortal(int portal) {
		this.portal = portal;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
