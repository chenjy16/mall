package com.meiduimall.service.member.model.response;

import java.io.Serializable;

public class BusinessConfigDTO implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String bcId;
	private String dictId;
	private String parentId;
	private String bcKey;
	private String bcValue;
	public String getBcId() {
		return bcId;
	}
	public void setBcId(String bcId) {
		this.bcId = bcId;
	}
	public String getDictId() {
		return dictId;
	}
	public void setDictId(String dictId) {
		this.dictId = dictId;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getBcKey() {
		return bcKey;
	}
	public void setBcKey(String bcKey) {
		this.bcKey = bcKey;
	}
	public String getBcValue() {
		return bcValue;
	}
	public void setBcValue(String bcValue) {
		this.bcValue = bcValue;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
