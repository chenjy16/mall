package com.meiduimall.service.settlement.model;

import java.io.Serializable;

public class CreateBillLog implements Serializable{
	
	private static final long serialVersionUID = 8024205236055142301L;

	private Integer id;

	private String reason;
	private Integer createdDate;
	private String remark;

	
	public CreateBillLog() {
		super();
	}
	
	
	public CreateBillLog(String reason, Integer createdDate, String remark) {
		super();
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

	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
