package com.meiduimall.service.member.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.meiduimall.exception.SystemException;
import com.meiduimall.service.member.constant.ApiStatusConst;

/**
 * MD5工具类
 * @author chencong
 *
 */
public class MD5Util {
	
	private final static Logger logger=LoggerFactory.getLogger(MD5Util.class);

	/**
	 * MD5 32位加密
	 * @param values 需要加密的字符串
	 * @return 加密后的字符串
	 * @throws SystemException 
	 * @throws Exception
	 */
	public static String MD5EncryptBy32(String values) throws SystemException{
		StringBuffer buf = new StringBuffer();
		MessageDigest md=null;
		try {
			md = MessageDigest.getInstance("MD5");	
		} catch (NoSuchAlgorithmException e) {
			logger.error("执行MD5EncryptBy32()方法异常：{}",e.toString());
			throw new SystemException(ApiStatusConst.MD5_EXCEPTION,ApiStatusConst.getZhMsg(ApiStatusConst.MD5_EXCEPTION));
		}
		md.update(values.getBytes());
		byte b[] = md.digest();
		int i;
		for (int offset = 0; offset < b.length; offset++) {
			i = b[offset];
			if (i < 0)
				i += 256;
			if (i < 16)
				buf.append("0");
			buf.append(Integer.toHexString(i));
		}
		return buf.toString();
	}


	// 主要把传递过来的字符串参数转化为经过MD5算法加密的字符串
	public final static String encrypeString(String neededEncrypedString) throws SystemException {
		// 初始化加密之后的字符串
		String encrypeString = null;
		// 16进制的数组
		char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		// 字符串的加密过程
		try {
			// 把需要加密的字符串转化为字节数组
			byte[] neededEncrypedByteTemp = neededEncrypedString.getBytes();
			// 得到MD5的加密算法对象
			MessageDigest md = MessageDigest.getInstance("MD5");
			// 更新算法使用的摘要
			md.update(neededEncrypedByteTemp);
			// 完成算法加密过程
			byte[] middleResult = md.digest();
			// 把加密后的字节数组转化为字符串
			int length = middleResult.length;
			char[] neededEncrypedByte = new char[length * 2];
			int k = 0;
			for (int i = 0; i < length; i++) {
				byte byte0 = middleResult[i];
				neededEncrypedByte[k++] = hexDigits[byte0 >>> 4 & 0xf];
				neededEncrypedByte[k++] = hexDigits[byte0 & 0xf];
			}
			encrypeString = new String(neededEncrypedByte);
		} catch (NoSuchAlgorithmException ex) {
			logger.error("执行encrypeString()方法异常：{}",ex.toString());
			throw new SystemException(ApiStatusConst.MD5_EXCEPTION,ApiStatusConst.getZhMsg(ApiStatusConst.MD5_EXCEPTION));
		}

		// 返回加密之后的字符串
		return encrypeString;
	}
}