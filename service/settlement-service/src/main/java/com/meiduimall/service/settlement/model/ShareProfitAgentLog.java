package com.meiduimall.service.settlement.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 保证金分润异常日志
 * @author guidl
 *
 */
public class ShareProfitAgentLog implements Serializable {

	private static final long serialVersionUID = 4666469018686450348L;

	//唯一标识
	private int id;
	
	//代理编号
	private String agentNo;
	
	//手机号码
	private String phone;
	
	//异常原因
	private String reason;
	
	//创建时间
	private int createdDate;
	
	//更新时间
	private Timestamp updatedDate;
	
	//重试状态
	private int retryStatus;
	
	//重试时间
	private int retryTime;
	
	//重试标识:1需要重试,0:不需要重试
	private int retryFlag;
	
	//备注
	private String remark;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAgentNo() {
		return agentNo;
	}

	public void setAgentNo(String agentNo) {
		this.agentNo = agentNo;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(int createdDate) {
		this.createdDate = createdDate;
	}
	
	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public int getRetryStatus() {
		return retryStatus;
	}

	public void setRetryStatus(int retryStatus) {
		this.retryStatus = retryStatus;
	}

	public int getRetryTime() {
		return retryTime;
	}

	public void setRetryTime(int retryTime) {
		this.retryTime = retryTime;
	}

	public int getRetryFlag() {
		return retryFlag;
	}

	public void setRetryFlag(int retryFlag) {
		this.retryFlag = retryFlag;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "ShareProfitAgentLog [id=" + id + ", agentNo=" + agentNo + ", phone=" + phone + ", reason=" + reason
				+ ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + ", retryStatus=" + retryStatus
				+ ", retryTime=" + retryTime + ", retryFlag=" + retryFlag + ", remark=" + remark + "]";
	}

	

}
