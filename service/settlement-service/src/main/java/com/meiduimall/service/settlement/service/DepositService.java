package com.meiduimall.service.settlement.service;

import java.util.List;
import java.util.Map;

import com.meiduimall.service.settlement.model.EcmAgent;
import com.meiduimall.service.settlement.model.EcmMzfAccount;
import com.meiduimall.service.settlement.model.EcmStore;
import com.meiduimall.service.settlement.model.ShareProfitAgentLog;

/**
 * Copyright (C), 2002-2017, 美兑壹购物
 * FileName: DepositService.java
 * Author:   guidl
 * Date:     2017年3月24日 上午11:25:02
 * Description: 个代保证金分润相关
 */
public interface DepositService {
	
	
	/**
	 * 功能描述:  保证金分账 业务逻辑方法
	 * Author: guidl
	 * Date:   2017年3月24日 上午11:25:02
	 * @param  ecmAgent 个代信息
	 * @return Map
	 */
	public List<Map<String, Object>> shareDeposit(EcmAgent ecmAgent);
	
	
	/**
	 * 功能描述:  新商家送积分
	 * Author: guidl
	 * Date:   2017年3月24日 上午11:25:02
	 * @param  ecmStore 商家信息
	 * @return Map
	 */
	public List<Map<String, Object>> updateStoreScore(EcmStore ecmStore);
	
	
	/**
	 * 功能描述:  创建账户
	 * Author: guidl
	 * Date:   2017年3月24日 上午11:25:02
	 * @param  ecmMzfAccount 账号信息
	 * @return int
	 */
	public int createAccount(EcmMzfAccount ecmMzfAccount);
	
	
	/**
	 * 功能描述:  保证金分润主方法
	 * Author: guidl
	 * Date:   2017年3月24日 上午11:25:02
	 * @param  ecmAgent 代理信息
	 * @param  systemSetting 系统设置相关信息
	 */
	public void shareDepositMain(EcmAgent ecmAgent, Map<String, String> systemSetting);
	
	
	/**
	 * 功能描述:  插入保证金分润异常日志
	 * Author: guidl
	 * Date:   2017年3月24日 上午11:25:02
	 * @param  agentLog 保证金分润错入日志对象
	 * @param  retryType 重试类型
	 */
	public void shareProfitAgentLog(ShareProfitAgentLog agentLog, String retryType);
	
	
}
