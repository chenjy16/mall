package com.meiduimall.service.settlement.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 账单
 * 
 * @author chencong
 * 
 */
public class EcmMzfBillWater implements Serializable {

	private static final long serialVersionUID = 3223328331539303488L;

	// id
	private int id;

	// 账单编号
	private String billId;
	
	// 角色编号
	private String code;
	
	//金额
	private double amount;
	
	//类型
	private int type;
	
	// 账单时间，按天
	private Date billTime;
	
	// 账单生成时间
    private Date billAddTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Date getBillTime() {
		return billTime;
	}

	public void setBillTime(Date billTime) {
		this.billTime = billTime;
	}

	public Date getBillAddTime() {
		return billAddTime;
	}

	public void setBillAddTime(Date billAddTime) {
		this.billAddTime = billAddTime;
	}
    
    

}
