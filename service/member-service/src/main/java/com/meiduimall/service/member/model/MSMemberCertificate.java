package com.meiduimall.service.member.model;

import java.io.Serializable;

import com.meiduimall.exception.SystemException;
import com.meiduimall.service.member.util.DESC;

/**
 * 会员证件表
 * @author chencong
 *
 */
public class MSMemberCertificate implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/** 会员证件表ID **/
	private String mcerId;

	/** 会员ID **/
	private String memId;

	/** 会员证件类别 **/
	private String dictMcerId;

	/** 证件号码 **/
	private String mcerNo;

	/** 证件有效期 **/
	private java.util.Date mcerVaildDate;

	/** 证件正面图 **/
	private String mcerPicTop;

	/** 证件反面图 **/
	private String mcerPicEnd;

	/** 手持证件照 **/
	private String mcerPicSelfTake;

	/** 照片描述 **/
	private String mcerPicDescription;

	/** 登记时间 **/
	private java.util.Date mcerCreatedDate;

	/** 登记人 **/
	private String mcerCreatedBy;

	/** 更新时间 **/
	private java.util.Date mcerUpdatedDate;

	/** 更新人 **/
	private String mcerUpdatedBy;

	/** 备注 **/
	private String mcerRemark;
	
	/** 真实姓名  **/
	private String mcerName;

	/** 预留字段1 **/
	private String mcerKeep1;

	/** 预留字段2 **/
	private String mcerKeep2;
	/** 认证状态 **/
	private String mcStatus;
	
	/**0412添加国籍字段**/
	private String mcerNationality;
	
	/** 信用代码**/
	private  String  mcerCreditcode ;
	
	/**
	 * 营业执照图片
	 */
	private String mcerPicBusinessLicense ;
	
	public String getMcerNationality() {
		return mcerNationality;
	}

	public void setMcerNationality(String mcerNationality) {
		this.mcerNationality = mcerNationality;
	}

	public String getMcerName()  throws SystemException {
		return DESC.deyption(this.mcerName);
	}

	public void setMcerName(String mcerName) throws SystemException  {
		this.mcerName = DESC.encryption(mcerName);
	}

	public String getMcStatus() {
		return this.mcStatus;
	}

	public void setMcStatus(String mcStatus) {
		this.mcStatus = mcStatus;
	}

	public void setMcerId(String mcerId) {
		this.mcerId = mcerId;
	}

	public String getMcerId() {
		return this.mcerId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemId() {
		return this.memId;
	}

	public void setDictMcerId(String dictMcerId) {
		this.dictMcerId = dictMcerId;
	}

	public String getDictMcerId() {
		return this.dictMcerId;
	}

	public void setMcerNo(String mcerNo) throws SystemException  {
		this.mcerNo = DESC.encryption(mcerNo,this.memId);
	}

	public String getMcerNo()  throws SystemException {
		return DESC.deyption(this.mcerNo, this.memId);
	}

	public void setMcerVaildDate(java.util.Date mcerVaildDate) {
		this.mcerVaildDate = mcerVaildDate;
	}

	public java.util.Date getMcerVaildDate() {
		return this.mcerVaildDate;
	}

	public void setMcerPicTop(String mcerPicTop) throws SystemException  {
		this.mcerPicTop = DESC.encryption(mcerPicTop,this.memId);
	}

	public String getMcerPicTop()  throws SystemException {
		return DESC.deyption(this.mcerPicTop,this.memId);
	}

	public void setMcerPicEnd(String mcerPicEnd) throws SystemException  {
		this.mcerPicEnd = DESC.encryption(mcerPicEnd,this.memId);
	}

	public String getMcerPicEnd()  throws SystemException {
		return DESC.deyption(this.mcerPicEnd,this.memId);
	}

	public void setMcerPicSelfTake(String mcerPicSelfTake)  throws SystemException {
		this.mcerPicSelfTake = DESC.encryption(mcerPicSelfTake,this.memId);
	}

	public String getMcerPicSelfTake() throws SystemException  {
		return DESC.deyption(this.mcerPicSelfTake,this.memId);
	}

	public void setMcerPicDescription(String mcerPicDescription) {
		this.mcerPicDescription = mcerPicDescription;
	}

	public String getMcerPicDescription() {
		return this.mcerPicDescription;
	}

	public void setMcerCreatedDate(java.util.Date mcerCreatedDate) {
		this.mcerCreatedDate = mcerCreatedDate;
	}

	public java.util.Date getMcerCreatedDate() {
		return this.mcerCreatedDate;
	}

	public void setMcerCreatedBy(String mcerCreatedBy) {
		this.mcerCreatedBy = mcerCreatedBy;
	}

	public String getMcerCreatedBy() {
		return this.mcerCreatedBy;
	}

	public void setMcerUpdatedDate(java.util.Date mcerUpdatedDate) {
		this.mcerUpdatedDate = mcerUpdatedDate;
	}

	public java.util.Date getMcerUpdatedDate() {
		return this.mcerUpdatedDate;
	}

	public String getMcerCreditcode() {
		return mcerCreditcode;
	}

	public String getMcerPicBusinessLicense() {
		return mcerPicBusinessLicense;
	}

	public void setMcerCreditcode(String mcerCreditcode) {
		this.mcerCreditcode = mcerCreditcode;
	}

	public void setMcerPicBusinessLicense(String mcerPicBusinessLicense) {
		this.mcerPicBusinessLicense = mcerPicBusinessLicense;
	}

	public void setMcerUpdatedBy(String mcerUpdatedBy) {
		this.mcerUpdatedBy = mcerUpdatedBy;
	}

	public String getMcerUpdatedBy() {
		return this.mcerUpdatedBy;
	}

	public void setMcerRemark(String mcerRemark) {
		this.mcerRemark = mcerRemark;
	}

	public String getMcerRemark() {
		return this.mcerRemark;
	}

	public void setMcerKeep1(String mcerKeep1) {
		this.mcerKeep1 = mcerKeep1;
	}

	public String getMcerKeep1() {
		return this.mcerKeep1;
	}

	public void setMcerKeep2(String mcerKeep2) {
		this.mcerKeep2 = mcerKeep2;
	}

	public String getMcerKeep2() {
		return this.mcerKeep2;
	}

}