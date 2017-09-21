package com.meiduimall.service.catalog.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.ApiException;
import com.meiduimall.service.catalog.annotation.HasMemId;
import com.meiduimall.service.catalog.constant.ServiceCatalogApiCode;
import com.meiduimall.service.catalog.entity.SysuserAccount;
import com.meiduimall.service.catalog.request.ShopProductRequest;
import com.meiduimall.service.catalog.service.ShopService;

@RestController
@RequestMapping("/mall/catalog-service/v1/shopInfo")
public class ShopController {

	private static Logger logger = LoggerFactory.getLogger(ShopController.class);

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private ShopService shopService;

	/**
	 * 获取店铺详情
	 * 
	 * @param shopId
	 *            店铺ID
	 * @param memId
	 *            会员系统ID
	 * @return 店铺详细信息
	 */
	@RequestMapping(value = "/getShopDetail")
	public ResBodyData getShopDetail(String shopId, String memId) {

		int intShopId = 0;
		try {
			intShopId = Integer.parseInt(shopId);
		} catch (NumberFormatException e) {
			logger.error("获取店铺详情: " + e);
			throw new ApiException(ServiceCatalogApiCode.REQUEST_PARAMS_ERROR,
					ServiceCatalogApiCode.getZhMsg(ServiceCatalogApiCode.REQUEST_PARAMS_ERROR));
		}
		return shopService.getShopDetail(intShopId, memId);

	}

	/**
	 * 收藏或者取消收藏店铺
	 * 
	 * @param shopId
	 *            店铺ID
	 * @param isCollect
	 *            1代表收藏，0代表取消收藏
	 * @return 收藏/取消收藏结果
	 */
	@HasMemId
	@RequestMapping(value = "/collectShop")
	public ResBodyData collectOrCancelShop(String shopId, String isCollect) {

		int intShopId = 0;
		int intIsCollect = 0;
		try {
			intShopId = Integer.parseInt(shopId);
			intIsCollect = Integer.parseInt(isCollect);
		} catch (NumberFormatException e) {
			logger.error("收藏或者取消收藏店铺: " + e);
			throw new ApiException(ServiceCatalogApiCode.REQUEST_PARAMS_ERROR,
					ServiceCatalogApiCode.getZhMsg(ServiceCatalogApiCode.REQUEST_PARAMS_ERROR));
		}
		SysuserAccount sysuserAccount = (SysuserAccount) request.getAttribute("sysuserAccount");
		return shopService.collectOrCancelShop(intShopId, sysuserAccount, intIsCollect);
	}

	/**
	 * 获取店铺商品分类
	 * 
	 * @param shopId
	 *            店铺ID
	 * @return 商品分类
	 */
	@RequestMapping(value = "/getShopCatalog")
	public ResBodyData getShopProductCatalog(String shopId) {

		int intShopId = 0;
		try {
			intShopId = Integer.parseInt(shopId);
		} catch (NumberFormatException e) {
			logger.error("获取店铺商品分类: " + e);
			throw new ApiException(ServiceCatalogApiCode.REQUEST_PARAMS_ERROR,
					ServiceCatalogApiCode.getZhMsg(ServiceCatalogApiCode.REQUEST_PARAMS_ERROR));
		}
		return shopService.getShopProductCatalog(intShopId);
	}

	/**
	 * 获取店铺的商品列表
	 * 
	 * @param param
	 *            请求参数封装对象
	 * @return 店铺商品列表
	 */
	@RequestMapping(value = "/getProductList")
	public ResBodyData getShopProductList(@Validated ShopProductRequest param) {
		return shopService.getShopProductList(param);
	}
}
