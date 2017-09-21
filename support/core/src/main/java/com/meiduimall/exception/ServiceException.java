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


public class ServiceException extends BizException {
	
	private static final long serialVersionUID = 3208750423221347264L;
	
	public ServiceException(Integer code) {
		super(code);
	}


	public ServiceException(Integer code, String msg) {
		super(code,msg);
	}


	public ServiceException(Integer code, String msg, Throwable cause) {
		super(code, msg,cause);
	}
}
