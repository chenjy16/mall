package com.meiduimall.service.catalog.mapper;

import com.meiduimall.service.catalog.entity.SysitemItem;
import com.meiduimall.service.catalog.entity.SysitemItemWithBLOBs;

public interface SysitemItemMapper {

	int deleteByPrimaryKey(Integer itemId);

	int insert(SysitemItemWithBLOBs record);

	int insertSelective(SysitemItemWithBLOBs record);

	SysitemItemWithBLOBs selectByPrimaryKey(Integer itemId);

	int updateByPrimaryKeySelective(SysitemItemWithBLOBs record);

	int updateByPrimaryKeyWithBLOBs(SysitemItemWithBLOBs record);

	int updateByPrimaryKey(SysitemItem record);
}