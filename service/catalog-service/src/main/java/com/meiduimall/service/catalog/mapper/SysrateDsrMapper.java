package com.meiduimall.service.catalog.mapper;

import com.meiduimall.service.catalog.entity.SysrateDsr;
import com.meiduimall.service.catalog.entity.SysrateDsrWithBLOBs;

public interface SysrateDsrMapper {

	int deleteByPrimaryKey(Long shopId);

	int insert(SysrateDsrWithBLOBs record);

	int insertSelective(SysrateDsrWithBLOBs record);

	SysrateDsrWithBLOBs selectByPrimaryKey(Long shopId);

	int updateByPrimaryKeySelective(SysrateDsrWithBLOBs record);

	int updateByPrimaryKeyWithBLOBs(SysrateDsrWithBLOBs record);

	int updateByPrimaryKey(SysrateDsr record);
}