package com.meiduimall.service.settlement.vo;

import java.io.Serializable;
import java.util.Date;

public class EcmMzfBillWaterVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String orderSn;
	
	// 账单时间，按天
	private Date billDate;
	
	// 账单生成时间
    private Date billAddDate;

	public String getOrderSn() {
		return orderSn;
	}

	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public Date getBillAddDate() {
		return billAddDate;
	}

	public void setBillAddDate(Date billAddDate) {
		this.billAddDate = billAddDate;
	}

    


}
