package com.meiduimall.service.sms.service;

/**
 * 阿里大于短信服务
 * 
 * @author yangchang
 */
public interface AliyunService {

	/**
	 * 发送短信
	 * 
	 * @param phones
	 *            手机号
	 * @param externalTemplateNo
	 *            阿里大于分配的模板ID
	 * @param params
	 *            发送参数,阿里只需要传递参数
	 * @return 发送结果，true表示发送成功，false表示发送失败
	 */
	boolean send(String phones, String externalTemplateNo, String params);
}
