package com.meiduimall.service.catalog.controller.test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.meiduimall.service.catalog.test.BaseTest;

/**
 * Copyright (C), 2002-2017, 美兑壹购物 
 * FileName: GoodsDetailControllerTest.java
 * Author: yangchangfu 
 * Description: 商品信息查询测试类单元测试
 */
public class GoodsDetailControllerTest extends BaseTest {

	/**
	 * 测试checkItemIsExist---正常测试
	 * 
	 * @throws Exception
	 */
	@Test
	public void checkItemIsExist_test_01() throws Exception {
		ResultActions results = mockMvc.perform(
				MockMvcRequestBuilders.post("/mall/catalog-service/v1/goodsDetail/isExist").param("item_id", "33349"))
				.andExpect(status().isOk());

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("checkItemIsExist_test_01*********" + result.getResponse().getContentAsString());
			}
		});
	}

	/**
	 * 测试checkItemIsExist---参数错误测试
	 * 
	 * @throws Exception
	 */
	@Test
	public void checkItemIsExist_test_02() throws Exception {
		ResultActions results = mockMvc.perform(
				MockMvcRequestBuilders.post("/mall/catalog-service/v1/goodsDetail/isExist").param("item_id", "ada"))
				.andExpect(status().isOk());

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("checkItemIsExist_test_02*********" + result.getResponse().getContentAsString());
			}
		});
	}

	/**
	 * 测试checkItemIsExist---商品不存在
	 * 
	 * @throws Exception
	 */
	@Test
	public void checkItemIsExist_test_03() throws Exception {
		ResultActions results = mockMvc.perform(
				MockMvcRequestBuilders.post("/mall/catalog-service/v1/goodsDetail/isExist").param("item_id", "35550"))
				.andExpect(status().isOk());

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("checkItemIsExist_test_03*********" + result.getResponse().getContentAsString());
			}
		});
	}

	/**
	 * getItemDetail----正常测试
	 * 
	 * @throws Exception
	 */
	@Test
	public void getItemDetail_test_01() throws Exception {
		ResultActions results = mockMvc
				.perform(MockMvcRequestBuilders.post("/mall/catalog-service/v1/goodsDetail/getItem")
						.param("memId", "bbdb1b5b-a5ec-4db7-9c69-929c100b2587").param("itemId", "33349"))
				.andExpect(status().isOk());

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("getItemDetail_test_01*********" + result.getResponse().getContentAsString());
			}
		});
	}

	/**
	 * getItemDetail----参数错误测试
	 * 
	 * @throws Exception
	 */
	@Test
	public void getItemDetail_test_02() throws Exception {
		ResultActions results = mockMvc
				.perform(MockMvcRequestBuilders.post("/mall/catalog-service/v1/goodsDetail/getItem")
						.param("memId", "bbdb1b5b-a5ec-4db7-9c69-929c100b2587").param("itemId", "adsf"))
				.andExpect(status().isOk());

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("getItemDetail_test_02*********" + result.getResponse().getContentAsString());
			}
		});
	}

	/**
	 * getItemDetail----商品不存在
	 * 
	 * @throws Exception
	 */
	@Test
	public void getItemDetail_test_03() throws Exception {
		ResultActions results = mockMvc
				.perform(MockMvcRequestBuilders.post("/mall/catalog-service/v1/goodsDetail/getItem")
						.param("memId", "bbdb1b5b-a5ec-4db7-9c69-929c100b2587").param("itemId", "35550"))
				.andExpect(status().isOk());

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("getItemDetail_test_03*********" + result.getResponse().getContentAsString());
			}
		});
	}
}
