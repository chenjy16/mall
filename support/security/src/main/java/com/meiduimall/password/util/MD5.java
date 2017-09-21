package com.meiduimall.password.util;
import java.security.MessageDigest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.meiduimall.password.SecurityBaseApiCode;
import com.meiduimall.password.exception.Md5Exception;

public class MD5 {
	
	private static Logger logger = LoggerFactory.getLogger(MD5.class);
	
	private static final String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };


	public static String byteArrayToHexString(byte[] b) {
		StringBuilder resultSb = new StringBuilder();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}
	
	
	/**
	 * 功能描述:  MD5加密
	 * Date:   2017年4月25日 下午4:46:47 
	 * param   @param origin
	 * param   @return
	 * param   @throws Md5Exception   
	 * return  String
	 */
	public static String encode(String origin) throws Md5Exception {
		String resultString = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(origin.getBytes()));
		} catch (Exception ex) {
			logger.error("md5加密报错:{}", ex);
			throw new Md5Exception(SecurityBaseApiCode.EXCEPTION_MD5,SecurityBaseApiCode.getZhMsg(SecurityBaseApiCode.EXCEPTION_MD5));
		}
		return resultString;
	}
	
	
	/**
	 * 功能描述:  先按照utf8编码MD5加密
	 * Date:   2017年4月25日 下午4:47:23 
	 * param   @param origin
	 * param   @return
	 * param   @throws Md5Exception   
	 * return  String
	 */
	public static String getMD5EncodeUTF8(String origin) throws Md5Exception {
		String resultString = null;
		try {
			resultString = new String(origin.getBytes("UTF-8"));
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
		} catch (Exception ex) {
			logger.error("utf8编码md5加密报错:{}", ex);
			throw new Md5Exception(SecurityBaseApiCode.EXCEPTION_MD5,SecurityBaseApiCode.getZhMsg(SecurityBaseApiCode.EXCEPTION_MD5));
		}
		return resultString;
	}
	
	
}