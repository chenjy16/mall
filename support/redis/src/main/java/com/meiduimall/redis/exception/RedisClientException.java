/*
 *  @项目名称: ${project_name}
 *
 *  @文件名称: ${file_name}
 *  @Date: ${date}
 *  @Copyright: ${year} www.meiduimall.com Inc. All rights reserved.
 *
 *  注意：本内容仅限于美兑壹购物公司内部传阅，禁止外泄以及用于其他的商业目的
 */

package com.meiduimall.redis.exception;
import com.meiduimall.exception.BizException;

public class RedisClientException extends BizException{

	private static final long serialVersionUID = -6241178004791395261L;

	public RedisClientException(Integer code, String msg) {
		super(code,msg);
	}


	public RedisClientException(Integer code, String message, Throwable cause) {
		super(code, message,cause);
	}

}
