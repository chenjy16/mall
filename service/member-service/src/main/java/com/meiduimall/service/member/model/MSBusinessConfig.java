package com.meiduimall.service.member.model;

import java.io.Serializable;


/**
 * 系统业务字典表，包含了一些全局的配置信息，例如用户积分最大消费比例
 * @author chencong
 *
 */
public class MSBusinessConfig implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/** 系统业务字典ID **/
	private String bcId;

	/** 编号 **/
	private Integer bcNo;

	/** 父节点ID **/
	private String bcParentId;

	/** 对应字典ID **/
	private String dictId;

	/** 存储Key值，区分不同子类 **/
	private String bcKey;

	/** 值1 **/
	private String bcValue1;

	/** 值2 **/
	private String bcValue2;

	/** 值3 **/
	private String bcValue3;

	/** 值4 **/
	private String bcValue4;

	/** 值5 **/
	private String bcValue5;

	/** 备注 **/
	private String bcRemark;
	
	/** 业务字典分类 **/
	private String bcCategory;
	
	
	

	public String getBcCategory() {
		return bcCategory;
	}

	public void setBcCategory(String bcCategory) {
		this.bcCategory = bcCategory;
	}

	public void setBcId(String bcId) {
		this.bcId = bcId;
	}

	public String getBcId() {
		return this.bcId;
	}

	public void setBcNo(Integer bcNo) {
		this.bcNo = bcNo;
	}

	public Integer getBcNo() {
		return this.bcNo;
	}

	public void setBcParentId(String bcParentId) {
		this.bcParentId = bcParentId;
	}

	public String getBcParentId() {
		return this.bcParentId;
	}

	public void setDictId(String dictId) {
		this.dictId = dictId;
	}

	public String getDictId() {
		return this.dictId;
	}

	public void setBcKey(String bcKey) {
		this.bcKey = bcKey;
	}

	public String getBcKey() {
		return this.bcKey;
	}

	public void setBcValue1(String bcValue1) {
		this.bcValue1 = bcValue1;
	}

	public String getBcValue1() {
		return this.bcValue1;
	}

	public void setBcValue2(String bcValue2) {
		this.bcValue2 = bcValue2;
	}

	public String getBcValue2() {
		return this.bcValue2;
	}

	public void setBcValue3(String bcValue3) {
		this.bcValue3 = bcValue3;
	}

	public String getBcValue3() {
		return this.bcValue3;
	}

	public void setBcValue4(String bcValue4) {
		this.bcValue4 = bcValue4;
	}

	public String getBcValue4() {
		return this.bcValue4;
	}

	public void setBcValue5(String bcValue5) {
		this.bcValue5 = bcValue5;
	}

	public String getBcValue5() {
		return this.bcValue5;
	}

	public void setBcRemark(String bcRemark) {
		this.bcRemark = bcRemark;
	}

	public String getBcRemark() {
		return this.bcRemark;
	}

}
