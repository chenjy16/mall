package com.meiduimall.service.settlement.task;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;
import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.redis.util.RedisUtils;
import com.meiduimall.service.settlement.common.CronExpression;
import com.meiduimall.service.settlement.common.ShareProfitConstants;
import com.meiduimall.service.settlement.dao.BaseMapper;
import com.meiduimall.service.settlement.model.EcmMzfShareProfit;
import com.meiduimall.service.settlement.model.ShareProfitOrderLog;
import com.meiduimall.service.settlement.util.DateUtil;


/**
 * Copyright (C), 2002-2017, 美兑壹购
 * FileName: ShareProfitRetryTask.java
 * Author:   许彦雄
 * Date:     2017年2月26日 下午6:15:47
 * Description: 订单分润重试机制定时任务
 */
@Component
public class ShareProfitRetryTask {
	
	private static final Logger log = LoggerFactory.getLogger(ShareProfitRetryTask.class);
	
	@Autowired
	private BaseMapper baseMapper;
	
	@Autowired
	private AsyncTaskService asyncTaskService;
	
	@Scheduled(cron = CronExpression.EVERY_10_MINUTE)
	@Async
	public void retryOrderShareProfit(){
		
		try {
			//从ecm_mzf_log_shareprofit_order表中查询需要重新分润的订单
			Map<String,String> map = getOrders2Retry();
			//调用订单送积分方法
			updateScore2MemberSystem(map);		
			
		} catch (ServiceException e) {
			log.error("订单分润重试送积分失败：{}", e);
		}
		

	}

	/**
	 * 调用订单送积分方法
	 * @param map
	 */
	private void updateScore2MemberSystem(Map<String, String> map) {
		if(!map.isEmpty()){
			for(Map.Entry<String, String> entry : map.entrySet()){
				String shareProfitJsonObj = RedisUtils.get(ShareProfitConstants.REDIS_KEY_PREFIX_ORDER + entry.getKey());
				EcmMzfShareProfit shareProfit = JsonUtils.jsonToBean(shareProfitJsonObj, EcmMzfShareProfit.class);	
				
				String retryType = "";
				if (ShareProfitConstants.SHARE_PROFIT_RETRY_TYPE_FINAL_ROUND.equals(entry.getValue())) {
					retryType = ShareProfitConstants.SHARE_PROFIT_RETRY_TYPE_FINAL_ROUND;
				}
				
				asyncTaskService.updateScore2MemberSystem(shareProfit, ShareProfitConstants.SHARE_PROFIT_SOURCE_CACHE, retryType);
			}
		}
	}
	
	private Map<String, String> getOrders2Retry()  {
		int currentTimestamp = DateUtil.getCurrentTimeSec();
		log.info("current timestamp sec:{},Date:{}",currentTimestamp,DateUtil.getCurrentTime());
		
		List<ShareProfitOrderLog> shareProfit5MinOrder2Retry = baseMapper.selectList(currentTimestamp, "ShareProfitOrderLogMapper.get5MinOrders2Retry");
		List<ShareProfitOrderLog> shareProfit30MinOrder2Retry = baseMapper.selectList(currentTimestamp, "ShareProfitOrderLogMapper.get30MinOrders2Retry");
		List<ShareProfitOrderLog> shareProfit12HOrder2Retry = baseMapper.selectList(currentTimestamp, "ShareProfitOrderLogMapper.get12HOrders2Retry");
		
		final Map<String, String> retryOrders = Maps.newHashMap();
		//注意retryOrders存放orderSn数据要安装这样的顺序:12H->30Min->5Min,因为要过滤掉已经重试过但失败的orderSn。
		if (shareProfit12HOrder2Retry != null && !shareProfit12HOrder2Retry.isEmpty()) {
			for (ShareProfitOrderLog orderLog : shareProfit12HOrder2Retry) {
				log.info("shareProfit12HOrder2Retry orderSn:{}",orderLog.getOrderSn());
				retryOrders.put(orderLog.getOrderSn(), ShareProfitConstants.SHARE_PROFIT_RETRY_TYPE_FINAL_ROUND);
			}
		}
		
		if (shareProfit30MinOrder2Retry != null && !shareProfit30MinOrder2Retry.isEmpty()) {
			for (ShareProfitOrderLog orderLog : shareProfit30MinOrder2Retry) {
				log.info("shareProfit30MinOrder2Retry orderSn:{}", orderLog.getOrderSn());
				retryOrders.put(orderLog.getOrderSn(), "");
			}
		}
		
		if (shareProfit5MinOrder2Retry != null && !shareProfit5MinOrder2Retry.isEmpty()) {
			for (ShareProfitOrderLog orderLog : shareProfit5MinOrder2Retry) {
				log.info("shareProfit5MinOrder2Retry orderSn:{}", orderLog.getOrderSn());
				retryOrders.put(orderLog.getOrderSn(), "");
			}
		}
		return retryOrders;
	}
	
}
