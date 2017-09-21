package com.meiduimall.service.catalog.mapper;

import com.meiduimall.service.catalog.entity.SysitemItemRecommend;

public interface SysitemItemRecommendMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(SysitemItemRecommend record);

	int insertSelective(SysitemItemRecommend record);

	SysitemItemRecommend selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(SysitemItemRecommend record);

	int updateByPrimaryKeyWithBLOBs(SysitemItemRecommend record);

	int updateByPrimaryKey(SysitemItemRecommend record);
}