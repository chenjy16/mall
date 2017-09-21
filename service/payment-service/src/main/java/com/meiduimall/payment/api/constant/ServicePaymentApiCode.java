package com.meiduimall.payment.api.constant;

import com.meiduimall.core.BaseApiCode;

public class ServicePaymentApiCode extends BaseApiCode{
	

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
	public static final Integer DB_EXCEPTION = 7500;

	/** 未知错误 */
	public static final Integer UNKNOWN_ERROR = 7777;
	
	public static final Integer HIKA_API_ERROR = 7009;
	
	public static final Integer HIKA_API_TRANS_ERROR = 7010;
	
	public static final Integer CLASS_REFLECT_ERROR = 7011;
	
	public static final Integer XML_OBJECT_TRANS_ERROR = 7012;
	
	public static final Integer WEBCHAT_API_ERROR = 7013;
	
	public static final Integer DATE_PARES_ERROR = 7014;
	
	public static final Integer ALIPAY_SIGN_ERROR = 7015;
	
	public static final Integer SIGN_ENCODE_ERROR = 7016;
	
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
		zhMsgMap.put(UNKNOWN_ERROR, "未知错误");
		zhMsgMap.put(HIKA_API_ERROR, "汇卡接口调用失败");
		zhMsgMap.put(HIKA_API_TRANS_ERROR, "汇卡接口返回内容转换对象异常");
		zhMsgMap.put(CLASS_REFLECT_ERROR, "获取类的属性或值异常");
		zhMsgMap.put(XML_OBJECT_TRANS_ERROR, "xml与对象间转换异常");
		zhMsgMap.put(WEBCHAT_API_ERROR, "微信接口调用异常");
		zhMsgMap.put(DATE_PARES_ERROR, "日期转换异常");
		zhMsgMap.put(ALIPAY_SIGN_ERROR, "支付宝获取签名异常");
		zhMsgMap.put(SIGN_ENCODE_ERROR, "签名加密异常");
	}

}
