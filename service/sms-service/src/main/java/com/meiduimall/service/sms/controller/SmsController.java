package com.meiduimall.service.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.service.sms.request.CheckCodeRequest;
import com.meiduimall.service.sms.request.SendCodeRequest;
import com.meiduimall.service.sms.request.SendMessageRequest;
import com.meiduimall.service.sms.service.SmsService;

/**
 * 公共短信发送服务
 *
 * @author lin
 */

@RestController
@RequestMapping("/notify/short_msg_service/v1")
public class SmsController {

	@Autowired
	private SmsService smsService;

	/**
	 * 发送普通短信
	 *
	 * @param model
	 *            请求参数封装的SendMessageRequest对象
	 * @return 发送结果
	 */
	@RequestMapping("/send_common_sms_message")
	public ResBodyData sendSmsMessage(@Validated SendMessageRequest model) {
		return smsService.sendSmsMessage(model);
	}

	/**
	 * 发送短信验证码
	 *
	 * @param model
	 *            请求参数封装的SendCodeRequest对象
	 * @return 发送结果和验证码
	 */
	@RequestMapping("/send_sms_verification_code")
	public ResBodyData sendSmsVerificationCode(@Validated SendCodeRequest model) {
		return smsService.sendSmsVerificationCode(model);
	}

	/**
	 * 校验短信验证码
	 *
	 * @param model
	 *            请求参数封装的CheckCodeRequest对象
	 * @return 校验结果
	 */
	@RequestMapping("/check_sms_verification_code")
	public ResBodyData checkSmsVerificationCode(@Validated CheckCodeRequest model) {
		return smsService.checkSmsVerificationCode(model);
	}
}
