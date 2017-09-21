package com.meiduimall.service.settlement.model;

import java.io.Serializable;

public class SmsReqDTO implements Serializable {

	private static final long serialVersionUID = 8761598097670063579L;
	
	//接收短信的手机号码
	private String phones;
	
	//模板id
	private String templateId;
	
	//替换短信中的参数
	private String params;
	
	private String clientID;
	
	
	public SmsReqDTO() {
		super();
	}

	public SmsReqDTO(String phones, String templateId, String params, String clientID) {
		super();
		this.phones = phones;
		this.templateId = templateId;
		this.params = params;
		this.clientID = clientID;
	}

	public String getPhones() {
		return phones;
	}

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public void setPhones(String phones) {
		this.phones = phones;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}
}
