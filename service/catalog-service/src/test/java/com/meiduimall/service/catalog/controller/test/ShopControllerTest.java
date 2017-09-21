package com.meiduimall.service.catalog.controller.test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.meiduimall.service.catalog.test.BaseTest;

public class ShopControllerTest extends BaseTest {
	
	/**
	 * 测试getShopDetail---正常测试
	 * @throws Exception
	 */
	@Test
	public void getShopDetail_test_01() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/mall/catalog-service/v1/shopInfo/getShopDetail")
				.param("shop_id", "600")
				.param("mem_id", "92331632-8ce5-4865-ba09-83c362ef6b85"))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	/**
	 * 测试getShopDetail---没有mem_id测试
	 * @throws Exception
	 */
	@Test
	public void getShopDetail_test_02() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/mall/catalog-service/v1/shopInfo/getShopDetail")
				.param("shop_id", "600"))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	/**
	 * 测试getShopDetail---没有shop_id测试
	 * @throws Exception
	 */
	@Test
	public void getShopDetail_test_03() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/mall/catalog-service/v1/shopInfo/getShopDetail")
				.param("shop_id", ""))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	/**
	 * 测试collectOrCancelShop---店铺不存在测试
	 * @throws Exception
	 */
	@Test
	public void collectOrCancelShop_test_01() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/mall/catalog-service/v1/shopInfo/collectShop")
				.param("shop_id", "60001")
				.param("is_collect", "1")
				.param("mem_id", "92331632-8ce5-4865-ba09-83c362ef6b85"))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	/**
	 * 测试collectOrCancelShop---收藏测试
	 * @throws Exception
	 */
	@Test
	public void collectOrCancelShop_test_02() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/mall/catalog-service/v1/shopInfo/collectShop")
				.param("shop_id", "611")
				.param("is_collect", "1")
				.param("mem_id", "92331632-8ce5-4865-ba09-83c362ef6b85"))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	/**
	 * 测试collectOrCancelShop---取消收藏
	 * @throws Exception
	 */
	@Test
	public void collectOrCancelShop_test_03() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/mall/catalog-service/v1/shopInfo/collectShop")
				.param("shop_id", "600")
				.param("is_collect", "0")
				.param("mem_id", "92331632-8ce5-4865-ba09-83c362ef6b85"))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	/**
	 * 测试collectOrCancelShop---没有mem_id测试
	 * @throws Exception
	 */
	@Test
	public void collectOrCancelShop_test_04() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/mall/catalog-service/v1/shopInfo/collectShop")
				.param("shop_id", "600")
				.param("is_collect", "0")
				.param("mem_id", ""))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	/**
	 * 测试getShopProductCatalog---正常测试
	 * @throws Exception
	 */
	@Test
	public void getShopProductCatalog_test_01() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/mall/catalog-service/v1/shopInfo/getShopCatalog")
				.param("shop_id", "600"))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	/**
	 * 测试getShopProductCatalog---店铺不存在
	 * @throws Exception
	 */
	@Test
	public void getShopProductCatalog_test_02() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/mall/catalog-service/v1/shopInfo/getShopCatalog")
				.param("shop_id", "60011"))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	/**
	 * 测试getShopProductCatalog---没有shop_id测试
	 * @throws Exception
	 */
	@Test
	public void getShopProductCatalog_test_03() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/mall/catalog-service/v1/shopInfo/getShopCatalog")
				.param("shop_id", ""))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	/**
	 * 测试getShopProductCatalog---正常测试
	 * @throws Exception
	 */
	@Test
	public void getShopProductCatalog_test_04() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/mall/catalog-service/v1/shopInfo/getShopCatalog")
				.param("shop_id", "611"))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	/**
	 * 测试getShopProductList---没有shop_id测试
	 * @throws Exception
	 */
	@Test
	public void getShopProductList_test_01() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/mall/catalog-service/v1/shopInfo/getProductList")
				.param("shopId", ""))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	/**
	 * 测试getShopProductList---正常测试
	 * @throws Exception
	 */
	@Test
	public void getShopProductList_test_02() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/mall/catalog-service/v1/shopInfo/getProductList")
				.param("shopId", "14"))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	/**
	 * 测试getShopProductList---增加shop_cat_id
	 * @throws Exception
	 */
	@Test
	public void getShopProductList_test_03() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/mall/catalog-service/v1/shopInfo/getProductList")
				.param("shopId", "14")
				.param("shopCatId", "2432")
				)
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	/**
	 * 测试getShopProductList---增加shop_cat_id，增加排序方式
	 * @throws Exception
	 */
	@Test
	public void getShopProductList_test_04() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/mall/catalog-service/v1/shopInfo/getProductList")
				.param("shopId", "14")
				.param("orderBy", "updateTime")
				.param("column", "asc")
				)
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	/**
	 * 测试getShopProductList---shop_id错误测试
	 * @throws Exception
	 */
	@Test
	public void getShopProductList_test_05() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/mall/catalog-service/v1/shopInfo/getProductList")
				.param("shopId", "adsf"))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********" + result.getResponse().getContentAsString());
			}
		});
	}
}
