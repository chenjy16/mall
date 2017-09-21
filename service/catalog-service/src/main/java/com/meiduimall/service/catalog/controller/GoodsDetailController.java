package com.meiduimall.service.catalog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.ApiException;
import com.meiduimall.service.catalog.constant.ServiceCatalogApiCode;
import com.meiduimall.service.catalog.service.GoodsDetailService;

/**
 * 商品详情相关操作
 * 
 * @author yangchangfu
 *
 */
@RestController
@RequestMapping("/mall/catalog-service/v1/goodsDetail")
public class GoodsDetailController {

	private static Logger logger = LoggerFactory.getLogger(GoodsDetailController.class);

	@Autowired
	private GoodsDetailService goodsDetailService;

	/**
	 * 根据商品编号，查询商品是否存在
	 * 
	 * @param itemId
	 *            商品编号，必须
	 * @return 查询结果和url
	 */
	@RequestMapping(value = "/isExist")
	public ResBodyData checkItemIsExist(
			@RequestParam(value = "item_id", required = false) String itemId) {
		int id = 0;
		try {
			id = Integer.parseInt(itemId);
		} catch (NumberFormatException e) {
			logger.error("根据商品编号，查询商品是否存在: " + e);
			throw new ApiException(ServiceCatalogApiCode.REQUEST_PARAMS_ERROR,
					ServiceCatalogApiCode.getZhMsg(ServiceCatalogApiCode.REQUEST_PARAMS_ERROR));
		}
		return goodsDetailService.checkItemIsExistById(id);
	}

	/**
	 * 根据商品编号，查询商品详情
	 * 
	 * @param memId
	 *            会员系统ID
	 * @param itemId
	 *            商品编号，必须
	 * @return 详情信息
	 */
	@RequestMapping(value = "/getItem")
	public ResBodyData getItemDetail(String memId, String itemId) {
		int id = 0;
		try {
			id = Integer.parseInt(itemId);
		} catch (NumberFormatException e) {
			logger.error("根据商品编号，查询商品详情: " + e);
			throw new ApiException(ServiceCatalogApiCode.REQUEST_PARAMS_ERROR,
					ServiceCatalogApiCode.getZhMsg(ServiceCatalogApiCode.REQUEST_PARAMS_ERROR));
		}
		return goodsDetailService.getItemDetailById(memId, id);
	}
}
