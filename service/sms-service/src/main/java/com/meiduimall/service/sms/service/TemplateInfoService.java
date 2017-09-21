package com.meiduimall.service.sms.service;

public interface TemplateInfoService {

	/**
	 * 获取短信模板并转成json字符串
	 * 
	 * @param key
	 *            短信模板key
	 * @return 所有短信模板组成的json串
	 */
	String getTemplateList(String key);
}
