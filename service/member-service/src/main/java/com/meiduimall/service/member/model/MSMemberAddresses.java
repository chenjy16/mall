package com.meiduimall.service.member.model;

import java.io.Serializable;

import com.meiduimall.exception.SystemException;
import com.meiduimall.service.member.util.DESC;


/**
 * 会员地址详细记录表
 * @author chencong
 *
 */
public class MSMemberAddresses implements Serializable{

	private static final long serialVersionUID = 1L;

	/** 会员地址详细表ID **/
	private String memaId;

	/** 会员ID **/
	private String memId;

	/** 详细地址 **/
	private String memaDetails;

	/** 邮政编码 **/
	private String memaPost;

	/** 地址有效状态 **/
	private String memaStatus;

	/** 地址联系人手机.主要在用户更改了住址后使用 **/
	private String memaContactPhone;

	/** 地址联系人邮箱.主要在用户更改了住址后使用 **/
	private String memaContactEmail;

	/** 地址联系人名称.主要在用户更改了住址后使用 **/
	private String memaContactName;

	/** 会员所属国家ID **/
	private String dictIdCountry;

	/** 会员所属省份 **/
	private Integer dictIdProvince;

	/** 会员所属城市 **/
	private Integer dictIdCity;

	/** 会员所属地区 **/
	private Integer dictIdArea;

	/** 地址信息备注 **/
	private String memaRemark;

	public void setMemaId(String memaId) {
		this.memaId = memaId;
	}

	public String getMemaId() {
		return this.memaId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemId() {
		return this.memId;
	}

	public void setMemaDetails(String memaDetails) throws SystemException  {
		this.memaDetails = DESC.encryption(memaDetails, this.memId);
	}

	public String getMemaDetails() throws SystemException  {
		return DESC.deyption(this.memaDetails,this.memId);
	}

	public void setMemaPost(String memaPost)  throws SystemException {
		this.memaPost = DESC.encryption(memaPost,this.memId);
	}

	public String getMemaPost()  throws SystemException {
		return DESC.deyption(this.memaPost, this.memId);
	}

	public void setMemaStatus(String memaStatus) {
		this.memaStatus = memaStatus;
	}

	public String getMemaStatus() {
		return this.memaStatus;
	}

	public void setMemaContactPhone(String memaContactPhone)  throws SystemException {
		this.memaContactPhone = DESC.encryption(memaContactPhone,this.memId);
	}

	public String getMemaContactPhone()  throws SystemException {
		return DESC.deyption(this.memaContactPhone, this.memId);
	}

	public void setMemaContactEmail(String memaContactEmail)  throws SystemException {
		this.memaContactEmail = DESC.encryption(memaContactEmail,this.memId);
	}

	public String getMemaContactEmail()  throws SystemException {
		return  DESC.deyption(this.memaContactEmail, this.memId);
	}

	public void setMemaContactName(String memaContactName) throws SystemException  {
		this.memaContactName = DESC.encryption(memaContactName,this.memId);
	}

	public String getMemaContactName()  throws SystemException {
		return DESC.deyption(this.memaContactName, this.memId);
	}

	public void setMemaRemark(String memaRemark) {
		this.memaRemark = memaRemark;
	}

	public String getMemaRemark() {
		return this.memaRemark;
	}
	
	public String getDictIdCountry() {
		return dictIdCountry;
	}

	public void setDictIdCountry(String dictIdCountry) {
		this.dictIdCountry = dictIdCountry;
	}

	public Integer getDictIdProvince() {
		return dictIdProvince;
	}

	public void setDictIdProvince(Integer dictIdProvince) {
		this.dictIdProvince = dictIdProvince;
	}

	public Integer getDictIdCity() {
		return dictIdCity;
	}

	public void setDictIdCity(Integer dictIdCity) {
		this.dictIdCity = dictIdCity;
	}

	public Integer getDictIdArea() {
		return dictIdArea;
	}

	public void setDictIdArea(Integer dictIdArea) {
		this.dictIdArea = dictIdArea;
	}
}
