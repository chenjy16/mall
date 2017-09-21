package com.meiduimall.exception;

import com.meiduimall.core.BaseApiCode;

public class BizException extends RuntimeException{
	
	private static final long serialVersionUID = 4172573415632244679L;
	
	private Integer code;
	
	public BizException(Integer code) {
		this.code = code;
	}
	
    public String getLocalizedMessage() {
        return BaseApiCode.getZhMsg(code);
    }


	public BizException(Integer code, String msg) {
		super(msg);
		this.code = code;
	}

	public BizException(Integer code, String msg, Throwable cause) {
		super(msg, cause);
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

}
