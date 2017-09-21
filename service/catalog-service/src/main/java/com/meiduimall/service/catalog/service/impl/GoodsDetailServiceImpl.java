package com.meiduimall.service.catalog.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.service.catalog.constant.ServiceCatalogApiCode;
import com.meiduimall.service.catalog.dao.BaseDao;
import com.meiduimall.service.catalog.entity.IdAndMemId;
import com.meiduimall.service.catalog.entity.SyscategoryProps;
import com.meiduimall.service.catalog.entity.SysitemItemCount;
import com.meiduimall.service.catalog.entity.SysitemItemDesc;
import com.meiduimall.service.catalog.entity.SysitemItemStatus;
import com.meiduimall.service.catalog.entity.SysitemItemStore;
import com.meiduimall.service.catalog.entity.SysitemItemWithBLOBs;
import com.meiduimall.service.catalog.entity.SysitemSkuStore;
import com.meiduimall.service.catalog.entity.SysitemSkuWithBLOBs;
import com.meiduimall.service.catalog.result.CheckGoodsResult;
import com.meiduimall.service.catalog.result.JsonItemDetailResult;
import com.meiduimall.service.catalog.result.JsonItemDetailResultItemData;
import com.meiduimall.service.catalog.result.JsonItemDetailResultPropValues;
import com.meiduimall.service.catalog.result.JsonItemDetailResultProps;
import com.meiduimall.service.catalog.result.JsonItemDetailResultShopData;
import com.meiduimall.service.catalog.result.JsonItemDetailResultSku;
import com.meiduimall.service.catalog.service.GoodsDetailService;
import com.meiduimall.service.catalog.service.ShopCommonService;
import com.meiduimall.service.catalog.util.ParseItemSpecDesUtil;
import com.meiduimall.service.catalog.util.ParseItemSpecDescBean;
import com.meiduimall.service.catalog.util.ParseItemSpecDescBean.PropValueBean;
import com.meiduimall.service.catalog.util.ParseSkuSpecDescBean;
import com.meiduimall.service.catalog.util.ParseSkuSpecDescUtil;

@Service
public class GoodsDetailServiceImpl implements GoodsDetailService {

	private static Logger logger = LoggerFactory.getLogger(GoodsDetailServiceImpl.class);

	@Autowired
	private Environment env;

	@Autowired
	private BaseDao baseDao;

	@Autowired
	private ShopCommonService shopCommonService;

	@Override
	public ResBodyData checkItemIsExistById(int itemId) {

		logger.info("根据商品ID，查询这个商品是否存在：" + itemId);

		/** TODO --------查询这个商品ID是否存在-------- */
		int count = baseDao.selectOne(itemId, "SysitemItemMapper.getItemCountByItemId");

		if (count > 0) {
			// 返回访问这个商品的详情页的地址
			ResBodyData result = new ResBodyData();

			CheckGoodsResult bean = new CheckGoodsResult();
			String base_url = env.getProperty("estore.base-url");
			String url = base_url + "/item.html?item_id=" + itemId;
			bean.setUrl(url);
			bean.setItemId(String.valueOf(itemId));

			result.setData(bean);
			result.setStatus(ServiceCatalogApiCode.SUCCESS);
			result.setMsg(ServiceCatalogApiCode.getZhMsg(ServiceCatalogApiCode.REQUEST_SUCCESS));
			return result;
		} else {
			throw new ServiceException(ServiceCatalogApiCode.NO_THIS_PRODUCT,
					ServiceCatalogApiCode.getZhMsg(ServiceCatalogApiCode.NO_THIS_PRODUCT));
		}

		/** TODO ----------查询该商品状态------- */
		/*
		 * SysitemItemStatus itemStatus = baseDao.selectOne(item_id,
		 * "SysitemItemStatusMapper.selectByPrimaryKey"); String approveStatus =
		 * itemStatus.getApproveStatus(); if ("onsale".equals(approveStatus)) {
		 * // 返回访问这个商品的详情页的地址 CheckGoodsResult bean = new CheckGoodsResult();
		 * String base_url = env.getProperty("estore.base-url"); String url =
		 * base_url + "/item.html?item_id=" + item_id; bean.setUrl(url);
		 * 
		 * result.setData(bean); result.setStatus(BaseApiCode.SUCCESS);
		 * result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.SUCCESS)); } else {
		 * result.setData(new JSONObject());
		 * result.setStatus(BaseApiCode.NONE_DATA);
		 * result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.NONE_DATA)); }
		 */
	}

