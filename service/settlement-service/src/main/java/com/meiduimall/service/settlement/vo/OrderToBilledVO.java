package com.meiduimall.service.settlement.vo;

import java.io.Serializable;

public class OrderToBilledVO  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4352989332198836082L;
	
	private String code;
	
	private String orderSn;
	
	private Integer type;
	
	private String billId;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getOrderSn() {
		return orderSn;
	}

	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}
	


}
