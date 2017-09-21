package com.meiduimall.service.member.model;

import java.io.Serializable;

import com.meiduimall.exception.SystemException;
import com.meiduimall.service.member.util.DESC;


/**
 * 会员转账记录表
 * @author chencong
 *
 */
public class MemberTransferHistory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7619574125300164936L;

	/** 会员转账记录ID **/
	private String mthId;

	/** 会员ID **/
	private String memId;

	/** 账户类别 **/
	private String dictMthCategory;

	/** 转账状态 **/
	private String dictMthStatus;

	/** 流水号 **/
	private String mthNo;

	/** 数量 **/
	private String mthQuantity;

	/** 实际到账 **/
	private String mthActualQuantity;

	/** 转账手续费规则 **/
	private String dictChargeFormula;

	/** 钱包地址 **/
	private String mthWalletUrl;

	/** 外部流水号 **/
	private String mthOtherNo;

	/** 创建人 **/
	private String mthCreatedBy;

	/** 创建时间 **/
	private java.util.Date mthCreatedDate;

	/** 更新人 **/
	private String mthUpdatedBy;

	/** 更新时间 **/
	private java.util.Date mthUpdatedDate;

	/** 备注 **/
	private String mthRemark;
	
	/** 更新人 **/
	private String mthQuantityLeft;
	
	/** 转入账户 **/
	private String mthAccount;
	
	/** 内部转账类型（转入；转出）**/
	private String internalTransferType;
	
	/** 转入会员名称 **/
	private String transInMemberNickName;
	
	public String getTransInMemberNickName() {
		return transInMemberNickName;
	}
	public void setTransInMemberNickName(String transInMemberNickName) {
		this.transInMemberNickName = transInMemberNickName;
	}

	/************add by lftan 调账管理导出辅助字段:begin************/
	private String memName;
	private String loginName;
	/************add by lftan 调账管理导出辅助字段:end************/
	
	public String getMthAccount() throws SystemException  {
		return DESC.deyption(this.mthAccount);
	}
	/** 内部转账类型（转入；转出）**/
	public String getInternalTransferType() {
		return internalTransferType;
	}
	/** 内部转账类型（转入；转出）**/
	public void setInternalTransferType(String internalTransferType) {
		this.internalTransferType = internalTransferType;
	}

	public void setMthAccount(String mthAccount) throws SystemException  {
		this.mthAccount = DESC.encryption(mthAccount);
	}

	public String getMthQuantityLeft() throws SystemException  {
		return DESC.deyption(mthQuantityLeft,this.memId);
	}

	public void setMthQuantityLeft(String mthQuantityLeft) throws SystemException  {
		this.mthQuantityLeft = DESC.encryption(mthQuantityLeft,this.memId);
	}

	public void setMthId(String mthId) {
		this.mthId = mthId;
	}

	public String getMthId() {
		return this.mthId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemId() {
		return this.memId;
	}

	public void setDictMthCategory(String dictMthCategory) {
		this.dictMthCategory = dictMthCategory;
	}

	public String getDictMthCategory() {
		return this.dictMthCategory;
	}

	public void setDictMthStatus(String dictMthStatus) {
		this.dictMthStatus = dictMthStatus;
	}

	public String getDictMthStatus() {
		return this.dictMthStatus;
	}

	public void setMthNo(String mthNo) {
		this.mthNo = mthNo;
	}

	public String getMthNo() {
		return this.mthNo;
	}

	public void setMthQuantity(String mthQuantity) throws SystemException  {
		this.mthQuantity = DESC.encryption(mthQuantity,this.memId);
	}

	public String getMthQuantity() throws SystemException  {
		return DESC.deyption(this.mthQuantity,this.memId);
	}

	public void setMthActualQuantity(String mthActualQuantity) throws SystemException  {
		this.mthActualQuantity = DESC.encryption(mthActualQuantity,this.memId);
	}

	public String getMthActualQuantity() throws SystemException  {
		return DESC.deyption(this.mthActualQuantity,this.memId);
	}

	public void setDictChargeFormula(String dictChargeFormula) throws SystemException  {
	 
		this.dictChargeFormula = DESC.encryption(dictChargeFormula,this.memId);
	}

	public String getDictChargeFormula() throws SystemException  {
		 
		return DESC.deyption(this.dictChargeFormula,this.memId);
	}

	public void setMthWalletUrl(String mthWalletUrl) {
		this.mthWalletUrl = mthWalletUrl;
	}

	public String getMthWalletUrl() {
		return this.mthWalletUrl;
	}

	public void setMthOtherNo(String mthOtherNo) {
		this.mthOtherNo = mthOtherNo;
	}

	public String getMthOtherNo() {
		return this.mthOtherNo;
	}

	public void setMthCreatedBy(String mthCreatedBy) {
		this.mthCreatedBy = mthCreatedBy;
	}

	public String getMthCreatedBy() {
		return this.mthCreatedBy;
	}

	public void setMthCreatedDate(java.util.Date mthCreatedDate) {
		this.mthCreatedDate = mthCreatedDate;
	}

	public java.util.Date getMthCreatedDate() {
		return this.mthCreatedDate;
	}

	public void setMthUpdatedBy(String mthUpdatedBy) {
		this.mthUpdatedBy = mthUpdatedBy;
	}

	public String getMthUpdatedBy() {
		return this.mthUpdatedBy;
	}

	public void setMthUpdatedDate(java.util.Date mthUpdatedDate) {
		this.mthUpdatedDate = mthUpdatedDate;
	}

	public java.util.Date getMthUpdatedDate() {
		return this.mthUpdatedDate;
	}

	public void setMthRemark(String mthRemark) {
		this.mthRemark = mthRemark;
	}

	public String getMthRemark() {
		return this.mthRemark;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

}
