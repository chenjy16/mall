package com.meiduimall.service.member.model;

import java.io.Serializable;

import com.meiduimall.exception.SystemException;
import com.meiduimall.service.member.util.DESC;


/**
 * 会员消费记录
 * @author chencong
 *
 */
public class MSMemberConsumeHistory  implements Serializable{

	private static final long serialVersionUID = 1L;

	/** 会员消费记录表ID **/
	private String mchId;

	/** 会员ID **/
	private String memId;

	/** 订单号 **/
	private String orderId;
	
	/** 套餐id **/
	private String pId;

	/** 消费金额 **/
	private String mchMoney;
	
	/** 成本价 **/
	private String mchCosts;

	/** 商家赠送积分 **/
	private String mchBshopGiveIntegral;

	/** 消费金额 **/
	private String mchTotalIntegral;

	/** 累计等级比率 **/
	private String bcLevel;

	/** 当次积分返本 **/
	private String mchCurrentReturnedIntegral;

	/** 待返本积分 **/
	private String mchNextReturnIntegral;

	/** 返利积分 **/
	private String mchReturnProfitIntegral;

	/** 消费基金 **/
	private String mchFunds;

	/** 本次所得积分 **/
	private String mchCurrentGetIntegral;

	/** 消费时间 **/
	private java.util.Date mchCreatedDate;

	/** 更新时间 **/
	private java.util.Date mchUpdatedDate;

	/** 更新人 **/
	private String mchUpdatedBy;

	/** 订单来源 **/
	private String mchOrginCategory;

	/** 消费商品名称 **/
	private String mchProductName;

	/** 消费来源 **/
	private String mchOrginType;

	/** 消费来源会员ID **/
	private String mchOrginMemId;

	/** 备注 **/
	private String mchRemark;

	/**结算状态 0已结算 1未结算 **/
	private Integer mchSettingStatus;
	
	/** 发放状态 0已发放 1未发放 **/
	private Integer mchIssueStatus;
	
	/** 美兑积分 **/
	private String mchConsumePointsCount;

	/**消费类型1：表示单独使用消费劵支付2：混合支付3: 其他第三方支付*/
	private String mchPayType;
	
	/**退单状态1表示已完成2表示其他已退单*/
	private String mchStatus;
	
	public Integer getMchIssueStatus() {
		return mchIssueStatus;
	}

	public void setMchIssueStatus(Integer mchIssueStatus) {
		this.mchIssueStatus = mchIssueStatus;
	}


	public String getMchStatus() {
		return mchStatus;
	}

	public void setMchStatus(String mchStatus) {
		this.mchStatus = mchStatus;
	}

	public String getMchPayType() {
		return mchPayType;
	}

