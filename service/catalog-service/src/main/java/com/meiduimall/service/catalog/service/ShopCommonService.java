package com.meiduimall.service.catalog.service;

import com.meiduimall.service.catalog.result.JsonItemDetailResultShopData;

public interface ShopCommonService {

	/**
	 * 获取店铺详情
	 * 
	 * @param shopId
	 *            店铺ID
	 * @param memId
	 *            会员ID
	 * @return 店铺详情
	 */
	JsonItemDetailResultShopData getJsonItemDetailResult_ShopData(Integer shopId, String memId);
}
