package com.meiduimall.service.member.model;

import java.io.Serializable;

import com.meiduimall.exception.SystemException;
import com.meiduimall.service.member.util.DESC;

/**
 * 
 * 会员地址详细表
 * 
 **/
public class MemberAddressesGet implements Serializable {

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
	private Integer cityIdProvince;

	/** 会员所属城市 **/
	private Integer cityIdCity;

	/** 会员所属地区 **/
	private Integer cityIdArea;

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

	public void setMemaDetails(String memaDetails) {
		this.memaDetails = memaDetails;
	}

	public String getMemaDetails() throws SystemException {
		return DESC.deyption(this.memaDetails,this.memId);
	}

	public void setMemaPost(String memaPost) {
		this.memaPost = memaPost;
	}

	public String getMemaPost() throws SystemException {
		return DESC.deyption(this.memaPost, this.memId);
	}

	public void setMemaStatus(String memaStatus) {
		this.memaStatus = memaStatus;
	}

	public String getMemaStatus() {
		return this.memaStatus;
	}

	public void setMemaContactPhone(String memaContactPhone) {
		this.memaContactPhone = memaContactPhone;
	}

	public String getMemaContactPhone() throws SystemException {
		return DESC.deyption(this.memaContactPhone, this.memId);
	}

	public void setMemaContactEmail(String memaContactEmail) {
		this.memaContactEmail = memaContactEmail;
	}

	public String getMemaContactEmail() throws SystemException {
		return  DESC.deyption(this.memaContactEmail, this.memId);
	}

	public void setMemaContactName(String memaContactName) {
		this.memaContactName = memaContactName;
	}

	public String getMemaContactName() throws SystemException {
		return DESC.deyption(this.memaContactName, this.memId);
	}

	public void setDictIdCountry(String dictIdCountry) {
		this.dictIdCountry = dictIdCountry;
	}

	public String getDictIdCountry() {
		return this.dictIdCountry;
	}

	public void setCityIdProvince(Integer cityIdProvince) {
		this.cityIdProvince = cityIdProvince;
	}

	public Integer getCityIdProvince() {
		return this.cityIdProvince;
	}

	public void setCityIdCity(Integer cityIdCity) {
		this.cityIdCity = cityIdCity;
	}

	public Integer getCityIdCity() {
		return this.cityIdCity;
	}

	public void setCityIdArea(Integer cityIdArea) {
		this.cityIdArea = cityIdArea;
	}

	public Integer getCityIdArea() {
		return this.cityIdArea;
	}

	public void setMemaRemark(String memaRemark) {
		this.memaRemark = memaRemark;
	}

	public String getMemaRemark() {
		return this.memaRemark;
	}

}
