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
 * FileName: OrderControllerTest.java
 * Author:   guidl
 * Description: 订单分润接口单元测试
 */
public class OrderControllerTest extends BaseTest {

	/**
	 * 功能描述:  订单分润
	 * Author: guidl
	 */
	@Test
	public void testShareProfit() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/settlementservice/orderservice/v1/shareprofit").param("orderSn", "D4405110000011704060002").param("sellerName", "440511000001")
				.param("sellerPhone", "13410229865").param("buyerName", "13798431927").param("addTime", "1491462068").param("status", "20")
				.param("payTime", "1491462068").param("payAmount", "1800.00").param("goodsAmount", "9000.00")
				.param("discount", "0.20").param("orderAmount", "1800.00").param("totalFee", "1800.00").param("storeDiscount", "7200.00")
				.param("norebate", "0.00").param("coupons", "0.00").param("brokerage", "0.00").param("discountInfo", "2")
				.param("agentNoRegion", "440511").param("agentNoPersonal", "4405111112").param("agentNoRegionS", "")
				.param("agentNameRegion", "美兑壹购物").param("agentNameRegionS", "").param("isTwoHundredArea", "0")
				.param("serviceFee", "20").param("serviceFeeCalc", "0").param("rebateAmount", "1800.00"))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********"+result.getResponse().getContentAsString());
			}
		});
	}

	/**
	 * 功能描述:  根据订单号查询订单状态
	 * Author: guidl
	 */
	@Test
	public void testQueryOrderStatus() throws Exception {
		ResultActions results = mockMvc.perform(
				MockMvcRequestBuilders.post("/settlementservice/orderservice/v1/queryorderstatus")
				.param("orderSns", "D1301020000011701030001"))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********"+result.getResponse().getContentAsString());
			}
		});
	}

	/**
	 * 功能描述:  O2O审核订单时调用此接口，将审核信息同步至结算
	 * Author: guidl
	 */
	@Test
	public void testSyncVerifyStatus() throws Exception {
		ResultActions results = mockMvc
				.perform(MockMvcRequestBuilders.post("/settlementservice/orderservice/v1/syncverifystatus")
						.param("orderSn", "D4405110000011704060002").param("verifyStatus", "1")
						.param("verifyTime", "1484192393").param("verifyName", "admin"))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********"+result.getResponse().getContentAsString());
			}
		});
	}

	/**
	 * 功能描述:  根据订单号获取订单详情或根据多个订单号查询导出订单数据
	 * Author: guidl
	 */
	@Test
	public void testQueryShareProfit() throws Exception {
		ResultActions results = mockMvc.perform(
				MockMvcRequestBuilders.post("/settlementservice/orderservice/v1/queryshareprofit")
				.param("orderSns", "D1301020000011701040001,D1301020000011701040002"))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("========*********"+result.getResponse().getContentAsString());
			}
		});
	}

	/**
	 * 功能描述:  根据区代/个代编号查询今日订单佣金和待结算金额
	 * Author: guidl
	 */
	@Test
	public void testQueryProfitByRole() throws Exception {
		ResultActions results = mockMvc.perform(
				MockMvcRequestBuilders.post("/settlementservice/orderservice/v1/queryprofitbyrole")
				.param("code", "440511").param("accountRoleType", "1"))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********"+result.getResponse().getContentAsString());
			}
		});
	}

	/**
	 * 功能描述:  获取账单流水详情
	 * Author: guidl
	 */
//	@Test
//	public void testQueryProfitByWaterByType() throws Exception {
//		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
//				.post("/settlementservice/orderservice/v1/queryprofitbywaterbytype").param("waterId", "SL44051100000116121277")
//				.param("loginType", "2").param("code", "1871477642660").param("pageNumber", "1").param("pageSize", "10"))
//				.andExpect(status().isOk());
//		
//		results.andDo(new ResultHandler() {
//			@Override
//			public void handle(MvcResult result) throws Exception {
//				System.out.println("*********"+result.getResponse().getContentAsString());
//			}
//		});
//	}

	/**
	 * 功能描述:  根据代理或商家编号查询汇总分润数据
	 * Author: guidl
	 */
	@Test
	public void testQueryTotalProfit() throws Exception {
		ResultActions results = mockMvc.perform(
				MockMvcRequestBuilders.post("/settlementservice/orderservice/v1/querytotalprofit")
				.param("codes", "440511000001,4405111112").param("billStartDate", "1480867200").param("billEndDate", "1481472000"))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********"+result.getResponse().getContentAsString());
			}
		});
	}

}
