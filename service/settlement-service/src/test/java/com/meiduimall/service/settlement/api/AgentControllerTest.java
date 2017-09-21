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
 * FileName: AgentControllerTest.java
 * Author:   guidl
 * Description: 个代保证金分润单元测试
 */
public class AgentControllerTest extends BaseTest {
	
	/**
	 * 功能描述:  保证金分润
	 * Author: guidl
	 */
	@Test
	public void testShareDeposit() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/settlementservice/agentservice/v1/sharedeposit")
				.param("id", "115").param("agentNo", "9999991112").param("bindPhone", "13410899920")
				.param("userName", "大区个代小天天").param("misUserId", "1gw_13410899920").param("companyName", "美兑o2o115")
				.param("cashDeposit", "6500").param("depositLeftAmount", "0").param("recommenderCode", "999999")
				.param("recommenderName", "大区联系人").param("recommenderPhone", "18999999999").param("recNo", "QTG999999161212001")
				.param("recType", "1").param("addAgentNo", "999999").param("addBindPhone", "18999999999")
				.param("addCompanyName", "大区").param("addDepositLeftAmount", "300"))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	/**
	 * 功能描述:  新商家送积分
	 * Author: guidl
	 */
//	@Test
//	public void testSendScore() throws Exception {
//		ResultActions results = mockMvc
//				.perform(MockMvcRequestBuilders.post("/settlementservice/agentservice/v1/sendscore")
//						.param("username", "15816881611").param("storeNo", "123456"))
//				.andExpect(status().isOk());
//
//		results.andDo(new ResultHandler() {
//			@Override
//			public void handle(MvcResult result) throws Exception {
//				System.out.println("*********" + result.getResponse().getContentAsString());
//			}
//		});
//	}

	/**
	 * 功能描述:  创建区代、个代和商家账号
	 * Author: guidl
	 */
	@Test
	public void testCreateAccoutBalance() throws Exception {
		ResultActions results = mockMvc
				.perform(MockMvcRequestBuilders.post("/settlementservice/agentservice/v1/createaccoutbalance")
						.param("accountRoleType", "1").param("code", "123"))
				.andExpect(status().isOk());

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********" + result.getResponse().getContentAsString());
			}
		});
	}

}
