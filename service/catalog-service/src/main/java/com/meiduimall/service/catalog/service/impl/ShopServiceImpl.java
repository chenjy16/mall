package com.meiduimall.service.catalog.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.service.catalog.constant.ServiceCatalogApiCode;
import com.meiduimall.service.catalog.dao.BaseDao;
import com.meiduimall.service.catalog.entity.SysshopShop;
import com.meiduimall.service.catalog.entity.SysshopShopCat;
import com.meiduimall.service.catalog.entity.SysuserAccount;
import com.meiduimall.service.catalog.entity.SysuserShopFav;
import com.meiduimall.service.catalog.request.ShopProductRequest;
import com.meiduimall.service.catalog.result.ChildShopCat;
import com.meiduimall.service.catalog.result.JsonItemDetailResultShopData;
import com.meiduimall.service.catalog.result.ParentShopCat;
import com.meiduimall.service.catalog.result.ShopCatResult;
import com.meiduimall.service.catalog.result.ShopGoodsDetailResult;
import com.meiduimall.service.catalog.result.ShopProductList;
import com.meiduimall.service.catalog.service.ShopCommonService;
import com.meiduimall.service.catalog.service.ShopService;

@Service
public class ShopServiceImpl implements ShopService {

	private static Logger logger = LoggerFactory.getLogger(ShopServiceImpl.class);

	@Autowired
	private BaseDao baseDao;

	@Autowired
	private ShopCommonService shopCommonService;

	@Override
	public ResBodyData getShopDetail(Integer shopId, String memId) {
		logger.info("根据商品编号，获取店铺详情，ID=" + shopId);

		JsonItemDetailResultShopData shopData = shopCommonService.getJsonItemDetailResult_ShopData(shopId, memId);

		if (shopData == null) {
			/** 没有这个店铺 */
			throw new ServiceException(ServiceCatalogApiCode.NO_THIS_SHOP,
					ServiceCatalogApiCode.getZhMsg(ServiceCatalogApiCode.NO_THIS_SHOP));
		}

		ResBodyData result = new ResBodyData();// 最终返回的数据对象
		result.setData(shopData);
		result.setStatus(ServiceCatalogApiCode.SUCCESS);
		result.setMsg(ServiceCatalogApiCode.getZhMsg(ServiceCatalogApiCode.REQUEST_SUCCESS));
		return result;
	}

	@Override
	public ResBodyData collectOrCancelShop(Integer shopId, SysuserAccount sysuserAccount, int isCollect) {

		if (isCollect == 1) {
			logger.info("收藏藏店铺，店铺ID：" + shopId);
		} else {
			logger.info("取消收藏店铺，店铺ID：" + shopId);
		}

		ResBodyData result = new ResBodyData();// 最终返回的数据对象

		// 获取user_id
		if (sysuserAccount == null) {
			throw new ServiceException(ServiceCatalogApiCode.NO_LOGIN,
					ServiceCatalogApiCode.getZhMsg(ServiceCatalogApiCode.NO_LOGIN));
		}
		Integer userId = sysuserAccount.getUserId();

		if (isCollect == 1) {
			/** 收藏店铺 **/
			// 1.首先判断该用户是否收藏了该店铺--如果已经收藏了，直接提示收藏成功
			SysuserShopFav queryShopFav = new SysuserShopFav();
			queryShopFav.setUserId(userId);
			queryShopFav.setShopId(shopId);
			int collectCount = baseDao.selectOne(queryShopFav, "SysuserShopFavMapper.countByShopIdAndUserId");
			if (collectCount > 0) {
				result.setStatus(ServiceCatalogApiCode.SUCCESS);
				result.setMsg(ServiceCatalogApiCode.getZhMsg(ServiceCatalogApiCode.COLLECT_SUCCESS));
				result.setData(JsonUtils.getInstance().createObjectNode());
				return result;
			}

			// 2.查询店铺信息
			List<SysshopShop> shopList = baseDao.selectList(shopId, "SysshopShopMapper.selectByPrimaryKey");

			if (shopList == null || shopList.isEmpty()) {
				// 没有这个店铺
				throw new ServiceException(ServiceCatalogApiCode.NO_THIS_SHOP,
						ServiceCatalogApiCode.getZhMsg(ServiceCatalogApiCode.NO_THIS_SHOP));
			}
			SysshopShop sysshopShop = shopList.get(0);

			// 3.保存数据
			SysuserShopFav shopFav = new SysuserShopFav();

			// 时间只保存到秒
			shopFav.setCreateTime((int) (System.currentTimeMillis() / 1000l));
			shopFav.setShopId(shopId);
			shopFav.setShopLogo(sysshopShop.getShopLogo());
			shopFav.setShopName(sysshopShop.getShopName());
			shopFav.setUserId(userId);
			Integer insertCount = baseDao.insert(shopFav, "SysuserShopFavMapper.insertSelective");
			if (insertCount > 0) {
				result.setStatus(ServiceCatalogApiCode.SUCCESS);
				result.setMsg(ServiceCatalogApiCode.getZhMsg(ServiceCatalogApiCode.COLLECT_SUCCESS));
				result.setData(JsonUtils.getInstance().createObjectNode());
			} else {
				throw new ServiceException(ServiceCatalogApiCode.COLLECT_FAIL,
						ServiceCatalogApiCode.getZhMsg(ServiceCatalogApiCode.COLLECT_FAIL));
			}

		} else {
			/** 取消收藏 **/
			SysuserShopFav queryShopFav = new SysuserShopFav();
			queryShopFav.setUserId(userId);
			queryShopFav.setShopId(shopId);
			Integer deleteCount = baseDao.delete(queryShopFav, "SysuserShopFavMapper.deleteByShopIdAndUserId");
			if (deleteCount > 0) {
				result.setStatus(ServiceCatalogApiCode.SUCCESS);
				result.setMsg(ServiceCatalogApiCode.getZhMsg(ServiceCatalogApiCode.CANCEL_COLLECT_SUCCESS));
				result.setData(JsonUtils.getInstance().createObjectNode());
			} else {
				throw new ServiceException(ServiceCatalogApiCode.CANCEL_COLLECT_FAIL,
						ServiceCatalogApiCode.getZhMsg(ServiceCatalogApiCode.CANCEL_COLLECT_FAIL));
			}
		}
		return result;
	}

