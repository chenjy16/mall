package com.meiduimall.password.exception;

public class Md5Exception extends Exception {
	
	private static final long serialVersionUID = -8572424828459981362L;
	private  Integer code;


	public Md5Exception(Integer code, String e) {
		super(e);
		this.code = code;
	}


	public Md5Exception(Integer code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

}
