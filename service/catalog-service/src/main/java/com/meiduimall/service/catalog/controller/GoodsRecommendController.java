package com.meiduimall.service.catalog.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.ApiException;
import com.meiduimall.service.catalog.constant.ServiceCatalogApiCode;
import com.meiduimall.service.catalog.service.GoodsRecommendService;
import com.meiduimall.service.catalog.util.HttpHeaderTools;

/**
 * 商品推荐相关操作
 * 
 * @author yangchangfu
 *
 */
@RestController
@RequestMapping("/mall/catalog-service/v1/goodsRecommend")
public class GoodsRecommendController {

	private static Logger logger = LoggerFactory.getLogger(GoodsRecommendController.class);

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private GoodsRecommendService goodsRecommendService;

	/**
	 * 批量插入，或者单个插入推荐商品
	 * 
	 * @param itemIds
	 *            商品编号，可以传一个或者多个，不能为空
	 * @param type
	 *            推荐类型，不能为空，1代表支付成功推荐，2代表注册成功推荐
	 * @param optUser
	 *            操作人
	 * @param level
	 *            推荐等级
	 * @return 状态信息
	 */
	@RequestMapping(value = "/insertBatch")
	public ResBodyData insertBatchItems(
			@RequestParam(value = "item_ids", required = false) String itemIds, String type,
			@RequestParam(value = "opt_user", required = false) String optUser,
			@RequestParam(value = "level", required = false, defaultValue = "0") String level) {

		String ip = HttpHeaderTools.getIpAddr(request);
		logger.info("请求IP：" + ip);

		int recoType = 0;
		int recoLevel = 0;

		try {
			recoType = Integer.parseInt(type);
			recoLevel = Integer.parseInt(level);

			if (recoType != 1 && recoType != 2) {
				logger.error("批量插入，或者单个插入推荐商品，请求参数错误，type= " + recoType);
				throw new ApiException(ServiceCatalogApiCode.REQUEST_PARAMS_ERROR,
						ServiceCatalogApiCode.getZhMsg(ServiceCatalogApiCode.REQUEST_PARAMS_ERROR));
			}

		} catch (NumberFormatException e) {
			logger.error("批量插入，或者单个插入推荐商品，请求参数错误: " + e);
			throw new ApiException(ServiceCatalogApiCode.REQUEST_PARAMS_ERROR,
					ServiceCatalogApiCode.getZhMsg(ServiceCatalogApiCode.REQUEST_PARAMS_ERROR));
		}

		if (StringUtils.isBlank(itemIds)) {
			logger.error("批量插入，或者单个插入推荐商品，请求参数错误，item_ids: " + itemIds);
			throw new ApiException(ServiceCatalogApiCode.REQUEST_PARAMS_ERROR,
					ServiceCatalogApiCode.getZhMsg(ServiceCatalogApiCode.REQUEST_PARAMS_ERROR));
		} else {
			String[] strIds = itemIds.split(",");
			if (strIds != null && strIds.length > 0) {
				int[] ids = new int[strIds.length];
				try {
					for (int i = 0; i < strIds.length; i++) {
						ids[i] = Integer.parseInt(strIds[i]);
					}
				} catch (NumberFormatException e) {
					logger.error("批量插入，或者单个插入推荐商品，请求参数错误，item_ids: " + itemIds);
					throw new ApiException(ServiceCatalogApiCode.REQUEST_PARAMS_ERROR,
							ServiceCatalogApiCode.getZhMsg(ServiceCatalogApiCode.REQUEST_PARAMS_ERROR));
				}

				// 插入数据
				return goodsRecommendService.insertBatchItems(ids, recoType, optUser, ip, recoLevel);
			} else {
				logger.error("批量插入，或者单个插入推荐商品，请求参数错误，item_ids: " + itemIds);
				throw new ApiException(ServiceCatalogApiCode.REQUEST_PARAMS_ERROR,
						ServiceCatalogApiCode.getZhMsg(ServiceCatalogApiCode.REQUEST_PARAMS_ERROR));
			}
		}
	}

	/**
	 * 获取推荐商品
	 * 
	 * @param type
	 *            推荐类型，不能为空，1代表支付成功推荐，2代表注册成功推荐
	 * @param sourceId
	 *            客户端编号，1为手机，2为PC，默认是1
	 * @return 推荐商品详细信息
	 */
	@RequestMapping(value = "/getFirstRecommend")
	public ResBodyData getFirstRecommendItems(String type,
			@RequestParam(value = "sourceId", required = false, defaultValue = "1") String sourceId) {

		int recoType = 0;
		int recoSourceId = 0;
		try {
			recoType = Integer.parseInt(type);
			recoSourceId = Integer.parseInt(sourceId);
		} catch (NumberFormatException e) {
			logger.error("获取推荐商品: " + e);
			throw new ApiException(ServiceCatalogApiCode.REQUEST_PARAMS_ERROR,
					ServiceCatalogApiCode.getZhMsg(ServiceCatalogApiCode.REQUEST_PARAMS_ERROR));
		}

		return goodsRecommendService.getFirstRecommendItems(recoType, recoSourceId);
	}

	/**
	 * 获取正在推荐的商品
	 * 
	 * @return 推荐商品的ID和url
	 */
	@RequestMapping(value = "/getFirstRecommendItemId")
	public ResBodyData getFirstRecommendItemsAllType() {
		return goodsRecommendService.getFirstRecommendItemsAllType();
	}
}
