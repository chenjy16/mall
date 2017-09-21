package com.meiduimall.service.sms.service;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.service.sms.request.CheckCodeRequest;
import com.meiduimall.service.sms.request.SendCodeRequest;
import com.meiduimall.service.sms.request.SendMessageRequest;

public interface SmsService {

	/**
	 * 发送普通短信
	 * 
	 * @param model
	 *            请求参数封装的SendMessageRequest对象
	 * @return 发送结果
	 */
	ResBodyData sendSmsMessage(SendMessageRequest model);

	/**
	 * 发送短信验证码
	 * 
	 * @param model
	 *            请求参数封装的SendCodeRequest对象
	 * @return 发送结果和验证码
	 */
	ResBodyData sendSmsVerificationCode(SendCodeRequest model);

	/**
	 * 校验短信验证码
	 * 
	 * @param model
	 *            请求参数封装的CheckCodeRequest对象
	 * @return 校验结果
	 */
	ResBodyData checkSmsVerificationCode(CheckCodeRequest model);
}
