package com.meiduimall.payment.api.model.api;

import java.io.Serializable;
import java.util.Date;

public class PaymentResultModel implements Serializable{

	
	private static final long serialVersionUID = -8122319796765407364L;
	
	private String orderNo;
	private String payTime;
	private String paymentNo;
	private String payStatus;
	private String notifyTime;
	private String notifyData;
	private String notifyStatus;
	private Date createTime;
	
	
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getPayTime() {
		return payTime;
	}
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	public String getPaymentNo() {
		return paymentNo;
	}
	public void setPaymentNo(String paymentNo) {
		this.paymentNo = paymentNo;
	}
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	public String getNotifyTime() {
		return notifyTime;
	}
	public void setNotifyTime(String notifyTime) {
		this.notifyTime = notifyTime;
	}
	public String getNotifyData() {
		return notifyData;
	}
	public void setNotifyData(String notifyData) {
		this.notifyData = notifyData;
	}
	public String getNotifyStatus() {
		return notifyStatus;
	}
	public void setNotifyStatus(String notifyStatus) {
		this.notifyStatus = notifyStatus;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	
	

}
