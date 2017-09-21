package com.meiduimall.service.settlement.task;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.redis.util.RedisUtils;
import com.meiduimall.service.settlement.common.ShareProfitConstants;
import com.meiduimall.service.settlement.common.ShareProfitUtil;
import com.meiduimall.service.settlement.config.MyProps;
import com.meiduimall.service.settlement.model.EcmAgent;
import com.meiduimall.service.settlement.model.EcmMzfShareProfit;
import com.meiduimall.service.settlement.model.ShareProfitAgentLog;
import com.meiduimall.service.settlement.model.ShareProfitOrderLog;
import com.meiduimall.service.settlement.model.SmsReqDTO;
import com.meiduimall.service.settlement.service.AgentService;
import com.meiduimall.service.settlement.service.DepositService;
import com.meiduimall.service.settlement.service.MemberService;
import com.meiduimall.service.settlement.service.O2oCallbackService;
import com.meiduimall.service.settlement.service.OrderStatusService;
import com.meiduimall.service.settlement.service.ShareProfitLogService;
import com.meiduimall.service.settlement.service.SmsService;
import com.meiduimall.service.settlement.util.DateUtil;



/**
 * Copyright (C), 2002-2017, 美兑壹购物
 * FileName: AsyncTaskService.java
 * Author:   许彦雄
 * Date:     2016年12月26日 下午6:15:47
 * Description: 异步任务执行服务类
 */
@Component
public class AsyncTaskService {
	
	private static final Logger log = LoggerFactory.getLogger(AsyncTaskService.class);
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private ShareProfitLogService shareProfitLogService;
	
	@Autowired
	private OrderStatusService orderStatusService;
	
	@Autowired
	private O2oCallbackService o2oCallbackService;
	
	@Autowired
	private AgentService agentService;
	
	@Autowired
	private DepositService depositService;
	
	@Autowired
	private SmsService smsService;
	
	@Autowired
	private MyProps myProps;
	

	/**
	 * 功能描述:  异步执行送订单所获积分到会员系统
	 * Author: 许彦 雄
	 * Date:   2017年3月14日 下午3:38:26 
	 * @param  shareProfit 订单分润结果信息
	 * @param  shareProfitSource 数据来源:o2o,Cache
	 * @param  retryType 重试标识
	 */
	@Async
	public void updateScore2MemberSystem(EcmMzfShareProfit shareProfit, String shareProfitSource, String retryType) {
				
		//更新积分到会员系统
		final List<String> errors = memberService.sendScore(shareProfit);
		
		//更新积分失败时
		if(errors != null && !errors.isEmpty()){
			log.warn("订单分润后更新积分到会员系统失败，记录Log出现异常,orderSn:{},error:{}",shareProfit.getOrderSn(),String.valueOf(errors));
			orderSendScoreFail(shareProfit, shareProfitSource, retryType, errors);
			
		}else{
			orderSendScoreSuc(shareProfit, shareProfitSource, retryType, errors);
			
		}
	}


	private void orderSendScoreSuc(EcmMzfShareProfit shareProfit, String shareProfitSource, String retryType,
			final List<String> errors) {
		//更新 已分润和积分是否已更新状态到 ecm_mzf_order_status表
		boolean isUpdated = orderStatusService.updateScoreStatus(shareProfit.getOrderSn());
		if (!isUpdated) {
			log.error("OrderServiceImpl-->updateScore2MemberSystem-->orderStatus.updateScoreStatus() ecm_mzf_order_status表 更新积分状态失败!orderSn:"+shareProfit.getOrderSn());
			//发送短信通知(概率极小的情况)
			String params="积分成功送出;但送积分成功状态更新到表 ecm_mzf_order_status表失败;需要手动更新;orderSn:"+shareProfit.getOrderSn();
			
			SmsReqDTO smsReqDTO = new SmsReqDTO(myProps.getSmsPhones(), ShareProfitUtil.TEMPLATE_ID_O2O_1009, params, "");

			boolean flag = smsService.sendMessage(smsReqDTO);
			if(flag){
				log.info("积分成功送出,但送积分成功状态更新到表 ecm_mzf_order_status表失败,需要手动更新,发送短信通知成功,orderSn:{}", shareProfit.getOrderSn());
			}else{
				log.error("积分成功送出,但送积分成功状态更新到表 ecm_mzf_order_status表失败,需要手动更新,发送短信通知失败,orderSn:{}", shareProfit.getOrderSn());
			}

		}
		
		//如果是重试且重试成功
		if(ShareProfitConstants.SHARE_PROFIT_SOURCE_CACHE.equals(shareProfitSource)){
			//记录到Log表 
			ShareProfitOrderLog orderLog = new ShareProfitOrderLog(shareProfit.getOrderSn(), String.valueOf(errors), DateUtil.getCurrentTimeSec(), "重试机制中更新积分到会员系统成功!");
			orderLog.setRetryFlag(ShareProfitConstants.SHARE_PROFIT_RETRY_FLAG_NO);
			orderLog.setRetryStatus(ShareProfitConstants.SHARE_PROFIT_RETRY_STATUS_CODE_SUCCESS);
			orderLog.setRetryTime(DateUtil.getCurrentTimeSec());
			
			shareProfitLogService.logShareProfitOrder(orderLog,retryType,ShareProfitConstants.SHARE_PROFIT_RETRY_STATUS_CODE_SUCCESS);
			
			//移除redis 缓存的分润数据
			RedisUtils.del(ShareProfitConstants.REDIS_KEY_PREFIX_ORDER + shareProfit.getOrderSn());
		}

		//回调O2O接口，通知积分更新成功
		boolean isSuccess = o2oCallbackService.informSettlementStatus(ImmutableList.of(shareProfit.getOrderSn()), ShareProfitConstants.O2O_SETTLEMENT_STATUS_CODE_SCORE);
		
		if(!isSuccess){
			ShareProfitOrderLog orderLog = new ShareProfitOrderLog(shareProfit.getOrderSn(), "通知O2O订单积分已更新到会员系统不成功!orderSn:" + shareProfit.getOrderSn(), DateUtil.getCurrentTimeSec(), null);
			shareProfitLogService.logShareProfitOrder(orderLog,null,null);
		}
	}


