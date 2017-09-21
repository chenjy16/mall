package com.meiduimall.service.catalog.mapper;

import com.meiduimall.service.catalog.entity.SysuserShopFav;

public interface SysuserShopFavMapper {

	int deleteByPrimaryKey(Integer snotifyId);

	int insert(SysuserShopFav record);

	int insertSelective(SysuserShopFav record);

	SysuserShopFav selectByPrimaryKey(Integer snotifyId);

	int updateByPrimaryKeySelective(SysuserShopFav record);

	int updateByPrimaryKey(SysuserShopFav record);
}