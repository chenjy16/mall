package com.meiduimall.service.member.model.response;

import java.io.Serializable;

/**
 * 类名:  ConsumePointsDetailDTO<br>
 * 描述:  会员积分明细DTO <br>
 * 创建时间: 2016-12-2
 */
public class InterfaceConsumePointsDetailsDTO implements Serializable{

	private static final long serialVersionUID = -1664408792644056966L;

	/** 主键 */
	private String mcpId;
	
	/** 会员编号 */
	private String memId;
	
	/** 订单编号 */
	private String orderId;
	
	/** 订单类型 */
	private String orderType;
	
	/** 收入积分 */
	private String incomePoints;
	
	/** 支出积分 */
	private String expenditurePoints;
	
	/** 余额 */
	private String balancePoints;
	
	/** 创建人 */
	private String createdBy;
	
	/** 创建时间  */
	private String createdDate;
	
	/** 修改人 */
	private String updatedBy;
	
	/** 修改时间  */
	private String updatedDate;
	
	/** 备注 */
	private String remark;

	public String getMcpId() {
		return mcpId;
	}

	public void setMcpId(String mcpId) {
		this.mcpId = mcpId;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getIncomePoints() {
		return incomePoints;
	}

	public void setIncomePoints(String incomePoints) {
		this.incomePoints = incomePoints;
	}

	public String getExpenditurePoints() {
		return expenditurePoints;
	}

	public void setExpenditurePoints(String expenditurePoints) {
		this.expenditurePoints = expenditurePoints;
	}

	public String getBalancePoints() {
		return balancePoints;
	}

	public void setBalancePoints(String balancePoints) {
		this.balancePoints = balancePoints;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
