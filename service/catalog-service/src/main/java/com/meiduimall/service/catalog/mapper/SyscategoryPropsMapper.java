package com.meiduimall.service.catalog.mapper;

import com.meiduimall.service.catalog.entity.SyscategoryProps;

public interface SyscategoryPropsMapper {

	int deleteByPrimaryKey(Integer propId);

	int insert(SyscategoryProps record);

	int insertSelective(SyscategoryProps record);

	SyscategoryProps selectByPrimaryKey(Integer propId);

	int updateByPrimaryKeySelective(SyscategoryProps record);

	int updateByPrimaryKey(SyscategoryProps record);
}