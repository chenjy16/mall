package com.meiduimall.service.catalog.mapper;

import com.meiduimall.service.catalog.entity.SysuserUser;

public interface SysuserUserMapper {

	int deleteByPrimaryKey(Integer userId);

	int insert(SysuserUser record);

	int insertSelective(SysuserUser record);

	SysuserUser selectByPrimaryKey(Integer userId);

	int updateByPrimaryKeySelective(SysuserUser record);

	int updateByPrimaryKey(SysuserUser record);
}