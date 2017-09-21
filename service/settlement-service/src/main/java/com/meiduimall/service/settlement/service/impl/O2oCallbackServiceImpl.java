package com.meiduimall.service.settlement.service.impl;


import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Joiner;
import com.meiduimall.core.BaseApiCode;
import com.meiduimall.core.Constants;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.service.SettlementApiCode;
import com.meiduimall.service.settlement.common.ShareProfitUtil;
import com.meiduimall.service.settlement.config.MyProps;
import com.meiduimall.service.settlement.model.EcmAgent;
import com.meiduimall.service.settlement.model.SmsReqDTO;
import com.meiduimall.service.settlement.service.O2oCallbackService;
import com.meiduimall.service.settlement.service.SmsService;
import com.meiduimall.service.settlement.util.ConnectionUrlUtil;
import com.meiduimall.service.settlement.util.DateUtil;


@Service
public class O2oCallbackServiceImpl implements O2oCallbackService{
	
	private static final Logger log = LoggerFactory.getLogger(O2oCallbackServiceImpl.class);
	
	@Autowired
	private SmsService smsService;
	
	@Autowired
	private MyProps myProps;

	@Override
	public boolean informSettlementStatus(Collection<String> orderSns, Integer statusCode) {
		boolean isSuccess = true;
		
		String statusCodeMsg = ShareProfitUtil.O2O_SETTLEMENT_STATUS_CODE_MAP.get(statusCode) == null ? "" : ShareProfitUtil.O2O_SETTLEMENT_STATUS_CODE_MAP.get(statusCode);
			
		String resultObjStr = ConnectionUrlUtil.httpRequest(buildUrl4InformSettlementStatus(orderSns,statusCode), ShareProfitUtil.REQUEST_METHOD_POST, null);
			
		ResBodyData resultObj = JsonUtils.jsonToBean(resultObjStr, ResBodyData.class);
		
		if(resultObj.getStatus() == 0){
			log.info("通知订单结算状态给O2O成功!orderSns:{},statusCode:{}",Joiner.on(Constants.SEPARATOR_COMMA).skipNulls().join(orderSns),statusCodeMsg);
		}else{
			isSuccess=false;
			log.error("通知订单结算状态给O2O失败!orderSns:{},statusCode:{}",Joiner.on(Constants.SEPARATOR_COMMA).skipNulls().join(orderSns),statusCodeMsg);
			log.error("通知订单结算状态给O2O失败!orderSns:{},失败信息:{}",Joiner.on(Constants.SEPARATOR_COMMA).skipNulls().join(orderSns),resultObj.getMsg());
		}
		
		//1.发送邮件或短信,修复后需要O2O手动更新订单状态
		if(!isSuccess){
			//注意，params中,逗号是参数分隔符,用于将分割后的参数填充到短信模板。
			String params = "通知订单结算状态给O2O失败;status_code:" + statusCodeMsg;
			SmsReqDTO smsReqDTO = new SmsReqDTO(myProps.getSmsPhones(), ShareProfitUtil.TEMPLATE_ID_O2O_1009, params, "");

			boolean flag = smsService.sendMessage(smsReqDTO);
			if(flag){
				log.info("发送短信通知订单结算状态给O2O成功,status_code:{}",statusCodeMsg);
			}else{
				log.error("发送短信通知订单结算状态给O2O失败,status_code:{}",statusCodeMsg);
			}
		}

		return isSuccess;
	}
	
	
	private String buildUrl4InformSettlementStatus(Collection<String> orderSns, Integer statusCode) {
		
		String apiUrl = myProps.getO2oUrl();
		String apiPath = myProps.getO2oSaveOrderBillStatu();
		String apiKey = myProps.getO2oApiKey();
		
		String orderSnsStr=Joiner.on(Constants.SEPARATOR_COMMA).skipNulls().join(orderSns);
		String params = "?orderSns=" + orderSnsStr + "&statusCode=" + statusCode  + "&apiKey="+apiKey;
		
		return apiUrl+apiPath+params;
	}


	@Override
	public String addProxyFee(EcmAgent areaAgent, double amount) {
		String resultObjStr = ConnectionUrlUtil.httpRequest(buildUrl4AddProxyFee(areaAgent, amount), ShareProfitUtil.REQUEST_METHOD_POST, null);

		ResBodyData resultObj = JsonUtils.jsonToBean(resultObjStr, ResBodyData.class);

		if (resultObj.getStatus() == 0) {
			log.info("回调o2o，更新余款，抵扣保证金插入缴费记录成功");
			return (String) resultObj.getData();
		} else {
			log.error("回调o2o更新余款、抵扣保证金插入缴费记录失败,agentNo:{}", areaAgent.getAddAgentNo());
			throw new ServiceException(SettlementApiCode.CALLBACK_O2O_UPD_BALANCE_FAILD, BaseApiCode.getZhMsg(SettlementApiCode.CALLBACK_O2O_UPD_BALANCE_FAILD));
		}

	}
	
	
	/**
	 * 组装传递参数
	 * @param areaAgent
	 * @param amount
	 * @return
	 */
	private String buildUrl4AddProxyFee(EcmAgent areaAgent, double amount) {
		
		String apiUrl = myProps.getO2oUrl();
		String apiPath = myProps.getO2oAddProxyFee();
		String apiKey = myProps.getO2oApiKey();
		
		int addTime = DateUtil.getCurrentTimeSec();
		
		String params = "?agentNo=" + areaAgent.getAddAgentNo() + "&amount=" + amount + "&operateUser=0"
				+ "&verifyStatus=VERIFYED" + "&verifyTime="
				+ addTime + "&verifyUser=0" + "&addTime="
				+ addTime + "&type=1" + "&apiKey=" + apiKey;
		return apiUrl + apiPath + params;
	}
	

}
