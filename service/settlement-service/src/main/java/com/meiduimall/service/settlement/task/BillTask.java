package com.meiduimall.service.settlement.task;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.meiduimall.exception.ServiceException;
import com.meiduimall.service.settlement.common.CronExpression;
import com.meiduimall.service.settlement.common.ShareProfitConstants;
import com.meiduimall.service.settlement.model.CreateBillLog;
import com.meiduimall.service.settlement.service.BillService;
import com.meiduimall.service.settlement.service.MemberService;
import com.meiduimall.service.settlement.service.O2oCallbackService;
import com.meiduimall.service.settlement.service.ShareProfitLogService;
import com.meiduimall.service.settlement.service.WaterService;
import com.meiduimall.service.settlement.util.DateUtil;
import com.meiduimall.service.settlement.vo.BilledWaterVO2Merge;


/**
 * Copyright (C), 2002-2017, 美兑壹购
 * FileName: BillTask.java
 * Author:   许彦雄
 * Date:     2017年2月20日 下午6:15:47
 * Description: 账单定时任务
 */
@Component
public class BillTask {
	
	private static final Logger log = LoggerFactory.getLogger(BillTask.class);
	
	@Autowired
	private BillService billService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private O2oCallbackService o2oCallbackService;
	
	@Autowired
	private ShareProfitLogService shareProfitLogService;
	
	@Autowired
	private WaterService waterService;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * 功能描述:  创建账单定时任务
	 * Author: 许彦 雄
	 * Date:   2017年3月14日 下午3:38:26   
	 * return  
	 * 
	 */
	//@Scheduled(cron = CronExpression.EVERY_5_MINUTE)
	@Scheduled(cron = CronExpression.TIME_ZERO_HOUR_FIVE_MIN)
	public void createBill()  {
		
		Collection<String> orderSns=null;
		try {
			orderSns=billService.createBills();
		} catch (Exception e) {
			log.error("billService.createBills()生成账单出错:{}", e);
		}
		
		if(orderSns!=null && !orderSns.isEmpty()){  //账单创建成功
			
			//通知O2O结算状态:账单创建成功
			boolean isBillStatusInformedToO2o=o2oCallbackService.informSettlementStatus(orderSns, ShareProfitConstants.O2O_SETTLEMENT_STATUS_CODE_BILL);
			
			//2.记录错误信息 到ecm_mzf_log_createbill
			if(!isBillStatusInformedToO2o){
				try {
					String reason="o2oCallbackService.informSettlementStatus()失败!账单生成时间:" + DateUtil.getCurrentTime();
					CreateBillLog orderLog=new CreateBillLog(reason,DateUtil.getCurrentTimeSec(),null);
					shareProfitLogService.logCreateBillLog(orderLog);
				} catch (Exception e) {
					log.error("shareProfitLogService.logCreateBillLog() 通知O2O结算状态STATUS_CODE_BILL errorLog!error:{}", e);
				}
			}
			// 异步送一级推荐人1%现金余额奖励到会员系统
			memberService.updateReferrerCash();
		}
	}

	/**
	 * 功能描述:  合并账单流水定时任务
	 * 	Bug:
	 *	原因：如果用户U即是商家A的区代，又是商家B的跨区代，如果在同一天时间消费者C即在A消费又在B也消费了，
	 *	那么在当天账单生成后，在当天生成的流水记录表里对于用户U会生成两条流水记录，一条为区代A的，一条为跨区代B的。
	 *	修复后：
	 *	每天的零点30分，会运行一个定时任务来合并属于当天同一个用户的流水记录金额并删掉多余的流水记录。
	 *	这样，那天的属于该用户U的流水就只有一天，但金额是从A和B分润得来的金额之和。
	 * Author: 许彦 雄
	 * Date:   2017年3月14日 下午3:38:26   
	 * return  
	 * 
	 */
	//@Scheduled(cron = CronExpression.EVERY_5_MINUTE)
	@Scheduled(cron = CronExpression.TIME_ZERO_HOUR_THIRTY_MIN)
	public void mergeBilledWaters(){
		
		//对生成账单当天的ecm_mzf_water表相同的 code,op_time,water_type记录进行分组，合并金额，删掉重复记录。主要用于修复：同一个用户既是区代又是跨区代分账后生成流水记录表时一个用户在同一时间点产生两天流水记录。
		List<BilledWaterVO2Merge> mergeWaterVOList = waterService.getBilledWatersToMerge();
		
		if(mergeWaterVOList!=null && !mergeWaterVOList.isEmpty()){
			//mergeBilledWaters
			String billTime = DateUtil.getUpDAY();//账单日期
			try{
				billService.mergeBilledWaters(mergeWaterVOList);
				log.info("合并流水表中同一用户重复流水金额成功,账单日期:{}", billTime);
			}catch(ServiceException e){
				log.error("合并流水表中同一用户重复流水金额失败,账单日期:{},异常:{}", billTime, e);
			}
		}

	}
	
}
