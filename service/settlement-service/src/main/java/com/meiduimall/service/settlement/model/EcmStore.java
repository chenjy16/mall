package com.meiduimall.service.settlement.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

public class EcmStore implements Serializable {

	private static final long serialVersionUID = 7336751736114301150L;
	
	//手机号
	@NotNull(message="手机号不能为空")
	private String username;
	
	//商家编号
	@NotNull(message="商家编号不能为空")
	private String storeNo;
	
	//创建时间
	private Timestamp createdDate;
	
	//更新时间
	private Timestamp updatedDate;

	public String getStoreNo() {
		return storeNo;
	}

	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}
	
}
