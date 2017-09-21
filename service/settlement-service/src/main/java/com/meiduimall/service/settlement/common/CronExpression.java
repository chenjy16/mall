package com.meiduimall.service.settlement.common;

/**
 * Copyright (C), 2002-2017, 美兑壹购
 * FileName: CronExpression.java
 * Author:   桂冬玲
 * Date:     2017年3月15日 下午6:15:47
 * Description: 定时任务触发时间常量类
 */
public class CronExpression {

	/**
	 * 每隔1分钟触发
	 */
	public static final String EVERY_1_MINUTE = "0 0/1 * * * ?";
	
	/**
	 * 每隔2分钟触发
	 */
	public static final String EVERY_2_MINUTE = "0 0/2 * * * ?";
	
	/**
	 * 每隔5分钟触发
	 */
	public static final String EVERY_5_MINUTE = "0 0/5 * * * ?";
	
	/**
	 * 每隔5分钟触发
	 */
	public static final String EVERY_10_MINUTE = "0 0/10 * * * ?";
	
	/**
	 * 每天凌晨3点触发
	 */
	public static final String TIME_3_HOUR = "0 0 3 * * ?";
	
	/**
	 * 每天0点5分触发
	 */
	public static final String TIME_ZERO_HOUR_FIVE_MIN = "0 5 0 * * ?";
	
	
	/**
	 * 每天0点30分触发
	 */
	public static final String TIME_ZERO_HOUR_THIRTY_MIN = "0 30 0 * * ?";
	
	private CronExpression(){}
	
}
