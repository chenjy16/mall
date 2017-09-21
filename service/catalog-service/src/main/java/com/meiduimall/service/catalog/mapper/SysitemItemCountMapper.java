package com.meiduimall.service.catalog.mapper;

import com.meiduimall.service.catalog.entity.SysitemItemCount;

public interface SysitemItemCountMapper {

	int deleteByPrimaryKey(Integer itemId);

	int insert(SysitemItemCount record);

	int insertSelective(SysitemItemCount record);

	SysitemItemCount selectByPrimaryKey(Integer itemId);

	int updateByPrimaryKeySelective(SysitemItemCount record);

	int updateByPrimaryKey(SysitemItemCount record);
}