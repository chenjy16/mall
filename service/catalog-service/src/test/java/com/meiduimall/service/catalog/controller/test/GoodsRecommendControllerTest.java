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
 * FileName: GoodsRecommendControllerTest.java
 * Author:   yangchangfu
 * Description: 推荐商品测试类单元测试
 */
public class GoodsRecommendControllerTest extends BaseTest {

	/**
	 * insertBatchItems---正常测试
	 * @throws Exception
	 */
	@Test
	public void insertBatchItems_test_01() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/mall/catalog-service/v1/goodsRecommend/insertBatch")
				.param("item_ids", "33349,33310")
				.param("type", "2")
				.param("level", "200")
				.param("opt_user", "张三"))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("insertBatchItems_test_01*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	/**
	 * insertBatchItems---请求参数错误测试
	 * @throws Exception
	 */
	@Test
	public void insertBatchItems_test_02() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/mall/catalog-service/v1/goodsRecommend/insertBatch")
				.param("item_ids", "33349,33310")
				.param("type", "3")
				.param("level", "200")
				.param("opt_user", "张三"))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("insertBatchItems_test_02*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	/**
	 * insertBatchItems---请求参数错误测试
	 * @throws Exception
	 */
	@Test
	public void insertBatchItems_test_03() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/mall/catalog-service/v1/goodsRecommend/insertBatch")
				.param("item_ids", "300-600")
				.param("type", "2")
				.param("level", "200")
				.param("opt_user", "张三"))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("insertBatchItems_test_03*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	/**
	 * insertBatchItems---商品不存在
	 * @throws Exception
	 */
	@Test
	public void insertBatchItems_test_04() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/mall/catalog-service/v1/goodsRecommend/insertBatch")
				.param("item_ids", "35550")
				.param("type", "2")
				.param("level", "200")
				.param("opt_user", "张三"))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("insertBatchItems_test_04*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	/**
	 * getFirstRecommendItems---正常测试
	 * @throws Exception
	 */
	@Test
	public void getFirstRecommendItems_test_01() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/mall/catalog-service/v1/goodsRecommend/getFirstRecommend")
				.param("type", "2")
				.param("sourceId", "1"))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("getFirstRecommendItems_test_01*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	/**
	 * getFirstRecommendItems---请求参数错误测试
	 * @throws Exception
	 */
	@Test
	public void getFirstRecommendItems_test_02() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/mall/catalog-service/v1/goodsRecommend/getFirstRecommend")
				.param("sourceId", "2"))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("getFirstRecommendItems_test_02*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	/**
	 * getFirstRecommendItems---推荐类型不存在
	 * @throws Exception
	 */
	@Test
	public void getFirstRecommendItems_test_03() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/mall/catalog-service/v1/goodsRecommend/getFirstRecommend")
				.param("type", "3")
				.param("sourceId", "1"))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("getFirstRecommendItems_test_03*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	/**
	 * getFirstRecommendItemsAllType---正常测试--没有参数
	 * @throws Exception
	 */
	@Test
	public void getFirstRecommendItemsAllType_test_01() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/mall/catalog-service/v1/goodsRecommend/getFirstRecommendItemId"))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("getFirstRecommendItemsAllType_test_01*********" + result.getResponse().getContentAsString());
			}
		});
	}
}
