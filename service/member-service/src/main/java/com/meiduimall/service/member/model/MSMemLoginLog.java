package com.meiduimall.service.member.model;

import java.io.Serializable;


/**
 * 会员登录日志表
 * @author chencong
 *
 */
public class MSMemLoginLog implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 日志ID **/
	private String mllogId;

	/**会员ID*/
	private String memId;

	/** 模块ID **/
	private String mllogModule;

	/** 行为ID **/
	private String mllogAction;

	/** 操作内容 **/
	private String mllogContent;

	/** 客户端操作IP地址 **/
	private String mllogIp;

	/** 操作人 **/
	private String mllogCreatedBy;

	/** 操作时间 **/
	private java.util.Date mllogCreatedDate;

	/** 登录状态 **/
	private String mllogStatus;
	
	/** 备注 **/
	private String mllogRemark;

	public void setMllogId(String mllogId) {
		this.mllogId = mllogId;
	}

	public String getMllogId() {
		return this.mllogId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemId() {
		return this.memId;
	}

	public void setMllogModule(String mllogModule) {
		this.mllogModule = mllogModule;
	}

	public String getMllogModule() {
		return this.mllogModule;
	}

	public void setMllogAction(String mllogAction) {
		this.mllogAction = mllogAction;
	}

	public String getMllogAction() {
		return this.mllogAction;
	}

	public void setMllogContent(String mllogContent) {
		this.mllogContent = mllogContent;
	}

	public String getMllogContent() {
		return this.mllogContent;
	}

	public void setMllogIp(String mllogIp) {
		this.mllogIp = mllogIp;
	}

	public String getMllogIp() {
		return this.mllogIp;
	}

	public void setMllogCreatedBy(String mllogCreatedBy) {
		this.mllogCreatedBy = mllogCreatedBy;
	}

	public String getMllogCreatedBy() {
		return this.mllogCreatedBy;
	}

	public void setMllogCreatedDate(java.util.Date mllogCreatedDate) {
		this.mllogCreatedDate = mllogCreatedDate;
	}

	public java.util.Date getMllogCreatedDate() {
		return this.mllogCreatedDate;
	}

	public void setMllogStatus(String mllogStatus) {
		this.mllogStatus = mllogStatus;
	}

	public String getMllogStatus() {
		return this.mllogStatus;
	}

	public void setMllogRemark(String mllogRemark) {
		this.mllogRemark = mllogRemark;
	}

	public String getMllogRemark() {
		return this.mllogRemark;
	}
}
