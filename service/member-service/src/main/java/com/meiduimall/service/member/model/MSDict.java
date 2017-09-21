package com.meiduimall.service.member.model;

import java.io.Serializable;


/**
 * 系统字典表
 * @author chencong
 *
 */
public class MSDict implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 字典ID **/
	private String dictId;

	/** 编号 **/
	private Long dictNo;

	/** 显示名称 **/
	private String dictName;

	/** 父节点ID **/
	private String dicParentId;

	/** 存储Key值，区分不同子类 **/
	private String dictKey;
	
	/** 字典分类 **/
	private String dictCategory;
	
	
	

	public String getDictCategory() {
		return dictCategory;
	}

	public void setDictCategory(String dictCategory) {
		this.dictCategory = dictCategory;
	}

	/** 值1 **/
	private String dictValue;

	/** 值2 **/
	private String dictValue2;

	/** 值3 **/
	private String dictValue3;

	/** 值4 **/
	private String dictValue4;

	/** 值5 **/
	private String dictValue5;

	/** 备注 **/
	private String dictRemark;

	/** 创建人 **/
	private String dictCreatedBy;

	/** 创建时间 **/
	private java.util.Date dictCreatedDate;

	/** 更新人 **/
	private String dictUpdatedBy;

	/** 更新时间 **/
	private java.util.Date dictUpdatedDate;

	public void setDictId(String dictId) {
		this.dictId = dictId;
	}

	public String getDictId() {
		return this.dictId;
	}

	public void setDictNo(Long dictNo) {
		this.dictNo = dictNo;
	}

	public Long getDictNo() {
		return this.dictNo;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public String getDictName() {
		return this.dictName;
	}

	public void setDicParentId(String dicParentId) {
		this.dicParentId = dicParentId;
	}

	public String getDicParentId() {
		return this.dicParentId;
	}

	public void setDictKey(String dictKey) {
		this.dictKey = dictKey;
	}

	public String getDictKey() {
		return this.dictKey;
	}

	public void setDictValue(String dictValue) {
		this.dictValue = dictValue;
	}

	public String getDictValue() {
		return this.dictValue;
	}

	public void setDictValue2(String dictValue2) {
		this.dictValue2 = dictValue2;
	}

	public String getDictValue2() {
		return this.dictValue2;
	}

	public void setDictValue3(String dictValue3) {
		this.dictValue3 = dictValue3;
	}

	public String getDictValue3() {
		return this.dictValue3;
	}

	public void setDictValue4(String dictValue4) {
		this.dictValue4 = dictValue4;
	}

	public String getDictValue4() {
		return this.dictValue4;
	}

	public void setDictValue5(String dictValue5) {
		this.dictValue5 = dictValue5;
	}

	public String getDictValue5() {
		return this.dictValue5;
	}

	public void setDictRemark(String dictRemark) {
		this.dictRemark = dictRemark;
	}

	public String getDictRemark() {
		return this.dictRemark;
	}

	public void setDictCreatedBy(String dictCreatedBy) {
		this.dictCreatedBy = dictCreatedBy;
	}

	public String getDictCreatedBy() {
		return this.dictCreatedBy;
	}

	public void setDictCreatedDate(java.util.Date dictCreatedDate) {
		this.dictCreatedDate = dictCreatedDate;
	}

	public java.util.Date getDictCreatedDate() {
		return this.dictCreatedDate;
	}

	public void setDictUpdatedBy(String dictUpdatedBy) {
		this.dictUpdatedBy = dictUpdatedBy;
	}

	public String getDictUpdatedBy() {
		return this.dictUpdatedBy;
	}

	public void setDictUpdatedDate(java.util.Date dictUpdatedDate) {
		this.dictUpdatedDate = dictUpdatedDate;
	}

	public java.util.Date getDictUpdatedDate() {
		return this.dictUpdatedDate;
	}
}
