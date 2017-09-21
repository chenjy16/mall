package com.meiduimall.service.catalog.service;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.service.catalog.entity.SysuserAccount;
import com.meiduimall.service.catalog.request.ShopProductRequest;

/**
 * 店铺相关
 * 
 * @author yangchang
 *
 */
public interface ShopService {

	/**
	 * 查询店铺详情
	 * 
	 * @param shopId
	 *            店铺ID
	 * @param memId
	 *            会员系统ID
	 * @return 店铺详细信息
	 */
	ResBodyData getShopDetail(Integer shopId, String memId);

	/**
	 * 收藏或者取消收藏店铺
	 * 
	 * @param shopId
	 *            店铺ID
	 * @param sysuserAccount
	 *            用户基本账户信息
	 * @param isCollect
	 *            1表示收藏，0表示取消收藏
	 * @return 收藏/取消收藏结果
	 */
	ResBodyData collectOrCancelShop(Integer shopId, SysuserAccount sysuserAccount, int isCollect);

	/**
	 * 查询店铺的商品分类
	 * 
	 * @param shopId
	 *            店铺ID
	 * @return 商品分类
	 */
	ResBodyData getShopProductCatalog(Integer shopId);

	/**
	 * 查询店铺商品列表
	 * 
	 * @param param
	 *            请求参数封装对象
	 * @return 店铺商品列表
	 */
	ResBodyData getShopProductList(ShopProductRequest param);
}
