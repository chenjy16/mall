package com.meiduimall.service.catalog.mapper;

import com.meiduimall.service.catalog.entity.SysitemItemStore;

public interface SysitemItemStoreMapper {

	int deleteByPrimaryKey(Integer itemId);

	int insert(SysitemItemStore record);

	int insertSelective(SysitemItemStore record);

	SysitemItemStore selectByPrimaryKey(Integer itemId);

	int updateByPrimaryKeySelective(SysitemItemStore record);

	int updateByPrimaryKey(SysitemItemStore record);
}