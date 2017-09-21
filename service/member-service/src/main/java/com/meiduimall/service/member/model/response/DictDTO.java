package com.meiduimall.service.member.model.response;

import java.io.Serializable;

public class DictDTO implements Serializable {

	
private static final long serialVersionUID = 1L;
	
	private String dictId;
	
	private String parentId;
	
	private String dictKey;
	
	private String dictValue;
	private int dictNo;
	private String dictCategroy;
	
	

	public int getDictNo() {
		return dictNo;
	}

	public void setDictNo(int dictNo) {
		this.dictNo = dictNo;
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

	public String getDictKey() {
		return dictKey;
	}

	public void setDictKey(String dictKey) {
		this.dictKey = dictKey;
	}

	public String getDictValue() {
		return dictValue;
	}

	public void setDictValue(String dictValue) {
		this.dictValue = dictValue;
	}

	public String getDictCategroy() {
		return dictCategroy;
	}

	public void setDictCategroy(String dictCategroy) {
		this.dictCategroy = dictCategroy;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
