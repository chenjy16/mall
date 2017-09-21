package com.meiduimall.service.settlement.model;

import java.io.Serializable;

public class ShareProfitOrderLog implements Serializable{
	

	private static final long serialVersionUID = 2907690276453091466L;
	
	private Integer id;
	private String orderSn;
	private String reason;
	private Integer createdDate;
	
	/** 重试状态	1，成功，0，失败   **/
	private Integer retryStatus;
	private Integer retryTime;
	
	/** 重试标识 1:需要重试 0:不需要重试  **/
	private Integer retryFlag=0; 
	private String remark;
	
	public ShareProfitOrderLog() {
		super();
	}
	
	
	public ShareProfitOrderLog(String orderSn, String reason, Integer createdDate, Integer retryStatus,
			Integer retryTime, String remark) {
		super();
		this.orderSn = orderSn;
		this.reason = reason;
		this.createdDate = createdDate;
		this.retryStatus = retryStatus;
		this.retryTime = retryTime;
		this.remark = remark;
	}

	

	public ShareProfitOrderLog(String orderSn, String reason, Integer createdDate, String remark) {
		super();
		this.orderSn = orderSn;
		this.reason = reason;
		this.createdDate = createdDate;
		this.remark = remark;
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrderSn() {
		return orderSn;
	}
	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Integer getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Integer createdDate) {
		this.createdDate = createdDate;
	}
	public Integer getRetryStatus() {
		return retryStatus;
	}
	public void setRetryStatus(Integer retryStatus) {
		this.retryStatus = retryStatus;
	}
	public Integer getRetryTime() {
		return retryTime;
	}
	public void setRetryTime(Integer retryTime) {
		this.retryTime = retryTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Integer getRetryFlag() {
		return retryFlag;
	}

	public void setRetryFlag(Integer retryFlag) {
		this.retryFlag = retryFlag;
	}

}
