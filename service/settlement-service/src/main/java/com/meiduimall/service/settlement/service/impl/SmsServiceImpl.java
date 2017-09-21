package com.meiduimall.service.settlement.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.service.settlement.common.SettlementUtil;
import com.meiduimall.service.settlement.common.ShareProfitUtil;
import com.meiduimall.service.settlement.config.MyProps;
import com.meiduimall.service.settlement.model.SmsReqDTO;
import com.meiduimall.service.settlement.service.SmsService;
import com.meiduimall.service.settlement.util.ConnectionUrlUtil;

@Service
public class SmsServiceImpl implements SmsService {
	
	private static final Logger logger = LoggerFactory.getLogger(SmsServiceImpl.class);
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private MyProps myProps;
	
	
	@Override
	public boolean sendMsm(SmsReqDTO smsReqDTO) {
		boolean flag = false;
		String api = myProps.getSmsUrl() + myProps.getSmsSendMessage();
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.postForEntity(api, smsReqDTO, String.class).getBody();
		ResBodyData resBodyData = JsonUtils.jsonToBean(result, ResBodyData.class);

		if (resBodyData.getStatus() == 0) {
			flag = true;
		} else {
			logger.error(resBodyData.getMsg());
		}

		return flag;
	}

	@Override
	public boolean sendMessage(SmsReqDTO smsReqDTO) {
		boolean flag = false;
		
		//许彦雄@2017-03-21;添加消息发送的环境，以便区分来自生产环境，开发环境和测试环境的短信。
		String [] activeProfiles=environment.getActiveProfiles();
		if(SettlementUtil.contains(activeProfiles, "dev") || SettlementUtil.contains(activeProfiles, "test")){
			String params=smsReqDTO.getParams();
			if(params==null){
				params="source:"+SettlementUtil.getValues(activeProfiles, ";");
			}else{
				params+=";source:"+SettlementUtil.getValues(activeProfiles, ";");
			}
			smsReqDTO.setParams(params);
		}
		
		String resultObjStr = ConnectionUrlUtil.httpRequest(buildSendMsgUrl(smsReqDTO), ShareProfitUtil.REQUEST_METHOD_POST, null);
		ResBodyData resBodyData = JsonUtils.jsonToBean(resultObjStr, ResBodyData.class);

		if (resBodyData.getStatus() == 0) {
			flag = true;
		} else {
			logger.error(resBodyData.getMsg());
		}

		return flag;
	}
	
	public String buildSendMsgUrl(SmsReqDTO smsReqDTO) {
		String api = myProps.getSmsUrl() + myProps.getSmsSendMessage();
		String params = "clientID=" + smsReqDTO.getClientID() + "&phones=" + smsReqDTO.getPhones() + "&templateId="
				+ smsReqDTO.getTemplateId() + "&params=" + smsReqDTO.getParams();
		
		return api + "?" + params; 
	}

}
