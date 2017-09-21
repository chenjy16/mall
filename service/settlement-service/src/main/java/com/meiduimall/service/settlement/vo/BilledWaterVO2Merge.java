package com.meiduimall.service.settlement.vo;

import java.io.Serializable;

public class BilledWaterVO2Merge implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5907535967793585936L;
	private Integer idDel;
	private Integer idKeep;
	private String code;
	
	public Integer getIdDel() {
		return idDel;
	}
	public void setIdDel(Integer idDel) {
		this.idDel = idDel;
	}
	public Integer getIdKeep() {
		return idKeep;
	}
	public void setIdKeep(Integer idKeep) {
		this.idKeep = idKeep;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	

}
