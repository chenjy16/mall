package com.meiduimall.password;

import java.util.HashMap;
import java.util.Map;

public class SecurityBaseApiCode {
	
	public static final Map<Integer, String> zhMsgMap = new HashMap<Integer, String>(300);
	
	public static final Integer EXCEPTION_MD5 = 7001;
	
	public static String getZhMsg(Integer errorCode) {
		return zhMsgMap.get(errorCode);
	}
	
	static {
		zhMsgMap.put(EXCEPTION_MD5, "MD5加密异常");
	}
}
