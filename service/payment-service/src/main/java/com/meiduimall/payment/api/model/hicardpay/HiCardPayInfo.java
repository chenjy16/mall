package com.meiduimall.payment.api.model.hicardpay;


import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class HiCardPayInfo implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 4373566725358944658L;

	private String appid;
	
	private String 	partnerid;
	
	private String prepayid;
	
	@SerializedName("package")
	private String pkg;
	
	
	private String noncestr;
	
	private String timestamp;
	
	private String sign;
	
	
	



	public String getPkg() {
		return pkg;
	}

	public void setPkg(String pkg) {
		this.pkg = pkg;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	

	public String getPrepayid() {
		return prepayid;
	}

	public void setPrepayid(String prepayid) {
		this.prepayid = prepayid;
	}

	public String getPartnerid() {
		return partnerid;
	}

	public void setPartnerid(String partnerid) {
		this.partnerid = partnerid;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getNoncestr() {
		return noncestr;
	}

	public void setNoncestr(String noncestr) {
		this.noncestr = noncestr;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	
	

}
