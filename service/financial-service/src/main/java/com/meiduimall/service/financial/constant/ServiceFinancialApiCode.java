package com.meiduimall.service.financial.constant;

import com.meiduimall.core.BaseApiCode;

public class ServiceFinancialApiCode extends BaseApiCode {
	
	/** 请求成功 */
	public static final Integer REQUEST_SUCCESS = 7001;
	/** 请求失败 */
	public static final Integer REQUEST_FAIL = 7002;

	/** 操作成功 */
	public static final Integer OPERAT_SUCCESS = 7003;
	/** 操作失败 */
	public static final Integer OPERAT_FAIL = 7004;

	/** 请求参数错误 */
	public static final Integer REQUEST_PARAMS_ERROR = 7005;
	/** 暂无数据 */
	public static final Integer NONE_DATA = 7006;

	/** 请先登录 */
	public static final Integer NO_LOGIN = 7007;
	/** memId验证异常 */
	public static final Integer MEMID_VALIDATE_ERROR = 7008;


	/** 数据库异常 */
	public static final Integer DB_EXCEPTION = 7777;

	static {
		zhMsgMap.put(REQUEST_SUCCESS, "请求成功");
		zhMsgMap.put(REQUEST_FAIL, "请求失败");
		zhMsgMap.put(OPERAT_SUCCESS, "操作成功");
		zhMsgMap.put(OPERAT_FAIL, "操作失败");
		zhMsgMap.put(REQUEST_PARAMS_ERROR, "请求参数错误");
		zhMsgMap.put(NONE_DATA, "暂无数据");
		zhMsgMap.put(NO_LOGIN, "请先登录");
		zhMsgMap.put(MEMID_VALIDATE_ERROR, "memId验证异常");
		zhMsgMap.put(DB_EXCEPTION, "数据库异常");
	}
	
	private ServiceFinancialApiCode(){}
}
