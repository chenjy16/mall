package com.meiduimall.service.member.model;

import java.io.Serializable;
import java.util.Date;



/**
 * 会员日志表
 * @author chencong
 *
 */
public class MSMemLog implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 日志ID **/
	private String mlogId;

	/****/
	private String memId;

	/** 模块ID **/
	private String mlogModule;

	/** 行为ID **/
	private String mlogAction;

	/** 操作内容 **/
	private String mlogContent;

	/** 备注 **/
	private String mlogRemark;

	/** 日志 **/
	private Date mlogDate;

	/** 客户端操作IP地址 **/
	private String mlogIp;

	/** 操作人 **/
	private String mlogCreatedBy;

	/** 操作时间 **/
	private Date mlogCreatedDate;

	public void setMlogId(String mlogId) {
		this.mlogId = mlogId;
	}

	public String getMlogId() {
		return this.mlogId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemId() {
		return this.memId;
	}

	public void setMlogModule(String mlogModule) {
		this.mlogModule = mlogModule;
	}

	public String getMlogModule() {
		return this.mlogModule;
	}

	public void setMlogAction(String mlogAction) {
		this.mlogAction = mlogAction;
	}

	public String getMlogAction() {
		return this.mlogAction;
	}

	public void setMlogContent(String mlogContent) {
		this.mlogContent = mlogContent;
	}

	public String getMlogContent() {
		return this.mlogContent;
	}

	public void setMlogRemark(String mlogRemark) {
		this.mlogRemark = mlogRemark;
	}

	public String getMlogRemark() {
		return this.mlogRemark;
	}

	public void setMlogIp(String mlogIp) {
		this.mlogIp = mlogIp;
	}

	public String getMlogIp() {
		return this.mlogIp;
	}

	public void setMlogCreatedBy(String mlogCreatedBy) {
		this.mlogCreatedBy = mlogCreatedBy;
	}

	public String getMlogCreatedBy() {
		return this.mlogCreatedBy;
	}

	public void setMlogCreatedDate(java.util.Date mlogCreatedDate) {
		this.mlogCreatedDate = mlogCreatedDate;
	}

	public java.util.Date getMlogCreatedDate() {
		return this.mlogCreatedDate;
	}

	public Date getMlogDate() {
		return mlogDate;
	}

	public void setMlogDate(Date mlogDate) {
		this.mlogDate = mlogDate;
	}
}
