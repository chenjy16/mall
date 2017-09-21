package com.meiduimall.service.settlement.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 流水表
 * @author guidl
 *
 */
public class EcmMzfWater implements Serializable {

	private static final long serialVersionUID = -4726687825584399310L;
	
	//id
	private int id;
	
	//流水编号
	private String waterId;
	
	//商家或代理的编号
	private String code;
	
	//流水金额，可为负数
	private BigDecimal money;
	
	//备注
	private String remark;
	
	//操作时间
	private Timestamp opTime;
	
	//流水类型，1提现 2账单 3代理费
	private String waterType;
	
	//类型对应的编号
	private String extId;
	
	//推荐单号
	private String recNo;
	
	//账号可提现金额
	private BigDecimal balance;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWaterId() {
		return waterId;
	}

	public void setWaterId(String waterId) {
		this.waterId = waterId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public Timestamp getOpTime() {
		return opTime;
	}

	public void setOpTime(Timestamp opTime) {
		this.opTime = opTime;
	}

	public String getWaterType() {
		return waterType;
	}

	public void setWaterType(String waterType) {
		this.waterType = waterType;
	}

	public String getExtId() {
		return extId;
	}

	public void setExtId(String extId) {
		this.extId = extId;
	}
	
	public String getRecNo() {
		return recNo;
	}

	public void setRecNo(String recNo) {
		this.recNo = recNo;
	}
	
	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	@Override
	public String toString() {
		return "EcmMzfWater [id=" + id + ", waterId=" + waterId + ", code=" + code + ", money=" + money + ", remark="
				+ remark + ", opTime=" + opTime + ", waterType=" + waterType + ", extId=" + extId + ", recNo=" + recNo
				+ ", balance=" + balance + "]";
	}
	

}
