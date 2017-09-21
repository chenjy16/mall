package com.meiduimall.service.sms.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.meiduimall.core.util.HttpUtils;
import com.meiduimall.exception.SystemException;
import com.meiduimall.password.exception.Md5Exception;
import com.meiduimall.password.util.MD5;
import com.meiduimall.service.sms.constant.SmsApiCode;
import com.meiduimall.service.sms.service.ZucpService;

@Service
public class ZucpServiceImpl implements ZucpService {

	private static Logger logger = LoggerFactory.getLogger(ZucpServiceImpl.class);

	@Value("${zucpUrl}")
	private String zucpUrl;

	@Value("${zucpUser}")
	private String zucpUser;

	@Value("${zucpPasswd}")
	private String zucpPasswd;

	private String buildBody(String mobile, String content, String ext, String stime, String rrid) throws Md5Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
				+ "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">");
		sb.append("<soap:Body>");
		sb.append("<mdSmsSend_u xmlns=\"http://tempuri.org/\">");
		sb.append("<sn>");
		sb.append(zucpUser);
		sb.append("</sn>");
		sb.append("<pwd>");
		sb.append(MD5.getMD5EncodeUTF8(zucpUser + zucpPasswd).toUpperCase());
		sb.append("</pwd>");
		sb.append("<mobile>");
		sb.append(mobile);
		sb.append("</mobile>");
		sb.append("<content>");
		sb.append(content);
		sb.append("</content>");
		sb.append("<ext>");
		sb.append(ext);
		sb.append("</ext>");
		sb.append("<stime>");
		sb.append(stime);
		sb.append("</stime>");
		sb.append("<rrid>");
		sb.append(rrid);
		sb.append("</rrid>");
		sb.append("</mdSmsSend_u>");
		sb.append("</soap:Body>");
		sb.append("</soap:Envelope>");
		return sb.toString();
	}

	@Override
	public String send(String mobile, String content, String ext, String stime, String rrid) throws SystemException {

		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "text/xml;charset=utf-8");
		headers.put("SOAPAction", "http://tempuri.org/mdSmsSend_u");

		try {

			String buildBody = buildBody(mobile, content, ext, stime, rrid);
			logger.info("短信发送，漫道发送内容 : " + buildBody);

			String result = HttpUtils.post(zucpUrl, buildBody, headers, "UTF-8", "UTF-8");

			logger.info("短信发送，漫道返回 : " + result);

			String[] array1 = result.split("<mdSmsSend_uResult>");
			String[] array2 = array1[1].split("</mdSmsSend_uResult>");

			return array2[0];
		} catch (Exception e) {
			logger.error("ZucpService error. " + e);
			throw new SystemException(SmsApiCode.SMS_SEND_FAILUER, SmsApiCode.getZhMsg(SmsApiCode.SMS_SEND_FAILUER));
		}

	}

	@Override
	public String send(String mobile, String content) throws SystemException {
		return this.send(mobile, content, "", "", "");
	}
}