	@Override
	public ResBodyData getItemDetailById(String memId, Integer itemId) {
		/**
		 * <table schema="" tableName="sysitem_item">
		 * <table schema="" tableName="sysitem_item_desc">
		 * <table schema="" tableName="sysitem_item_status">
		 * <table schema="" tableName="sysitem_item_count">
		 * <table schema="" tableName="sysitem_item_store">
		 * 
		 * <table schema="" tableName="sysitem_sku">
		 * <table schema="" tableName="sysitem_sku_store">
		 * 
		 * <table schema="" tableName="sysshop_shop">
		 * <table schema="" tableName="sysrate_dsr">
		 * 
		 * <table schema="" tableName="syscategory_props">
		 * <table schema="" tableName="syscategory_prop_values">
		 * 
		 * <table schema="" tableName="sysuser_account">
		 * <table schema="" tableName="sysuser_user_fav">
		 */

		logger.info("根据商品ID，查询商品详情：" + itemId);

		ResBodyData result = new ResBodyData();// 最终返回的数据对象

		// 根据item_id查找sysitem_item表中对应的商品记录信息
		SysitemItemWithBLOBs itemWithBLOBs = baseDao.selectOne(itemId, "SysitemItemMapper.selectByPrimaryKey");
		if (itemWithBLOBs == null) {// 查询不到该商品
			throw new ServiceException(ServiceCatalogApiCode.NO_THIS_PRODUCT,
					ServiceCatalogApiCode.getZhMsg(ServiceCatalogApiCode.NO_THIS_PRODUCT));
		}

		JsonItemDetailResult jsonResult = new JsonItemDetailResult();
		// ----------1、开始拼接商品规格数据-----------
		List<JsonItemDetailResultProps> itemPropsList = new ArrayList<JsonItemDetailResultProps>();

		// 反序列化数据---解析商品的规格参数---读取sysitem_item表的spec_desc字段
		// 最终得到每一个规格，以及规格对应的规格属性。比如：[{4颜色：43黑色，44咖啡色，51军绿色},{},{}]
		List<ParseItemSpecDescBean> parseList = null;
		try {
			parseList = ParseItemSpecDesUtil.parse(itemWithBLOBs.getSpecDesc());
		} catch (Exception e) {
			logger.error("解析商品的规格参数，service异常： " + e);
			throw new ServiceException(ServiceCatalogApiCode.SPEC_DESC_DATA_EXCEPTION,
					ServiceCatalogApiCode.getZhMsg(ServiceCatalogApiCode.SPEC_DESC_DATA_EXCEPTION));
		}
		if (parseList != null && !parseList.isEmpty()) {
			for (int i = 0; i < parseList.size(); i++) {

				// 获取每一组规格。比如：[{4颜色：43黑色，44咖啡色，51军绿色},{},{}]
				ParseItemSpecDescBean parserItemSpecDescBean = parseList.get(i);

				if (parserItemSpecDescBean != null) {
					// 规格
					JsonItemDetailResultProps itemProps = new JsonItemDetailResultProps();

					// 根据规格ID查找规格名称。查找表syscategory_props
					// 比如上面只得到了编号4，并没有得到4对应的名称颜色
					// TODO 待优化
					SyscategoryProps categoryProps = baseDao.selectOne(parserItemSpecDescBean.getPropId(),
							"SyscategoryPropsMapper.selectByPrimaryKey");

					itemProps.setPropId(parserItemSpecDescBean.getPropId().toString());
					itemProps.setPropName(categoryProps.getPropName());

					// 遍历该规格下的每一种规格属性
					// 获取规格编号4颜色，对应的属性值：43黑色，44咖啡色，51军绿色，整理数据
					List<PropValueBean> propValueBeanList = parserItemSpecDescBean.getPropValueBeanList();
					if (propValueBeanList != null && propValueBeanList.size() > 0) {
						List<JsonItemDetailResultPropValues> propList = new ArrayList<JsonItemDetailResultPropValues>();
						for (int j = 0; j < propValueBeanList.size(); j++) {
							PropValueBean propValueBean = propValueBeanList.get(j);
							if (propValueBean != null) {
								JsonItemDetailResultPropValues propValues = new JsonItemDetailResultPropValues();
								Integer specValueId = propValueBean.getSpecValueId();
								String specValue = propValueBean.getSpecValue();
								if (specValueId != null) {
									propValues.setPropValueId(specValueId.toString());
								} else {
									propValues.setPropValueId("0");
								}
								propValues.setPropValue(specValue);
								propList.add(propValues);
							} else {
								// 数据异常
							}
						}
						// 给该规格(比如4颜色)添加对应的规格属性列表
						itemProps.setPropList(propList);
					} else {
						// 只有规格名称，没有规格对应的规格属性
						continue;
					}
					itemPropsList.add(itemProps);
				} else {
					// 数据异常
				}
			} // 循环结束
		} else {
			// 查找不到规格参数
		}
		jsonResult.setItemPropsList(itemPropsList);

		// --------2、开始拼接商品信息数据-----------
		JsonItemDetailResultItemData itemData = new JsonItemDetailResultItemData();

		// 获取商品详情的HTML页面地址，查找sysitem_item_desc表
		SysitemItemDesc itemDesc = baseDao.selectOne(itemId, "SysitemItemDescMapper.selectByPrimaryKey");
		String htmlDetailUrl = "";
		String wapDesc = itemDesc.getWapDesc();
		String pcDesc = itemDesc.getPcDesc();
		if (!StringUtils.isEmpty(wapDesc)) {
			htmlDetailUrl = wapDesc;
		} else {
			htmlDetailUrl = pcDesc;
		}
		itemData.setHtmlDetailUrl(htmlDetailUrl);

		// 获取商品销量和商品评论信息，查找表sysitem_item_count
		SysitemItemCount itemCount = baseDao.selectOne(itemId, "SysitemItemCountMapper.selectByPrimaryKey");
		// 评论数量
		Integer rateCount = itemCount.getRateCount();
		if (rateCount == null) {
			itemData.setRateCount("0");
		} else {
			itemData.setRateCount(rateCount.toString());
		}

		// 商品销量=虚拟销量+实际销量
		Integer soldQuantity = itemCount.getSoldQuantity();
		Integer vituralQuantity = itemCount.getVituralQuantity();
		int sold = 0;
		int vitural = 0;
		if (soldQuantity != null) {
			sold = soldQuantity;
		}
		if (vituralQuantity != null) {
			vitural = vituralQuantity;
		}
		int rate_count = sold + vitural;
		itemData.setSalesVolume(String.valueOf(rate_count));

		// 查询商品状态信息，表sysitem_item_status
		SysitemItemStatus itemStatus = baseDao.selectOne(itemId, "SysitemItemStatusMapper.selectByPrimaryKey");
		// 商品状态
		String approveStatus = itemStatus.getApproveStatus();
		itemData.setApproveStatus(approveStatus);
		// if ("onsale".equals(approveStatus)) {
		// itemData.setApprove_status("出售中");
		// } else {
		// itemData.setApprove_status("库中");
		// }

		// 商品上架时间
		Integer listTime = itemStatus.getListTime();
		if (listTime != null) {
			itemData.setListTime(DateFormatUtils.format(listTime.intValue() * 1000l, "yyyy-MM-dd HH:mm:ss"));
		} else {
			itemData.setListTime("");
		}

		itemData.setBn(itemWithBLOBs.getBn());
		itemData.setImageDefaultId(itemWithBLOBs.getImageDefaultId());
		itemData.setItmeId(String.valueOf(itemId));
		itemData.setListImage(itemWithBLOBs.getListImage());
		itemData.setPoint(itemWithBLOBs.getPoint().toString());
		itemData.setPrice(itemWithBLOBs.getPrice().toString());

		String subTitle = itemWithBLOBs.getSubTitle();
		String title = itemWithBLOBs.getTitle();
		if (StringUtils.isEmpty(subTitle)) {
			subTitle = title;
		}
		itemData.setSubTitle(subTitle);
		itemData.setTitle(title);
		BigDecimal weight = itemWithBLOBs.getWeight();
		if (weight != null) {
			itemData.setWeight(weight.toString());
		} else {
			itemData.setWeight("");
		}

		if (itemWithBLOBs.getIsShowWeight() != null) {
			itemData.setIsShowWeight(itemWithBLOBs.getIsShowWeight().toString());
		} else {
			itemData.setIsShowWeight("0");
		}

		// 查询商品的库存，表sysitem_item_store
		SysitemItemStore itemStore = baseDao.selectOne(itemId, "SysitemItemStoreMapper.selectByPrimaryKey");
		Integer store = itemStore.getStore();
		Integer freez = itemStore.getFreez();
		if (store != null) {
			if (freez != null) {
				int item_store = store.intValue() - freez.intValue();
				itemData.setItemStore(String.valueOf(item_store));
			} else {
				itemData.setItemStore(store.toString());
			}
		} else {
			itemData.setItemStore("0");
		}

		// 检查用户是否收藏了该商品
		if (StringUtils.isEmpty(memId)) {
			// 没有token，不需要处理
			itemData.setIsCollect("0");
		} else {
			// 处理token
			IdAndMemId idAndMemId = new IdAndMemId();
			idAndMemId.setId(itemId.intValue());
			idAndMemId.setMemId(memId);
			int count = baseDao.selectOne(idAndMemId, "SysuserUserFavMapper.selectCountByItemIdAndMemId");
			if (count > 0) {
				itemData.setIsCollect("1");
			} else {
				itemData.setIsCollect("0");
			}
		}
		jsonResult.setItemData(itemData);

		// -------------3、开始拼接商品SKU数据-----------
		List<JsonItemDetailResultSku> skuList = new ArrayList<JsonItemDetailResultSku>();

		// 查sysitem_sku表，根据item_id查找该商品对应的SKU列表
		List<SysitemSkuWithBLOBs> itemSkuWithBLOBsList = baseDao.selectList(itemId, "SysitemSkuMapper.selectByItemId");

		if (itemSkuWithBLOBsList != null && !itemSkuWithBLOBsList.isEmpty()) {
			for (int i = 0; i < itemSkuWithBLOBsList.size(); i++) {
				SysitemSkuWithBLOBs sysitemSkuWithBLOBs = itemSkuWithBLOBsList.get(i);
				if (sysitemSkuWithBLOBs == null) {
					continue;// can not reach
				}
				JsonItemDetailResultSku resultSku = new JsonItemDetailResultSku();

				resultSku.setPoint(sysitemSkuWithBLOBs.getPoint().toString());
				resultSku.setPrice(sysitemSkuWithBLOBs.getPrice().toString());
				resultSku.setSkuId(sysitemSkuWithBLOBs.getSkuId().toString());

				String skuStatus = sysitemSkuWithBLOBs.getStatus();
				resultSku.setStatus(skuStatus);
				// if ("normal".equals(sku_status)) {
				// result_sku.setStatus("正常");
				// } else {
				// result_sku.setStatus("删除");
				// }

				if (sysitemSkuWithBLOBs.getWeight() != null) {
					resultSku.setWeight(sysitemSkuWithBLOBs.getWeight().toString());
				} else {
					resultSku.setWeight("0");
				}

				// 查sysitem_sku_store表，获取每一个SKU对应的库存信息
				SysitemSkuStore skuStore = baseDao.selectOne(sysitemSkuWithBLOBs.getSkuId(),
						"SysitemSkuStoreMapper.selectByPrimaryKey");
				int intSkuStore = skuStore.getStore().intValue() - skuStore.getFreez().intValue();
				resultSku.setSkuStore(String.valueOf(intSkuStore));

				// 反序列化数据---解析每一个商品对应的SKU数据
				List<ParseSkuSpecDescBean> skuSpecDescBeanList = null;
				try {
					skuSpecDescBeanList = ParseSkuSpecDescUtil.parse(sysitemSkuWithBLOBs.getSpecDesc());
				} catch (Exception e) {
					logger.error("解析每一个商品对应的SKU数据，service异常： " + e);
					throw new ServiceException(ServiceCatalogApiCode.SKU_DATA_EXCEPTION,
							ServiceCatalogApiCode.getZhMsg(ServiceCatalogApiCode.SKU_DATA_EXCEPTION));
				}

				if (skuSpecDescBeanList != null && !skuSpecDescBeanList.isEmpty()) {
					StringBuilder sb = new StringBuilder();
					for (int j = 0; j < skuSpecDescBeanList.size(); j++) {
						Integer propValueId = skuSpecDescBeanList.get(j).getPropValueId();
						sb.append(propValueId + "_");
					}
					if (sb.length() > 1) {
						String propValueIds = sb.substring(0, sb.length() - 1);
						resultSku.setPropValueIds(propValueIds);
					} else {
						// 数据异常，没有找到prop_value_id(一般不会发生)
					}
				} else {
					// 数据异常，反序列化失败
				}
				skuList.add(resultSku);
			}
		} else {
			// 没有SKU，不需要处理
		}
		jsonResult.setSkuList(skuList);

		// -------------4、开始拼接商家数据-----------
		Integer shopId = itemWithBLOBs.getShopId();
		JsonItemDetailResultShopData shopData = shopCommonService.getJsonItemDetailResult_ShopData(shopId, memId);

		jsonResult.setShopData(shopData);

		result.setData(jsonResult);
		result.setStatus(ServiceCatalogApiCode.SUCCESS);
		result.setMsg(ServiceCatalogApiCode.getZhMsg(ServiceCatalogApiCode.REQUEST_SUCCESS));

		return result;
	}

}
