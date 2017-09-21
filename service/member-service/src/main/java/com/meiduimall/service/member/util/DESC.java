package com.meiduimall.service.member.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.meiduimall.exception.SystemException;
import com.meiduimall.service.member.constant.SysParamsConst;

/**
 * 数据库加密解密类
 * @author chencong
 *
 */
public class DESC {
	
	private final static Logger logger=LoggerFactory.getLogger(DESC.class);
	
	private final static String key=SystemConfig.configMap.get(SysParamsConst.DESC_KEY);//加密解密需要的key
	
	/**
	 * 字符串加密 系统默认方式
	 * @param str 需要加密的字符串
	 * @return
	 * @throws SystemException 
	 */
	public static String encryption(String str){
		return encrypt(str,key);
	}

	/**
	 * 字符串解密 系统默认方式
	 * @param str 需要解密的字符串
	 * @return
	 * @throws SystemException 
	 */
	public static String deyption(String str) throws SystemException {
		return decrypt(str,key);
	}

	/**
	 * 字符串加密
	 * @param str 需要加密的字符串
	 * @param memberId 会员编号
	 * @return
	 * @throws SystemException 
	 */
	public static String encryption(String str, String memberId) throws SystemException {
		return encrypt(str, MD5Util.encrypeString(memberId));
	}

	/**
	 * 字符串解密
	 * @param str 需要解密的字符串
	 * @param memberId 会员编号
	 * @return
	 * @throws SystemException 
	 */
	public static String deyption(String str, String memberId) throws SystemException{
		return decrypt(str, MD5Util.encrypeString(memberId));
	}

	private static String encrypt(String data, String key) {
		String result=null;
		Key deskey=null;
		try {
			deskey = keyGenerator(key);
		} catch (InvalidKeyException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvalidKeySpecException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Cipher cipher=null;
		try {
			cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SecureRandom random = new SecureRandom();
		try {
			cipher.init(Cipher.ENCRYPT_MODE, deskey, random);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			result=Base64.encodeBase64String(cipher.doFinal(data.getBytes(SysParamsConst.GBK)));
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	private static String decrypt(String data, String key) throws SystemException {
		String result=null;
		Key deskey=null;
		try {
			deskey = keyGenerator(key);
		} catch (InvalidKeyException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvalidKeySpecException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Cipher cipher=null;
		try {
			cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			cipher.init(Cipher.DECRYPT_MODE, deskey);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			result=new String(cipher.doFinal(Base64.decodeBase64(data)),SysParamsConst.GBK);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	private static SecretKey keyGenerator(String keyStr) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException{
		byte input[] = HexString2Bytes(keyStr);
		DESKeySpec desKey;
		desKey = new DESKeySpec(input);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(SysParamsConst.DES);
		SecretKey securekey = keyFactory.generateSecret(desKey);
		return securekey;
	}

	private static int parse(char c) {
		if (c >= 'a')
			return (c - 'a' + 10) & 0x0f;
		if (c >= 'A')
			return (c - 'A' + 10) & 0x0f;
		return (c - '0') & 0x0f;
	}

	private static byte[] HexString2Bytes(String hexstr) {
		byte[] b = new byte[hexstr.length() / 2];
		int j = 0;
		for (int i = 0; i < b.length; i++) {
			char c0 = hexstr.charAt(j++);
			char c1 = hexstr.charAt(j++);
			b[i] = (byte) ((parse(c0) << 4) | parse(c1));
		}
		return b;
	}

}