package com.meiduimall.service.catalog.mapper;

import com.meiduimall.service.catalog.entity.SysitemItemStatus;

public interface SysitemItemStatusMapper {

	int deleteByPrimaryKey(Integer itemId);

	int insert(SysitemItemStatus record);

	int insertSelective(SysitemItemStatus record);

	SysitemItemStatus selectByPrimaryKey(Integer itemId);

	int updateByPrimaryKeySelective(SysitemItemStatus record);

	int updateByPrimaryKey(SysitemItemStatus record);
}