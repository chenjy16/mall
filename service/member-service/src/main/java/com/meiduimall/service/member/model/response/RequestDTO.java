package com.meiduimall.service.member.model.response;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meiduimall.service.member.constant.ApiStatusConst;


public class RequestDTO implements Serializable{

	private static final long serialVersionUID = 6428066573289350974L;

	private HttpServletRequest request;
	
	private HttpServletResponse response;
	
	private String status_code = ApiStatusConst.SUCCESS.toString();
	
	private String result_msg ="0";
	
	private String ip;

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public String getStatus_code() {
		return status_code;
	}

	public void setStatus_code(String status_code) {
		this.status_code = status_code;
	}

	public String getResult_msg() {
		return result_msg;
	}

	public void setResult_msg(String result_msg) {
		this.result_msg = result_msg;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
