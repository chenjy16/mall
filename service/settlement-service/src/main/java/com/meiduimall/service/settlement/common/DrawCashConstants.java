package com.meiduimall.service.settlement.common;

/**
 * Copyright (C), 2002-2017, 美兑壹购
 * FileName: DrawCashConstants.java
 * Author:   许彦雄
 * Date:     2017年3月15日 下午6:15:47
 * Description: 提现状态常量定义类
 */
public class DrawCashConstants {
	
	/** 提现状态常量   1待审核，2审核通过，3审核不通过   4转账成功,5转账失败 **/
	public static final int STATUS_WAITING_FOR_VERIFY=1;
	
	public static final int STATUS_VERIFIED_SUCDESS=2;
	
	public static final int STATUS_VERIFIED_REJECTED=3;
	
	public static final int STATUS_TRANSFER_SUCCESS=4;
	
	public static final int STATUS_TRANSFER_FAIL=5;
	
	private DrawCashConstants(){}

}
