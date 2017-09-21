package com.meiduimall.service.settlement.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

/**
 * 订单信息表
 * @author 许彦 雄
 *
 */
public class EcmMzfOrderStatus implements Serializable {

	private static final long serialVersionUID = -819772399023048586L;
	
	private Integer id;
	//订单号
	@NotBlank(message="订单号不能为空")
	private String orderSn;
	private Integer status;
	private Integer shareStatus=0;
	private Integer billStatus=0;
	private Integer scoreStatus=0;
	private Integer cashStatus=0;
	
	@Range(min=0,max=Integer.MAX_VALUE,message="审核状态应该为数值")
	@NotNull(message="审核状态不能为空")
	private Integer verifyStatus;
	
	@NotBlank(message="审核人姓名不能为空")
	private String verifyName;
	
	@Range(min=0,max=Integer.MAX_VALUE,message="审核时间应该为10位数时间戳")
	@NotNull(message="审核时间不能为空")
	private Integer verifyTime;
	
	private Integer addTime;
	private Integer payTime;
	private Integer createdDate;
	private String statusDesc="已支付";
	
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getShareStatus() {
		return shareStatus;
	}
	public void setShareStatus(Integer shareStatus) {
		this.shareStatus = shareStatus;
	}
	public Integer getBillStatus() {
		return billStatus;
	}
	public void setBillStatus(Integer billStatus) {
		this.billStatus = billStatus;
	}
	public Integer getVerifyStatus() {
		return verifyStatus;
	}
	public void setVerifyStatus(Integer verifyStatus) {
		this.verifyStatus = verifyStatus;
	}
	public String getVerifyName() {
		return verifyName;
	}
	public void setVerifyName(String verifyName) {
		this.verifyName = verifyName;
	}
	public Integer getVerifyTime() {
		return verifyTime;
	}
	public void setVerifyTime(Integer verifyTime) {
		this.verifyTime = verifyTime;
	}
	public Integer getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Integer createdDate) {
		this.createdDate = createdDate;
	}
	
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	
	public Integer getAddTime() {
		return addTime;
	}
	public void setAddTime(Integer addTime) {
		this.addTime = addTime;
	}
	public Integer getPayTime() {
		return payTime;
	}
	public void setPayTime(Integer payTime) {
		this.payTime = payTime;
	}
	public Integer getScoreStatus() {
		return scoreStatus;
	}
	public void setScoreStatus(Integer scoreStatus) {
		this.scoreStatus = scoreStatus;
	}
	public Integer getCashStatus() {
		return cashStatus;
	}
	public void setCashStatus(Integer cashStatus) {
		this.cashStatus = cashStatus;
	}

}