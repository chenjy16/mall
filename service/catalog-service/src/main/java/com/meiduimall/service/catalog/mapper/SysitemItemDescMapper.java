package com.meiduimall.service.catalog.mapper;

import com.meiduimall.service.catalog.entity.SysitemItemDesc;

public interface SysitemItemDescMapper {

	int deleteByPrimaryKey(Integer itemId);

	int insert(SysitemItemDesc record);

	int insertSelective(SysitemItemDesc record);

	SysitemItemDesc selectByPrimaryKey(Integer itemId);

	int updateByPrimaryKeySelective(SysitemItemDesc record);

	int updateByPrimaryKeyWithBLOBs(SysitemItemDesc record);
}