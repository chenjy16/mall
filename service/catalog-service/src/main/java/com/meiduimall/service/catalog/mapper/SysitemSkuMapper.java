package com.meiduimall.service.catalog.mapper;

import com.meiduimall.service.catalog.entity.SysitemSku;
import com.meiduimall.service.catalog.entity.SysitemSkuWithBLOBs;

public interface SysitemSkuMapper {

	int deleteByPrimaryKey(Integer skuId);

	int insert(SysitemSkuWithBLOBs record);

	int insertSelective(SysitemSkuWithBLOBs record);

	SysitemSkuWithBLOBs selectByPrimaryKey(Integer skuId);

	int updateByPrimaryKeySelective(SysitemSkuWithBLOBs record);

	int updateByPrimaryKeyWithBLOBs(SysitemSkuWithBLOBs record);

	int updateByPrimaryKey(SysitemSku record);
}