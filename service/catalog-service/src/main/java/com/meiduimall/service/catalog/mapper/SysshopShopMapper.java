package com.meiduimall.service.catalog.mapper;

import com.meiduimall.service.catalog.entity.SysshopShop;
import com.meiduimall.service.catalog.entity.SysshopShopWithBLOBs;

public interface SysshopShopMapper {

	int deleteByPrimaryKey(Integer shopId);

	int insert(SysshopShopWithBLOBs record);

	int insertSelective(SysshopShopWithBLOBs record);

	SysshopShopWithBLOBs selectByPrimaryKey(Integer shopId);

	int updateByPrimaryKeySelective(SysshopShopWithBLOBs record);

	int updateByPrimaryKeyWithBLOBs(SysshopShopWithBLOBs record);

	int updateByPrimaryKey(SysshopShop record);
}