	private void orderSendScoreFail(EcmMzfShareProfit shareProfit, String shareProfitSource, String retryType,
			final List<String> errors) {
		//将shareProfit 数据放到redis 缓存 
		if(ShareProfitConstants.SHARE_PROFIT_SOURCE_O2O.equals(shareProfitSource)){
			RedisUtils.set(ShareProfitConstants.REDIS_KEY_PREFIX_ORDER + shareProfit.getOrderSn(), JsonUtils.beanToJson(shareProfit));
		}
		
		//记录到Log表  
		ShareProfitOrderLog orderLog = new ShareProfitOrderLog(shareProfit.getOrderSn(), String.valueOf(errors), DateUtil.getCurrentTimeSec(), "更新积分到会员系统失败!");
		orderLog.setRetryFlag(ShareProfitConstants.SHARE_PROFIT_RETRY_FLAG_YES);
		
		if(ShareProfitConstants.SHARE_PROFIT_SOURCE_CACHE.equals(shareProfitSource)){
			orderLog.setRetryFlag(ShareProfitConstants.SHARE_PROFIT_RETRY_FLAG_NO);
			orderLog.setRetryStatus(ShareProfitConstants.SHARE_PROFIT_RETRY_STATUS_CODE_FAIL);
			orderLog.setRetryTime(DateUtil.getCurrentTimeSec());
		}
		
		//移除redis 缓存的分润数据 
		if(ShareProfitConstants.SHARE_PROFIT_RETRY_TYPE_FINAL_ROUND.equals(retryType)){
			RedisUtils.del(ShareProfitConstants.REDIS_KEY_PREFIX_ORDER + shareProfit.getOrderSn());
			//三次重试仍然失败，发邮件或短信通知,重试机制终止,需要手动触发重试机制 
			String params = "经过三次重试后;更新积分到会员系统仍然失败;重试机制终止;需要手动触发重试机制或手动更新积分到会员系统;orderSn:" + shareProfit.getOrderSn();
			
			SmsReqDTO smsReqDTO = new SmsReqDTO(myProps.getSmsPhones(), ShareProfitUtil.TEMPLATE_ID_O2O_1009, params, "");

			boolean flag = smsService.sendMessage(smsReqDTO);
			if(flag){
				log.info("经过三次重试后,更新积分到会员系统仍然失败。发送短信通知成功,orderSn:{}",shareProfit.getOrderSn());
			}else{
				log.error("经过三次重试后,更新积分到会员系统仍然失败。发送短信通知失败,orderSn:{}",shareProfit.getOrderSn());
			}
		}
		
		//插入异常日志
		shareProfitLogService.logShareProfitOrder(orderLog, retryType, null);
	}
		

