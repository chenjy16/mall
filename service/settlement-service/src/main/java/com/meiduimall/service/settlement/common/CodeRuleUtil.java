package com.meiduimall.service.settlement.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.meiduimall.service.settlement.util.DateUtil;

/**
 * Copyright (C), 2002-2017, 美兑壹购
 * FileName: CodeRuleUtil.java
 * Author:   桂冬玲
 * Date:     2017年3月15日 下午6:15:47
 * Description: 提现和流水编号生成工具类
 */
public class CodeRuleUtil {

	private static final String DAY_PATTERN = "yyMMdd";
	
	private CodeRuleUtil(){}
	
	/**
	 * 保证金分润  生成区代流水编号
	 * @param  agentCode 代理编号
	 * @return String
	 */
	public static String getAreaAgentFlowCode(String agentCode){
		return CodeRuleUtil.flowCode("QL", agentCode, 2);
	}
	
	/**
	 * 保证金分润  生成个代流水编号
	 * @param  agentCode 代理编号
	 * @return String
	 */
	public static String getPersonalAgentFlowCode(String agentCode) {
		return CodeRuleUtil.flowCode("GL", agentCode, 2);
	}
	
	/**
	 * 保证金分润  生成商家流水编号
	 * @param  code 代理编号
	 * @return String
	 */
	public static String getSLFlowCode(String code){
		return CodeRuleUtil.flowCode("SL", code, 2);
	}
	
	/**
	 * 生成区代流水编号
	 * @param  agentCode 代理编号
	 * @param  count 流水总数
	 * @return String
	 */
	public static String getQLWaterId(String agentCode, String count){
		return CodeRuleUtil.createDrawCode("QL", agentCode, count);
	}
	
	/**
	 * 生成个代流水编号
	 * @param  agentCode 代理编号
	 * @param  count 流水总数
	 * @return String
	 */
	public static String getGLWaterId(String agentCode, String count) {
		return CodeRuleUtil.createDrawCode("GL", agentCode, count);
	}
	
	/**
	 * 生成商家流水编号
	 * @param  code 代理编号
	 * @param  count 流水总数
	 * @return String
	 */
	public static String getSLWaterId(String code, String count){
		return CodeRuleUtil.createDrawCode("SL", code, count);
	}
	
	/**
	 * 生成区代提现编号
	 * @param  code 代理编号
	 * @param  count 流水总数
	 * @return String
	 */
	public static String getQZDrawCode(String code, String count){
		return CodeRuleUtil.createDrawCode("QT", code, count);
	}
	
	/**
	 * 生成个代提现编号
	 * @param  code 代理编号
	 * @param  count 流水总数
	 * @return String
	 */
	public static String getGZDrawCode(String code, String count){
		return CodeRuleUtil.createDrawCode("GT", code, count);
	}
	
	/**
	 * 生成商家提现编号
	 * @param  code 代理编号
	 * @param  count 流水总数
	 * @return String
	 */
	public static String getSTDrawCode(String code, String count){
		return CodeRuleUtil.createDrawCode("ST", code, count);
	}
	
	/**
	 * 获取流水编号
	 * @param  prefix 商家-SL，个代-GL，区代-QL
	 * @param  code 商家编码、个代编码、区代编码
	 * @param  length 随机数长度
	 * @return String
	 */
	private static String flowCode(String prefix,String code,int length){
		SimpleDateFormat fmt = new SimpleDateFormat(DAY_PATTERN);
		return prefix + code + fmt.format(new Date()) + getRandomNumber(length);
	}
	
	/**
	 * 生成提现编号
	 * @param  prefix 前缀
	 * @param  code 代理编号
	 * @param  count 提现次数
	 * @return String
	 */
	private static String createDrawCode(String prefix,String code,String count){
		SimpleDateFormat fmt = new SimpleDateFormat(DAY_PATTERN);
		String random = count;
		if(count.length() <= 1){
			random = "0" + count;
		}
		return prefix + code + fmt.format(new Date()) + random;
	}
	
	/**
	 * 获得0-9,a-z,A-Z范围的随机数
	 * @param  length 随机数长度
	 * @return String
	 */
	public static String getRandomChar(int length) {
		char[] chr = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a',
				'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
				'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
				'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
				'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
				'X', 'Y', 'Z' };
		Random random = new Random();
		StringBuilder buffer = new StringBuilder();
		for (int i = 0; i < length; i++) {
			buffer.append(chr[random.nextInt(62)]);
		}
		return buffer.toString();
	}

	public static String getRandomChar() {
		return getRandomChar(10);
	}

	/**
	 * 获得0-9的随机数
	 * @param  length 随机数长度
	 * @return String
	 */
	public static String getRandomNumber(int length) {
		Random random = new Random();
		StringBuilder buffer = new StringBuilder();
		for (int i = 0; i < length; i++) {
			buffer.append(random.nextInt(10));
		}
		return buffer.toString();
	}
	
	/**
	 * 账单编号生成规则
	 * @param  type 角色类型
	 * @param  code 角色编号
	 * @return String
	 */
	public static String getBillid(int type, String code) {
		
		String billid = "ZD" + code + DateUtil.getCurrentSixDay();
		
		switch (type) {
		case 1:
			billid = "SZ" + code + DateUtil.getCurrentSixDay();
			break;
		case 2:
			billid = "GZ" + code + DateUtil.getCurrentSixDay();
			break;
		case 3:
		case 4:
			billid = "QZ" + code + DateUtil.getCurrentSixDay();
			break;
		default:
			break;
		}
		return billid;
	}
	
	/**
	 * 获取账单流水汇总表编号，特殊处理
	 * @param  type 角色类型
	 * @param  code 角色编号
	 * @return String
	 */
	public static String getBillFlowCode(int type, String code) {
		
		String billid = "ZD" + code + DateUtil.getCurrentSixDay();
		
		switch (type) {
		case 1:
			billid = "SL" + code + DateUtil.getCurrentSixDay() + getRandomNumber(2);
			break;
		case 2:
			billid = "GL" + code + DateUtil.getCurrentSixDay() + getRandomNumber(2);
			break;
		case 3:
		case 4:
			billid = "QL" + code + DateUtil.getCurrentSixDay() + getRandomNumber(2);
			break;
		default:
			break;
		}
		return billid;
	}
	
	/**
	 * 由于账户表和账单流水表类型不对应，为了代码简洁，做特殊处理
	 * @param  type 原始类型编号
	 * @return String
	 */
	public static int getAccountRoleType(int type) {
		int newtype = 0;
		switch (type) {
		case 1:
			newtype = 3;
			break;
		case 2:
			newtype = 2;
			break;
		case 3:
		case 4:
			newtype = 1;
			break;
		default:
			break;
		}
		return newtype;
	}
	
}