	public void setMchPayType(String mchPayType) {
		this.mchPayType = mchPayType;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getMchId() {
		return this.mchId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemId() {
		return this.memId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderId() {
		return this.orderId;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public void setMchMoney(String mchMoney)  throws SystemException {
		this.mchMoney = DESC.encryption(mchMoney,this.memId);
	}

	public String getMchMoney()  throws SystemException {
		return DESC.deyption(this.mchMoney,this.memId);
	}

	public String getMchCosts()  throws SystemException {
		return DESC.deyption(this.mchCosts,this.memId);
		
	}

	public void setMchCosts(String mchCosts)  throws SystemException {
		this.mchCosts = DESC.encryption(mchCosts,this.memId);
	}

	public void setMchBshopGiveIntegral(String mchBshopGiveIntegral) throws SystemException  {
		this.mchBshopGiveIntegral = DESC.encryption(mchBshopGiveIntegral,this.memId);
	}

	public String getMchBshopGiveIntegral()  throws SystemException {
		return DESC.deyption(this.mchBshopGiveIntegral,this.memId);
	}

	public void setMchTotalIntegral(String mchTotalIntegral)  throws SystemException {
		this.mchTotalIntegral = DESC.encryption(mchTotalIntegral,this.memId);
	}

	public String getMchTotalIntegral() throws SystemException  {
		return DESC.deyption(this.mchTotalIntegral,this.memId);
	}

	public void setBcLevel(String bcLevel) {
		this.bcLevel = bcLevel;
	}

	public String getBcLevel() {
		return this.bcLevel;
	}

	public void setMchCurrentReturnedIntegral(String mchCurrentReturnedIntegral)  throws SystemException {
		this.mchCurrentReturnedIntegral = DESC.encryption(mchCurrentReturnedIntegral,this.memId);
	}

	public String getMchCurrentReturnedIntegral() throws SystemException  {
		return DESC.deyption(this.mchCurrentReturnedIntegral,this.memId);
	}

	public void setMchNextReturnIntegral(String mchNextReturnIntegral)  throws SystemException {
		this.mchNextReturnIntegral = DESC.encryption(mchNextReturnIntegral,this.memId);
	}

	public String getMchNextReturnIntegral() throws SystemException  {
		return DESC.deyption(this.mchNextReturnIntegral,this.memId);
	}

	public void setMchReturnProfitIntegral(String mchReturnProfitIntegral)  throws SystemException {
		this.mchReturnProfitIntegral = DESC.encryption(mchReturnProfitIntegral,this.memId);
	}

	public String getMchReturnProfitIntegral() throws SystemException  {
		return DESC.deyption(this.mchReturnProfitIntegral,this.memId);
	}

	public void setMchFunds(String mchFunds) throws SystemException  {
		this.mchFunds = DESC.encryption(mchFunds,this.memId);
	}

	public String getMchFunds() throws SystemException  {
		return DESC.deyption(this.mchFunds,this.memId);
	}

	public void setMchCurrentGetIntegral(String mchCurrentGetIntegral) throws SystemException  {
		this.mchCurrentGetIntegral = DESC.encryption(mchCurrentGetIntegral,this.memId);
	}

	public String getMchCurrentGetIntegral()  throws SystemException {
		return DESC.deyption(this.mchCurrentGetIntegral,this.memId);
	}

	public void setMchCreatedDate(java.util.Date mchCreatedDate) {
		this.mchCreatedDate = mchCreatedDate;
	}

	public java.util.Date getMchCreatedDate() {
		return this.mchCreatedDate;
	}

	public void setMchUpdatedDate(java.util.Date mchUpdatedDate) {
		this.mchUpdatedDate = mchUpdatedDate;
	}

	public java.util.Date getMchUpdatedDate() {
		return this.mchUpdatedDate;
	}

	public void setMchUpdatedBy(String mchUpdatedBy) {
		this.mchUpdatedBy = mchUpdatedBy;
	}

	public String getMchUpdatedBy() {
		return this.mchUpdatedBy;
	}

	public void setMchOrginCategory(String mchOrginCategory) {
		this.mchOrginCategory = mchOrginCategory;
	}

	public String getMchOrginCategory() {
		return this.mchOrginCategory;
	}

	public void setMchProductName(String mchProductName) {
		this.mchProductName = mchProductName;
	}

	public String getMchProductName() {
		return this.mchProductName;
	}

	public void setMchOrginType(String mchOrginType) {
		this.mchOrginType = mchOrginType;
	}

	public String getMchOrginType() {
		return this.mchOrginType;
	}

	public void setMchOrginMemId(String mchOrginMemId) {
		this.mchOrginMemId = mchOrginMemId;
	}

	public String getMchOrginMemId() {
		return this.mchOrginMemId;
	}

	public void setMchRemark(String mchRemark) {
		this.mchRemark = mchRemark;
	}

	public String getMchRemark() {
		return this.mchRemark;
	}

	public void setMchSettingStatus(Integer mchSettingStatus) {
		this.mchSettingStatus = mchSettingStatus;
	}

	public Integer getMchSettingStatus() {
		return this.mchSettingStatus;
	}

	public String getMchConsumePointsCount() {
		if("".equals(mchConsumePointsCount) || null == mchConsumePointsCount) {
			return "0";
		} else {
			return mchConsumePointsCount;
		}
	}

	public void setMchConsumePointsCount(String mchConsumePointsCount) {
		this.mchConsumePointsCount = mchConsumePointsCount;
	}
	
}
