package com.meiduimall.service.settlement.service;

import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Maps;
import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.service.BaseTest;
import com.meiduimall.service.settlement.common.ShareProfitUtil;
import com.meiduimall.service.settlement.model.EcmMzfDraw;
import com.meiduimall.service.settlement.model.SmsReqDTO;

public class SmsServiceTest extends BaseTest {
	
	@Autowired
	private SmsService smsService;

	@Test
	public void testSendMessage() {
		SmsReqDTO smsReqDTO = new SmsReqDTO("13798431927",
				ShareProfitUtil.TEMPLATE_ID_O2O_1008,  "15111612373,6500", "");
//		smsService.sendMessage(smsReqDTO);
	}
	
	@Test
	public void test(){
		EcmMzfDraw draw = new EcmMzfDraw();
		draw.setCode("123");
		Map<String,String> map = Maps.newHashMap();
		map.put("draw", JsonUtils.beanToJson(draw));
		EcmMzfDraw ecmMzfDraw = JsonUtils.jsonToBean(map.get("draw"),EcmMzfDraw.class);
		System.out.println("=========="+ecmMzfDraw.getCode());
	}

}
