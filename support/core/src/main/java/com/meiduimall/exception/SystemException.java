/*
 *  @项目名称: ${project_name}
 *
 *  @文件名称: ${file_name}
 *  @Date: ${date}
 *  @Copyright: ${year} www.meiduimall.com Inc. All rights reserved.
 *
 *  注意：本内容仅限于美兑壹购物公司内部传阅，禁止外泄以及用于其他的商业目的
 */

package com.meiduimall.exception;

import com.meiduimall.core.BaseApiCode;

public class SystemException extends Exception {

	private static final long serialVersionUID = 522159568098470670L;
	private Integer code;

	public SystemException(Integer code) {
		this.code = code;
	}
	
    public String getLocalizedMessage() {
        return BaseApiCode.getZhMsg(code);
    }
	
	public SystemException(Integer code, String msg) {
		super(msg);
		this.code = code;
	}

	public SystemException(Integer code, String msg, Throwable cause) {
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
