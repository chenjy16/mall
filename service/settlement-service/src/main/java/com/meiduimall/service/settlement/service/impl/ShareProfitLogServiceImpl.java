package com.meiduimall.service.settlement.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meiduimall.service.settlement.common.ShareProfitConstants;
import com.meiduimall.service.settlement.dao.BaseMapper;
import com.meiduimall.service.settlement.model.CreateBillLog;
import com.meiduimall.service.settlement.model.ShareProfitOrderLog;
import com.meiduimall.service.settlement.service.ShareProfitLogService;

@Service
public class ShareProfitLogServiceImpl implements ShareProfitLogService {
	
	private static final Logger log=LoggerFactory.getLogger("ShareProfitLogServiceImpl.class");
	
	@Autowired
	private BaseMapper baseMapper;
	
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class)
	@Override
	public void logShareProfitOrder(ShareProfitOrderLog orderLog, String retryType, Integer retryStatus) {
		Integer newRetryStatus = retryStatus == null ? 0 : retryStatus;
		Integer flag = baseMapper.insert(orderLog, "ShareProfitOrderLogMapper.saveShareProfitOrderLog");
		if (flag <= 0) {
			log.error("ShareProfitOrderLogMapper.saveShareProfitOrderLog failed for orderSn:{}",orderLog.getOrderSn());
		}
		
		if(ShareProfitConstants.SHARE_PROFIT_RETRY_TYPE_FINAL_ROUND.equals(retryType) || ShareProfitConstants.SHARE_PROFIT_RETRY_STATUS_CODE_SUCCESS==newRetryStatus.intValue()){
			String orderSn=orderLog.getOrderSn();
			Integer flag2 = baseMapper.update(orderSn, "ShareProfitOrderLogMapper.removeRetryFlag");
			if (flag2 <= 0) {
				log.error("ShareProfitOrderLogMapper.removeRetryFlag failed for orderSn:{}",orderLog.getOrderSn());
			}
		}
		

	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class)
	public boolean removeRetryFlag(String orderSn)  {
		Integer cnt = baseMapper.update(orderSn, "ShareProfitOrderLogMapper.removeRetryFlag");
		if(cnt<=0){
			log.error("ShareProfitOrderLogMapper.removeRetryFlag failed for orderSn:{}",orderSn);
		}
		return cnt>0?true:false;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class)
	public boolean logCreateBillLog(CreateBillLog createBillLog)  {
		
		Integer flag = baseMapper.insert(createBillLog, "CreateBillLogMapper.saveCreateBillLog");
		if(flag<=0){
			log.error("CreateBillLogMapper.saveCreateBillLog failed, reason:",createBillLog.getReason());
		}
		return flag>0?true:false;
	}



}
