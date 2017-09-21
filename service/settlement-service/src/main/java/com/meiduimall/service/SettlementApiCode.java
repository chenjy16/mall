package com.meiduimall.service;

import java.util.Map;

import com.meiduimall.core.BaseApiCode;

public class SettlementApiCode extends BaseApiCode {
	
	public static final Integer DEDUCT_DEPOSIT_FAILED = 7000;
	public static final Integer UPD_BALANCE_FAILD = 7001;
	public static final Integer AGENCY_ACCOUNT_NOT_FOUND = 7002;
	public static final Integer CALLBACK_O2O_UPD_BALANCE_FAILD = 7003;
	public static final Integer ALREADY_SHAREPROIFT = 7004;
	public static final Integer ALREADY_EXIST_ACCOUNT = 7005;
	public static final Integer SEND_STORE_SCORE_FAILE = 7006;
	public static final Integer ORDER_SHARE_DATA_EMPTY = 7008;
	public static final Integer BALANCE_NOT_ENOUGH = 7009;
	public static final Integer WATERID_OR_WATERTYPE_ISNULL = 7010;
	public static final Integer LOGIN_TYPE_ISNULL = 7011;
	public static final Integer LOGIN_TYPE_AGENTCODE_ISNULL = 7012;
	public static final Integer VERIFY_DRAWCODE_ISNULL = 7013;
	public static final Integer REJECT_DRAWCODE_REMARK_ISNULL = 7013;
	public static final Integer FAILURE_REASON = 7014;
	public static final Integer REJECT_DRAWCASH_FAILURE = 7015;
	public static final Integer VERIFY_DRAWCASH_FAILURE = 7016;
	public static final Integer CONFIRM_DRAWCASH_FAILURE = 7017;
	public static final Integer DRAWCASH_FAILURE = 7018;
	public static final Integer ORDER_ALREADY_SHAREPROFIT = 7019;
	public static final Integer ORDER_SHAREDATA_INSERT_FAILURE = 7020;
	public static final Integer SELLER_NAME_ISNULL = 7021;
	public static final Integer SERVICE_RATE_ISNULL = 7022;
	public static final Integer ORDER_AMOUNT_ISNULL = 7023;
	public static final Integer AGENT_NO_REGION_ISNULL = 7024;
	public static final Integer GET_RECOMMENDER_INFO_FAILURE = 7025;
	public static final Integer IS_TWO_HUNDRED_AGENT_ISNULL = 7026;
	public static final Integer SQLSESSIONFACTORY_INIT_FAIL = 7027;
	
	static {
		Map<Integer, String> zhMsgMap = BaseApiCode.zhMsgMap;
		zhMsgMap.put(DEDUCT_DEPOSIT_FAILED, "区代30%代理费抵扣保证金失败");
		zhMsgMap.put(UPD_BALANCE_FAILD, "更新账户余额失败");
		zhMsgMap.put(AGENCY_ACCOUNT_NOT_FOUND, "代理账户不存在,无法更新账户余额");
		zhMsgMap.put(CALLBACK_O2O_UPD_BALANCE_FAILD, "回调o2o更新余款、抵扣保证金插入缴费记录失败");
		zhMsgMap.put(ALREADY_SHAREPROIFT, "当前个代已分润，不可重复分润");
		zhMsgMap.put(ALREADY_EXIST_ACCOUNT, "当前个代账户已存在，不可重复创建账户");
		zhMsgMap.put(SEND_STORE_SCORE_FAILE, "新商家送积分失败");
		zhMsgMap.put(ORDER_SHARE_DATA_EMPTY, "订单分润数据为空");
		zhMsgMap.put(BALANCE_NOT_ENOUGH, "提现金额不能大于账号可提现金额，请重新输入");
		zhMsgMap.put(WATERID_OR_WATERTYPE_ISNULL, "流水编号或流水类型不能为空");
		zhMsgMap.put(LOGIN_TYPE_ISNULL, "查询账单流水详情，登陆类型不能为空");
		zhMsgMap.put(LOGIN_TYPE_AGENTCODE_ISNULL, "查询账单流水详情，登陆类型为代理，代理编号不能为空");
		zhMsgMap.put(VERIFY_DRAWCODE_ISNULL, "审核提现申请时，提现编号不能为空");
		zhMsgMap.put(REJECT_DRAWCODE_REMARK_ISNULL, "驳回提现申请时，提现编号和驳回原因不能为空");
		zhMsgMap.put(FAILURE_REASON, "确认转账失败时，必须提供原因");
		zhMsgMap.put(REJECT_DRAWCASH_FAILURE, "驳回提现申请失败");
		zhMsgMap.put(VERIFY_DRAWCASH_FAILURE, "审核提现申请失败");
		zhMsgMap.put(CONFIRM_DRAWCASH_FAILURE, "确认转账成功或失败异常");
		zhMsgMap.put(ORDER_ALREADY_SHAREPROFIT, "该订单已经分润过啦！不能再重复分润");
		zhMsgMap.put(ORDER_SHAREDATA_INSERT_FAILURE, "分润数据插入失败，创建订单状态数据失败");
		zhMsgMap.put(SELLER_NAME_ISNULL, "商家编号为空");
		zhMsgMap.put(SERVICE_RATE_ISNULL, "商家收益比例不能为空");
		zhMsgMap.put(ORDER_AMOUNT_ISNULL, "支付金额不能为空或为0");
		zhMsgMap.put(AGENT_NO_REGION_ISNULL, "该订单区代不能为空");
		zhMsgMap.put(GET_RECOMMENDER_INFO_FAILURE, "从会员系统获取推荐人信息失败");
		zhMsgMap.put(IS_TWO_HUNDRED_AGENT_ISNULL, "前二百区代标识不能为空");
		zhMsgMap.put(SQLSESSIONFACTORY_INIT_FAIL, "sqlSessionFactory初始化失败");

	}

}
