package com.meiduimall.service.catalog.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpHeaderTools {

	private static Logger logger = LoggerFactory.getLogger(HttpHeaderTools.class);

	private static final String UNKNOWN = "unknown";

	private HttpHeaderTools() {
	}

	/**
	 * 获取当前请求的IP地址
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @return IP地址
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ipAddress = "";
		try {
			ipAddress = request.getHeader("x-forwarded-for");
			if (StringUtils.isBlank(ipAddress) || UNKNOWN.equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getHeader("Proxy-Client-IP");
			}
			if (StringUtils.isBlank(ipAddress) || UNKNOWN.equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getHeader("WL-Proxy-Client-IP");
			}
			if (StringUtils.isBlank(ipAddress) || UNKNOWN.equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getRemoteAddr();
			}
			// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
			if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
				logger.info("当前请求ip地址未过滤=" + ipAddress); // = 15
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(',', 1));
			}
		} catch (Exception e) {
			logger.error("获取当前请求的IP地址异常: " + e);
		}
		return ipAddress;
	}
}
