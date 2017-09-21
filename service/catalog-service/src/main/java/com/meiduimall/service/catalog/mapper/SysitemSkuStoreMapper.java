package com.meiduimall.service.catalog.mapper;

import com.meiduimall.service.catalog.entity.SysitemSkuStore;

public interface SysitemSkuStoreMapper {

	int deleteByPrimaryKey(Integer skuId);

	int insert(SysitemSkuStore record);

	int insertSelective(SysitemSkuStore record);

	SysitemSkuStore selectByPrimaryKey(Integer skuId);

	int updateByPrimaryKeySelective(SysitemSkuStore record);

	int updateByPrimaryKey(SysitemSkuStore record);
}