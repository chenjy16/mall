package com.meiduimall.payment.api.model.api;

import java.io.Serializable;
import java.util.Date;

public class PaymentNotifyModel implements Serializable {

	private static final long serialVersionUID = 5174919174311827642L;

	private Long id;
	private String orderId;
	private Date notifyTime;
	private String notifyData;
	private Integer notifyStatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Date getNotifyTime() {
		return notifyTime;
	}

	public void setNotifyTime(Date notifyTime) {
		this.notifyTime = notifyTime;
	}

	public String getNotifyData() {
		return notifyData;
	}

	public void setNotifyData(String notifyData) {
		this.notifyData = notifyData;
	}

	public Integer getNotifyStatus() {
		return notifyStatus;
	}

	public void setNotifyStatus(Integer notifyStatus) {
		this.notifyStatus = notifyStatus;
	}
}
