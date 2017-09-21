package com.meiduimall.service.settlement.api;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.meiduimall.service.BaseTest;

/**
 * Copyright (C), 2002-2017, 美兑壹购物
 * FileName: WaterControllerTest.java
 * Author:   guidl
 * Description: 流水管理单元测试
 */
public class WaterControllerTest extends BaseTest {
	
	/**
	 * 功能描述:  获取流水列表
	 * Author: guidl
	 */
	@Test
	public void testQueryWater() throws UnsupportedEncodingException, Exception {
		
		ResultActions results = mockMvc.perform(
				MockMvcRequestBuilders.post("/settlementservice/revenueservice/v1/querywater"))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********"+result.getResponse().getContentAsString());
			}
		});
	}

	/**
	 * 功能描述:  根据流水编号获取流水详情
	 * Author: guidl
	 */
	@Test
	public void testQueryWaterById() throws Exception {
		ResultActions results = mockMvc
				.perform(MockMvcRequestBuilders.post("/settlementservice/revenueservice/v1/querywaterbyid")
						.param("waterId", "QL41010216121426").param("waterType", "3"))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********"+result.getResponse().getContentAsString());
			}
		});
	}

	/**
	 * 功能描述:  获取推荐人推荐费
	 * Author: guidl
	 */
	@Test
	public void testGetRecMoney() throws Exception {
		ResultActions results = mockMvc
				.perform(MockMvcRequestBuilders.post("/settlementservice/revenueservice/v1/getrecmoney")
						.param("code", "440511").param("recNo", "QTG440511161209001"));
		results.andDo(new ResultHandler(){
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********"+result.getResponse().getContentAsString());
			}
		});
	}

}