	@Override
	public ResBodyData getShopProductCatalog(Integer shopId) {
		logger.info("获取店铺自定义商品分类，shop_id： " + shopId);

		List<SysshopShopCat> list = baseDao.selectList(shopId, "SysshopShopCatMapper.selectByShopId");

		if (list != null && !list.isEmpty()) {
			ShopCatResult shopCatResult = new ShopCatResult();
			List<ParentShopCat> results = new ArrayList<ParentShopCat>();

			// 1.先得到所有的parent_id
			for (SysshopShopCat cat : list) {
				Integer parentId = cat.getParentId();
				if (parentId != null && parentId.intValue() == 0) {
					ParentShopCat parentShopCat = new ParentShopCat();
					parentShopCat.setCatId(cat.getCatId());
					parentShopCat.setCatName(cat.getCatName());
					parentShopCat.setChildShopCat(new ArrayList<ChildShopCat>());
					results.add(parentShopCat);
				}
			}

			// 2.将所有的cat_id放到对应的parent_id集合中
			out: for (SysshopShopCat cat : list) {
				Integer parentId = cat.getParentId();
				if (parentId != null && parentId.intValue() != 0) {
					for (ParentShopCat parent : results) {
						Integer cat_id = parent.getCatId();
						if (cat_id != null && cat_id.intValue() == parentId.intValue()) {
							ChildShopCat childShopCat = new ChildShopCat();
							childShopCat.setCatId(cat.getCatId());
							childShopCat.setCatName(cat.getCatName());
							parent.getChildShopCat().add(childShopCat);
							continue out;
						}
					}
				}
			}
			shopCatResult.setResults(results);

			ResBodyData result = new ResBodyData();// 最终返回的数据对象
			result.setData(shopCatResult);
			result.setStatus(ServiceCatalogApiCode.SUCCESS);
			result.setMsg(ServiceCatalogApiCode.getZhMsg(ServiceCatalogApiCode.REQUEST_SUCCESS));
			return result;
		} else {
			throw new ServiceException(ServiceCatalogApiCode.NONE_DATA,
					ServiceCatalogApiCode.getZhMsg(ServiceCatalogApiCode.NONE_DATA));
		}
	}

	@Override
	public ResBodyData getShopProductList(ShopProductRequest param) {
		logger.info("获取店铺下的所有商品，shopId： " + param.getShopId());

		ShopProductList data = new ShopProductList();
		// 设置排序字段
		if (StringUtils.isBlank(param.getSortBy())) {
			param.setSortBy("store");
		}
		switch (param.getSortBy()) {
		case "updateTime":// 按修改时间排序
			param.setSortBy("sysitem_item_status.list_time");
			break;
		case "price":// 按价格排序
			param.setSortBy("sysitem_item.price");
			break;
		case "point":// 按积分排序
			param.setSortBy("sysitem_item.point");
			break;
		default:// 默认：按销量排序
			param.setSortBy("sysitem_item_count.sold_quantity");
			break;
		}

		// 设置排序规则--升序或者降序
		if (StringUtils.isBlank(param.getColumn())) {
			param.setColumn("desc");
		}
		switch (param.getColumn()) {
		case "asc":// 升序
			param.setColumn("asc");
			break;
		default:// 默认：降序
			param.setColumn("desc");
			break;
		}

		// 处理分页
		if (param.getPageNo() == null || param.getPageNo().intValue() == 0) {
			param.setPageNo(1);
		}
		if (param.getPageSize() == null || param.getPageSize().intValue() == 0) {
			param.setPageSize(20);
		}
		param.setLimitBegin((param.getPageNo().intValue() - 1) * param.getPageSize().intValue());

		// 查询商品总数量
		int itemTotal = baseDao.selectOne(param, "SysitemItemMapper.selectItemCountByShopInfo");
		int totalPage = (itemTotal + param.getPageSize() - 1) / param.getPageSize();

		// 查询商品列表
		List<ShopGoodsDetailResult> productList = baseDao.selectList(param, "SysitemItemMapper.selectItemByShopInfo");

		data.setPageNo(param.getPageNo());
		data.setPageSize(param.getPageSize());
		data.setTotalPage(totalPage);
		data.setProductList(productList);

		ResBodyData result = new ResBodyData();// 最终返回的数据对象
		result.setData(data);
		result.setStatus(ServiceCatalogApiCode.SUCCESS);
		result.setMsg(ServiceCatalogApiCode.getZhMsg(ServiceCatalogApiCode.REQUEST_SUCCESS));
		return result;
	}
}
