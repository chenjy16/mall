package com.meiduimall.service.catalog.service;

import com.meiduimall.core.ResBodyData;

/**
 * 商品推荐相关操作
 * 
 * @author yangchangfu
 *
 */
public interface GoodsRecommendService {

	/**
	 * 批量插入推荐商品
	 * 
	 * @param itemIds
	 *            商品ID，一个或者多个
	 * @param type
	 *            推荐类型
	 * @param optUser
	 *            操作人
	 * @param ip
	 *            操作IP
	 * @param recoLevel
	 *            推荐等级
	 * @return 状态信息
	 */
	ResBodyData insertBatchItems(int[] itemIds, int type, String optUser, String ip, int recoLevel);

	/**
	 * 根据类型，获得优先推荐的商品
	 * 
	 * @param type
	 *            推荐类型
	 * @param sourceId
	 *            客户端编号
	 * @return 推荐商品详细信息
	 */
	ResBodyData getFirstRecommendItems(int type, int sourceId);

	/**
	 * 获取所有类型，优先推荐的商品
	 * @return 推荐商品的ID和url
	 */
	ResBodyData getFirstRecommendItemsAllType();
}
