package com.meiduimall.service.member.model;

import java.util.Date;


/**
 * 会员账户表
 * @author chencong
 *
 */
public class MSAccount {

	private static final long serialVersionUID = 8080490869514026587L;
	
	/** 账户标识 */
	private String id;

	/** 会员ID */
	private String memId;

	/** 账户类型（AT01：积分账户；AT02：现金账户；） */
	private String type;

	/** 余额（发放的总余额） */
	private String balance;

	/** 冻结余额 */
	private String freezeBalance;

	/** 创建时间 */
	private Date createDate;

	/** 修改时间 */
	private Date updateDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getFreezeBalance() {
		return freezeBalance;
	}

	public void setFreezeBalance(String freezeBalance) {
		this.freezeBalance = freezeBalance;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
