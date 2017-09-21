package com.meiduimall.service.financial.entity;

/**
 * 按时间段查询，请求参数封装
 * @author yangchang
 *
 */
public class DownloadStatisticsDTO {

	// 开始时间，格式'2017-03-08 09:30:25'
	private String beginDate;
	
	// 结束时间
	private String endDate;
	
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
