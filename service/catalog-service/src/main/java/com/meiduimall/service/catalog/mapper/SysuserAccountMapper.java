package com.meiduimall.service.catalog.mapper;

import com.meiduimall.service.catalog.entity.SysuserAccount;

public interface SysuserAccountMapper {

	int deleteByPrimaryKey(Integer userId);

	int insert(SysuserAccount record);

	int insertSelective(SysuserAccount record);

	SysuserAccount selectByPrimaryKey(Integer userId);

	int updateByPrimaryKeySelective(SysuserAccount record);

	int updateByPrimaryKey(SysuserAccount record);
}