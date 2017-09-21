package com.meiduimall.service.settlement.api;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.meiduimall.service.BaseTest;

/**
 * Copyright (C), 2002-2017, 美兑壹购物
 * FileName: SettingController.java
 * Author:   guidl
 * Description: 结算服务参数配置和查询接口单元测试
 */
public class SettingControllerTest extends BaseTest {

	/**
	 * 功能描述:  更新分润比例配置接口
	 * Author: guidl
	 */
	@Test
	public void testUpdatesystemsetting() throws Exception {
		ResultActions results = mockMvc
				.perform(MockMvcRequestBuilders
						.post("/settlementservice/settingservice/v1/updatesystemsetting")
						.param("value", "3").param("state", "1").param("scode", "person_shareprofit_rate"))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********" + result.getResponse().getContentAsString());
			}
		});
	}

	/**
	 * 功能描述:  查询分润比例配置列表接口
	 * Author: guidl
	 */
	@Test
	public void testListsystemsetting() throws Exception {
		ResultActions results = mockMvc
				.perform(MockMvcRequestBuilders.post("/settlementservice/settingservice/v1/listsystemsetting")
						//可根据参数查询   .param("state", "1").param("scode", "person_shareprofit_rate")
						)
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********" + result.getResponse().getContentAsString());
			}
		});
	}

}
