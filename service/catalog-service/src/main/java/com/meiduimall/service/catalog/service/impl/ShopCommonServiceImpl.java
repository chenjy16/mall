package com.meiduimall.service.catalog.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.core.util.NumberUtils;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.service.catalog.constant.ServiceCatalogApiCode;
import com.meiduimall.service.catalog.dao.BaseDao;
import com.meiduimall.service.catalog.entity.IdAndMemId;
import com.meiduimall.service.catalog.entity.SysrateDsrWithBLOBs;
import com.meiduimall.service.catalog.entity.SysshopShopWithBLOBs;
import com.meiduimall.service.catalog.result.JsonItemDetailResultShopData;
import com.meiduimall.service.catalog.service.ShopCommonService;
import com.meiduimall.service.catalog.util.ParseSysRateDsrInfoUtil;

/**
 * 公共服务类--店铺相关
 * 
 * @author yangchang
 *
 */
@Service
public class ShopCommonServiceImpl implements ShopCommonService {

	private static Logger logger = LoggerFactory.getLogger(ShopCommonServiceImpl.class);
	
	@Autowired
	private BaseDao baseDao;

	@Override
	public JsonItemDetailResultShopData getJsonItemDetailResult_ShopData(Integer shopId,
			String memId) {

		// 查询店铺信息
		SysshopShopWithBLOBs shopWithBLOBs = baseDao.selectOne(shopId, "SysshopShopMapper.selectByPrimaryKey");

		if (shopWithBLOBs == null) {
			return null;
		}

		JsonItemDetailResultShopData shopData = new JsonItemDetailResultShopData();

		SysrateDsrWithBLOBs rateDsrWithBLOBs = baseDao.selectOne(Long.valueOf(shopId.intValue()),
				"SysrateDsrMapper.selectByPrimaryKey");
		if (rateDsrWithBLOBs != null) {
			// 反序列化数据---解析店铺信息中的：描述相符、服务态度、发货速度的分值
			try {
				float fTallyDsr = ParseSysRateDsrInfoUtil.getValue(rateDsrWithBLOBs.getTallyDsr());
				float fDeliverySpeedDsr = ParseSysRateDsrInfoUtil.getValue(rateDsrWithBLOBs.getDeliverySpeedDsr());
				float fAttitudeDsr = ParseSysRateDsrInfoUtil.getValue(rateDsrWithBLOBs.getAttitudeDsr());

				shopData.setTallyDsr(NumberUtils.formatString(fTallyDsr, 1));
				shopData.setDeliverySpeedDsr(NumberUtils.formatString(fDeliverySpeedDsr, 1));
				shopData.setAttitudeDsr(NumberUtils.formatString(fAttitudeDsr, 1));
			} catch (Exception e) {
				logger.error("反序列化数据--解析店铺信息: " + e);
				throw new ServiceException(ServiceCatalogApiCode.SHOP_DATA_EXCEPTION,
						ServiceCatalogApiCode.getZhMsg(ServiceCatalogApiCode.SHOP_DATA_EXCEPTION));
			}
		} else {
			shopData.setTallyDsr("5.0");
			shopData.setDeliverySpeedDsr("5.0");
			shopData.setAttitudeDsr("5.0");
		}

		shopData.setShopDescript(shopWithBLOBs.getShopDescript());
		shopData.setShopId(shopId.toString());
		shopData.setShopLogo(shopWithBLOBs.getShopLogo());
		shopData.setShopName(shopWithBLOBs.getShopName());
		shopData.setShopArea(shopWithBLOBs.getShopArea());

		// 开店时间
		Integer openTime = shopWithBLOBs.getOpenTime();
		if (openTime != null) {
			shopData.setOpenTime(DateFormatUtils.format(openTime.intValue() * 1000l, "yyyy年MM月dd日"));
		} else {
			shopData.setOpenTime("");
		}

		String shopType = shopWithBLOBs.getShopType();
		shopData.setShopType(shopType);
		// if ("brand".equals(shopType)) {
		// shopData.setShop_type("品牌专卖店");
		// } else if ("cat".equals(shopType)) {
		// shopData.setShop_type("类目专营店");
		// } else if ("flag".equals(shopType)) {
		// shopData.setShop_type("品牌旗舰店");
		// } else if ("self".equals(shopType)) {
		// shopData.setShop_type("运营商自营店铺");
		// } else {
		// shopData.setShop_type("未知");
		// }

		// 查询用户是否收藏了该店铺，查 表sysuser_shop_fav
		if (StringUtils.isEmpty(memId)) {
			// 没有token，不需要处理
			shopData.setIsCollect("0");
		} else {
			// 处理token
			IdAndMemId idAndMemId = new IdAndMemId();
			idAndMemId.setId(shopId.intValue());
			idAndMemId.setMemId(memId);
			int count = baseDao.selectOne(idAndMemId, "SysuserShopFavMapper.selectCountByItemIdAndMemId");
			if (count > 0) {
				shopData.setIsCollect("1");
			} else {
				shopData.setIsCollect("0");
			}
		}

		return shopData;
	}
}
