package com.meiduimall.service.settlement.common;

/**
 * Copyright (C), 2002-2017, 美兑壹购
 * FileName: ShareProfitConstants.java
 * Author:   许彦雄
 * Date:     2017年3月15日 下午6:15:47
 * Description: 分润常量定义类
 */
public abstract class ShareProfitConstants {
	
	// date
	public static final String FORMATE_DATE_ALL = "yyyy-mm-dd hh24:mm:ss";
	
	//数据来源
	public static final String DATA_SOURCE_O2O = "o2o";
	
	//代理费比例
	public static final int REC_AREA_AGENTRATE = 50;//推荐人是区代时，区代获取代理费的比例
	public static final int AREA_AGENTRATE = 30;//推荐人是个代时，个代所属区代获取代理费的比例
	public static final int REC_PERSONAL_AGENTRATE = 20;//推荐人是个代时，个代获取代理费的比例
	
	//区代所属公司名称
	public static final String COMPANY_NAME = "美兑壹购物";
	
	//推荐时备注内容
	public static final String REMARK_1_TYPE = "区代推荐个代";
	public static final String REMARK_2_TYPE = "个代推荐个代";
	public static final String REMARK_3_TYPE = "抵扣履约保证金";
	
	//区代直推个代代理费分成
	public static final String AREA_TO_PERSON_RATE = "area_to_person_rate";
	
	//个代推个代代理费推荐人
	public static final String PERSON_TO_PERSON_REC_RATE = "person_to_person_rec_rate";
	
	//个代推个代区代代理费
	public static final String PERSON_TO_PERSON_AREA_RATE = "person_to_person_area_rate";
	
	//新加入的个代积分
	public static final String NEWBIE_PERSON_POINT = "newbie_person_point";
	
	//商家初始积分
	public static final String STORE_INIT_POINT = "store_init_point_num";
	
	//提现手续费
	public static final String CASH_WITHDRAWAL_FEE = "cash_withdrawal_fee";
	
	public static final int RESPONSE_STATUS_CODE_SUCCESS=0;
	public static final int RESPONSE_STATUS_CODE_FAILURE=1;
	
	public static final String RESPONSE_SUCCESS = "成功";
	
	public static final int SHARE_PROFIT_RETRY_STATUS_CODE_SUCCESS=1;
	public static final int SHARE_PROFIT_RETRY_STATUS_CODE_FAIL=0;
	
	public static final int SHARE_PROFIT_RETRY_FLAG_YES=1;
	public static final int SHARE_PROFIT_RETRY_FLAG_NO=0;
	
	public static final String SHARE_PROFIT_RETRY_TYPE_FINAL_ROUND="finalRound";
	
	public static final String REDIS_KEY_PREFIX_ORDER="orderSn:";
	
	public static final String REDIS_KEY_PRIFIX_AGENT = "agentNo";
	
	public static final String SHARE_PROFIT_SOURCE_O2O="O2O";
	public static final String SHARE_PROFIT_SOURCE_CACHE="Cache";
	
	
	public static final int SHARE_PROFIT_RESULT_CODE_SUCCESS=0; //成功
	
	/** 如果status_code为非0，表示分润失败，O2O需要记录下这些订单编号，将来需要在页面中显示用于手动重新出发分润。 **/
	public static final int SHARE_PROFIT_RESULT_CODE_FAILURE_ONE=1;  //分润数据有问题，分润失败，没有重试机制，修复问题后需要手动触发分润
	public static final int SHARE_PROFIT_RESULT_CODE_FAILURE_NINETEEN=19;  //分润失败，重试机制触发，如果重试机制3次后都失败，则需要手动触发分润，如果成功，则从页面上删除掉该订单编号。
	
	public static final int ROLE_TYPE_AREA_AGENT=1;
	public static final int ROLE_TYPE_PERSONAL_AGENT=2;
	public static final int ROLE_TYPE_STORE=3;
	
	/** 1提现 2账单 3代理费 4保证金   **/
	public static final String WATER_TYPE_DRAW_CASH="1";
	public static final String WATER_TYPE_BILL="2";
	public static final String WATER_TYPE_AGENT_PROFIT="3";
	public static final String WATER_TYPE_DEPOSIT="4";
	
	/**  回调O2O接口时传递的结算状态码： 1:积分已送  2：已结算   3：一级推荐人现金奖励已送 **/
	public static final Integer O2O_SETTLEMENT_STATUS_CODE_SCORE=1;
	public static final Integer O2O_SETTLEMENT_STATUS_CODE_BILL=2;
	public static final Integer O2O_SETTLEMENT_STATUS_CODE_CASH=3;
	
	private ShareProfitConstants(){}
	
}
