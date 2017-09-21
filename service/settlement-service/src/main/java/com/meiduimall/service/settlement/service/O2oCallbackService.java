package com.meiduimall.service.settlement.service;

import java.util.Collection;

import com.meiduimall.service.settlement.model.EcmAgent;

/**
 * Copyright (C), 2002-2017, 美兑壹购
 * FileName: O2oCallbackService.java
 * Author:   许彦雄
 * Date:     2017年2月21日 下午6:15:47
 * Description: O2O回调接口Service
 */
public interface O2oCallbackService {
	
	/**
	 * 功能描述:  通知订单结算各阶段状态给O2O
	 * Author: 吴军
	 * Date:   2017年3月14日 下午3:38:26   
	 * @param  orderSns 订单号
	 * @param  statusCode 状态码
	 * @return boolean
	 * 
	 */
	public boolean informSettlementStatus(Collection<String> orderSns,Integer statusCode);
	
	/**
	 * 功能描述:  回调O2O接口 更新代理表中区代的余款  插入抵扣保证金到缴费记录表中
	 * Author: 吴军
	 * Date:   2017年3月14日 下午3:38:26   
	 * @param  areaAgent 代理相关信息
	 * @param  amount 金额
	 * @return boolean
	 * 
	 */
	public String addProxyFee(EcmAgent areaAgent, double amount);

}
