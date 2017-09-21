package com.meiduimall.service.catalog.mapper;

import com.meiduimall.service.catalog.entity.SyscategoryPropValues;

public interface SyscategoryPropValuesMapper {

	int deleteByPrimaryKey(Integer propValueId);

	int insert(SyscategoryPropValues record);

	int insertSelective(SyscategoryPropValues record);

	SyscategoryPropValues selectByPrimaryKey(Integer propValueId);

	int updateByPrimaryKeySelective(SyscategoryPropValues record);

	int updateByPrimaryKey(SyscategoryPropValues record);
}