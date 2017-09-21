package com.meiduimall.service.catalog.mapper;

import com.meiduimall.service.catalog.entity.SysuserUserFav;

public interface SysuserUserFavMapper {

	int deleteByPrimaryKey(Integer gnotifyId);

	int insert(SysuserUserFav record);

	int insertSelective(SysuserUserFav record);

	SysuserUserFav selectByPrimaryKey(Integer gnotifyId);

	int updateByPrimaryKeySelective(SysuserUserFav record);

	int updateByPrimaryKeyWithBLOBs(SysuserUserFav record);

	int updateByPrimaryKey(SysuserUserFav record);
}