package com.meiduimall.service.sms.service;

public interface MessageChannelService {

	/**
	 * 获取短信渠道并转成json字符串
	 * 
	 * @param key
	 *            短信渠道key
	 * @return 所有短信渠道组成的json串
	 */
	String getChannelList(String key);

}
