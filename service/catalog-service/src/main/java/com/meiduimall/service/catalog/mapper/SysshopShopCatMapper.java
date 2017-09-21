package com.meiduimall.service.catalog.mapper;

import com.meiduimall.service.catalog.entity.SysshopShopCat;

public interface SysshopShopCatMapper {

	int deleteByPrimaryKey(Integer catId);

	int insert(SysshopShopCat record);

	int insertSelective(SysshopShopCat record);

	SysshopShopCat selectByPrimaryKey(Integer catId);

	int updateByPrimaryKeySelective(SysshopShopCat record);

	int updateByPrimaryKey(SysshopShopCat record);
}