package com.meiduimall.service.settlement.task;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.redis.util.RedisUtils;
import com.meiduimall.service.settlement.common.CronExpression;
import com.meiduimall.service.settlement.common.ShareProfitConstants;
import com.meiduimall.service.settlement.model.EcmAgent;
import com.meiduimall.service.settlement.model.ShareProfitAgentLog;
import com.meiduimall.service.settlement.service.AgentService;
import com.meiduimall.service.settlement.util.DateUtil;


/**
 * Copyright (C), 2002-2017, 美兑壹购
 * FileName: DepositRetryTask.java
 * Author:   桂冬玲
 * Date:     2017年2月26日 下午6:15:47
 * Description: 保证金分润重试机制 定时任务
 */
@Service
public class DepositRetryTask {
	
	private static final Logger logger = LoggerFactory.getLogger(DepositRetryTask.class);
	
	private static final String SHARE_5MIN_RETRY = "share5MinRetry";
	private static final String SHARE_30MIN_RETRY = "share30MinRetry";
	private static final String SHARE_12HOURS_RETRY = "share12HoursRetry";
	
	@Autowired
	private AgentService agentService;
	
	@Autowired
	private AsyncTaskService asyncTaskService;
	
	/**
	 * 重试送积分定时任务
	 */
	@Scheduled(cron = CronExpression.EVERY_10_MINUTE)
	@Async
	public void retryShareDeposit(){
		try {
			
			//查询基本分润配置
			Map<String, String> systemSetting = agentService.quertSharefit();
			int score = Integer.parseInt(systemSetting.get(ShareProfitConstants.NEWBIE_PERSON_POINT));//新加盟个代获得积分 6500
			
			//获取新个代送积分失败日志记录
			Map<String, Object> result = getAgentRetry();
			//调用送积分方法
			updateScore(score, result);
		} catch (ServiceException e) {
			logger.error("新个代重试送积分失败：{}", e);
		}
		
	}

	/**
	 * 给新个代送积分
	 * @param score
	 * @param result
	 */
	private void updateScore(int score, Map<String, Object> result) {
		if (result != null && !result.isEmpty()) {
			
			for (Map.Entry<String, Object> entry : result.entrySet()) {
				String shareProfitJsonObj = RedisUtils.get(ShareProfitConstants.REDIS_KEY_PRIFIX_AGENT + entry.getKey());
				EcmAgent ecmAgent = JsonUtils.jsonToBean(shareProfitJsonObj, EcmAgent.class);
				
				String retryType = "";
				
				if (ShareProfitConstants.SHARE_PROFIT_RETRY_TYPE_FINAL_ROUND.equals(entry.getValue())) {
					retryType = ShareProfitConstants.SHARE_PROFIT_RETRY_TYPE_FINAL_ROUND;
				}
				
				//调用新个代送积分方法
				asyncTaskService.updateScore(ecmAgent, score, ShareProfitConstants.SHARE_PROFIT_SOURCE_CACHE, retryType);
			}
		}
	}
	
	
	/**
	 * 获取新个代送积分失败日志记录
	 * @return Map
	 */
	private Map<String, Object> getAgentRetry() {
		
		int currentTimestamp = DateUtil.getCurrentTimeSec();
		
		final Map<String, Object> retryAgents = Maps.newHashMap();
		
		List<ShareProfitAgentLog> share5MinRetry = agentService.getAgentsRetry(currentTimestamp, SHARE_5MIN_RETRY);//获取5分钟后重新送积分的个代
		List<ShareProfitAgentLog> share30MinRetry = agentService.getAgentsRetry(currentTimestamp, SHARE_30MIN_RETRY);//获取30分钟后重新送积分的个代
		List<ShareProfitAgentLog> share12HoursRetry = agentService.getAgentsRetry(currentTimestamp, SHARE_12HOURS_RETRY);//获取12小时后重新送积分的个代
		
		if (share12HoursRetry != null && !share12HoursRetry.isEmpty()) {
			for (ShareProfitAgentLog agentLog : share12HoursRetry) {
				retryAgents.put(agentLog.getAgentNo(), ShareProfitConstants.SHARE_PROFIT_RETRY_TYPE_FINAL_ROUND);
			}
		}

		if (share30MinRetry != null && !share30MinRetry.isEmpty()) {
			for (ShareProfitAgentLog agentLog : share30MinRetry) {
				retryAgents.put(agentLog.getAgentNo(), "");
			}
		}

		if (share5MinRetry != null && !share5MinRetry.isEmpty()) {
			for (ShareProfitAgentLog agentLog : share5MinRetry) {
				retryAgents.put(agentLog.getAgentNo(), "");
			}
		}
		return retryAgents;
	}
}
