package com.meiduimall.service.sms.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * 发送普通短信参数模板
 * 
 * @author pc
 *
 */
public class CheckCodeRequest implements Serializable {

	private static final long serialVersionUID = 7996920400702100275L;

	/* 公共参数 */
	@NotNull
	private String phones;

	@NotNull
	private String templateKey; // 模板id

	@NotNull
	private String verificationCode; // 验证码，用户输入验证码校验时使用

	public String getPhones() {
		return phones;
	}

	public void setPhones(String phones) {
		this.phones = phones;
	}

	public String getTemplateKey() {
		return templateKey;
	}

	public void setTemplateKey(String templateKey) {
		this.templateKey = templateKey;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
}