	/**
	 * 功能描述:  异步执行送新个代奖励积分到会员系统
	 * Author: guidl
	 * Date:   2017年3月14日 下午3:38:26 
	 * @param ecmAgent 个代相关信息
	 * @param score 积分
	 * @param dataSource 数据来源:o2o,Cache
	 * @param retryType 重试标识
	 */
	@Async
	public void updateScore(EcmAgent ecmAgent, int score, String dataSource, String retryType) {
		
		log.info("更新积分开始时间:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		
		try {
			if(!Strings.isNullOrEmpty(ecmAgent.getBindPhone()) && score > 0){//手机号码和积分都不为空，则更新加盟个代积分
				//积分流水号
				String scoreFlow = "EGW" + ecmAgent.getAgentNo() + System.currentTimeMillis();
				
				//调用积分接口 更新积分 
				boolean result = memberService.addConsumePoints(ecmAgent.getBindPhone(), String.valueOf(score), ShareProfitConstants.DATA_SOURCE_O2O, scoreFlow);

				if(result){
					//修改分润表中积分状态为已更新
					agentSendScoreSuc(ecmAgent, score, dataSource);
				}else{
					log.error("手机号码为:"+ecmAgent.getBindPhone()+"更新积分失败");
					//积分更新失败时 插入失败日志 将代理信息存入redis
					agentSendScoreFail(ecmAgent, score, dataSource, retryType);
				}
			}
		} catch (ServiceException e) {
			log.error("更新积分：{}", e);
			throw e;
		}
	}


	private void agentSendScoreFail(EcmAgent ecmAgent, int score, String dataSource, String retryType) {
		//送积分失败 插入异常记录
		int time = DateUtil.getCurrentTimeSec();
		Timestamp timestamp = Timestamp.valueOf(DateUtil.timeStamp2Date(String.valueOf(time), DateUtil.YYYY_MM_DD_HH_MM_SS));
		ShareProfitAgentLog agentLog = new ShareProfitAgentLog();
		agentLog.setAgentNo(ecmAgent.getAgentNo());
		agentLog.setPhone(ecmAgent.getBindPhone());
		agentLog.setReason("手机号码为:"+ecmAgent.getBindPhone()+"更新积分失败");
		agentLog.setCreatedDate(time);
		agentLog.setUpdatedDate(timestamp);
		agentLog.setRemark("更新积分到会员系统失败");
		agentLog.setRetryFlag(ShareProfitConstants.SHARE_PROFIT_RETRY_FLAG_YES);//重试标识:1需要重试,0不需要重试
		
		//o2o调用代理保证金分润方法 执行更新积分环节失败时 将代理数据放入redis缓存中 用于定时器执行重试机制
		if(ShareProfitConstants.SHARE_PROFIT_SOURCE_O2O.equals(dataSource)){
			RedisUtils.set(ShareProfitConstants.REDIS_KEY_PRIFIX_AGENT + ecmAgent.getAgentNo(), JsonUtils.beanToJson(ecmAgent));
		}
		
		//定时任务从缓存获取数据 执行重试机制出错时 插入异常日志
		if(ShareProfitConstants.SHARE_PROFIT_SOURCE_CACHE.equals(dataSource)){
			agentLog.setRetryFlag(ShareProfitConstants.SHARE_PROFIT_RETRY_FLAG_NO);//重试标识:1需要重试,0不需要重试
			agentLog.setRetryStatus(ShareProfitConstants.SHARE_PROFIT_RETRY_STATUS_CODE_FAIL);//重试状态 :1成功,0失败
			agentLog.setRetryTime(time);
			agentLog.setRemark("重试更新积分到会员失败");
		}
		
		//重试机制有三次机会  如果是最后一次  则移除redis缓存数据
		if(ShareProfitConstants.SHARE_PROFIT_RETRY_TYPE_FINAL_ROUND.equals(retryType)){
			
			RedisUtils.del(ShareProfitConstants.REDIS_KEY_PRIFIX_AGENT + ecmAgent.getAgentNo());
			
			//三次重试仍然失败，发邮件或短信通知,重试机制终止,需要手动触发重试机制
			agentLog.setRemark("三次自动重试更新积分到会员失败，需手动更新积分到会员");
			
			SmsReqDTO smsReqDTO = new SmsReqDTO(myProps.getSmsPhones(),
					ShareProfitUtil.TEMPLATE_ID_O2O_1008, ecmAgent.getBindPhone() + "," + score, "");
			boolean flag = smsService.sendMessage(smsReqDTO);
			if(flag){
				log.info("新个代编号为{}手机号码为{}送{}积分失败，提醒短信发送成功", ecmAgent.getAgentNo(), ecmAgent.getBindPhone(), score);
			}else{
				log.error("新个代编号为{}手机号码为{}送{}积分失败，提醒短信发送失败", ecmAgent.getAgentNo(), ecmAgent.getBindPhone(), score);
			}
		}
		
		//插入异常日志(重新开启新事务)
		depositService.shareProfitAgentLog(agentLog, retryType);
		log.info("个代送积分异常日志参数：{}", agentLog.toString());
	}


	private void agentSendScoreSuc(EcmAgent ecmAgent, int score, String dataSource) {
		int flag = agentService.updateScoreStatusByCode(ecmAgent.getId(), ecmAgent.getAgentNo(), score);
		if(flag > 0){
			//重试机制  分润成功 送积分成功时 修改异常日志记录状态，并删除缓存数据
			if(ShareProfitConstants.SHARE_PROFIT_SOURCE_CACHE.equals(dataSource)){
				
				//修改异常日志记录状态
				agentService.updateStatusFlag(ecmAgent.getAgentNo());
				
				//重试成功 删除缓存数据
				RedisUtils.del(ShareProfitConstants.REDIS_KEY_PRIFIX_AGENT + ecmAgent.getAgentNo());
			}
			log.info("代理编码为：{}手机号码为：{}的积分更新成功，其状态已修改为 '已更新'{}", ecmAgent.getAgentNo(), ecmAgent.getBindPhone(), flag);
		}else{
			log.error("代理编码为：{}手机号码为：{}修改代理流水表积分 '状态' 失败", ecmAgent.getAgentNo(), ecmAgent.getBindPhone());
		}
	}

}
