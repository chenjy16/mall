package com.meiduimall.service.settlement.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 提现流水
 * @author guidl
 *
 */
public class EcmMzfDrawWater implements Serializable {

	private static final long serialVersionUID = 2171074637628217239L;
	
	private int id;
	
	//提现编号
	private String drawCode;
	
	//代理和商家的编号
	private String code;
	
	//提现状态
	private String status;
	
	//流水金额，可为负数
	private BigDecimal money;
	
	//备注
	private String remark;
	
	//操作时间
	private Timestamp drawTime;
	
	
	
	public EcmMzfDrawWater() {
		super();
	}

	public EcmMzfDrawWater(String drawCode, String status) {
		super();
		this.drawCode = drawCode;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDrawCode() {
		return drawCode;
	}

	public void setDrawCode(String drawCode) {
		this.drawCode = drawCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Timestamp getDrawTime() {
		return drawTime;
	}

	public void setDrawTime(Timestamp drawTime) {
		this.drawTime = drawTime;
	}

}
