package com.meiduimall.service.member.model;

import java.io.Serializable;

import com.meiduimall.exception.SystemException;
import com.meiduimall.service.member.util.DESC;


/**
 * 会员积分明细
 * @author chencong
 *
 */
public class MSConsumePointsDetailSet implements Serializable{

	private static final long serialVersionUID = -6510155902802772482L;

	/**  积分明细编号  */
	private String mcpId;
	
	/**  会员编号  */
	private String memId;
	
	/**  订单号  */
	private String mcpOrderId;
	
	/**  订单来源  */
	private String mcpOrderSource;

	/**  操作类型,如调增，调减，订单消费，订单退款等  */
	private String mcpOperatorType;
	
	/**  收入  */
	private String mcpIncome;
	
	/**  支出  */
	private String mcpExpenditure;
	
	/**  余额  */
	private String mcpBalance;
	
	/**  创建人  */
	private String mcpCreatedBy;
	
	/**  创建时间  */
	private java.util.Date mcpCreatedDate;
	
	/**  更新人  */
	private String mcpUpdatedBy;
	
	/**  更新时间  */
	private java.util.Date mcpUpdatedDate;
	
	/**  备注  */
	private String mcpRemark;

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

	public String getMcpOrderId() {
		return mcpOrderId;
	}

	public void setMcpOrderId(String mcpOrderId) {
		this.mcpOrderId = mcpOrderId;
	}

	public String getMcpOrderSource() {
		return mcpOrderSource;
	}

	public void setMcpOrderSource(String mcpOrderSource) {
		this.mcpOrderSource = mcpOrderSource;
	}

	public String getMcpOperatorType() {
		return mcpOperatorType;
	}

	public void setMcpOperatorType(String mcpOperatorType) {
		this.mcpOperatorType = mcpOperatorType;
	}

	public String getMcpIncome() {
		return mcpIncome;
	}

	public void setMcpIncome(String mcpIncome) throws SystemException  {
		this.mcpIncome = DESC.encryption(mcpIncome, memId);
	}

	public String getMcpExpenditure() {
		return mcpExpenditure;
	}

	public void setMcpExpenditure(String mcpExpenditure) throws SystemException  {
		this.mcpExpenditure = DESC.encryption(mcpExpenditure, memId);
	}

	public String getMcpBalance() {
		return mcpBalance;
	}

	public void setMcpBalance(String mcpBalance)  throws SystemException {
		this.mcpBalance = DESC.encryption(mcpBalance, memId);
	}

	public String getMcpCreatedBy() {
		return mcpCreatedBy;
	}

	public void setMcpCreatedBy(String mcpCreatedBy) {
		this.mcpCreatedBy = mcpCreatedBy;
	}

	public java.util.Date getMcpCreatedDate() {
		return mcpCreatedDate;
	}

	public void setMcpCreatedDate(java.util.Date mcpCreatedDate) {
		this.mcpCreatedDate = mcpCreatedDate;
	}

	public String getMcpUpdatedBy() {
		return mcpUpdatedBy;
	}

	public void setMcpUpdatedBy(String mcpUpdatedBy) {
		this.mcpUpdatedBy = mcpUpdatedBy;
	}

	public java.util.Date getMcpUpdatedDate() {
		return mcpUpdatedDate;
	}

	public void setMcpUpdatedDate(java.util.Date mcpUpdatedDate) {
		this.mcpUpdatedDate = mcpUpdatedDate;
	}

	public String getMcpRemark() {
		return mcpRemark;
	}

	public void setMcpRemark(String mcpRemark) {
		this.mcpRemark = mcpRemark;
	}
}